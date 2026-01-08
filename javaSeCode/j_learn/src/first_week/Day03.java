package first_week;

/**
 * ClassName: Day03
 * Package: first_week
 * Description:
 *
 * @Author fly
 * @Create 2026/1/8 22:28
 * @Version 1.0
 */
public class Day03 {
    public static void main(String[] args) {
        // 打印九九乘法表
        System.out.println("1 * 1 = 1");
        for (int i = 2; i <= 9; i ++) {
            for (int j = 1; j <= i; j ++) {
                System.out.print(j + " * " + i + " = " + (i * j) + "\t\t");
            }
            System.out.println();
        }
    }
}
