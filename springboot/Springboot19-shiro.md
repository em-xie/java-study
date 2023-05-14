**1.shiro的10分钟快速开始**

- 导入依赖
  新建一个普通的maven项目,然后new一个hello-shiro(moudle)作为第一个测试项目
  具体框架如下:
  ![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210512215901078-36872889.png)

导入对应的依赖在pom.xml文件里

```xml
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.apache.shiro/shiro-core -->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-core</artifactId>
            <version>1.7.1</version>
        </dependency>

        <!-- configure logging -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jcl-over-slf4j</artifactId>
            <version>1.7.21</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.21</version>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
    </dependencies>
```

- 配置文件
  在官方的github目录下下载zip或者直接copy代码

[shiro的github请点击这](https://github.com/apache/shiro)

在resources目录下新建一个log4j.properties和shiro.ini文件
如果idea创建失败ini文件或者识别ini失败可以看[IDEA中怎么创建ini文件](https://www.cnblogs.com/feng-zhi/p/14762198.html)
log4j.properties具体代码如下:

```ini
log4j.rootLogger=INFO, stdout

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m %n

# General Apache libraries
log4j.logger.org.apache=WARN

# Spring
log4j.logger.org.springframework=WARN

# Default Shiro logging
log4j.logger.org.apache.shiro=INFO

# Disable verbose logging
log4j.logger.org.apache.shiro.util.ThreadContext=WARN
log4j.logger.org.apache.shiro.cache.ehcache.EhCache=WARN
```

shiro.ini的具体代码如下:

```shell
[users]
# user 'root' with password 'secret' and the 'admin' role
root = secret, admin
# user 'guest' with the password 'guest' and the 'guest' role
guest = guest, guest
# user 'presidentskroob' with password '12345' ("That's the same combination on
# my luggage!!!" ;)), and role 'president'
presidentskroob = 12345, president
# user 'darkhelmet' with password 'ludicrousspeed' and roles 'darklord' and 'schwartz'
darkhelmet = ludicrousspeed, darklord, schwartz
# user 'lonestarr' with password 'vespa' and roles 'goodguy' and 'schwartz'
lonestarr = vespa, goodguy, schwartz

# -----------------------------------------------------------------------------
# Roles with assigned permissions
#
# Each line conforms to the format defined in the
# org.apache.shiro.realm.text.TextConfigurationRealm#setRoleDefinitions JavaDoc
# -----------------------------------------------------------------------------
[roles]
# 'admin' role has all permissions, indicated by the wildcard '*'
admin = *
# The 'schwartz' role can do anything (*) with any lightsaber:
schwartz = lightsaber:*
# The 'goodguy' role is allowed to 'drive' (action) the winnebago (type) with
# license plate 'eagle5' (instance specific id)
goodguy = winnebago:drive:eagle5
```

在java目录导入Quickstart.java,具体代码如下:

```java
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {

    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);


    public static void main(String[] args) {

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // Now that a simple Shiro environment is set up, let's see what you can do:

        // get the currently executing user:
        //获取当前的用户对象
        Subject currentUser = SecurityUtils.getSubject();

        // Do some stuff with a Session (no need for a web or EJB container!!!)
        //通过当前用户拿到session
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("Retrieved the correct value! [" + value + "]");
        }

        //判断当前用户是否被认证
        //Token:没有获取,直接设置令牌
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true);//设置记住我
            try {
                currentUser.login(token);//执行登录操作
            } catch (UnknownAccountException uae) {
                log.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //test a role:
        if (currentUser.hasRole("schwartz")) {
            log.info("May the Schwartz be with you!");
        } else {
            log.info("Hello, mere mortal.");
        }
        //粗粒度
        //test a typed permission (not instance-level)
        if (currentUser.isPermitted("lightsaber:wield")) {
            log.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }
        //细粒度
        //a (very powerful) Instance Level permission:
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }
        //注销
        //all done - log out!
        currentUser.logout();
        //结束
        System.exit(0);
    }
}
```

- HelloWorld

开启项目检查
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210512220450395-422885465.png)

**看到能打印出这行信息,说明快如入门成功**

**2.springboot整合shiro环境搭建**

新建一个moudle叫shiro-springboot
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513194623032-126194390.png)

勾选spring web依赖即可
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513194741756-852327453.png)

在pom.xml中导入thymeleaf依赖

```xml
<!--        thymeleaf模板-->
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf-spring5</artifactId>
        </dependency>
        <dependency>
            <groupId>org.thymeleaf.extras</groupId>
            <artifactId>thymeleaf-extras-java8time</artifactId>
        </dependency>
```

新建一个controller编写一个MyController测试,代码如下:

```kotlin
package cn.dzp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @RequestMapping({"/index","/"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello,shiro");
        return "index";
    }
}
```

**在templates下新建一个index.html,导入thymeleaf约束,这样可以编写thymeleaf提示**

