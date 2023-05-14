```dp.properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/shop?userUnicode=false&characterEncoding=utf8&useSSL=false
username=root
password=123456
```

```java
package com.xie.lesson01;

import java.sql.*;

public class jdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.用户信息和url
        String ur1="jdbc:mysql://localhost:3306/shop?useUnicode=false&characterEncoding=utf8&useSSL=false";
        String username="root";
        String password="123456";

        //3.链接成功，数据库对象 Connection 代表数据库
        Connection connection = DriverManager.getConnection(ur1, username, password);

        //4.执行sql的对象Statement，执行sql的对象
        Statement statement = connection.createStatement();

        //5.执行sql的对象 去 执行sql，可能存在结果，查看返回结果
        String sql="SELECT *FROM account";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.println("id="+resultSet.getObject("id"));
        }

        //6.释放链接 从后往前

        resultSet.close();
        statement.close();
        connection.close();
    }

}
```





Connection
//链接成功 数据库对象 connection代表数据库
Connection connection = DriverManager.getConnection(url,username,password);
connection.rollback();
connection.rollback();
connection.setAutoCommit();

Statement
        statement.executeQuery();//查询操作 返回结果集ResultSet
        statement.execute();//可以执行任何sql
        statement.executeUpdate();//更新 插入 删除都是用这个，返回受影响的行数

ResultSet
//不知道类型就用Object
resultSet.getObject();
//知道类型可以直接使用对应类型获取
resultSet.getString();
resultSet.getInt();
resultSet.getFloat();
resultSet.getDouble();
resultSet.next();//移动到下一行数据
resultSet.beforeFirst();//移动到最前
resultSet.afterLast();//移动到最后
resultSet.previous();//移动到前一行
resultSet.absolute(i);//移动到第i行



工具类

```java
package com.xie.lesson02.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class jdbcUtils {

    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;



    static {
        try{
            InputStream in = jdbcUtils.class.getClassLoader().getResourceAsStream("dp.properties");
            Properties properties = new Properties();
            properties.load(in);

          driver=  properties.getProperty("driver");
           url= properties.getProperty("url");
           username= properties.getProperty("username");
           password= properties.getProperty("password");

           //1.驱动只用加载一次
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取链接

    public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(url,username,password);

    }

    //释放链接资源
    public static  void release(Connection conn, Statement st,ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
```



```java
package com.xie.lesson02;

import com.xie.lesson02.utils.jdbcUtils;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {
    public static void main(String[] args) {
        Connection coon=null;
        Statement st=null;
        ResultSet rs=null;
        try {
           coon= jdbcUtils.getConnection();//获取数据库链接
           st=coon.createStatement();//获得sql的执行对象
           String sql="INSERT INTO account(id,`name`,`money`)\n" +
                   "VALUES(3,'dahei','1')";

            int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("插入成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.release(coon,st,rs);
        }

    }
}
```



```java
package com.xie.lesson02;

import com.xie.lesson02.utils.jdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {
    public static void main(String[] args) {
        Connection coon=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            coon= jdbcUtils.getConnection();//获取数据库链接
            st=coon.createStatement();//获得sql的执行对象
            String sql="DELETE FROM account WHERE id=3";

            int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("删除成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.release(coon,st,rs);
        }

    }
}
```





```java
package com.xie.lesson02;

import com.xie.lesson02.utils.jdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
    public static void main(String[] args) {
        Connection coon=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            coon= jdbcUtils.getConnection();//获取数据库链接
            st=coon.createStatement();//获得sql的执行对象
            String sql="UPDATE account SET `name`='dahei',`money`='1' WHERE id=1";

            int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("更新成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.release(coon,st,rs);
        }

    }
}
```





```java
package com.xie.lesson02;

import com.xie.lesson02.utils.jdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {
    public static void main(String[] args) {
        Connection coon=null;
        Statement st=null ;
        ResultSet rs=null;


        try {
          coon=  jdbcUtils.getConnection();
          st=coon.createStatement();

          //sql
            String sql="SELECT * FROM account WHERE id=1";
            rs=st.executeQuery(sql);//查询完毕会返回一个结果集

            while (rs.next()){
                System.out.println(rs.getString("name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.release(coon,st,rs);
        }
    }
}
```





