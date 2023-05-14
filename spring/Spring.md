



## Spring

1.1 简介
Spring：春天------>给软件行业带来了春天！
2002，首次推出了Spring框架的雏形：interface21框架！
Spring框架即以interface21框架为基础，经过重新设计，并不断丰富其内涵，于2004年3月24日发布了1.0正式版。
Rod Johnson，Spring Framework创始人，著名作者。很难想象Rod Johnson的学历，真的让好多人大吃一惊，他是悉尼大学的博士，然而他的专业不是计算机，而是音乐学。
Spring理念：使现有的技术更加容易使用，本身是一个大杂烩，整合了现有的技术框架！
SSH：Struct2 + Spring + Hibernate!
SSM：SpringMVC + Spring + Mybatis!

```xml

`<dependency>`
    `<groupId>org.springframework</groupId>`
    `<artifactId>spring-webmvc</artifactId>`
    `<version>5.2.0.RELEASE</version>`
`</dependency>`
`<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->`
`<dependency>`
    `<groupId>org.springframework</groupId>`
    `<artifactId>spring-jdbc</artifactId>`
    `<version>5.2.0.RELEASE</version>`
`</dependency>
```

### 优点

- Spring是一个开源的免费的框架（容器）！
- Spring是一个轻量级的、非入侵式的框架！
- 控制反转（IOC），面向切面编程（AOP）！
- 支持事务的处理，对框架整合的支持！

总结一句话：Spring就是一个轻量级的控制反转（IOC）和面向切面编程（AOP）的框架！



###  拓展

现代化的Java开发！说白就是基于Spring的开发！

- Spring Boot
  - 一个快速开发的脚手架。
  - 基于SpringBoot可以快速的开发单个微服务。
  - 约定大于配置。
- Spring Cloud
  - SpringCloud是基于SpringBoot实现的。



### IOC理论推导



在我们之前的业务中，用户的需求可能会影响我们原来的代码，我们需要根据用户的需求去修改原代码！如果程序代码量十分大，修改一次的成本代价十分昂贵！


我们使用一个Set接口实现，已经发生了革命性的变化！

    private UserDao userDao;
    
    //利用set进行动态实现值的注入！
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

![img](https://img-blog.csdnimg.cn/20201109112133683.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xpNjQzOTM3NTc5,size_16,color_FFFFFF,t_70#pic_center)
之前，程序是主动创建对象！控制权在程序猿手上！
使用了set注入后，程序不再具有主动性，而是变成了被动的接收对象！
这种思想，从本质上解决了问题，我们程序猿不用再去管理对象的创建了。系统的耦合性大大降低~，可以更加专注的在业务的实现上！这是IOC的原型！



### IOC本质

控制反转IoC（Inversion of Control），是一种设计思想，DI（依赖注入）是实现IoC的一种方法，也有人认为DI只是IoC的另一种说法。没有IoC的程序中，我们使用面向对象编程，对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓控制反转就是：获得依赖对象的方式反转了。

采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。
控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection，DI）



### HelloSpring

#### test

```java
import com.xie.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象现在都在spring中的管理了，我们要使用，直接去里面取出来

        Hello hello = (Hello) context.getBean("hello");

        System.out.println(hello.toString());
    }
}
```

```xml
<import resource="beans.xml"/>
<!--    import 合并 开发用-->
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

<!--使用spring来创建对象，在spring中这些都成为bean
        类型  变量名  =new 类型（）；
        Hello hello =new Hello（）；

        id=变量名
        class =new 的对象
        property相当与给对象中的属性设置一个值！

-->

    <bean id="hello" class="com.xie.pojo.Hello">
        <property name="str" value="Spring"/>
    </bean>

</beans>
```

```java
package com.xie.pojo;

public class Hello {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "str='" + str + '\'' +
                '}';
    }
}
```

这个过程就叫控制反转：

控制：谁来控制对象的创建，传统应用程序的对象是由程序本身控制创建的，使用Spring后，对象是由Spring来创建的。

反转：程序本身不创建对象，而变成被动的接收对象。

依赖注入：就是利用set方法来进行注入的。

IOC是一种编程思想，由主动的编程变成被动的接收。

```xml
<!--    别名-->
    <alias name="user" alias="iojojoi"/>


<!--    id：bean的唯一标识符，也就是相当与我们学的对象名
class：bean对象所对应的全限定名：包名+类型
name：别名，可多个
-->
```

```java
/*
ioc创建对象的方式
1.使用无参构造创建对象，默认
  public User() {
        System.out.println("User的无参构造");
    }
<bean id="user" class="com.xie.pojo.User">
    <property name="name" value="大黑"/>
</bean>
2.假设我们要使用有参构造创建对象
1.下标赋值
<constructor-arg index="0" value="cnm"/>
2.通过类型创建 不建议使用
<constructor-arg type="java.lang.String" value="cnm"/>
3.直接通过参数名创建
<constructor-arg name="name" value="cnm"/>
 */
