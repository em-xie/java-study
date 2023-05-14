# Java 常用IO流操作解释

# 1、IO流基本概念

## 1.1基本概念

 IO：Java对数据的操作是通过流的方式，IO流用来处理设备之间的数据传输，上传文件和下载文件，Java用于操作流的对象都在IO包中

# 2、IO流的分类

## 2.1[字节流](https://so.csdn.net/so/search?q=字节流&spm=1001.2101.3001.7020)

![img](https://img-blog.csdnimg.cn/img_convert/7cb7a6bfc4c18f9ac1c9d25ef01cf0ee.png)

2.2字符流

![img](https://img-blog.csdnimg.cn/img_convert/6a5ff7dea088a82e3a7ad9c44f8995a6.png)


2.3该如何选择哪种IO流？
 如果数据通过Window自带的记事本软件打开，我们还可以读懂里面的内容，就使用字符流，否则使用字节流。 如果你不知道该使用哪种类型的流,就使用字节流。

3、字节流
3.1字节流基础类
3.1.1 InputStream类
InputStream：字节输入流基类，抽象类是表示字节输入流的所有类的超类。

常用方法：


- [ ] `序号	方法名	解释`
  `1	abstract int read()	从输入流中读取数据的下一个字节`
  `2	int read(byte[] b)	从输入流读取一些字节数，并将它们存储到缓冲区 b`
  `3	int read(byte[] b, int off, int len)	从输入流读取最多 len字节的数据到一个字节数组。`
  `4	void close()	关闭此输入流并释放与该流关联的所有系统资源`
  `3.1.2 OutputStream类`
  `OutputStream：字节输出流基类，抽象类是表示输出字节流的所有类的超类。`
- [ ] `常用方法：`

序号	方法名	解释
1	void write(byte[] b)	将 b.length 个字节从指定的 byte 数组写入此输出流
2	void write(byte[] b, int off, int len)	将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此输出流
3	abstract void write(int b)	将指定的字节写入此输出流
4	void close()	关闭此输出流并释放与此流有关的所有系统资源
5	void flush()	刷新此输出流并强制写出所有缓冲的输出字节
3.2字节文件操作流
3.2.1FileInputStream
FileInputStream：字节文件输入流，从文件系统中的某个文件中获得输入字节，用于读取诸如图像数据之类的原始字节流。

```
package IO;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStream_01 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("D:\\Desktop\\test.txt");
        int by;
        //一次读取一个字节
        while ((by=fis.read())!=-1)
        {
            //因为字符在底层存储的时候就是存储的数值。即字符对应的ASCII码。
            //所以需要强制转换一下类型
            System.out.print((char)by);
        }
        //关闭IO流
        fis.close();

    }

}
```

![img](https://img-blog.csdnimg.cn/img_convert/3fe7fb48aa6f21b6d25e7c9d06969597.png)

```
package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputStream_02 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\Desktop\\test.txt");
        byte[] s1= new byte[1024];
        int len;
        //  一次读取一个字节数组
        while ((len=fileInputStream.read(s1))!=-1)
        {
            System.out.println(new String(s1,0,len));
        }
        fileInputStream.close();
    }
}
```

![image-20201102232430867](https://img-blog.csdnimg.cn/img_convert/0f36dce56543afd7533410c252d8a1ec.png)

注： 一次读取一个字节数组，提高了操作效率,IO流使用完毕一定要关闭。

### 3.2.1FileOutputStream

FileOutputStream：字节文件输出流是用于将数据写入到File，从程序中写入到其他位置。

```
package IO;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
//创建字节流输出对象
//调用字节流写入方法
//释放资源

public class FileOutputStream_01 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("D:\\Desktop\\text.txt");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            int a=scanner.nextInt();
            fos.write(a);
        }
        scanner.close();
        fos.close();

    }

}
```

![image-20201103103150627](https://img-blog.csdnimg.cn/img_convert/357570cda716fa0ecabc22cefb373d88.png)

![image-20201103103303344](https://img-blog.csdnimg.cn/img_convert/4ad621b20b694194abcada63310ae1ca.png)

**注意：**

会发现输入65 66 67数字变成了字符ABC，因为字符在底层存储的时候就是存储的数值。即字符对应的ASCII码。

```
package IO;
//字节流写入的三个方法

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
//write写入的数组为byte类型
public class FileOutputStream_02 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("D:\\Desktop\\test.txt");
        Scanner scanner = new Scanner(System.in);
        byte[] s1=new byte[10];
        for (int i = 0; i < 10; i++) {
            s1[i]=scanner.nextByte();

        }
        fos.write(s1[0]);
        fos.write('\n');
        fos.write(s1);
        fos.write('\n');
        fos.write(s1,1,2);
        fos.write('\n');
        //getBeytes()返回字符串对应的字节流数组
        byte[] bytes = "asdfghjkl".getBytes();
        fos.write(bytes);
        scanner.close();
    }

}
```

![image-20201103103836291](https://img-blog.csdnimg.cn/img_convert/5713bd04cd4699ea89716578ae7bb78e.png)

![image-20201103103930616](https://img-blog.csdnimg.cn/img_convert/826bff0182e51a266f4a2f97978e5628.png)

注：

 输出的目的地文件不存在，则会自动创建，不指定盘符的话，默认创建在项目目录下;输出换行符时不一定是写\r\n或者写\n,因为不同的操作系统、不同文本编辑器对换行符的识别存在差异性。

注意：实验多次会发现写入是重新写的，想追加的话需要多传一个参数。

```
//创建一个向具有指定name的文件中写入数据的输出文件流
//如果第二个参数是true ，则字节将写入文件的末尾而不是开头。
FileOutputStream(String name, boolean append)
```

![image-20201103104046070](https://img-blog.csdnimg.cn/img_convert/fa8aef70532385dfed3311ef4b2c69f4.png)

## 3.3字节缓冲流（高效流）

### 3.3.1BufferedInputStream

BufferedInputStream：字节缓冲输入流，提高了读取效率。

```
 构造方法：
	 // 创建一个 BufferedInputStream并保存其参数，即输入流in，以便将来使用。
	 BufferedInputStream(InputStream in)
	 // 创建具有指定缓冲区大小的 BufferedInputStream并保存其参数，即输入流in以便将来使用
	 BufferedInputStream(InputStream in, int size)
```

```
package IO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedInputStream_01 {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\Desktop\\test.txt"));

        byte[] s1=new byte[1024];
        int len;
        while((len=bis.read(s1))!=-1)
        {
            System.out.print(new String(s1,0,len));
        }
        bis.close();
    }

}
```

![image-20201103104727152](https://img-blog.csdnimg.cn/img_convert/599c973c0978082678f126e8f9ba85cd.png)

### 3.3.2BufferedOutputStream

BufferedOutputStream：字节缓冲输出流，提高了写出效率。

	 构造方法：
	 // 创建一个新的缓冲输出流，以将数据写入指定的底层输出流
	 BufferedOutputStream(OutputStream out)
	 // 创建一个新的缓冲输出流，以将具有指定缓冲区大小的数据写入指定的底层输出流
	 BufferedOutputStream(OutputStream out, int size)
	 
	 常用方法：
	 // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此缓冲的输出流
	 void write(byte[] b, int off, int len)
	 // 将指定的字节写入此缓冲的输出流
	 void write(int b)
	 // 刷新此缓冲的输出流
	 void flush()

```
package IO;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStream_01 {
    public static void main(String[] args) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\Desktop\\test.txt"));
        bos.write("\n".getBytes());
        bos.write("我爱学BufferedOutputStream".getBytes());
        bos.close();
    }
}
```

![image-20201103105347634](https://img-blog.csdnimg.cn/img_convert/622ba686c8ddee55c964238ed7d9a2bd.png)

# 4、字符流

## 4.1字符流基类

### 4.1.1Reader

**常用方法：**

序号	方法	解释
1	int read()	读取单个字符
2	int read(char[] cbuf)	将字符读入数组
3	abstract int read(char[] cbuf, int off, int len)	将字符读入数组的某一部分
4	long skip(long n)	跳过字符
5	abstract void close()	关闭该流并释放与之关联的所有资源

### 4.1.2Writer

**常用方法：**

序号		
1	void write(char[] cbuf)	写入字符数组
2	abstract void write(char[] cbuf, int off, int len)	写入字符数组的某一部分
3	void write(int c)	写入单个字符
4	void write(String str)	写入字符串
5	void write(String str, int off, int len)	写入字符串的某一部分
6	abstract void close()	关闭此流，但要先刷新它
7	abstract void flush()	刷新该流的缓冲4.2字符转换流

### 4.2.1InputStreamReader

InputStreamReader：字节流转字符流，它使用的字符集可以由名称指定或显式给定，否则将接受平台默认的字符集。

序号	方法	解释
1	InputStreamReader(InputStream in)	创建一个使用默认字符集的 InputStreamReader
2	InputStreamReader(InputStream in, Charset cs)	创建一个使用给定字符集的InputStreamReader。
3	InputStreamReader(InputStream in, CharsetDecoder dec)	创建一个使用给定字符集解码器的InputStreamReader。
4	InputStreamReader(InputStream in, String charsetName)	创建一个使用命名字符集的InputStreamReader。
5	String getEncoding()	返回此流使用的字符编码的名称

### 4.2.2OutputStreamWriter

OutputStreamWriter：字节流转字符流。

序号	方法	解释
1	OutputStreamWriter(OutputStream out)	创建一个使用默认字符编码的OutputStreamWriter。
2	OutputStreamWriter(OutputStream out, Charset cs)	创建一个使用给定字符集的OutputStreamWriter。
3	OutputStreamWriter(OutputStream out, CharsetEncoder enc)	创建一个使用给定字符集编码器的OutputStreamWriter。
4	OutputStreamWriter(OutputStream out, String charsetName)	创建一个使用命名字符集的OutputStreamWriter。

```
package IO;

import java.io.*;
/*
OutputStreamWriter(OutputStream out)
创建一个使用默认字符编码的OutputStreamWriter。
 OutputStreamWriter(OutputStream out, Charset cs)
 创建一个使用给定字符集cs的OutputStreamWriter。
 */
//具体编码
public class CharacterStream_01 {
    public static void main(String[] args) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:\\Desktop\\test1.txt"));
       // OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:\\Desktop\\test1.txt"),"GBK");
        osw.write("中国");
        osw.close();
        InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\Desktop\\test1.txt"));
        //InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\Desktop\\test1.txt"),"GBK");
        char[] s1=new char[1024];
        int len;
        while ((len=isr.read(s1))!=-1){
            System.out.println(new String(s1,0,len));
        }
        isr.close();
    }
}
```

![image-20201103113704285](https://img-blog.csdnimg.cn/img_convert/011b5f3d2bef752d525eeec0a5d6f86a.png)

![image-20201103113731504](https://img-blog.csdnimg.cn/img_convert/92d0e140b8c39e757e60ad28847494d9.png)

注意：IDEA默认编码为“UTF-8”，Eclipse默认使用“GBK”编码。

4.3字符缓冲流（高效流）
4.3.1BufferedReader
 BufferedReader：字符缓冲流，从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。

构造方法：

| 序号 | 方法                              | 解释                                           |
| ---- | --------------------------------- | ---------------------------------------------- |
| 1    | BufferedReader(Reader in)         | 创建一个使用默认大小输入缓冲区的缓冲字符输入流 |
| 2    | BufferedReader(Reader in, int sz) | 创建一个使用指定大小输入缓冲区的缓冲字符输入流 |
| 3    | String readLine()                 | 读取一个文本行                                 |

```
package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//readLine()读一行文字。
public class BufferedReader_01 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Desktop\\test.txt"));
        String s;
        while ((s=bufferedReader.readLine())!=null)
        {
            System.out.println(s);
        }
        bufferedReader.close();
    }
}
```

![image-20201103115222008](https://img-blog.csdnimg.cn/img_convert/c2e710005c6bf9ba5850044c2cc26f78.png)

### 4.3.2BufferedWriter

BufferedWriter：字符缓冲流，将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入。

**构造方法：**

| 序号 | 方法                               | 解释                                             |
| ---- | ---------------------------------- | ------------------------------------------------ |
| 1    | BufferedWriter(Writer out)         | 创建一个使用默认大小输出缓冲区的缓冲字符输出流   |
| 2    | BufferedWriter(Writer out, int sz) | 创建一个使用给定大小输出缓冲区的新缓冲字符输出流 |
| 3    | void newLine()                     | 写入一个行分隔符                                 |

```
package IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//字符缓冲流特有的newLine()适应不同系统写一行行分隔符。
public class BufferedWriter_01 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Desktop\\test2.txt",true));
        for (int i = 0; i < 10; i++) {
            bufferedWriter.write("我真的很爱学BufferedReader");
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
```

![image-20201103120019538](https://img-blog.csdnimg.cn/img_convert/b1e39a2725b9a14813a702fabc1ff934.png)