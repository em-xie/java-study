# Docker 

### Docker 概述

### Docker安装

### Docker命令

### 镜像命令

### 容器命令

### Docker镜像

### 容器数据卷

### DockerFile

### Docker网络原理

### IDEA整合Docker（单机Docker)

### Docker Compose

### Docker Swarm

### CI\CD Jenkins



#### Docker概述

Docker为什么出现？
一款产品： 开发–上线 两套环境！应用环境，应用配置！

开发 — 运维。 问题：我在我的电脑上可以允许！版本更新，导致服务不可用！对于运维来说考验十分大？

环境配置是十分的麻烦，每一个及其都要部署环境(集群Redis、ES、Hadoop…) !费事费力。

发布一个项目( jar + (Redis MySQL JDK ES) ),项目能不能带上环境安装打包！

之前在服务器配置一个应用的环境 Redis MySQL JDK ES Hadoop 配置超麻烦了，不能够跨平台。

开发环境Windows，最后发布到Linux！

传统：开发jar，运维来做！

现在：开发打包部署上线，一套流程做完！

安卓流程：java — apk —发布（应用商店）一 张三使用apk一安装即可用！

docker流程： java-jar（环境） — 打包项目帯上环境（镜像） — ( Docker仓库：商店）-----

Docker给以上的问题，提出了解决方案！



Docker的思想就来自于集装箱！

JRE – 多个应用(端口冲突) – 原来都是交叉的！
隔离：Docker核心思想！打包装箱！每个箱子是互相隔离的。

Docker通过隔离机制，可以将服务器利用到极致！

本质：所有的技术都是因为出现了一些问题，我们需要去解决，才去学习！
Docker历史
2010年，几个的年轻人，就在美国成立了一家公司 dotcloud

做一些pass的云计算服务！LXC（Linux Container容器）有关的容器技术！

Linux Container容器是一种内核虚拟化技术，可以提供轻量级的虚拟化，以便隔离进程和资源。

他们将自己的技术（容器化技术）命名就是 Docker
Docker刚刚延生的时候，没有引起行业的注意！dotCloud，就活不下去！

开源

2013年，Docker开源！

越来越多的人发现docker的优点！火了。Docker每个月都会更新一个版本！

2014年4月9日，Docker1.0发布！

docker为什么这么火？十分的轻巧！

在容器技术出来之前，我们都是使用虚拟机技术！

虚拟机：在window中装一个VMware，通过这个软件我们可以虚拟出来一台或者多台电脑！笨重！

虚拟机也属于虚拟化技术，Docker容器技术，也是一种虚拟化技术！

vm : linux centos 原生镜像（一个电脑！） 隔离、需要开启多个虚拟机！ 几个G 几分钟
docker: 隔离，镜像（最核心的环境 4m + jdk + mysql）十分的小巧，运行镜像就可以了！小巧！
聊聊Docker

Docker基于Go语言开发的！开源项目！

docker官网：https://www.docker.com/

文档：https://docs.docker.com/ Docker的文档是超级详细的！

仓库：https://hub.docker.com/


Docker能干嘛
之前的虚拟机技术！

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE1Mzg1Mjk1NC5wbmc?x-oss-process=image/format,png)

虚拟机技术缺点：

1、 资源占用十分多

2、 冗余步骤多

3、 启动很慢！

容器化技术

容器化技术不是模拟一个完整的操作系统

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTA5NDMzNjg0Ni5wbmc?x-oss-process=image/format,png)

比较Docker和虚拟机技术的不同：

传统虚拟机，虚拟出一条硬件，运行一个完整的操作系统，然后在这个系统上安装和运行软件
容器内的应用直接运行在宿主机的内容，容器是没有自己的内核的，也没有虚拟我们的硬件，所以就轻便了
每个容器间是互相隔离，每个容器内都有一个属于自己的文件系统，互不影响
DevOps（开发、运维）

应用更快速的交付和部署

传统：一对帮助文档，安装程序。

Docker：打包镜像发布测试一键运行。

更便捷的升级和扩缩容

使用了 Docker之后，我们部署应用就和搭积木一样
项目打包为一个镜像，扩展服务器A！服务器B

更简单的系统运维
在容器化之后，我们的开发，测试环境都是高度一致的

更高效的计算资源利用

Docker是内核级别的虚拟化，可以在一个物理机上可以运行很多的容器实例！服务器的性能可以被压榨到极致。


Docker安装
Docker的基本组成

镜像（image)：

docker镜像就好比是一个目标，可以通过这个目标来创建容器服务，tomcat镜像==>run==>容器（提供服务器），通过这个镜像可以创建多个容器（最终服务运行或者项目运行就是在容器中的）。

容器(container)：

Docker利用容器技术，独立运行一个或者一组应用，通过镜像来创建的.
启动，停止，删除，基本命令
目前就可以把这个容器理解为就是一个简易的 Linux系统。

仓库(repository)：

仓库就是存放镜像的地方！
仓库分为公有仓库和私有仓库。(很类似git)
Docker Hub是国外的。
阿里云…都有容器服务器(配置镜像加速!)



## 安装Docker

> 环境准备

Linux要求内核3.0以上

➜  ~ uname -r    
4.15.0-96-generic # 要求3.0以上
`➜  ~ cat /etc/os-release` 
`NAME="Ubuntu"`
`VERSION="18.04.4 LTS (Bionic Beaver)"`
`ID=ubuntu`
`ID_LIKE=debian`
`PRETTY_NAME="Ubuntu 18.04.4 LTS"`
`VERSION_ID="18.04"`
`HOME_URL="https://www.ubuntu.com/"`
`SUPPORT_URL="https://help.ubuntu.com/"`
`BUG_REPORT_URL="https://bugs.launchpad.net/ubuntu/"`
`PRIVACY_POLICY_URL="https://www.ubuntu.com/legal/terms-and-policies/privacy-policy"VERSION_CODENAME=bionic`
`UBUNTU_CODENAME=bionic`



安装

帮助文档：https://docs.docker.com/engine/install/





#1.卸载旧版本



```
yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```



#2.需要的安装包

```
yum install -y yum-utils
```

#3.设置镜像的仓库

```
yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
```

#默认是从国外的，不推荐
#推荐使用国内的

```
yum-config-manager \
    --add-repo \
    https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

#更新yum软件包索引

```
yum makecache fast
```

#4.安装docker相关的 docker-ce 社区版 而ee是企业版

```
yum install docker-ce docker-ce-cli containerd.io
```

#5、启动docker

```
docker systemctl start docker
```

#6. 使用docker version查看是否按照成功

```
docker version
```

#7. 测试

```
docker run hello-world
```



#7. 测试

```
➜  ~ docker run hello-world
Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:

  1. The Docker client contacted the Docker daemon.
  2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
     (amd64)
  3. The Docker daemon created a new container from that image which runs the
     executable that produces the output you are currently reading.
  4. The Docker daemon streamed that output to the Docker client, which sent it
     to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
```

#8.查看一下下载的镜像

```
➜  ~ docker images         
REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
hello-world           latest              bf756fb1ae65        4 months ago        13.3kB
```

了解：卸载docker

```
#1. 卸载依赖
yum remove docker-ce docker-ce-cli containerd.io
#2. 删除资源
rm -rf /var/lib/docker

# /var/lib/docker 是docker的默认工作路径！
```



## 阿里云镜像加速

1、登录阿里云找到容器服务

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTEwMjExMjg1MS5wbmc?x-oss-process=image/format,png)

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTEwMjAwOTQ3MC5wbmc?x-oss-process=image/format,png)

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTEwMjIxNjg5OC5wbmc?x-oss-process=image/format,png)

## 回顾HelloWorld流程

![image-20200515102503722](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTEwMjUwMzcyMi5wbmc?x-oss-process=image/format,png)

**docker run 流程图**

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTEwMjYzNzI0Ni5wbmc?x-oss-process=image/format,png)

#### 底层原理

Docker是怎么工作的？

Docker是一个Client-Server结构的系统，Docker的守护进程运行在主机上。通过Socket从客户端访问！

Docker-Server接收到Docker-Client的指令，就会执行这个命令！


为什么Docker比Vm快
1、docker有着比虚拟机更少的抽象层。由于docker不需要Hypervisor实现硬件资源虚拟化,运行在docker容器上的程序直接使用的都是实际物理机的硬件资源。因此在CPU、内存利用率上docker将会在效率上有明显优势。
2、docker利用的是宿主机的内核,而不需要Guest OS。

GuestOS： VM（虚拟机）里的的系统（OS）;

HostOS：物理机里的系统（OS）；
![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTEwNDExNzMyOS5wbmc?x-oss-process=image/format,png)

因此,当新建一个 容器时,docker不需要和虚拟机一样重新加载一个操作系统内核。仍而避免引导、加载操作系统内核返个比较费时费资源的过程,当新建一个虚拟机时,虚拟机软件需要加载GuestOS,返个新建过程是分钟级别的。而docker由于直接利用宿主机的操作系统,则省略了这个复杂的过程,因此新建一个docker容器只需要几秒钟。
Docker的常用命令
帮助命令

```
docker version    #显示docker的版本信息。
docker info       #显示docker的系统信息，包括镜像和容器的数量
docker 命令 --help #帮助命令
```

帮助文档的地址：https://docs.docker.com/engine/reference/commandline/build/

```shell
docker version    #显示docker的版本信息。
docker info       #显示docker的系统信息，包括镜像和容器的数量
docker 命令 --help #帮助命令
```



镜像命令

```
docker images #查看所有本地主机上的镜像 可以使用docker image ls代替