```



### Set方式注入【重点】

- 依赖注入：Set注入
  - 依赖：bean对象的创建依赖于容器！
  - 注入：bean对象中的所有属性，由容器来注入！



```java
package com.xie.pojo;

public class Address {
    private String address;

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                '}';
    }

    public void setAddress(String address) {
        this.address = address;


    }
}
```

public class Student {

```java
private String name;
private Address address;
private String[] books;
private List<String> hobbies;
private Map<String,String> card;
private Set<String> games;
private String wife;
private Properties info;
```

}





```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

        <bean id="address" class="com.xie.pojo.Address">
            <property name="address" value="西安"/>
        </bean>
    
    <bean id="student" class="com.xie.pojo.Student">

<!-- 第一种，普通值注入 ，value       -->
        <property name="name" value="大黑"/>

<!--        第二种，Bean注入，ref-->
        <property name="address" ref="address"/>
<!--        数组-->
        <property name="books">
            <array>
                <value>红楼梦</value>
                <value>西游记</value>
                <value>水浒传</value>
                <value>三国演义</value>
            </array>
        </property>

<!--        list-->
        <property name="hobbies">
            <list>
                <value>听歌</value>
                <value>打代码</value>
                <value>看电影</value>
            </list>
        </property>

<!--        map-->
        <property name="card">
            <map>
                <entry key="身份证" value="11111111111111"/>
                <entry key="银行卡" value="11111222222222"/>

            </map>
        </property>

<!--        set-->
        <property name="games">
            <set>
                <value>LOL</value>
                <value>cdc</value>
                <value>bbc</value>
            </set>
        </property>
<!--        null-->
        <property name="wife">
            <null></null>
        </property>
<!--        Properties-->
        <property name="info">
            <props>
                <prop key="driver">1212112121</prop>
                <prop key="url">男</prop>
                <prop key="username">root</prop>
                <prop key="password">123456</prop>
            </props>
        </property>
    </bean>



</beans>
```

import com.xie.pojo.Student;
import com.xie.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
    }

```java
@Test
public void  test2(){
    ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
    User user = context.getBean("user", User.class);
    User user2 = context.getBean("user2", User.class);
    //spring 默认单例模式singleton
    System.out.println(user==user2);
}
```

}

p命名和c命名空间不能直接使用，需要导入xml约束！

```xml
   xmlns:p="http://www.springframework.org/schema/p"
   xmlns:c="http://www.springframework.org/schema/c"
```

```xml
<!--    p命名空间注入,可以直接注入-->
<bean id="user" class="com.xie.pojo.User" p:name="大黑" p:age="18" scope="prototype"/>

<bean id="user2" class="com.xie.pojo.User" c:age="18" c:name="sb" scope="prototype"/>
```

1

### 使用注解实现自动装配

@Qualifier(value = “xxx”)去配置@Autowired的使用，指定一个唯一的bean对象注入！

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	        https://www.springframework.org/schema/beans/spring-beans.xsd
	        http://www.springframework.org/schema/context
	        https://www.springframework.org/schema/context/spring-context.xsd">
		

		<!--开启注解的支持    -->
	    <context:annotation-config/>

</beans>
```

```java
public class People {
    @Autowired
    private Cat cat;
    @Autowired
    private Dog dog
```



```xml
<bean id="cat" class="com.xie.pojo.Cat"/>
    <bean id="dog" class="com.xie.pojo.Dog"/>
<!--
byName:会自动在容器中上下文中查找，和自己对象set方法后面值对应的beanid唯一
byType：会自动在容器中上下文中查找，和自己对象属性类型相同的bean 需要保证class唯一
-->

    <bean id="people" class="com.xie.pojo.People"/>
```





```java
package com.xie.pojo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


//等价与《bean id=user class。。。//这里这个注解的意思，就是说明这个类被Spring接管了，注册到了容器中

@Component
public class User {

    @Value("大黑")
    public String name;
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

<!--    指定扫描的包，这个包下的注解就会失效-->
    <context:component-scan base-package="com.xie"/>
    <context:annotation-config/>








</beans>
```



```java
// 这个也会Spring容器托管，注册到容器中，因为它本来就是一个@Component
// @Configuration代表这是一个配置类，就和我们之前看的beans.xml
@Configuration
@ComponentScan("com.kuang.pojo")
@Import(KuangConfig2.class)
public class KuangConfig {

    // 注册一个bean，就相当于我们之前写的一个bean标签
    // 这个方法的名字，就相当于bean标签中id属性
    // 这个方法的返回值，就相当于bean标签中的class属性
    @Bean
    public User user(){
        return new User(); // 就是返回要注入到bean的对象！
    }

}
```



