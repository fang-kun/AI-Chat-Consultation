# AI聊天咨询

AI聊天咨询 (chatthymeleaf-test-master) 采用Springboot + Thymeleaf 的架构实现。

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

## API配置方法

**前提说明**：目前（2024.5.24）项目中的API秘钥仍可用，但使用的是国内的API接口，但本项目接口完全兼容OpenAI API，只需将本项目中原始API域名或API URL替换为OpenAI的，API密钥也替换成OpenAI的秘钥。

<br>

在 `service/GptServiceImpl.java` 文件下，可替换 API URL 和 API 秘钥，分别位于代码33行和63行。使用者可根据自己的API秘钥进行替换。
