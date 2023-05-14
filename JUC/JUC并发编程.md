# JUC并发编程

## 什么是JUC

**源码+JDK帮助文档**

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudyb63be231-9223-4e0e-a642-4a676f36e768.png)

java.util 工具包、包、分类

**业务：普通的线程代码 Thread**

**Runnable：**没有返回值，效率相比于Callable相对较低！

![JUC](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudya890b70e-232d-4438-b9ca-56eab95aeb26.png)

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudyee396a11-6e7f-4bd8-9b39-812a762e54b5.png)

## 线程和进程

> 线程、进程，如果不能使用一句话说出来的技术，不扎实！

**进程**：一个程序，QQ.exe Music.exe 程序的集合；

一个进程往往可以包含多个线程，至少包含一个！

Java默认有几个线程？`2个 main、GC`

**线程**：开了一个进程Typora，写字，自动保存（线程负责的）

对于Java而言：Thread、Runnable、Callable

**Java真的能开启线程吗？**`不能`

```
public synchronized void start() {
    /**
         * This method is not invoked for the main method thread or "system"
         * group threads created/set up by the VM. Any new functionality added
         * to this method in the future may have to also be added to the VM.
         *
         * A zero status value corresponds to state "NEW".
         */
    if (threadStatus != 0)
        throw new IllegalThreadStateException();
    /* Notify the group that this thread is about to be started
         * so that it can be added to the group's list of threads
         * and the group's unstarted count can be decremented. */
    group.add(this);
    boolean started = false;
    try {
        start0();
        started = true;
    } finally {
        try {
            if (!started) {
                group.threadStartFailed(this);
            }
        } catch (Throwable ignore) {
            /* do nothing. If start0 threw a Throwable then
                  it will be passed up the call stack */
        }
    }
}
// 本地方法，底层的C++，Java无法直接操作硬件
private native void start0();
```

> 并发、并行

并发编程：并发、并行

并发（多线程操作同一个资源）

- CPU一核，模拟出来多条线程，天下武功，唯快不破，快速交替

并行（多个人一起走）

- CPU多核，多个线程可以同时执行；

```
public class test {
    public static void main(String[] args) {
        // 获取CPU的核数
        // CPU密集型，IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
```

结果：我只有4核`好辣鸡`

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy01b7faa5-7b8c-47c5-aec4-7c557891362c.png)

并发编程的本质：**充分利用CPU的资源**

**所有的公司都很看重！**

```
企业，挣钱=》提高效率，裁员，找一个厉害的人顶替三个不怎么样的人；
人员（减）、技术成本（高）
```

线程有几个状态

```
public enum State {
    // 新生
    NEW,
    // 运行
    RUNNABLE,
    // 阻塞
    BLOCKED,
    // 等待，一直等
    WAITING,
    // 超时等待
    TIMED_WAITING,
    // 终止
    TERMINATED;
}
```

> wait和sleep的区别

1. 来自不同的类

   wait**》Object

   sleep**》Thread

2. 关于锁的释放

   wait会释放，sleep睡着了，抱着锁睡，不会释放！

3. 使用的范围是不同的

   wait：必须再同步代码块中`因为要等一个对象`

   sleep：任何地方都能睡！

## Lock锁（重点）

> 传统Synchronized

 * ```
 /**
 
  * 真正的多线程开发，公司中的开发
     * 线程就是一个单独的资源类，没有任何附属的操作！
     * 1、属性、方法
       */
       // 基本的卖票例子
    ```
    
    
    
    ```
    public class saleTicketDemo01 {
    public static void main(String[] args) {
        // 并发，多线程操作同一个资源类
        Ticket ticket = new Ticket();
        // @FunctionalInterface 函数式接口，jdk1.8之后采用lambda表达式（参数）->{代码}
        new Thread(()->{
            for(int i=0;i<30;++i){
                ticket.sale();
            }
        },"A").start();
        new Thread(()->{
         for(int i=0;i<30;++i){
             ticket.sale();
         }
        },"B").start();
        new Thread(()->{
            for(int i=0;i<30;++i){
                ticket.sale();
            }
        },"C").start();
    }
    }
 // synchronized
 class Ticket{
 // 属性、方法
 private int number=20;
 //卖票的方式
 public synchronized void sale(){
     if (number>0){
         System.out.println(Thread.currentThread().getName()+"卖出了第"+(number--)+"张票,剩余："+number);
     }
 }
 }
 ```
 
 

> Lock接口

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy97da555a-558b-4c2a-86fb-e475c80b8b5b.png)

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudyddf64003-8914-4b46-bc68-48e36d46e359.png)

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy250bd8f4-feb9-4e71-9759-ccf4cac0a585.png)

公平锁：十分公平，先来后到

非公平锁：十分不公平，可以插队（默认）

为什么默认非公平锁呢？`因为公平?`

```
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        // 并发，多线程操作同一个资源类
        Ticket2 ticket = new Ticket2();
        // @FunctionalInterface 函数式接口，jdk1.8之后采用lambda表达式（参数）->{代码}
        new Thread(()->{for(int i=0;i<30;++i)ticket.sale(); },"A").start();
        new Thread(()->{for(int i=0;i<30;++i)ticket.sale(); },"B").start();
        new Thread(()->{for(int i=0;i<30;++i)ticket.sale(); },"C").start();
    }
}
// lock三部曲
// 1、new ReentrantLock();
// 2、lock.lock();//加锁
// 3、finally {
//         lock.unlock(); //解锁
//         }
class Ticket2{
    // 属性、方法
    private int number=20;
    Lock lock=new ReentrantLock();
    //卖票的方式
    public void sale(){
        lock.lock();//加锁
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了第"+(number--)+"张票,剩余："+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); //解锁
        }
    }
}
```

> Synchronaized 和 Lock区别

1. Synchronized 内置Java关键字，Lock是一个Java类
2. Synchronized 无法判断获取锁的状态，Lock可以判断是否获取到了锁
3. Synchronized 会自动释放锁，Lock必须手动释放锁！如果不释放锁可能会造成**死锁**
4. Synchronized 线程1（获得锁，阻塞），线程2（等待，一直等）；Lock锁就不一定会等；`lock.tryLock();尝试获取锁`
5. Synchronized 可重入锁，不可以中断的，非公平；Lock，可重入锁，可以判断锁，非公平（可以自己设定）；
6. Synchronized 适合锁少量的代码同步问题，Lock适合锁大量的同步代码！

