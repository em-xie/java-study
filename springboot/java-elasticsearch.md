# java-elasticsearch

## 安装

ElasticSearch: https://mirrors.huaweicloud.com/elasticsearch/?C=N&O=D

logstash: https://mirrors.huaweicloud.com/logstash/?C=N&O=D

可视化界面elasticsearch-head.https://github.com/mobz/elasticsearch-head

kibana: https://mirrors.huaweicloud.com/kibana/?C=N&O=D

ik分词器 https://github.com/medcl/elasticsearch-analysis-ik

jdk必须是1.8及以上的版本

下载慢的小伙伴们可以到 华为云的镜像去下载
速度很快，自己找对应版本就可以
ElasticSearch: https://mirrors.huaweicloud.com/elasticsearch/?C=N&O=D
logstash: https://mirrors.huaweicloud.com/logstash/?C=N&O=D
kibana: https://mirrors.huaweicloud.com/kibana/?C=N&O=D

***1\***|***2\*****1.2 macos安装jdk跟mvn**

mac下配置jdk



```
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home
PATH=$JAVA_HOME/bin:$PATH:.
CLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar:.
export JAVA_HOME
export PATH
export CLASSPATH
```

安装JDK1.8并配置环境变量



```
org.elasticsearch.ElasticsearchException: X-Pack is not supported and Machine Learning is not available for [windows-x86]; you can use the other X-Pack features (unsupported) by setting xpack.ml.enabled: false in elasticsearch.yml
```

在es的yml文件添加



```
cluster.initial_master_nodes: ["node-1"]  # 这里的node-1为node-name配置的值
xpack.ml.enabled: false
发现连接不上，跨域问题（跨端口，跨网站等）修改配置，设置跨域
http.cors.enabled: true
http.cors.allow-origin: "*" 
```

打开配置文件



```
vi ~/.bash_profile
```

maven路径 这边就用的idea下载的



```
export M2_HOME=/Users/lisen/apache-maven-3.6.3
export PATH=$PATH:$M2_HOME/bin
```

刷新资源



```
source ~/.bash_profile 
```

测试maven是否安装成功



```
mvn -v
```

windows的安装很多 这边就不再说了。。。

***1\***|***3\*****1.3 elk安装 参考**

https://blog.csdn.net/mgdj25/article/details/105740191

这边说下基本要做的

kibana的国际化设置 yml中设置成zh-CN

es下的yml文件添加



```
http.cors.enabled: true
http.cors.allow-origin: "*" 
```

分词器下载后，修改里面的pom文件修改对应es的版本，放到es的plugins的ik目录下（创建一个），放进去，进到ik目录下通过



```
mvn clean
mvn compile
mvn package
```





来下载jar包 以及编译打包后生成target文件下的releases下的带config以及jar包的文件 复制出来 放到ik下 其他的文件删了(重点)

这边特别提醒一下分词的粒度设置，比如一个分词只是将一个词的单体拆分，“李逵哈哈”，可能只是将李逵两个都分开了，并没有分成“李逵”这个词，那就要自己去写字典了

在ik分词下的config目录下新增一个 “cyx.dic”

把自己的dic 添加到配置中 IKAnalyzer.cfg.xml中

增加一个(里面很多用法 都有注释)



```
<!-- 用户可以在这扩展自己的用户字典-->  
<entry key="ext_dict">cyx.dic</entry>
```



## ES核心概念

集群，节点，索引，类型，文档，分片，映射是什么？

elasticsearch是面向文档，关系型数据库和elasticsearch客观的对比！一切都是json

| Relational DB      | Elasticsearch   |
| :----------------- | :-------------- |
| 数据库（database） | 索引（indices） |
| 表（tables）       | types           |
| 行（rows）         | documents       |
| 字段（columns）    | fields          |

物理设计：

elasticsearch在后台把每个索引划分成多个分片。每个分片可以在集群中的不同服务器间迁移

逻辑设计：

一个索引类型中，抱哈an多个文档，当我们索引一篇文档时，可以通过这样的一个顺序找到它：索引-》类型-》文档id，通过这个组合我们就能索引到某个具体的文档。注意：ID不必是整数，实际上它是一个字符串。



## 文档

文档就是我们的一条条的记录

