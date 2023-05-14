### Swagger简介

**前后端分离**

- 前端 -> 前端控制层、视图层
- 后端 -> 后端控制层、服务层、数据访问层
- 前后端通过API进行交互
- 前后端相对独立且松耦合

产生的问题

前后端集成，前端或者后端无法做到“及时协商，尽早解决”，最终导致问题集中爆发
解决方案

首先定义schema [ 计划的提纲 ]，并实时跟踪最新的API，降低集成风险
Swagger

号称世界上最流行的API框架
Restful Api 文档在线自动生成器 => API 文档 与API 定义同步更新
直接运行，在线测试API
支持多种语言 （如：Java，PHP等）
官网：https://swagger.io/

### SpringBoot集成Swagger



**SpringBoot集成Swagger** => **springfox**，两个jar包

- **Springfox-swagger2**
- swagger-springmvc





**使用Swagger**

要求：jdk 1.8 + 否则swagger2无法运行

步骤：

### 1、新建一个SpringBoot-web项目

### 2、添加Maven依赖 （注意：2.9.2版本之前，之后的不行）





```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>3.0.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>3.0.0</version>
</dependency>

      <!-- https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
            <version>3.0.0</version>
        </dependency>

```

### 3.要使用Swagger，我们需要编写一个配置类-SwaggerConfig来配置 Swagger



```java
@Configuration
@EnableSwagger2 //开启Swagger2
public class SwaggerConfig {
}
```

### 5、访问测试 ：(http://localhost:8080/swagger-ui/index.html) ，可以看到swagger的界面；

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9naXRlZS5jb20vbHpoX2dpdGVlL3NwcmluZ2Jvb3RfaW1hZ2UvcmF3L21hc3Rlci9pbWcvaW1hZ2UtMjAyMDA3MzExMzIyMjkyNjUucG5n?x-oss-process=image/format.png)





```java
//配置了swagger的docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }
//配置swagger信息=apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("laohon","https://www.bilibili.com/","1932576789@qq.com");
        return new ApiInfo(
                "学习swagger",
                "fuck",
                "v1.0",
                "https://www.bilibili.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
```

## 配置扫描接口



1、构建Docket时通过select()方法配置怎么扫描接口。

```java
//配置了swagger的docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //RequestHandlerSelectors 配置要扫描的接口
                .apis(RequestHandlerSelectors.basePackage("com.xie.swagger.controller"))
                .build();
    }
```

2、重启项目测试，由于我们配置根据包的路径扫描接口，所以我们只能看到一个类

3、除了通过包路径配置扫描接口外，还可以通过配置其他方式扫描接口，这里注释一下所有的配置方式：

```java
//RequestHandlerSelectors 配置要扫描的接口
//basePackage ：指定扫描的包
//any：扫描全部
//none：不扫描
//withClassAnnotation:扫描类上的注解
//withMethodAnnotation:扫描方法上的注解
```

4、除此之外，我们还可以配置接口扫描过滤：

```java
.paths(PathSelectors.ant("/xie/**"))
```

### 配置Swagger开关



1、通过enable()方法配置是否启用swagger，如果是false，swagger将不能在浏览器中访问了

```java
.enable(false)
```

2、如何动态配置当项目处于test、dev环境时显示swagger，处于prod时不显示？

```java
public Docket docket(Environment environment){

    //设置要显示的swagger环境
    Profiles profiles = Profiles.of("dev", "test");
    //通过environment.acceptsProfiles判断是否处在注解设定的环境当中
    boolean flag = environment.acceptsProfiles(profiles);
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .enable(flag)
            .select()
            //RequestHandlerSelectors 配置要扫描的接口
            //basePackage ：指定扫描的包
            //any：扫描全部
            //none：不扫描
            //withClassAnnotation:扫描类上的注解
            //withMethodAnnotation:扫描方法上的注解
            .apis(RequestHandlerSelectors.basePackage("com.xie.swagger.controller"))
            //.paths(PathSelectors.ant("/xie/**"))
            .build();
}
```

可以在项目中增加一个dev的配置文件查看效果！

```properties
spring.profiles.active=dev
```

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9naXRlZS5jb20vbHpoX2dpdGVlL3NwcmluZ2Jvb3RfaW1hZ2UvcmF3L21hc3Rlci9pbWcvaW1hZ2UtMjAyMDA3MzExOTMxMDk4MjYucG5n?x-oss-process=image/format,png)

### 配置API分组

1、如果没有配置分组，默认是default。通过groupName()方法即可配置分组：

2、重启项目查看分组

```java
.groupName("谢捞币")
```

3、如何配置多个分组？配置多个分组只需要配置多个docket即可：

```java
`@Bean`
`public Docket docket1(){`
   `return new Docket(DocumentationType.SWAGGER_2).groupName("group1");`
`}`
`@Bean`
`public Docket docket2(){`
   `return new Docket(DocumentationType.SWAGGER_2).groupName("group2");`
`}`
`@Bean`
`public Docket docket3(){`
   `return new Docket(DocumentationType.SWAGGER_2).groupName("group3");`
`}`
```



### 实体配置

1、新建一个实体类

```java
//@Api("注释")
@ApiModel("用户实体")
public class User {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
```



```java
public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}
```
2、只要这个实体在**请求接口**的返回值上（即使是泛型），都能映射到实体项中

```java
//只要我们的接口中，返回值中存在实体类，他就会被扫描到swagger中
@PostMapping(value = "/user")
public User user(){
    return new User();
}
```

2、bootstrap-ui 访问 http://localhost:8080/doc.html

<!-- 引入swagger-bootstrap-ui包 /doc.html-->
<dependency>
   <groupId>com.github.xiaoymin</groupId>
   <artifactId>swagger-bootstrap-ui</artifactId>
   <version>1.9.1</version>
</dependency>



3、Layui-ui 访问 http://localhost:8080/docs.html

<!-- 引入swagger-ui-layer包 /docs.html-->
<dependency>
   <groupId>com.github.caspar-chen</groupId>
   <artifactId>swagger-ui-layer</artifactId>
   <version>1.1.3</version>
</dependency>



4、mg-ui 访问 http://localhost:8080/document.html

<!-- 引入swagger-ui-layer包 /document.html-->
<dependency>
   <groupId>com.zyplayer</groupId>
   <artifactId>swagger-mg-ui</artifactId>
   <version>1.0.6</version>
</dependency>

com.github.caspar-chen
swagger-ui-layer
1.1.3



4、mg-ui   **访问 http://localhost:8080/document.html**

