## Java类和对象

### 一、类的声明

类的声明可以包含以下组件

1. Modifiers  修饰符
2. the class name 类名
3. The name of the class's parent.  父类名
4. A comma-separated list of interfaces implemented by the class. 接口
5. The class body   类体

```java
[public] class Myclass extends MySuperClass implements YourInterfaceOne, YourInterfaceTwo {
		// field, constructor and method declarations
}
```

### 二、成员变量

#### 1、变量的区分

变量根据根据声明的位置不同可以分以下几种

- Member variables in a class-these are called fields.    在类中声明的成员变量--字段
- Variables in a method or block of code--there are called local variables.   在方法或代码块中声明的变量--局部变量
- Variables in method declarations--there are called parameters.   出现在方法声明中的变量--参数

#### 2、字段的组成

字段的声明由三部分组成：

1. Zero or more modifiers.   零个或多个修饰符
2. The field's type.  字段类型
3. The field's name.  字段名

#### 3、访问修饰符Access Modifiers

- public modifier--the field is accessible from all classes.    public修饰符-该字段可以从所有类访问 -- 属于top level级别
- package-private -- 没有修饰符时的默认权限（包私有）-- top level
- protected -- 除可以在当前包中的类访问外，可以在含有继承关系的子类中访问   -- member level
- private modifier--the field is accessible only within its own class.    private修饰符-该字段只能在自己的类中访问 -- member level

| Modifier    | Class | Package | Subclass | World |
| ----------- | ----- | ------- | -------- | ----- |
| `public`    | Y     | Y       | Y        | Y     |
| `protected` | Y     | Y       | Y        | N     |
| no modifier | Y     | Y       | N        | N     |
| `private`   | Y     | N       | N        | N     |

> [!NOTE]
>
> Top level 指的是，可以对top level class 进行修饰，也就是说对顶级类使用（当然也可以对member level级别使用）
>
> class Person{}  // 这个类没在其他类中，就是顶级类
>
> member level指的是可以对类中成员进行使用，如：字段，方法，内部类等



#### 4、类型

所有变量必须有类型，可以使用基本类型或引用数据类型（也就是所有的类型都可以，必须指定类型）

#### 5、示例

```java
// 秉承封装原则，通常将字段设为私有。我们需要获取或修改字段值时，应从对应的公共getter/setter方法间接完成
public class Bicycle {
	private int cadence;
	private int gear;
	private int speed;
}
```

### 三、方法声明

#### 1、方法声明

方法声明可包含以下部分：

- Modifiers-修饰符
- The return type-返回类型（返回值类型，必须指定）
- The method name-方法名 （必须）
- The parameter list in parenthesis-参数列表用（）括起来，（）必须有
- An exception list-异常列表
- The method body-方法体（必须）

#### 2、方法签名

我们把如 【方法名(参数列表)】的形式称为方法的签名

```java
calculateAnswer(double, int, double, double)
```

#### 3、方法重载Overloading Methods

如果类中的方法具有不同参数列表，那么他们可以具有相同名称

#### 4、示例

```java
public class DataArtist {
    
    public void draw(String s) {
        
    }
    public void draw(int i) {
        
    }
    public void draw(double f) {
        
    }
    public void draw(int i, double f) {
        
    }
}
```



### 四、构造函数

#### 1、作用

构造函数可以用来创建对象

#### 2、声明

构造函数和方法声明类似，只是没有返回值，并且方法名是类名

```java
class Bicycle {
	public Bicycle() {}
  public Bicycle(int startCadence, int startSpeed, int startGear) {}
}
```

> [!CAUTION]
>
> 你可以不为类创建构造函数，编译器会自动为你提供一个没有参数的默认构造函数，这个构造函数会去调用父类的无参构造函数，（所有的类都是继承Object的，如果你的类没有显示声明父类，那么父类就是Object）如果你为你的类显示声明了有参构造，编译器将不在提供这个无参构造器。
>
> 需要格外注意：
>
> 如果你创建的类是要用作一个父类，并且声明了有参构造（没有声明无参构造），并且在子类中没有显示调用父类的任何构造器，编译器会报错

### 五、关于参数的补充

方法或构造函数的声明中都可以包含参数，参数由类型和参数名构成

#### 1、任意数量的参数(Arbitrary Number of Arguments)

要使用varargs，在最后一个参数的类型后面加上一个省略号（三个点，...），然后是空格和参数名。然后，可以使用任意数量的该参数调用该方法，包括无参数。

```java
		public int add(int... nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        return sum;
		}


		@Test
    public void testAdd(){
        int sum = add(1, 2);
        System.out.println(sum); // 3
        sum = add();
        System.out.println(sum); // 0
    }
```

#### 2、值传递机制