docker search 搜索镜像

docker pull 下载镜像 docker image pull

docker rmi 删除镜像 docker image rm
```

**docker images** 查看所有本地的主机上的镜像

```
➜  ~ docker images
REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
mysql                 5.7                 e73346bdf465        24 hours ago        448MB
```



#### 解释
```
#REPOSITORY			# 镜像的仓库源
#TAG				# 镜像的标签
#IMAGE ID			# 镜像的id
#CREATED			# 镜像的创建时间
#SIZE				# 镜像的大小
```


#### 可选项
```
Options:
  -a, --all             Show all images (default hides intermediate images) #列出所有镜像
  -q, --quiet           Only show numeric IDs # 只显示镜像的id

➜  ~ docker images -aq ＃显示所有镜像的id
e73346bdf465
d03312117bb0
d03312117bb0
602e111c06b6
2869fc110bf7
470671670cac
bf756fb1ae65
5acf0e8da90b
```

**docker search 搜索镜像**

```
➜  ~ docker search mysql
NAME                              DESCRIPTION                                     STARS               OFFICIAL            AUTOMATED
mysql                             MySQL is a widely used, open-source relation…   9500                [OK]                
mariadb                           MariaDB is a community-developed fork of MyS…   3444                [OK]  
```


#### --filter=STARS=3000 #搜索出来的镜像就是STARS大于3000的
```
Options:
  -f, --filter filter   Filter output based on conditions provided
      --format string   Pretty-print search using a Go template
      --limit int       Max number of search results (default 25)
      --no-trunc        Don't truncate output
      

```

```
➜  ~ docker search mysql --filter=STARS=3000
NAME                DESCRIPTION                                     STARS               OFFICIAL            AUTOMATED
mysql               MySQL is a widely used, open-source relation…   9500                [OK]             
mariadb             MariaDB is a community-developed fork of MyS…   3444                [OK]
```





**docker pull** 下载镜像



#### 下载镜像 docker pull 镜像名[:tag]
```
➜  ~ docker pull tomcat:8
8: Pulling from library/tomcat #如果不写tag，默认就是latest
90fe46dd8199: Already exists   #分层下载： docker image 的核心 联合文件系统
35a4f1977689: Already exists 
bbc37f14aded: Already exists 
74e27dc593d4: Already exists 
93a01fbfad7f: Already exists 
1478df405869: Pull complete 
64f0dd11682b: Pull complete 
68ff4e050d11: Pull complete 
f576086003cf: Pull complete 
3b72593ce10e: Pull complete 
Digest: sha256:0c6234e7ec9d10ab32c06423ab829b32e3183ba5bf2620ee66de866df640a027  # 签名 防伪
Status: Downloaded newer image for tomcat:8
docker.io/library/tomcat:8 #真实地址

#等价于
docker pull tomcat:8
docker pull docker.io/library/tomcat:8


```

```
docker rmi 删除镜像**

➜  ~ docker rmi -f 镜像id #删除指定的镜像
➜  ~ docker rmi -f 镜像id 镜像id 镜像id 镜像id#删除指定的镜像
➜  ~ docker rmi -f $(docker images -aq) #删除全部的镜像
```



## 容器命令

docker run 镜像id 新建容器并启动

docker ps 列出所有运行的容器 docker container list

docker rm 容器id 删除指定容器

docker start 容器id #启动容器
docker restart 容器id #重启容器
docker stop 容器id #停止当前正在运行的容器
docker kill 容器id #强制停止当前容器
说明：我们有了镜像才可以创建容器，Linux，下载centos镜像来学习





```
➜  ~ docker container     
Usage:  docker container COMMAND
Manage containers
Commands:
  attach      Attach local standard input, output, and error streams to a running container
  commit      Create a new image from a container's changes
  cp          Copy files/folders between a container and the local filesystem
  create      Create a new container
  diff        Inspect changes to files or directories on a container's filesystem
  exec        Run a command in a running container
  export      Export a container's filesystem as a tar archive
  inspect     Display detailed information on one or more containers
  kill        Kill one or more running containers
  logs        Fetch the logs of a container
  ls          List containers
  pause       Pause all processes within one or more containers
  port        List port mappings or a specific mapping for the container
  prune       Remove all stopped containers
  rename      Rename a container
  restart     Restart one or more containers
  rm          Remove one or more containers
  run         Run a command in a new container
  start       Start one or more stopped containers
  stats       Display a live stream of container(s) resource usage statistics
  stop        Stop one or more running containers
  top         Display the running processes of a container
  unpause     Unpause all processes within one or more containers
  update      Update configuration of one or more containers
  wait        Block until one or more containers stop, then print their exit codes
Run 'docker container COMMAND --help' for more information on a command.
```



**新建容器并启动**



```
docker run [可选参数] image | docker container run [可选参数] image 
#参书说明
--name="Name"		容器名字 tomcat01 tomcat02 用来区分容器
-d					后台方式运行
-it 				使用交互方式运行，进入容器查看内容
-p					指定容器的端口 -p 8080(宿主机):8080(容器)
		-p ip:主机端口:容器端口
		-p 主机端口:容器端口(常用)
		-p 容器端口
		容器端口
-P(大写) 				随机指定端口
```


#### 测试、启动并进入容器
```
➜  ~ docker run -it centos /bin/bash
Unable to find image 'centos:latest' locally
latest: Pulling from library/centos
8a29a15cefae: Already exists 
Digest: sha256:fe8d824220415eed5477b63addf40fb06c3b049404242b31982106ac204f6700
Status: Downloaded newer image for centos:latest
[root@95039813da8d /]# ls
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
[root@95039813da8d /]# exit #从容器退回主机
exit
➜  ~ ls
shell  user.txt
```



**列出所有运行的容器**

```
#docker ps命令 #列出当前正在运行的容器
  -a, --all             Show all containers (default shows just running)
  -n, --last int        Show n last created containers (includes all states) (default -1)
  -q, --quiet           Only display numeric IDs

  ➜  ~ docker ps   
CONTAINER ID        IMAGE                 COMMAND                  CREATED             STATUS              PORTS                    NAMES
68729e9654d4        portainer/portainer   "/portainer"             14 hours ago        Up About a minute   0.0.0.0:8088->9000/tcp   funny_curie
d506a017e951        nginx                 "nginx -g 'daemon of…"   15 hours ago        Up 15 hours         0.0.0.0:3344->80/tcp     nginx01
➜  ~ docker ps -a
CONTAINER ID        IMAGE                 COMMAND                  CREATED             STATUS                       PORTS                    NAMES
95039813da8d        centos                "/bin/bash"              3 minutes ago       Exited (0) 2 minutes ago                              condescending_pike
1e46a426a5ba        tomcat                "catalina.sh run"        11 minutes ago      Exited (130) 9 minutes ago                            sweet_gould
14bc9334d1b2        bf756fb1ae65          "/hello"                 3 hours ago         Exited (0) 3 hours ago                                amazing_stonebraker
f10d60f473f5        bf756fb1ae65          "/hello"                 3 hours ago         Exited (0) 3 hours ago                                dreamy_germain
68729e9654d4        portainer/portainer   "/portainer"             14 hours ago        Up About a minute            0.0.0.0:8088->9000/tcp   funny_curie
677cde5e4f1d        elasticsearch         "/docker-entrypoint.…"   15 hours ago        Exited (143) 8 minutes ago                            elasticsearch
33eb3f70b4db        tomcat                "catalina.sh run"        15 hours ago        Exited (143) 8 minutes ago                            tomcat01
d506a017e951        nginx                 "nginx -g 'daemon of…"   15 hours ago        Up 15 hours                  0.0.0.0:3344->80/tcp     nginx01
24ce2db02e45        centos                "/bin/bash"              16 hours ago        Exited (0) 15 hours ago                               hopeful_faraday
42267d1ad80b        bf756fb1ae65          "/hello"                 16 hours ago        Exited (0) 16 hours ago                               ecstatic_sutherland
➜  ~ docker ps -aq
95039813da8d
1e46a426a5ba
14bc9334d1b2
f10d60f473f5
68729e9654d4
677cde5e4f1d
33eb3f70b4db
d506a017e951
24ce2db02e45
42267d1ad80b
```



**退出容器**

```
exit #容器直接退出
ctrl +P +Q #容器不停止退出


