package first_week.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: Rank
 * Package: first_week.game
 * Description:
 * 定义整个牌组的牌的字面量
 * @Author fly
 * @Create 2026/1/10 01:03
 * @Version 1.0
 */
public record Rank(String symbol, Integer value) {
    // 预定义所有牌面的名次和对应的值
    private static final Rank ACE = new Rank("A", 14);
    private static final Rank TWO = new Rank("2", 2);
    private static final Rank THREE = new Rank("3", 3);
    private static final Rank FOUR = new Rank("4", 4);
    private static final Rank FIVE = new Rank("5", 5);
    private static final Rank SIX = new Rank("6", 6);
    private static final Rank SEVEN = new Rank("7", 7);
    private static final Rank EIGHT = new Rank("8", 8);
    private static final Rank NINE = new Rank("9", 9);
    private static final Rank TEN = new Rank("10", 10);
    private static final Rank JACK = new Rank("J", 11);
    private static final Rank QUEEN = new Rank("Q", 12);
    private static final Rank KING = new Rank("K", 13);
    private static final Rank LITTLE_JOKER = new Rank("小王", 15);
    private static final Rank BIG_JOKER = new Rank("大王", 16);

    // 将牌分为普通牌和特殊牌分别装入不同集合
    private static final Rank[] NORMAL_CARDS = {ACE, TWO, THREE, FOUR, FIVE, SIX,  SEVEN, EIGHT, NINE, TEN,  JACK,  QUEEN, KING};
    private static final Rank[] JOKER_CARDS = {LITTLE_JOKER, BIG_JOKER};

    public static Rank[] getNormalCards() {
        return NORMAL_CARDS.clone();
    }

    public static Rank[] getJokerCards() {
        return JOKER_CARDS.clone();
    }

}
