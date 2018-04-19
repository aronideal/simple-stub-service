
# 桩服务配置与使用的方法

## 配置桩映射

### 1. 打开 WEB-INF/classes/conf/stub-config.properties

### 2. 添加 stub[i].response.contentType和stub[i].response.data 响应配置

#### stub[i].response.contentType：响应数据的内容类型，可取值支持各种数据类型:

    application/json - JSON数据类型
    text/xml - XML结构数据类型
    text/html; charset=UTF-8 - HTML页面类型
    ...

#### stub[i].response.data：响应数据的内容，以[BINARY:]开头的Base64数据被当做二进制内容处理。根据stub[i].response.contentType内容类型的不同显示对应格式的数据。

### 3. 添加 stub[i].response.processTimeMillis 配置，设定任务完成的毫秒数

## 访问桩服务的方法

### 1. 启动桩服务

### 2. 访问配置好的桩 [EXTERNAL_URL]/stub/{i}

#### EXTERNAL_URL: 服务启动后的上下文路径。

#### i是stub-config.properties里的服务索引。

# 现有桩：

Stub 0 - [EXTERNAL_URL]/stub/0

# License
The MIT License is here, [LICENSE](./LICENSE).