> 锁是什么，如何判断锁的是谁？

## 生产者和消费者问题

**面试的问题**：单例模式、排序算法、生产者和消费者、死锁

### 生产者和消费者问题 Synchronized版

```
public class Demo01 {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}
// 判断等待，业务，通知
class Data{ //数字 资源类
    private int number = 0;
    // +1
    synchronized void increment() throws InterruptedException {
        if (number!=0){
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"**>"+number);
        // 通知其他线程我操作完毕了
        this.notifyAll();
    }
    // -1
    synchronized void decrement() throws InterruptedException {
        if (number**0){
            // 等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"**>"+number);
        // 通知其他线程我操作完毕了
        this.notifyAll();
    }
}
```

> 问题存在，A B C D 4个线程还安全吗？ `虚假唤醒问题！`

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy04d073b2-2615-421e-93f9-3cf277e54080.png)

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy99000198-ea4f-4c22-93fd-17ece1e40410.png)

**if 改为 while**完美解决问题

### 生产者和消费者问题JUC版

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudyf3c04545-6b71-497e-b90d-60333a3b2609.png)

通过Lock找到Condition

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy68a78e6f-eabc-4ef1-bc03-ae12a164da0a.png)

```
 class BoundedBuffer {
   final Lock lock = new ReentrantLock();
   final Condition notFull  = lock.newCondition(); 
   final Condition notEmpty = lock.newCondition(); 
   final Object[] items = new Object[100];
   int putptr, takeptr, count;
   public void put(Object x) throws InterruptedException {
     lock.lock(); try {
       while (count ** items.length)
         notFull.await();
       items[putptr] = x;
       if (++putptr ** items.length) putptr = 0;
       ++count;
       notEmpty.signal();
     } finally { lock.unlock(); }
   }
   public Object take() throws InterruptedException {
     lock.lock(); try {
       while (count ** 0)
         notEmpty.await();
       Object x = items[takeptr];
       if (++takeptr ** items.length) takeptr = 0;
       --count;
       notFull.signal();
       return x;
     } finally { lock.unlock(); }
   }
 }
```

代码实现：

```
public class Demo02 {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
// 判断等待，业务，通知
class Data2{ //数字 资源类
    private int number = 0;
    // +1
    final Lock lock=new ReentrantLock();
    Condition condition = lock.newCondition();
    void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number!=0){
                // 等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"**>"+number);
            // 通知其他线程我操作完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    // -1
    void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number**0){
                // 等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"**>"+number);
            // 通知其他线程我操作完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
```

任何一个新的技术，绝对不仅仅只是覆盖了原有的的技术，应该有优势及补充！

> Condition 精准的通知和唤醒

```
代码实现：

public class Demo03 {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        },"C").start();
    }
}
class Data3{
    private int number=1;
    Lock lock=new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    void printA() {
        lock.lock();
        try {
            while (number!=1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"**>AAAA");
            number=2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    void printB() {
        lock.lock();
        try {
            while (number!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"**>BBBB");
            number=3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    void printC() {
        lock.lock();
        try {
            while (number!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"**>CCCC");
            number=1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
```

## 8锁现象

如何判断锁的是谁？永远的知道什么是锁！锁的到底是谁！

深刻理解我们的锁

 * ```
 package edu.hut.lock8;
 import java.util.concurrent.TimeUnit;
    /**
    
     * @author DennngW
     * @date 2021-01-31
       */
    /**
     * 8锁，就是关于锁的8个问题
     * 1、标准情况下，两个线程先打印发短信还是打电话？
     * 2、sendMsg延迟3秒，两个线程先打印发短信还是打电话？
       */
       public class Test1 {
       public static void main(String[] args) {
           Phone phone = new Phone();
           // 锁的存在
           new Thread(()->{
               phone.sendMsg();
    },"A").start();
           try {
           TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
           e.printStackTrace();
           }
           new Thread(()->{
           phone.call();
           },"B").start();
           }
           }
       class Phone {
       // synchronized 锁的对象是方法的调用者
       // 两个方法用的是同一个锁，谁先拿到谁先执行
    public synchronized void sendMsg(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
    }
 ```
 
 

 * ```
 package edu.hut.lock8;
 import java.util.concurrent.TimeUnit;
    /**
    
     * @author DennngW
     * @date 2021-01-31
       */
       /**
     * 3、增加了一个普通方法hello，先执行发短信还是hello
     * 4、两个对象，两个同步方法，先执行发短信还是打电话
       */
       public class Test2 {
       public static void main(String[] args) {
           Phone2 phone1 = new Phone2();
           Phone2 phone2 = new Phone2();
           // 锁的存在
           new Thread(()->{
               phone1.sendMsg();
        },"A").start();
        try {
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           new Thread(()->{
               phone2.call();
           },"B").start();
       }
       }
       class Phone2 {
       // synchronized 锁的对象是方法的调用者
       public synchronized void sendMsg(){
           try {
               TimeUnit.SECONDS.sleep(3);
           } catch (InterruptedException e) {
               e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
    // 这里没有锁！不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("Hello!");
    }
    }
 ```
 
 

```
package edu.hut.lock8;
import java.util.concurrent.TimeUnit;
/**

 * @author DennngW
 * @date 2021-01-31
   */
   /**
 * 7、1个静态同步方法，1个普通同步方法，一个对象，先打印发短信还是打电话？
 * 8、1个静态同步方法，1个普通同步方法，两个对象，先打印发短信还是打电话？
   */
   public class Test4 {
   public static void main(String[] args) {
       Phone4 phone = new Phone4();
       Phone4 phone2 = new Phone4();
       // 锁的存在
       new Thread(()->{
           phone.sendMsg();
       },"A").start();
       try {
           TimeUnit.SECONDS.sleep(1);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       new Thread(()->{
           phone2.call();
       },"B").start();
   }
   }
   class Phone4 {
   // 静态的同步方法
   public static synchronized void sendMsg(){
       try {
           TimeUnit.SECONDS.sleep(3);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       System.out.println("发短信");
   }
   // 普通同步方法
   public synchronized void call(){
       System.out.println("打电话");
   }
   }


```

