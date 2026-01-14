package first_week.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * ClassName: TestPerson
 * Package: first_week.lambda
 * Description:
 *
 * @Author fly
 * @Create 2026/1/12 16:15
 * @Version 1.0
 */
public class TestPerson {
    List<Person>  persons;
    @Test
    public void test1(){
        persons = new ArrayList<>();
        persons.add(new Person("zhangsan", 25, Sex.Male));
        persons.add(new Person("lisi", 18, Sex.Male));
        persons.add(new Person("wangwu", 40, Sex.Male));
        persons.add(new Person("zhaoliu", 16, Sex.Male));
        persons.add(new Person("zhiqiang", 30, Sex.Male));
        findPerson(this.persons, new CheckPerson() {
            @Override
            public boolean tester(Person person) {
                if (person.getAge() >= 18 && person.getAge() <= 40) {
                    return true;
                }
                return false;
            }
        });
    }


    @Test
    public void test2(){
        persons = new ArrayList<>();
        persons.add(new Person("zhangsan", 25, Sex.Male));
        persons.add(new Person("lisi", 18, Sex.Male));
        persons.add(new Person("wangwu", 40, Sex.Male));
        persons.add(new Person("zhaoliu", 16, Sex.Male));
        persons.add(new Person("zhiqiang", 30, Sex.Male));

        findPerson(this.persons, (CheckPerson) p -> p.getAge() >= 18 && p.getAge() < 40);
    }
    
    @Test
    public void test3(){
        persons = new ArrayList<>();
        persons.add(new Person("zhangsan", 25, Sex.Male));
        persons.add(new Person("lisi", 18, Sex.Male));
        persons.add(new Person("wangwu", 40, Sex.Male));
        persons.add(new Person("zhaoliu", 16, Sex.Male));
        persons.add(new Person("zhiqiang", 30, Sex.Male));
        findPerson(this.persons, p -> p.getAge() >= 18 && p.getAge() < 40, System.out::println);
    }

    @Test
    public void test4(){
        persons = new ArrayList<>();
        persons.add(new Person("zhangsan", 25, Sex.Male));
        persons.add(new Person("lisi", 18, Sex.Male));
        persons.add(new Person("wangwu", 40, Sex.Male));
        persons.add(new Person("zhaoliu", 16, Sex.Male));
        persons.add(new Person("zhiqiang", 30, Sex.Male));

        persons.stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() < 40)
                .map(Person::getName)
                .forEach(System.out::println);

    }

    @Test
    public void test5(){
        persons = new ArrayList<>();
        persons.add(new Person("zhangsan", 25, Sex.Male));
        persons.add(new Person("lisi", 18, Sex.Male));
        persons.add(new Person("wangwu", 40, Sex.Male));
        persons.add(new Person("zhaoliu", 16, Sex.Male));
        persons.add(new Person("zhiqiang", 30, Sex.Male));
        comparePerson(persons, Integer::compareTo);
        System.out.println(persons);
    }

    public void findPerson(List<Person> persons, CheckPerson checkPerson){
        for(Person person:persons){

            if(checkPerson.tester(person)){
                System.out.println(person);
            }
        }

    }

    public void comparePerson(List<Person> persons, BiFunction<Integer, Integer, Integer> compare) {
        for (int i = 0; i < persons.size() - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < persons.size() - i - 1; j++) {
                if (compare.apply(persons.get(j).getAge(), persons.get(j + 1).getAge()) == 1) {
                    Person temp = persons.get(j);
                    persons.set(j, persons.get(j + 1));
                    persons.set(j + 1, temp);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public void findPerson(List<Person> persons, Predicate<Person> predicate, Consumer<Person> consumer){
        for(Person person:persons){
            if(predicate.test(person)){
                consumer.accept(person);
            }
        }
    }
    public void findPerson(List<Person> persons, Predicate<Person> predicate){
        for(Person person:persons){
            if(predicate.test(person)){
                System.out.println(person);
            }

        }
    }


}
