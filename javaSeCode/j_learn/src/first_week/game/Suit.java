package first_week.game;

import java.util.*;

/**
 * ClassName: Suit
 * Package: first_week.game
 * Description:
 *这个记录用于存储花色的内容，花色只允许有四种选项梅花，方片，红桃，黑桃
 *
 * @Author fly
 * @Create 2026/1/10 00:51
 * @Version 1.0
 */
public record Suit(String image, String name) {
    private static final Suit HEARTS = new Suit("♥", "红桃");
    private static final Suit DIAMONDS = new Suit("♦", "方块");
    private static final Suit CLUBS = new Suit("♣", "梅花");
    private static final Suit SPADES = new Suit("♠", "黑桃");

    private static final Suit[] SUIT_LIST = {HEARTS, DIAMONDS, CLUBS, SPADES};
    private static final Map<Suit, Integer>  SUIT_COUNT_MAP = new HashMap<Suit, Integer>();
    static {
        SUIT_COUNT_MAP.put(HEARTS, 1);
        SUIT_COUNT_MAP.put(DIAMONDS, 2);
        SUIT_COUNT_MAP.put(CLUBS, 4);
        SUIT_COUNT_MAP.put(SPADES, 3);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Suit suit = (Suit) o;
        return Objects.equals(name, suit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public static Suit[] getSuitList() {
        return SUIT_LIST.clone();
    }

    public static Integer getSuitCount(Suit suit) {
        return SUIT_COUNT_MAP.get(suit);
    }


}
