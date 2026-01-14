package first_week.linkedlist;

import org.junit.Test;

/**
 * ClassName: TestList
 * Package: first_week.linkedlist
 * Description:
 *
 * @Author fly
 * @Create 2026/1/10 23:52
 * @Version 1.0
 */
public class TestList {
    @Test
    public void testMyLinkerList(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.show();
    }
}