## 代理模式

![img](https://img-blog.csdnimg.cn/20201112093129742.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xpNjQzOTM3NTc5,size_16,color_FFFFFF,t_70#pic_center)

###  静态代理

角色分析：

- 抽象角色：一般会使用接口或者抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
- 客户：访问代理对象的人！



```java
package com.xie.demo01;


//租房
public interface Rent {

    public void rent();

}
```

```java
package com.xie.demo01;

public class Proxy implements Rent{

    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }


    public void rent() {
     host.rent();
     seeHouse();
     hetong();
     fare();
    }


    //看房

    public void  seeHouse(){
        System.out.println("中介带你看房");
    }

    public void  hetong(){
        System.out.println("合同");
    }
    public void  fare(){
        System.out.println("中介费");
    }


}
```

```java
package com.xie.demo01;

//房东 真实的代理角色
public class Host implements Rent {

    public void rent(){
        System.out.println("房东要出租房子！");
    }
}
```

```java
package com.xie.demo01;

public class Client {

    public static void main(String[] args) {

        Host host=new Host();
        //代理,一般会有附属操作
        Proxy proxy = new Proxy(host);
        proxy.rent();

    }
}
```

1

动态代理
动态代理和静态代理角色一样
动态代理的代理类是动态生成的，不是我们直接写好的！
动态代理分为两大类：基于接口的动态代理，基于类的动态代理
基于接口 — JDK动态代理【我们在这里使用】
基于类：cglib
java字节码实现：javassist
需要了解两个类：Proxy：代理；InvocationHandler：调用处理程序。

```xml
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.4</version>
</dependency>
```

applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

<!--    注册bean-->


    <bean id="userService" class="com.xie.service.UserServiceImpl"/>
    <bean id="log" class="com.xie.log.Log"/>
    <bean id="afteLog" class="com.xie.log.AfterLog"/>

<!--    配置aop-->

<!--    <aop:config>-->
<!--&lt;!&ndash;        切入点：expression：表达式&ndash;&gt;-->
<!--        <aop:pointcut id="pointcut" expression="execution(* com.xie.service.UserServiceImpl.*(..))"/>-->

<!--&lt;!&ndash;        执行环绕增加&ndash;&gt;-->

<!--        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>-->
<!--        <aop:advisor advice-ref="afteLog" pointcut-ref="pointcut"/>-->
<!--    </aop:config>-->


<!--    <bean id="diy" class="com.xie.diy.DiyPointCut"/>-->

<!--    <aop:config>-->
<!--&lt;!&ndash;        自定义切面 ref为引用的类&ndash;&gt;-->
<!--        <aop:aspect ref="diy">-->
<!--&lt;!&ndash;            切入点&ndash;&gt;-->
<!--            <aop:pointcut id="point" expression="execution(* com.xie.service.UserServiceImpl.*(..))"/>-->
<!--&lt;!&ndash;            通知&ndash;&gt;-->
<!--            <aop:before method="before" pointcut-ref="point"/>-->
<!--            <aop:after method="after" pointcut-ref="point"/>-->
<!--        </aop:aspect>-->

<!--    </aop:config>-->

    <bean id="annotationPoinCut" class="com.xie.diy.AnnotationPoinCut"/>
<!--    注解开启-->
    <aop:aspectj-autoproxy/>
</beans>
```

MyBatis整合

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-study</artifactId>
        <groupId>com.xie</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-10-mybatis</artifactId>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>

        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.1.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.1.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.4</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.2</version>
        </dependency>
    </dependencies>
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>


</project>
```

spring-dao.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

<!--    DataSource:使用Spring 的数据源代替mybatis的配置  c3p0 dbcp druid-->

    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUniCode=true&amp;characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>


<!--    sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource"/>
<!--        绑定Myabtis配置文件-->
        <property name="configLocation" value="classpath:myabtis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/xie/mapper/*.xml"/>
    </bean>
<!-- SqlSessionTemplate：就是我们使用的 sqlSession  -->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
<!--        只能使用构造器注入sqlSessionFactory，因为没有set方法-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>


</beans>
```

myabtis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <typeAliases>
        <package name="com.xie.pojo"/>

    </typeAliases>

<!--设置-->


<!--    <settings>-->
<!--        <setting name="" value=""/>-->
<!--    </settings>-->
</configuration>
```

applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <import resource="spring-dao.xml"/>

    <bean id="userMapper" class="com.xie.mapper.UserMapperImpl">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>


    <bean id="userMapper2" class="com.xie.mapper.UserMapperImpl2">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>

</beans>
```



1