## 1.标题

### 1.1使用 # 号标记

使用 **#** 号可表示 1-6 级标题，一级标题对应一个 **#** 号，二级标题对应两个 **#** 号，以此类推。在 用 <kbd>Ctrl</kbd>+ <kbd>数字1-6</kbd>

```
# 一级标题## 二级标题### 三级标题#### 四级标题##### 五级标题###### 六级标题
```

## 2.段落格式

Typora 段落用 <kbd>Ctrl</kbd>+ <kbd>0</kbd>

### 2.1字体

```
*斜体文本*_斜体文本_**粗体文本**__粗体文本__***粗斜体文本***___粗斜体文本___
```

*斜体文本*
*斜体文本*
**粗体文本**
**粗体文本**
***粗斜体文本\***
***粗斜体文本\***

#### 2.2线性

##### 2.2.1 分隔线

```
**** * ******- - -----------
```

------

------

------

------

------

##### 2.2.2 删除线

在文字的两端加上两个波浪线 **~~**

```
~~loser~~
```

~~lower~~

##### 2.2.3 下划线

**<u>** 标签来实现

```
<u>带下划线文本</u>
```

<u>带下划线文本</u>

##### 2.2.4 脚注

脚注是对文本的补充说明

```
[^要注明的文本]
```

只有站在最高处[^才不会仰望他人的风景]

## 3.列表

### 3.1有序列表

数字并加上 **.** 号来表示，标记后面要添加一个空格

```
1. 第一项2. 第二项3. 第三项
```

1. 第一项
2. 第二项
3. 第三项

### 3.2无序列表

无序列表使用星号(*****)、加号(**+**)或是减号(**-**)作为列表标记，这些标记后面要添加一个空格

```
* 第一项* 第二项* 第三项+ 第一项+ 第二项+ 第三项- 第一项- 第二项- 第三项
```

- 第一项
- 第二项
- 第三项

- 第一项
- 第二项
- 第三项

- 第一项
- 第二项
- 第三项

## 4.区块引用

选择使用Typora快捷键<kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>Q</kbd>

在段落开头使用 **>** 符号 ，然后后面紧跟一个**空格**符号

```
> 人生没有白走的路，每一步都算数
```

> 人生没有白走的路，每一步都算数

## 5.代码

可以选择使用Typora快捷键**** <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>K</kbd>

在代码开头使用 ```` `

```
## 6.链接
```

[我是链接](https://www.kuangstudy.com/bbs/选择链接网址)

```
## 7.图片可以选择使用Typora快捷键**** <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>I</kbd>
```

![我是一张图片](https://www.kuangstudy.com/bbs/%E9%80%89%E6%8B%A9%E5%9B%BE%E7%89%87%E8%B7%AF%E5%BE%84)

```
## 8.表格可以选择使用Typora快捷键 <kbd>Ctrl</kbd>+<kbd>T</kbd>Markdown使用 **|** 来分隔不同的单元格，使用 **-** 来分隔表头和其他行。语法格式：
```

| 表头 | 表头 || —— | —— || 单元格 | 单元格 || 单元格 | 单元格 |

```
**表格的对齐方式：**- **-:** 设置内容和标题栏居右对齐。- **:-** 设置内容和标题栏居左对齐。- **:-:** 设置内容和标题栏居中对齐
```

eg:| 左对齐 | 右对齐 | 居中对齐 || :——-| ——: | :——: || 单元格 | 单元格 | 单元格 || 单元格 | 单元格 | 单元格 |

```
| 左对齐 | 右对齐 | 居中对齐 || :----- | -----: | :------: || 单元格 | 单元格 |  单元格  || 单元格 | 单元格 |  单元格  |## 9.高级技巧### 9.1 常用html元素`<kbd> </kbd> 标签定义键盘文本` eg：可以选择使用快捷键<kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>K</kbd> 来插入代码块`<b> </b> 加粗文本`eg：<b>看到这里的你可真是厉害</b>`<i> </i>斜体文本` eg：<i>斜斜的东西好看？</i>` <em> </em> 引用把其中的文本表示为强调的内容。 标签可以用来把这些名称和其他斜体字区别开来。`  eg：<em>好看的你 麻烦点个赞?  </em>`<sup></sup>`上标 eg：<sup>我是上标</sup>`<sub> </sub>下标 ` eg：<sub>我是下标</sub>`<br>换行`    eg：我要<br>换行了### 9.2 软件开发常用图 **Typora** #### 9.2.1 时序图mermaid`->>` 代表实线箭头，`-->>` 则代表虚线。```javasequenceDiagram    Alice->>John: Hello John, how are you?    John-->>Alice: Great!
sequenceDiagram    Alice->>John: Hello John, how are you?    John-->>Alice: Great!
```

#### 9.2.2 流程图 mermaid

`graph` 关键字就是声明一张流程图，`TD 从上至下竖向 LR从左至右横向 箭头 -->`。

eg：

```
graph LRF[横向流程图]A[方形] -->B(圆角)    B --> C{条件a}    C -->|a=1| D[结果1]    C -->|a=2| E[结果2]
graph TDF[竖向流程图]A[方形] -->B(圆角)    B --> C{条件a}    C -->|a=1| D[结果1]    C -->|a=2| E[结果2]
graph LRF[横向流程图]A[方形] -->B(圆角)    B --> C{条件a}    C -->|a=1| D[结果1]    C -->|a=2| E[结果2]
graph TDF[竖向流程图]A[方形] -->B(圆角)    B --> C{条件a}    C -->|a=1| D[结果1]    C -->|a=2| E[结果2]
```

#### 9.2.3 甘特图 mermaid

```
%% 语法示例        gantt        dateFormat  YYYY-MM-DD        title JAVA学习路线图计划图        section Java基础        基础知识                      :done,    des1, 2014-01-06,2014-01-08        项目                     :         des2, after des2, 5d        section JAVA Web       基础知识                      :done,    des1, 2014-01-06,2014-01-08        项目                                 :2d        section 框架        基础知识                      :done,    des1, 2014-01-06,2014-01-08        项目                               : 48h
```

#### 9.2.4 饼图 mermaid

饼图使用 `pie` 表示，title下面分别是区域名称及其百分比。

```
pie    title 饼图举例    "A" : 25    "B" : 25    "C" : 25    "D" :  25
pie    title 饼图举例    "A" : 25    "B" : 25    "C" : 25    "D" :  25
```

#### 9.2.5 类图

语法：`<|--` 表示继承，`+` 表示 `public`，`-` 表示 `private`

```
classDiagram      Animal <|-- Duck      Animal <|-- Fish      Animal <|-- Zebra      Animal : +int age      Animal : +String gender      Animal: +isMammal()      Animal: +mate()      class Duck{          +String beakColor          +swim()          +quack()      }      class Fish{          -int sizeInFeet          -canEat()      }      class Zebra{          +bool is_wild          +run()      }
classDiagram      Animal <|-- Duck      Animal <|-- Fish      Animal <|-- Zebra      Animal : +int age      Animal : +String gender      Animal: +isMammal()      Animal: +mate()      class Duck{          +String beakColor          +swim()          +quack()      }      class Fish{          -int sizeInFeet          -canEat()      }      class Zebra{          +bool is_wild          +run()      }
```

#### 9.2.6状态图

语法：`[*]` 表示开始或者结束，如果在箭头右边则表示结束。

```
stateDiagram    [*] --> s1    s1 --> [*]
stateDiagram    [*] --> s1    s1 --> [*]
```