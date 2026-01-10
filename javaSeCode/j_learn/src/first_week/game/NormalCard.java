package first_week.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ClassName: NormalCard
 * Package: first_week.game
 * Description:
 * 这个类用于将花色和牌面值组合起来，不包括大小王
 *
 *
 *
 * @Author fly
 * @Create 2026/1/10 00:48
 * @Version 1.0
 */
public record NormalCard(Suit suit, Rank rank) implements Card, Comparable<NormalCard>{

    private static final List<Rank> NORMAL_CARDS = Arrays.asList(Rank.getNormalCards());

    private Boolean verifyRank(Rank rank) {
        return NORMAL_CARDS.contains(rank);
    }

    @Override
    public String display() {
        if (verifyRank(rank)) {
            return suit.image() + rank.symbol();
        }
        return null;
    }

    @Override
    public int compareTo(NormalCard o) {
        int res = compare(o);
        if (res == 0) {
            // 说明在数值上相等了，用花色排序
            return Suit.getSuitCount(this.suit) - Suit.getSuitCount(o.suit);
        }
        return res;
    }


    private int compare(NormalCard o) {
        if (verifyRank(rank) && o.verifyRank(rank)) {
            return this.rank.value() - o.rank.value();
        }
        return  -1;
    }


    @Override
    public String toString() {
        return display();
    }
}