之前说elasticsearch是面向文档的,那么就意味着索弓和搜索数据的最小单位是文档, elasticsearch中,文档有几个重要属性:

自我包含, - -篇文档同时包含字段和对应的值,也就是同时包含key:value !
可以是层次型的，-一个文档中包含自文档,复杂的逻辑实体就是这么来的! {就是一 个json对象! fastjson进行自动转换!}
灵活的结构,文档不依赖预先定义的模式,我们知道关系型数据库中,要提前定义字段才能使用,在elasticsearch中,对于字段是非常灵活的,有时候,我们可以忽略该字段,或者动态的添加一个新的字段。
尽管我们可以随意的新增或者忽略某个字段,但是,每个字段的类型非常重要,比如一一个年龄字段类型,可以是字符串也可以是整形。因为elasticsearch会保存字段和类型之间的映射及其他的设置。这种映射具体到每个映射的每种类型,这也是为什么在elasticsearch中,类型有时候也称为映射类型。

## 类型

类型类型是文档的逻辑容器,就像关系型数据库一样,表格是行的容器。类型中对于字段的定 义称为映射,比如name映射为字符串类型。我们说文档是无模式的 ,它们不需要拥有映射中所定义的所有字段,比如新增一个字段,那么elasticsearch是怎么做的呢?elasticsearch会自动的将新字段加入映射,但是这个字段的不确定它是什么类型, elasticsearch就开始猜,如果这个值是18 ,那么elasticsearch会认为它是整形。但是elasticsearch也可能猜不对 ，所以最安全的方式就是提前定义好所需要的映射,这点跟关系型数据库殊途同归了,先定义好字段,然后再使用,别整什么幺蛾子。



## 索引



索引就是数据库!

索引是映射类型的容器, elasticsearch中的索引是一个非常大的文档集合。索|存储了映射类型的字段和其他设置。然后它们被存储到了各个分片上了。我们来研究下分片是如何工作的。

物理设计:节点和分片如何工作

