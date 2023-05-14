# 用户交互Scanner

1. ```
   package scanner;
   import java.util.Scanner;
   public class Demo01 {
       public static void main(String[] args) {
           //创建一个扫描器对象，用于接收键盘数据
           Scanner scanner = new Scanner(System.in);
           System.out.println("使用next方式接收：");
           //判断用户有没有输入字符串
           if(scanner.hasNext()){
               //使用next放式接收
               String str = scanner.next();
               System.out.println("输出的内容为："+str);
           }
           //凡是属于IO流的类如果不关闭会一直占用资源，要养成用好就关的好习惯
           scanner.close();
       }
   }
   ```

   ```
   package scanner;
   import java.util.Scanner;
   public class Demo02 {
       public static void main(String[] args) {
           //创建一个扫描器对象，用于接收键盘数据
           Scanner scanner = new Scanner(System.in);
           System.out.println("使用nextLine方式接收：");
           //判断用户有没有输入字符串
           if(scanner.hasNextLine()){
               //使用next放式接收
               String str = scanner.nextLine();
               System.out.println("输出的内容为："+str);
           }
           //凡是属于IO流的类如果不关闭会一直占用资源，要养成用好就关的好习惯
           scanner.close();
       }
   }
   ```

   

```
package scanner;
import java.util.Scanner;
public class Demo03 {
    public static void main(String[] args) {
        //创建一个扫描器对象，用于接收键盘数据
        Scanner scanner = new Scanner(System.in);
        System.out.println("使用nextLine方式接收：");
        String str = scanner.nextLine();
        System.out.println("输出的内容为："+str);
        //凡是属于IO流的类如果不关闭会一直占用资源，要养成用好就关的好习惯
        scanner.close();
    }
}
```

通过Scanner类的next()与nextLine()方法获取输入的字符串，在读取之前我们一般需要使用hasNext()与hasNextLine()判断是否还有输入的数据。

```
package scanner;
import java.util.Scanner;
public class Demo04 {
    public static void main(String[] args) {
        //创建一个扫描器对象，用于接收键盘数据
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        float f = 0.0f;
        System.out.println("Please enter the integral:");
        if(scanner.hasNextInt()){
            i = scanner.nextInt();
            System.out.println("Integral:"+i);
        }else{
            System.out.println("It's not Integral");
        }
        System.out.println("Please enter the float:");
        if(scanner.hasNextFloat()){
            f = scanner.nextFloat();
            System.out.println("Float:"+f);
        }else{
            System.out.println("It is not a float");
        }
        //凡是属于IO流的类如果不关闭会一直占用资源，要养成用好就关的好习惯
        scanner.close();
    }
}
```

```
package scanner;
import java.util.Scanner;
public class Demo05 {
    public static void main(String[] args) {
        //我们可以输入多个数字，并求总和和平均数，每一个数字用回车确认，通过输入非数字来结束输入并输出执行结果
        Scanner scanner = new Scanner(System.in);
        //和
        double sum = 0;
        //计算输入了多少数字
        int m = 0;
        //通过循环判断是否还有输入
        while(scanner.hasNextDouble()){
            double x = scanner.nextDouble();
            m++;
            sum = sum + x;
        }
        System.out.println(m+"个数的和为"+sum);
        System.out.println(m+"个数的平均数为"+(sum/m));
        scanner.close();
    }
}
```

