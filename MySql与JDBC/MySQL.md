> `什么是数据库`

`数据库 ( **DataBase** , 简称**DB** )`

`**概念** : 长期存放在计算机内,有组织,可共享的大量数据的集合,是一个数据 "仓库"`

`**作用** : 保存,并能安全管理数据(如:增删改查等),减少冗余...`

`**数据库总览 :**`

- `关系型数据库 ( SQL )`

- - `MySQL , Oracle , SQL Server , SQLite , DB2 , ...`
  - `关系型数据库通过外键关联来建立表与表之间的关系`

- `非关系型数据库 ( NOSQL )`

- - `Redis , MongoDB , ...`
  - `非关系型数据库通常指数据以对象的形式存储在数据库中，而对象之间的关系通过每个对象自身的属性来决定`
  - 

- `什么是DBMS`

- `数据库管理系统 ( **D**ata**B**ase **M**anagement **S**ystem )`

- `数据库管理软件 , 科学组织和存储数据 , 高效地获取和维护数据`





mysql5.7 64位下载地址:

https://dev.mysql.com/get/Downloads/MySQL-5.7/mysql-5.7.19-winx64.zip

电脑是64位的就下载使用64位版本的！



> 安装步骤

1、下载后得到zip压缩包.

2、解压到自己想要安装到的目录，本人解压到的是D:\Environment\mysql-5.7.19

3、添加环境变量：我的电脑->属性->高级->环境变量

```
选择PATH,在其后面添加: 你的mysql 安装文件下面的bin文件夹
```

4、编辑 my.ini 文件 ,注意替换路径位置

```
[mysqld]
basedir=D:\Program Files\mysql-5.7\
datadir=D:\Program Files\mysql-5.7\data\
port=3306
skip-grant-tables
```

5、启动管理员模式下的CMD，并将路径切换至mysql下的bin目录，然后输入mysqld –install (安装mysql)

6、再输入  mysqld --initialize-insecure --user=mysql 初始化数据文件

7、然后再次启动mysql 然后用命令 mysql –u root –p 进入mysql管理界面（密码可为空）

8、进入界面后更改root密码

```
update mysql.user set authentication_string=password('123456') where user='root' and Host = 'localhost';
```

9、刷新权限

```
flush privileges;
```

10、修改 my.ini文件删除最后一句skip-grant-tables

11、重启mysql即可正常使用

```
net stop mysql
net start mysql
```

12、连接上测试出现以下结果就安装好了







打开MySQL命令窗口

- 在DOS命令行窗口进入 **安装目录\mysql\bin**
- 可设置环境变量，设置了环境变量，可以在任意目录打开！

**连接数据库语句 :** mysql -h 服务器主机地址 -u 用户名 -p 用户密码

注意 : -p后面不能加空格,否则会被当做密码的内容,导致登录失败 !

**几个基本的数据库操作命令 :**

```mysql
update user set password=password('123456')where user='root'; 修改密码
flush privileges; 刷新数据库
show databases; 显示所有数据库
use dbname；打开某个数据库
show tables; 显示数据库mysql中所有的表
describe user; 显示表mysql数据库中user表的列信息
create database name; 创建数据库
use databasename; 选择数据库

exit; 退出Mysql
? 命令关键词 : 寻求帮助
-- 表示注释
```





### 结构化查询语句分类

