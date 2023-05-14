# 阿里云配置emqx服务器+微信小程序上线配置

## 阿里云配置emqx

### 前提：域名备案。基于centos7.3配置

#### 一、

#### 查看openssl版本

`openssl version` 如果版本为1.0.1则需要升级版本为1.1.1

先安装依赖

`yum install -y gcc pam-devel zlib-devel perl expat-devel perl-Time-HiRes perl-Test-Harness perl-Test-Simple xinetd telnet-server vsftpd`



#### 下载源码包并安装

`wget -P /usr/local/src https://github.com/openssl/openssl/archive/OpenSSL_1_1_1-stable.zip tar xf /usr/local/src/OpenSSL_1_1_1-stable.zip -C /usr/local/src/ cd /usr/local/src/OpenSSL_1_1_1-stable ./config --prefix=/usr/local/openssl`



​	这三个命令都没报错就可以进入下一步

1. [root@localhost ~]# make`
2. `[root@localhost ~]# make test`
3. `[root@localhost ~]# make install`



#### 备份原始文件

`mv /usr/bin/openssl /usr/bin/openssl.old mv /usr/lib64/openssl /usr/lib64/openssl.old mv /usr/lib64/libssl.so /usr/lib64/libssl.so.old ln -s /usr/local/openssl/bin/openssl /usr/bin/openssl ln -s /usr/local/openssl/include/openssl /usr/include/openssl ln -s /usr/local/openssl/lib/libssl.so /usr/lib64/libssl.so echo "/usr/local/openssl/lib" >> /etc/ld.so.conf ldconfig -v` 

#### 再次查看版本

openssl version



### 二、安装emqx

1. 访问 [emqx.com (opens new window)](https://www.emqx.com/zh/try?product=broker)或 [Github (opens new window)](https://github.com/emqx/emqx/releases)下载EMQX 版本的二进制包。

2. 选择最新版+centos7 下载

3. `wget https://www.emqx.com/zh/downloads/broker/4.4.2/emqx-4.4.2-otp24.1.5-3-el7-amd64.rpm`

4. `sudo yum install emqx-4.4.2-otp24.1.5-3-el7-amd64.rpm`

5. `sudo emqx start`

   ​     

   ##  

## emqx配置wss：8084

### 前提：购买免费ssl证书 下载证书-这里下载证书时下载nginx证书和其他证书。



### 一：传输文件ssl证书：xxx.pem和xxx.key 到服务器 /etc/emqx/certs 上

### 二：vim /etc/emqx/emqx.conf 

```
定位到这里                /listener.ssl.external.keyfile 修改certs路径的.pem .key 文件为你上传的文件

listener.ssl.external.keyfile = /etc/emqx/certs/mqtt.key
listener.ssl.external.cacertfile = /etc/emqx/certs/mqtt.pem
listener.ssl.external.certfile = /etc/emqx/certs/mqtt.pem
同理
						/listener.wss.external.keyfile 
listener.wss.external.keyfile = /etc/emqx/certs/mqtt.key
listener.wss.external.cacertfile = /etc/emqx/certs/mqtt.pem
listener.wss.external.certfile = /etc/emqx/certs/mqtt.pem
```



## 阿里云配置nignx+https

### 一、Nginx的安装

1. 安装gcc

   ```r
   yum install gcc # 输入gcc -v 查询版本信息,看系统是否已经安装
   ```

2. 安装pcre

   ```undefined
   yum install pcre-devel -y
   ```

3. 安装zlib

   ```undefined
   yum install zlib zlib-devel -y
   ```

4. 安装openssl

   ```
   yum install openssl openssl-devel -y # 如需支持ssl,才需安装openssl
   ```

  6下载源码包

```bash
    wget https://nginx.org/download/nginx-1.12.1.tar.gz



    tar -zxvf nginx-1.12.1.tar.gz



    rm -rf nginx-1.12.1.tar.gz
```

Nginx安装
进入nginx目录以后执行

`./configure --prefix=/usr/local/nginx --with-http_stub_status_module --with-http_ssl_module --with-htpp_stub_status_module`

1. make
2. make install



#### 配置环境变量

export NGINX_HOME=/usr/local/nginx
export PATH=$NGINX_HOME/sbin:$PATH

source /etc/profile



### 二、

nginxssl证书上传：nginx/conf 新建一个cert目录 放入ssl证书




cd /usr/local/nginx/conf

`server {`
        `listen       80;`
        `server_name  www.xiemingquan.top;`

        #charset koi8-r;
    
        #access_log  logs/host.access.log  main;
    
        location / {
            root   html;
            index  index.html index.htm;
        }
    }
    server {
        listen  443 ssl;
        server_name www.xiemingquan.top;
        ssl_certificate   cert/mqtt.pem;
        ssl_certificate_key  cert/mqtt.key;
        ssl_session_timeout  5m;
        ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNUL
`L:!MD5:!ADH:!RC4;` 

`ssl_protocols TLSv1 TLSv1.1 TLSv1.2;`
 `ssl_prefer_server_ciphers on;`

        # 添加反向代理
        location /mqtt {
        proxy_pass http://www.xiemingquan.top:8083/mqtt;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        client_max_body_size 35m;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
        }
        location /{
            root html;
            index index.html index.htm;
        }
    
    }

[(17条消息) 微信小程序mqtt真机失败原因分析_Dnils的博客-CSDN博客](https://blog.csdn.net/weixin_45654405/article/details/124001630)