> 小结

new this 具体的一个手机

static Class 唯一的一个模板

## 集合类不安全

### List不安全

```
package edu.hut.unsafe;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
/**

 * @author DennngW
 * @date 2021-01-31
   */
   // java.util.ConcurrentModificationException 并发修改异常
   public class ListTest {
   public static void main(String[] args) {
       // 并发下ArrayList不安全
       /*
           解决方案：
           1、List<String> list = new Vector<>();
           2、List<String> list = Collections.synchronizedList(new ArrayList<>());
           3、List<String> list = new CopyOnWriteArrayList<>();
        */
       // CopyOnWrite写入时复制，简称COW  计算机程序设计领域的一种优化策略，
       // 多个线程调用的时候，list，读取的时候是固定的，写入就会发生覆盖（set方法）
       // 在写入的时候避免发生覆盖，造成数据问题！
       // 读写分离
       // CopyOnWriteArrayList    比   Vector  NB在哪里？（底层没有用synchronized，而是用的lock）    
       List<String> list = new CopyOnWriteArrayList<>();
       for (int i = 1; i <=10 ; i++) {
           new Thread(()->{
               list.add(UUID.randomUUID().toString().substring(0,5));
               System.out.println(list);
           },String.valueOf(i)).start();
       }
   }
   }


```

### Set不安全

```
// java.util.ConcurrentModificationException 并发修改异常
public class SetTest {
    public static void main(String[] args) {
        // Set<String> set = new HashSet<>();
        // Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
```

```
HashSet底层是什么？

public HashSet() {
    map = new HashMap<>();
}
// add set 本质就是map的key  因为key是无法重复的！
public boolean add(E e) {
    return map.put(e, PRESENT)**null;
}
// 不变的值！
private static final Object PRESENT = new Object();
```

### Map不安全

回顾map的基本操作

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy67ffbd8a-de27-40ca-aacb-50abf7f152b4.png)

```
public class MapTest {
    public static void main(String[] args) {
        // map 是这样用的吗？ 不是，工作中不用HashMap
        // 默认等价于什么？ new HashMap<>(16,0.75f);
        // Map<String, String> map = new HashMap<>();
        // Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
```

## Callable

1、可以有返回值

2、可以抛出异常

3、方法不同，run()/call()

> 实现机制

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy8911f79e-5228-41ef-8632-1f380e911365.png)

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy25317131-d25f-499b-93d2-b334e2096494.png)

> 代码测试

```
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        FutureTask<String> futureTask = new FutureTask<>(myThread);
        new Thread(futureTask,"A").start();//适配类
        String s = futureTask.get();//获取Callable的返回结果
        System.out.println(s);
    }
}
class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("call:");
        return "7355608";
    }
}
```

1、**get方法可能会产生阻塞**`因为要等执行完才会有结果`

2、有缓存

## 常用的辅助类

### CountDownLatch

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy58fdf6b3-e5e4-499b-9a99-9540aeec05d5.png)

代码测试：

```
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 总数是6，必须要执行任务的时候，再使用！
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"go out");
                countDownLatch.countDown(); // 数量减1
            },String.valueOf(i)).start();
        }
        countDownLatch.await(); // 等待计数器归零，然后再向下执行
        System.out.println("close door");
    }
}
```

原理：

**countDownLatch.countDown();** // 数量减1

**countDownLatch.await();** // 等待计数器归零，然后再向下执行

每次有线程调用countDown()数量-1，假设计数器变为0，countDownLatch.await()就会被唤醒，继续执行下面的代码！

### CyclicBarrier

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy701148bc-0628-4245-8587-8425cdeeafcf.png)

```
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () ->System.out.println("召唤神龙！"));
        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集了第"+temp+"颗龙珠！");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
```

### Semaphore

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudycd714cef-a2c0-4099-a66c-d5eb87ac2a70.png)

代码实现：

```
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    // acquire() 得到
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // release() 释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
```

**原理：**

`semaphore.acquire()`获得，假设如果已经满了，等待，等待被释放为止！

`semaphore.release()`释放，会将当前的信号量释放+1，然后唤醒等待的线程！

作用：多个共享资源互斥的使用！并发限流，控制最大的线程数！

## 读写锁ReadWriteLock

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy161f21bf-38e4-47c3-bf85-3764a953559e.png)

代码测试：

 * ```
    /**
    
     * ReadWriteLock
     * 读-读，可以共存！
     * 写-写，不能共存！
     * 读-写，不能共存！
        */
       public class ReadWriteLockDemo {
       public static void main(String[] args) {
           MyThread myThread = new MyThread();
           // 写入
           for (int i = 1; i <=5; i++) {
               final int temp =i;
               new Thread(()->{
                   myThread.put(temp+"",temp+"");
               },String.valueOf(i)).start();
           }
           // 读取
           for (int i = 1; i <=5; i++) {
               final int temp =i;
               new Thread(()->{
                   myThread.get(temp+"");
               },String.valueOf(i)).start();
           }
       }
       }
       class MyThread{
       private volatile Map<String ,Object> map =new HashMap<>();
       // 读写锁，跟价细粒度的控制
       private ReentrantReadWriteLock readWriteLock= new ReentrantReadWriteLock();
       public void put(String key,String value){
           readWriteLock.writeLock().lock();
           try {
               System.out.println(key+"写入"+value);
               map.put(key,value);
               System.out.println(key+"写入完成");
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               readWriteLock.writeLock().unlock();
           }
       }
       public void get(String key){
           readWriteLock.readLock().lock();
           try {
               System.out.println(key+"读取");
               map.get(key);
               System.out.println(key+"读取完成");
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               readWriteLock.readLock().unlock();
           }
       }
       }
    ```

    

## 阻塞队列

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy39e6d380-bdc4-497f-874d-31464fcac126.png)

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy9ac57da7-52cc-4f7d-a290-795f90768bab.png)

### BlockingQueue

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudyd881bbf6-4d45-4d7b-b6a6-8103544ff2a7.png)

什么情况下我们会使用阻塞队列？`多线程和线程池中`

**学会使用队列**

添加、删除

**四组API**

