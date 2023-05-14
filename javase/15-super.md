### super

1. super调用父类的构造方法，必须在构造方法的第一个
2. super必须只能出现在子类的方法或者构造方法中！
3. super和this不能同时调用构造方法！

Vs this:

- 代表的对象不同
  - this：本身调用者这个对象
  - super：代表父类对象的引用
- 前提
  - this：没有继承也可以使用
  - super：只能在继承条件下才可以使用
- 构造方法
  - this()：本类的构造
  - super()：父类的构造！

```
package com.oop;
import com.oop.demo05.Person;
import com.oop.demo05.Student;
//一个项目应该只存在一个main方法
public class Application {
    public static void main(String[] args) {
        Student student = new Student();
        //student.test("小李");
        student.test1();
    }
}
```

```
package com.oop.demo05;
//学生 is 人：派生类，子类
//子类继承了父类，就会拥有父类的全部方法！
public class Student extends Person {
    //父类没有无参构造方法，子类也不能写无参构造器
    public Student() {
        //隐藏代码：调用了父类的无参构造方法
        super();//调用父类的构造器必须放在子类构造器的第一行
        System.out.println("Student无参执行了");
    }
    private String name = "xiaoli";
    public void print(){
        System.out.println("Student");
    }
    public void test1(){
        print();//Student
        this.print();//Student
        super.print();//Person
    }
    public void test(String name){
        System.out.println(name);
        System.out.println(this.name);
        System.out.println(super.name);
    }
}
```

```
package com.oop.demo05;
//在java中，所有的类，都默认直接或者间接继承Object
//Person 人:父类
public class Person {
    protected String name = "xiaohong";
    public Person() {
        System.out.println("Person无参执行了");
    }
    //如果改成私有的无法被继承！
    public void print(){
        System.out.println("Person");
    }
}
```