```

**删除容器**

```
docker rm 容器id   #删除指定的容器，不能删除正在运行的容器，如果要强制删除 rm -rf
docker rm -f $(docker ps -aq)  #删除指定的容器
docker ps -a -q|xargs docker rm  #删除所有的容器
```



**启动和停止容器的操作**

```
docker start 容器id	#启动容器
docker restart 容器id	#重启容器
docker stop 容器id	#停止当前正在运行的容器
docker kill 容器id	#强制停止当前容器
```



#### 常用其他命令

**后台启动命令**



#### 命令 docker run -d 镜像名
```
➜  ~ docker run -d centos
a8f922c255859622ac45ce3a535b7a0e8253329be4756ed6e32265d2dd2fac6c
➜  ~ docker ps           
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```



#### 问题docker ps. 发现centos 停止了
#### 常见的坑，docker容器使用后台运行，就必须要有要一个前台进程，docker发现没有应用，就会自动停止
#### nginx，容器启动后，发现自己没有提供服务，就会立刻停止，就是没有程序了

![img](https://raw.githubusercontent.com/chengcodex/cloudimg/master/img/image-20200514214313962.png)

 

```
 attach      Attach local standard input, output, and error streams to a running container
  #当前shell下 attach连接指定运行的镜像
  build       Build an image from a Dockerfile # 通过Dockerfile定制镜像
  commit      Create a new image from a container's changes #提交当前容器为新的镜像
  cp          Copy files/folders between a container and the local filesystem #拷贝文件
  create      Create a new container #创建一个新的容器
  diff        Inspect changes to files or directories on a container's filesystem #查看docker容器的变化
  events      Get real time events from the server # 从服务获取容器实时时间
  exec        Run a command in a running container # 在运行中的容器上运行命令
  export      Export a container's filesystem as a tar archive #导出容器文件系统作为一个tar归档文件[对应import]
  history     Show the history of an image # 展示一个镜像形成历史
  images      List images #列出系统当前的镜像
  import      Import the contents from a tarball to create a filesystem image #从tar包中导入内容创建一个文件系统镜像
  info        Display system-wide information # 显示全系统信息
  inspect     Return low-level information on Docker objects #查看容器详细信息
  kill        Kill one or more running containers # kill指定docker容器
  load        Load an image from a tar archive or STDIN #从一个tar包或标准输入中加载一个镜像[对应save]
  login       Log in to a Docker registry #
  logout      Log out from a Docker registry
  logs        Fetch the logs of a container
  pause       Pause all processes within one or more containers
  port        List port mappings or a specific mapping for the container
  ps          List containers
  pull        Pull an image or a repository from a registry
  push        Push an image or a repository to a registry
  rename      Rename a container
  restart     Restart one or more containers
  rm          Remove one or more containers
  rmi         Remove one or more images
  run         Run a command in a new container
  save        Save one or more images to a tar archive (streamed to STDOUT by default)
  search      Search the Docker Hub for images
  start       Start one or more stopped containers
  stats       Display a live stream of container(s) resource usage statistics
  stop        Stop one or more running containers
  tag         Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE
  top         Display the running processes of a container
  unpause     Unpause all processes within one or more containers
  update      Update configuration of one or more containers
  version     Show the Docker version information
  wait        Block until one or more containers stop, then print their exit codes
```



#### Docker 安装Nginx

#1. 搜索镜像 search 建议大家去docker搜索，可以看到帮助文档
#2. 拉取镜像 pull
#3、运行测试

#### -d 后台运行
#### --name 给容器命名
#### -p 宿主机端口：容器内部端口
```
➜  ~ docker run -d --name nginx00 -p 82:80 nginx
75943663c116f5ed006a0042c42f78e9a1a6a52eba66311666eee12e1c8a4502
➜  ~ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                NAMES
75943663c116        nginx               "nginx -g 'daemon of…"   41 seconds ago      Up 40 seconds       0.0.0.0:82->80/tcp   nginx00
➜  ~ curl localhost:82   #测试
```



<!DOCTYPE html>,,,,





#### docker 来装一个tomcat

#### 官方的使用
docker run -it --rm tomcat:9.0
#### 之前的启动都是后台，停止了容器，容器还是可以查到， docker run -it --rm image 一般是用来测试，用完就删除
```
--rm       Automatically remove the container when it exits
#下载
docker pull tomcat
#启动运行
docker run -d -p 8080:8080 --name tomcat01 tomcat
#测试访问有没有问题
curl localhost:8080

#进入容器
➜  ~ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS                    NAMES
db09851cf82e        tomcat              "catalina.sh run"   28 seconds ago      Up 27 seconds       0.0.0.0:8080->8080/tcp   tomcat01
➜  ~ docker exec -it db09851cf82e /bin/bash             
root@db09851cf82e:/usr/local/tomcat# 
```



#### 发现问题：1、linux命令少了。 2.没有webapps



#### 部署es+kibana

#### es 暴露的端口很多！
#### es 的数据一般需要放置到安全目录！挂载
#### --net somenetwork ? 网络配置

#### 启动elasticsearch
docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.6.2
#### 测试一下es是否成功启动
```
➜  ~ curl localhost:9200
{
  "name" : "d73ad2f22dd3",
  "cluster_name" : "docker-cluster",
  "cluster_uuid" : "atFKgANxS8CzgIyCB8PGxA",
  "version" : {
    "number" : "7.6.2",
    "build_flavor" : "default",
    "build_type" : "docker",
    "build_hash" : "ef48eb35cf30adf4db14086e8aabd07ef6fb113f",
    "build_date" : "2020-03-26T06:34:37.794943Z",
    "build_snapshot" : false,
    "lucene_version" : "8.4.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```

```
➜  ~ docker stats # 查看docker容器使用内存情况
```





```
#关闭，添加内存的限制，修改配置文件 -e 环境配置修改
➜  ~ docker rm -f d73ad2f22dd3                                                  
➜  ~ docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms64m -Xmx512m" elasticsearch:7.6.2
```





```
➜  ~ curl localhost:9200
{
  "name" : "b72c9847ec48",
  "cluster_name" : "docker-cluster",
  "cluster_uuid" : "yNAK0EORSvq3Wtaqe2QqAg",
  "version" : {
    "number" : "7.6.2",
    "build_flavor" : "default",
    "build_type" : "docker",
    "build_hash" : "ef48eb35cf30adf4db14086e8aabd07ef6fb113f",
    "build_date" : "2020-03-26T06:34:37.794943Z",
    "build_snapshot" : false,
    "lucene_version" : "8.4.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```



![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE1MzcyNTk5MS5wbmc?x-oss-process=image/format,png)



## 可视化

- portainer(先用这个)
- docker run -d -p 8080:9000 \
  --restart=always -v /var/run/docker.sock:/var/run/docker.sock --privileged=true portainer/portainer



- Rancher(CI/CD再用)

**什么是portainer？**

Docker图形化界面管理工具！提供一个后台面板供我们操作！

docker run -d -p 8080:9000 \
--restart=always -v /var/run/docker.sock:/var/run/docker.sock --privileged=true portainer/portainer



测试访问： 外网：8080

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE1MzcyNTk5MS5wbmc?x-oss-process=image/format,png)

进入之后的面板



![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE1NTExMzY5My5wbmc?x-oss-process=image/format,png)



## 镜像原理之联合文件系统

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706223610670-724751442.png)

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706223623355-1905315240.png)

#### 分层理解

> 分层的镜像

我们可以去下载一个镜像，注意观察下载的日志输出，可以看到是一层层的在下载

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE2MzgzOTE4MC5wbmc?x-oss-process=image/format,png)

思考：为什么Docker镜像要采用这种分层的结构呢？

最大的好处，我觉得莫过于资源共享了！比如有多个镜像都从相同的Base镜像构建而来，那么宿主机只需在磁盘上保留一份base镜像，同时内存中也只需要加载一份base镜像，这样就可以为所有的容器服务了，而且镜像的每一层都可以被共享。

查看镜像分层的方式可以通过docker image inspect 命令

#### 分层理解

> 分层的镜像

我们可以去下载一个镜像，注意观察下载的日志输出，可以看到是一层层的在下载

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE2MzgzOTE4MC5wbmc?x-oss-process=image/format,png)

思考：为什么Docker镜像要采用这种分层的结构呢？

最大的好处，我觉得莫过于资源共享了！比如有多个镜像都从相同的Base镜像构建而来，那么宿主机只需在磁盘上保留一份base镜像，同时内存中也只需要加载一份base镜像，这样就可以为所有的容器服务了，而且镜像的每一层都可以被共享。

查看镜像分层的方式可以通过docker image inspect 命令分层理解

> 分层的镜像

我们可以去下载一个镜像，注意观察下载的日志输出，可以看到是一层层的在下载

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNTE2MzgzOTE4MC5wbmc?x-oss-process=image/format,png)

思考：为什么Docker镜像要采用这种分层的结构呢？

最大的好处，我觉得莫过于资源共享了！比如有多个镜像都从相同的Base镜像构建而来，那么宿主机只需在磁盘上保留一份base镜像，同时内存中也只需要加载一份base镜像，这样就可以为所有的容器服务了，而且镜像的每一层都可以被共享。

查看镜像分层的方式可以通过docker image inspect 命令

