package first_week;

import org.junit.Test;

import java.util.Comparator;

/**
 * ClassName: Day04
 * Package: first_week
 * Description:
 *
 * @Author fly
 * @Create 2026/1/8 22:36
 * @Version 1.0
 */
public class Day04 {

    @Test
    public void testFindMaxValue(){
        int[] arr1 = new int[0];
        assert (-1 == findMaxValue(arr1));
        int[] arr2 = null;
        assert (-1 == findMaxValue(arr2));
        int[] arr3 = {1, 5, 6, 2, 8, 7, 1, 0};
        assert (8 == findMaxValue(arr3));
        int[] arr4 = new int[2];
        assert (0 == findMaxValue(arr4));
    }

    @Test
    public void testFindMaxValueT(){
        String[] arr1 = new String[0];
        assert(null == findMaxValue(arr1, String::compareTo));

        String[] arr2 = null;
        assert (null == findMaxValue(arr2, String::compareTo));
        String[] arr3 = {"1", "3", "5", "a", ".", ";", "zhang"};
        assert ("zhang".equals(findMaxValue(arr3, String::compareTo)));
        String[] arr4 = {"1", null, ".", ";", "zhang", null};
        assert ("zhang".equals(findMaxValue(arr4, String::compareTo)));
        String[] arr5 = {null, null};
        assert (null == findMaxValue(arr5, String::compareTo));
        String[] arr6 = {"1"};
        assert ("1".equals(findMaxValue(arr6, String::compareTo)));
    }

    public int findMaxValue(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i ++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public <T>T findMaxValue(T[] t, Comparator<T> comparator) {
        if (t == null || t.length == 0) {
            return null;
        }
        if (t.length == 1) {
            return t[0];
        }
        T max = t[0];
        for (int i = 1; i < t.length; i++) {
            if (null == t[i]) {
                continue;
            }
            int res = comparator.compare(max, t[i]);
            if (res < 0) {
                max = t[i];
            }
        }
        return max;
    }
}
