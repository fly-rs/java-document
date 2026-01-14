package first_week.linkedlist;


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * ClassName: MyLinkedList
 * Package: first_week.linkedlist
 * Description:
 *
 * @Author fly
 * @Create 2026/1/10 23:10
 * @Version 1.0
 */
public class MyLinkedList<T>{
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public MyLinkedList() {
    }


    public void add(T t) {
        // 判断列表是否已经存在
        if (head == null) {
            // 不存在
            head = new Node<>(t, null, null);
            tail = head;
        } else {
            Node node = new Node(t, null, tail);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void show() {
        ListIterator iterator = new ListIterator(size, head);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static class ListIterator<T> implements Iterator<T> {
        int index;
        // 获取所有节点
        final Node<T> node;
        Node<T> current;
        public ListIterator(int size, Node<T> node) {
            this.index = size;
            this.node = node;
        }


        @Override
        public boolean hasNext() {
            if (index-- > 0) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (current == null) {
                current = node;
            }
            T res = current.item;
            current = current.next;
            return res;
        }
    }


    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        public Node(T item, Node<T> next, Node<T> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
