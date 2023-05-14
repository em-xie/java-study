# Java方法

### 何谓方法？

- System.out.println()，到底是个什么？

  答：解释为调用系统System的标准输出对象out中的方法println。

- Java方法是语句的集合，它们在一起执行一个功能。

  - 方法是解决一类问题的步骤的有序集合。
  - 方法包含于类型或者对象中。
  - 方法在程序中被创建，在其他地方被引用。

- 设计方法的原则：方法的本意是功能块，就是实现某个功能的语句的集合。我们设计方法的时候，最好保持方法的原子性，`就是一个方法只能完成一个功能，这样利于我们后期的拓展。`

- `回顾：方法的命名规则？`

  答：方法的名字的第一个单词应以**小写字母**作为开头，后面的单词则用**大写字母**开头。示例：**demoFunction**，也就是所谓的驼峰命名规则。

- `Java都是值传递。`

## 方法

### 方法的定义

- Java的方法类似于其他语言的函数，是一段`用来完成特定功能的代码片段`。

- 方法包含一个`方法`和一个`方法体`，下面是一个方法的所有部分：

  - `修饰符`：修饰符，可选，告诉编译器如何调用方法，定义该方法的访问类型。

  - `返回值类型`：方法可能会返回值。returnValueType是方法返回值的数据类型，有些方法执行所需的操作，但没有返回值，在这种情况下，returnValueType的关键字是void。

  - `方法名`：是方法的实际名称，方法和参数表共同构成方法签名。

  - ```
    参数类型
    ```

    ：参数是一个占位符，当方法被调用时，传递值给参数，这个值被称为

    ```
    实参或者变量
    ```

    ，参数列表是指方法的参数类型、顺序和参数的个数，参数是可选的，

    ```
    方法可以不包含任何参数
    ```

    。

    - 形参：在方法被调用时用于接收外界的数据。
    - 实参：被调用方法时实际传给方法的数据。。

  - `方法体`：方法体包含具体的语句，定义该方法的功能。

- 方法的格式

  ```java
  修饰符 返回值类型 方法名(参数类型 参数名){
      方法体
      return 返回值;   //返回值要与返回值的类型对应
  }
  ```

#### 如何使用方法？

eg.使用方法实现加法运算，以及大小比较。

```
public class Method {
    public static void main(String[] arg){
        Scanner a = new Scanner(System.in);
        Scanner b = new Scanner(System.in);
        //两种调用方法
        //方法1：
        int sum = add(a.nextInt(),b.nextInt());
        System.out.println(sum);
        //方法2：
        System.out.println("大小比较");
        System.out.println(Max(a.nextInt(),b.nextInt()));
    }
    //方法
    public static int add(int a,int b){
        return a+b;
    }
    public static int Max(int a,int b){
        int max = 0;
        if (a == b){
            System.out.println("两个数相等");
            return 0;
        }
        if (a < b){
            max = b;
            return max;
        }else {
            max = a;
            return max;
        }
    }
}
```

### 方法的重载

- 重载就是在一个类中，有相同的函数名称，但形参不同的函数。
- 方法的重载规则：
  - 方法的名称必须相同。
  - 参数列表必须不同（个数不同、类型不同、顺序不同）。
  - 方法的返回类型可以相同，也可以不相同。
  - 仅返回类型不同不足以成为方法的重载。
- 实现理论：
  - 方法名称相同时，编译器会根据调用方法的参数的个数、参数类型等去逐个匹配，以选择对应的方法，如果匹配失败，则编译器报错。

```
public static int add(double a, double b){ return a+b; }
public static int add(int a, int b){ return a+b; }
public static int add(int a, int b, int c){return a+b+c;}
```

```
public class java {
    public static void main(String[] arg){
        //此为方法的重载。简单解释为方法名相同，但值可能不同不起冲突实现重载的功能。
        //方法重载的规则:
        //   .方法名必须相同
        //   .参数列表必须不同（个数不同、或类型不同、参数排列顺序不同等）
        //   .方法返回值类型可以相同也可以不相同
        //   .仅仅返回类型不同不足以成为方法的重载
       int sum = add(1,2);
       int sum1 = add(1,2,3);
       System.out.println(sum);
       System.out.println(sum1);
    }
    public static int add(int a, int b){ return a+b; }
    public static int add(int a, int b, int c){
        return a+b+c;
    }
}
```

### 命令行传参（只作为了解）

- 有时候你希望运行一个程序时候再传递给他消息，这要靠传递命令行参数给main()函数实现。

```
public class commd {
    public static void main(String[] args) {
        //数组的长度
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "]:" + args[i]);
        }
    }
}
```

**用法**：在命令行Java的文件下按照图中所示输入cmd，回车。

![img](https://img-blog.csdnimg.cn/img_convert/4c35f500ef624192c05ed9e61587486f.png)

先编译，在运行java 文件名 你要传的东西

![img](https://img-blog.csdnimg.cn/img_convert/a6c4553aaf41b647bbcdba2372af2483.png)

### 可变参数

- JDK1.5开始，Java支持传递同类型的可变参数给一个方法。
- 在方法的声明中，在指定参数类型后加一个省略号（…）。
- 一个方法中只能指定一个可变参数，它必须是方法的最后一个参数。任何普通的参数必须在它之前声明。

#### 实例

eg.找出最大值

```
public class java {
    public static void main(String[] args){
        java12 max = new java12();
        max.printMax(45,78,96,74,52,63,82,71,93);
    }
    //可变参数
    public void printMax(double... i){
        if (i.length == 0){
            System.out.println("You didn't type the numbers");
            return;
        }
        //比较大小
        double max = i[0];
        for (int j=1 ; j<i.length ; j++){
            if (i[j] > max){
                max = i[j];
            }
        }
        System.out.println("The maximum number is "+max);
    }
}
```

### 递归

- 递归就是自己调用自己。
- 递归策略只需少量的程序就可描述出解题过程所需要的多次重复计算，大大地减少了程序的代码量。递归的能力在于用有限的语句来定义对象的无限集合。
- 递归结构包括两个部分：
  - 递归头：什么时候不调用自身方法。如果没有头，将陷入死循环。
  - 递归体：什么时候需要调用自身方法。
- 注意点：
  1. 适用于小计算。
  2. 能不使用递归尽量不使用。
  3. 自己调用自己。

#### 实例

eg.计算5的阶乘。

```
public class java {
    public static void main(String[] arg){
        /*递归：
        适用于小计算。
        1.能不使用递归尽量不使用
        2.自己调用自己*/
        java n = new java();
        System.out.println(n.diGui(5));
    }
    public int diGui(int i){
        if (i == 1){
            return 1;
        }else {
            return i*diGui(i-1);
        }
    }
}
```

#### 流程解释

![digui](https://img-blog.csdnimg.cn/img_convert/0138d68f46daf91257cccae50b3d7438.png)