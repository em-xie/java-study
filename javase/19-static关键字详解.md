## static关键字详解

示例1：属性和方法的调用

```
package OOP.Demo7;
//static
public class Student {
    private static int age;//静态变量
    private double score;//非静态变量
    //非静态方法可调用非静态和静态方法
    //但静态方法只能调用静态方法，而不能调用非静态方法
    //非静态方法
    public void run(){
    }
    //静态方法
    public static void go(){
    }
    public static void main(String[] args) {
        //run();调用异常，不能直接调用非静态方法
        go();//可以直接调用本类中的方法
        Student s1 = new Student();
        System.out.println(Student.age);
        System.out.println(s1.age);
        System.out.println(s1.score);
    }
}
```

代码块：

    {//作用：可赋予一些初始值，一般第二次执行
        //代码块(匿名代码块)
    }
    static{//只执行一次，且在第一次执行
        //静态代码块
    }
    public Person(){
        //构造方法，一般第三个执行
    }

示例2：

```
package OOP.Demo7;
public class Person {
    {
        System.out.println("匿名代码块");
    }
    static{
        System.out.println("静态代码块");
    }
    public Person(){
        System.out.println("构造方法");
    }
    public static void main(String[] args) {
        Person person1 = new Person();
        System.out.println("==========");
        Person person2 = new Person();
    }
}
/*
静态代码块
匿名代码块

构造方法
==========

匿名代码块
构造方法
*/
```

通过final修饰的类就不能被继承了，没有子类了

```
package OOP.Demo7;
public class English extends Test{
}
```

```
public final class Test {
    public static void main(String[] args) {
        System.out.println(random());//就不需要在random前加上“Math.”
    }
}
```

这里的English类是不能作为Test的子类，会报错