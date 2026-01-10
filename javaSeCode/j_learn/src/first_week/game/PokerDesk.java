package first_week.game;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ClassName: PokerDesk
 * Package: first_week.game
 * Description:
 *
 * @Author fly
 * @Create 2026/1/10 02:48
 * @Version 1.0
 */
public class PokerDesk implements Poker {
    private List<Card> cards;

    {
        this.cards = createCards();
        if (!this.cards.isEmpty()) {
            shuffle();
        }
    }

    public PokerDesk() {

    }

    @Override
    public List<Card> createCards() {
        // 通过Stream流，将花色和牌面组合
        Stream<Card> normalStream = Arrays.stream(Suit.getSuitList())
                .flatMap(suit -> Arrays.stream(Rank.getNormalCards())
                        .map(rank -> new NormalCard(suit, rank))
                );
        // 通过Stream流，将jokerList转换为Card的Stream流
        Stream<Card> jokerStream = Arrays.stream(Rank.getJokerCards())
                .map(JokerCard::new);

        // 将Joker和normal组合在一起
        return Stream.concat(normalStream, jokerStream)
                .collect(Collectors.toList());
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    @Override
    public Optional<Card> deal() {
        if (this.cards.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.cards.removeLast());
    }

    @Override
    public List<Card> deal(int count) {
        return Stream.generate(this::deal).limit(Math.min(this.cards.size(), count))
                .takeWhile(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> getAllCards() {
        return List.copyOf(this.cards);
    }

    @Override
    public void reset() {
        this.cards.clear();
        this.cards.addAll(createCards());
    }

    @Override
    public void getSortedCards(List<Card> cards) {
        List<Card> list = cards.stream()
                .sorted((c1, c2) -> {
                    return switch (c1) {
                        case NormalCard n1 -> switch (c2) {
                            case NormalCard n2 -> n1.compareTo(n2);
                            case JokerCard ignore -> -1;
                        };
                        case JokerCard j1 -> switch (c2) {
                            case NormalCard ignore -> 1;
                            case JokerCard j2 -> j1.compareTo(j2);
                        };
                    };
                }).collect(Collectors.toList());
                cards.clear();
                cards.addAll(list);
    }

    @Override
    public Optional<Card> findCard(Suit suit, Rank rank) {
        return this.cards.stream()
                .filter(card -> {
                    return switch (card) {
                        case NormalCard normal ->
                                normal.display().equals(suit.image().concat(rank.symbol()));
                        case JokerCard joker ->
                            joker.rank().equals(rank);
                    };
                }).findFirst();
    }

    @Override
    public String toString() {
        return "PokerDesk{" +
                "cards=" + this.cards.size() +
                '}';
    }

    @Override
    public String checkPoker() {
        return this.cards.toString();
    }
}