| 方式     | 抛出异常 | 有返回值`不抛出异常` | 阻塞等待 | 超时等待  |
| -------- | -------- | -------------------- | -------- | --------- |
| 添加元素 | add      | offer                | put      | offer(,,) |
| 移除元素 | remove   | poll                 | take     | poll(,)   |
| 查找队首 | element  | peek                 | -        | -         |

 * ```
 /**
 
     * 抛出异常
       */
       public static void test1(){
       BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);// 队列的大小  3
       System.out.println(blockingQueue.add("a"));
       System.out.println(blockingQueue.add("b"));
       System.out.println(blockingQueue.add("c"));
       // IllegalStateException: Queue full 队满异常！
       // System.out.println(blockingQueue.add("d"));
       System.out.println(blockingQueue.remove());
       System.out.println(blockingQueue.remove());
    System.out.println(blockingQueue.remove());
    // NoSuchElementException 空队列
    // System.out.println(blockingQueue.remove());
    }
 ```
 
 

 * ```
 /**
 
     * 有返回值
       */
       public static void test2(){
       // 队列的大小
       BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
       System.out.println(blockingQueue.offer("a"));
       System.out.println(blockingQueue.offer("b"));
       System.out.println(blockingQueue.offer("c"));
       System.out.println(blockingQueue.offer("d")); //false  不抛出异常
       System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());  // null  不跑出异常
    }
 ```
 
 

 * ```
 /**
 
     * 阻塞等待
       */
       public static void test3() throws InterruptedException {
       ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
       blockingQueue.put("a");
       blockingQueue.put("b");
       blockingQueue.put("c");
       // blockingQueue.put("d");  // 队列没有位置了，一直等待
       System.out.println(blockingQueue.take());
    System.out.println(blockingQueue.take());
    System.out.println(blockingQueue.take());
    // System.out.println(blockingQueue.take());  // 没有元素了，一直等待
    }
 ```
 
 

 * ```
 /**
 
     * 超时等待
       */
       public static void test4() throws InterruptedException {
       // 队列的大小
       BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
       System.out.println(blockingQueue.offer("a"));
       System.out.println(blockingQueue.offer("b"));
       System.out.println(blockingQueue.offer("c"));
       System.out.println(blockingQueue.offer("d",2, TimeUnit.SECONDS));  //等待超过2秒就退出
       System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));  // 等待超过2秒就退出
    }
 ```
 
 

> SynchronousQueue

没有容量，进去一个元素后必须等待取出来之后，才能再往里面放一个元素！

put、take

 * ```
 /**
 
     * 同步队列
     * 和其他BlockingQueue不一样，SynchronousQueue不存储元素
     * put了一个元素，必须take，否则不能继续put
       */
       public class SynchronousQueueDemo {
       public static void main(String[] args) {
           SynchronousQueue<Object> queue = new SynchronousQueue<>(); //同步队列
           new Thread(()->{
               try {
                   System.out.println(Thread.currentThread().getName()+" put 1");
                   queue.put("1");
                   System.out.println(Thread.currentThread().getName()+" put 2");
                   queue.put("2");
                   System.out.println(Thread.currentThread().getName()+" put 3");
                   queue.put("3");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           },"T1").start();
           new Thread(()->{
               try {
                   TimeUnit.SECONDS.sleep(2);
                   System.out.println(Thread.currentThread().getName()+"=>"+ queue.take());
                   TimeUnit.SECONDS.sleep(2);
                   System.out.println(Thread.currentThread().getName()+"=>"+ queue.take());
                   TimeUnit.SECONDS.sleep(2);
                   System.out.println(Thread.currentThread().getName()+"=>"+ queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();
    }
    }
 ```
 
 

## 线程池（重点）

> 池化技术

程序的运行，本质：占用系统的资源！**》优化资源的使用= =》池化技术

线程池、连接池、内存池、对象池。。。`创建、销毁。十分浪费资源`

池化技术：事先准备好一些资源，有人要用，就来我这里拿，用完之后还给我。

**线程池的好处：**

1. 降低资源的消耗
2. 提高响应的速度
3. 方便管理

**线程复用、可以控制最大并发数、管理线程**

### 线程池: 三大方法

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudyfa0689bb-5110-4459-ac81-668f8ae03b08.png)

```
// Executors：工具类，3大方法
public class Demo01 {
    public static void main(String[] args) {
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();// 单个线程
        ExecutorService threadPool =Executors.newFixedThreadPool(5);// 创建一个固定的线程池的大小
        // Executors.newCachedThreadPool();// 可伸缩的，遇强则强，遇弱则弱
        try {
            for (int i = 1; i < 11; i++) {
                // 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池使用完要记得关闭
            threadPool.shutdown();
        }
    }
}
```

### 7大参数

源码分析：

```
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}
public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
}
```

**本质：ThreadPoolExecutor()**

```
public ThreadPoolExecutor(int corePoolSize, // 核心线程池大小
                          int maximumPoolSize, // 最大核心线程池大小
                          long keepAliveTime, // 超时了没有人调用就会释放
                          TimeUnit unit, // 超时单位
                          BlockingQueue<Runnable> workQueue,  // 阻塞队列
                          ThreadFactory threadFactory,  // 线程工厂，创建线程的，一般不用动
                          RejectedExecutionHandler handler) { //拒绝策略
    if (corePoolSize < 0 ||
        maximumPoolSize <= 0 ||
        maximumPoolSize < corePoolSize ||
        keepAliveTime < 0)
        throw new IllegalArgumentException();
    if (workQueue ** null || threadFactory ** null || handler ** null)
        throw new NullPointerException();
    this.acc = System.getSecurityManager() ** null ?
            null :
            AccessController.getContext();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;
}
```

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy3067ec02-9776-41ef-b359-7484254019fb.png)

### 手动创建线程池

```
public class Demo02 {
    public static void main(String[] args) {
        // 自定义线程池！工作中用ThreadPoolExecutor
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()//银行满了，还有人要进来，处理这个人，抛出异常 默认
        );
        try {
            // 最大承载： Deque + max
            // RejectedExecutionException超过会抛出这个异常
            for (int i = 1; i < 11; i++) {
                // 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池使用完要记得关闭
            threadPool.shutdown();
        }
    }
}
```

### 4种拒绝策略

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy0e70910c-ce28-4d13-93ed-2a74ac8dd7a0.png)