```
➜  / docker image inspect redis          
[
    {
        "Id": "sha256:f9b9909726890b00d2098081642edf32e5211b7ab53563929a47f250bcdc1d7c",
        "RepoTags": [
            "redis:latest"
        ],
        "RepoDigests": [
            "redis@sha256:399a9b17b8522e24fbe2fd3b42474d4bb668d3994153c4b5d38c3dafd5903e32"
        ],
        "Parent": "",
        "Comment": "",
        "Created": "2020-05-02T01:40:19.112130797Z",
        "Container": "d30c0bcea88561bc5139821227d2199bb027eeba9083f90c701891b4affce3bc",
        "ContainerConfig": {
            "Hostname": "d30c0bcea885",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "ExposedPorts": {
                "6379/tcp": {}
            },
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin",
                "GOSU_VERSION=1.12",
                "REDIS_VERSION=6.0.1",
                "REDIS_DOWNLOAD_URL=http://download.redis.io/releases/redis-6.0.1.tar.gz",
                "REDIS_DOWNLOAD_SHA=b8756e430479edc162ba9c44dc89ac394316cd482f2dc6b91bcd5fe12593f273"
            ],
            "Cmd": [
                "/bin/sh",
                "-c",
                "#(nop) ",
                "CMD [\"redis-server\"]"
            ],
            "ArgsEscaped": true,
            "Image": "sha256:704c602fa36f41a6d2d08e49bd2319ccd6915418f545c838416318b3c29811e0",
            "Volumes": {
                "/data": {}
            },
            "WorkingDir": "/data",
            "Entrypoint": [
                "docker-entrypoint.sh"
            ],
            "OnBuild": null,
            "Labels": {}
        },
        "DockerVersion": "18.09.7",
        "Author": "",
        "Config": {
            "Hostname": "",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "ExposedPorts": {
                "6379/tcp": {}
            },
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin",
                "GOSU_VERSION=1.12",
                "REDIS_VERSION=6.0.1",
                "REDIS_DOWNLOAD_URL=http://download.redis.io/releases/redis-6.0.1.tar.gz",
                "REDIS_DOWNLOAD_SHA=b8756e430479edc162ba9c44dc89ac394316cd482f2dc6b91bcd5fe12593f273"
            ],
            "Cmd": [
                "redis-server"
            ],
            "ArgsEscaped": true,
            "Image": "sha256:704c602fa36f41a6d2d08e49bd2319ccd6915418f545c838416318b3c29811e0",
            "Volumes": {
                "/data": {}
            },
            "WorkingDir": "/data",
            "Entrypoint": [
                "docker-entrypoint.sh"
            ],
            "OnBuild": null,
            "Labels": null
        },
        "Architecture": "amd64",
        "Os": "linux",
        "Size": 104101893,
        "VirtualSize": 104101893,
        "GraphDriver": {
            "Data": {
                "LowerDir": "/var/lib/docker/overlay2/adea96bbe6518657dc2d4c6331a807eea70567144abda686588ef6c3bb0d778a/diff:/var/lib/docker/overlay2/66abd822d34dc6446e6bebe73721dfd1dc497c2c8063c43ffb8cf8140e2caeb6/diff:/var/lib/docker/overlay2/d19d24fb6a24801c5fa639c1d979d19f3f17196b3c6dde96d3b69cd2ad07ba8a/diff:/var/lib/docker/overlay2/a1e95aae5e09ca6df4f71b542c86c677b884f5280c1d3e3a1111b13644b221f9/diff:/var/lib/docker/overlay2/cd90f7a9cd0227c1db29ea992e889e4e6af057d9ab2835dd18a67a019c18bab4/diff",
                "MergedDir": "/var/lib/docker/overlay2/afa1de233453b60686a3847854624ef191d7bc317fb01e015b4f06671139fb11/merged",
                "UpperDir": "/var/lib/docker/overlay2/afa1de233453b60686a3847854624ef191d7bc317fb01e015b4f06671139fb11/diff",
                "WorkDir": "/var/lib/docker/overlay2/afa1de233453b60686a3847854624ef191d7bc317fb01e015b4f06671139fb11/work"
            },
            "Name": "overlay2"
        },
        "RootFS": {
            "Type": "layers",
            "Layers": [
                "sha256:c2adabaecedbda0af72b153c6499a0555f3a769d52370469d8f6bd6328af9b13",
                "sha256:744315296a49be711c312dfa1b3a80516116f78c437367ff0bc678da1123e990",
                "sha256:379ef5d5cb402a5538413d7285b21aa58a560882d15f1f553f7868dc4b66afa8",
                "sha256:d00fd460effb7b066760f97447c071492d471c5176d05b8af1751806a1f905f8",
                "sha256:4d0c196331523cfed7bf5bafd616ecb3855256838d850b6f3d5fba911f6c4123",
                "sha256:98b4a6242af2536383425ba2d6de033a510e049d9ca07ff501b95052da76e894"
            ]
        },
        "Metadata": {
            "LastTagTime": "0001-01-01T00:00:00Z"
        }
    }
]
```

理解：

所有的 Docker镜像都起始于一个基础镜像层，当进行修改或培加新的内容时，就会在当前镜像层之上，创建新的镜像层。

举一个简单的例子，假如基于 Ubuntu Linux16.04创建一个新的镜像，这就是新镜像的第一层；如果在该镜像中添加 Python包，
就会在基础镜像层之上创建第二个镜像层；如果继续添加一个安全补丁，就会创健第三个镜像层该像当前已经包含3个镜像层，如下图所示（这只是一个用于演示的很简单的例子）。

在添加额外的镜像层的同时，镜像始终保持是当前所有镜像的组合，理解这一点.

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706223716001-1673133064.png)

 

 

在添加额外的镜像层的同时，镜像始终保持是当前所有镜像的组合，理解这一点非常重要。下图中举了一个简单的例子，每个镜像层包含3个文件，而镜像包含了来自两个镜像层的6个文件。

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706223721480-1508203642.png)

 

 

上图中的镜像层跟之前图中的略有区別，主要目的是便于展示文件
下图中展示了一个稍微复杂的三层镜像，在外部看来整个镜像只有6个文件，这是因为最上层中的文件7是文件5的一个更新版。

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706223726833-1475199695.png)

 

 

文种情況下，上层镜像层中的文件覆盖了底层镜像层中的文件。这样就使得文件的更新版本作为一个新镜像层添加到镜像当中

Docker通过存储引擎（新版本采用快照机制）的方式来实现镜像层堆栈，并保证多镜像层对外展示为统一的文件系统

Linux上可用的存储引撃有AUFS、 Overlay2、 Device Mapper、Btrfs以及ZFS。顾名思义，每种存储引擎都基于 Linux中对应的
件系统或者块设备技术，井且每种存储引擎都有其独有的性能特点。

Docker在 Windows上仅支持 windowsfilter 一种存储引擎，该引擎基于NTFS文件系统之上实现了分层和CoW [1]。

下图展示了与系统显示相同的三层镜像。所有镜像层堆并合井，对外提供统一的视图。
![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706223753923-1883573845.png)

 

 

> 特点

Docker 镜像都是只读的，当容器启动时，一个新的可写层加载到镜像的顶部！

这一层就是我们通常说的容器层，容器之下的都叫镜像层！

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706223807059-1948721796.png)

 





```
### 1、启动一个默认的tomcat

[root@iz2zeak7sgj6i7hrb2g862z ~]# docker run -d -p 8080:8080 tomcat
de57d0ace5716d27d0e3a7341503d07ed4695ffc266aef78e0a855b270c4064e

### 2、发现这个默认的tomcat 是没有webapps应用，官方的镜像默认webapps下面是没有文件的！

#docker exec -it 容器id /bin/bash
[root@iz2zeak7sgj6i7hrb2g862z ~]# docker exec -it de57d0ace571 /bin/bash
root@de57d0ace571:/usr/local/tomcat# 

### 3、从webapps.dist拷贝文件进去webapp

root@de57d0ace571:/usr/local/tomcat# cp -r webapps.dist/* webapps
root@de57d0ace571:/usr/local/tomcat# cd webapps
root@de57d0ace571:/usr/local/tomcat/webapps# ls
ROOT  docs  examples  host-manager  manager

### 4、将操作过的容器通过commit调教为一个镜像！我们以后就使用我们修改过的镜像即可，而不需要每次都重新拷贝webapps.dist下的文件到webapps了，这就是我们自己的一个修改的镜像。

docker commit -m="描述信息" -a="作者" 容器id 目标镜像名:[TAG]
docker commit -a="kuangshen" -m="add webapps app" 容器id tomcat02:1.0

[root@iz2zeak7sgj6i7hrb2g862z ~]# docker commit -a="csp提交的" -m="add webapps app" de57d0ace571 tomcat02.1.0
sha256:d5f28a0bb0d0b6522fdcb56f100d11298377b2b7c51b9a9e621379b01cf1487e

[root@iz2zeak7sgj6i7hrb2g862z ~]# docker images
REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
tomcat02.1.0          latest              d5f28a0bb0d0        14 seconds ago      652MB
tomcat                latest              1b6b1fe7261e        5 days ago          647MB
nginx                 latest              9beeba249f3e        5 days ago          127MB
mysql                 5.7                 b84d68d0a7db        5 days ago          448MB
elasticsearch         7.6.2               f29a1ee41030        8 weeks ago         791MB
portainer/portainer   latest              2869fc110bf7        2 months ago        78.6MB
centos                latest              470671670cac        4 months ago        237MB
hello-world           latest              bf756fb1ae65        4 months ago        13.3kB
```





### 容器数据卷

什么是容器数据卷
将应用和环境打包成一个镜像！

数据？如果数据都在容器中，那么我们容器删除，数据就会丢失！需求：数据可以持久化

MySQL，容器删除了，删库跑路！需求：MySQL数据可以存储在本地！

容器之间可以有一个数据共享的技术！Docker容器中产生的数据，同步到本地！

这就是卷技术！目录的挂载，将我们容器内的目录，挂载到Linux上面！

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706223918960-1382486738.png)

 

 

总结一句话：容器的持久化和同步操作！容器间也是可以数据共享的！

使用数据卷
方式一 ：直接使用命令挂载 -v

```
-v, --volume list                    Bind mount a volume

docker run -it -v 主机目录:容器内目录  -p 主机端口:容器内端口

# /home/ceshi：主机home目录下的ceshi文件夹  映射：centos容器中的/home

[root@iz2zeak7 home]# docker run -it -v /home/ceshi:/home centos /bin/bash
#这时候主机的/home/ceshi文件夹就和容器的/home文件夹关联了,二者可以实现文件或数据同步了

#通过 docker inspect 容器id 查看
[root@iz2zeak7sgj6i7hrb2g862z home]# docker inspect 6064c490c371
```

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706223942116-1125618022.png)

 

 测试文件的同步

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706223951528-296127979.png)

 

 

再来测试！

1、停止容器

2、宿主机修改文件

3、启动容器

4、容器内的数据依旧是同步的

docker start 容器id

docker attach 容器id ：进入容器。

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224006829-1259590754.png)

## 实战：安装MySQL

思考：MySQL的数据持久化的问题