> xmlns:th="[http://www.themeleaf.org](http://www.themeleaf.org/)"

index.html完整代码如下:

```xml
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.themeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
<h1>首页</h1>
<p  th:text="${msg}"></p>
</body>
</html>
```

**运行项目检查**
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513195812516-164669649.png)
可以看到可以跑通!

shiro的三大对象:

- Subject:用户
- SecurityManager:管理所有用户
- Realm:连接数据

pom.xml导入依赖:

```xml
<!--shiro整合包-->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.7.1</version>
        </dependency>
```

创建一个config包编写ShiroConfig配置类
代码如下:

```typescript
package cn.dzp.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
//    ShiroFilterFactoryBean:第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//        设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        return bean;
    }
//    DefaultWebSecurityManager:第二步
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
//    Realm:创建realm对象,需要自定义:第一步,从后往前配置
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
```

因为配置涉及到userRealm,这个需要自己自定义,所以在config包下再写一个UserRealm类
代码如下:

```scala
package cn.dzp.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {
//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权的=>doGetAuthorizationInfo");
        return null;
    }
//认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证的=>doGetAuthenticationInfo");
        return null;
    }
}
```

在templates建一个user夹放关于用户的页面:add.html;update:html
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513202311577-205812297.png)
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513202321117-1644105381.png)

回到MyController
添加两个页面跳转的方法

```typescript
@RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }
@RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }
```

回到主页index.html
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513202755865-432061643.png)

再次重启项目检查
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513203114595-1800996345.png)
可以实现跳转add和update页面
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513203139854-295521706.png)
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513203149410-1427342619.png)

**到此环境搭建完成!**

**3.shiro实现登陆拦截**

在ShiroConfig添加shiro的内置过滤器

```javascript
//        添加shiro的内置过滤器
        /*
        * anon:无需认证都可访问
        * authc: 必须认证了才能访问
        * user:必须拥有 记住我 才能用
        * perms:拥有对某个资源的权限才能访问
        * role:拥有某个角色权限才能访问*/
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/add","authc");
        filterMap.put("/user/update","authc");
        //filterMap.put("/user/*","authc");拦截所有user下的请求
        bean.setFilterChainDefinitionMap(filterMap);
```

![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513204106246-159896849.png)

再次运行项目点击add,发现失败,此时拦截已经成功了
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513204214825-92490633.png)

因为它跳转的url是login页面,所以还得重写login页面
代码如下:

```xml
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
</head>
<body>
<h1>登录</h1>
<form action="/toLogin" method="post">
<p>用户名: <input type="text" name="username"></p>
<p>密码: <input type="password" name="password"></p>
<p><input type="submit"></p>
</form>
</body>
</html>
```

MyController添加方法

```typescript
@RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
```

配置登录页面
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513204822006-1584148992.png)

点击add或者update已经跳转到了登录页面,说明已经拦截成功!

![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210513205007923-1755918867.png)

**4.shiro实现用户认证**

在MyController添加login方法:代码如下

```typescript
 @RequestMapping("/login")
    public String login(String username,String password,Model model){
//        获取当前的用户
        Subject subject = SecurityUtils.getSubject();
//        封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);//执行登录的方法,如果没有异常就说明ok了
            return "index";
        }catch (UnknownAccountException e){//用户名不存在
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){//密码不存在
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
```

**在login.html写入信息msg**

![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210514205415260-1926011606.png)

启动项目随便登录测试一下
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210514205531729-4261091.png)

![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210514205555018-831729670.png)

在UserRealm代码下修改认证代码:

//认证
@Override
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
System.out.println("执行了认证的=>doGetAuthenticationInfo");
// 用户名,密码到数据库中取
String name="root";
String password="123456";
UsernamePasswordToken userToken = (UsernamePasswordToken) token;
if (!userToken.getUsername().equals(name)){
return null;//抛出异常:UnknownAccountException
}
// 密码认证:shiro做
return new SimpleAuthenticationInfo("",password,"");
}

重启项目查看,登录写的数据
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210514211020590-43975283.png)

可以看到登陆成功

![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210514211047936-492411838.png)

**5.shiro整合mybatis**

在pom.xml导入对应的依赖

```xml
<!--        整合mybatis-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.6</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.4</version>
        </dependency>
```

在resource目录下新建application.yaml配置文件

```yaml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
    #    使用德鲁伊的数据源
    type: com.alibaba.druid.pool.DruidDataSource
    #Spring Boot 默认是不注入这些属性值的,需要自己绑定
    #druid 数据源专有配置
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true

    #配置监控统计拦截的filters
    # stat:监控统计
    # log4j:日志记录(需要导入log4j依赖)
    # wall:防御sql注入
    filters: stat,wall,log4j
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

新建数据库连接绑定mybatis的数据库
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515110057754-2098160313.png)

application.properties文件里配置mybatis的相关设置
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515110352065-343890.png)

由于配置文件里多了别名的扫描和mapper的文件,所以要完整架构,新建一个pojo实体类和mapper的包,如下
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515110533260-1293304194.png)

pojo实体类为了方便代码简洁,我使用了lombok,在pom导入对应依赖即可

```xml
<dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
```

实体类如下:

```java
package cn.dzp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String pwd;
}
```

编写mapper
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515111117520-1983011555.png)

在编写UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.dzp.mapper.UserMapper">
    <select id="queryUserByName" parameterType="String" resultType="user">
        select * from user where name=#{name}
    </select>
</mapper>
```

