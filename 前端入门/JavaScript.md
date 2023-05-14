#  JavaScript

## 1、什么是 JavaScript

### 1.1、概述

JavaScript是一门世界上最流行的脚本语言
Java、 JavaScript
10天—

一个合格的后端人员,必须要精通 JavaScript

**ECMAScript**它可以理解为是Javascript的一个标准
最新版本已经到es6版本-
但是大部分浏览器还只停留在支持es5代码上!
开发环境一线上环境,版本不一致

## 2、快速入门

### 2.1、引入JavaSciprt

1、 内部标签

<script>
//...
</script>

2、外部引入
abs.js

```javascript
 //...
1
```

test.html

```javascript
  <script src="abc.js"></script>
1
```

测试代码

```
<!DOCTYPE html>  
<html lang="en"> 
<head> 
    <meta charset="UTF-8"> 
    <title>Title</title> 

    <!--script标签内,写Javascript代码--> 
    <!--<script>--> 
    <!--alert( 'hello,world');--> 
    <!--</script>--> 

    <!--外部引入--> 
    <!--注意：script标签必须成对出现--> 
    <script src="js/qj.js"></script>

    <!--不用显示定义type，也默认就是 javascript--> 
    <script type="text/javascript"> 

    </script> 
</head> 
<body>
<!--这里也可以存放--> 
</body> 
</html>

```

### 2.2、基本语法入门

<head> 
    <meta charset="UTF-8"> 
    <title>Title</title> 
<!-- JavaScript严格区分大小写 -->
<script>
    //1.定义变量   变量类型  变量名 
    var score = 71; 
    // alert(num); 
    // 2.条件控制
    if (score>60 && score<70){
    	alert("60~70") 
    }else if(score>70 && score<80){ 
    	alert("70~80") 
    }else{ 
    	alert("other") 
    }
</script>

浏览器调试必备须知
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601020137662.png)

### 2.3、数据类型

数值，文本，图形，音频，视频。。。