## SQL注入的问题

SQL注入即是指web应用程序对用户输入数据的合法性没有判断或过滤不严，攻击者可以在web应用程序中事先定义好的查询语句的结尾上添加额外的SQL语句，在管理员不知情的情况下实现非法操作，以此来实现欺骗数据库服务器执行非授权的任意查询，从而进一步得到相应的数据信息。

sql存在漏洞，会被攻击导致数据泄露
比如登录业务中，需要查询账号密码所对应的用户（比对），可能用到如下sql语句：

```mysql
select * from users where name='name' and password ='password'
```


其中name和password两个变量都是用户所传入的数据
如果用户构造合适的输入，比如：

String name=" ' or  1=1 -- ";
String password ="12412r1";//password在此例中的值不重要

那么如上sql语句拼接成了：

select * from users where name='  ' or  1=1 -- password ='12412r1'

就可以匹配到表中所有用户的信息

PreparedStatement
可以防止SQL注入，而且效率更高





```java
package com.xie.lesson03;

import com.xie.lesson02.utils.jdbcUtils;

import java.sql.*;

public class sql注入 {
    public static void main(String[] args) {
      //  login("laohon");
        login(" 'or '1=1");
    }

    //登录业务
    public static void login(String username) {
        Connection coon = null;
        //防止sql注入的本质，把传递进来的参数当做字符集
        PreparedStatement st = null;
        ResultSet rs = null;


        try {
            coon = jdbcUtils.getConnection();



            String sql = "SELECT * FROM account WHERE `name`=?";
            st=coon.prepareStatement(sql);
            st.setString(1,username);
            rs=st.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.release(coon, st, rs);
        }
    }
}
```



```
//区别
//使用？ 占位符代替参数
String sql="delete from account where id=4";
st=coon.prepareStatement(sql);//预编译sql，先写sql，然后不换行
```





```java
package com.xie.lesson04;

import com.xie.lesson02.utils.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction1 {
    public static void main(String[] args) {
        Connection coon=null;
        PreparedStatement st=null;
        ResultSet rs=null;

        try {
            coon=jdbcUtils.getConnection();
            //关闭数据库的自动提交，自动会开启事务
            coon.setAutoCommit(false);//开启事务

            String sql1="update account set money= money-100 where name='laohon'";

            st=coon.prepareStatement(sql1);
            st.executeUpdate();

            String sql2="update account set money= money+100 where name='B'";
            st=coon.prepareStatement(sql2);
            st.executeUpdate();

            //业务完毕，提交事务
            coon.commit();
            System.out.println("成功！");
        } catch (SQLException e) {
            try {
                coon.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            jdbcUtils.release(coon,st,rs);
        }
    }
}
```





```java
package com.xie.lesson04;

import com.xie.lesson02.utils.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction2 {
    public static void main(String[] args) {
        Connection coon=null;
        PreparedStatement st=null;
        ResultSet rs=null;

        try {
            coon= jdbcUtils.getConnection();
            //关闭数据库的自动提交，自动会开启事务
            coon.setAutoCommit(false);//开启事务

            String sql1="update account set money= money-100 where name='laohon'";

            st=coon.prepareStatement(sql1);
            st.executeUpdate();

            int x=1/0;

            String sql2="update account set money= money+100 where name='B'";
            st=coon.prepareStatement(sql2);
            st.executeUpdate();

            //业务完毕，提交事务
            coon.commit();
            System.out.println("成功！");
        } catch (SQLException e) {
            try {
                //一旦失败就会回滚；
                coon.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            jdbcUtils.release(coon,st,rs);
        }
    }
}
```





数据库连接池
数据库链接–执行完毕–施放十分消耗资源
池化技术：准备一些预先的资源，过来就链接预先准备好的

若常用连接数10个
最小连接数：10个即可
最大连接数：15 业务最高承载上限，超过此值则排队等待
等待超时：等待时间超过一定值直接失败

编写连接池：实现DataSource接口

开源数据源实现

DBCP
C3P0
Druid：阿里巴巴

使用了数据库连接池之后，我们在项目开发中就不需要编写链接数据库的代码了！

DBCP
需要导入的包：

commons-dbcp-1.4
commons-pool-1.6
