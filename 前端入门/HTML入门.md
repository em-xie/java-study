# HTML入门

# 1、HTML入门

1. DOCTYPE声明
2. title标签
3. meta标签

<!--告诉浏览器我们使用什么规范-->

<!DOCTYPE html>
<html lang="en">
<!--head标签代表网页头部-->
<head>
    <!--meta描述性标签，用来描述一些网站的信息-->
    <!--meta一般用来做ＳＥＯ-->
    <meta charset="UTF-8">
    <meta name="keywords" content="这里是关键词">
    <meta name="descript" content="这里是描述">
    <!--网页标题-->
    <title>第一个网页</title>
</head>
<!--网页主体-->
<body>
Hello，World！
</body>
</html>

```
<!--告诉浏览器我们使用什么规范-->
<!DOCTYPE html>
<html lang="en">
<!--head标签代表网页头部-->
<head>
    <!--meta描述性标签，用来描述一些网站的信息-->
    <!--meta一般用来做ＳＥＯ-->
    <meta charset="UTF-8">
    <meta name="keywords" content="这里是关键词">
    <meta name="descript" content="这里是描述">
    <!--网页标题-->
    <title>第一个网页</title>
</head>
<!--网页主体-->
<body>
Hello，World！
</body>
</html>
```

# 2、网页基本标签

1. ```
   1. 标题标签
   2. 段落标签
   3. 换行标签
   4. 水平线标签
   5. 字体样式标签
   6. 注释和特殊符号
   
   1. 标题标签<title>基本标签学习</title>
   2. 段落标签<p> </p>
   3. 换行标签<br/>
   4. 水平线标签<hr>
   5. 字体样式标签
      粗体 ： <strong></strong>
      斜体 ： <em></em>
   6. 注释和特殊符号
      注释<!-- --> 注意，注释可以使用Ctry+斜杠来达到注释的效果。
      特殊符号：
      空格 ： &nbsp;
      大于号：&gt;
      小于号：&lt;
      版权符号：&copy;
      <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <title>基本标签学习</title>
   </head>
   <body>
   <!--标题标签-->
   <h1>一级标签</h1>
   <h2>二级标签</h2>
   <h3>三级标签</h3>
   <h4>四级标签</h4>
   <h5>五级标签</h5>
   <h6>六级标签</h6>
   <!--段落标签
   <p> </p>
   -->
   <p>我们程序设计追求“高内聚，低耦合”。</p>
   <p>高内聚就是类的内部数据操作细节自己完成，不允许外部干涉</p>
   <p>低耦合:仅暴露少量的方法给外部使用</p>
   <!--换行标签<br/>-->
   程序是指令和数据的有序集合，其本身没有任何运行的含义，是一个静态的概念。<br/>
   而进程则是执行程序的一次执行过程，它是一个动态的概念。是系统资源分配的单位。<br/>
   通常在一个进程中可以包含若干个线程，当然一个进程中至少有一个线程，不然没有存在的意义。线程是中央处理器调度和执行的单位。<br/>
   <!--水平线标签-->
   <hr>
   <!--粗体，斜体-->
   <h1>字体样式标签</h1>
   粗体 ： <strong> i lover you</strong><br>
   斜体 ： <em>i love you</em>
   <br>
   <!--特殊符号-->
   空格 ： &nbsp;<br>
   <br>
   大于号：&gt;
   <br>
   小于号：&lt;
   <br>
   版权符号：&copy;版权所有坏银
   </body>
   </html>
   ```

   

# 3、图像标签



```
<img src="" alt=""title=""width=""height="">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图像标签</title>
</head>
<body>
<!--
    src：图片地址
    相对路径和绝对路径
    ../上一级目录
    alt：加载失败返回
    title：表示鼠标悬停到上面所显示的文字
    width,height：设置图片宽高
-->
<img src="" alt="坏银的头像"title="悬停文字"width="300"height="300">
</body>
</html>
```

按一下Tab会出现智能补全

# 4、链接标签

- 页面间链接
  - 从一个页面链接到另一个页面
- 锚链接
  - 页面间跳转
- 功能性链接

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>链接标签</title>
</head>
<body>
<!--
    a标签
    href：必填，表示要跳转的那个页面
    target：表示窗口在那里打开
    _blank在新标签中打开
    _self在此页面打开
-->
<!--页面间链接-->
<a href="第一个网页.html"target="_blank">点击我跳转到我写的</a>
<a href="https://www.baidu.com"target="_self">点击我跳转到百度</a>
<br>
<!--图片超链接，点击图片跳转链接-->
<a href="第一个网页.html">
    <img src="" alt="坏银的头像"title="悬停文字"width="300"height="300">
</a>
<!--锚链接
    1.需要一个标记
     使用name作为标记
     <a name="top"顶部</a>>
    2.跳转到标记,使用#关联标记
    <a name="#top"></a>