在新建service写一个UserService接口和它的实现类
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515122905938-1303562709.png)

UserMapperImpl实现类代码:

```typescript
package cn.dzp.mapper;

import cn.dzp.pojo.User;
import cn.dzp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
```

在测试类测试代码
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515123332455-1236998949.png)

可以看到查询成功
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515123421060-1564527975.png)

这样就可以去改Realm的代码,开始的用户名和密码都是手写伪造的

UserRealm代码修改如下:

```scala
package cn.dzp.config;

import cn.dzp.pojo.User;
import cn.dzp.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权的=>doGetAuthorizationInfo");
        return null;
    }
//认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证的=>doGetAuthenticationInfo");
//        用户名,密码到数据库中取
//        链接真实的数据库
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userService.queryUserByName(userToken.getUsername());
        if (user==null){//没有这个人
            return null;//UnknownAccoutException
        }
//        密码认证:shiro做
//         密码可以加密:md5,md5盐值加密
        return new SimpleAuthenticationInfo("",user.getPwd(),"");
    }
}
```

现在的话登录用户就是数据库中的,启动项目测试

![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515124208318-1516025310.png)

可以看到登录成功

![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515124243453-908777311.png)

**6.shiro实现请求授权**

在ShiroConfig添加部分代码,如图:
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515184142865-513624131.png)

登录用户点击添加的请求,显示未授权401的错误

![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515184236530-1734617237.png)

正常情况下,授权会跳转到未授权的页面,所以才MyController写一个跳转到未授权的页面方法

```typescript
@ResponseBody
    @RequestMapping("/noauth")
    public String unauthorized(){
        return "无法访问此页面";
    }
```

ShiroConfig类修改一下:
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515184804231-1631711820.png)

重启项目测试,发现已经可以跳转到我们设置的未授权的页面了
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515184909460-1248536681.png)

怎样添加add页面的授权呢,在UserRealm修改下代码,因为ShiroConfig设定了add页面需要权限,所以要在UserRealm添加权限

![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515185451965-1015622843.png)

但是所有登录的用户都有此权限,所以我打算把数据库的表新增一个权限的字段,添加下权限
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515190114425-1033994518.png)

记得改下User实体类
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515190301507-758658039.png)

UserRealm类
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515191016204-18139903.png)

ShiroConfig里添加对update的过滤
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515191057723-661910295.png)

开启项目测试:
登录有add权限的账号dzp
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515191243529-1078101731.png)
点击add,成功跳转
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515191328841-1467894510.png)

登录有update权限的账号root
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515191430582-1175272935.png)

点击update,成功跳转
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515191505735-426905406.png)

**7.shiro整合thymeleaf**

导入对应的依赖

```xml
<!--        shiro整合thymeleaf-->
        <dependency>
            <groupId>com.github.theborakompanioni</groupId>
            <artifactId>thymeleaf-extras-shiro</artifactId>
            <version>2.0.0</version>

ShiroConfig类添加方法
```

// 整合ShiroDialect:用来整合shiro thymeleaf
@Bean
public ShiroDialect getShiroDialect(){
return new ShiroDialect();
}

```bash
        </dependency>
```

在index.html添加约束

> xmlns:shiro="http://www.thymeleaf.org/thymeleaf-extras-shiro"

![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515194628706-1829819698.png)

运行项目测试

没有权限不会显示add和update的页面
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515194530959-800408736.png)

为了让登录后不在显示登录按钮,需要在UserRealm里添加代码
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515195720702-2247066.png)

然后在前端判断它从而决定显不显示登录按钮,
index.html:

```xml
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.themeleaf.org"
        xmlns:shiro="http://www.thymeleaf.org/thymeleaf-extras-shiro">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
<h1>首页</h1>
<p  th:text="${msg}"></p>

<div shiro:hasPermission="user:add">
    <a th:href="@{/user/add}">添加</a>
</div>

<div shiro:hasPermission="user:update">
    <a th:href="@{/user/update}">更新</a>
</div>

<br>
<div th:if="${session.loginUser==null}"><a th:href="@{/toLogin}">登录</a></div>
<div><a th:href="@{/logout}">注销</a></div>

</body>
</html>
```

运行项目检查,登录后登录按钮消失
![img](https://img2020.cnblogs.com/blog/2320681/202105/2320681-20210515200156742-1387950667.png)