[Reference data type parameters, such as objects, are also passed into methods *by value*. This means that when the method returns, the passed-in reference still references the same object as before. *However*, the values of the object's fields *can* be changed in the method, if they have the proper access level.](https://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html)

引用数据类型参数,如对象也*按值*传递到方法中。这意味着当方法返回时，传入的引用仍然引用与以前相同的对象。*但是*，如果对象的字段具有适当的访问级别*，则可以*在方法中更改这些字段的值。

```java
    public void changePersonName(Person person){
        person.setName("lisi");
        person = new Person();
        person.setName("wangwu"); // p1并不受影响，只不过是person指向了新的对象
    }
    
    @Test
    public void testPerson(){
        Person p1 = new Person();
        p1.setName("zhangsan");
        System.out.println(p1.getName()); // zhangsan
        changePersonName(p1);
        System.out.println(p1.getName()); // lisi
    }
```

![对象创建流程](/Users/fly/Pictures/对象创建流程.png)

实际上在调用init后，首先会去执行super(),也就是去创建父类对象，图中没表现

### 六、对象实例化及使用

#### 1、实例化

通过 new 关键字，配合指定构造方法，可以创建一个对象

new Person();

可以使用这个对象类型的变量接收它

Person p = new Person();

#### 2、使用

对象之中基本上只有两种结构，状态，行为 <=> 字段，方法

调用字段：

p.name = "zhangsan";

如果字段不是私有的，可以使用 p.field 的方式获取或修改字段值

调用方法:

p.method();

### 七、类成员详解

#### 1、字段

- 属于对象实例成员，声明在类体中，在使用 new 关键字时，对其分配空间

- 可以使用Access Modifiers修饰. private public protected (no-modifiers)

- 也可以使用static 和 final 修饰。

- 用 static 修饰的字段叫类成员，所有此类创造的对象均共享此成员，并且在使用new关键字时，不会在堆空间为其分配内存。因为它存储在本地内存中。

- 使用final修饰的是常量，不可改变一旦赋值不可改变。final修饰字段必须要进行初始化，因为字段如果不初始化的话会有默认值，例如引用类型的成员会有null这个默认值，如果用final修饰一个null是没有意义的。

- 如果一个字段用static 和 final同时修饰，那么字段名应使用大写字母，如果是多个单词，使用 （_）分隔

  ```java
  class Person {
   // 成员变量--字段
  	private String name;
  	// 类变量，用来记录创建了多少个对象
  	private static Integer createObjNumber = 0;
    // 赋给人一个终身不变的ID
    public static final String ID = "1234567";
  	public Person() {
  		createObjNumber ++;
  	}
  }
  ```

  

#### 2、方法

- 可以使用4中access modifiers修饰

- 实例方法，通过对象引用去调用

- 静态方法，static 修饰的方法，通过类引用去调用 

- 通过final 修饰的方法不能被重写

  ```java
  class Person {
  	public void printParam(String str) {
  		System.out.println(str);
  	}
    public static void ps() {
      System.out.println("我是一个静态方法");
    }
  }
  
  class TestPerson {
    public static void main(String[] args) {
      Person p = new Person();
      p.printParam("我是Person中的方法");
      // 调用类方法
      Person.ps();
    }
  }
  ```

#### 3、构造函数

#### 4、代码块 Initialization Blocks

- 静态代码块    常用于对需要进行运算或异常处理的静态变量进行初始化操作。

- 代码块.   也常用于对成员变量初始化操作，它的主要逻辑是将代码块中的内容复制到每一个构造函数中，所以我们可以将构造函数中重复的工作提取出来放到代码块中。

  ```java
  class Person {
  	private static int v;
  	private int c;
  	static {
  		v = 10;
  	}
  	
  	{
  		c = 20;
  	}
  }
  ```

  > [!CAUTION]
  >
  > 静态代码块无法访问成员变量，但是代码块可以访问静态变量
  >
  > 如果定义了多个静态代码块，那么按照定义的顺序依次执行,代码块也一样，不过静态代码块是在加载类模版时就执行，而代码块是创建对象时执行

#### 5、内部类Nested Classes

定义在一个类中的类

[Why Use Nested class](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html)

Compelling reasons for using nested classes include the following:

- **It is a way of logically grouping classes that are only used in one place**: If a class is useful to only one other class, then it is logical to embed it in that class and keep the two together. Nesting such "helper classes" makes their package more streamlined.
- **It increases encapsulation**: Consider two top-level classes, A and B, where B needs access to members of A that would otherwise be declared `private`. By hiding class B within class A, A's members can be declared private and B can access them. In addition, B itself can be hidden from the outside world.
- **It can lead to more readable and maintainable code**: Nesting small classes within top-level classes places the code closer to where it is used.

```java
class OuterClass {
	class InnerClass {
		
	}
}
```

- 静态内部类. 使用static修饰的内部类

```java
public class NestedClass {
    private String name;
    
    class InnerClass {
        public void sayName() {
            System.out.println(name);
        }
    }
    
    static class staticInnerClass {
        public void sayName() {
          // Non-static field 'name' cannot be referenced from a static context
            System.out.println(name);
        }
    }
}
```

> [!CAUTION]
>
> 内部类可以直接访问顶级类(NestedClass)中的成员
>
> 静态内部类不能直接访问顶级类中的非静态成员（当使用外部类对象的时候可以访问）
>
> 静态内部类也是唯一可以定义静态属性和静态方法的嵌套类，在jdk16之前非静态内部类不允许定义静态成员
>
> ```java
> public class NestedClass {
>  private String name;
>  static class staticInnerClass {
>      public void sayName(NestedClass o) {
>          System.out.println(o.name);
>      }
>  }
> }
> ```

- 创建对象的区别

  - 内部类需要通过外部类对象.new创建
  - 静态内部类直接通过new 外部类.静态内部类创建

  ```java
  NestedClass obj = new NestedClass();
  NestedClass.InnerClass innerClass = obj.new InnerClass();
  innerClass.sayName();
  NestedClass.StaticInnerClass staticInnerClass = new NestedClass.StaticInnerClass();
  staticInnerClass.sayName(obj);
  ```

- 局部内部类  常见在方法内部调用

  ```java
  public class LocalClass {
      static String regularExpression = "[^0-9]";
      private String phoneNumber;
      public static void validatePhoneNumber(String phoneNumber) {
          final int numberLength = 11;
          class PhoneNumber {
              String formattedNumber = null;
              public PhoneNumber(String phoneNumber) {
                  String currentNumber = phoneNumber.replaceAll(regularExpression, "");
                  if (currentNumber.length() == numberLength) {
                      formattedNumber = currentNumber;
                  }
              }
  
              public String getNumber() {
                  return formattedNumber;
              }
          }
          PhoneNumber myNumber1 = new PhoneNumber(phoneNumber);
  
          if (myNumber1.getNumber() == null)
              System.out.println("First number is invalid");
          else
              System.out.println("First number is " + myNumber1.getNumber());
      }
  
  
      public static void main(String[] args) {
          validatePhoneNumber("12345678901");
      }
  }
  ```

  

- 匿名内部类--只需要使用一次的局部内部类

> [!CAUTION]
>
> 局部内部类和匿名内部类都可以访问外部类的成员
>
> 局部内部类和匿名内部类中都不能定义静态成员，除非这个静态成员是个常量 static final，即便这个规定在jdk16之后有所放宽，但仍应该注意。
>
> 局部内部类和匿名内部类可以访问局部变量，但是局部变量必须是常量 final，否则会报错
>
> Variable 'numberLength' is accessed from within inner class, needs to be final or effectively final
>
> 在jdk1.8之后，会默认将没有修改过的变量视作常量，所以只要不修改局部变量，不加final也是可以访问的，但还是加入final更好，会更明确的知道我们可能捕获了哪些局部变量

### Lambda表达式

当一个接口只包含一个方法，并且试图将方法作为参数传递给另一个方法时，使用Lambda会更加简洁

java[定义了一些常用的函数接口](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

```java
findPerson(this.persons, p -> p.getAge() >= 18 && p.getAge() < 40, p -> System.out.println(p));

public void findPerson(List<Person> persons, Predicate<Person> predicate, Consumer<Person> consumer){
        for(Person person:persons){
            if(predicate.test(person)){
                consumer.accept(person);
            }
        }
}
```

### 方法引用

使用lambda时，lambda表达式的方法只是调用已有方法，并不做其他事，这时可以使用方法引用

```
findPerson(this.persons, p -> p.getAge() >= 18 && p.getAge() < 40, System.out::println);

public void findPerson(List<Person> persons, Predicate<Person> predicate, Consumer<Person> consumer){
        for(Person person:persons){
            if(predicate.test(person)){
                consumer.accept(person);
            }
        }
}
```

```java
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
```

### 补充this关键字和变量遮蔽

在一个实例方法或构造函数中，`this`是对*当前对象*的引用--这个对象的方法或构造函数被调用。你可以使用`this`从一个实例方法或构造函数中引用当前对象的任何成员。

还可以使用this()来调用同一个类中的另一个构造函数，如果使用了this()显示调用另一个构造函数，须要将其放在第一行，一个类中，最多可以有N-1个this(), N指的是构造函数数量

变量遮蔽

当我们在方法参数中，或是内部类中，定义了与字段相同名称的变量时，会发生变量遮蔽，这时我们无法直接访问成员变量（字段）

在方法中我们可以使用this.字段名来表示成员变量

在内部类中，我们可以使用 外部类名.this.字段名 来访问外部类的字段，使用this.字段名访问当前类中字段

### 对象在堆空间中实际分配内存大小

![对象在内存中实际空间分配](/Users/fly/Pictures/对象在内存中实际空间分配.png)

可以看到，对象在堆中实际分配的空间为（对象头 + 实例字段所占字节大小），如果实际空间大小不是8的倍数，那么就在最后自动补齐。