package first_week.lambda;

/**
 * ClassName: Person
 * Package: first_week.lambda
 * Description:
 *
 * @Author fly
 * @Create 2026/1/12 16:14
 * @Version 1.0
 */
public class Person {
    private String name;
    private Integer age;
    private Sex gender;
    private static Integer createObjNum;
    public static final String ID = "123456878952222";

    public Person() {
    }

    public Person(String name, Integer age, Sex gender) {
        this.gender = gender;
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