一个集群至少有一 个节点,而一个节点就是一-个elasricsearch进程 ,节点可以有多个索引默认的,如果你创建索引,那么索引将会有个5个分片( primary shard ,又称主分片)构成的,每一个主分片会有-一个副本( replica shard ,又称复制分片）

上图是一个有3个节点的集群,可以看到主分片和对应的复制分片都不会在同-个节点内,这样有利于某个节点挂掉了,数据也不至于丢失。实际上, 一个分片是- -个Lucene索引, -一个包含倒排索引的文件目录,倒排索引的结构使得elasticsearch在不扫描全部文档的情况下,就能告诉你哪些文档包含特定的关键字。不过,等等,倒排索引是什么鬼?



倒排索引

倒排索引elasticsearch使用的是一种称为倒排索引 |的结构,采用Lucene倒排索作为底层。这种结构适用于快速的全文搜索，一个索引由文
档中所有不重复的列表构成,对于每一个词,都有一个包含它的文档列表。 例如,现在有两个文档，每个文档包含如下内容:



```
Study every day， good good up to forever  # 文 档1包含的内容
To forever, study every day，good good up  # 文档2包含的内容
```

为为创建倒排索引,我们首先要将每个文档拆分成独立的词(或称为词条或者tokens) ,然后创建一一个包含所有不重 复的词条的排序列表,然后列出每个词条出现在哪个文档:

| term     | doc_1 | doc_2 |
| :------- | :---- | :---- |
| Study    | √     | x     |
| To       | x     | x     |
| Every    | √     | √     |
| Forevery | √     | √     |
| Day      | √     | √     |
| Study    | x     | √     |
| Good     | √     | √     |
| Every    | √     | √     |
| To       | √     | x     |
| Up       | √     | √     |

现在，我们试图搜索 to forever，只需要查看包含每个词条的文档

| term    | doc_1 | doc_2 |
| :------ | :---- | :---- |
| to      | √     | x     |
| forever | √     | √     |
| total   | 2     | 1     |

两个文档都匹配,但是第一个文档比第二个匹配程度更高。如果没有别的条件,现在,这两个包含关键字的文档都将返回。
再来看一个示例,比如我们通过博客标签来搜索博客文章。那么倒排索引列表就是这样的一个结构:

| 博客文章(原始数据) | 博客文章(原始数据) | 索引列表(倒排索引) | 索引列表(倒排索引) |
| :----------------- | :----------------- | :----------------- | :----------------- |
| 博客文章ID         | 标签               | 标签               | 博客文章ID         |
| 1                  | python             | python             | 1,2,3              |
| 2                  | python             | linux              | 3,4                |
| 3                  | linux              | python             |                    |
| 4                  | linux              |                    |                    |


如果要搜索含有python标签的文章,那相对于查找所有原始数据而言，查找倒排索引后的数据将会快的多。只需要查看标签这一栏,然后获取相关的文章ID即可。完全过滤掉无关的所有数据,提高效率!

elasticsearch的索引和Lucene的索引对比

在elasticsearch中，索引(库)这个词被频繁使用,这就是术语的使用。在elasticsearch中 ,索引被分为多个分片,每份分片是-个Lucene的索引。所以一个elasticsearch索引是由多 个Lucene索引组成的。别问为什么,谁让elasticsearch使用Lucene作为底层呢!如无特指，说起索引都是指elasticsearch的索引。

接下来的一切操作都在kibana中Dev Tools下的Console里完成。基础操作!



ik分词器

什么是IK分词器 ?

分词:即把一-段中文或者别的划分成一个个的关键字,我们在搜索时候会把自己的信息进行分词,会把数据库中或者索引库中的数据进行分词,然后进行一个匹配操作,默认的中文分词是将每个字看成一个词,比如“我爱狂神”会被分为"我",“爱”,“狂”,“神” ,这显然是不符合要求的,所以我们需要安装中文分词器ik来解决这个问题。

如果要使用中文,建议使用ik分词器!

IK提供了两个分词算法: ik_ smart和ik_ max_ word ,其中ik_ smart为最少切分, ik_ max_ _word为最细粒度划分!一会我们测试!

什么是IK分词器：

把一句话分词
如果使用中文：推荐IK分词器
两个分词算法：ik_smart（最少切分），ik_max_word（最细粒度划分）
【ik_smart】测试：



```
GET _analyze
{
  "analyzer": "ik_smart",
  "text": "我是社会主义接班人"
}

//输出
{
  "tokens" : [
    {
      "token" : "我",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "是",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    },
    {
      "token" : "社会主义",
      "start_offset" : 2,
      "end_offset" : 6,
      "type" : "CN_WORD",
      "position" : 2
    },
    {
      "token" : "接班人",
      "start_offset" : 6,
      "end_offset" : 9,
      "type" : "CN_WORD",
      "position" : 3
    }
  ]
}
```

【ik_max_word】测试：



```
GET _analyze
{
  "analyzer": "ik_max_word",
  "text": "我是社会主义接班人"
}
//输出
{
  "tokens" : [
    {
      "token" : "我",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "是",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    },
    {
      "token" : "社会主义",
      "start_offset" : 2,
      "end_offset" : 6,
      "type" : "CN_WORD",
      "position" : 2
    },
    {
      "token" : "社会",
      "start_offset" : 2,
      "end_offset" : 4,
      "type" : "CN_WORD",
      "position" : 3
    },
    {
      "token" : "主义",
      "start_offset" : 4,
      "end_offset" : 6,
      "type" : "CN_WORD",
      "position" : 4
    },
    {
      "token" : "接班人",
      "start_offset" : 6,
      "end_offset" : 9,
      "type" : "CN_WORD",
      "position" : 5
    },
    {
      "token" : "接班",
      "start_offset" : 6,
      "end_offset" : 8,
      "type" : "CN_WORD",
      "position" : 6
    },
    {
      "token" : "人",
      "start_offset" : 8,
      "end_offset" : 9,
      "type" : "CN_CHAR",
      "position" : 7
    }
  ]
}
```



## Rest分格

一种软件架构风格，而不是标准。更易于实现缓存等机制

| method | 地址                                                       | url描述                |
| :----- | :--------------------------------------------------------- | :--------------------- |
| PUT    | localhost:9200/索引名称/类型名称/文档id                    | 创建文档(指定文档id)   |
| POST   | localhost:9200/索引名称/类型名称                           | 创建文档（随机文档id） |
| POST   | localhost:9200/索引名称/类型名称/文档id/_update 修改文档   | 修改文档               |
| DELETE | localhost:9200/索引名称/类型名称/文档id 删除文档           | 删除文档               |
| GET    | localhost:9200/索引名称/类型名称/文档id 通过文档id查询文档 | 通过文档id查询文档     |
| POST   | localhost:9200/索引名称/类型名称/search 查询所有的数据     | 查询所有的数据         |

## springboot集成



## 引入依赖

创建一个springboot的项目 同时勾选上springboot-web的包以及Nosql的elasticsearch的包

如果没有就手动引入



```
<!--es客户端-->
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>7.6.2</version>
</dependency>

<!--springboot的elasticsearch服务-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```

注意下spring-boot的parent包内的依赖的es的版本是不是你对应的版本

不是的话就在pom文件下写个properties的版本

<!--这边配置下自己对应的版本--> <properties>    <java.version>1.8</java.version>    <elasticsearch.version>7.6.2</elasticsearch.version> </properties>



## 注入RestHighLevelClient客户端

```
@Configuration 
	public class ElasticSearchClientConfig {    
	@Bean    
	public RestHighLevelClient restHighLevelClient(){       
    RestHighLevelClient client = new RestHighLevelClient(                			   		RestClient.builder(new HttpHost("127.0.0.1",9200,"http"))        );       
    return client;    } 
}
```

索引的删，增是否存在

```
//测试索引的创建
@Test
void testCreateIndex() throws IOException {
    //1.创建索引的请求
    CreateIndexRequest request = new CreateIndexRequest("lisen_index");
    //2客户端执行请求，请求后获得响应
    CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
    System.out.println(response);
}

//测试索引是否存在
@Test
void testExistIndex() throws IOException {
    //1.创建索引的请求
    GetIndexRequest request = new GetIndexRequest("lisen_index");
    //2客户端执行请求，请求后获得响应
    boolean exist =  client.indices().exists(request, RequestOptions.DEFAULT);
    System.out.println("测试索引是否存在-----"+exist);
}

//删除索引
@Test
void testDeleteIndex() throws IOException {
    DeleteIndexRequest request = new DeleteIndexRequest("lisen_index");
    AcknowledgedResponse delete = client.indices().delete(request,RequestOptions.DEFAULT);
    System.out.println("删除索引--------"+delete.isAcknowledged());
}

```

## 文档操作

```
//测试添加文档
    @Test
    void testAddDocument() throws IOException {
        User user = new User("lisen",27);
        IndexRequest request = new IndexRequest("lisen_index");
        request.id("1");
        //设置超时时间
        request.timeout("1s");
        //将数据放到json字符串
        request.source(JSON.toJSONString(user), XContentType.JSON);
        //发送请求
        IndexResponse response = client.index(request,RequestOptions.DEFAULT);
        System.out.println("添加文档-------"+response.toString());
        System.out.println("添加文档-------"+response.status());
//        结果
//        添加文档-------IndexResponse[index=lisen_index,type=_doc,id=1,version=1,result=created,seqNo=0,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]
//        添加文档-------CREATED
    }

    //测试文档是否存在
    @Test
    void testExistDocument() throws IOException {
        //测试文档的 没有index
        GetRequest request= new GetRequest("lisen_index","1");
        //没有indices()了
        boolean exist = client.exists(request, RequestOptions.DEFAULT);
        System.out.println("测试文档是否存在-----"+exist);
    }

    //测试获取文档
    @Test
    void testGetDocument() throws IOException {
        GetRequest request= new GetRequest("lisen_index","1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println("测试获取文档-----"+response.getSourceAsString());
        System.out.println("测试获取文档-----"+response);

//        结果
//        测试获取文档-----{"age":27,"name":"lisen"}
//        测试获取文档-----{"_index":"lisen_index","_type":"_doc","_id":"1","_version":1,"_seq_no":0,"_primary_term":1,"found":true,"_source":{"age":27,"name":"lisen"}}

    }

    //测试修改文档
    @Test
    void testUpdateDocument() throws IOException {
        User user = new User("李逍遥", 55);
        //修改是id为1的
        UpdateRequest request= new UpdateRequest("lisen_index","1");
        request.timeout("1s");
        request.doc(JSON.toJSONString(user),XContentType.JSON);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println("测试修改文档-----"+response);
        System.out.println("测试修改文档-----"+response.status());

//        结果
//        测试修改文档-----UpdateResponse[index=lisen_index,type=_doc,id=1,version=2,seqNo=1,primaryTerm=1,result=updated,shards=ShardInfo{total=2, successful=1, failures=[]}]
//        测试修改文档-----OK

//        被删除的
//        测试获取文档-----null
//        测试获取文档-----{"_index":"lisen_index","_type":"_doc","_id":"1","found":false}
    }


    //测试删除文档
    @Test
    void testDeleteDocument() throws IOException {
        DeleteRequest request= new DeleteRequest("lisen_index","1");
        request.timeout("1s");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println("测试删除文档------"+response.status());
    }

    //测试批量添加文档
    @Test
    void testBulkAddDocument() throws IOException {
        ArrayList<User> userlist=new ArrayList<User>();
        userlist.add(new User("cyx1",5));
        userlist.add(new User("cyx2",6));
        userlist.add(new User("cyx3",40));
        userlist.add(new User("cyx4",25));
        userlist.add(new User("cyx5",15));
        userlist.add(new User("cyx6",35));

        //批量操作的Request
        BulkRequest request = new BulkRequest();
        request.timeout("1s");

        //批量处理请求
        for (int i = 0; i < userlist.size(); i++) {
            request.add(
                    new IndexRequest("lisen_index")
                            .id(""+(i+1))
                            .source(JSON.toJSONString(userlist.get(i)),XContentType.JSON)
            );
        }
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        //response.hasFailures()是否是失败的
        System.out.println("测试批量添加文档-----"+response.hasFailures());

//        结果:false为成功 true为失败
//        测试批量添加文档-----false
    }


    //测试查询文档
    @Test
    void testSearchDocument() throws IOException {
        SearchRequest request = new SearchRequest("lisen_index");
        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置了高亮
        sourceBuilder.highlighter();
        //term name为cyx1的
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "cyx1");
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        System.out.println("测试查询文档-----"+JSON.toJSONString(response.getHits()));
        System.out.println("=====================");
        for (SearchHit documentFields : response.getHits().getHits()) {
            System.out.println("测试查询文档--遍历参数--"+documentFields.getSourceAsMap());
        }

//        测试查询文档-----{"fragment":true,"hits":[{"fields":{},"fragment":false,"highlightFields":{},"id":"1","matchedQueries":[],"primaryTerm":0,"rawSortValues":[],"score":1.8413742,"seqNo":-2,"sortValues":[],"sourceAsMap":{"name":"cyx1","age":5},"sourceAsString":"{\"age\":5,\"name\":\"cyx1\"}","sourceRef":{"fragment":true},"type":"_doc","version":-1}],"maxScore":1.8413742,"totalHits":{"relation":"EQUAL_TO","value":1}}
//        =====================
//        测试查询文档--遍历参数--{name=cyx1, age=5}
    }
```

## 实战

### 构建项目骨架

1. 构建springboot项目引入相关依赖
2. ![在这里插入图片描述](https://img-blog.csdnimg.cn/13ac36f847d14584bd488124dc3f168d.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBAQ2N55Li25Y-M,size_14,color_FFFFFF,t_70,g_se,x_16#pic_center)



```
 <properties>        <java.version>1.8</java.version>        <!--        自定义版本依赖，保证和本地一致-->        <elasticsearch.version>7.6.1</elasticsearch.version>    </properties><!--        为了将对象转换为json数据--><dependency>    <groupId>com.alibaba</groupId>    <artifactId>fastjson</artifactId>    <version>1.2.62</version></dependency>

```

1. 引入前端页面，并且编写controller层访问

```
@Controllerpublic class IndexController {    @GetMapping({"/", "/index"})    public String index() {        return "index";    }}
```



```
<!--        jsoup解析网页-->
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.10.2</version>
</dependency>

```



```
@Component
public class HtmlParseUtil {

    public static List<Content> parseJD(String keywords) throws Exception {
//        获取请求 https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=" + keywords;
//        解析网页（Jsoup返回Document就是浏览器Document对象）
        Document document = Jsoup.parse(new URL(url), 30000);
//        所有你在js中可以使用的方法这里都能使用
        Element element = document.getElementById("J_goodsList");
//        获取所有li元素
        Elements elements = element.getElementsByTag("li");
//        获取元素中的内容，这里el，就是每个li标签

        ArrayList<Content> goodslist = new ArrayList<>();
        for (Element el : elements
        ) {
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img"); // 爬取懒加载的图片
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            Content content = new Content(img, price, title);
            goodslist.add(content);
        }
        return goodslist;
    }
}

```

```
@Service
public class ContentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public Boolean parseContent(String keywords) throws Exception {
        List<Content> contents = new HtmlParseUtil().parseJD(keywords);
//        把查询到的数据批量添加放到es中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");

        for (int i = 0; i < contents.size(); i++) {
            bulkRequest
                    .add(new IndexRequest("jd_goods")
                            .source(JSON.toJSONString(contents.get(i)), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }
}

```

```
@RestController
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keywords}")
    public Boolean parse(@PathVariable("keywords") String keywords) throws Exception {
        return contentService.parseContent(keywords);
    }
}

```

### 前后端分离

1. 添加pojo类

```
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private String img;
    private String price;
    private String title;
}

```

```
//    获取数据实现搜索功能
    public List<Map<String, Object>> searchPage(String keyword, int pageNO, int pagesize) throws Exception {
        if (pageNO <= 1) {
            pageNO = 1;
        }
//        条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        分页
        sourceBuilder.from(pageNO);
        sourceBuilder.size(pagesize);
//        精确匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//        解析结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit documentFields : searchResponse.getHits().getHits()
        ) {
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

```

```
@GetMapping("/search/{keyword}/{pageNO}/{pagesize}")public List<Map<String, Object>> search(@PathVariable("keyword") String keyword,                                        @PathVariable("pageNO") int pageNO,                                        @PathVariable("pagesize") int pagesize) throws Exception {    return contentService.searchPage(keyword, pageNO, pagesize);}

```

```
<!--前端使用vue，实现前后端分离-->
<script th:src="@{/js/vue.min.js}"></script>
<script th:src="@{/js/axios.min.js}"></script>
<script>
    new Vue({
        el: '#app', // 在最大的<div标签中添加 id = "app">
        data: {
            keyword: '', // 搜索关键字
            results: [] // 搜索结果
        },
        methods: {
            searchKey() {
                var keyword = this.keyword;
                console.log(keyword);
                //对接后端的接口
                axios.get('search/' + keyword + "/1/10").then(response => {
                    console.log(response);
                    this.results = response.data; // 绑定数据
                })
            }
        }
    })

```

1. 导入vue和axios的js代码，在任意文件夹下执行命令下载vue和axios的js代码

![在这里插入图片描述](https://img-blog.csdnimg.cn/08d06e5096e441659bfa427679260142.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBAQ2N55Li25Y-M,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center)

```
<div class="product" v-for="result in results">
    <div class="product-iWrap">
        <!--商品封面-->
        <div class="productImg-wrap">
            <a class="productImg">
                <img :src="result.img">
            </a>
        </div>
        <!--价格-->
        <p class="productPrice">
            <em> {{result.price}} </em>
        </p>
        <!--标题-->
        <p class="productTitle">
            <a> {{result.title}} </a>
        </p>
        <!-- 店铺名 -->
        <div class="productShop">
            <span>店铺： 狂神说Java </span>
        </div>
        <!-- 成交信息 -->
        <p class="productStatus">
            <span>月成交<em>999笔</em></span>
            <span>评价 <a>3</a></span>
        </p>
    </div>
</div>

```

```
 //    获取数据实现高亮搜索
    public List<Map<String, Object>> searchLightPage(String keyword, int pageNO, int pagesize) throws Exception {
        if (pageNO <= 1) {
            pageNO = 1;
        }
//        条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        分页
        sourceBuilder.from(pageNO);
        sourceBuilder.size(pagesize);
//        精确匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.requireFieldMatch(true); // 多个高亮显示
        highlightBuilder.field("title");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);
//        执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//        解析结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()
        ) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap(); // 原来的结果
//            解析高亮的字段，将原来的字段换位我们高亮的字段即可
            if (title != null) {
                Text[] fragments = title.fragments();
                String n_title = "";
                for (Text text:fragments) {
                    n_title += text;
                }
                sourceAsMap.put("title",n_title); // 高亮字段替换掉原来的内容即可
            }
            list.add(sourceAsMap);
        }
        return list;
    }

```

