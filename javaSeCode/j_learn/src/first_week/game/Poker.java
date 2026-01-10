package first_week.game;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: Poker
 * Package: first_week.game
 * Description:
 *定义了游戏中扑克牌的创建，发牌，洗牌，重置牌堆，对牌进行排序，获取剩余牌数
 * @Author fly
 * @Create 2026/1/10 02:31
 * @Version 1.0
 */
public interface Poker {
    /**
     * 创建牌
     * @return 所有牌的集合
     */
    List<Card> createCards();

    /**
     * 洗牌，对当前牌集合顺序进行打乱
     */
    void shuffle();

    /**
     * 发牌
     * @return 返回一张牌
     */
    Optional<Card> deal();

    /**
     * 发多张牌
     * @param count 发牌数量
     * @return 牌集合
     */
    List<Card> deal(int count);

    /**
     * 获取所有牌
     * @return 返回牌组
     */
    List<Card> getAllCards();

    /**
     * 重置牌堆
     */
    void reset();

    /**
     * 对玩家的牌进行排序
     * @param cards 返回排序后的牌
     */
    void getSortedCards(List<Card> cards);


    /**
     * 查找指定牌
     * @param suit 花色
     * @param rank 牌面值
     * @return 查找结果找到-》Card 找不到-〉Null
     */
    Optional<Card> findCard(Suit suit, Rank rank);

    @Override
    String toString();

    /**
     * 验牌
     * @return  返回所有牌的内容
     */
    String checkPoker();
}
