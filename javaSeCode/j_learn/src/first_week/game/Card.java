package first_week.game;

/**
 * ClassName: Card
 * Package: first_week.game
 * Description:
 * 定义扑克牌的方法
 * 包括：
 * 展示一张牌
 * 比较牌的大小
 * toString
 *
 *
 *
 *
 * @Author fly
 * @Create 2026/1/10 00:47
 * @Version 1.0
 */
public sealed interface Card permits NormalCard, JokerCard {
    String display();
    @Override
    String toString();
}

