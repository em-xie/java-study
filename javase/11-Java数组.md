# Java数组

## 1.数组概述

### 1.1 数组的定义

- 数组是相同类型数据的有序集合
- 数组描述的是相同类型的若干个数据，按照一定的先后次序排列组合而成
- 其中，每一个数据称作一个数组元素，每个数组元素可以通过一个下标来访问他们

## 2.数组声明创建

- 首先必须声明数组变量，才能在程序中使用数组。下面是声明数组变量的语法：

```
dataType[] arrayRefVar; //首选的方法
或
dataType arrayRefVar[];//效果相同
```

- Java语言使用new操作符来创建数组，语法如下：

  ```
  dataType[] arrayRefVar=new dataType[arraySize];
  ```

- 数组的元素是通过索引访问的，数组索引从0开始。

- 获取数组的长度：

  ```
  arrays.length
  ```

- 代码示例：

```
package com.zzhhangjiashun.array;
public class ArrayDemo01 {
    //变量的类型  变量的名字=变量的值
    //数组类型
    public static void main(String[] args) {
        int[] nums;//1.定义
        int nums2[];
        nums=new int[10];//这里面可以存放10个int类型的数字
        //3.给数组中元素赋值
        nums[0]=1;
        nums[1]=2;
        nums[2]=3;
        nums[3]=4;
        nums[4]=5;
        nums[5]=6;
        nums[6]=7;
        nums[7]=8;
        nums[8]=9;
        nums[9]=10;
        //计算所有元素的和
        int sum=0;
        //获取数组长度：arrays.length
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        System.out.println("总和为:"+sum);
    }
}
```

### 2.1内存分析

- Java内存分析

  Java内存

  - 堆

    存放new的对象和数组

    可以被所有的线程共享，不会存放别的对象引用

  - 栈

    存放基本变量类型(会包含这个基本类型的具体数值)

    引用对象的变量(会存放这个引用在堆里面的具体地址)

  - 方法区

    可以被所有的线程共享

    包含了所有的class和static变量

    <img src="F:\软件下载\processon on流程图\Java内存分析.png" style="zoom:80%;" />

### 2.2三种初始化

- 静态初始化

  ```
  int[] a={1,2,3};
  Man[] mans={new Man(1,1),new man(2,2)}
  ```

- 动态初始化

```
int[] a=new int[2];
a[0]=1;
a[1]=2;
```

- 数组默认初始化
  - 数组是引用类型，它的元素相当于类的实例变量，因此数组一经分配空间，其中的每个元素也被按照实例变量相同的方式被隐式初始化。

```
package com.zzhhangjiashun.array;
public class Demo02 {
    public static void main(String[] args) {
        //静态初始化:创建+赋值
        int[] a={1,2,3,4,5,6,7,8};
        System.out.println(a[0]);
        //Man [] mans={new Man(),new Man()};
        //动态初始化:包含默认初始化(int初始化为0,String初始化null)
        int[] b=new int[10];
        b[0]=10;
        System.out.println(b[0]);
        System.out.println(b[8]);
    }
}
```

### 2.3数组的四个基本特点

- 其长度是确定的，数组一旦被创建，它的大小就是不可以变的。
- 其元素必须是相同类型，不允许出现混合类型。
- 数组中的元素可以是任何数据类型，包括基本类型和引用类型。
- 数组变量属引用类型，数组也可以看成是对象，数组中的每个元素相当于该对象的成员变量。数组本身就是对象，Java中对象是在堆中的，因此数组无论保存原始类型还是其他对象类型，数组对象本身是在堆中的。

## 2.4数组边界

- 下标的合法区间：[0:length-1],如果越界就会报错；

```
public static void main(String[] args) {
        int[] a={1,2,3,4,5,6,7,8};
        for(int i=0;i<=a.length;i++){
            System.out.println(a[i]);
}
```

- java.lang.ArrayIndexOutOfBoundsException：数组下标越界异常！
- 小结：
  - 数组是相同数据类型(数据类型可以为任意类型)的有序集合
  - 数组也是对象。数组元素相当于对象的成员变量
  - 数组长度是确定的，不可变的。如果越界，则报：ArrayIndexOutOfBoundsException

