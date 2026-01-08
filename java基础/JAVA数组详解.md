## JAVA数组详解:

### 一：什么是数组

1. 数组的定义
   - 数组是用来存储相同类型的一组固定长度的集合
2. 为什么要使用数组
   - 当我们想要存储{x| 0 < x < 11} 的整数的时候，如果使用变量的方式，需要创建10个单独的变量来存储，这些变量之间看起来毫无联系，而且非常麻烦，但是我们知道这些数本应该是x可能取到的值，这时可以使用数组将他们存储在一起，作为x的定义域
3. 数组都可以存储什么类型的元素
   - 数组可以存储任意类型，但必须是同一类型。对于引用类型，数组可以存储该类型及其子类（或实现类）的对象
   
     ```java
     Object[] arr = new Object[3];  // 这是一个Object类型的数组，众所周知，java所有对象都隐式继承Object，所以Object数组可以存储java内的所有类型
     arr[0] = "Hello";  // String
     arr[1] = 123;      // Integer
     arr[2] = new int[]{1, 2}; // 数组也是对象
     ```

### 二：数组的创建

数组创建有两种方式：

1. 动态创建：

   - 在创建数组时，只是指定了数组的长度，但并未对数组进行赋值

   - ```java
     int[] arr = new int[10];
     ```

   - 动态创建的数组中的元素是具有默认值的，基本数据类型的数组默认值为：

     ```
     整型 -> 0
     浮点型 -> 0.0
     boolean -> false
     char -> /u0000
     ```

   - 引用类型数组默认值为null

2. 静态创建：

   - 在创建数组时，不指定数组的长度，而是直接将元素添加到数组中，元素的个数就是数组的长度

   - ```java
     int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
     ```

### 三：数组在内存中的存储方式

数组是一种特殊的引用数据类型

 2 newarray 10 (int)
 4 astore_1
 5 aload_1

可以通过字节码确定下来

数组在堆内存中分配一段连续空间，存储元素的值（基本类型）或引用（引用类型）。数组变量本身存储的是指向堆内存中该数组的引用。索引访问本质上是“基地址 + 偏移量”的计算

![arr](/Users/fly/Pictures/arr.png)

### 四：数组元素的访问

数组可以通过索引访问每个元素的位置（指针的偏移量，指针应该指向数组的第一个位置，所以数组第一个元素的偏移量为0）

```java
int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
int x = arr[0]
System.out.println(x);    // 1
int y = arr[1];
System.out.println(y);   // 2
```

数组元素遍历：

```java
int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
// for 循环遍历
for (int i = 0; i < arr.length; i ++) {
	System.out.println(i);
}

// foreach
for (int value: arr) {
  System.out.println(value);
}
```

### 五：常见的错误

1. ArrayIndexOutOfBoundsException //索引越界 

   ```java
   int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
   arr[10] = 0; // 因为数组长度为10，所以index最大应该是9报异常 Index 10 out of bounds for length 10
   int x = arr[10]; // 一样的报异常
   ```

2. NullPointerException  // 空指针异常

   ```java
   // 当我们在数组中存储另一个数组的时候【二维数组】，里面的数组还没有创建，我们尝试调用它会报空指针异常
   int[][] arr = new arr[10][]; // 创建了10个一维int[]，数组中应该存储的也是int[]，但现在还没存
   // 这时获取一维数组中的数组中的数据会报错
   int y = arr[0]; // null
   int x = arr[0][0]; // 空指针异常
   ```

### 六：常见算法

- 排序

  1. 冒泡排序

     ```java
     public void bubbleSort(int[] arr){
             if (arr==null||arr.length==0){
                 return;
             }
             for (int i = 0; i < arr.length - 1; i++) {
                 boolean flag = true;
                 for (int j = 0; j < arr.length - i - 1; j++) {
                     if (arr[j] > arr[j + 1]) {
                         int temp = arr[j];
                         arr[j] = arr[j + 1];
                         arr[j + 1] = temp;
                         flag = false;
                     }
                 }
                 if (flag){
                     break;
                 }
             }
      }
     ```

     

  2. 插入排序

     ```java
     public void insertSort(int[] arr){
         if (arr==null||arr.length==0){return;}
         for (int i = 1; i < arr.length; i++) {
             int key = arr[i];
             int j = i - 1;
             while (j >= 0 && arr[j] > key) {
                 arr[j + 1] = arr[j];
                 j--;
             }
             arr[j + 1] = key;
         }
     }
     ```

  3. 选择排序

     ```java
     public void selectSort(int[] arr){
             if (arr==null||arr.length==0){return;}
     
             for (int i = 0; i < arr.length; i++) {
                 int min = arr[i];
                 int index = i;
                 for (int j = i + 1; j < arr.length; j++) {
                     if (arr[j] < min){
                         min = arr[j];
                         index = j;
                     }
                 }
                 if (index != i){
                     int temp = arr[i];
                     arr[i] = arr[index];
                     arr[index] = temp;
                 }
             }
         }
     ```

  4. 快速排序

     ```java
     /**
          * 快速排序
          * @param arr 要排序的数组
          * @param left 数组左边界
          * @param right 数组右边界
          */
         public void quickSort(int[] arr, int left, int right){
             if (arr==null||arr.length==0){return;}
             if (left < right){
                 // pivot 是选择的标量，比pivot小的放在pivot左侧，比pivot大的放在右侧
                 int pivot = arr[left];
                 int l = left;
                 int r = right;
                 while (l < r){
                     while (l < r && arr[r] > pivot) {
                         r --;
                     }
                     if (l < r) {
                         arr[l ++] = arr[r];
                     }
                     while (l < r && arr[l] < pivot) {
                         l ++;
                     }
                     if (l < r) {
                         arr[r --] = arr[l];
                     }
                 }
                 arr[l] = pivot;
                 quickSort(arr, left, l - 1);
                 quickSort(arr, l + 1, right);
             }
         }
     ```