```
/** * 四种拒绝策略 *  new ThreadPoolExecutor.AbortPolicy()//银行满了，还有人要进来，处理这个人，抛出异常 *  new ThreadPoolExecutor.CallerRunsPolicy()// 哪来的去哪里 *  new ThreadPoolExecutor.DiscardPolicy()// 队列满了，丢掉任务，不会抛出异常！ *  new ThreadPoolExecutor.DiscardOldestPolicy()// 队列满了，尝试和最早执行的线程竞争，失败则丢弃任务，不会抛出异常！ */
```

> 小结和扩展

最大的线程如何去设置！

了解：IO密集型、CPU密集型**》调优

1. IO密集型，判断系统中有多少耗IO的线程，一般设置为2倍
2. CPU密集型，几核就是几，可以保持CPU的效率最高！

**Runtime.getRuntime().availableProcessors()**

## 四大函数式接口（必须掌握）

新时代程序员：lambda表达式、链式编程、函数式接口、Stream流式计算

> 函数式接口：只有一个方法的接口

@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
// 超级多@FunctionalInterface
// 简化编程模型，在新版本的框架底层大量应用！
// foreach(消费者的函数式接口)

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy497cc1d2-54e6-4d06-8c75-79fe59907a89.png)

**代码测试：**

> Function函数式接口

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudyb87522a8-53c8-41c3-bbfa-7b56b832fa7f.png)

 * ```
 package edu.hut.function;
 import java.util.function.Function;
    /**
    
     * @author DennngW
     * @date 2021-02-02
       */
       /**
     * Function,函数型接口，有一个输入参数，有一个输出
     * 只要是函数型接口都能用lambda表达式简化
       */
       public class FunctionDemo {
       public static void main(String[] args) {
        // Function<String, Integer> function = new Function<String, Integer>() {
        //
        //     @Override
        //     public Integer apply(String str) {
        //         return 123;
        //     }
        // };
        Function<String, Integer> function=(str)->{return 123;};
        System.out.println(function.apply("123"));
    }
    }
 ```
 
  * ```
  package edu.hut.function;
  import java.util.function.Predicate;
     /**
     
      * @author DennngW
      * @date 2021-02-02
        */
        /**
      * 断定型接口，有一个输入参数，返回值只能是布尔值！
        */
        public class PredicateDemo {
        public static void main(String[] args) {
            // 判断字符串是否为空
         // Predicate<String> predicate = new Predicate<String>() {
         //     @Override
         //     public boolean test(String s) {
         //         return s.isEmpty();
         //     }
         // };
         Predicate<String> predicate =  (s)->{return s.isEmpty();};
         System.out.println(predicate.test(""));
     }
     }
  ```
  
  

> Consumer：消费型接口

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudye3b5fa04-b3e1-4216-a160-7a45ed4a5d2e.png)

 * ```
 package edu.hut.function;
 import java.util.function.Consumer;
    /**
    
     * @author DennngW
     * @date 2021-02-02
       */
       public class ConsumerDemo {
       public static void main(String[] args) {
           // Consumer消费型接口，只有输入，没有返回值
           // Consumer<String> consumer = new Consumer<String>() {
           //     @Override
           //     public void accept(String s) {
        //         System.out.println(s);
        //     }
        // };
        Consumer<String> consumer = (s)->{System.out.println(s);};
        System.out.println(consumer);
    }
    }
 ```
 
 

> Supplier：供给型接口

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy3dd9a253-bd56-4800-83da-de6213518355.png)

 * ```
 package edu.hut.function;
 import java.util.function.Supplier;
    /**
    
     * @author DennngW
     * @date 2021-02-02
       */
       public class SupplierDemo {
       public static void main(String[] args) {
           // Supplier,没有参数，只有返回值
           // Supplier<String> supplier = new Supplier<String>() {
           //     @Override
           //     public String get() {
        //         return "123";
        //     }
        // };
        Supplier<String> supplier = ()->{return "123";};
        System.out.println(supplier.get());
    }
    }
 ```
 
 

## Stream流式计算

> 什么是流式计算？

大数据：存储 + 计算

集合、MySQL本质就是存储东西的；

计算都应该交给流来操作！

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy0d1405de-e9ef-4405-9cb5-f0d1062001a6.png)

 * ```
 package edu.hut.function;
 /**
    
     * @author DennngW
     * @date 2021-02-03
       */
       import java.util.Arrays;
       import java.util.List;
       /**
     * 题目要求，一分钟完成此题，只能用一行代码实现！
     * 现在有5个用户！筛选：
     * 1、ID必须是偶数
     * 2、年龄必须大于23岁
     * 3、用户名转为大写字母
     * 4、用户名字母倒序排序
     * 5、只输出一个用户！
       */
       public class StreamDemo {
       public static void main(String[] args) {
           User a = new User("a", 1, 21);
        User b = new User("b", 2, 22);
        User c = new User("c", 3, 23);
        User d = new User("d", 4, 24);
        User e = new User("e", 5, 25);
        // 集合就是存储
        List<User> userList = Arrays.asList(a, b, c, d, e);
        // 计算交给Stream流
        // lambda表达式、链式编程、函数式接口、Stream流式计算
        userList.stream()
                .filter(u->{return u.getId()%2**0;})
                .filter(u->{return u.getAge()>23;})
                .map(u->{return u.getName().toUpperCase();})
                .sorted((u1,u2)->{return u2.compareTo(u1);})
                .limit(1)
                .forEach(System.out::println);
    }
    }
 ```
 
 

## ForkJoin

> 什么是ForkJoin

ForkJoin在jdk1.7之后出现，主要用于并行执行任务！提高效率，大数据量！

大数据：Map Reduce（把大任务拆成小任务）

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy3c089312-bce3-4796-9e9d-387aff89b847.png)

> ForkJoin 特点：工作窃取

这里面维护的都是双端队列

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudyac8c7c60-58a7-4f47-a57f-324e8745125f.png)