## 3.数组使用

```
  package com.zzhhangjiashun.array;
  public class Demo03 {
      public static void main(String[] args) {
          int[] arrays={1,2,3,4,5};
          //打印全部的数组元素
          for(int i=0;i<arrays.length;i++) {
              System.out.println(arrays[i]);
          }
          System.out.println("==========================================");
          //计算所有元素的和
          int sum=0;
          for(int i=0;i<arrays.length;i++){
              sum+=arrays[i];
          }
              System.out.println("sum=:"+sum);
          System.out.println("==========================================");
          //查找最大元素
          int max=arrays[0];
          for(int i=0;i<arrays.length;i++){
              if(arrays[i]>max){
                  max=arrays[i];
              }
          }
          System.out.println("max="+max);
      }
  }
```

- For-Each循环
- 数组作方法入参
- 数组作返回值
- 代码示例

```
package com.zzhhangjiashun;
public class AarrayDemo04 {
    public static void main(String[] args) {
        int[] arrays={1,2,3,4,5};
        //JDK1.5 没有下标
        //For-Each循环
//        for (int array : arrays) {
//            System.out.println(array);
//        }
        printArray(arrays);
        int[] reverse=reverse(arrays);
        printArray(reverse);
    }
    //打印数组元素
    public static void printArray(int[] arrays){
        for (int i=0;i<arrays.length;i++){
            System.out.print(arrays[i]+" ");
        }
    }
    //反转数组
    public static int[] reverse(int[] arrays){
        int[] result=new int[arrays.length];
        //反转的操作
        for (int i=0,j=result.length-1;i<arrays.length;i++,j--){
            result[j]=arrays[i];
        }
        return result;
    }
}
```

- ## 4.多维数组

- 多维数组可以看成是数组的数组，比如二维数组就是一个特殊的一维数组，其每个元素都是一个一维数组

- 二维数组

  ```
  int a[][]=new int[2][5];
  ```

```
package com.zzhhangjiashun.array;
public class ArrayDemo05 {
    public static void main(String[] args) {
        int[][] array={ {1,2},{3,4},{4,5},{5,6} };
        System.out.println(array[0][0]);
        System.out.println(array[0][1]);
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                System.out.println(array[i][j]);
            }
        }
    }
}
```

## 5.Arrays类

- 数组的工具类java.util.Arrays
- 由于数组对象本身并没有什么方法可以供我们调用，API中提供了一个工具类Arrays供我们使用，从而可以对数据对象进行一些基本的操作。
- 查看JDK帮助文档
- Arrays类中的方法都是static修饰的静态方法，在使用的时候可以直接使用类名进行调用，而不用使用对象来调用
- 具有以下常用功能：
  - 给数组赋值：通过fill方法；
  - 对数组排序：通过sort方法，按升序；
  - 比较数组：通过equals方法比较数组中的元素值是否相等。
  - 查找数组元素：通过binarySearch方法能对排序好的数组进行二分查找法操作。
- 代码示例：

```
package com.zzhhangjiashun.array;
import java.util.Arrays;
public class ArrayDemo06 {
    public static void main(String[] args) {
        int[] a={1,435,241,78,22,12421,66};
        System.out.println(a);//[I@1b6d3586
        //打印数组元素
        System.out.println(Arrays.toString(a));
        printArray(a);
        System.out.println();
        System.out.println("====================================================");
        Arrays.sort(a);//数组进行排序
        System.out.println(Arrays.toString(a));
        Arrays.fill(a,0);//数组填充
        System.out.println(Arrays.toString(a));
    }
    public static void printArray(int[] a){
        for (int i=0;i<a.length;i++){
            if (i==0){
                System.out.print("[");
            }
            if (i==a.length-1){
                System.out.print(a[i]+"]");
            }else{
            System.out.print(a[i]+", ");
            }
        }
    }
}
```

- ## 6.冒泡排序

- 冒泡排序是最为出名的排序算法之一，总共有八大排序

- 冒泡代码两层循环，外层冒泡轮数，里层依次比较；

- 代码示例：