```
# 获取mysql镜像

[root@iz2zeak7sgj6i7hrb2g862z home]# docker pull mysql:5.7

# 运行容器,需要做数据挂载 #安装启动mysql，需要配置密码的，这是要注意点！

# 参考官网hub 

docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag

#启动我们得
-d 后台运行
-p 端口映射
-v 卷挂载
-e 环境配置
-- name 容器名字
$ docker run -d -p 3310:3306 -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --name mysql01 mysql:5.7

# 启动成功之后，我们在本地使用sqlyog来测试一下

# sqlyog-连接到服务器的3306--和容器内的3306映射 

# 在本地测试创建一个数据库，查看一下我们映射的路径是否ok！
```

## 具名和匿名挂载

```
# 匿名挂载

-v 容器内路径!
$ docker run -d -P --name nginx01 -v /etc/nginx nginx

# 查看所有的volume(卷)的情况

$ docker volume ls    
DRIVER              VOLUME NAME # 容器内的卷名(匿名卷挂载)
local               21159a8518abd468728cdbe8594a75b204a10c26be6c36090cde1ee88965f0d0
local               b17f52d38f528893dd5720899f555caf22b31bf50b0680e7c6d5431dbda2802c
         

# 这里发现，这种就是匿名挂载，我们在 -v只写了容器内的路径，没有写容器外的路径！

# 具名挂载 -P:表示随机映射端口

$ docker run -d -P --name nginx02 -v juming-nginx:/etc/nginx nginx
9663cfcb1e5a9a1548867481bfddab9fd7824a6dc4c778bf438a040fe891f0ee

# 查看所有的volume(卷)的情况

$ docker volume ls                  
DRIVER              VOLUME NAME
local               21159a8518abd468728cdbe8594a75b204a10c26be6c36090cde1ee88965f0d0
local               b17f52d38f528893dd5720899f555caf22b31bf50b0680e7c6d5431dbda2802c
local               juming-nginx #多了一个名字


# 通过 -v 卷名：查看容器内路径

# 查看一下这个卷

$ docker volume inspect juming-nginx
[
    {
        "CreatedAt": "2020-05-23T13:55:34+08:00",
        "Driver": "local",
        "Labels": null,
        "Mountpoint": "/var/lib/docker/volumes/juming-nginx/_data", #默认目录
        "Name": "juming-nginx",
        "Options": null,
        "Scope": "local"
    }
]
```

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224052728-291626547.png)

 

 所有的docker容器内的卷，没有指定目录的情况下都是在**/var/lib/docker/volumes/自定义的卷名/_data**下，
如果指定了目录，docker volume ls 是查看不到的。

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224104195-555425257.png)

 

```
 区分三种挂载方式

\# 三种挂载： 匿名挂载、具名挂载、指定路径挂载
-v 容器内路径 #匿名挂载
-v 卷名：容器内路径 #具名挂载
-v /宿主机路径：容器内路径 #指定路径挂载 docker volume ls 是查看不到的

拓展：

\# 通过 -v 容器内路径： ro rw 改变读写权限
ro #readonly 只读
rw #readwrite 可读可写
dockerrun−d−P−−namenginx05−vjuming:/etc/nginx:ronginxdockerrun−d−P−−namenginx05−vjuming:/etc/nginx:ronginx docker run -d -P --name nginx05 -v juming:/etc/nginx:rw nginx

\# ro 只要看到ro就说明这个路径只能通过宿主机来操作，容器内部是无法操作！
```

## 初始Dockerfile

Dockerfile 就是用来构建docker镜像的构建文件！命令脚本！先体验一下！

通过这个脚本可以生成镜像，镜像是一层一层的，脚本是一个个的命令，每个命令都是一层！

```
# 创建一个dockerfile文件，名字可以随便 建议Dockerfile

# 文件中的内容： 指令(大写) + 参数
```



```
$ vim dockerfile1
    FROM centos                     # 当前这个镜像是以centos为基础的

    VOLUME ["volume01","volume02"]     # 挂载卷的卷目录列表(多个目录)
    
    CMD echo "-----end-----"        # 输出一下用于测试
    CMD /bin/bash                    # 默认走bash控制台

# 这里的每个命令，就是镜像的一层！

# 构建出这个镜像 

-f dockerfile1             # f代表file，指这个当前文件的地址(这里是当前目录下的dockerfile1)
-t caoshipeng/centos     # t就代表target，指目标目录(注意caoshipeng镜像名前不能加斜杠‘/’)
.                         # 表示生成在当前目录下
$ docker build -f dockerfile1 -t caoshipeng/centos .
Sending build context to Docker daemon   2.56kB
Step 1/4 : FROM centos
latest: Pulling from library/centos
8a29a15cefae: Already exists 
Digest: sha256:fe8d824220415eed5477b63addf40fb06c3b049404242b31982106ac204f6700
Status: Downloaded newer image for centos:latest
 ---> 470671670cac
Step 2/4 : VOLUME ["volume01","volume02"]             # 卷名列表
 ---> Running in c18eefc2c233
Removing intermediate container c18eefc2c233
 ---> 623ae1d40fb8
Step 3/4 : CMD echo "-----end-----"                    # 输出 脚本命令
 ---> Running in 70e403669f3c
Removing intermediate container 70e403669f3c
 ---> 0eba1989c4e6
Step 4/4 : CMD /bin/bash
 ---> Running in 4342feb3a05b
Removing intermediate container 4342feb3a05b
 ---> f4a6b0d4d948
Successfully built f4a6b0d4d948
Successfully tagged caoshipeng/centos:latest

# 查看自己构建的镜像

$ docker images
REPOSITORY          TAG          IMAGE ID            CREATED              SIZE
caoshipeng/centos   latest       f4a6b0d4d948        About a minute ago   237MB
```

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224147715-1227854442.png)

启动自己写的容器镜像

```
$ docker run -it f4a6b0d4d948 /bin/bash    # 运行自己写的镜像
$ ls -l                                 # 查看目录
```

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224207042-896979399.png)

这个卷和外部一定有一个同步的目录

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNjEyMTUzMTYyNi5wbmc?x-oss-process=image/format,png)

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706230525727-1298596265.png)

 

如果没有找到挂载卷的位置可以手动再挂载一下

 



查看一下卷挂载

\# docker inspect 容器id
$ docker inspect ca3b45913df5

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224225775-558486318.png)

 

 

测试一下刚才的文件是否同步出去了！

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200524154444736.png#pic_center)

这种方式使用的十分多，因为我们通常会构建自己的镜像！

假设构建镜像时候没有挂载卷，要手动镜像挂载 -v 卷名：容器内路径！

## 数据卷容器

多个MySQL同步数据！

命名的容器挂载数据卷！

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224246793-900895284.png)

```
# 测试 启动3个容器，通过刚才自己写的镜像启动

# 创建docker01：因为我本机是最新版，故这里用latest，狂神老师用的是1.0如下图

$ docker run -it --name docker01 caoshipeng/centos:latest

# 查看容器docekr01内容

$ ls
bin  home   lost+found    opt   run   sys  var
dev  lib    media    proc  sbin  tmp  volume01
etc  lib64  mnt        root  srv   usr  volume02

# 不关闭该容器退出

CTRL + Q + P  

# 创建docker02: 并且让docker02 继承 docker01

$ docker run -it --name docker02 --volumes-from docker01 caoshipeng/centos:latest

# 查看容器docker02内容

$ ls
bin  home   lost+found    opt   run   sys  var
dev  lib    media    proc  sbin  tmp  volume01
etc  lib64  mnt        root  srv   usr  volume02
```

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224301261-115994955.png)

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224306143-1544752197.png)

```
# 再新建一个docker03同样继承docker01

$ docker run -it --name docker03 --volumes-from docker01 caoshipeng/centos:latest
$ cd volume01    #进入volume01 查看是否也同步docker01的数据
$ ls 
docker01.txt

# 测试：可以删除docker01，查看一下docker02和docker03是否可以访问这个文件

# 测试发现：数据依旧保留在docker02和docker03中没有被删除
```

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224318224-1220916531.png)

```
$ docker run -d -p 3306:3306 -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --name mysql01 mysql:5.7

$ docker run -d -p 3310:3306 -e MYSQL_ROOT_PASSWORD=123456 --name mysql02 --volumes-from mysql01  mysql:5.7

# 这个时候，可以实现两个容器数据同步！
```

结论：

容器之间的配置信息的传递，数据卷容器的生命周期一直持续到没有容器使用为止。

但是一旦你持久化到了本地，这个时候，本地的数据是不会删除的！

### DockerFile

DockerFile介绍
dockerfile是用来构建docker镜像的文件！命令参数脚本！

构建步骤：

1、 编写一个dockerfile文件

2、 docker build 构建称为一个镜像

3、 docker run运行镜像

4、 docker push发布镜像（DockerHub 、阿里云仓库)

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224341853-1925182661.png)

点击后跳到一个Dockerfile

 

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224351525-1501962575.png)

 

 

很多官方镜像都是基础包，很多功能没有，我们通常会自己搭建自己的镜像！

官方既然可以制作镜像，那我们也可以！

DockerFile构建过程
基础知识：