> forkjoin

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudye2334042-e789-4d03-8856-66b02df845ce.png)

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudye9581b47-8bc4-4e2f-a170-22fa5354609c.png)

 * ```
 package edu.hut.forkjoin;
 /**
    
     * @author DennngW
     * @date 2021-02-03
       */
       import java.util.concurrent.RecursiveTask;
       /**
     * 求和计算的任务！
     * 3000 6000（ForkJoin） 9000（Stream并行流）
     * 如何使用ForkJoin
     * 1、forkjoinPool 通过它来执行
     * 2、计算任务 forkjoinPool.execute（ForkJoinTask task）
     * 3、计算类要继承 ForkJoinTask
       */
       public class ForkJoinDemo extends RecursiveTask<Long> {
       private Long start; // 1
       private Long end; // 100000000000
       //临界值
       private Long temp = 10000L;
       public ForkJoinDemo(Long start,Long end){
           this.start=start;
           this.end=end;
       }
       // 计算方法
       @Override
       protected Long compute() {
           if ((end-start)<temp){
               Long sum = 0L;
            for (Long i = start; i <=end; i++) {
                sum +=i;
            }
            return sum;
        }else {
            //forkjoin 递归
            long middle = (start + end) /2; //中间值
            ForkJoinDemo task1=new ForkJoinDemo(start,middle);
            task1.fork();// 拆分任务，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle+1,end);
            task2.fork();// 拆任务，把任务压入线程队列
            return task1.join()+task2.join();
        }
    }
    }
 ```
 
 

测试：

 * ```
 package edu.hut.forkjoin;
 import java.util.concurrent.ExecutionException;
    import java.util.concurrent.ForkJoinPool;
    import java.util.concurrent.ForkJoinTask;
    import java.util.stream.LongStream;
    /**
    
     * @author DennngW
     * @date 2021-02-03
       */
       public class Test {
       public static void main(String[] args) throws ExecutionException, InterruptedException {
           // test1();  // sum=500000000500000000 执行时间：6869
           // test2(); // sum=500000000500000000 执行时间：4200
           test3();  // sum=500000000500000000 执行时间：311
       }
       // 3000程序员
       public static void test1(){
           Long sum=0L;
           Long start=System.currentTimeMillis();
           for (Long i = 1L; i <= 10_0000_0000L; i++) {
               sum+=i;
           }
           Long end=System.currentTimeMillis();
           System.out.println("sum="+sum+" 执行时间："+(end-start));
       }
       // 6000 会使用forkjoin
       public static void test2() throws ExecutionException, InterruptedException {
           Long start=System.currentTimeMillis();
           ForkJoinPool forkJoinPool = new ForkJoinPool();
           ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
           ForkJoinTask<Long> submit = forkJoinPool.submit(task);// 提交任务
           Long sum = submit.get();
           Long end=System.currentTimeMillis();
           System.out.println("sum="+sum+" 执行时间："+(end-start));
    }
    // 9000 使用流式计算
    public static void test3(){
        Long start=System.currentTimeMillis();
        // Stream并行流  (]
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        Long end=System.currentTimeMillis();
        System.out.println("sum="+sum+" 执行时间："+(end-start));
    }
    }
 ```
 
 ## 异步回调
 
 > Future 设计的初衷： 对将来的某个事件的结果进行建模
 
 ![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudycd4c0c73-ed08-4738-b378-f4ff6bb212ed.png)

 * ```
 package edu.hut.future;
 import java.util.concurrent.CompletableFuture;
    import java.util.concurrent.ExecutionException;
    import java.util.concurrent.TimeUnit;
    /**
    
     * @author DennngW
     * @date 2021-02-03
       */
       public class FutureDemo {
       public static void main(String[] args) throws ExecutionException, InterruptedException {
           // 没有返回值的runAsync 异步回调
           // CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
           //     try {
           //         TimeUnit.SECONDS.sleep(2);
           //     } catch (InterruptedException e) {
           //         e.printStackTrace();
           //     }
           //     System.out.println(Thread.currentThread().getName() + "runAsync=>Void");
           // });
           //
           // System.out.println("main");
           //
           // voidCompletableFuture.get(); // 获取阻塞执行结果
           /**
            * 有返回值的supplyAsync 异步回调
            * ajax，成功(200)和失败(404/500)的回调
            * 返回的是错误信息
            */
           CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
               System.out.println(Thread.currentThread().getName() + "supplyAsync=>Integer");
               int i = 10 / 0;
               return 200;
           });
        System.out.println(integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("正常返回的结果t=" + t); //正常的返回结果
            System.out.println("错误信息u=" + u); // 错误信息，
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 500; //可以获取到错误的返回结果
        }).get());
    }
    }
 ```
 
 
      * 有返回值的supplyAsync 异步回调
           * ajax，成功(200)和失败(404/500)的回调
           * 返回的是错误信息
                */
             CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
         System.out.println(Thread.currentThread().getName() + "supplyAsync=>Integer");
         int i = 10 / 0;
         return 200;
             });
             System.out.println(integerCompletableFuture.whenComplete((t, u) -> {
         System.out.println("正常返回的结果t=" + t); //正常的返回结果
         System.out.println("错误信息u=" + u); // 错误信息，
             }).exceptionally((e) -> {
         System.out.println(e.getMessage());
         return 500; //可以获取到错误的返回结果
             }).get());
 }
 }

## JMM

> 请你谈谈你对Volatile的理解

Volatile是Java虚拟机提供**轻量级的同步机制**

1. 保证可见性
2. **不保证原子性**
3. 禁止指令重排

> 什么是JMM

JMM ： Java内存模型，不存在的东西，概念！约定！

**关于JMM的一些同步的约定：**

1、线程解锁前，必须把共享变量**立刻**刷回主存。

2、线程枷锁前，必须读取主存中的最新值到工作内存中！

3、加锁和解锁是同一把锁

线程 **工作内存**、**主内存**

8种操作

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudye725f8d1-d5f3-4921-8234-31d60a21a1da.png)

关于主内存与工作内存之间的交互协议，即一个变量如何从主内存拷贝到工作内存。如何从工作内存同步到主内存中的实现细节。java内存模型定义了8种操作来完成。**这8种操作每一种都是原子操作。8种操作如下：**

