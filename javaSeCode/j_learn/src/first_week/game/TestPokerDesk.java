package first_week.game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * ClassName: TestPokerDesk
 * Package: first_week.game
 * Description:
 *
 * @Author fly
 * @Create 2026/1/10 20:41
 * @Version 1.0
 */
public class TestPokerDesk {
    private Poker poker = new PokerDesk();
    @Test
    public void testCreateCards(){
        System.out.println(poker);
    }

    @Test
    public void testCheckPoker(){
        System.out.println(poker.checkPoker());
    }


    // 测试发牌单张发牌后在对排序进行测试
    @Test
    public void testDeal(){
        // 创建三个集合，执行发牌操作
        List<Card> cards1 = new ArrayList<>(20);
        List<Card> cards2 = new ArrayList<>(20);
        List<Card> cards3 = new ArrayList<>(20);
        // 模拟斗地主发牌，留3张底牌 = 51 每个人17张牌
        AtomicInteger i = new AtomicInteger();
        // 创建一个stream流，让他一直执行发牌操作
        Stream.generate(poker::deal)
                // 最多发51张牌，前提是我们排堆中有51张，如果没有，就把剩余的牌都发了
                .limit(Math.min(51, poker.getAllCards().size()))
                .takeWhile(Optional::isPresent)
                .map(Optional::get)
                .filter(card -> {
                    if (i.get() % 3 == 0) {
                        cards1.add(card);
                    }
                    if (i.get() % 3 == 1) {
                        cards2.add(card);
                    }
                    if (i.get() % 3 == 2) {
                        cards3.add(card);
                    }
                    i.getAndIncrement();
                    return true;
                }).count();
        System.out.println(cards1.size());
        System.out.println(cards2.size());
        System.out.println(cards3.size());
        System.out.println(cards1);
        System.out.println(cards2);
        System.out.println(cards3);
        // 验证剩余三张牌
        System.out.println(poker.checkPoker());

        poker.getSortedCards(cards1);
        System.out.println(cards1);
        poker.getSortedCards(cards2);
        System.out.println(cards2);
        poker.getSortedCards(cards3);
        System.out.println(cards3);


    }

    @Test
    public void testDealFromCount(){
        List<Card> cards1 = new ArrayList<>(3);
        cards1.addAll(poker.deal(3));
        System.out.println(cards1);

    }




}