- 查找

  - 二分查找

    ```java
    public int binarySearch(int[] arr, int target){
            if (arr==null||arr.length==0){return -1;}
            int left = 0;
            int right = arr.length - 1;
    
            while (left <= right){
                int mid = left + (right - left)/2;
                if (arr[mid] == target){
                    return mid;
                }
                if (arr[mid] < target){
                    left = mid + 1;
                }
                if (arr[mid] > target){
                    right = mid - 1;
                }
            }
            return -1;
        }
    ```

    


### 七：工具类Arrays

常用方法

1. 排序： Arrays.sort(int[] arr);

2. 查找： Arrays.binarySearch(int[] arr, int key);

3. toString(int[] arr)： 将数组转换为字符串

4. 比较： 使用带有Comparator参数的比较方法需要保证数组中元素类型不是基本数据类型

   ```java
   Arrays.compare(arr, ints, (x, y) -> {
               return (x - y) < 0 ? -1 : ((x.equals(y)) ? 0 : 1);
           });
   ```

5. 复制: Arrays.copyOf(T[] t, int newLength);

6. `Arrays.fill(arr, value)`：填充数组

7. `Arrays.equals(arr1, arr2)`：比较两个数组内容

8. `Arrays.asList(arr)`：数组转列表

9. Arrays.stream(T[] t)   将数组转换为stream流，可以执行filter, map等操作

### 八：深拷贝与浅拷贝

- 浅拷贝：对于只拷贝了一级内容（如我们使用copyOf拷贝数组时，会在堆内存分配新空间，并将数组中存的值拷贝过来）

  ```java
  int[] arr = {8, 4, 3, 5, 1, 7, 5, 6, 9, 3, 2, 0, 0, 1};
  int[] arr2 = arr;
  
  public static <T> T[] copyOf(T[] original, int newLength) {
          return (T[]) copyOf(original, newLength, original.getClass());
  }
  
  
  @IntrinsicCandidate
      public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
          @SuppressWarnings("unchecked")
          T[] copy = ((Object)newType == (Object)Object[].class)
              ? (T[]) new Object[newLength]
              : (T[]) Array.newInstance(newType.getComponentType(), newLength);
          System.arraycopy(original, 0, copy, 0,
                           Math.min(original.length, newLength));
          return copy;
      }
  ```

- 深拷贝 （如拷贝数组，在堆空间分配新内存后（一级拷贝），将原数组（如果是引用类型数据，将堆空间的数据在拷贝一份，将新的空间地址存到我们新分配的数组空间中）如果是基本类型，就直接将值拷贝到新数组空间中）

  ```java
  // 实现Cloneable接口
  class Person implements Cloneable {
      private String name;
      
      @Override
      protected Object clone() throws CloneNotSupportedException {
          return super.clone();
      }
  }
  
  Person[] original = {new Person("Alice"), new Person("Bob")};
  Person[] deepCopy = new Person[original.length];
  for (int i = 0; i < original.length; i++) {
      deepCopy[i] = (Person) original[i].clone();
  }
  
  ```

  > [!IMPORTANT]
  >
  > 总结：
  >
  > 对于**基本类型数组**，`copyOf`、`clone`、`System.arraycopy` 都是深拷贝；
  > 对于**引用类型数组**，这些方法只是复制了引用，是浅拷贝。若要实现深拷贝，需遍历数组并复制每个对象。

  

  ### 九：数组类的父类

  数组的父类是Object类
  
  ```java
  Integer[] arr = {8, 4, 3, 5, 1, 7, 5, 6, 9, 3, 2, 0, 0, 1};
  System.out.println(arr.getClass().getSuperclass().getName());
  // java.lang.Object
  ```
  
  