- ```
  - ```java
    package com.zzhhangjiashun.array;
  
  import java.util.Arrays;
  
  public class Array07 {
  
    public static void main(String[] args) {
        int[] a={1,35,5558,213,32,436,57876,23};
        int[] sorted=sort(a);//调用完冒泡排序后，返回一个排序后的数组
        System.out.println(Arrays.toString(a));
    }
        //冒泡排序
        //1.比较数组中，两个相邻的元素，如果第一个数比第二个数大，我们就交换他们的位置
        //2.每一次比较，都会产生一个最大，或者最小的数字
        //3.下一轮可以少一次排序
        //4.以此循环，直到结束
        public static int[] sort(int[] array){
            //外层循环，判断我们要走多少次
            boolean flag=false;//通过flag标识减少没有意义的比较
            for(int i=0;i<array.length-1;i++){
                //n内层循环，比较判断两个数，如果第一个数比第二个数大，则交换位置
                for(int j=0;j<array.length-1-i;j++){
                    if (array[j]>array[j+1]){
                        int temp;
                        temp=array[j];
                        array[j]=array[j+1];
                        array[j+1]=temp;
                        flag=true;
                    }
                }
                if (flag==false){
                    break;
                }
            }
            return array;
        }
  ```

  

## 7.稀疏数组
- 一种数据结构
- 需求：编写五子棋游戏中，有存盘退出和续上盘的功能；因为该二维数组的很多值是默认值0，因此记录了很多没有意义的数据。
### 7.1稀疏数组介绍
- 当一个数组中大部分元素为0，或者为同一值的数组时，可以使用稀疏数组来保存该数组。
- 稀疏数组的处理方式是：
    - 记录数组一共有几行几列，有多少个不同的值
    - 把具有不同值的元素和行列及值记录在一个小规模的数组中，从而缩小程序规模
- 如下图：左边是原始数组，右边是稀疏数组：
  <img src="C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20210904181632193.png" alt="image-20210904181632193" style="zoom:50%;" />
- 代码示例：
  ```java
  package com.zzhhangjiashun.array;
  public class ArrayDemo08 {
      public static void main(String[] args) {
          //1.创建一个二维数组11*11 0：没有棋子，1：黑棋，2：白棋；
          int[][] array1=new int[11][11];
          array1[1][2]=1;
          array1[2][3]=2;
          //输出原始的数组；
          System.out.println("输出原始的数组");
          for (int[] ints : array1) {
              for (int anInt : ints) {
                  System.out.print(anInt+"\t");
              }
              System.out.println();
          }
          System.out.println("==================================");
          //转换为稀疏数组保存
          //获取有效值的个数；
          int sum=0;
          for (int i=0;i<11;i++){
              for (int j=0;j<11;j++)
                  if(array1[i][j]!=0){
                      sum++;
                  }
          }
          System.out.println("有效值的个数："+sum);
          //2.创建一个稀疏数组的数组
          int[][] array2=new int[sum+1][3];
          array2[0][0]=11;
          array2[0][1]=11;
          array2[0][2]=sum;
          //遍历二维数组，将非零的值存放到稀疏数组中
          int count=0;
          for(int i=0;i<array1.length;i++){
              for(int j=0;j<array1[i].length;j++){
                  if(array1[i][j]!=0){
                      count++;
                      array2[count][0]=i;
                      array2[count][1]=j;
                      array2[count][2]=array1[i][j];
                  }
              }
          }
          //输出稀疏数组
          System.out.println("稀疏数组");
          for (int i=0;i<array2.length;i++){
              System.out.println(array2[i][0]+"\t"+
                      array2[i][1]+"\t"+array2[i][2]+"\t");
          }
          System.out.println("===============================================");
          System.out.println("还原：");
          //1.读取稀疏数组
          int[][] array3=new int[array2[0][0]][array2[0][1]];
          //2.给其中的元素还原它的值
          for(int i=1;i<array2.length;i++){
              array3[array2[i][0]][array2[i][1]]=array2[i][2];
          }
          //3.打印
          System.out.println("输出还原的数组");
          for (int[] ints : array3) {
              for (int anInt : ints) {
                  System.out.print(anInt+"\t");
              }
              System.out.println();
          }
      }
  }