1、每个保留关键字(指令）都是必须是大写字母

2、执行从上到下顺序

3、#表示注释

4、每一个指令都会创建提交一个新的镜像曾，并提交！



![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224406429-1558749853.png)

 

 

Dockerfile是面向开发的，我们以后要发布项目，做镜像，就需要编写dockerfile文件，这个文件十分简单！

Docker镜像逐渐成企业交付的标准，必须要掌握！

DockerFile：构建文件，定义了一切的步骤，源代码

DockerImages：通过DockerFile构建生成的镜像，最终发布和运行产品。

Docker容器：容器就是镜像运行起来提供服务。

DockerFile的指令

```
FROM                # from:基础镜像，一切从这里开始构建
MAINTAINER            # maintainer:镜像是谁写的， 姓名+邮箱
RUN                    # run:镜像构建的时候需要运行的命令
ADD                    # add:步骤，tomcat镜像，这个tomcat压缩包！添加内容 添加同目录
WORKDIR                # workdir:镜像的工作目录
VOLUME                # volume:挂载的目录
EXPOSE                # expose:保留端口配置
CMD                    # cmd:指定这个容器启动的时候要运行的命令，只有最后一个会生效，可被替代
ENTRYPOINT            # entrypoint:指定这个容器启动的时候要运行的命令，可以追加命令
ONBUILD                # onbuild:当构建一个被继承DockerFile这个时候就会运行onbuild的指令，触发指令
COPY                # copy:类似ADD，将我们文件拷贝到镜像中
ENV                    # env:构建的时候设置环境变量！
```

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224439087-1603288655.png)

#### 实战测试

```
scratch 镜像

FROM scratch
ADD centos-7-x86_64-docker.tar.xz /

LABEL \
    org.label-schema.schema-version="1.0" \
    org.label-schema.name="CentOS Base Image" \
    org.label-schema.vendor="CentOS" \
    org.label-schema.license="GPLv2" \
    org.label-schema.build-date="20200504" \
    org.opencontainers.image.title="CentOS Base Image" \
    org.opencontainers.image.vendor="CentOS" \
    org.opencontainers.image.licenses="GPL-2.0-only" \
    org.opencontainers.image.created="2020-05-04 00:00:00+01:00"

CMD ["/bin/bash"]
```

Docker Hub 中 99%的镜像都是从这个基础镜像过来的 FROM scratch，然后配置需要的软件和配置来进行构建。

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224500504-483457663.png)

 

 创建一个自己的centos

```
# 1./home下新建dockerfile目录

$ mkdir dockerfile

# 2. dockerfile目录下新建mydockerfile-centos文件

$ vim mydockerfile-centos

# 3.编写Dockerfile配置文件

FROM centos                            # 基础镜像是官方原生的centos
MAINTAINER cao<1165680007@qq.com>     # 作者

ENV MYPATH /usr/local                # 配置环境变量的目录 
WORKDIR $MYPATH                        # 将工作目录设置为 MYPATH

RUN yum -y install vim                # 给官方原生的centos 增加 vim指令
RUN yum -y install net-tools        # 给官方原生的centos 增加 ifconfig命令

EXPOSE 80                            # 暴露端口号为80

CMD echo $MYPATH                    # 输出下 MYPATH 路径
CMD echo "-----end----"                
CMD /bin/bash                        # 启动后进入 /bin/bash

# 4.通过这个文件构建镜像

# 命令： docker build -f 文件路径 -t 镜像名:[tag] .

$ docker build -f mydockerfile-centos -t mycentos:0.1 .

# 5.出现下图后则构建成功
```

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224518937-1609147936.png)

```
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
mycentos            0.1                 cbf5110a646d        2 minutes ago       311MB

# 6.测试运行

$ docker run -it mycentos:0.1         # 注意带上版本号，否则每次都回去找最新版latest

$ pwd    
/usr/local                            # 与Dockerfile文件中 WORKDIR 设置的 MYPATH 一致
$ vim                                # vim 指令可以使用
$ ifconfig                             # ifconfig 指令可以使用

# docker history 镜像id 查看镜像构建历史步骤

$ docker history 镜像id
```

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224528555-762671914.png)

 

 我们可以列出本地进行的变更历史

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224536438-2128040889.png)

 

 我们平时拿到一个镜像，可以用 “docker history 镜像id” 研究一下是什么做的

CMD 和 ENTRYPOINT区别

```
CMD                    # 指定这个容器启动的时候要运行的命令，只有最后一个会生效，可被替代。
ENTRYPOINT            # 指定这个容器启动的时候要运行的命令，可以追加命令
```

测试cmd

```
# 编写dockerfile文件

$ vim dockerfile-test-cmd
FROM centos
CMD ["ls","-a"]                    # 启动后执行 ls -a 命令

# 构建镜像

$ docker build  -f dockerfile-test-cmd -t cmd-test:0.1 .

# 运行镜像

$ docker run cmd-test:0.1        # 由结果可得，运行后就执行了 ls -a 命令
.
..
.dockerenv
bin
dev
etc
home

# 想追加一个命令  -l 成为ls -al：展示列表详细数据

$ docker run cmd-test:0.1 -l
docker: Error response from daemon: OCI runtime create failed: container_linux.go:349: starting container process caused "exec: \"-l\":
executable file not found in $PATH": unknown.
ERRO[0000] error waiting for container: context canceled 

# cmd的情况下 -l 替换了CMD["ls","-l"] 而 -l  不是命令所以报错
```

测试ENTRYPOINT

```
# 编写dockerfile文件

$ vim dockerfile-test-entrypoint
FROM centos
ENTRYPOINT ["ls","-a"]

# 构建镜像

$ docker build  -f dockerfile-test-entrypoint -t cmd-test:0.1 .

# 运行镜像

$ docker run entrypoint-test:0.1
.
..
.dockerenv
bin
dev
etc
home
lib
lib64
lost+found ...

# 我们的命令，是直接拼接在我们得ENTRYPOINT命令后面的

$ docker run entrypoint-test:0.1 -l
total 56
drwxr-xr-x   1 root root 4096 May 16 06:32 .
drwxr-xr-x   1 root root 4096 May 16 06:32 ..
-rwxr-xr-x   1 root root    0 May 16 06:32 .dockerenv
lrwxrwxrwx   1 root root    7 May 11  2019 bin -> usr/bin
drwxr-xr-x   5 root root  340 May 16 06:32 dev
drwxr-xr-x   1 root root 4096 May 16 06:32 etc
drwxr-xr-x   2 root root 4096 May 11  2019 home
lrwxrwxrwx   1 root root    7 May 11  2019 lib -> usr/lib
lrwxrwxrwx   1 root root    9 May 11  2019 lib64 -> usr/lib64 ....
```

Dockerfile中很多命令都十分的相似，我们需要了解它们的区别，我们最好的学习就是对比他们然后测试效果！

## 实战：Tomcat镜像

##### 1、准备镜像文件

```
准备tomcat 和 jdk 到当前目录，编写好README
```

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224731339-771230384.png)

##### 2、编写dokerfile

```
$ vim dockerfile
FROM centos                                         # 基础镜像centos
MAINTAINER cao<1165680007@qq.com>                    # 作者
COPY README /usr/local/README                         # 复制README文件
ADD jdk-8u231-linux-x64.tar.gz /usr/local/             # 添加jdk，ADD 命令会自动解压
ADD apache-tomcat-9.0.35.tar.gz /usr/local/         # 添加tomcat，ADD 命令会自动解压
RUN yum -y install vim                                # 安装 vim 命令
ENV MYPATH /usr/local                                 # 环境变量设置 工作目录
WORKDIR $MYPATH

ENV JAVA_HOME /usr/local/jdk1.8.0_231                 # 环境变量： JAVA_HOME环境变量
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.35     # 环境变量： tomcat环境变量
ENV CATALINA_BASH /usr/local/apache-tomcat-9.0.35

# 设置环境变量 分隔符是：

ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin     

EXPOSE 8080                                         # 设置暴露的端口

CMD /usr/local/apache-tomcat-9.0.35/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.35/logs/catalina.out                     # 设置默认命令
```

##### 3、构建镜像

```
# 因为dockerfile命名使用默认命名 因此不用使用-f 指定文件
$ docker build -t mytomcat:0.1 .
```

##### 4、run镜像

```
# -d:后台运行 -p:暴露端口 --name:别名 -v:绑定路径 
$ docker run -d -p 8080:8080 --name tomcat01 
-v /home/kuangshen/build/tomcat/test:/usr/local/apache-tomcat-9.0.35/webapps/test 
-v /home/kuangshen/build/tomcat/tomcatlogs/:/usr/local/apache-tomcat-9.0.35/logs mytomcat:0.1
```

##### 5、访问测试

```
$ docker exec -it 自定义容器的id /bin/bash

$ cul localhost:8080
```

##### 6、发布项目

(由于做了卷挂载，我们直接在本地编写项目就可以发布了！)

发现：项目部署成功，可以直接访问！

我们以后开发的步骤：需要掌握Dockerfile的编写！我们之后的一切都是使用docker镜像来发布运行！

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%> <%@ page import = "java.util.Date" %> <%@ page import = "java.text.SimpleDateFormat" %> <!DOCTYPE html> <html> <head> <meta charset="UTF-8"> <title>Insert title here</title> </head> <body> <% Date date = new Date(); SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String today = df.format(date); %> 当前时间：<%=today %> </body> </html>
```



## 发布自己的镜像

> 发布到 Docker Hub

 

1、地址 https://hub.docker.com/

2、确定这个账号可以登录

3、登录

```
$ docker login --help
Usage:  docker login [OPTIONS] [SERVER]

Log in to a Docker registry.
If no server is specified, the default is defined by the daemon.

Options:
  -p, --password string   Password
      --password-stdin    Take the password from stdin
  -u, --username string   Username

$ docker login -u 你的用户名 -p 你的密码
```

4、提交 push镜像

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224855190-137835458.png)

```
 # 会发现push不上去，因为如果没有前缀的话默认是push到 官方的library