![图片](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7JZX1D0neUIMjSFiazUGK15QOGpDicLzwS6NJVAeLECu2rx2qboGU36S9hXLov0x0c5vzZAceySSXnA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

### 数据库操作

> 命令行操作数据库

创建数据库 :  create database [if not exists] 数据库名;

删除数据库 : drop database [if exists] 数据库名;

查看数据库 : show databases;

使用数据库 : use 数据库名;



2.2、数据库的列类型

数值

tinyint 十分小的数据 一个字节
smallint 较小的数据 ， 两个字节
mediumint 中等大小的数据 三个字节
int 标准的整数 四个字节
big 较大的数据 八个字节
float 浮点数 四个字节
double 浮点数 八个字节（精度问题）
decimal 字符串形式的浮点数 金融计算的时候，一般用decimal
字符串

char 字符串固定大小 0~255
varchar 可变字符串 0~65535 常用的String
tinytext 微形文本 2^8-1
text 文本穿 2^16-1
时间日期

java,util.Date

date yyyy-mm-dd,日期
time hh：mm：ss 时间格式
datetime yyyy-mm-dd hh：mm：ss 最常用的时间格式
timestamp 时间戳 1970.1.1到现在的毫秒数 较为常用
year 年份表示
null

没有数值，未知
不要用null运算，结果为null
————————————————



### 创建数据表

属于DDL的一种，语法 :

```
create table [if not exists] `表名`(
   '字段名1' 列类型 [属性][索引][注释],
   '字段名2' 列类型 [属性][索引][注释],
  #...
   '字段名n' 列类型 [属性][索引][注释]
)[表类型][表字符集][注释];
```

**说明 :** 反引号用于区别MySQL保留字与普通字符而引入的 (键盘esc下面的键).



### 数据字段属性

**UnSigned**

- 无符号的
- 声明该数据列不允许负数 .

**ZEROFILL**

- 0填充的
- 不足位数的用0来填充 , 如int(3),5则为005

**Auto_InCrement**

- 自动增长的 , 每添加一条数据 , 自动在上一个记录数上加 1(默认)

- 通常用于设置**主键** , 且为整数类型

- 可定义起始值和步长

- - 当前表设置步长(AUTO_INCREMENT=100) : 只影响当前表
  - SET @@auto_increment_increment=5 ; 影响所有使用自增的表(全局)

**NULL 和 NOT NULL**

- 默认为NULL , 即没有插入该列的数值
- 如果设置为NOT NULL , 则该列必须有值

**DEFAULT**

- 默认的
- 用于设置默认值
- 例如,性别字段,默认为"男" , 否则为 "女" ; 若无指定该列的值 , 则默认值为"男"的值







```mysql

-- 目标 : 创建一个school数据库
-- 创建学生表(列,字段)
-- 学号int 登录密码varchar(20) 姓名,性别varchar(2),出生日期(datatime),家庭住址,email
-- 创建表之前 , 一定要先选择数据库

CREATE TABLE IF NOT EXISTS `student` (
`id` int(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
`name` varchar(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
`pwd` varchar(20) NOT NULL DEFAULT '123456' COMMENT '密码',
`sex` varchar(2) NOT NULL DEFAULT '男' COMMENT '性别',
`birthday` datetime DEFAULT NULL COMMENT '生日',
`address` varchar(100) DEFAULT NULL COMMENT '地址',
`email` varchar(50) DEFAULT NULL COMMENT '邮箱',
PRIMARY KEY (`id`)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8




```

### 修改数据库

> 修改表 ( ALTER TABLE )

修改表名 :ALTER TABLE 旧表名 RENAME AS 新表名

添加字段 : ALTER TABLE 表名 ADD字段名 列属性[属性]

修改字段 :

- ALTER TABLE 表名 MODIFY 字段名 列类型[属性]
- ALTER TABLE 表名 CHANGE 旧字段名 新字段名 列属性[属性]

删除字段 :  ALTER TABLE 表名 DROP 字段名



> 删除数据表

语法：DROP TABLE [IF EXISTS] 表名

- IF EXISTS为可选 , 判断是否存在该数据表
- 如删除不存在的数据表会抛出错误







```mysql
-- 创建外键的方式一 : 创建子表同时创建外键

-- 年级表 (id\年级名称)
CREATE TABLE `grade` (
`gradeid` INT(10) NOT NULL AUTO_INCREMENT COMMENT '年级ID',
`gradename` VARCHAR(50) NOT NULL COMMENT '年级名称',
PRIMARY KEY (`gradeid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

-- 学生信息表 (学号,姓名,性别,年级,手机,地址,出生日期,邮箱,身份证号)
CREATE TABLE `student` (
`studentno` INT(4) NOT NULL COMMENT '学号',
`studentname` VARCHAR(20) NOT NULL DEFAULT '匿名' COMMENT '姓名',
`sex` TINYINT(1) DEFAULT '1' COMMENT '性别',
`gradeid` INT(10) DEFAULT NULL COMMENT '年级',
`phoneNum` VARCHAR(50) NOT NULL COMMENT '手机',
`address` VARCHAR(255) DEFAULT NULL COMMENT '地址',
`borndate` DATETIME DEFAULT NULL COMMENT '生日',
`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
`idCard` VARCHAR(18) DEFAULT NULL COMMENT '身份证号',
PRIMARY KEY (`studentno`),
KEY `FK_gradeid` (`gradeid`),
CONSTRAINT `FK_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade` (`gradeid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8
```





# 3、MySQL数据管理

## 3.1、外键

> 方法一：在创建表的时候增加约束
>
> ```
> CREATE TABLE IF NOT EXISTS `student`
> (
> `id`INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
> `pwd` VARCHAR(30) NOT NULL DEFAULT '***' COMMENT'密码',
> `name` VARCHAR(30) NOT NULL  DEFAULT '张三' COMMENT '姓名',
> `sex` VARCHAR(50) NOT NULL DEFAULT '男' COMMENT '性别',
> `birthday` VARCHAR (20) NOT NULL COMMENT '生日',
> `address` VARCHAR(30) NOT NULL COMMENT'住址',
> `emill` VARCHAR (30) NOT NULL COMMENT'邮箱',
> `gradeid` INT(4) NOT NULL COMMENT '学生的年级',
> PRIMARY KEY(`id`),
> KEY `FK_gradeid`(`gradeid`) ,
> CONSTRAINT `FK_gradeid` FOREIGN KEY(`gradeid`) REFERENCES `grade`(`gradeid`)
> )ENGINE=INNODB DEFAULT CHARSET=utf8
> 
> ```
>
> 

方式二：创建表成功后再去添加

```
ALTER TABLE student
ADD CONSTRAINT `FK_gradeid` FOREIGN KEY(`gradeid`) REFERENCES `grade`(`gradeid`);


```



### 添加数据



> INSERT命令

**语法：**

```mysql
INSERT INTO 表名[(字段1,字段2,字段3,...)] VALUES('值1','值2','值3')
```

**注意 :** 

- 字段或值之间用英文逗号隔开 .
- ' 字段1,字段2...' 该部分可省略 , 但添加的值务必与表结构,数据列,顺序相对应,且数量一致 .
- 可同时插入多条数据 , values 后用英文逗号隔开 .

```mysql
-- 使用语句如何增加语句?
-- 语法 : INSERT INTO 表名[(字段1,字段2,字段3,...)] VALUES('值1','值2','值3')
INSERT INTO grade(gradename) VALUES ('大一');

-- 主键自增,那能否省略呢?
INSERT INTO grade VALUES ('大二');

-- 查询:INSERT INTO grade VALUE ('大二')错误代码：1136
Column count doesn`t match value count at row 1

-- 结论:'字段1,字段2...'该部分可省略 , 但添加的值务必与表结构,数据列,顺序相对应,且数量一致.

-- 一次插入多条数据
INSERT INTO grade(gradename) VALUES ('大三'),('大四');
```





### 修改数据

> update命令

语法：

```mysql
UPDATE 表名 SET column_name=value [,column_name2=value2,...] [WHERE condition];
```

**注意 :** 

- column_name 为要更改的数据列
- value 为修改后的数据 , 可以为变量 , 具体指 , 表达式或者嵌套的SELECT结果
- condition 为筛选条件 , 如不指定则修改该表的所有列数据



> where条件子句

可以简单的理解为 : 有条件地从表中筛选数据

![图片](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7LCI6xGKJ7bKiaBudOSBHd9dyJWPxp3H9GicphPXMEvCwtUyKX3vibUCESqSaDnKnLzlwYpcRTJsdUIg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)测试：

```mysql
-- 修改年级信息
UPDATE grade SET gradename = '高中' WHERE gradeid = 1;
```





### 删除数据

> DELETE命令

语法：

```
DELETE FROM 表名 [WHERE condition];
```

注意：condition为筛选条件 , 如不指定则删除该表的所有列数据

```mysql
-- 删除最后一个数据
DELETE FROM grade WHERE gradeid = 5
```



> TRUNCATE命令

作用：用于完全清空表数据 , 但表结构 , 索引 , 约束等不变 ;

语法：

```mysql
TRUNCATE [TABLE] table_name;

-- 清空年级表
TRUNCATE grade
```

**注意：区别于DELETE命令**

- 相同 : 都能删除数据 , 不删除表结构 , 但TRUNCATE速度更快

- 不同 :

- - 使用TRUNCATE TABLE 重新设置AUTO_INCREMENT计数器
  - 使用TRUNCATE TABLE不会对事务有影响 （事务后面会说）







> SELECT语法

```mysql
SELECT [ALL | DISTINCT]
{* | table.* | [table.field1[as alias1][,table.field2[as alias2]][,...]]}
FROM table_name [as table_alias]
  [left | right | inner join table_name2]  -- 联合查询
  [WHERE ...]  -- 指定结果需满足的条件
  [GROUP BY ...]  -- 指定结果按照哪几个字段来分组
  [HAVING]  -- 过滤分组的记录必须满足的次要条件
  [ORDER BY ...]  -- 指定查询记录按一个或多个条件排序
  [LIMIT {[offset,]row_count | row_countOFFSET offset}];
   -- 指定查询的记录从哪条至哪条
```

**注意 : [ ] 括号代表可选的 , { }括号代表必选得**



### 指定查询字段

```mysql
-- 查询表中所有的数据列结果 , 采用 **" \* "** 符号; 但是效率低，不推荐 .

-- 查询所有学生信息
SELECT * FROM student;

-- 查询指定列(学号 , 姓名)
SELECT studentno,studentname FROM student;
```



> AS 子句作为别名

作用：

- 可给数据列取一个新别名
- 可给表取一个新别名
- 可把经计算或总结的结果用另一个新名称来代替

```mysql
-- 这里是为列取别名(当然as关键词可以省略)
SELECT studentno AS 学号,studentname AS 姓名 FROM student;

-- 使用as也可以为表取别名
SELECT studentno AS 学号,studentname AS 姓名 FROM student AS s;

-- 使用as,为查询结果取一个新名字
-- CONCAT()函数拼接字符串
SELECT CONCAT('姓名:',studentname) AS 新姓名 FROM student;
```



> DISTINCT关键字的使用

作用 : 去掉SELECT查询返回的记录结果中重复的记录 ( 返回所有列的值都相同 ) , 只返回一条

```mysql
-- # 查看哪些同学参加了考试(学号) 去除重复项
SELECT * FROM result; -- 查看考试成绩
SELECT studentno FROM result; -- 查看哪些同学参加了考试
SELECT DISTINCT studentno FROM result; -- 了解:DISTINCT 去除重复项 , (默认是ALL)

```





```mysql
-- 查询考试成绩在95-100之间的
SELECT Studentno,StudentResult
FROM result
WHERE StudentResult>=95 AND StudentResult<=100;

-- AND也可以写成 &&
SELECT Studentno,StudentResult
FROM result
WHERE StudentResult>=95 && StudentResult<=100;

-- 模糊查询(对应的词:精确查询)
SELECT Studentno,StudentResult
FROM result
WHERE StudentResult BETWEEN 95 AND 100;

-- 除了1000号同学,要其他同学的成绩
SELECT studentno,studentresult
FROM result
WHERE studentno!=1000;

-- 使用NOT
SELECT studentno,studentresult
FROM result
WHERE NOT studentno=1000;
```





> 模糊查询 ：比较操作符

![图片](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7LwfjFbCQXic0pcE21lUFGvDk8xl58oP6ch67ZibicU1fn2O7Lk4uLZyiaG8p8Zhkl4oF1GUibbPF0iaxIQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

注意：

- 数值数据类型的记录之间才能进行算术运算 ;
- 相同数据类型的数据之间才能进行比较 ;



测试：

```mysql
-- 模糊查询 between and \ like \ in \ null

-- =============================================
-- LIKE
-- =============================================
-- 查询姓刘的同学的学号及姓名
-- like结合使用的通配符 : % (代表0到任意个字符) _ (一个字符)
SELECT studentno,studentname FROM student
WHERE studentname LIKE '刘%';

-- 查询姓刘的同学,后面只有一个字的
SELECT studentno,studentname FROM student
WHERE studentname LIKE '刘_';

-- 查询姓刘的同学,后面只有两个字的
SELECT studentno,studentname FROM student
WHERE studentname LIKE '刘__';

-- 查询姓名中含有 嘉 字的
SELECT studentno,studentname FROM student
WHERE studentname LIKE '%嘉%';

-- 查询姓名中含有特殊字符的需要使用转义符号 '\'
-- 自定义转义符关键字: ESCAPE ':'

-- =============================================
-- IN
-- =============================================
-- 查询学号为1000,1001,1002的学生姓名
SELECT studentno,studentname FROM student
WHERE studentno IN (1000,1001,1002);

-- 查询地址在北京,南京,河南洛阳的学生
SELECT studentno,studentname,address FROM student
WHERE address IN ('北京','南京','河南洛阳');

-- =============================================
-- NULL 空
-- =============================================
-- 查询出生日期没有填写的同学
-- 不能直接写=NULL , 这是代表错误的 , 用 is null
SELECT studentname FROM student
WHERE BornDate IS NULL;

-- 查询出生日期填写的同学
SELECT studentname FROM student
WHERE BornDate IS NOT NULL;

-- 查询没有写家庭住址的同学(空字符串不等于null)
SELECT studentname FROM student
WHERE Address='' OR Address IS NULL;
```





### 连接查询

> JOIN 对比

![图片](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7LwfjFbCQXic0pcE21lUFGvDowQf1HHaYIicELYKnU9kDeaFHnfx0GYW6AsEwoTySywn91ia8Wz2sXiaA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

七种Join：

![图片](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7LwfjFbCQXic0pcE21lUFGvDw5aZLehIYzwLprCfqdxSjsm2wficHrSEzJiaJBGaKWpatQ7sISib9MgCQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

测试

```mysql
/*
连接查询
   如需要多张数据表的数据进行查询,则可通过连接运算符实现多个查询
内连接 inner join
   查询两个表中的结果集中的交集
外连接 outer join
   左外连接 left join
       (以左表作为基准,右边表来一一匹配,匹配不上的,返回左表的记录,右表以NULL填充)
   右外连接 right join
       (以右表作为基准,左边表来一一匹配,匹配不上的,返回右表的记录,左表以NULL填充)
       
等值连接和非等值连接

自连接
*/

-- 查询参加了考试的同学信息(学号,学生姓名,科目编号,分数)
SELECT * FROM student;
SELECT * FROM result;

/*思路:
(1):分析需求,确定查询的列来源于两个类,student result,连接查询
(2):确定使用哪种连接查询?(内连接)
*/
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno

-- 右连接(也可实现)
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
RIGHT JOIN result r
ON r.studentno = s.studentno

-- 等值连接
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s , result r
WHERE r.studentno = s.studentno

-- 左连接 (查询了所有同学,不考试的也会查出来)
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
LEFT JOIN result r
ON r.studentno = s.studentno

-- 查一下缺考的同学(左连接应用场景)
SELECT s.studentno,studentname,subjectno,StudentResult
FROM student s
LEFT JOIN result r
ON r.studentno = s.studentno
WHERE StudentResult IS NULL

-- 思考题:查询参加了考试的同学信息(学号,学生姓名,科目名,分数)
SELECT s.studentno,studentname,subjectname,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON sub.subjectno = r.subjectno
```





### 排序和分页

测试

```mysql
/*============== 排序 ================
语法 : ORDER BY
   ORDER BY 语句用于根据指定的列对结果集进行排序。
   ORDER BY 语句默认按照ASC升序对记录进行排序。
   如果您希望按照降序对记录进行排序，可以使用 DESC 关键字。
   
*/

-- 查询 数据库结构-1 的所有考试结果(学号 学生姓名 科目名称 成绩)
-- 按成绩降序排序
SELECT s.studentno,studentname,subjectname,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
WHERE subjectname='数据库结构-1'
ORDER BY StudentResult DESC

/*============== 分页 ================
语法 : SELECT * FROM table LIMIT [offset,] rows | rows OFFSET offset
好处 : (用户体验,网络传输,查询压力)

推导:
   第一页 : limit 0,5
   第二页 : limit 5,5
   第三页 : limit 10,5
   ......
   第N页 : limit (pageNo-1)*pageSzie,pageSzie
   [pageNo:页码,pageSize:单页面显示条数]
   
*/

-- 每页显示5条数据
SELECT s.studentno,studentname,subjectname,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
WHERE subjectname='数据库结构-1'
ORDER BY StudentResult DESC , studentno
LIMIT 0,5

-- 查询 JAVA第一学年 课程成绩前10名并且分数大于80的学生信息(学号,姓名,课程名,分数)
SELECT s.studentno,studentname,subjectname,StudentResult
FROM student s
INNER JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
WHERE subjectname='JAVA第一学年'
ORDER BY StudentResult DESC
LIMIT 0,10
```

## 5.2、聚合函数

```
SELECT COUNT(`name`)FROM `tykdk`

SELECT SUM(`9`)AS '总和' FROM `tykdk`

SELECT AVG (`9`)AS '平均分'FROM `tykdk`

SELECT MAX(`9`)AS '最高分'FROM `tykdk`

SELECT MIN(`9`) AS '最低分'FROM `tykdk`


```



## 5.3、数据库级别的MD5加密

MD5不可逆，具体的MD5密码值是一样的

MD5破解网站的原理，背后有一个字典

```
UPDATE `testmd5` SET `pwd`='123456',`name`='张三' WHERE id=1

INSERT INTO `testmd5` (`pwd`,`name`) VALUES ('123457','李四'),('1234557','王五')

UPDATE `testmd5` SET `pwd`=MD5(pwd) WHERE id=1

INSERT INTO `testmd5`  VALUES ('4',MD5('123456'),'xm')

```



## 事务

> 什么是事务

- 事务就是将一组SQL语句放在同一批次内去执行
- 如果一个SQL语句出错,则该批次内的所有SQL都将被取消执行
- MySQL事务处理只支持InnoDB和BDB数据表类型

> 事务的ACID原则  百度 ACID

**原子性(Atomic)**

- 整个事务中的所有操作，要么全部完成，要么全部不完成，不可能停滞在中间某个环节。事务在执行过程中发生错误，会被回滚（ROLLBACK）到事务开始前的状态，就像这个事务从来没有执行过一样。

**一致性(Consist)**

- 一个事务可以封装状态改变（除非它是一个只读的）。事务必须始终保持系统处于一致的状态，不管在任何给定的时间并发事务有多少。也就是说：如果事务是并发多个，系统也必须如同串行事务一样操作。其主要特征是保护性和不变性(Preserving an Invariant)，以转账案例为例，假设有五个账户，每个账户余额是100元，那么五个账户总额是500元，如果在这个5个账户之间同时发生多个转账，无论并发多少个，比如在A与B账户之间转账5元，在C与D账户之间转账10元，在B与E之间转账15元，五个账户总额也应该还是500元，这就是保护性和不变性。

**隔离性(Isolated)**

- 隔离状态执行事务，使它们好像是系统在给定时间内执行的唯一操作。如果有两个事务，运行在相同的时间内，执行相同的功能，事务的隔离性将确保每一事务在系统中认为只有该事务在使用系统。这种属性有时称为串行化，为了防止事务操作间的混淆，必须串行化或序列化请求，使得在同一时间仅有一个请求用于同一数据。

**持久性(Durable)**

- 在事务完成以后，该事务对数据库所作的更改便持久的保存在数据库之中，并不会被回滚。





```mysql
-- 使用set语句来改变自动提交模式
SET autocommit = 0;   /*关闭*/
SET autocommit = 1;   /*开启*/

-- 注意:
--- 1.MySQL中默认是自动提交
--- 2.使用事务时应先关闭自动提交

-- 开始一个事务,标记事务的起始点
START TRANSACTION  

-- 提交一个事务给数据库
COMMIT

-- 将事务回滚,数据回到本次事务的初始状态
ROLLBACK

-- 还原MySQL数据库的自动提交
SET autocommit =1;

-- 保存点
SAVEPOINT 保存点名称 -- 设置一个事务保存点
ROLLBACK TO SAVEPOINT 保存点名称 -- 回滚到保存点
RELEASE SAVEPOINT 保存点名称 -- 删除保存点
```





## 索引

> 索引的作用

- 提高查询速度
- 确保数据的唯一性
- 可以加速表和表之间的连接 , 实现表与表之间的参照完整性
- 使用分组和排序子句进行数据检索时 , 可以显著减少分组和排序的时间
- 全文检索字段进行搜索优化.

> 分类

- 主键索引 (Primary Key)
- 唯一索引 (Unique)
- 常规索引 (Index)
- 全文索引 (FullText)

> 主键索引

主键 : 某一个属性组能唯一标识一条记录

特点 :

- 最常见的索引类型
- 确保数据记录的唯一性
- 确定特定数据记录在数据库中的位置

> 唯一索引

作用 : 避免同一个表中某数据列中的值重复

与主键索引的区别

- 主键索引只能有一个
- 唯一索引可能有多个

```
CREATE TABLE `Grade`(
  `GradeID` INT(11) AUTO_INCREMENT PRIMARYKEY,
  `GradeName` VARCHAR(32) NOT NULL UNIQUE
   -- 或 UNIQUE KEY `GradeID` (`GradeID`)
)
```

> 常规索引

作用 : 快速定位特定数据

注意 :

- index 和 key 关键字都可以设置常规索引
- 应加在查询找条件的字段
- 不宜添加太多常规索引,影响数据的插入,删除和修改操作

```
CREATE TABLE `result`(
   -- 省略一些代码
  INDEX/KEY `ind` (`studentNo`,`subjectNo`) -- 创建表时添加
)
-- 创建后添加
ALTER TABLE `result` ADD INDEX `ind`(`studentNo`,`subjectNo`);
```

> 全文索引

百度搜索：全文索引

作用 : 快速定位特定数据

注意 :

- 只能用于MyISAM类型的数据表
- 只能用于CHAR , VARCHAR , TEXT数据列类型
- 适合大型数据集

```mysql
/*
#方法一：创建表时
  　　CREATE TABLE 表名 (
               字段名1 数据类型 [完整性约束条件…],
               字段名2 数据类型 [完整性约束条件…],
               [UNIQUE | FULLTEXT | SPATIAL ]   INDEX | KEY
               [索引名] (字段名[(长度)] [ASC |DESC])
               );


#方法二：CREATE在已存在的表上创建索引
       CREATE [UNIQUE | FULLTEXT | SPATIAL ] INDEX 索引名
                    ON 表名 (字段名[(长度)] [ASC |DESC]) ;


#方法三：ALTER TABLE在已存在的表上创建索引
       ALTER TABLE 表名 ADD [UNIQUE | FULLTEXT | SPATIAL ] INDEX
                            索引名 (字段名[(长度)] [ASC |DESC]) ;
                           
                           
#删除索引：DROP INDEX 索引名 ON 表名字;
#删除主键索引: ALTER TABLE 表名 DROP PRIMARY KEY;


#显示索引信息: SHOW INDEX FROM student;
*/

/*增加全文索引*/
ALTER TABLE `school`.`student` ADD FULLTEXT INDEX `studentname` (`StudentName`);

/*EXPLAIN : 分析SQL语句执行性能*/
EXPLAIN SELECT * FROM student WHERE studentno='1000';

/*使用全文索引*/
-- 全文搜索通过 MATCH() 函数完成。
-- 搜索字符串作为 against() 的参数被给定。搜索以忽略字母大小写的方式执行。对于表中的每个记录行，MATCH() 返回一个相关性值。即，在搜索字符串与记录行在 MATCH() 列表中指定的列的文本之间的相似性尺度。
EXPLAIN SELECT *FROM student WHERE MATCH(studentname) AGAINST('love');

/*
开始之前，先说一下全文索引的版本、存储引擎、数据类型的支持情况

MySQL 5.6 以前的版本，只有 MyISAM 存储引擎支持全文索引；
MySQL 5.6 及以后的版本，MyISAM 和 InnoDB 存储引擎均支持全文索引;
只有字段的数据类型为 char、varchar、text 及其系列才可以建全文索引。
测试或使用全文索引时，要先看一下自己的 MySQL 版本、存储引擎和数据类型是否支持全文索引。
*/
```

```mysql
-- 我们可以在创建上述索引的时候，为其指定索引类型，分两类
hash类型的索引：查询单条快，范围查询慢
btree类型的索引：b+树，层数越多，数据量指数级增长（我们就用它，因为innodb默认支持它）
```

# 8、数据库的备份与权限

## 8.1、用户管理

> SQLyog可视化管理

用户表：mysql.user

```
---创建用户
CREATE USER kuangshen IDENTIFIED BY '123456'
---修改密码（当前账户）
SET PASSWORD =PASSWORD('123456')
---修改密码（指定账户）
SET PASSWORD FOR kuangsheng =PASSWORD('123456')

---用户授权
GRANT ALL PRIVLEGES PN*.* TO kuangshen

--查看权限
SHOW GRANTS FOE kuangshen

--撤销权限
REOMVE ALL PRIVILEGES OM*.* FROM kuangshen

```

## 8.2、数据库备份

为什么要备份

- 保证数据不丢失
- 数据转移

MySQL数据库备份的方式

- 直接拷贝物理文件
- sqlyog可视化工具
- 命令行导出 mysqldump 命令行使用

```
--musqldummp -h表名 -u用户名 -p密码 数据库 数据库表1 数据库表2.... >导入位置
--导出
mysqldump -hlocalhost -uroot -pehero000921 daka tykdk >D:a.sql

--导入
--登录的情况下，切换到指定的数据库

--导入，登录的情况下
source D:/文件.sql

--没有登录的情况

mysql -u用户 -p密码  数据库名字<备份文件

```

备份数据库防止数据丢失

把数据库给朋友，sql文件给比人即可！

9、规范数据库设计
9.1、为什么要设计
当数据库比较复杂的时候就要设计

糟糕的数据库设计：

数据冗长，浪费空间
数据库插入和删除都会麻烦，异常
程序性能差
良好的数据库设计

节省空间
保证完整性
方便开发
软件开发中，关于数据库的设计

分析需求
概要设计
设计数据库的步骤

收集信息，分析需求

用户表（用户登录注销，用户的个人信息，写博客，创建分类）

分类表（文章分类，谁创建的）

文章表（文章的信息）

评论表

友情链接（信息）

自定义表（系统信息，某个关键字）

说说表（发表心情。。。。id）

标识实体（把需求落地到每个字段）

标识实体之间的关系

user ->blog

user->category

关注：user->user

友链：links

评论：user->user-blog

9.2、规范数据库设计
为什么要有范式

信息重复

更新异常

插入异常

无法正常显示信息

删除异常

丢失有效的信息

三大范式
————————————————三大范式

**第一范式**

原子性：保证每一列不可再分

**第二范范式**

前提：满足第一范式的前提下，每张表只表示一个信息

**第三范式**

前提：满足第一第二范式

确保数据表的每一列数据都和主键直接相关，而不能见解相关
![在这里插入图片描述](https://img-blog.csdnimg.cn/2021051811471539.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzE0NTUzOQ==,size_16,color_FFFFFF,t_70)

# 

