

## JAVA基本数据类型详解

### 第一章：概述与分类：

### 1.1 数据类型分类

java中有8种基本数据类型

其中包括boolean类型，字符型char，和六种数值类型

Byte->short->int->long->float->double

### 1.2 类型对比表格

| 基本数据类型 |  分类  | 字节大小（byte） |      取值范围      |  默认值  |              说明               |
| :----------: | :----: | :--------------: | :----------------: | :------: | :-----------------------------: |
|   boolean    | 布尔型 |                  |   {true, false}    |  false   |                                 |
|     char     | 字符型 |        2         | ['\u0000', \uffff] | '\u0000' |  用单引号包裹，存储unicode字符  |
|     byte     |  整型  |        1         |  [-2^7, 2^7 - 1]   |    0     |                                 |
|    short     |  整型  |        2         | [-2^15, 2^15 - 1]  |    0     |                                 |
|     int      |  整型  |        4         | [-2^31, 2^31 - 1]  |    0     |                                 |
|     long     |  整型  |        8         | [-2^63, 2^63 - 1]  |    0L    |    使用时需要在值后加 l 或 L    |
|    float     | 浮点型 |        4         |                    |   0.0F   |    使用时需要在值后加 f 或 F    |
|    double    | 浮点型 |        8         |                    |   0.0    | 可以在值后加 d 或 D（可以省略） |

> [!CAUTION]
>
> 关于boolean类型所占用空间大小，引用[The Java Tutorials Java](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)关于原始数据类型的声明
>
> **boolean**: The `boolean` data type has only two possible values: `true` and `false`. Use this data type for simple flags that track true/false conditions. This data type represents one bit of information, but its "size" isn't something that's precisely defined.
>
> `boolean`数据类型只有两个可能的值：`true`和`false`。此数据类型用于跟踪真/假条件的简单标志。这种数据类型表示一个比特的信息，但它的“大小”并没有精确定义。
>
> float类型取值范围的计算方式与其他数值类型不同，会在后面详说

### 第二章：整形数据深度分析：

### 2.1 存储原理：补码系统

计算机中的数据都是以二进制的方式进行存储的，而二进制还有三种表现形式

1. 原码
   - 举例：将一个十进制数，直接转换为二进制，得到的就是原码，其中最高位为符号位
   - 十进制：-2
   - 二进制：1 0000000 00000000 00000000 00000010
2. 反码
   - 原码的符号为不变，其他位取反
   - 反码：1 1111111 11111111 11111111 11111101
3. 补码
   - 反码加1
   - 补码：1  1111111 11111111 11111111 11111110 

> [!CAUTION]
>
> 正数的原码、反码、补码都相同

采用补码存储的原因：

1. **解决“0”的重叠问题**：在反码系统中，`+0` 是 `0000 0000`，`-0` 是 `1111 1111`，这会导致两个零的存在，增加电路判断难度。而补码系统中，`0` 是唯一的。原码 +0 0000 0000 -0 1 000 0000；
2. **简化加减运算**：使用补码，减法可以直接当作加法来做（例如 `A - B` 等于 `A + (-B的补码)`），CPU 只需要加法器即可，不需要减法器，大大节省了硬件成本。

如果出现了在反码+1的情况超出了原有位长度会发生什么

会将溢出位直接丢弃

**-0 的原码**：`1000 0000`

**-0 的反码**：`1111 1111`

**-0 的补码（反码 + 1）**：1 0000 0000  (结果变成了 9 位！)

将1丢弃0000 0000 还是0

### 2.2 运算规则与类型提升

1. 隐式类型转换（自动提升）

   - 上面我们提到的小于int的类型会自动转换为int的方式，就是自动提升
   - 当两个或多个数据进行运算时，会自动提升为大的类型
   - byte、short、char =>int
   - int、long => long
   - long、float => float （按精度）
   - long、double =》 double