![变量](https://img-blog.csdnimg.cn/20200601120323456.png)
![数据类型](https://img-blog.csdnimg.cn/20200601024136189.png)
![数据类型](https://img-blog.csdnimg.cn/20200601113000433.png)
![数据类型](https://img-blog.csdnimg.cn/20200601113211325.png)
浮点数问题

![浮点数问题](https://img-blog.csdnimg.cn/2020060111550447.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601120438614.png)
![对象](https://img-blog.csdnimg.cn/20200601120606379.png)
对象是大括号，数组是中括号~~

每个属性之间使用逗号隔开，最后一个不需要添加

```
//Person person = new Person(1,2,3,4,5); 
var person = { 
    name:"qinjiang", 
    age: 3, 
    tags: ['js','java','web','...'] 
}
```

在浏览器调试

```javascript
person.name 
> "qinjiang" 
person.age 
> 3
```

### 2.4、严格检查格式

![设置位置](https://img-blog.csdnimg.cn/20200601121937754.png)

```
<head> 
    <meta charset="UTF-8"> 
    <title>Title</title> 
<！--
前提：IEDA需要设置支持ES6语法
'use strict'；严格险查模式，预防JavaScript的随意性导致产生的一些问题,必须写在Javascript的第一行！
局演变量建议都使用 let 去定义~
-->
<script>
	'use strict' ;
	//全局变量
	let = 1;
	//ES6   let
</script>

</head>

```

![use strict](https://img-blog.csdnimg.cn/20200601121828198.png)

## 3、数据类型详解

### 3.1、字符串

![字符](https://img-blog.csdnimg.cn/20200601122433619.png)
console.log("\x41") ==> a
![字符串](https://img-blog.csdnimg.cn/20200601124110177.png)
console.log(msg) ==> ‘你好呀，qinjiang’
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601124120750.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601124127690.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601124237358.png)

### 3.2、数组

**Array可以包含任意的数据类型**

```javascript
var arr = [1,2,3,4,5,6]; //通过下标取值和赋值
arr[0]
arr[0] = 1
123
```

1、长度

```javascript
arr.length
```

注意：加入给arr.ength赋值，数组大小就会发生变化-，如果赋值过小，元素就会丢失
2、indexOf，通过元素获得下标索引

```
arr.indexof(2)
1
```

字符串的“1”和数字 1 是不同的

arr = [0,1,1];

3、slice()截取Array的一部分，返回一个新数组，类似于String中的substring

4、push()，pop()尾部

```
push：压入到尾部
pop：弹出尾部的一个元素
```

5、unshiift()，shiit()头部

```
unshift：压入到头部
shift：弹出头部的一个元素
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601130214563.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601130226775.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601130238713.png)
常用的方法slice（截取），push（压入），pop（弹出），shift（压入首部），unshift（弹出首部），concat（拼接）

### 3.3、对象

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601131225985.png)
JavaScript中的所有的键都是字符串，值都是任意对象
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601131249944.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601131302288.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JlbGxfbG92ZQ==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601131312970.png)

### 3.4、流程控制

**if 判断**

```
var age = 3; 
if(age>3){ //第一个判断 
   alert('haha"); 
}else if(age<5) {  //第二个判断 
   alert("kuwa~"); 
}else { //否则, 
   alert("kuwa~"); 
}

```

**while循环,避免程序死循环**

```
while(age<100){ 
    age = age + 1; 
    console.log(age) 
} 
do{ 
    age = age + 1; 
    console.log(age) 
}while(age<100)

```

**for循环**

```
for (let i = 0;i< 100;i++){ 
  console.Log(i) 
 } 

```

**forEach 循环**

```
var age = [12,3,12,3,12,3,12,31,23,123]; 
    //函数 
     age. ForEach(function (value) { 
     console.Log(value) 
 })

```

**forEach是ES5.1引入的**

**for…in**

```
//for(var index in object) { }
for(var num in age){ 
    if(age.hasownProperty(num)){ 
        console.log("存在") 
        console.log(age[num]) 
    } 
}

```

### 3.5、map和set

ES6的新特性~
![Map](https://img-blog.csdnimg.cn/20200601133412295.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JlbGxfbG92ZQ==,size_16,color_FFFFFF,t_70)
![Set](https://img-blog.csdnimg.cn/2020060113343566.png)

### 3.6、iterator

ES6的新特性~

使用iterator来迭代Map和Set

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020060114184024.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JlbGxfbG92ZQ==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601141848365.png)

## 4、函数

### 4.1、定义函数

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601142830443.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601144650562.png)
function(x){…}这是一个匿名函数，但是可以把结果赋值给abs，通过abs就可以调用函数！
方式一和方式二等价

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601144213605.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601144221809.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601144230467.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JlbGxfbG92ZQ==,size_16,color_FFFFFF,t_70)
![args[]](https://img-blog.csdnimg.cn/20200601144435925.png)
![匿名函数](https://img-blog.csdnimg.cn/20200601144246504.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JlbGxfbG92ZQ==,size_16,color_FFFFFF,t_70)
问题：arguments包含所有的参数，我们有时候想使用多余的参数来进行附加操作，需要排除已有参数~

![rest](https://img-blog.csdnimg.cn/20200601144313101.png)
rest参数只能卸载最后面，必须用…标识（**rest是自定义名称**）
function aaa(a,b,…**like**){
console.log(**like**);
}

### 4.2、变量的作用域

![作用域](https://img-blog.csdnimg.cn/20200602162341904.png)
![作用域](https://img-blog.csdnimg.cn/20200602162437503.png)
![内部函数](https://img-blog.csdnimg.cn/20200602162457960.png)
![内部函数](https://img-blog.csdnimg.cn/20200602162510704.png)

假设在JavaScript中，函数查找变量从自身函数开始。由“内”向“外”查找。
假设外部存在这个同名的函数变量。则内部函数会屏蔽外部函数的变量。
![提升变量作用域](https://img-blog.csdnimg.cn/20200602162539684.png)
![提升变量作用域](https://img-blog.csdnimg.cn/20200602162551176.png)
这个是在JavaScript建立之初就存在的特性，养成规范，所有的变量定义都放在函数的头部，不要乱放，便于代码维护。

![规范](https://img-blog.csdnimg.cn/20200602162615198.png)
![全局函数](https://img-blog.csdnimg.cn/20200602162619906.png)
![全局对象](https://img-blog.csdnimg.cn/20200602162627924.png)
![windows的使用](https://img-blog.csdnimg.cn/20200602162646741.png)

JavaScript实际上只有一个全局作用域，任何变量（函数也可以视为变量），假设没有在函数作用范围内找到，就会向外查找，如果在全局作用域都没有找到，报错 RefernceError

规范

![唯一全局变量](https://img-blog.csdnimg.cn/20200602163104527.png)

我们所有的全局变量都会绑定到我们的windows上。如果不同的js文件。使用了相同的全局变量。冲突 ~ >如果能够减少冲突?

把自己的代码全部放入自己定义的唯一空间名字中,上面。啊降低全局命名冲突的问题.
（jQuery库）
![局部作用域](https://img-blog.csdnimg.cn/20200602163139197.png)
![let](https://img-blog.csdnimg.cn/20200602163158102.png)

> 常量const

在ES6之前，怎么定义常量：只有用最全部大写字母命名的变量就是常量，建议不要修改这样的值。

![常量](https://img-blog.csdnimg.cn/20200602163224706.png)

### 4.3、方法

![定义方法](https://img-blog.csdnimg.cn/20200602163248659.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200602163305982.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200602163316641.png)

### 5、内部对象

![基本类型](https://img-blog.csdnimg.cn/20200602165115887.png)

### 5.1、基本语法

![基本语法](https://img-blog.csdnimg.cn/2020060216504087.png)
![转换](https://img-blog.csdnimg.cn/20200602165142464.png)

### 5.2、JSON

![JSON](https://img-blog.csdnimg.cn/20200602170401679.png)

在JavaScript一切皆为对象，任何js支持的类型都可以用JSON来表示：number，String…
![JSON](https://img-blog.csdnimg.cn/2020060217042187.png)
![JSON和JS的区别](https://img-blog.csdnimg.cn/20200602170448306.png)

### 5.3、Ajax

![Ajax](https://img-blog.csdnimg.cn/20200602170517885.png)

## 6、面向对象编程

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200602175246355.png)
原型：
![原型](https://img-blog.csdnimg.cn/20200602172539690.png)
以前的继承
![以前的继承](https://img-blog.csdnimg.cn/20200602175002595.png)
现在的继承
![现在的继承](https://img-blog.csdnimg.cn/2020060217500913.png)

2、继承
![ES6后的继承](https://img-blog.csdnimg.cn/20200602175052494.png)
本质：查看对象原型
![原型](https://img-blog.csdnimg.cn/20200602175522453.png)

> 原型链

![原型链](https://img-blog.csdnimg.cn/20200602180120186.png)

## 7、操作BOM对象(重点)

![浏览器介绍](https://img-blog.csdnimg.cn/2020060218294210.png)
![window](https://img-blog.csdnimg.cn/20200602183004800.png)
![navigation](https://img-blog.csdnimg.cn/20200602183016961.png)
![screen](https://img-blog.csdnimg.cn/20200602183036714.png)

![location](https://img-blog.csdnimg.cn/20200602183046757.png)
![document](https://img-blog.csdnimg.cn/20200602183116381.png)
![cookie](https://img-blog.csdnimg.cn/20200602183133794.png)

## 8、操作DOM对象（重点）

![核心](https://img-blog.csdnimg.cn/20200602184338474.png)

> 获得DOM节点

![实例](https://img-blog.csdnimg.cn/2020060312494667.png)

![获取对应的选择器](https://img-blog.csdnimg.cn/20200603112811365.png)
这种都是原生代码，往后尽量用jQuery();
![更新节点](https://img-blog.csdnimg.cn/2020060311302690.png)

> 删除节点

![删除节点](https://img-blog.csdnimg.cn/20200603113205752.png)
注意，删除多个节点的时刻，children是在时刻变化的，删除节点的时候一定要注意。

> 插入节点

获得了某个DOM节点，假设这个DOM节点是空的，通过innerHTML就可以增加一个元素了，但是这个DOM节点已经存在元素了，就会产生覆盖。
![插入节点](https://img-blog.csdnimg.cn/2020060311345583.png)
效果：
![效果](https://img-blog.csdnimg.cn/20200603113602423.png#pic_center)

> 创建一个新的标签，实现插入

![创建新标签](https://img-blog.csdnimg.cn/20200603113737568.png)
![insertBefore](https://img-blog.csdnimg.cn/20200603113816242.png)

## 9、操作表单（验证）

![表单](https://img-blog.csdnimg.cn/20200603133458724.png)

> 获取要提交的信息

> ![提交表单信息](https://img-blog.csdnimg.cn/20200603133529586.png)
> ![提交表单信息](https://img-blog.csdnimg.cn/20200603133545783.png)

提交表单，md5密码加密，表单优化（高级玩法 - hidden）

```
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <!-- MD5工具类 -->
    <script src="https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.min.js"></script>
</head>
<body>
<!-- 表单绑定提交事件
οnsubmit= 绑定一个提交检测的函数，true，false
将这个结果返回给表单，使用onsubmit接受 -->
    <form action="" method="POST" onsubmit="return aaa()">
        用户：<input type="text" id="uname" name="username" placeholder="用户名"/><br/>
        密码：<input type="password" id="pname" name="userpassword" placeholder="密码"/><br/>
        <input type="hidden" id="md5pwd" name="epassword"/>
        <button type="submit">提交</button>
    </form>
</body>

<script>
    function aaa(){
        var u = document.getElementById('uname');
        var p = document.getElementById('pname');
        var md5pwd = document.getElementById('md5pwd');
        md5pwd.value = md5(p.value);
        //可以校验判断表单内容，true就是通过提交，false是阻止提交
        return true;
    }
</script>

```

```
不用按钮提交，一般都用表单级别的提交 ← ← ← 刮刮乐
```

## 10、jQuery

> 获取jQuery - 公式：$(selector).action()

![jquery](https://img-blog.csdnimg.cn/20200603141625278.png#pic_center)
公式：$(选择器).事件(事件函数)

```
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <!-- jQuery的cdn加速地址 -->
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
</head>
<body>
    <a href="" id="test-jquery">点我</a>

    <script>
        //选择器就是css的选择器
        $('#test-jquery').click(
            function(){
                alert('holle，world');
            }
        )

    </script>
</body>

```

> 选择器

![选择器](https://img-blog.csdnimg.cn/20200603142315437.png)
文档工具站：https://jquery.cuishifeng.cn/

> 事件

鼠标事件，键盘事件，其他事件
![鼠标事件](https://img-blog.csdnimg.cn/20200603144309489.png)
比如获取鼠标坐标：

```
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <style>
        #divMove{
            width: 500px;
            height: 500px;
            border: 1px solid red; 
        }
    </style>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
</head>
<body>
    <!-- 要求：获取鼠标当前的一个坐标 -->
    mouse: <span id="mouseMove"></span>
    <div id="divMove">
        在这里移动试试：
    </div>
    <script>
       // 在网页加载完毕之后，响应事件
        $(function(){
            $('#divMove').mousemove(function(e){
                $('#mouseMove').text('x:'+e.pageX+'y:'+e.pageY)
            })
        });
    </script>
</body>

```

> 操作DOM

![操作DOM节点](https://img-blog.csdnimg.cn/20200603150029349.png)
![Ajax](https://img-blog.csdnimg.cn/2020060315005214.png)

## > 小技巧

1、巩固JS

- 看jQuery源码
- 看游戏源码

2、巩固HTML

- CSS —> 扒网站，全部下载下来，修改对应位置的样式，看效果