- lock(锁定)：作用于主内存，它把一个变量标记为一条线程独占状态；
- read(读取)：作用于主内存，它把变量值从主内存传送到线程的工作内存中，以便随后的load动作使用；
- load(载入)：作用于工作内存，它把read操作的值放入工作内存中的变量副本中；
- use(使用)：作用于工作内存，它把工作内存中的值传递给执行引擎，每当虚拟机遇到一个需要使用这个变量的指令时候，将会执行这个动作；
- assign(赋值)：作用于工作内存，它把从执行引擎获取的值赋值给工作内存中的变量，每当虚拟机遇到一个给变量赋值的指令时候，执行该操作；
- store(存储)：作用于工作内存，它把工作内存中的一个变量传送给主内存中，以备随后的write操作使用；
- write(写入)：作用于主内存，它把store传送值放到主内存中的变量中。
- unlock(解锁)：作用于主内存，它将一个处于锁定状态的变量释放出来，释放后的变量才能够被其他线程锁定；

**Java内存模型还规定了执行上述8种基本操作时必须满足如下规则:**

（1）不允许read和load、store和write操作之一单独出现（即不允许一个变量从主存读取了但是工作内存不接受，或者从工作内存发起会写了但是主存不接受的情况），以上两个操作必须按顺序执行，但没有保证必须连续执行，也就是说，read与load之间、store与write之间是可插入其他指令的。

（2）不允许一个线程丢弃它的最近的assign操作，即变量在工作内存中改变了之后必须把该变化同步回主内存。

（3）不允许一个线程无原因地（没有发生过任何assign操作）把数据从线程的工作内存同步回主内存中。

（4）一个新的变量只能从主内存中“诞生”，不允许在工作内存中直接使用一个未被初始化（load或assign）的变量，换句话说就是对一个变量实施use和store操作之前，必须先执行过了assign和load操作。

（5）一个变量在同一个时刻只允许一条线程对其执行lock操作，但lock操作可以被同一个条线程重复执行多次，多次执行lock后，只有执行相同次数的unlock操作，变量才会被解锁。

（6）如果对一个变量执行lock操作，将会清空工作内存中此变量的值，在执行引擎使用这个变量前，需要重新执行load或assign操作初始化变量的值。

（7）如果一个变量实现没有被lock操作锁定，则不允许对它执行unlock操作，也不允许去unlock一个被其他线程锁定的变量。

（8）对一个变量执行unlock操作之前，必须先把此变量同步回主内存（执行store和write操作）。

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudyc8464436-4999-40a4-8657-e10232638084.png)

**问题：**程序不知道主存的值已经被修改过了`需要线程A知道主存中的值发生了变化`

## Volatile

> 保证可见性

 * ```
 package edu.hut;
 import java.util.concurrent.TimeUnit;
    /**
    
     * @author DennngW
     * @date 2021-02-03
       */
       public class VolatileDemo {
       // 不加volatile 程序就会死循环！
       // 加volatile，可以保证可见性
       private volatile static int num = 0;
       public static void main(String[] args) {
           new Thread(()->{
               while (num**0){
               }
           }).start();
           try {
               TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);
    }
    }
 ```
 
 

> 不保证原子性

原子性：不可分割

线程A在执行任务的时候，不能被打扰，也不能被分割，要么同时成功，要么同时失败。

 * ```
 package edu.hut.JMM;
 /**
    
     * @author DennngW
     * @date 2021-02-03
       */
       public class VDemo2 {
       // volatile 不保证原子性
       private volatile static int num = 0;
       public static void add(){
           num++; // 不是原子性操作
       }
       public static void main(String[] args) {
           for (int i = 1; i < 20; i++) {
               new Thread(()->{
                   for (int j = 0; j < 1000; j++) {
                       add();
                   }
               }).start();
           }
           // 判断上面线程全部跑完
        while (Thread.activeCount()>2){// main gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+num);
    }
    }
 ```
 
 

**问题本质：num++不是原子性操作**

1. 获得这个值
2. +1
3. 写回这个值

**如果不加lock和synchronized，怎样保证原子性**

使用**原子类**，解决原子性问题

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy08ae42cb-6588-4d84-966f-cfb1c76f84bc.png)

 * ```
 package edu.hut.JMM;
 import java.util.concurrent.atomic.AtomicInteger;
    /**
    
     * @author DennngW
     * @date 2021-02-03
       */
       public class VDemo2 {
       // volatile 不保证原子性
       // 原子类的 Integer
       private volatile static AtomicInteger num = new AtomicInteger(0);
       public static void add(){
           // num++;  //不是一个原子性操作
           num.getAndIncrement();//AtomicInteger + 1 方法，底层原理是CAS
       }
       public static void main(String[] args) {
           for (int i = 1; i <= 20; i++) {
               new Thread(()->{
                   for (int j = 0; j < 1000; j++) {
                       add();
                   }
               }).start();
           }
        // 判断上面线程全部跑完
        while (Thread.activeCount()>2){// main gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+num);
    }
    }
 ```
 
 

**这些类的底层都直接和操作系统挂钩（native）！在内存中修改值！Unsafe类是一个很特殊的存在**

这些原子类的效率比lock和synchronized高！

> 指令重排

什么是指令重排：**你写的程序，计算机并不是按照你写的那样去执行的。**

源代码=>编译器优化的重排=>指令并行也可能会重排=>内存系统也会重排=>执行

***\*处理器在进行指令重排的时候会考虑数据之间的依赖性！\****

```
int x = 1; //1
int y = 2; //2
x = x + 5; //3
y = x * x; //4
我们所期望的是，1234  但是实际执行的时候可能会变成 2134、1324
但是不可能是 4123！
```

可能造成影响的结果： a b x y 这四个默认值都是0 ；

| 线程A | 线程B |
| ----- | ----- |
| x=a   | y=b   |
| b=1   | a=2   |

正常的结果： x=0；y=0；

| 线程A | 线程B |
| ----- | ----- |
| b=1   | a=2   |
| x=a   | y=b   |

指令重排导致的诡异结果：x=2;y=1；

```
可能写1000W次都不会发生，但是理论上是存在的
```

volatile可以避免指令重排：

内存屏障。CPU指令。作用：

1、保证特定的操作的执行顺序！

2、可以保证某些变量的内存可见性（利用这些特性volatile实现了可见性）

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy07f57850-1234-4767-8877-6c629a37a293.png)

volatile是可以保持可见性。不能保证原子性，由于内存屏障，可以保证避免指令重排的现象产生！

## 彻底玩转单例模式