2. 显示类型转换 （强制转换）

   - 将大的强制转为小的

   - 如果我们想让两个byte相加的结果仍然为byte应该怎么做

   - ```java
     byte b1 = 10;
     byte b2 = 20;
     byte b3 = (byte)(b1 + b2);
     log.info("b3 = " + b3);
     //1月 03, 2026 4:18:59 下午 DataType main
     //信息: b3 = 30
     
     0 bipush 10
     2 istore_1
     3 bipush 20
     5 istore_2
     6 iload_1
     7 iload_2
     8 iadd
     9 i2b
     10 istore_3
     ```

   - 这单代码从字节码上看，同样是先提升为int计算，计算后通过i2b命令对int类型进行截断，强制其变为byte，从结果上看，好像也没什么影响么，但如果是下面这样呢？

     ```java
     byte b1 = 110;
     byte b2 = 20;
     byte b3 = (byte)(b1 + b2);
     log.info("b3 = " + b3);
     
     // 1月 03, 2026 4:21:07 下午 DataType main
     // 信息: b3 = -126
     ```

     我们发现110+20得出的结果却是-126,为什么会这样呢？byte类型只能存储[-128,127]这个区间内的值，而130是明显大于这个值的，所以我们在强制转换时会产生损失，可能是损失精度，也可能是值的准确性，那为什么最后会出现-126这个值呢，这个值是随机的吗？

     当然不！！！

     在jvm计算时，会将b1, b2提升为int, 所以他们在内存中的二进为：

     b1: 00000000 00000000 00000000 01101110

     b2: 00000000 00000000 00000000 00010100

     b3: 00000000 00000000 00000000 10000010

     当我们执行i2b时，会进行截断只留最后8位

     b3: 10000010 // 这是一个负数，因为符号为是1，所以应该转换为补码

     反码： 1 1111101

     补码： 1 1111110

     转化为10进制byte为-126

### 2.3 转换策略与风险控制

为了防止将超出数据类型范围的值强制转换为此类型导致精度丢失，可以在计算时进行验证

```java
// 例:
int safeAdd(byte a, byte b) {
		int c = a + b;
		if (c > Byte.MAX_VALUE || c < Byte.MIN_VALUE) {
				throw new ArithmeticException("byte类型溢出");
		}
		return (byte) c;
}
```

### 第三章：浮点数系统详解

### 3.1 IEEE 754标准

计算机发明以来, 曾出现许多中不同的浮点数表示方式, 但目前最通用的是 IEEE 二进制浮 点数算术标准 (IEEE Standard for Binary Floating-Point Arithmetic, 简称 IEEE 754 标准).

通常一个浮点数由符号、尾数、基和指数组成。如：−0.3141592610 × 10^2 , 0.101012 × 2^3

这里要求小数点前面为零, 小数点后面的数称为尾数. 若尾数的首位数字不为 0 时, 我们称其为正 规数 (或规范化数), 否则称为次正规数 (或非规范化数). 如 0.31410 ×102 是正规数, 而 0.0031410 × 104 是次正规数. 正规化表示方法可以使得每个浮点数的表示方式唯一, 而且可以空出一个位置, 使得表示精度更高

### 3.2 内存结构与表示范围

![截屏2026-01-04 02.40.46](/Users/fly/Pictures/截屏2026-01-04 02.40.46.png)

