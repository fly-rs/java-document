package first_week.nestedclasses;

import org.junit.Test;

/**
 * ClassName: NestedClass
 * Package: first_week.nestedclasses
 * Description:
 *
 * @Author fly
 * @Create 2026/1/13 00:58
 * @Version 1.0
 */
public class NestedClass {
    private String name;



    class InnerClass {
        String name;
        public void sayName() {
            NestedClass.this.name = this.name;
            System.out.println(name);
        }
    }

    static class StaticInnerClass {
        public void sayName(NestedClass o) {
            System.out.println(o.name);
        }
    }
}

class TestInnerClass {
    public static void main(String[] args) {
        NestedClass obj = new NestedClass();
        NestedClass.InnerClass innerClass = obj.new InnerClass();
        innerClass.sayName();
        NestedClass.StaticInnerClass staticInnerClass = new NestedClass.StaticInnerClass();
        staticInnerClass.sayName(obj);
    }
}
