# 五、包机制、JavaDoc

## 1. 包机制

为更好地组织类，Java提供了包机制，用于区别类名的命名空间。

**一般以公司域名的倒置作为包名**。

**为了能够使用某一包内的成员，需要在Java程序里明确导入该包，使用`import`语句实现此功能**。

![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/20/kuangstudyf2288940-ced8-4adf-85f4-03a7d26740ac.png)

1. ```
   1. `package com.Study.SHILIKNG.www; //package关键字指明当前类所在的包名，包就相当于文件夹`
   2. ``
   3. `import javax.xml.crypto.Data;   //为了能够使用某一包内的成员，需要在Java程序里明确导入该包，使用import语句实现此功能`
   4. ``
   5. `//import com.SHILIKNG.www.Demo01    导入com.SHILIKNG.www包内的类Demo01，与当前的类Demo01冲突，因此类名应该命名为不同的名称`
   6. `//import com.SHILIKNG.www.*         *是通配符，该语句表示导入com.SHILIKNG.www包内的所有类`
   7. ``
   8. ``
   9. `public class Demo01 {`
   10. `    public static void main(String[] args) {`
   11. `        Data   //这里的Data是javax.xml.crypto下的成员`
   12. `    }`
   ```

   

## 2. 文档注释及JavaDoc

1. ```
   1. `package com.Study.SHILIKNG.www;`
   2. `import java.io.*;   // 导入java.io包内所有的类`
   3. ``
   4. `/**`
   5. ` * 这个类进行文档注释的演示`
   6. ` * @author  SHILIKNG`
   7. ` * @version test1.0`
   8. ` * @since 1.8`
   9. ` */`
   10. `public class Demo02 {`
   11. ``
   12. `    /**该方法返回数字的平方`
   13. `     * @param num The value to be squared.`
   14. `     * @return num squared.`
   15. `     */`
   16. `    public double square (double num) {`
   17. `        return num * num;`
   18. `    }`
   19. ``
   20. `    /**`
   21. `     * 该方法用于接收一个从用户那里输入的数`
   22. `     * @return The value input as a double.`
   23. `     * @exception IOException On input error.`
   24. `     * @see IOException`
   25. `     */`
   26. `    public double getNumber() throws IOException {`
   27. `        InputStreamReader isr = new InputStreamReader(System.in);`
   28. `        BufferedReader inData = new BufferedReader(isr);`
   29. `        String str;`
   30. `        str = inData.readLine();`
   31. `        return (new Double (str)).doubleValue();`
   32. `    }`
   33. ``
   34. `    /**`
   35. `     * main方法，调用上述平方计算和接受数值方法，显示所接受数的平方计算后的结果`
   36. `     * @param args Unused.`
   37. `     * @exception  IOException on input error.`
   38. `     * @see IOException`
   39. `     */`
   40. `    public static void main(String[] args) throws IOException {`
   41. `        Demo02 ob = new Demo02();`
   42. `        double val;`
   43. `        System.out.println("Enter value to be aquared: ");`
   44. `        val = ob.getNumber();`
   45. `        val = ob.square(val);`
   46. `        System.out.println("Squared value is " + val);`
   47. `    }`
   48. `}`
   49. `-----------------------------`
   50. `运行结果：`
   51. `    Enter value to be aquared: `
   52. `    2`
   53. `    Squared value is 4.0`
   54. `下面利用IDEA对程序中缩写的文档注释生成JavaDoc文档：`
   ```

   

选择菜单Tools->Generate JavaDoc

1. 
   ![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/20/kuangstudy5a27e3ed-f2dc-4ad6-a0d2-0a58b6b5dbee.png)

2. 进行JavaDoc文档生成设置

   `文档信息栏中填写：-encoding UTF-8 -charset UTF-8 -windowtitle "test"`

   `其中：-encoding是java代码编码，设定为UTF-8编码. -charset是对生成文档所用的编码,设定为UTF-8编码.-windowtitle就是对应html的<title>标签`

   ![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/20/kuangstudyde4d8e0c-17c6-4443-add0-334d1e27b291.png)

3. 结果：

   ![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/02/20/kuangstudy527b30f5-2ce8-4c58-809f-b45fecfa69d1.png)

## 3.文档注释中的标签

| **标签**                                       |                        **描述**                        |                           **示例**                           |
| :--------------------------------------------- | :----------------------------------------------------: | :----------------------------------------------------------: |
| [@author](https://github.com/author)           |                    标识一个类的作者                    |       [@author](https://github.com/author) description       |
| [@deprecated](https://github.com/deprecated)   |                 指名一个过期的类或成员                 |   [@deprecated](https://github.com/deprecated) description   |
| {[@docRoot](https://github.com/docRoot)}       |                指明当前文档根目录的路径                |                        Directory Path                        |
| [@exception](https://github.com/exception)     |                  标志一个类抛出的异常                  | [@exception](https://github.com/exception) exception-name explanation |
| {[@inheritDoc](https://github.com/inheritDoc)} |                  从直接父类继承的注释                  |      Inherits a comment from the immediate surperclass.      |
| {[@link](https://github.com/link)}             |               插入一个到另一个主题的链接               |         {[@link](https://github.com/link) name text}         |
| {[@linkplain](https://github.com/linkplain)}   |  插入一个到另一个主题的链接，但是该链接显示纯文本字体  |          Inserts an in-line link to another topic.           |
| [@param](https://github.com/param)             |                   说明一个方法的参数                   | [@param](https://github.com/param) parameter-name explanation |
| [@return](https://github.com/return)           |                     说明返回值类型                     |       [@return](https://github.com/return) explanation       |
| [@see](https://github.com/see)                 |               指定一个到另一个主题的链接               |            [@see](https://github.com/see) anchor             |
| [@serial](https://github.com/serial)           |                   说明一个序列化属性                   |       [@serial](https://github.com/serial) description       |
| [@serialData](https://github.com/serialData)   | 说明通过writeObject( ) 和 writeExternal( )方法写的数据 |   [@serialData](https://github.com/serialData) description   |
| [@serialField](https://github.com/serialField) |             说明一个ObjectStreamField组件              | [@serialField](https://github.com/serialField) name type description |
| [@since](https://github.com/since)             |               标记当引入一个特定的变化时               |          [@since](https://github.com/since) release          |
| [@throws](https://github.com/throws)           | 和 [@exception](https://github.com/exception)标签一样. | The [@throws](https://github.com/throws) tag has the same meaning as the [@exception](https://github.com/exception) tag. |
| {[@value](https://github.com/value)}           |         显示常量的值，该常量必须是static属性。         | Displays the value of a constant, which must be a static field. |
| [@version](https://github.com/version)         |                      指定类的版本                      |         [@version](https://github.com/version) info          |