根据oracle官方文档中给出的浮点数的公式： *s* · *m* · 2^(*e* - *N* + 1）[参照资料Floating-Point Types, Formats, and Values](http://docs.oracle.com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.3)

| Parameter | float | float-extended-exponent | double | double-extended-exponent |
| --------- | ----- | ----------------------- | ------ | ------------------------ |
| *N*       | 24    | 24                      | 53     | 53                       |
| *K*       | 8     | ≥ 11                    | 11     | ≥ 15                     |
| *Emax*    | +127  | ≥ +1023                 | +1023  | ≥ +16383                 |
| *Emin*    | -126  | ≤ -1022                 | -1022  | ≤ -16382                 |

其中：

- `s`: +1 或 -1（符号）
- `m`: 整数，满足 1 ≤ m < 2^N（规范化数）
- `e`: 整数，Emin ≤ e ≤ Emax
- `N`: 有效数字位数（float:24, double:53）

### 3.3 运算特性与特殊值

浮点数除有限非0.0的值外，还存在一下几种特殊值

- NaN     在0.0/0.0时会产生此值，表示为Not a Number
- Double.NEGATIVE_INFINITY     负无穷，在-1.0/0.0时产生
- Double.POSITIVE_INFINITY     正无穷，在1.0/0.0时产生
- 正负零

运算特性

1. 浮点数之间的比较运算

   ```java
   // 从小到大依次是 负无穷-》0.0 -》正无穷
   				double d1 = -1.0 / 0.0;
           double d2 = -1.0;
           double d3 = 0.0;
           double d4 = 1.0;
           double d5 = 1.0/0.0;
           log.info("d1 is : " + d1);
           log.info("d1 < d2: " + (d1 < d2));
           log.info("d2 < d3: " + (d2 < d3));
           log.info("d3 < d4: " + (d3 < d4));
           log.info("d5 is : " + d5);
           log.info("d4 < d5: " + (d4 < d5));
   
   //1月 04, 2026 12:16:08 上午 DataType main
   //信息: d1 is : -Infinity
   //1月 04, 2026 12:16:08 上午 DataType main
   //信息: d1 < d2: true
   //1月 04, 2026 12:16:08 上午 DataType main
   //信息: d2 < d3: true
   //1月 04, 2026 12:16:08 上午 DataType main
   //信息: d3 < d4: true
   //1月 04, 2026 12:16:08 上午 DataType main
   //信息: d5 is : Infinity
   //1月 04, 2026 12:16:08 上午 DataType main
   //信息: d4 < d5: true
   ```

2. 有NaN参与到运算

   ```java
   // 任何数据类型与NaN参与的比较运算结果均为false，数值运算结果为NaN
   double d6 = 0.0/0.0;
   double d5 = 1.0/0.0;
   log.info("d6 is : " + d6);
   log.info("d6 < d2: " + (d6 < d2));
   log.info("d6 + d2: " + (d2 + d6));
   
   1月 04, 2026 12:19:18 上午 DataType main
   信息: d6 is : NaN
   1月 04, 2026 12:19:18 上午 DataType main
   信息: d6 < d2: false
   1月 04, 2026 12:19:18 上午 DataType main
   信息: d6 + d2: NaN
   ```

3. 若数值运算中，两个数中有一个为浮点数，则将另一个自动提升为浮点数，float和double进行运算时，float也会提升double

4. 浮点数溢出会产生有符号无穷大

### 第四章：类型转换与运算综合

### 4.1 自动类型提升规则

> [!CAUTION]
>
> boolean类型由于类型大小不确定，所以无法参与运算，后续内容不在讨论
>
> 所有参加运算的数据必须能转换为基本数据类型，否则会抛出异常

在基础整型二元运算中，当一个类型的值范围小于int时，且不包含long类型时，应该自动提升为int类型进行运算，如果有long类型参与到运算，应该提升为long类型进行运算

在二元运算中，如果有一个类型为浮点型时，参与运算的其他类型应改提升为浮点型，当其中一个类型为double时，应该将其他类型自动提升为double

### 4.2 强制类型转换策略

当我们想将一个数值范围大的类型存入到范围小的类型时，可以通过强制类型转换，让其对当前数据进行截断

如 int a = 128;

byte b = (byte) a;

此时jvm会通过i2b进行截断，只保留低位的8位

强制转换可能会损失精度

### 第五章：精度问题全面解决方案

### 5.1 问题根源分析

```java
d1 = 1.1;
d2 = 0.1;
log.info("d1 + d2 = " + (d1 + d2));
        
1月 04, 2026 12:32:54 上午 DataType main
信息: d1 + d2 = 1.2000000000000002
```

我们发现d1+d2并没有产生我们想要的结果1.2

这是因为用二进制无法每次都精确表示一个浮点数，可能某个浮点数用二进制表示是一个无限【不】循环小数

但是浮点数是有存储位数限制的，多余的位数就需要被舍弃，丢失精度，在不断的运算中，丢失的精度会被不断放大。

### 5.2 浮点数比较正确姿势

避免直接使用==来比较浮点数，可以通过两浮点数相减的绝对值，小于某个值域来判断是否相等

```java
double de1 = 0.100001;
double de2 = 0.13;
if (Math.abs(de1 - de2) < 0.001) {
     System.out.println("相等");
} else {
     System.out.println("不相等");
}
```

### 5.3 高精度计算工具使用

使用BigDecimal类

```java
BigDecimal bigDecimal = new BigDecimal("10.12");
BigDecimal bigDecimal2 = new BigDecimal("1.3");
BigDecimal bigDecimal3 = bigDecimal.add(bigDecimal2).setScale(2, RoundingMode.HALF_UP);
log.info("bigDecimal : " + bigDecimal3);

// 可以通过setScale方法指定保留有效位数，和取舍模式来达到我们想要的精度
//1月 04, 2026 1:14:59 上午 DataType main
//信息: bigDecimal : 11.42

```

RoudingMode的几种取舍模式

| **模式 (RoundingMode)**    | **原始值**  | **处理结果**  | **逻辑说明**                                                 |
| -------------------------- | ----------- | ------------- | ------------------------------------------------------------ |
| **HALF_UP** (四舍五入)     | 1.15        | **1.2**       | 最常用的模式，若舍弃部分 $\ge 0.5$ 则进位。                  |
| **HALF_DOWN** (五舍六入)   | 1.15        | **1.1**       | 若舍弃部分 $> 0.5$ 才进位。如果是 1.151 则会变成 1.2。       |
| **UP** (远离零方向进位)    | 1.11        | **1.2**       | 只要舍弃部分非零，就直接进位（无视大小）。                   |
| **DOWN** (向零方向舍弃)    | 1.19        | **1.1**       | 直接截断，不进位。                                           |
| **CEILING** (向正无穷大)   | -1.19       | **-1.1**      | 在数轴上向右取值（对负数来说结果变大）。                     |
| **FLOOR** (向负无穷大)     | 1.19        | **1.1**       | 在数轴上向左取值（对负数来说结果变小）。                     |
| **HALF_EVEN** (银行家舍入) | 1.15 / 1.25 | **1.2 / 1.2** | **四舍六入五考虑**：若舍弃位是 5，看前一位，偶数舍，奇数进。能平衡累计误差。 |

> [!CAUTION]
>
> 注意：
>
> 这里的BigDecimal应该使用String参数的构造方法，否则还会有精度问题
>
> 因为使用Double作为参数时，已经发生了精度损失，所以使用BigDecimal也无济于事
>
> 也可以使用BigDecimal.valueOf(Double d),因为其内部代码也是通过String构造
>
> ```java
> public static BigDecimal valueOf(double val) {
>      // Reminder: a zero double returns '0.0', so we cannot fastpath
>      // to use the constant ZERO.  This might be important enough to
>      // justify a factory approach, a cache, or a few private
>      // constants, later.
>      return new BigDecimal(Double.toString(val));
> }
> ```
>

### 第六章：实战应用指南

### 6.1 类型选择原则

`byte`数据类型对于在大型数组中节省内存非常有用，在大型[数组中](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)，内存节省实际上很重要。它们也可以用来代替`int`,short同理

当你需要使用比int提供的值范围更大的类型请选择选择Long

与`byte`和`short`的建议一样，如果需要在大型浮点数数组中保存内存，请使用`float`（而不是`double`)此数据类型不应用于精确值，例如货币

如果需要使用高精度浮点数，请选择BigDecimal

### 6.2 常见错误与调试技巧

1. 当我们在使用非数值类型的默认类型时（整型的默认类型为int，浮点型double），在做运算时要注意会发生类型提升，如果想维持原类型需要做强制转换，但对类型有精度要求时，需要在转换前对运算结果进行验证，如果超出当原类型范围会发生精度丢失
2. 当我们使用BigDecimal时，需要注意不要直接使用double类型来构造对象，因为在使用double的时候已经出现精度错误，这样构造出的对象已经是错误对象