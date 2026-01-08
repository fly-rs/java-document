## Java面向对象概念

### 一、什么是对象

#### 1、理解对象

对象是理解面向对象技术的关键，那具体什么是对象呢？

我认为可以把对象理解为对一个具有某种状态(state) 和 行为(behavior) 的事物的具像化

例如：有一个人（你的女朋友 ps：应该大概率是个人）

具有以下状态(state)

- 姓名
- 性别
- 年龄
- 身高
- 体重

有以下行为(behavior)

- 吃饭
- 喝水
- 上厕所
- 睡觉

这时你可能会想，你也具有这些状态和行为啊，那这个说的到底是你还是你女朋友啊，所以说对象是具体的，只有姓名是“王桂芬” 年龄 “18” 性别“女”的才是你女朋友。

在编程中也是如此

[参考oracle javase tutorial](https://docs.oracle.com/javase/tutorial/java/concepts/object.html)

![A software obj](/Users/fly/Pictures/A software obj.png)

#### 2、理解封装

将对象的状态(state)存储在fields中，并隐藏起来，将对象的行为(behavior)存储在Methods中，并公开其行为，方法造作内部字段，并作为对象到对象之间通信机制。隐藏内部状态并要求所有的交互都通过对象的方法来执行，这被称为*数据封装*，这是面向对象编程的一个基本原则。

#### 3、为什么要封装

例如我们在设计一个购物系统，这个系统有一个状态为商品列表commodity

```java
// 假设这个列表是一个容器，例如数组，此列表中只有两种商品
commodityList{葡萄，香蕉}
// 现在我们想向这个容器中添加商品，我想添加的所有商品都要是水果的类别
// 如果我们不使用封装，将这个状态暴露出去，那么我们可以很随意很方便的向其痛添加商品，那我可以将任何商品放入其中
commodityList{葡萄，香蕉，鞋子}
// 这样就会失去控制
// 如果使用封装，我们可以在添加商品的行为中去验证我们添加的商品是否为水果
addCommodityToList(commodity) {
	如果 commodity 是 水果
		添加
	否则
		不添加并告知
}
```

#### 4、将代码编写到对象中的好处

1. Modularity: The source code for an object can be written and maintained independently of the source code for other objects. Once created, an object can be easily passed around inside the system.
   模块化：一个对象的源代码可以独立于其他对象的源代码编写和维护。一旦创建，对象就可以在系统内轻松传递。
2. Information-hiding: By interacting only with an object's methods, the details of its internal implementation remain hidden from the outside world.
   信息隐藏：通过仅与对象的方法交互，其内部实现的细节对外部世界保持隐藏。
3. Code re-use: If an object already exists (perhaps written by another software developer), you can use that object in your program. This allows specialists to implement/test/debug complex, task-specific objects, which you can then trust to run in your own code.
   代码重用：如果一个对象已经存在（可能是由其他软件开发人员编写的），您可以在程序中使用该对象。这允许专家实现/测试/调试复杂的、特定于任务的对象，然后您可以信任这些对象在您自己的代码中运行。
4. Pluggability and debugging ease: If a particular object turns out to be problematic, you can simply remove it from your application and plug in a different object as its replacement. This is analogous to fixing mechanical problems in the real world. If a bolt breaks, you replace *it*, not the entire machine.
   可插拔性和调试简便性：如果一个特定的对象被证明是有问题的，您可以简单地将其从应用程序中删除，并插入一个不同的对象作为其替换。这类似于解决真实的世界中的机械问题。如果一个螺栓坏了，你就换掉*它*，而不是整台机器。

### 二、什么是类

我认为可以把类理解为对一个具有某种状态(state) 和 行为(behavior) 的事物的描述

例如：上面举例你的女朋友，你发现上面列出的状态和行为你也都有，甚至很多其他人也就有，那么我们把统一拥有这种特征的都叫做人类

```java
public class Person {
    String name;
    int age;
    char gender;
    int height;
    int weight;
    

    void eat() {
        System.out.println("人吃饭");

    }

    void drink() {
        System.out.println("人喝水");
    }

    void sleep() {
        System.out.println("人睡觉");
    }

    void goToTheToilet() {
        System.out.println("人去厕所");
    }
}

```

### 三、什么是继承

不同种类的事物之间具有共同点。例如人可以分为 黑人，白人，黄人，只是肤色不同，但都属于人类这种情况下Person就可以被称为父类。黑人，白人，黄人就可以被称为子类

```java
class BlackAmerica extends Person {
    
}
```

这使得子类拥有父类的所有字段和方法，还允许有自己的独特功能和状态

### 四、什么是接口

接口是用来约定对象与外部交互的行为的。这些行为是强制拥有的，没有这个行为就不能归结为是这种类型的对象

### 五、什么是包

包是组织一组相关类和接口的命名空间。从概念上讲，您可以将包视为类似于计算机上的不同文件夹。您可以将HTML页面保存在一个文件夹中，将图像保存在另一个文件夹中，将脚本或应用程序保存在另一个文件夹中。因为用Java编程语言编写的软件可以由数百或*数千*个单独的类组成，所以通过将相关的类和接口放入包中来保持组织是有意义的