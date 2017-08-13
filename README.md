
# Simple Stub Service
桩服务

> 现有桩：

>> Stub 0 - <a href="http://host:port/simple-stub-service/stub/0" target="_blank">http://host:port/simple-stub-service/stub/0</a>

<br/>

# <center>桩服务配置和使用的方法</center>

* 配置桩映射

>> 1.打开WEB-INF/classes/conf/stub-config.properties

>> 2.添加stub[i].response.contentType和stub[i].response.data响应配置

>>> 1）stub[i].response.contentType：响应数据的内容类型，可取值支持各种数据类型:

>>>> application/json - JSON数据类型

>>>> text/xml - XML结构数据类型

>>>> text/html; charset=UTF-8 - HTML页面类型

>>>> ...

>>> 2）stub[i].response.data：响应数据的内容，以[BINARY:]开头的Base64数据被当做二进制内容处理。根据stub[i].response.contentType内容类型的不同显示对应格式的数据。

* 访问桩服务的方法

>> 1.启动桩服务

>> 2.CONTEXT_PATH/stub/{i}

>>> 1）CONTEXT_PATH: 服务启动后的上下文路径。

>>> 2）i是stub-config.properties里的服务索引。

<br/>