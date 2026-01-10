package first_week.game;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName: JokerCard
 * Package: first_week.game
 * Description:
 *
 * @Author fly
 * @Create 2026/1/10 00:48
 * @Version 1.0
 */
public record JokerCard(Rank rank) implements Card, Comparable<JokerCard> {
    private static final List<Rank> JOKER_CARDS = Arrays.asList(Rank.getJokerCards());
    private Boolean verifyRank(Rank rank) {
        return JOKER_CARDS.contains(rank);
    }

    @Override
    public String display() {
        if (verifyRank(rank)) {
            return rank.symbol();
        }
        return null;
    }

    @Override
    public int compareTo(JokerCard o) {
        if (verifyRank(rank) && o.verifyRank(rank)) {
            return this.rank.value() - o.rank.value();
        }
        return -1;
    }

    @Override
    public String toString() {
        return display();
    }
}
