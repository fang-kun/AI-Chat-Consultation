# AI聊天问诊

AI聊天问诊 (chatthymeleaf-test-master) 采用Springboot + Thymeleaf 的架构实现。

<br>

本项目可与医院管理系统结合使用，也可独立运行。
医院管理系统仓库：[HospitalDevelopment](https://github.com/fang-kun/HospitalManagement)

<br>

以下是**独立运行**的配置与运行方法。

<br>

## 集成开发环境

- JDK：corretto-11
- Maven：3.8.4
- Idea：2021.3.1

## 基础搭建方法

1. 将项目Git到本地Idea工作空间，并导入Idea配置好上述JDK环境。

<br>

2. 配置 chatthymeleaf-test-master 项目（请确保在此之前Maven已配置在Idea中）。打开 pom.xml 文件，右键->Maven->Reload Project，引入依赖。启动Springboot启动类。

<br>

3. 项目正常启动后，浏览器运行 http://localhost:8091/index/
