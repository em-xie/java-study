## Java多态、抽象类、接口

### 一、多态

- 概述：某一个事物，在不同时刻表现出来的不同状态

  举例：

  Cat c=new Cat();
  Animal a=new Cat();
  猫可以是猫的类型。猫 m = new 猫();
  同时猫也是动物的一种，也可以把猫称为动物。动物 d = new 猫();

- 多态的前提

  a:要有继承关系
  b:要有方法重写，如果没有就没有意义
  c:要有父类引用指向子类对象

- 案例

  \``````````

```
public class MyTest {

public static void main(String[] args) {
    //通过多态创建对象
    Animal an=new Cat();
    an.sleep();
    an.eat();
    an.show();
}
```

```
class Animal{
    public void eat(){
        System.out.println("吃饭");
    }
    public void sleep() {
        System.out.println("睡觉");
    }
    public void show() {
        System.out.println("父类中的show方法");
    }
}
class Cat extends Animal{
    @Override
    public void eat() {
        System.out.println("猫爱吃鱼");
    }
    @Override
    public void sleep() {
        System.out.println("猫爱白天睡觉");
    }
}
```

**多态中的成员访问特点**

a:成员变量
编译看左边，运行看左边
b:构造方法
创建子类对象的时候，会访问父类的构造方法，对父类的数据进行初始化
c:成员方法
编译看左边，运行看右边
d:静态方法
编译看左边，运行看左边(静态和类相关，算不上重写，所以，访问还是左边的)

``````````
class Demo {
    public static void main (String[] args) {
        Base b = new SubClass();
        System.out.println(b.x);//2
        System.out.println(b.method() );//3
    }
}
class Base {
        int x = 2;
        int method(){
        return x;
    }
}
class SubClass extends Base {
        int x = 3;
        int method() {
        return x;
    }
}
``````````

- 多态的好处

  - 提高了代码的维护性(继承保证)
  - 提高了代码的扩展性(由多态保证)

- 多态的弊端以及多态中向上转型和向下转型

  不能使用子类特有的功能，如果要使用，需要父类的引用强制转换为子类的引用

```
//描述动物类，并抽取共性eat方法
abstract class Animal {
    abstract void eat();
}
// 描述狗类，继承动物类，重写eat方法，增加lookHome方法
class Dog extends Animal {
    void eat() {
        System.out.println("啃骨头");
    }
    void lookHome() {
        System.out.println("看家");
    }
}
// 描述猫类，继承动物类，重写eat方法，增加catchMouse方法
class Cat extends Animal {
    void eat() {
        System.out.println("吃鱼");
    }
    void catchMouse() {
        System.out.println("抓老鼠");
    }
}
public class Test {
    public static void main(String[] args) {
        Animal a = new Dog(); //多态形式，创建一个狗对象
        a.eat(); // 调用对象中的方法，会执行狗类中的eat方法
        // a.lookHome();//使用Dog类特有的方法，需要向下转型，不能直接使用
        // 为了使用狗类的lookHome方法，需要向下转型
        Dog d = (Dog) a; //向下转型
        d.lookHome();//调用狗类的lookHome方法
    }
}
```

- 多态的内存图解

### 二、抽象类

- 概述

  方法功能声明相同，但方法功能主体不同。那么这时也可以抽取，但只抽取方法声明，不抽取方法主体。那么此方法就是一个抽象方法

- 定义格式

  抽象类格式: abstract class 类名 {}
  抽象方法格式: public abstract void eat();

- 抽象类特点

  1. 抽象类和抽象方法都需要被abstract修饰。抽象方法一定要定义在抽象类中
  2. 抽象类不可以直接创建对象，原因：调用抽象方法没有意义
  3. 只有覆盖了抽象类中所有的抽象方法后，其子类才可以创建对象。否则该子类还是一个抽象类

- 案例

```
abstract class Developer {
    public abstract void work();//抽象方法 需要abstract修饰，并分号;结束
}
//JavaEE工程师
class JavaEE extends Developer{
    public void work() {
        System.out.println("正在研发淘宝网站");
    }
}
//Android工程师
class Android extends Developer {
    public void work() {
        System.out.println("正在研发淘宝手机客户端软件");
    }
}
```

- abstract不能和哪些关键字共存?
  - private 被私有的成员只能在本类中调用，而abstract强制重写抽象方法，二者冲突
  - final final表示最终的，被修饰的类和方法不能被继承和重写，与abstract强制重写抽象方法冲突
  - static 无意义

### 三、接口

- 概述

  接口是额外功能的集合，同样可看做是一种数据类型，是比抽象类更为抽象的”类”。接口只描述所应该具备的方法，并没有具体实现

- 定义格式

  public interface 接口名 {

  抽象方法1;

  抽象方法2;

  抽象方法3;

  }

- 类实现接口

  class 类 implements 接口 {

  重写接口中方法

  }

  在类实现接口后，该类就会将接口中的抽象方法继承过来，此时该类需要重写该抽象方法，完成具体的逻辑，可以多实现

```
interface Fu1{
    void show();
}
interface Fu2{
    void show1();
}
interface Fu3{
    void show2();
}
interface Zi extends Fu1,Fu2,Fu3{
    void show3();
}
```

- 接口的子类

  a:可以是抽象类。但是意义不大。
  b:可以是具体类。要重写接口中的所有抽象方法。(推荐)

- **接口的成员特点**

  - 成员变量：只能是常量，并且是静态的，默认有public static final修饰
  - 成员方法：只能是抽象方法，默认有public abstract修饰
  - 构造方法：接口没有构造方法

- 案例

```
interface Demo { ///定义一个名称为Demo的接口。
    public static final int NUM = 3;// NUM的值不能改变
    public abstract void show1();
    public abstract void show2();
}
//定义子类去覆盖接口中的方法。类与接口之间的关系是 实现。通过 关键字 implements
class DemoImpl implements Demo { //子类实现Demo接口。
    //重写接口中的方法。
    public void show1(){}
    public void show2(){}
}
```

### 四、类与类,类与接口,接口与接口的关系

- 类与类：继承关系,只能单继承,可以多层继承
- 类与接口：实现关系,可以单实现,也可以多实现。并且可以在继承一个类的同时实现多个接口
- 接口与接口：继承关系,可以单继承,也可以多继承

### 五、抽象类和接口的区别

- 成员区别

  抽象类：

  成员变量：可以是变量，也可以是常量

  成员方法：可以死抽象类，也可以非抽象类

  构造方法：有

  接口：

  成员变量：只能是常量

  成员方法：只能是抽象类

  构造方法：无

- 关系区别

  抽象方法：

  类与类：继承，单继承
  类与接口：实现，单实现，多实现
  接口与接口：继承，单继承，多继承

- 设计理念区别

  抽象类：体现的是“is a ”的关系，抽象类中定义的是该继承体系的共性功能

  接口：体现的是“like a ”的关系，接口中定义的是该继承体系的个性功能（扩展功能）