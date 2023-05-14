# MyBatis

## 1、简介

### 1.1 什么是Mybatis

MyBatis 是一款优秀的持久层框架;
它支持自定义 SQL、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

### 1.2 持久化

数据持久化

- 持久化就是将程序的数据在持久状态和瞬时状态转化的过程
- 内存：**断电即失**
- 数据库（Jdbc）,io文件持久化。

**为什么要持久化？**

- 有一些对象，不能让他丢掉
- 内存太贵

1.3 持久层
Dao层、Service层、Controller层

完成持久化工作的代码块
层界限十分明显
1.4 为什么需要MyBatis
帮助程序员将数据存入到数据库中

方便

传统的JDBC代码太复杂了，简化，框架，自动化

不用MyBatis也可以，技术没有高低之分

优点：

简单易学
灵活
sql和代码的分离，提高了可维护性。
提供映射标签，支持对象与数据库的orm字段关系映射
提供对象关系映射标签，支持对象关系组建维护
提供xml标签，支持编写动态sql



2.1 搭建环境
新建项目

创建一个普通的maven项目

删除src目录 （就可以把此工程当做父工程了，然后创建子工程）

导入maven依赖



```xml
<!--导入依赖-->
<dependencies>
    <!--mysqlq驱动-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.12</version>
    </dependency>
    <!--mybatis-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.4</version>
    </dependency>
    <!--junit-->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```



### 编写mybatis的核心配置文件

mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUniCode=true&amp;characterEncoding=UTF-8"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>
<!--    每一个Mapper.xml都需要在Mybatis核心配置文件中注册-->
    <mappers>
        <mapper resource="com/xie/dao/UserMapper.xml"/>
    </mappers>
</configuration>
```

注意 XML 头部的声明，它用来验证 XML 文档的正确性。environment 元素体中包含了事务管理和连接池的配置。mappers 元素则包含了一组映射器（mapper），这些映射器的 XML 映射文件包含了 SQL 代码和映射定义信息。





### 编写mybatis工具类

mybatisUtils

```java
package com.xie.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
//工具类
//SqlSessionFactory -->sqlSession
public class mybatisUtils {
    //提升作用域
    private static SqlSessionFactory sqlSessionFactory;
    static {
//使用mybatis获取SqlSessionFactory 对象
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
//    SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。
    public static SqlSession getSqlSession(){

        return sqlSessionFactory.openSession();
    }
}
```



可能遇到的问题：

1. 配置文件没有注册；
2. 绑定接口错误；
3. 方法名不对；
4. 返回类型不对；
5. Maven导出资源问题。

### 编写代码

实体类

Dao接口

### 万能Map

假设，我们的实体类，或者数据库中的表，字段或者参数过多，我们应当考虑使用Map！

Map传递参数，直接在sql中取出key即可！【parameterType=“map”】
对象传递参数，直接在sql中取对象的属性即可！【parameterType=“Object”】
只有一个基本类型参数的情况下，可以直接在sql中取到！
多个参数用Map，**或者注解！**

```java
package com.xie.dao;

import com.xie.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //查询全部用户
    List<User> getUserLister();
    //根据id查询用户
    User getUserById(int id);

    //insert一个用户
    int addUser(User user);

    //修改用户
    int updateUser(User user);

    //删除一个用户
    int deleteUser(int id);

    //万能的map
    int addUser2(Map<String,Object> map);

    //模糊查询
    List<User> getUserLike(String value);
}
万能Map
假设，我们的实体类，或者数据库中的表，字段或者参数过多，我们应该考虑使用Map!

UserMapper接口
//用万能Map插入用户
public void addUser2(Map<String,Object> map);

