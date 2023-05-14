# JAVA异常机制

## 什么是异常

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/06/05/kuangstudy756373b1-341a-4c2a-8b32-d87c1cc9cf59.png)

### 异常处理框架

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/06/05/kuangstudye7ab1bb0-9c7c-42a9-8bc5-40d708b87c32.png)
![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/06/05/kuangstudy2ebe40d2-76f6-4052-8798-2fd516e08c1a.png)
![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/06/05/kuangstudy508b1797-1ead-43b4-a3f2-ae85c13328ca.png)

```
package com.exception;
public class Demo01 {
    public static void main(String[] args) {
        new Demo01().a();
    }
    public void a() {
        b();
    }
    public void b() {
        a();
    }
}
```

## 异常处理机制

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/06/05/kuangstudyf60a0632-916f-41f9-955b-49ea1b913bd2.png)

```
package com.exception;
public class Test {
    public static void main(String[] args) {
        int a = 1;
        int b = 0;
        //快捷键ctrl+alt+t+(win)
        try {//监控区域
            System.out.println(a / b);
        } catch (ArithmeticException e) {
            //捕获异常 catch:想要捕获的异常类型
            new Test().a();
            System.out.println("程序出现异常，变量b不能为0");
        } finally {
            //无论是否出现异常都会运行
            //善后工作
            System.out.println("finally");
            //finally 可以不要finally,一般用于IO流,资源相关的东西,关闭的操作放在finally里面
        }
    }
    public void a() {
        b();
    }
    public void b() {
        a();
    }
}
```

```
package com.exception;
public class Test2 {
    public static void main(String[] args) {
        int a = 1;
        int b = 0;
        //假设要捕获多个异常:从小到大
        try {//监控区域
            System.out.println(a / b);
        } catch (Error e) {
            //捕获异常 catch:想要捕获的异常类型
            new Test().a();
            System.out.println("error");
        } catch (Exception e) {
            System.out.println("exception");
        } catch (Throwable t) {
            //最大的异常写在最后，否则小的会不执行
            System.out.println("throwable");
        } finally {
            //无论是否出现异常都会运行
            //善后工作
            System.out.println("finally");
            //finally 可以不要finally,一般用于IO流,资源相关的东西,关闭的操作放在finally里面
        }
    }
    public void a() {
        b();
    }
    public void b() {
        a();
    }
}
```

```
package com.exception;
public class Test3 {
    public static void main(String[] args) {
        int a = 1;
        int b = 0;
        try {
            System.out.println(a / b);
        } catch (Exception e) {
            System.exit(0);
            e.printStackTrace();//打印错误的栈信息
        }
    }
}
```

```
package com.exception;
public class Test4 {
    public static void main(String[] args) {
        try {
            new Test4().test(1, 0);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }
    //假设这个方法中,处理不了这个异常，我们需要在方法上抛出异常throws
    public void test(int a, int b) throws ArithmeticException {
        if (b == 0) {//主动抛出异常 throw throws
            throw new ArithmeticException();//主动地抛出异常
        }
//        System.out.println(a / b);
    }
}
/* try {//监控区域
            if (b == 0) {//主动抛出异常 throw throws
                throw new ArithmeticException();//主动地抛出异常
            }
            System.out.println(a / b);
        } catch (ArithmeticException e) {
            //捕获异常 catch:想要捕获的异常类型
            System.out.println("程序出现异常，变量b不能为0");
        } finally {
            //无论是否出现异常都会运行
            //善后工作
            System.out.println("finally");
            //finally 可以不要finally,一般用于IO流,资源相关的东西,关闭的操作放在finally里面
        }
 */
```

## 自定义异常

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/06/05/kuangstudybfabc9af-7162-4015-8860-053d7e003f41.png)

```
package com.exception.demo02;
//自定义的异常类
public class MyException extends Exception {
    //传递数组>10;
    private int detai1;
    public MyException(int a) {
        this.detai1 = a;
    }
    //toString:异常的打印信息
    @Override
    public String toString() {
        return "MyException{" +
                "detai1=" + detai1 +
                '}';
    }
}
```

```
package com.exception.demo02;
public class Test {
    //可能存在异常的方法
    static void test(int a) throws MyException {
        System.out.println("传递的参数为:" + a);
        if (a > 10) {
            throw new MyException(a);//抛出
        }
        System.out.println("ok");
    }
    public static void main(String[] args) {
        try {
            test(11);
        } catch (MyException e) {
            //增加一些处理异常的代码
            System.out.println("MyException>" + e);
        }
    }
}
```