-->
<a name="top"></a>
<a name="#top"></a>
<!--也可以打开新连接并定位到标记位置-->
<br>
<a href="基本标签.html#down">跳转到基本标签的底部</a>
<!--功能性链接
    邮件链接：mailto
    QQ链接：QQ推广官方链接
    https://shang.qq.com/v3/index.html
-->
<a href="mailto:1938857445@qq.com">点击联系我</a>
</body>
</html>
```

# 5、行内元素和块元素

1. 块元素

无论内容多少，该元素独占一行

（p、h1~h6….）

1. 行内元素

内容撑开宽度，左右都是行内元素的可以排在一行

（a.strong.em…）

# 6、列表

什么是列表：

列表就是信息资源的一种展示形式。它可以使信息结构化和条理化，并以列表的样式显示出来，以便浏览者能更快便捷地获得相应地信息。

列表的分类：

- 无序列表：ol，li
- 有序列表ul，li
- 定义列表dl，dt，dd

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>列表</title>
</head>
<body>
<!--有序列表
    应用范围：试卷，问答....
-->
<ol>
    <li>java</li>
    <li>python</li>
    <li>运维</li>
    <li>前端</li>
    <li>C/C++</li>
</ol>
<hr>
<!--无序列表
    应用范围：导航，侧边栏....
-->
<ul>
    <li>java</li>
    <li>python</li>
    <li>运维</li> 
    <li>前端</li>
    <li>C/C++</li>
</ul>
<!--定义列表
    dl:标签
    dt：列表名称
    dd：列表内容
    应用范围：网站底部
-->
<dl>
    <dt>学科</dt>
    <dd>java</dd>
    <dd>python</dd>
    <dd>运维</dd>
    <dd>前端</dd>
    <dd>C/C++</dd>
    <dt>位置</dt>
    <dd>西安</dd>
    <dd>重庆</dd>
    <dd>新疆</dd>
</dl>
</body>
</html>
```

# 7、表格

为什么使用表格：

- 简单通用
- 结构稳定

基本结构

1. 单元格
2. 行
3. 列
4. 跨行
5. 跨列

```
    1.表格table
    2.行 tr
    3.列 td
    4.border="1px" 增加边框，1px为大小
    5.colspan 跨列
    6.rowspan 跨列
    
```

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>表格学习</title>
</head>
<body>
<!--表格table
    行 tr
    列 td
    border="1px" 增加边框，1px为大小
    colspan 跨列
    rowspan 跨列
-->
    <table border="1px">
        <tr>
            <!--colspan 跨列-->
            <td colspan="4">1-1</td>
            <td>1-2</td>
            <td>1-3</td>
            <td>1-4</td>
        </tr>
        <tr>
            <!--rowspan 跨列-->
            <td rowspan="4">2-1</td>
            <td>2-2</td>
            <td>2-3</td>
            <td>2-4</td>
        </tr>
        <tr>
            <td>3-1</td>
            <td>3-2</td>
            <td>3-3</td>
            <td>3-4</td>
        </tr>
    </table>
    <br>
    <table border="1px">
        <tr>
           <td colspan="3">学生成绩</td>
        </tr>
        <tr>
            <td rowspan="2">坏银1</td>
            <td>语文</td>
            <td>100</td>
        </tr>
        <tr>
            <td>数学</td>
            <td>100</td>
        </tr>
        <tr>
            <td rowspan="2">坏银2</td>
            <td>语文</td>
            <td>100</td>
        </tr>
        <tr>
            <td>数学</td>
            <td>100</td>
        </tr>
    </table>
</body>
</html>
```

# 8、视频音频

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>媒体元素学习</title>
</head>
<body>
<!--音频和视频
    video：视频
    src：资源路径
    controls：控制条
    autoplay：自动播放
    audio:音频
    其他同上
-->
<video src="" controls autoplay></video>
<audio src="" controls autoplay></audio>
</body>
</html>
```

# 9、页面结构分析