> 懒汉式

 * ```
 package edu.hut.single;
 /**
 
     * @author DennngW
     * @date 2021-02-04
       */
       // 饿汉式单例
       public class Hungry {
       // 可能会浪费空间
       private Hungry(){
    }
    private final static Hungry HUNGRY= new Hungry();
    public static Hungry getInstance(){
        return HUNGRY;
    }
    }
 ```
 
 

DCL饿汉式

 *  ```
 package edu.hut.single;
     import java.lang.reflect.Constructor;
     import java.lang.reflect.InvocationTargetException;
     /**
     
     * @author DennngW
     * @date 2021-02-04
       */
       // 懒汉式单例
       public class Lazy {
       private Lazy(){
           System.out.println(Thread.currentThread().getName());
       }
       private volatile static Lazy lazy;
       public static Lazy getInstance(){
        if (lazy**null){
            synchronized (Lazy.class){
                while (lazy**null){
                    lazy=new Lazy(); // 不是一个原子性操作
    /**
  * 1.分配内存空间
  * 2.执行构造方法
  * 3.把这个对象指向这个空间
    *
  * 希望顺序：123
  * 可能重排：132 A线程此时已经分配空间但没有完成构造
  * B 由于A分配了内存空间，所以判断错误直接return
  * 所以需要volatile关键字防止指令重排；
    */
               }
           }
       }
       return lazy;
    }
    // 反射破解单例！
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Lazy instance = Lazy.getInstance();
        Constructor<Lazy> declaredConstructor = Lazy.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Lazy newInstance = declaredConstructor.newInstance();
        System.out.println(instance);
        System.out.println(newInstance);
    }
    }
 ```
 
 

静态内部类

 * ```
 package edu.hut.single;
 /**
 
     * @author DennngW
     * @date 2021-02-04
       */
       // 静态内部类
       public class Holder {
       private Holder(){
           System.out.println("1");
       }
       public static Holder getInstance(){
        return InnerClass.HOLDER;
    }
    private static class InnerClass{
        private static final Holder HOLDER=new Holder();
    }
    }
 ```
 
 

由于反射的存在，上述单例模式都能被破解！！！

**But**

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy5eb68038-08fd-40c6-825e-8a721566ad5f.png)

> 枚举单例

 * ```
 package edu.hut.single;
 /**
 
     * @author DennngW
     * @date 2021-02-04
       */
       // enum:枚举类，本身也是一个Class类
    public enum EnumSingle {
    INSTANCE;
    public EnumSingle getInstance(){
        return INSTANCE;
    }
    }
 ```
 
 

## 深入理解CAS

> 什么是CAS

大厂你必须要深入研究底层！有所突破！修内功，**操作系统，计算机网络原理**

 * ```
 package edu.hut.cas;
 import java.util.concurrent.atomic.AtomicInteger;
    /**
    
     * @author DennngW
     * @date 2021-02-04
       */
       public class CASDemo {
       public static void main(String[] args) {
           // CAS compareAndSet : 比较并交换！
           AtomicInteger atomicInteger = new AtomicInteger(2020);
           // 期望、更新
           // public final boolean compareAndSet(int expect, int update)
           // 如果我期望的值达到了，那么就更新，否则，就不更新，CAS是CPU的并发原语！
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
    }
    }
 ```
 
 

> Unsafe类

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudy23a0d299-0287-4b27-a299-80dff659ee50.png)

------

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudya9106190-6c28-4f03-8c47-ce803ed9a524.png)

如果 var1和var2对应的内存中的值还是var5，那么var5+var4

------

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudyc2f29881-09d3-41c6-bdb1-7818ba7f752d.png)

CAS：比较当前工作内存中的值和主存中的值，如果这个值是期望的，那么则执行操作！如果不是就一直循环！

**缺点:**

1. 循环会耗时
2. 一次性只能保证一个共享变量的原子性
3. ABA问题

> CAS: ABA问题（狸猫换太子）

## 原子引用

> 解决ABA问题，引入原子引用！对应思想：乐观锁！

带版本号的原子操作！

 * ```
 package edu.hut.cas;
 import java.util.concurrent.TimeUnit;
    import java.util.concurrent.atomic.AtomicInteger;
    import java.util.concurrent.atomic.AtomicStampedReference;
    /**
    
     * @author DennngW
     * @date 2021-02-04
       */
       public class CASDemo {
       // AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题
       // 在正常业务操作中，这里面比较的都是一个个对象
       static AtomicStampedReference<Integer> atomicStampedReference= new AtomicStampedReference<Integer>(1,1);
       public static void main(String[] args) {
           // CAS compareAndSet : 比较并交换！
           new Thread(()->{
               int stamp = atomicStampedReference.getStamp();//获得版本号
               System.out.println("a1=》"+stamp);
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(atomicStampedReference.compareAndSet(1,
                       2,
                       atomicStampedReference.getStamp(),
                       atomicStampedReference.getStamp() + 1));
               System.out.println("a2=>"+atomicStampedReference.getStamp());
               System.out.println(atomicStampedReference.compareAndSet(2,
                       1,
                       atomicStampedReference.getStamp(),
                       atomicStampedReference.getStamp() + 1));
               System.out.println("a2=>"+atomicStampedReference.getStamp());
           }).start();
           // 乐观锁的原理相同！
           new Thread(()->{
               int stamp = atomicStampedReference.getStamp();//获得把那本号
               System.out.println("b1=>"+stamp);
               try {
                   TimeUnit.SECONDS.sleep(2);
               } catch (InterruptedException e) {
                   e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1,
                    6,
                    stamp,
                    stamp + 1));
            System.out.println("b2=>"+atomicStampedReference.getStamp());
        }).start();
    }
    }
 ```
 
 

**注意**

Integer使用了对象缓存机制，默认范围是-128~127，推荐使用静态工厂方法valuOf获取对象实例，而不是new，因为valueOf使用缓存，而new一定会创建新的对象分配新的内存空间；

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/07/kuangstudycc264668-8fb4-4d72-bf85-3f48f294391c.png)

## 各种锁的理解

### 公平锁、非公平锁

公平锁：非常公平，不能够插队，必须先来后到！

非公平锁：非常不公平，可以插队（默认都是非公平）

```
public ReentrantLock() {
    sync = new NonfairSync();
}
public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
```