# 解决方法：

# 第一种 build的时候添加你的dockerhub用户名，然后在push就可以放到自己的仓库了

$ docker build -t kuangshen/mytomcat:0.1 .

# 第二种 使用docker tag #然后再次push

$ docker tag 容器id kuangshen/mytomcat:1.0 #然后再次push
$ docker push kuangshen/mytomcat:1.0
```

发布到 阿里云镜像服务上

看官网 很详细https://cr.console.aliyun.com/repository/

```
$ sudo docker login --username=zchengx registry.cn-shenzhen.aliyuncs.com
$ sudo docker tag [ImageId] registry.cn-shenzhen.aliyuncs.com/dsadxzc/cheng:[镜像版本号]

# 修改id 和 版本

sudo docker tag a5ef1f32aaae registry.cn-shenzhen.aliyuncs.com/dsadxzc/cheng:1.0

# 修改版本

$ sudo docker push registry.cn-shenzhen.aliyuncs.com/dsadxzc/cheng:[镜像版本号]
```

## 小结

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706224929108-494579576.png)

 

## Docker 网络

##### 理解Docker 0

学习之前清空下前面的docker 镜像、容器

```
# 删除全部容器
$ docker rm -f $(docker ps -aq)

# 删除全部镜像
$ docker rmi -f $(docker images -aq)
```

三个网络

> 问题： docker 是如果处理容器网络访问的？

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225010652-1560825052.png)

 

 

 

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
# 测试  运行一个tomcat
$ docker run -d -P --name tomcat01 tomcat

# 查看容器内部网络地址
$ docker exec -it 容器id ip addr

# 发现容器启动的时候会得到一个 eth0@if91 ip地址，docker分配！
$ ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
261: eth0@if91: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:ac:12:00:02 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 172.18.0.2/16 brd 172.18.255.255 scope global eth0
       valid_lft forever preferred_lft forever

       
# 思考？ linux能不能ping通容器内部！ 可以 容器内部可以ping通外界吗？ 可以！
$ ping 172.18.0.2
PING 172.18.0.2 (172.18.0.2) 56(84) bytes of data.
64 bytes from 172.18.0.2: icmp_seq=1 ttl=64 time=0.069 ms
64 bytes from 172.18.0.2: icmp_seq=2 ttl=64 time=0.074 ms
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

原理

1、我们每启动一个docker容器，docker就会给docker容器分配一个ip，我们只要按照了docker，就会有一个docker0桥接模式，使用的技术是veth-pair技术！

https://www.cnblogs.com/bakari/p/10613710.html

再次测试 ip addr
![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225031049-213828563.png)

 

 

2 、再启动一个容器测试，发现又多了一对网络

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225043242-1037542003.png)

 

 

```
# 我们发现这个容器带来网卡，都是一对对的
# veth-pair 就是一对的虚拟设备接口，他们都是成对出现的，一端连着协议，一端彼此相连
# 正因为有这个特性 veth-pair 充当一个桥梁，连接各种虚拟网络设备的
# OpenStac,Docker容器之间的连接，OVS的连接，都是使用evth-pair技术
```

3、我们来测试下tomcat01和tomcat02是否可以ping通

 

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
# 获取tomcat01的ip 172.17.0.2
$ docker-tomcat docker exec -it tomcat01 ip addr  
550: eth0@if551: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:ac:11:00:02 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 172.17.0.2/16 brd 172.17.255.255 scope global eth0
       valid_lft forever preferred_lft forever
       
# 让tomcat02 ping tomcat01       
$ docker-tomcat docker exec -it tomcat02 ping 172.17.0.2
PING 172.17.0.2 (172.17.0.2) 56(84) bytes of data.
64 bytes from 172.17.0.2: icmp_seq=1 ttl=64 time=0.098 ms
64 bytes from 172.17.0.2: icmp_seq=2 ttl=64 time=0.071 ms

# 结论：容器和容器之间是可以互相ping通
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

网络模型图

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225118108-1105564466.png)

 

 

结论：tomcat01和tomcat02公用一个路由器，docker0。

所有的容器不指定网络的情况下，都是docker0路由的，docker会给我们的容器分配一个默认的可用ip。

小结

Docker使用的是Linux的桥接，宿主机是一个Docker容器的网桥 docker0

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225134448-1957743031.png)

 

 

Docker中所有网络接口都是虚拟的，虚拟的转发效率高（内网传递文件）

只要容器删除，对应的网桥一对就没了！

思考一个场景：我们编写了一个微服务，database url=ip: 项目不重启，数据ip换了，我们希望可以处理这个问题，可以通过名字来进行访问容器？

##### –-link

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
$ docker exec -it tomcat02 ping tomca01   # ping不通
ping: tomca01: Name or service not known

# 运行一个tomcat03 --link tomcat02 
$ docker run -d -P --name tomcat03 --link tomcat02 tomcat
5f9331566980a9e92bc54681caaac14e9fc993f14ad13d98534026c08c0a9aef

# 3连接2
# 用tomcat03 ping tomcat02 可以ping通
$ docker exec -it tomcat03 ping tomcat02
PING tomcat02 (172.17.0.3) 56(84) bytes of data.
64 bytes from tomcat02 (172.17.0.3): icmp_seq=1 ttl=64 time=0.115 ms
64 bytes from tomcat02 (172.17.0.3): icmp_seq=2 ttl=64 time=0.080 ms

# 2连接3
# 用tomcat02 ping tomcat03 ping不通
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

探究：

docker network inspect 网络id 网段相同

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225157260-1489190112.png)

 

 docker inspect tomcat03

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225207038-1413961651.png)

 

 查看tomcat03里面的/etc/hosts发现有tomcat02的配置

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225216852-907345532.png)

 

 

–link 本质就是在hosts配置中添加映射

现在使用Docker已经不建议使用–link了！

自定义网络，不适用docker0！

docker0问题：不支持容器名连接访问！

##### 自定义网络

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
docker network
connect     -- Connect a container to a network
create      -- Creates a new network with a name specified by the
disconnect  -- Disconnects a container from a network
inspect     -- Displays detailed information on a network
ls          -- Lists all the networks created by the user
prune       -- Remove all unused networks
rm          -- Deletes one or more networks
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

查看所有的docker网络

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225240157-384460438.png)

 

 

网络模式

bridge ：桥接 docker（默认，自己创建也是用bridge模式）

none ：不配置网络，一般不用

host ：和所主机共享网络

container ：容器网络连通（用得少！局限很大）

测试

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
# 我们直接启动的命令 --net bridge,而这个就是我们得docker0
# bridge就是docker0
$ docker run -d -P --name tomcat01 tomcat
等价于 => docker run -d -P --name tomcat01 --net bridge tomcat

# docker0，特点：默认，域名不能访问。 --link可以打通连接，但是很麻烦！
# 我们可以 自定义一个网络
$ docker network create --driver bridge --subnet 192.168.0.0/16 --gateway 192.168.0.1 mynet
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225256388-1438330145.png)

 

 

```
$ docker network inspect mynet;
```

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225307573-289687728.png)

 

 

启动两个tomcat,再次查看网络情况

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNjE5MTg0NDI0MC5wbmc?x-oss-process=image/format,png)

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNjE5MjAwNzM3MS5wbmc?x-oss-process=image/format,png)

在自定义的网络下，服务可以互相ping通，不用使用–link

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNjE5MjEzNDY3My5wbmc?x-oss-process=image/format,png)

我们自定义的网络docker当我们维护好了对应的关系，推荐我们平时这样使用网络！

好处：

redis -不同的集群使用不同的网络，保证集群是安全和健康的

mysql-不同的集群使用不同的网络，保证集群是安全和健康的

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNjE5MjUwNDM2Ny5wbmc?x-oss-process=image/format,png)

## 网络连通

 

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNjE5MzI0MzE0Ni5wbmc?x-oss-process=image/format,png)

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNjE5MzI1OTE4NS5wbmc?x-oss-process=image/format,png)

\# 测试两个不同的网络连通 再启动两个tomcat 使用默认网络，即docker0
dockerrun−d−P−−nametomcat01tomcatdockerrun−d−P−−nametomcat01tomcat docker run -d -P --name tomcat02 tomcat
\# 此时ping不通
![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225403840-732000956.png)

 

 

\# 要将tomcat01 连通 tomcat—net-01 ，连通就是将 tomcat01加到 mynet网络
\# 一个容器两个ip（tomcat01）



![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225410047-736501947.png)

 

 


\# 01连通 ，加入后此时，已经可以tomcat01 和 tomcat-01-net ping通了
\# 02是依旧不通的

结论：假设要跨网络操作别人，就需要使用docker network connect 连通！

实战：部署Redis集群

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225428336-1765951546.png)

 

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
# 创建网卡
docker network create redis --subnet 172.38.0.0/16
# 通过脚本创建六个redis配置
for port in $(seq 1 6);\
do \
mkdir -p /mydata/redis/node-${port}/conf
touch /mydata/redis/node-${port}/conf/redis.conf
cat << EOF >> /mydata/redis/node-${port}/conf/redis.conf
port 6379
bind 0.0.0.0
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
cluster-announce-ip 172.38.0.1${port}
cluster-announce-port 6379
cluster-announce-bus-port 16379
appendonly yes
EOF
done

# 通过脚本运行六个redis
for port in $(seq 1 6);\
docker run -p 637${port}:6379 -p 1667${port}:16379 --name redis-${port} \
-v /mydata/redis/node-${port}/data:/data \
-v /mydata/redis/node-${port}/conf/redis.conf:/etc/redis/redis.conf \
-d --net redis --ip 172.38.0.1${port} redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf
docker exec -it redis-1 /bin/sh #redis默认没有bash
redis-cli --cluster create 172.38.0.11:6379 172.38.0.12:6379 172.38.0.13:6379 172.38.0.14:6379 172.38.0.15:6379 172.38.0.16:6379  --cluster-replicas 1
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210706225448493-1102910835.png)

 

 

docker搭建redis集群完成！

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL2NoZW5nY29kZXgvY2xvdWRpbWcvbWFzdGVyL2ltZy9pbWFnZS0yMDIwMDUxNjIwMzMyMzk3MS5wbmc?x-oss-process=image/format,png)

我们使用docker之后，所有的技术都会慢慢变得简单起来！

## SpringBoot微服务打包Docker镜像

1、构建SpringBoot项目

2、打包运行

mvn package

3、编写dockerfile

```
FROM java:8
COPY *.jar /app.jar
CMD ["--server.port=8080"]
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```

创建一个dockerfile文件，放入上面的代码，把jar和文件上传到xshell

4、构建镜像

```
# 1.复制jar和DockerFIle到服务器
# 2.构建镜像
$ docker build -t xxxxx:xx  .
```

5、发布运行

以后我们使用了Docker之后，给别人交付就是一个镜像即可！

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707170843870-1125913730.png)

 [【狂神说Java】Docker进阶篇超详细版教程通俗易懂_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1kv411q7Qc?p=9)

 Compose

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
version: '2.0'
services:
  web:
    build: .
    ports:
    - "5000:5000"
    volumes:
    - .:/code
    - logvolume01:/var/log
    links:
    - redis
  redis:
    image: redis
volumes:
  logvolume01: {}
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

docker-compose up 100个服务

Compose：重要概念

- 服务services， 容器、应用（web、redis、mysql...）
- 项目project。 一组关联的容器

## 安装

1. 下载

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
# 官网提供 （没有下载成功）
curl -L "https://github.com/docker/compose/releases/download/1.26.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
 
# 国内地址
curl -L https://get.daocloud.io/docker/compose/releases/download/1.25.5/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

 

 

1. 授权

 

 

chmod +x /usr/local/bin/docker-compose

ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707170954843-126655621.png)

 

 

## 体验(没有测试通过)

地址：https://docs.docker.com/compose/gettingstarted/

python应用。 计数器。redis！

1. 应用app.py

1. Dockerfile 应用打包为镜像

2. [![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

   ```
   FROM python:3.6-alpine
   ADD . /code
   WORKDIR /code
   RUN pip install -r requirements.txt
   CMD ["python", "app.py"]
   # 官网的用来flask框架，我们这里不用它
   # 这告诉Docker
   # 从python3.7开始构建镜像
   # 将当前目录添加到/code印像中的路径中
   # 将工作目录设置为/code
   # 安装Python依赖项
   # 将容器的默认命令设置为python app.py
   ```

   [![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

   Docker-compose yaml文件（定义整个服务，需要的环境 web、redis） 完整的上线服务！

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
version: '3.8'
services:
web:
build: .
ports:
- "5000:5000"
volumes:
- .:/code
redis:
image: "redis:alpine"
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

 

启动compose 项目 （docker-compose up）

1. 停止应用程序，方法是从第二个终端的项目目录中运行，或者在启动应用的原始终端中按 Ctrl+C。`docker-compose down`

流程：

创建网络
执行Docker-compose.yaml
启动服务
yaml规则
docker-compose.yaml 核心！

https://docs.docker.com/compose/compose-file/#compose-file-structure-and-examples

开源项目：博客
https://docs.docker.com/compose/wordpress/

下载程序、安装数据库、配置....

compose应用 => 一键启动

下载项目（docker-compse.yaml）
如果需要文件。Dockerfile
文件准备齐全，一键启动项目即可

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171313981-1430808811.png)

 

 

## 实战：自己编写微服务上线

1. 编写项目微服务
2. Dockerfile构建镜像

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
FROM java:8
 
COPY *.jar /app.jar
 
CMD ["--server.port=8080"]
 
EXPOSE 8080
 
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

docker-compose.yml编排项目

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
version '3.8'
services:
  xiaofanapp:
    build: .
    image: xiaofanapp
    depends_on:
      - redis
    ports:
      - "8080:8080"
 
  redis:
    image: "library/redis:alpine"
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

 

 

1. 丢到服务器运行 docker-compose up

```
docker-compose down         # 关闭容器
docker-compose up --build   # 重新构建
```

 

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171407818-1314190973.png)

 

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171412971-1209369886.png)

 

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171424954-1282980123.png)

 [【狂神说Java】Docker进阶篇超详细版教程通俗易懂_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1kv411q7Qc?p=11)

 跳过，买四台，或者虚拟机

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171502425-452625467.png)

 

 

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
docker swarm init --help
 
ip addr # 获取自己的ip（用内网的不要流量）
 
[root@iZ2ze58v8acnlxsnjoulk5Z ~]# docker swarm init --advertise-addr 172.16.250.97
Swarm initialized: current node (otdyxbk2ffbogdqq1kigysj1d) is now a manager.
To add a worker to this swarm, run the following command:
    docker swarm join --token SWMTKN-1-3vovnwb5pkkno2i3u2a42yrxc1dk51zxvto5hrm4asgn37syfn-0xkrprkuyyhrx7cidg381pdir 172.16.250.97:2377
To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions.
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

初始化结点`docker swarm init`

docker swarm join 加入一个结点！

```
# 获取令牌
docker swarm join-token manager
docker swarm join-token worker
[root@iZ2ze58v8acnlxsnjoulk6Z ~]# docker swarm join --token SWMTKN-1-3vovnwb5pkkno2i3u2a42yrxc1dk51zxvto5hrm4asgn37syfn-0xkrprkuyyhrx7cidg381pdir 172.16.250.97:2377
This node joined a swarm as a worker.
 
