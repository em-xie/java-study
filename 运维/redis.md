# redis学习笔记

redis-server

redis-cli

shutdown

![img](https://img-blog.csdnimg.cn/2021033018571815.png)

![img](https://img-blog.csdnimg.cn/20210330185827533.png)

一、Nosql概述
1、单机Mysql时代
90年代,一个网站的访问量一般不会太大，单个数据库完全够用。随着用户增多，网站出现以下问题：

数据量增加到一定程度，单机数据库就放不下了
数据的索引（B+ Tree）,一个机器内存也存放不下
访问量变大后（读写混合），一台服务器承受不住。

![img](https://img-blog.csdnimg.cn/20201208150050825.png)





2、Memcached(缓存) + Mysql + 垂直拆分（读写分离）
网站80%的情况都是在读，每次都要去查询数据库的话就十分的麻烦！所以说我们希望减轻数据库的压力，我们可以使用缓存来保证效率！

![img](https://img-blog.csdnimg.cn/20201208150104786.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgwMzc2MA==,size_16,color_FFFFFF,t_70)





优化过程经历了以下几个过程：

优化数据库的数据结构和索引(难度大)
文件缓存，通过IO流获取比每次都访问数据库效率略高，但是流量爆炸式增长时候，IO流也承受不了
MemCache,当时最热门的技术，通过在数据库和数据库访问层之间加上一层缓存，第一次访问时查询数据库，将结果保存到缓存，后续的查询先检查缓存，若有直接拿去使用，效率显著提升。

3、分库分表 + 水平拆分 + Mysql集群



![img](https://img-blog.csdnimg.cn/20201208150121750.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgwMzc2MA==,size_16,color_FFFFFF,t_70)






4、如今最近的年代
如今信息量井喷式增长，各种各样的数据出现（用户定位数据，图片数据等），大数据的背景下关系型数据库（RDBMS）无法满足大量数据要求。Nosql数据库就能轻松解决这些问题。目前一个基本的互联网项目：

![img](https://img-blog.csdnimg.cn/20201208150205982.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgwMzc2MA==,size_16,color_FFFFFF,t_70)





5、为什么要用NoSQL ？
用户的个人信息，社交网络，地理位置。用户自己产生的数据，用户日志等等爆发式增长！这时候我们就需要使用NoSQL数据库的，Nosql可以很好的处理以上的情况！

什么是Nosql
NoSQL = Not Only SQL（不仅仅是SQL）

Not Only Structured Query Language

关系型数据库：列+行，同一个表下数据的结构是一样的。

非关系型数据库：数据存储没有固定的格式，并且可以进行横向扩展。

NoSQL泛指非关系型数据库，随着web2.0互联网的诞生，传统的关系型数据库很难对付web2.0时代！尤其是超大规模的高并发的社区，暴露出来很多难以克服的问题，NoSQL在当今大数据环境下发展的十分迅速，Redis是发展最快的。

Nosql特点
1.方便扩展（数据之间没有关系，很好扩展！）

2.大数据量高性能（Redis一秒可以写8万次，读11万次，NoSQL的缓存记录级，是一种细粒度的缓存，性能会比较高！）

3.数据类型是多样型的！（不需要事先设计数据库，随取随用）

4.传统的 RDBMS 和 NoSQL

传统的 RDBMS(关系型数据库)

结构化组织
SQL
数据和关系都存在单独的表中 row col
操作，数据定义语言
严格的一致性
基础的事务
Nosql

不仅仅是数据
没有固定的查询语言
键值对存储，列存储，文档存储，图形数据库（社交关系）
最终一致性
CAP定理和BASE
高性能，高可用，高扩展
5.大数据时代的3V ：主要是描述问题的

海量Velume

多样Variety

实时Velocity

6.大数据时代的3高 ： 主要是对程序的要求

高并发

高可扩

高性能

真正在公司中的实践：NoSQL + RDBMS 一起使用才是最强的。

### 二、Redis入门

#### Redis是什么？

Redis（Remote Dictionary Server )，即远程字典服务。

是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。

与memcached一样，为了保证效率，数据都是缓存在内存中。区别的是redis会周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件，并且在此基础上实现了master-slave(主从)同步。

#### Redis能干什么？

内存存储、持久化，内存是断电即失的，所以需要持久化（RDB、AOF）
高效率、用于高速缓冲
发布订阅系统
地图信息分析
计时器、计数器(eg：浏览量)
。。。
特性
多样的数据类型
持久化
集群
事务
…
环境搭建（略）
性能测试
redis-benchmark：Redis官方提供的性能测试工具，参数选项如下：


![img](https://img-blog.csdnimg.cn/20201208150712931.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgwMzc2MA==,size_16,color_FFFFFF,t_70)

简单测试：

#### 测试：100个并发连接 100000请求
redis-benchmark -h localhost -p 6379 -c 100 -n 100000

结果：

![img](https://img-blog.csdnimg.cn/20201208150810502.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgwMzc2MA==,size_16,color_FFFFFF,t_70)

基础知识
redis默认有16个数据库

![img](https://img-blog.csdnimg.cn/2020120815082078.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgwMzc2MA==,size_16,color_FFFFFF,t_70)

默认使用的第0个;

16个数据库为：DB 0~DB 15 默认使用DB 0 ，可以使用select n切换到DB n，dbsize可以查看当前数据库的大小，与key数量相关。

127.0.0.1:6379> config get databases # 命令行查看数据库数量databases
1) "databases"
2) "16"
127.0.0.1:6379> select 8 # 切换数据库 DB 8
OK
127.0.0.1:6379[8]> dbsize # 查看数据库大小
(integer) 0
**不同数据库之间 数据是不能互通的，并且dbsize 是根据库中key的个数。**
127.0.0.1:6379> set name sakura
OK
127.0.0.1:6379> SELECT 8
OK
127.0.0.1:6379[8]> get name # db8中并不能获取db0中的键值对。
(nil)
127.0.0.1:6379[8]> DBSIZE
(integer) 0
127.0.0.1:6379[8]> SELECT 0
OK
127.0.0.1:6379> keys *
"counter:rand_int"
"mylist"
"name"
"key:rand_int"
"myset:rand_int"
127.0.0.1:6379> DBSIZE # size和key个数相关
(integer) 5

**keys ** ：查看当前数据库中所有的key。

flushdb：清空当前数据库中的键值对。

flushall：清空所有数据库的键值对。

Redis是单线程的，Redis是基于内存操作的。

所以Redis的性能瓶颈不是CPU,而是机器内存和网络带宽。

那么为什么Redis的速度如此快呢，性能这么高呢？QPS达到10W+

#### Redis为什么单线程还这么快？

误区1：高性能的服务器一定是多线程的？

误区2：多线程（CPU上下文会切换！）一定比单线程效率高！

核心：Redis是将所有的数据放在内存中的，所以说使用单线程去操作效率就是最高的，多线程（CPU上下文会切换：耗时的操作！），对于内存系统来说，如果没有上下文切换效率就是最高的，多次读写都是在一个CPU上的，在内存存储数据情况下，单线程就是最佳的方案。



![img](https://img-blog.csdnimg.cn/20201208150712931.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgwMzc2MA==,size_16,color_FFFFFF,t_70)



#### 三、五大数据类型

Redis是一个开源（BSD许可），内存存储的数据结构服务器，可用作数据库，高速缓存和消息队列代理。它支持字符串、哈希表、列表、集合、有序集合，位图，hyperloglogs等数据类型。内置复制、Lua脚本、LRU收回、事务以及不同级别磁盘持久化功能，同时通过Redis Sentinel提供高可用，通过Redis Cluster提供自动分区。

Redis-key
在redis中无论什么数据类型，在数据库中都是以key-value形式保存，通过进行对Redis-key的操作，来完成对数据库中数据的操作。

下面学习的命令：

exists key：判断键是否存在
del key：删除键值对
move key db：将键值对移动到指定数据库
expire key second：设置键值对的过期时间
type key：查看value的数据类型



127.0.0.1:6379> keys * # 查看当前数据库所有key
(empty list or set)
127.0.0.1:6379> set name qinjiang # set key
OK
127.0.0.1:6379> set age 20
OK
127.0.0.1:6379> keys *
1) "age"
2) "name"
127.0.0.1:6379> move age 1 # 将键值对移动到指定数据库
(integer) 1
127.0.0.1:6379> EXISTS age # 判断键是否存在
(integer) 0 # 不存在
127.0.0.1:6379> EXISTS name
(integer) 1 # 存在
127.0.0.1:6379> SELECT 1
OK
127.0.0.1:6379[1]> keys *
1) "age"
127.0.0.1:6379[1]> del age # 删除键值对
(integer) 1 # 删除个数
127.0.0.1:6379> set age 20

OK

127.0.0.1:6379> EXPIRE age 15 # 设置键值对的过期时间


(integer) 1 # 设置成功 开始计数

127.0.0.1:6379> ttl age # 查看key的过期剩余时间

(integer) 13

127.0.0.1:6379> ttl age

(integer) 11

127.0.0.1:6379> ttl age

(integer) 9

127.0.0.1:6379> ttl age

(integer) -2 # -2 表示key过期，-1表示key未设置过期时间


127.0.0.1:6379> get age # 过期的key 会被自动delete

(nil)

127.0.0.1:6379> keys *



"name"


127.0.0.1:6379> type name # 查看value的数据类型

string


#### 关于TTL命令

Redis的key，通过TTL命令返回key的过期时间，一般来说有3种：

1. 当前key没有设置过期时间，所以会返回-1.
2. 当前key有设置过期时间，而且key已经过期，所以会返回-2.
3. 当前key有设置过期时间，且key还没有过期，故会返回key的正常剩余时间.

**关于重命名RENAME和RENAMENX**

1. RENAME key newkey修改 key 的名称

2. RENAMENX key newkey仅当 newkey 不存在时，将 key 改名为 newkey 。

   

**String(字符串)**
普通的set、get直接略过。

常用命令及其示例：

**APPEND key value**: 向指定的key的value后追加字符串



127.0.0.1:6379> set msg hello  

OK  

127.0.0.1:6379> append msg " world" 

 (integer) 11

127.0.0.1:6379> get msg  

“hello world”



**DECR/INCR key**: 将指定key的value数值进行+1/-1(仅对于数字)

127.0.0.1:6379> set age 20 

 OK 

 127.0.0.1:6379> incr age  

(integer) 21  

127.0.0.1:6379> decr age  

(integer) 20



**INCRBY/DECRBY key n**: 按指定的步长对数值进行加减

127.0.0.1:6379> INCRBY age 5 

(integer) 25  

127.0.0.1:6379> DECRBY age 10 

 (integer) 15





**INCRBYFLOAT key n**: 为数值加上浮点型数值

127.0.0.1:6379> INCRBYFLOAT age 5.2 

 “20.2”

**STRLEN key**: 获取key保存值的字符串长度

127.0.0.1:6379> get msg

  “hello world”  

127.0.0.1:6379> STRLEN msg 

 (integer) 11



`GETRANGE key start end`: 按起止位置获取字符串（闭区间，起止位置都取）

127.0.0.1:6379> get msg

  “hello world”  

127.0.0.1:6379> GETRANGE msg 

3 9  “lo worl”



`SETRANGE key offset value`:用指定的value 替换key中 offset开始的值

127.0.0.1:6379> set msg hello
OK
127.0.0.1:6379> setrange msg 2 hello
(integer) 7
127.0.0.1:6379> get msg
"hehello"
127.0.0.1:6379> set msg2 world
OK
127.0.0.1:6379> setrange msg2 2 ww
(integer) 5
127.0.0.1:6379> get msg2
"wowwd"

GETSET key value: 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。

127.0.0.1:6379> GETSET msg test 
“hello world”
1
2
SETNX key value: 仅当key不存在时进行set

127.0.0.1:6379> SETNX msg test 
(integer) 0 
127.0.0.1:6379> SETNX name sakura 
(integer) 1
1
2
3
4
SETEX key seconds value: set 键值对并设置过期时间

127.0.0.1:6379> setex name 10 root 
OK 
127.0.0.1:6379> get name 
(nil)
1
2
3
4
MSET key1 value1 [key2 value2..]: 批量set键值对

127.0.0.1:6379> MSET k1 v1 k2 v2 k3 v3 
OK
1
2
MSETNX key1 value1 [key2 value2..]: 批量设置键值对，仅当参数中所有的key都不存在时执行

127.0.0.1:6379> MSETNX k1 v1 k4 v4 
(integer) 0
1
2
MGET key1 [key2..]: 批量获取多个key保存的值

127.0.0.1:6379> MGET k1 k2 k3 
1) “v1” 
2) “v2” 
3) “v3”





PSETEX key milliseconds value: 和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间

String类似的使用场景：value除了是字符串还可以是数字，用途举例：

计数器
统计多单位的数量：uid:123666：follow 0
粉丝数
对象存储缓存



List(列表)
Redis列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）

一个列表最多可以包含 232 - 1 个元素 (4294967295, 每个列表超过40亿个元素)。

首先我们列表，可以经过规则定义将其变为队列、栈、双端队列等。
![img](https://img-blog.csdnimg.cn/2020120815213183.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTgwMzc2MA==,size_16,color_FFFFFF,t_70)

正如图Redis中List是可以进行双端操作的，所以命令也就分为了LXXX和RLLL两类，有时候L也表示List例如LLEN

LPUSH/RPUSH key value1[value2..]从左边/右边向列表中PUSH值(一个或者多个)。
LRANGE key start end 获取list 起止元素==（索引从左往右 递增）==
LPUSHX/RPUSHX key value 向已存在的列名中push值（一个或者多个）
LINSERT key BEFORE|AFTER pivot value 在指定列表元素的前/后 插入value
LLEN key 查看列表长度
LINDEX key index 通过索引获取列表元素
LSET key index value 通过索引为元素设值
LPOP/RPOP key 从最左边/最右边移除值 并返回
RPOPLPUSH source destination 将列表的尾部(右)最后一个值弹出，并返回，然后加到另一个列表的头部
LTRIM key start end 通过下标截取指定范围内的列表
LREM key count value List中是允许value重复的 count >0：从头部开始搜索 然后删除指定的value 至多删除count个 count < 0：从尾部开始搜索… count = 0：删除列表中所有的指定value。
BLPOP/BRPOP key1[key2] timout 移出并获取列表的第一个/最后一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
BRPOPLPUSH source destination timeout 和RPOPLPUSH功能相同，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。

---------------------------LPUSH---RPUSH---LRANGE--------------------------------
127.0.0.1:6379> LPUSH mylist k1 # LPUSH mylist=>{1}

(integer) 1

127.0.0.1:6379> LPUSH mylist k2 # LPUSH mylist=>{2,1}

(integer) 2

127.0.0.1:6379> RPUSH mylist k3 # RPUSH mylist=>{2,1,3}

(integer) 3

127.0.0.1:6379> get mylist # 普通的get是无法获取list值的

(error) WRONGTYPE Operation against a key holding the wrong kind of value

127.0.0.1:6379> LRANGE mylist 0 4 # LRANGE 获取起止位置范围内的元素



"k2"

"k1"

"k3"

127.0.0.1:6379> LRANGE mylist 0 2

"k2"

"k1"

"k3"

127.0.0.1:6379> LRANGE mylist 0 1

"k2"

"k1"

127.0.0.1:6379> LRANGE mylist 0 -1 # 获取全部元素

"k2"

"k1"

"k3"


---------------------------LPUSHX---RPUSHX-----------------------------------


127.0.0.1:6379> LPUSHX list v1 # list不存在 LPUSHX失败

(integer) 0

127.0.0.1:6379> LPUSHX list v1 v2

(integer) 0

127.0.0.1:6379> LPUSHX mylist k4 k5 # 向mylist中 左边 PUSH k4 k5

(integer) 5

127.0.0.1:6379> LRANGE mylist 0 -1



"k5"

"k4"

"k2"

"k1"

"k3"


---------------------------LINSERT--LLEN--LINDEX--LSET----------------------------


127.0.0.1:6379> LINSERT mylist after k2 ins_key1 # 在k2元素后 插入ins_key1

(integer) 6

127.0.0.1:6379> LRANGE mylist 0 -1



"k5"

"k4"

"k2"

"ins_key1"

"k1"

"k3"

127.0.0.1:6379> LLEN mylist # 查看mylist的长度

(integer) 6

127.0.0.1:6379> LINDEX mylist 3 # 获取下标为3的元素

"ins_key1"

127.0.0.1:6379> LINDEX mylist 0

"k5"

127.0.0.1:6379> LSET mylist 3 k6 # 将下标3的元素 set值为k6

OK

127.0.0.1:6379> LRANGE mylist 0 -1

"k5"

"k4"

"k2"

"k6"

"k1"

"k3"


---------------------------LPOP--RPOP--------------------------


127.0.0.1:6379> LPOP mylist # 左侧(头部)弹出

"k5"

127.0.0.1:6379> RPOP mylist # 右侧(尾部)弹出

"k3"


---------------------------RPOPLPUSH--------------------------


127.0.0.1:6379> LRANGE mylist 0 -1



"k4"

"k2"

"k6"

"k1"

127.0.0.1:6379> RPOPLPUSH mylist newlist # 将mylist的最后一个值(k1)弹出，加入到newlist的头部

"k1"

127.0.0.1:6379> LRANGE newlist 0 -1

"k1"

127.0.0.1:6379> LRANGE mylist 0 -1

"k4"

"k2"

"k6"


---------------------------LTRIM--------------------------


127.0.0.1:6379> LTRIM mylist 0 1 # 截取mylist中的 0~1部分

OK

127.0.0.1:6379> LRANGE mylist 0 -1



"k4"

"k2"


初始 mylist: k2,k2,k2,k2,k2,k2,k4,k2,k2,k2,k2

---------------------------LREM--------------------------


127.0.0.1:6379> LREM mylist 3 k2 # 从头部开始搜索 至多删除3个 k2

(integer) 3


删除后：mylist: k2,k2,k2,k4,k2,k2,k2,k2

127.0.0.1:6379> LREM mylist -2 k2 #从尾部开始搜索 至多删除2个 k2

(integer) 2


删除后：mylist: k2,k2,k2,k4,k2,k2

---------------------------BLPOP--BRPOP--------------------------


mylist: k2,k2,k2,k4,k2,k2

newlist: k1


127.0.0.1:6379> BLPOP newlist mylist 30 # 从newlist中弹出第一个值，mylist作为候选



"newlist" # 弹出

"k1"

127.0.0.1:6379> BLPOP newlist mylist 30

"mylist" # 由于newlist空了 从mylist中弹出

"k2"

127.0.0.1:6379> BLPOP newlist 30

(30.10s) # 超时了


127.0.0.1:6379> BLPOP newlist 30 # 我们连接另一个客户端向newlist中push了test, 阻塞被解决。



"newlist"

"test"

小结
list实际上是一个链表，before Node after , left, right 都可以插入值
如果key不存在，则创建新的链表
如果key存在，新增内容
如果移除了所有值，空链表，也代表不存在
在两边插入或者改动值，效率最高！修改中间元素，效率相对较低
应用：

消息排队！消息队列（Lpush Rpop）,栈（Lpush Lpop）







Set(集合）
Redis的Set是string类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。

Redis中集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。

集合中最大的成员数为 232 - 1 (4294967295, 每个集合可存储40多亿个成员)。

SADD key member1[member2..] 向集合中无序增加一个/多个成员
SCARD key 获取集合的成员数
SMEMBERS key 返回集合中所有的成员
SISMEMBER key member 查询member元素是否是集合的成员,结果是无序的
SRANDMEMBER key [count] 随机返回集合中count个成员，count缺省值为1
SPOP key [count] 随机移除并返回集合中count个成员，count缺省值为1
SMOVE source destination member 将source集合的成员member移动到destination集合
SREM key member1[member2..] 移除集合中一个/多个成员
SDIFF key1[key2..] 返回所有集合的差集 key1- key2 - …
SDIFFSTORE destination key1[key2..] 在SDIFF的基础上，将结果保存到集合中==(覆盖)==。不能保存到其他类型key噢！
SINTER key1 [key2..] 返回所有集合的交集
SINTERSTORE destination key1[key2..] 在SINTER的基础上，存储结果到集合中。覆盖
SUNION key1 [key2..] 返回所有集合的并集
SUNIONSTORE destination key1 [key2..] 在SUNION的基础上，存储结果到及和张。覆盖
SSCAN KEY [MATCH pattern] [COUNT count] 在大量数据环境下，使用此命令遍历集合中元素，每次遍历部分
代码示例

---------------SADD--SCARD--SMEMBERS--SISMEMBER--------------------
127.0.0.1:6379> SADD myset m1 m2 m3 m4 # 向myset中增加成员 m1~m4
(integer) 4
127.0.0.1:6379> SCARD myset # 获取集合的成员数目
(integer) 4
127.0.0.1:6379> smembers myset # 获取集合中所有成员
"m4"
"m3"
"m2"
"m1"
127.0.0.1:6379> SISMEMBER myset m5 # 查询m5是否是myset的成员
(integer) 0 # 不是，返回0
127.0.0.1:6379> SISMEMBER myset m2
(integer) 1 # 是，返回1
127.0.0.1:6379> SISMEMBER myset m3
(integer) 1
---------------------SRANDMEMBER--SPOP----------------------------------
127.0.0.1:6379> SRANDMEMBER myset 3 # 随机返回3个成员
"m2"
"m3"
"m4"
127.0.0.1:6379> SRANDMEMBER myset # 随机返回1个成员
"m3"
127.0.0.1:6379> SPOP myset 2 # 随机移除并返回2个成员
"m1"
"m4"
将set还原到{m1,m2,m3,m4}
---------------------SMOVE--SREM----------------------------------------
127.0.0.1:6379> SMOVE myset newset m3 # 将myset中m3成员移动到newset集合
(integer) 1
127.0.0.1:6379> SMEMBERS myset
"m4"
"m2"
"m1"
127.0.0.1:6379> SMEMBERS newset
"m3"
127.0.0.1:6379> SREM newset m3 # 从newset中移除m3元素
(integer) 1
127.0.0.1:6379> SMEMBERS newset
(empty list or set)
下面开始是多集合操作,多集合操作中若只有一个参数默认和自身进行运算
setx=>{m1,m2,m4,m6}, sety=>{m2,m5,m6}, setz=>{m1,m3,m6}

-----------------------------SDIFF------------------------------------
127.0.0.1:6379> SDIFF setx sety setz # 等价于setx-sety-setz
"m4"
127.0.0.1:6379> SDIFF setx sety # setx - sety
"m4"
"m1"
127.0.0.1:6379> SDIFF sety setx # sety - setx
"m5"
-------------------------SINTER---------------------------------------
共同关注（交集）
127.0.0.1:6379> SINTER setx sety setz # 求 setx、sety、setx的交集
"m6"
127.0.0.1:6379> SINTER setx sety # 求setx sety的交集
"m2"
"m6"
-------------------------SUNION---------------------------------------
127.0.0.1:6379> SUNION setx sety setz # setx sety setz的并集
"m4"
"m6"
"m3"
"m2"
"m1"
"m5"
127.0.0.1:6379> SUNION setx sety # setx sety 并集
"m4"
"m6"
"m2"
"m1"
"m5"

Hash（哈希）
Redis hash 是一个string类型的field和value的映射表，hash特别适合用于存储对象。

Set就是一种简化的Hash,只变动key,而value使用默认值填充。可以将一个Hash表作为一个对象进行存储，表中存放对象的信息。

HSET key field value 将哈希表 key 中的字段 field 的值设为 value。重复设置同一个field会覆盖,返回0
HMSET key field1 value1 [field2 value2..] 同时将多个 field-value (域-值)对设置到哈希表 key 中。
HSETNX key field value 只有在字段 field不存在时，设置哈希表字段的值。
HEXISTS key field 查看哈希表 key 中，指定的字段是否存在。
HGET key field value 获取存储在哈希表中指定字段的值
HMGET key field1 [field2..] 获取所有给定字段的值
HGETALL key 获取在哈希表key 的所有字段和值 HKEYS key 获取哈希表key中所有的字段
HLEN key获取哈希表中字段的数量
HVALS key 获取哈希表中所有值
HDEL key field1 [field2..]删除哈希表key中一个/多个field字段
HINCRBY key field n 为哈希表 key 中的指定字段的整数值加上增量n，并返回增量后结果 一样只适用于整数型字段
HINCRBYFLOAT key field n 为哈希表 key 中的指定字段的浮点数值加上增量 n。
HSCAN key cursor [MATCH pattern] [COUNT count]迭代哈希表中的键值对。
代码示例
------------------------HSET--HMSET--HSETNX----------------
127.0.0.1:6379> HSET studentx name sakura # 将studentx哈希表作为一个对象，设置name为sakura
(integer) 1
127.0.0.1:6379> HSET studentx name gyc # 重复设置field进行覆盖，并返回0
(integer) 0
127.0.0.1:6379> HSET studentx age 20 # 设置studentx的age为20
(integer) 1
127.0.0.1:6379> HMSET studentx sex 1 tel 15623667886 # 设置sex为1，tel为15623667886
OK
127.0.0.1:6379> HSETNX studentx name gyc # HSETNX 设置已存在的field
(integer) 0 # 失败
127.0.0.1:6379> HSETNX studentx email 12345@qq.com
(integer) 1 # 成功
----------------------HEXISTS--------------------------------
127.0.0.1:6379> HEXISTS studentx name # name字段在studentx中是否存在
(integer) 1 # 存在
127.0.0.1:6379> HEXISTS studentx addr
(integer) 0 # 不存在
-------------------HGET--HMGET--HGETALL-----------
127.0.0.1:6379> HGET studentx name # 获取studentx中name字段的value
"gyc"
127.0.0.1:6379> HMGET studentx name age tel # 获取studentx中name、age、tel字段的value
"gyc"
"20"
"15623667886"
127.0.0.1:6379> HGETALL studentx # 获取studentx中所有的field及其value
"name"
"gyc"
"age"
"20"
"sex"
"1"
"tel"
"15623667886"
"email"
"12345@qq.com"
--------------------HKEYS--HLEN--HVALS--------------
127.0.0.1:6379> HKEYS studentx # 查看studentx中所有的field
"name"
"age"
"sex"
"tel"
"email"
127.0.0.1:6379> HLEN studentx # 查看studentx中的字段数量
(integer) 5
127.0.0.1:6379> HVALS studentx # 查看studentx中所有的value
"gyc"
"20"
"1"
"15623667886"
"12345@qq.com"
-------------------------HDEL--------------------------
127.0.0.1:6379> HDEL studentx sex tel # 删除studentx 中的sex、tel字段
(integer) 2
127.0.0.1:6379> HKEYS studentx
"name"
"age"
"email"
-------------HINCRBY--HINCRBYFLOAT------------------------
127.0.0.1:6379> HINCRBY studentx age 1 # studentx的age字段数值+1
(integer) 21
127.0.0.1:6379> HINCRBY studentx name 1 # 非整数字型字段不可用
(error) ERR hash value is not an integer
127.0.0.1:6379> HINCRBYFLOAT studentx weight 0.6 # weight字段增加0.6
"90.8"
Hash变更的数据user name age，尤其是用户信息之类的，经常变动的信息！Hash更适合于对象的存储，Sring更加适合字符串存储





Zset（有序集合）
不同的是每个元素都会关联一个double类型的分数（score）。redis正是通过分数来为集合中的成员进行从小到大的排序。

score相同：按字典顺序排序

有序集合的成员是唯一的,但分数(score)却可以重复

ZADD key score member1 [score2 member2] 向有序集合添加一个或多个成员，或者更新已存在成员的分数
ZCARD key 获取有序集合的成员数
ZCOUNT key min max 计算在有序集合中指定区间score的成员数
ZINCRBY key n member 有序集合中对指定成员的分数加上增量 n
ZSCORE key member 返回有序集中，成员的分数值
ZRANK key member 返回有序集合中指定成员的索引
ZRANGE key start end通过索引区间返回有序集合成指定区间内的成员
ZRANGEBYLEX key min max 通过字典区间返回有序集合的成员
ZRANGEBYSCORE key min max通过分数返回有序集合指定区间内的成员==-inf 和 +inf分别表示最小最大值，只支持开区间()==
ZLEXCOUNT key min max 在有序集合中计算指定字典区间内成员数量
ZREM key member1 [member2..] 移除有序集合中一个/多个成员
ZREMRANGEBYLEX key min max 移除有序集合中给定的字典区间的所有成员
ZREMRANGEBYRANK key start stop移除有序集合中给定的排名区间的所有成员
ZREMRANGEBYSCORE key min max 移除有序集合中给定的分数区间的所有成员
ZREVRANGE key start end 返回有序集中指定区间内的成员，通过索引，分数从高到底
ZREVRANGEBYSCORRE key max min 返回有序集中指定分数区间内的成员，分数从高到低排序
ZREVRANGEBYLEX key max min返回有序集中指定字典区间内的成员，按字典顺序倒序
ZREVRANK key member返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
ZINTERSTORE destination numkeyskey1 [key2 ..] 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中，numkeys：表示参与运算的集合数，将score相加作为结果的score
ZUNIONSTORE destination numkeys key1 [key2..] 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
ZSCAN key cursor [MATCH pattern\] [COUNT count] 迭代有序集合中的元素（包括元素成员和元素分值）
代码示例
-------------------ZADD--ZCARD--ZCOUNT--------------
127.0.0.1:6379> ZADD myzset 1 m1 2 m2 3 m3 # 向有序集合myzset中添加成员m1 score=1 以及成员m2 score=2..
(integer) 2
127.0.0.1:6379> ZCARD myzset # 获取有序集合的成员数
(integer) 2
127.0.0.1:6379> ZCOUNT myzset 0 1 # 获取score在 [0,1]区间的成员数量
(integer) 1
127.0.0.1:6379> ZCOUNT myzset 0 2
(integer) 2
----------------ZINCRBY--ZSCORE--------------------------
127.0.0.1:6379> ZINCRBY myzset 5 m2 # 将成员m2的score +5
"7"
127.0.0.1:6379> ZSCORE myzset m1 # 获取成员m1的score
"1"
127.0.0.1:6379> ZSCORE myzset m2
"7"
--------------ZRANK--ZRANGE-----------------------------------
127.0.0.1:6379> ZRANK myzset m1 # 获取成员m1的索引，索引按照score排序，score相同索引值按字典顺序顺序增加
(integer) 0
127.0.0.1:6379> ZRANK myzset m2
(integer) 2
127.0.0.1:6379> ZRANGE myzset 0 1 # 获取索引在 0~1的成员
"m1"
"m3"
127.0.0.1:6379> ZRANGE myzset 0 -1 # 获取全部成员
"m1"
"m3"
"m2"
testset=>{abc,add,amaze,apple,back,java,redis} score均为0

------------------ZRANGEBYLEX---------------------------------
127.0.0.1:6379> ZRANGEBYLEX testset - + # 返回所有成员
"abc"
"add"
"amaze"
"apple"
"back"
"java"
"redis"
127.0.0.1:6379> ZRANGEBYLEX testset - + LIMIT 0 3 # 分页 按索引显示查询结果的 0,1,2条记录
"abc"
"add"
"amaze"
127.0.0.1:6379> ZRANGEBYLEX testset - + LIMIT 3 3 # 显示 3,4,5条记录
"apple"
"back"
"java"
127.0.0.1:6379> ZRANGEBYLEX testset (- [apple # 显示 (-,apple] 区间内的成员
"abc"
"add"
"amaze"
"apple"
127.0.0.1:6379> ZRANGEBYLEX testset [apple [java # 显示 [apple,java]字典区间的成员
"apple"
"back"
"java"
-----------------------ZRANGEBYSCORE---------------------
127.0.0.1:6379> ZRANGEBYSCORE myzset 1 10 # 返回score在 [1,10]之间的的成员
"m1"
"m3"
"m2"
127.0.0.1:6379> ZRANGEBYSCORE myzset 1 5
"m1"
"m3"
-------------------ZLEXCOUNT-----------------------------

127.0.0.1:6379> ZLEXCOUNT testset - +
(integer) 7
127.0.0.1:6379> ZLEXCOUNT testset [apple [java
(integer) 3
------------------ZREM--ZREMRANGEBYLEX--ZREMRANGBYRANK--ZREMRANGEBYSCORE--------------------------------

127.0.0.1:6379> ZREM testset abc # 移除成员abc
(integer) 1
127.0.0.1:6379> ZREMRANGEBYLEX testset [apple [java # 移除字典区间[apple,java]中的所有成员
(integer) 3
127.0.0.1:6379> ZREMRANGEBYRANK testset 0 1 # 移除排名0~1的所有成员
(integer) 2
127.0.0.1:6379> ZREMRANGEBYSCORE myzset 0 3 # 移除score在 [0,3]的成员
(integer) 2
testset=> {abc,add,apple,amaze,back,java,redis} score均为0
myzset=> {(m1,1),(m2,2),(m3,3),(m4,4),(m7,7),(m9,9)}
----------------ZREVRANGE--ZREVRANGEBYSCORE--ZREVRANGEBYLEX-----------
127.0.0.1:6379> ZREVRANGE myzset 0 3 # 按score递减排序，然后按索引，返回结果的 0~3
"m9"
"m7"
"m4"
"m3"
127.0.0.1:6379> ZREVRANGE myzset 2 4 # 返回排序结果的 索引的2~4
"m4"
"m3"
"m2"
127.0.0.1:6379> ZREVRANGEBYSCORE myzset 6 2 # 按score递减顺序 返回集合中分数在[2,6]之间的成员
"m4"
"m3"
"m2"
127.0.0.1:6379> ZREVRANGEBYLEX testset [java (add # 按字典倒序 返回集合中(add,java]字典区间的成员
"java"
"back"
"apple"
"amaze"
-------------------------ZREVRANK------------------------------

127.0.0.1:6379> ZREVRANK myzset m7 # 按score递减顺序，返回成员m7索引
(integer) 1
127.0.0.1:6379> ZREVRANK myzset m2
(integer) 4
mathscore=>{(xm,90),(xh,95),(xg,87)} 小明、小红、小刚的数学成绩
enscore=>{(xm,70),(xh,93),(xg,90)} 小明、小红、小刚的英语成绩
-------------------ZINTERSTORE--ZUNIONSTORE-----------------------------------
127.0.0.1:6379> ZINTERSTORE sumscore 2 mathscore enscore # 将mathscore enscore进行合并 结果存放到sumscore
(integer) 3
127.0.0.1:6379> ZRANGE sumscore 0 -1 withscores # 合并后的score是之前集合中所有score的和
"xm"
"160"
"xg"
"177"
"xh"
"188"
127.0.0.1:6379> ZUNIONSTORE lowestscore 2 mathscore enscore AGGREGATE MIN # 取两个集合的成员score最小值作为结果的
(integer) 3
127.0.0.1:6379> ZRANGE lowestscore 0 -1 withscores
"xm"
"70"
"xg"
"87"
"xh"
"93"





### 事务

redis事务本质：一组命令的集合!一个事务中的所有命令都会被序列化，执行过程中按照顺序执行
一次性，顺序性，排他性，执行一些列的命令

redis单条命令是保持原子性，但是事务不保证原子性
redis事务没有隔离级别的概念
所有的命令在事务中，并没有被直接执行，只有发起执行命令才会被执行!exec
redis事务
a.开启事务（multi）
b.命令入队
c.执行事务（exec）

## 正常执行事务

1. ```
    127.0.0.1:6379> multi
    OK
    127.0.0.1:6379(TX)> set k1 v1
    QUEUED
    127.0.0.1:6379(TX)> set k2 v2
    QUEUED
    127.0.0.1:6379(TX)> get k2
    QUEUED
    127.0.0.1:6379(TX)> set k3 v3
    QUEUED
    127.0.0.1:6379(TX)> exec

  1) OK
  2) OK
  3) "v2"
  4) OK
     127.0.0.1:6379> 
  ```

  ```

  ```

  ## 放弃事务

  discard

  127.0.0.1:6379> multi
  OK
  127.0.0.1:6379(TX)> set k1 v1
  QUEUED
  127.0.0.1:6379(TX)> set k4 v4
  QUEUED
  127.0.0.1:6379(TX)> discard
  OK
  127.0.0.1:6379> keys *

  1) "k3"
  2) "k2"
  3) "k1"
  127.0.0.1:6379> 

  ## 编译型异常（代码有问题，命令有问题）

  事务中所有命令都不会被执行

  127.0.0.1:6379> multi
  OK
  127.0.0.1:6379(TX)> set k1 v1
  QUEUED
  127.0.0.1:6379(TX)> set k2 v2
  QUEUED
  127.0.0.1:6379(TX)> set k3 v3
  QUEUED
  127.0.0.1:6379(TX)> getset k3
  (error) ERR wrong number of arguments for 'getset' command
  127.0.0.1:6379(TX)> set k4 v4
  QUEUED
  127.0.0.1:6379(TX)> exec
  (error) EXECABORT Transaction discarded because of previous errors.
  127.0.0.1:6379> get k1
  (nil)
  127.0.0.1:6379> 



## 运行是异常（其他命令正常执行，错误命令抛出异常）

127.0.0.1:6379> set k1 v1
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379(TX)> incr k1
QUEUED
127.0.0.1:6379(TX)> set k2 v2
QUEUED
127.0.0.1:6379(TX)> get k2
QUEUED
127.0.0.1:6379(TX)> exec
1) (error) ERR value is not an integer or out of range #虽然第一条命令报错，但是依旧正常执行
2) OK
3) "v2"
127.0.0.1:6379> get k2
"v2"
127.0.0.1:6379>





redis实现乐观锁（面试常问）
悲观锁：很悲观，认为什么时候都会出现问题，无论什么都会加锁，效率降低
乐观锁：很乐观，认为什么时候都不会出现问题，所以不上锁，更新数据时会判断在此期间是否有人改动数据，version！
1.获取version
2.更新时比较version

redis监视测试
正常执行成功

  ```

```
127.0.0.1:6379> set money 100
OK
127.0.0.1:6379> set out 0
OK
127.0.0.1:6379> watch money    #监视money对象
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379(TX)> decrby money 20
QUEUED
127.0.0.1:6379(TX)> incrby out 20
QUEUED
127.0.0.1:6379(TX)> exec

1) (integer) 80
2) (integer) 20
   127.0.0.1:6379> 


```





#### 测试多线程修改值，使用watch可以当作乐观锁操作

新开客户端模拟第二个线程

![img](https://img-blog.csdnimg.cn/20210404154242178.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

执行事务之前另一个线程修改值

![img](https://img-blog.csdnimg.cn/20210404154351811.png)



1. ```
    127.0.0.1:6379> unwatch
    OK #r#如果事务执行失败，先解锁
    127.0.0.1:6379> watch money
    OK## 获取最新值，再次监视
    127.0.0.1:6379> multi
    OK
    127.0.0.1:6379(TX)> decrby money 1
    QUEUED
    127.0.0.1:6379(TX)> incrby money 1
    QUEUED
    127.0.0.1:6379(TX)> exec ##对比监视值是否发送变化，如果没有变化可以执行，变化则执行失败

  1) (integer) 999
  2) (integer) 1000
     127.0.0.1:6379> 
  ```

  



#### jedis

我们要使用java操作redis
jedis是官方推荐的java连接开发工具，使用java操作redis中间件，如果你要使用java操作redis，那么你一定要对jedis十分熟悉

#### 测试

#### 新建空项目

  ```

![img](https://img-blog.csdnimg.cn/20210404165919336.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

![img](https://img-blog.csdnimg.cn/20210404172205991.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

### 编码测试

a.连接数据库
b.操作命令
c.断开连接

![img](https://img-blog.csdnimg.cn/20210404173402279.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

所有api都是上面对应的命令

#### 通过Jedis理解事务

![img](https://img-blog.csdnimg.cn/20210404175650868.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

![img](https://img-blog.csdnimg.cn/20210404175935387.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

#### .springboot整合

springboo操作数据库：spring-data jpa jdbc mongodb redis
springData也是和springboot其名的项目

#### 新建模块

新建springboot模块



![img](https://img-blog.csdnimg.cn/20210404180633454.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

spring Native不能勾选否则后面测试会失败

![img](https://img-blog.csdnimg.cn/20210404180657261.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

说明在springboot2.x之后，原来的jedis被替换为lettuce
jedis采用直连，多个线程操作的化是不安全的，如果想要避免不安全，使用jedis pool连接池。bio模式
lettuce：采用netty，实例可以在多个线程中共享，不存在线程不安全的情况，可以减少线程数量。更像nio模式

![img](https://img-blog.csdnimg.cn/20210404183239515.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

ctrl+f搜索redis

![img](https://img-blog.csdnimg.cn/20210404183500743.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

![img](https://img-blog.csdnimg.cn/2021040418360719.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

```java
  @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
    //我们可以自己定义一个redisTemplate来替换这个默认的！
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    	//默认的RedisTemplate没有过多的设置，redis对象都需要序列化！
    	//两个泛型都是object类型，我们使用需要强制转换<String,Object>
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
```



```java
@Bean
@ConditionalOnMissingBean
@ConditionalOnSingleCandidate(RedisConnectionFactory.class)
//由于String是Redis中最常使用的类型，所有单独提出来一个bean
public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
    StringRedisTemplate template = new StringRedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
}
```
、

![img](https://img-blog.csdnimg.cn/20210404184525692.png)

#### 整合测试

1.导入依赖
操作redis

```xml
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```

2.配置连接

![img](https://img-blog.csdnimg.cn/20210404184939542.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

![img](https://img-blog.csdnimg.cn/20210405135555390.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

![img](https://img-blog.csdnimg.cn/20210405141209844.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

![img](https://img-blog.csdnimg.cn/20210405140038824.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

![img](https://img-blog.csdnimg.cn/20210405140320523.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

![img](https://img-blog.csdnimg.cn/20210405140500707.png)

![img](https://img-blog.csdnimg.cn/20210405140702599.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80ODQxMjg0Ng==,size_16,color_FFFFFF,t_70)

#### 自定义RedisTemplate

所有的对象都需要序列化不然会报错