UserMapper.xml
<!--对象中的属性可以直接取出来 传递map的key-->
<insert id="addUser2" parameterType="map">
    insert into user (id,name,password) values (#{userid},#{username},#{userpassword})
</insert>

```

接口实现类 （由原来的UserDaoImpl转变为一个Mapper配置文件）

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.xie.dao.UserMapper">
    <select id="getUserLister" resultType="com.xie.pojo.User">
    select * from mybatis.user
  </select>

    <select id="getUserById" parameterType="int" resultType="com.xie.pojo.User">
        select * from mybatis.user where id=#{id}
    </select>

    <insert id="addUser" parameterType="com.xie.pojo.User">
        insert into mybatis.user(id,name,pwd)value (#{id},#{name},#{pwd});
    </insert>

    <update id="updateUser" parameterType="com.xie.pojo.User">
        update mybatis.user set name=#{name},pwd=#{pwd}  where id=#{id}
    </update>
    <delete id="deleteUser" parameterType="int">
        delete from mybatis.user where id=#{id}
    </delete>

<!--    <insert id="addUser2" parameterType="map">-->
<!--        insert into mybatis.user (id,pwd) values (#{userid},#{pwd});-->
<!--    </insert>-->



<!--
select * from  mybatis.user where id=?
select * from  mybatis.user where id=1 or 1=1
-->
    <select id="getUserLike" resultType="com.xie.pojo.User">
        select * from  mybatis.user where name like "%"#{value}"%"
    </select>
</mapper>
```

### junit

```java
package com.xie.dao;

import com.xie.pojo.User;
import com.xie.utils.mybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    @Test
    public void test(){
        //获取SqlSession对象
        SqlSession sqlSession = mybatisUtils.getSqlSession();

        //执行SQL
        //方式1：getMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> userLister = mapper.getUserLister();

        //方式二

        // List<User>  userLister = sqlSession.selectList("com.xie.dap.UserDao.getUserList");


        for (User user : userLister) {
            System.out.println(user);
        }

        //关闭sqlSession
        sqlSession.close();

    }

    @Test
    public void getUserById(){
        SqlSession sqlSession = mybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(1);
        System.out.println(userById);

        sqlSession.close();
    }

//增删改需要提交事务
    @Test
    public void addUser(){
        SqlSession sqlSession = mybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res= mapper.addUser(new User(4,"哈哈","1212112121"));


        if(res>0){
            System.out.println("插入成功");
        }
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession = mybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser(new User(4,"呵呵","111111"));

        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void deleteUser(){
        SqlSession sqlSession = mybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser(4);

        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void addUser2(){
        SqlSession sqlSession = mybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
       Map<String,Object> map=new HashMap<String,Object>();
        map.put("userid",5);
        map.put("pwd",12121);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getUserLike(){
        SqlSession sqlSession = mybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userLike = mapper.getUserLike("大");

        for (User user : userLike) {
            System.out.println(user);
        }
        //提交事务
//        sqlSession.commit();
        sqlSession.close();
    }

}
```

**注意：增删改查一定要提交事务：**

```java
sqlSession.commit();
```

## 配置

MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。 配置文档的顶层结构如下：

- configuration（配置）
  - [properties（属性）](https://mybatis.org/mybatis-3/zh/configuration.html#properties)
  - [settings（设置）](https://mybatis.org/mybatis-3/zh/configuration.html#settings)
  - [typeAliases（类型别名）](https://mybatis.org/mybatis-3/zh/configuration.html#typeAliases)
  - [typeHandlers（类型处理器）](https://mybatis.org/mybatis-3/zh/configuration.html#typeHandlers)
  - [objectFactory（对象工厂）](https://mybatis.org/mybatis-3/zh/configuration.html#objectFactory)
  - [plugins（插件）](https://mybatis.org/mybatis-3/zh/configuration.html#plugins)
  - environments（环境配置）
    - environment（环境变量）
      - transactionManager（事务管理器）
      - dataSource（数据源）
  - [databaseIdProvider（数据库厂商标识）](https://mybatis.org/mybatis-3/zh/configuration.html#databaseIdProvider)
  - [mappers（映射器）](https://mybatis.org/mybatis-3/zh/configuration.html#mappers)

MyBatis默认的事务管理器就是JDBC ，连接池：POOLED

### . 属性 properties

我们可以通过properties属性来实现引用配置文件

这些属性可以在外部进行配置，并可以进行动态替换。你既可以在典型的 Java 属性文件中配置这些属性，也可以在 properties 元素的子元素中设置。【db.poperties】

编写一个配置文件

db.properties

```java
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&useUniCode=true&characterEncoding=UTF-8
username=root
password=123456
```

置文件中引入

```xml
<!--    引入外部文件-->
    <properties resource="db.properties"/>
<!--可以给实体类写别名-->
<typeAliases>
    <package name="com.xie.pojo.User"/>
</typeAliases>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
<!--    每一个Mapper.xml都需要在Mybatis核心配置文件中欧你注册-->
    <mappers>
        <mapper resource="com/xie/dao/UserMapper.xml"/>
<!--        <mapper class="com.xie.dao.UserMapper"/>-->
    </mappers>
</configuration>
```





```xml
<!--    结果集映射-->
    <resultMap id="UserMap" type="User">
        <result column="id" property="id"/>
        <result column="name" property="name"/>
        <result column="pwd" property="password"/>
    </resultMap>

    <select id="getUserById"  resultMap="UserMap">
        select * from mybatis.user where id=#{id}
    </select>
```



```xml
<!--    标准的日志工厂实现-->
<!--    <settings>-->
<!--        <setting name="logImpl" value="STDOUT_LOGGING"/>-->
<!--    </settings>-->
    
    <settings>
        <setting name="logImpl" value="LOG4J"/>
    </settings>
```

| logImpl | 指定 MyBatis 所用日志的具体实现，未指定时将自动查找。 |
| ------- | ----------------------------------------------------- |
|         |                                                       |

先导入log4j的包

```xml
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

```properties
log4j.rootLogger=DEBUG,console,file

#控制台输出的相关设置
log4j.appender.console = org.apache.log4j.ConsoleAppender
log4j.appender.console.Target = System.out
log4j.appender.console.Threshold=DEBUG
log4j.appender.console.layout = org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=[%c]-%m%n

#文件输出的相关设置
log4j.appender.file = org.apache.log4j.RollingFileAppender
log4j.appender.file.File=./log/kuang.text
log4j.appender.file.MaxFileSize=10mb
log4j.appender.file.Threshold=DEBUG
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n

#日志输出级别
log4j.logger.org.mybatis=DEBUG
log4j.logger.java.sql=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
```





分页
思考：为什么分页？

减少数据的处理量
7.1 使用Limit分页
SELECT * from user limit startIndex,pageSize 
1
使用MyBatis实现分页，核心SQL

接口

//分页
List<User> getUserByLimit(Map<String,Integer> map);

Mapper.xml

<!--分页查询-->
<select id="getUserByLimit" parameterType="map" resultMap="UserMap">
    select * from user limit #{startIndex},#{pageSize}
</select>
```

```


测试

```java
@Test
public void getUserByLimit(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    map.put("startIndex",1);
    map.put("pageSize",2);
    List<User> list = mapper.getUserByLimit(map);
    for (User user : list) {
        System.out.println(user);
    }
}
```

### 7.2 RowBounds分页

不再使用SQL实现分页

1. 接口

```
    //分页2
    List<User> getUserByRowBounds();

```



1. Mapper.xml

```xml
<!--    分页2-->
    <select id="getUserByRowBounds" resultMap="UserMap">
        select * from mybatis.user
    </select>
```

1. 测试

```
    @Test
    public void getUserByRowBounds(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //RowBounds实现
        RowBounds rowBounds = new RowBounds(0, 2);

        //通过java代码层面实现分页
        List<User> userList = sqlSession.selectList("com.kuang.dao.UserMapper.getUserByRowBounds",null,rowBounds);

        for (User user : userList) {
            System.out.println(user);
        }
        
        sqlSession.close();
    }

```

### 7.3 分页插件

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201024131905259.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xpNjQzOTM3NTc5,size_16,color_FFFFFF,t_70#pic_center)



了解即可，使用时，需要知道是什么东西！





## 8、使用注解开发注解在接口上实现

8.1 面向接口编程
之前学过面向对象编程，也学习过接口，但在真正的开发中，很多时候会选择面向接口编程。
根本原因：解耦，可拓展，提高复用，分层开发中，上层不用管具体的实现，大家都遵守共同的标准，使得开发变得容易，规范性更好
在一个面向对象的系统中，系统的各种功能是由许许多多的不同对象协作完成的。在这种情况下，各个对象内部是如何实现自己的，对系统设计人员来讲就不那么重要了；
而各个对象之间的协作关系则成为系统设计的关键。小到不同类之间的通信，大到各模块之间的交互，在系统设计之初都是要着重考虑的，这也是系统设计的主要工作内容。面向接口编程就是指按照这种思想来编程。

### 8.2 使用注解开发

注解在UserMapper接口上实现，并删除UserMapper.xml文件


```
@Select("select * from user")
List<User> getUsers();
```

1. 需要在mybatis-config.xml核心配置文件中绑定接口

```
<mappers>
    <mapper class="com.kuang.dao.UserMapper"/>
</mappers>
```

测试

```
    @Test
    public void getUsers(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUsers();

        for (User user : users) {
            System.out.println(user);
        }

        sqlSession.close();
    }

```

本质：反射机制实现

底层：动态代理


MyBatis详细执行流程

### 8.3 注解CURD

1. 在MybatisUtils工具类创建的时候实现自动提交事务！

```java
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(true);
    }
```

1. 编写接口，增加注解

```
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

    //方法存在多个参数，所有参数前面必须加上@Param("id")注解
    @Select("select * from user where id=#{id}")
    User getUserById(@Param("id") int id);

    @Insert("insert into user (id,name,pwd) values(#{id},#{name},#{password})")
    int addUser(User user);

    @Update("update user set name=#{name},pwd=#{password} where id=#{id}")
    int updateUser(User user);

    @Delete("delete from user where id = #{uid}")
    int deleteUser(@Param("uid") int id);
    
}

```

1. 测试类

【注意：我们必须要将接口注册绑定到我们的核心配置文件中！】

关于@Param( )注解

基本类型的参数或者String类型，需要加上
引用类型不需要加
如果只有一个基本类型的话，可以忽略，但是建议大家都加上
我们在SQL中引用的就是我们这里的@Param()中设定的属性名
#{} 和 ${}

## 9、Lombok（偷懒的话可以使用）

使用步骤：

1. 在IDEA中安装Lombok插件！
2. 在项目pom.xml文件中导入Lombok的jar包

```
        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.10</version>
        </dependency>

```

1. 在实体类上加注解即可！

   

   ```
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   
   ```

   ```
   @Getter and @Setter
   @FieldNameConstants
   @ToString
   @EqualsAndHashCode
   @AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
   @Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
   @Data
   @Builder
   @SuperBuilder
   @Singular
   @Delegate
   @Value
   @Accessors
   @Wither
   @With
   @SneakyThrows
   
   ```

   

### 多对一

多对一：

- 多个学生，对应一个老师
- 对于学生而言，**关联**–多个学生，关联一个老师【多对一】
- 对于老师而言，**集合**–一个老师，有很多个学生【一对多】

SQL语句：

```
CREATE TABLE `teacher` (
	`id` INT(10) NOT NULL,
	`name` VARCHAR(30) DEFAULT NULL,
	PRIMARY KEY (`id`)
)ENGINE = INNODB DEFAULT CHARSET=utf8

INSERT INTO teacher(`id`,`name`) VALUES (1,'秦老师');

CREATE TABLE `student` (
	`id` INT(10) NOT NULL,
	`name` VARCHAR(30) DEFAULT NULL,
	`tid` INT(10) DEFAULT NULL,
	PRIMARY KEY (`id`),
	KEY `fktid`(`tid`),
	CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
)ENGINE = INNODB DEFAULT CHARSET=utf8

INSERT INTO `student`(`id`,`name`,`tid`) VALUES ('1','小明','1');
INSERT INTO `student`(`id`,`name`,`tid`) VALUES ('2','小红','1');
INSERT INTO `student`(`id`,`name`,`tid`) VALUES ('3','小张','1');
INSERT INTO `student`(`id`,`name`,`tid`) VALUES ('4','小李','1');
INSERT INTO `student`(`id`,`name`,`tid`) VALUES ('5','小王','1');

```

### 测试环境搭建

1. 导入Lombok
2. 新建实体类Teacher，Student
3. 建立Mapper接口
4. 建立Mapper.XML文件（在resource下建同级包-com/xie/dao）
5. 在核心配置文件中绑定注册我们的Mapper接口或者文件！【方式很多,随心选】
6. 测试查询是否能够成功！

### 按照结果嵌套处理

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.xie.dao.StudentMapper">
    <!--    按照结果嵌套处理-->
    <select id="getStudent2" resultMap="StudentTeacher2">
        select s.id sid,s.name sname,t.name tname
        from student s,teacher t
        where s.tid=t.id;
    </select>
    <resultMap id="StudentTeacher2" type="Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <association property="teacher" javaType="Teacher">
            <result property="name" column="tname"/>
        </association>
    </resultMap>







按照查询嵌套处理
    <!--================================================-->
    <!--思路
        1.查询所有的学生信息
        2.根据查询出来的学生的tid，寻找对应的老师-->

    <select id="getStudent" resultMap="StudentTeacher">
        select * from student
    </select>

    <resultMap id="StudentTeacher" type="Student">
        <result property="id" column="id"/>
        <result property="name" column="name"/>
        <!--        负责的属性，我们需要单独处理，对象；association，集合 ：collection-->
        <association property="teacher" column="tid" javaType="Teacher" select="getTeacher"/>
    </resultMap>



    <select id="getTeacher" resultType="Teacher">
        select * from teacher where id = #{id}
    </select>




</mapper>
```

回顾Mysql多对一查询方式：

- 子查询
- 联表查询

### 一对多

比如：一个老师拥有多个学生！
对于老师而言，就是一对多的关系！

### 环境搭建

1. 环境搭建，和刚才一样
   **实体类：**





```
@Data
public class Student {
    private int id;
    private String name;
    private int tid;

}

```

```
@Data
public class Teacher {
    private int id;
    private String name;

    //一个老师拥有多个学生
    private List<Student> students;
}

```

### 按照结果嵌套处理

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.xie.dao.TeacherMapper">

<select id="getTeacher" resultMap="TeacherStudent">
    select s.id sid, s.name sname, t.name tname, t.id tid
    from student s,teacher t
    where s.tid=t.id and t.id=#{tid}
</select>
    <resultMap id="TeacherStudent" type="Teacher">
        <result property="id" column="tid"/>
        <result property="name" column="tname"/>
        <collection property="students" ofType="Student">
            <result property="id" column="sid"/>
            <result property="name" column="sname"/>
            <result property="tid" column="tid"/>
        </collection>
    </resultMap>


    </mapper>
```

### 按照查询嵌套处理

    <select id="getTeacher2" resultMap="TeacherStudent2">
        select * from mybatis.teacher where id = #{tid}
    </select>
    
    <resultMap id="TeacherStudent2" type="Teacher">
        <collection property="students" javaType="ArrayList" ofType="Student" select="getStudentByTeacherId" column="id"/>
    </resultMap>
    
    <select id="getStudentByTeacherId" resultType="Student">
        select * from  mybatis.student where tid = #{tid}
    </select>

1. 关联 - association 【多对一】
2. 集合 - collection 【一对多】
3. javaType & ofType
   1. JavaType用来指定实体类中的类型
   2. ofType用来指定映射到List或者集合中的pojo类型，泛型中的约束类型



### 动态SQL

**什么是动态SQL：动态SQL就是 指根据不同的条件生成不同的SQL语句**

利用动态SQL这一特性可以彻底摆脱这种痛苦。

```
搭建环境
CREATE TABLE `blog`(
	`id` VARCHAR(50) NOT NULL COMMENT '博客id',
	`title` VARCHAR(100) NOT NULL COMMENT '博客标题',
	`author` VARCHAR(30) NOT NULL COMMENT '博客作者',
	`create_time` DATETIME NOT NULL COMMENT '创建时间',
	`views` INT(30) NOT NULL COMMENT '浏览量'
)ENGINE=INNODB DEFAULT CHARSET=utf8


```

1. 导包

2. 编写配置文件

3. 编写实体类

   ```
   @Data
   public class Blog {
       private String id;
       private String title;
       private String author;
       private Date createTime; //属性名和字段名不一致
       private int views;
   
   
   }
   
   ```

   1. 编写实体类对应Mapper接口和Mapper.XML文件

      ### if

      



```
<select id="queryBlogIF" parameterType="map" resultType="blog">
    select * from blog
    <where>
        <if test="title!=null">
            and title = #{title}
        </if>
        <if test="author!=null">
            and author = #{author}
        </if>
    </where>
</select>
```

### foreach

动态 SQL 的另一个常见使用场景是对集合进行遍历（尤其是在构建 IN 条件语句的时候）。比如：

```xml
<select id="selectPostIn" resultType="domain.blog.Post">
  SELECT *
  FROM POST P
  WHERE ID in
  <foreach item="item" index="index" collection="list"
      open="(" separator="," close=")">
        #{item}
  </foreach>
</select>
```

### choose (when, otherwise)

```
    <select id="queryBlogChoose" parameterType="map" resultType="Blog">
        select * from mybatis.blog
        <where>
            <choose>
                <when test="title != null">
                    title = #{title}
                </when>
                <when test="author != null">
                    and author = #{author}
                </when>
                <otherwise>
                    and views = #{views}
                </otherwise>
            </choose>
        </where>
    </select>

```

### trim (where, set)

```
    <select id="queryBlogIF" parameterType="map" resultType="Blog">
        select * from mybatis.blog
        <where>
            <if test="title != null">
                and title = #{title}
            </if>
            <if test="author != null">
                and author = #{author}
            </if>
        </where>

    </select>

```

```
    <update id="updateBlog" parameterType="map">
        update mybatis.blog
        <set>
            <if test="title != null">
                title = #{title},
            </if>
            <if test="author != null">
                author = #{author}
            </if>
        </set>
        where id = #{id}
    </update>

```

所谓的动态SQL，本质还是SQL语句，只是我们可以在SQL层面，去执行一个逻辑代码

### Foreach

动态 SQL 的另一个常见使用场景是对集合进行遍历（尤其是在构建 IN 条件语句的时候）。

foreach 元素的功能非常强大，它允许你指定一个集合，声明可以在元素体内使用的集合项（item）和索引（index）变量。它也允许你指定开头与结尾的字符串以及集合项迭代之间的分隔符。这个元素也不会错误地添加多余的分隔符，看它多智能！

提示你可以将任何可迭代对象（如 List、Set 等）、Map 对象或者数组对象作为集合参数传递给 foreach。当使用可迭代对象或者数组时，index 是当前迭代的序号，item 的值是本次迭代获取到的元素。当使用 Map 对象（或者 Map.Entry 对象的集合）时，index 是键，item 是值。


```
    <!--select * from blog where 1=1 and (id=1 or id=2 or id=3)
        我们现在传递一个万能的map，这map中可以存在一个集合！
        -->
    <select id="queryBlogForeach" parameterType="map" resultType="Blog">
        select * from mybatis.blog
        <where>
            <foreach collection="ids" item="id" open="and (" close=")" separator="or">
                id = #{id}
            </foreach>
        </where>
    </select>

```

动态SQL就是在拼接SQL语句，我们只要保证SQL的正确性，按照SQL的格式，去排列组合就可以了。
建议：

先在Mysql中写出完整的SQL，再对应的去修改成我们的动态SQL实现通用即可！

### SQL片段

有的时候，我们可以能会将一些功能的部分抽取出来，方便复用！

使用SQL标签抽取公共的部分


```
    <sql id="if-title-author">
        <if test="title != null">
            title = #{title}
        </if>
        <if test="author != null">
            and author = #{author}
        </if>
    </sql>

```

1. 在需要使用的地方使用Include标签引用即可

2. ```
       <select id="queryBlogIF" parameterType="map" resultType="Blog">
           select * from mybatis.blog
           <where>
               <include refid="if-title-author"></include>
           </where>
       </select>
   
   ```

   

注意事项：

- 最好基于单表来定义SQL片段！
- 不要存在where标签