![img](https://cdn.jsdelivr.net/gh/lmlx66/img/html%E5%AD%A6%E4%B9%A0/1.png)

# 10、iframe内联结构

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>页面结构分析</title>
</head>
<body>
    <!--   
        iframe标签
        src:引用页面地址
        name:框架标识名
     -->
    <iframe src="//player.bilibili.com/player.html?aid=55631961&bvid=BV1x4411V75C&cid=97257627&page=10"
            scrolling="no"
            border="0"
            frameborder="no"
            framespacing="0"
            allowfullscreen="true">
    </iframe>
    <br>
    <iframe src="https://www.baidu.com" name="bilibili" frameborder="0" width="1000px" height="800px"></iframe>
    <a href="http://www.lmlx66.top" target="bilibili" >点击跳转</a>
</body>
</html>
```

# 11、表单post和get

注册登录弹框

![img](https://cdn.jsdelivr.net/gh/lmlx66/img/html%E5%AD%A6%E4%B9%A0/2.png)

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录注册</title>
</head>
<body>
    <h1>注册</h1>
    <!--表单form
        action:表单提交的位置，可以是网站，也可以是一个请求处理地址
        method:post,get 提交方式
        get方式提交，我们可以看到我们提交的信息，不安全但是高效
        post，不可以看到，但是效率没那么高，比较安全，传输大文件
        表单p
        文本输入框：text
        密码输入框：password
        选项input
        提交：submit
        重置：reset
    -->
    <form action="1.我的第一个网页.html" method="get">
        <!--文本输入框：input type="text"-->
        <p>名字：<input type="text" name="username"></p>
        <!--文本输入框：input type="password"-->
        <p>密码：<input type="password" name="pwd"></p>
        <input type="submit">
        <input type="reset">
    </form>
</body>
</html>
```

# 12、文本框单选框

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录注册</title>
</head>
<body>
    <h1>注册</h1>
    <form action="1.我的第一个网页.html" method="get">
        <p>名字：<input type="text" name="username"></p>
        <p>密码：<input type="password" name="pwd"></p>
        <p>
        <!--单选框标签
            input type="radio"
            value:单选框的值
            name：表示组
           -->
            <input type="radio" value="boy" name="sex">男
            <input type="radio" value="girl" name="sex">女
        </p>
        <p>
            <input type="submit">
            <input type="reset">
        </p>
    </form>
</body>
</html>
```

# 13、按钮与多选框

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录注册</title>
</head>
<body>
    <h1>注册</h1>
    <form action="1.我的第一个网页.html" method="get">
        <p>名字：<input type="text" name="username"></p>
        <p>密码：<input type="password" name="pwd"></p>
        <p>
        <!--多选框标签
            input type="checkbox"
            value:多选框的值,名字
            name：表示组
           -->
            <input type="checkbox" value="sleep" name="hobby" >睡觉
            <input type="checkbox" value="code" name="hobby">打代码
            <input type="checkbox" value="chat" name="hobby">聊天
            <input type="checkbox" value="sleep" name="hobby">游戏
        </p>
        <!--按钮
            value:按钮的值
            name：表示组
        -->
        <p>
            按钮：<input type="button" name="btn1" value="点击变长">
            图片按钮：<input type="image" src="" name="btn1" value="点击跳转">
        </p>
        <p>
            <input type="submit">
            <input type="reset">
        </p>
    </form>
</body>
</html>
```

# 10~14总结

- form:提交表单
  - action：跳转
  - method：提交方式
    - get
    - post

------

- input表单
  - name：组
  - value：名字
  - type：类型
    - text：文本框
    - password：密码框
    - radio：单选框
    - checkbox：多选框
    - button：按钮
    - image：图片按钮
    - email：邮箱

# 14、搜索框滑块与简单验证

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>滑块</title>
</head>
<body>
<form action="1.我的第一个网页" method="get">
    <p>邮箱：
        <input type="email" name="email">
    </p>
    <p>URL：
        <input type="url" name="url">
    </p>
    <p>数字：
        <input type="number" name="num" max="100" min="0" step="10" >
    </p>
    <p>滑块：
        <input type="range" name="音量" min="0" max="100" step="8">
    </p>
    <p>搜索：
        <input type="search" name="查找">
    </p>
     <p>
        <input type="submit">
        <input type="reset">
    </p>
</form>
</body>
</html>
```

# 15、表单的应用

- 隐藏域
- 只读
- 禁用

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>滑块</title>
</head>
<body>
    <!--
       readonly：只读
       disabled：禁用
       hidden：隐藏
    -->
    <form action="1.我的第一个网页.html" method="get">
        <p>
            <input type="text" name="username" value="admin" readonly>
        </p>
        <p>
            <input type="password" hidden>   
        </p>
        <p>
            <input type="submit">
            <input type="reset" disabled>
        </p>
        <p>
        <!--增强鼠标可用性，点击跳转到框-->
            <label> 点击我进入：<input type="text" name="跳转"></label>
        </p>
        <p>
            <!--增强鼠标可用性，点击跳转到框-->
          <label for="mark">点击我进入：</label>
            <input type="text" id="mark">
        </p>
    </form>
</body>
</html>
```

# 16、表单的初级验证

为什么需要表单验证：减少服务器的压力，保证安全

常用方式：

- placeholder(提示信息)
- required(非空判断)
- pattern（正则表达式）

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>滑块</title>
</head>
<body>
    <!--
       placeholder:提示表单
       required：不能为空
       pattern:正则表达式，可百度
    -->
    <form action="1.我的第一个网页.html" method="get">
       <p><input type="text" name="username" placeholder="请输入用户名"></p>
        <p><input type="password" name="pwd" placeholder="请输入密码" required></p>
        <p><input type="password" name="pwd" pattern="[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?"></p>
    </form>
</body>
</html>
```

# 17、总结

![img](https://cdn.jsdelivr.net/gh/lmlx66/img/html%E5%AD%A6%E4%B9%A0/3.png)