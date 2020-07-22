<h1 align="center">
  Docker 部署各类应用
</h1>

<p align="center">
	<strong>所有代码都通过测试，并且真实有效</strong>
</p>

<p align="center">
  <a target="_blank" href="https://github.com/intomylife/Docker">
    <img src="https://img.shields.io/github/stars/intomylife/Docker.svg?style=social" alt="github star"/>
  </a>&ensp;
  <a target="_blank" href="https://github.com/intomylife/Docker">
    <img src="https://img.shields.io/github/forks/intomylife/Docker.svg?style=social" alt="github fork"/>
  </a>&ensp;
  <img src="https://img.shields.io/badge/real%20stuff-Max-red" alt="real stuff - Max"/>
</p>

------

## 简介

本仓库整合了一些工作中经常用到的一些技术。目前深度较浅，但会持续更新，并且会慢慢加深。

## 文档

* 理解相关

    * [简单记录一下了解Docker的心路历程](https://blog.csdn.net/qq_41402200/article/details/89916213)

* 安装相关

    * [CentOS7中安装Docker](https://blog.csdn.net/qq_41402200/article/details/89790700)

    * [CentOS7中安装Docker-Compose](https://blog.csdn.net/qq_41402200/article/details/101628965)

* 单机部署相关

    * [Docker 简单部署 SpringBoot 项目](https://blog.csdn.net/qq_41402200/article/details/89814045)

    * [Docker 部署 SpringBoot + Redis 项目](https://blog.csdn.net/qq_41402200/article/details/90111065)

    * [Docker 部署 SpringBoot + MySQL 项目](https://blog.csdn.net/qq_41402200/article/details/90166804)

    * [Docker 部署 SpringBoot + Nginx 实现负载均衡](https://blog.csdn.net/qq_41402200/article/details/90201811)

    * [Docker 简单部署 SpringCloud 项目](https://blog.csdn.net/qq_41402200/article/details/90315094)

    * [docker-compose 部署 SpringBoot + Redis + MySQL + Nginx](https://blog.csdn.net/qq_41402200/article/details/102585935)

    * [docker-compose 快速部署前后端分离项目](https://blog.csdn.net/qq_41402200/article/details/103225344)

* 伪集群部署相关

    * [CentOS7中使用Docker部署MySQL实现主从同步](https://blog.csdn.net/qq_41402200/article/details/95876316)

    * [docker-compose 部署 Nacos 集群](https://blog.csdn.net/qq_41402200/article/details/107442549)

* ...

## 对于项目结构的一些说明

### 前言

**有朋友说出了一些疑问：**

> 为什么有些工程里没有任何代码，是有什么特别的用途或者意义吗？

**我的回答是：**

> 可以看出，所有的相关整合我都是用的一种结构，这种结构其实是可以适用于 **Dubbo** 和 **微服务** 架构的，也是出于习惯，即使有些单个的技术点可能只需用到一两个类文件，我也是“不厌其烦”，更或者说是想保持一种统一的风格，就还是把所有的目录结构全部新建上去。

**不过，最近我发现了一个很尴尬的事情：**

> 原来提交代码的时候，Git 自动把空目录过滤了！也就是空目录压根没有提交上来，内心瞬间“五味杂陈”（笑 cry）。瞬间想到，原来问我的是：大兄弟提交的空目录是干啥的？随后我花了一天的时间把所有的空目录补回来了：[9f202c2](https://github.com/intomylife/Docker/commit/9f202c263897b5394ff3b99bd0946d03bad95fd0)，[d4099d6](https://github.com/intomylife/Docker/commit/d4099d64e31104d84068950b37bcce611aa3a763)，[eaa0ca5](https://github.com/intomylife/Docker/commit/eaa0ca59fbe328ae497a9dbb33c8b3d485f24583)，[bde7ebb](https://github.com/intomylife/Docker/commit/bde7ebba99d6aea58dc1e5022ca4900e9be54351)，[3132fd5](https://github.com/intomylife/Docker/commit/3132fd5372cea40106d46acafd13b2e49a508485)

### 结构

**最外层目录：**

* xxx-commons：公用工程，用来引入公共的依赖，编写默认初始的配置信息，对应的工具类，以及统一返回实体类等等能抽取出来的一切公用代码。比如当项目中需要使用`Redis`做缓存，这时首先会在此工程中引入`Redis`的依赖`spring-boot-starter-data-redis`；其次编写`Redis`默认的最大连接数，连接超时时间等这些配置信息；然后考虑到兼容还需要统一解决序列化问题；最后把一些频繁使用的`Redis`操作封装到工具类中来简化调用。

* xxx-service：聚合服务工程，用来指定`SpringBoot`版本信息，配置部署信息，以及包含所需的所有子模块。也就是说这个父工程是没有其他代码的，主要就只有一个`pom.xml`文件。

**xxx-service：**

* 包含各种模块，比如**用户模块**，**订单模块**等等

* 从 [SpringBoot 整合各类框架和应用](https://github.com/intomylife/SpringBoot) 中的服务之间调用使用`Dubbo`，到 [简单了解微服务](https://github.com/intomylife/SpringCloud) 中的服务之间调用使用`服务注册与发现`，项目结构都是 **xxx-commons** + **xxx-service**

**xxx-service-api：**

* 每个模块中的“接口”工程

* 使用`Dubbo`技术调用服务时，需要先把对外提供的接口在内部实现了，然后再对外暴露并被引入到调用者的工程中，这时为了解耦合，会只把对外暴露的接口单独写在一个工程里，也就是当前的 **xxx-service-api** 工程；在 [SpringBoot 整合各类框架和应用](https://github.com/intomylife/SpringBoot) 中对应的目录结构如下

```
 - api              ## 对外暴露的接口
 - constant         ## 常量
 - dto              ## 扩展实体类
```

* 使用`服务注册与发现`技术调用服务时，而是使用的 **服务名** + **请求的完整签名** 来实现的，所以到 [简单了解微服务](https://github.com/intomylife/SpringCloud) 时目录结构变成了这样

```
 - constant         ## 常量
 - dto              ## 扩展实体类
```

**xxx-service-core：**

* 每个模块的“核心”工程

* 而此时两种架构的基础结构都有如下

```
 - controller       ## 前端控制器
 - domain           ## 基础实体类
 - mapper           ## mapper 接口
    - xml           ## mapper.xml 文件
 - service          ## 处理业务逻辑
```

* 如果是使用`Dubbo`技术，那么，要在自己内部实现对外暴露的接口，所有就有

```
 - api              ## 这里与 xxx-service-api 工程的包名统一
    - impl          ## 对外暴露接口的实现类
```

* 如果是使用`服务注册与发现`技术，那么，写远程调用的类就在

```
 - feign        ## 远程调用
    - fallback  ## 熔断方法
```

* 甚至，使用`服务注册与发现`技术时，可能还会把给其他服务调用的方法专门放在一个统一的包下管理

```
 - api          ## 被其他服务远程调用
```

**其他：**

可能之前有些项目结构不是和上面所描述的一致，但是现在开始往后的都会以这个为标准来搭建

## 联系我

> 如果您有任何疑问，或者有宝贵的建议，欢迎提交 [issues](https://github.com/intomylife/Docker/issues)。

或通过如下方式联系我：

* Email: intomylife@foxmail.com

## 关于我

* 个人博客：https://www.zouwencong.com

* CSDN：https://blog.csdn.net/qq_41402200
