# instanceof和类型转换

```
package com.oop;
import com.oop.demo06.Person;
import com.oop.demo06.Student;
import com.oop.demo06.Teacher;
public class Application {
    public static void main(String[] args) {
        //类型之间的转换： 父  子   基本类型转换 高低64 32 16 8
        //高                    低                 高转低  强制转换
        Person obj = new Student();
        //student将这个对象转换为Student类型，我们就可以使用Student类型的方法了！
        ((Student) obj).go();
        //子类转换为父类，可能丢失自己的本来的一些方法！
        Student student = new Student();
        student.go();
        Person person = student;
    }
}
/*
1.父类引用指向子类的对象
2.把子类转换为父类，向上转型：不用强制转换
3.把父类转换为子类，向下转型：强制转换
4.方便方法的调用，减少重复的代码！简洁
抽象：封装、继承、多态  抽象类，接口
 */
```

```
package com.oop.demo06;
public class Person {
    public void run(){
        System.out.println("run");
    }
}
```

```
package com.oop.demo06;
public class Student extends Person {
    public void go(){
        System.out.println("go");
    }
}
/*
//Object > String
        //Object > Person > Teacher
        //Object > Person > Student
        Object object = new Student();
        //System.out.println(X instanceof Y);//能不能编译通过！接口   有父子关系编译通过
        System.out.println(object instanceof Student);//true
        System.out.println(object instanceof Person);//true
        System.out.println(object instanceof Object);//true
        System.out.println(object instanceof Teacher);//false
        System.out.println(object instanceof String);//false
        System.out.println("****************************=");
        Person person = new Student();
        System.out.println(person instanceof Student);//true
        System.out.println(person instanceof Person);//true
        System.out.println(person instanceof Object);//true
        System.out.println(person instanceof Teacher);//false
        //System.out.println(person instanceof String);//编译报错！
        System.out.println("****************************=");
        Student student = new Student();
        System.out.println(student instanceof Student);//true
        System.out.println(student instanceof Person);//true
        System.out.println(student instanceof Object);//true
        //System.out.println(student instanceof Teacher);//编译报错！
        //System.out.println(person instanceof String);//编译报错！
 */
```

```
package com.oop.demo06;
public class Teacher extends Person {
}
```

