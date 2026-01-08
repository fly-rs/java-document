package first_week;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * ClassName: Day02
 * Package: first_week
 * Description:
 *
 * @Author fly
 * @Create 2026/1/8 21:33
 * @Version 1.0
 */
public class Day02 {

    @Test
    public void testCalcBmi(){
        // 测试用例
        double weight1 = 75.0;
        double height1 = 1.85;
        // 正常
        calcBmi(weight1, height1);
        double weight2 = 50.0;
        double height2 = 1.85;
        // 不足
        calcBmi(weight2, height2);
        double weight3 = 85.0;
        double height3 = 1.85;
        // 超重
        calcBmi(weight3, height3);
        double weight4 = 100.0;
        double height4 = 1.85;
        // 肥胖
        calcBmi(weight4, height4);
        double weight5 = 85.0;
        double height5 = 0.4;
        // 身高不满足要求
        calcBmi(weight5, height5);
        double weight6 = 5.01;
        double height6 = 1.85;
        // 体重不满足要求
        calcBmi(weight6, height6);
        double weight7 = -1;
        double height7 = 1.85;
        // 有负数不行
        calcBmi(weight7, height7);
    }

    /**
     * 计算BMI指数的方法
     * @param w 体重,体重不得低于5kg，计算时只保留一位小数
     * @param h 身高，不得低于0.4m，计算时保留两位小数
     */
    public void calcBmi(double w, double h) {
        if (w < 0 || h < 0) {
            System.out.println("回炉重造了？");
            return;
        }
        if ((h - 0.30) < 0.101) {
            System.out.println("我不允许你这么矮");
            return;
        }
        if ((w - 5.0) < 0.01) {
            System.out.println("我不允许你会飞");
            return;
        }
        BigDecimal weight = BigDecimal.valueOf(w).setScale(1, RoundingMode.UP);
        System.out.print("\n请输入您的身高(m): ");
        BigDecimal height = BigDecimal.valueOf(h).setScale(2, RoundingMode.UP);
        BigDecimal hh = height.multiply(height).setScale(2, RoundingMode.UP);
        BigDecimal bmi = weight.divide(hh, 2, RoundingMode.UP).setScale(2, RoundingMode.UP);
        System.out.println("您的BMI为: " + bmi);
        if (bmi.subtract(new BigDecimal("18.5")).doubleValue() < 0.0) {
            System.out.println("体重不足");
        } else if (bmi.subtract(new BigDecimal("24.0")).doubleValue() < 0.0) {
            System.out.println("体重正常");
        } else if (bmi.subtract(new BigDecimal("28.0")).doubleValue() < 0.0) {
            System.out.println("超重");
        } else {
            System.out.println("肥胖");
        }
    }
}
