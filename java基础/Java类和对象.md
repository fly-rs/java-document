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

- public modifier--the field is accessible from all classes.    public修饰符-该字段可以从所有类访问
- private modifier--the field is accessible only within its own class.    private修饰符-该字段只能在自己的类中访问

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

![对象创建执行方法流程](/Users/fly/Pictures/对象创建执行方法流程.png)

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

如果字段不是私有的，可以使用 p.field 的方式获取或修改字段值

调用方法:

p.method();