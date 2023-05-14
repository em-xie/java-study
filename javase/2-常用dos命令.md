# 打开CMD的方式

**常用的快捷键**

- **功能键**

  F1 显示帮助
  F2 重命名
  F4+Alt 关闭窗口
  F5 刷新
  F6 切换
  F10+Shift =Alt+F10 右键菜单
  F11 全屏、Esc退出
  F12 Excel 或Word文档是另存为
  网页页面是打开调试环境（审查元素）

  **方向键**

  方向键 + Win 使窗口全屏、最小化、靠左半边、靠右半边（部分版本不支持）
  方向键+Shift 连续选中
  方向键（上下）+Ctrl 在段落开头间跳跃

- Ctrl+Alt+Delete打开安全选项
  Ctrl+Shift+Esc 打开任务管理器
  Ctrl+Shift+N 新建一个新的文件
  Ctrl+Shift 切换中英文输入法
  Ctrl+Space（空格）切换输入法和非输入法
  Ctrl+Esc 打开开始菜单
  Ctrl+A 全选
  Ctrl+C 复制
  Ctrl+N 新建
  Ctrl+P 打印
  Ctrl+S 保存
  Ctrl+V 粘贴
  Ctrl+W 关闭当前的窗口
  Ctrl+X 剪切
  Ctrl+Z 撤销

- Alt+D：定位到地址栏。
  Alt+向左键：查看上一个
  Alt+向右键：查看下一个

- Win键+A：操作中心
  Win键+B：右下角托盘区
  Win键+C：通过语音激活Cortana （可能要在设置里打开）
  Win键+D：显示桌面
  Win键+E：打开文件管理器
  Win键+G：打开Xbox游戏录制工具栏，供用户录制游戏视频或截屏激活截屏功能
  Win键+H：打开听写
  Win键+I：打开Windows10设置
  Win键+K：激活无线显示器连接或音频设备连接
  Win键+L：锁定屏幕
  Win键+P：投影屏幕
  Win键+R：运行
  Win键+S：搜索
  Win键+T：快速切换任务栏程序
  Win键+V：打开云剪贴板
  Win键+X：打开高级用户功能
  Win+ Pause：看配置
  Win键+Tab：激活任务视图
  Win键+分号：调出win10自带表情
  Win键+1/2/3：打开任务栏中固定的程序（1代表任务栏中第一个应用图标,以此类推）
  Win键+Shift+S：Windows自带截图

- 调出cmd命令：

  **win+R**

  **win10 放大镜**

  Win+加号/减号 放大或缩小
  Ctrl+ Alt+空格键 显示鼠标指针
  Ctrl+Alt+F 切换到全屏模式
  Ctrl+Alt+L 切换到镜头模式
  Ctrl+Alt+D 切换到停靠模式
  Ctrl+Alt+I 反色
  Ctrl+Alt+箭头键 按箭头键的方向平移
  Ctrl+Alt+R 调整镜头的大小
  Win + Esc 退出放大镜

  **cmd常用命令**

  winver windows版本信息
  calc 计算器
  mspaint 画图
  regedit 注册表编辑器
  gpedit.msc 策略组编辑器

**Dos命令**

- 常用内部命令：
  dir 列当前目录下的目录和文件 常用参数 /p 分页 /w 宽列表
  cd 显示当前目录 / cd 切换当前目录
  copy 复制文件
  del 删除文件
  ren 重命名文件
  md 建立目录
  rd 删除目录（必须为空） 常用参数 /r 递归删除
  type 显示文件内容

  常用外部命令：
  format 格式化
  move 移动文件（夹）/重命名文件夹
  xcopy 批量复制文件（夹）

- ![image-20210411131609909](https://img-blog.csdnimg.cn/img_convert/7d806af2cc7f3e494c066bd7699e3e6f.png)

- ![image-20210411132352178](https://img-blog.csdnimg.cn/img_convert/6fcd248b640fcbf62b64aca64f4f1591.png)

- ![image-20210411132543205](https://img-blog.csdnimg.cn/img_convert/d3219fba2f405baa0de70f07b4d633f1.png)

- ![image-20210411132748908](https://img-blog.csdnimg.cn/img_convert/17a955f11f9c7ecdccd14cbc7884cd21.png)

1. 开始 + 系统 + 命令提示符
2. Win键 + R 输入cmd打开控制台 （推荐使用，较为简单）
3. 在任意的文件夹下面，按住shift键 + 鼠标右键点击在此处打开 Powershell窗口，在此处打开命令行窗口
4. 在本地磁盘下的文件夹地址栏前面加上cmd + 一个空格 然后回车就打开了命令行窗口，且对应就是该文件

管理员方式运行：开始 + Window系统 + 命令提示符 + 鼠标右键 + 更多 + 以管理员身份运行

常用的Dos命令

```
#盘符切换 盘名 + 英文冒号 + 回车#查看当前目录下的所有文件  dir + 回车#切换目录 cd（change director缩写） 注意一点：当跨盘符或者跨目录时(都要在cd后面加上 一个空格 + /d 才行，这里的 /d 属于一种参数)在cd + 一个空格 + /d + 盘名 + 英文冒号 既跨盘，要是还想进入该盘下的某个文件夹就在：英文冒号后加上 \ + 文件夹名 + 回车 ，在同一目录下时，cd + 文件名 + 回车#返回上一级 cd + .. + 回车#清理屏幕 cls (clear screen 的缩写) + 回车#退出终端(退出命令提示符窗口)  exit + 回车#查看电脑的IP ipconfig + 回车#打开电脑常用应用：     calc (打开计算器)     mspaint  (打开电脑的画图工具)     notepad  (新建并打开记事本)#ping 命令    ping命令一般用于测试网络是否正常上面，可以ping一些网站得到该网站的一些IP信息 eg：ping www.baidu.com#复制 在命令提示符窗口内不能使用 ctrl + c 和 ctrl + v 来进行复制粘贴，在窗口外将需要复制进命令行窗口的东西鼠标右键复制后进入提示符命令行窗口直接鼠标右键就粘贴进去了!#文件操作   md  目录名 (创建目录)   rd  目录名 (移除目录)   cd> 文件名 (创建文件)   del 文件名 (删除文件)   <像用rd和del方式删除目录或者文件的方法是比较干净的，因为它不会进入回收站而是直接抹除掉>   (注意点：当你要删除目录时，一定要进入既cd进该目录，并且将目录里的文件全部删除后cd..回上一级目录再 rd + 目录名 才行)
```