```

把后面的结点都搭建进去

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171551589-229491483.png)

 

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171615304-1495557804.png)

 

 

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171639862-161922291.png)

 

 查看服务

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171700659-502153902.png)

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707172235769-164616158.png)

 

 

 

动态扩缩容

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
[root@iZ2ze58v8acnlxsnjoulk5Z ~]# docker service update --replicas 3 my-nginx
1/3: running   [==================================================>] 
1/3: running   [==================================================>] 
2/3: running   [==================================================>] 
3/3: running   [==================================================>] 
verify: Service converged 
 
 
[root@iZ2ze58v8acnlxsnjoulk5Z ~]# docker service scale my-nginx=5
my-nginx scaled to 5
overall progress: 3 out of 5 tasks 
overall progress: 3 out of 5 tasks 
overall progress: 3 out of 5 tasks 
overall progress: 5 out of 5 tasks 
1/5: running   [==================================================>] 
2/5: running   [==================================================>] 
3/5: running   [==================================================>] 
4/5: running   [==================================================>] 
5/5: running   [==================================================>] 
verify: Service converged 
 
 
[root@iZ2ze58v8acnlxsnjoulk5Z ~]# docker service scale my-nginx=1
my-nginx scaled to 1
overall progress: 1 out of 1 tasks 
1/1: running   [==================================================>] 
verify: Service converged 
 
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171744397-2018895909.png)

 

 ![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171800454-36010514.png)

 

 

--mode string
Service mode (replicated or global) (default "replicated")

docker service create --mode replicated --name mytom tomcat:7 默认的
docker service create --mode global --name haha alpine ping www.baidu.com

拓展： 网络模式 "PublishMode":"ingress"

Swarm:

Overlay:

ingress:特殊的Overlay网络！负载均衡的功能！ipvs vip！

 

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
[root@iZ2ze58v8acnlxsnjoulk5Z ~]# docker network ls
NETWORK ID          NAME                DRIVER              SCOPE
74cecd37149f        bridge              bridge              local
168d35c86dd5        docker_gwbridge     bridge              local
2b8f4eb9c2e5        host                host                local
dmddfc14n7r3        ingress             overlay             swarm
8e0f5f648e69        none                null                local
 
 
[root@iZ2ze58v8acnlxsnjoulk5Z ~]# docker network inspect ingress
[
    {
        "Name": "ingress",
        "Id": "dmddfc14n7r3vms5vgw0k5eay",
        "Created": "2020-08-17T10:29:07.002315919+08:00",
        "Scope": "swarm",
        "Driver": "overlay",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": null,
            "Config": [
                {
                    "Subnet": "10.0.0.0/24",
                    "Gateway": "10.0.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": true,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {
            "ingress-sbox": {
                "Name": "ingress-endpoint",
                "EndpointID": "9d6ec47ec8309eb209f4ff714fbe728abe9d88f9f1cc7e96e9da5ebd95adb1c4",
                "MacAddress": "02:42:0a:00:00:02",
                "IPv4Address": "10.0.0.2/24",
                "IPv6Address": ""
            }
        },
        "Options": {
            "com.docker.network.driver.overlay.vxlanid_list": "4096"
        },
        "Labels": {},
        "Peers": [
            {
                "Name": "cea454a89163",
                "IP": "172.16.250.96"
            },
            {
                "Name": "899a05b64e09",
                "IP": "172.16.250.99"
            },
            {
                "Name": "81d65a0e8c03",
                "IP": "172.16.250.97"
            },
            {
                "Name": "36b31096f7e2",
                "IP": "172.16.250.98"
            }
        ]
    }
]
 
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

![img](https://img2020.cnblogs.com/blog/1363376/202107/1363376-20210707171840420-775711852.png)

 

 

 

 

 

 

 

```

```

