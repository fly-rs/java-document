package first_week;

/**
 * ClassName: User
 * Package: first_week
 * Description:
 *
 * @Author fly
 * @Create 2026/1/9 14:07
 * @Version 1.0
 */
public class User {
    private String name;
    private int age;
    private Phone phone;
    private static int objectNum;
    public User(String name, int age, Phone phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public User() {}

    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }
}
