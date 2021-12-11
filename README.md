# Cloud Alibaba Study

准备搞一个属于自己的后台服务，一点一点的完善她！


## 一、常用功能

### 1. 项目组成

#### 1.1 公共部分

* 工具类：redis、json、加解密
* 公共服务：mail、MQ、短信、oss

#### 1.2 服务生产者

#### 1.3 服务消费者

* 会员服务
  * 登陆、注册、认证、授权、校验设备唯一、授权时间
* 订单服务
  * 实际业务
* 自动升级服务
  * 检查更新、自动升级
* 网关服务 gateway
* 鉴权服务 auth

### 2. 项目各组件版本

* JDK 8
* Maven 3.6.3
* Spring 5.3.4
* Spring Boot 2.4.3
* Spring Cloud 2020.0.2
* Spring Cloud Alibaba 2021.1
  * LoadBalancer 3.0.2
  * OpenFeign 3.0.2
  * Nacos 2.0.3
* Mybatis Plus 3.4.0
* Logback 1.2.3
* Jackson 2.11.4
* Junit 5.7.1
* Tomcat 9.0.43

### 3. 常用配置

#### 3.2 Nacos注册中心配置自动刷新

1. yaml 中开启 refresh-enabled=true 时（默认开启），通过 applicationContext.getEnvironment.getProperty 直接获取。

   ```java
   @SpringBootApplication
   public class NacosConfigSimpleApplication {
       public static void main(String[] args) throws InterruptedException {
           ConfigurableApplicationContext applicationContext =
                     SpringApplication.run(NacosConfigSimpleApplication.class, args);
           //取到Spring的配置环境
           while(true){
               ConfigurableEnvironment environment = applicationContext.getEnvironment();
               String username = environment.getProperty("user.name");
               String age = environment.getProperty("user.age");
               System.out.println("username:"+username+" | age:"+age);
               TimeUnit.SECONDS.sleep(1);
           }
       }
   }
   ```

2. 单独使用，@NacosValue 获取最新值 nacos 配置信息需要写在配置类上。

   ```xml
   <dependency>
       <groupId>com.alibaba.boot</groupId>
       <artifactId>nacos-config-spring-boot-starter</artifactId>
   </dependency>
   ```

   ```java
   // 配置类
   @Configuration
   @EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
   @NacosPropertySource(dataId = "example", group="test",autoRefreshed = true)
   public class NacosConfiguration { }
   
   //测试类
   @Controller
   public class ConfigController {
       @NacosValue(value = "${test.data}", autoRefreshed = true)
       private boolean data;                                     
       @RequestMapping(value = "/test", method = GET)
       @ResponseBody
       public boolean get() { return data; }
   }
   ```

3. 结合 spring-cloud 使用，@Value 获取最新值一定要加 @RefreshScope 注解，配置文件中配置 refresh: true。

   ```xml
   <dependency>
       <groupId>com.alibaba.cloud</groupId>
       <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
   </dependency>
   ```

   ```yaml
   spring:
     application:
       name: example
     cloud:
       nacos:
         config:
         	server-addr:  127.0.0.1:8848
           extension-configs[0]:
             dataId: test.yml
             group: test
             refresh: true
   ```

   ```java
   // 测试类
   @RestController
   @RefreshScope
   public class TestController {
       @NacosValue(value = "${test.data}", autoRefreshed = true)
       private String data;
       @Value(value = "${test.data}")
       private String datas;
       @GetMapping("test")
       public String test() {
           return "data ：" + data + ",datas="+datas;
       }
   }
   ```

#### 3.1 JSR-303-Validation 注解说明

| 限制                      | 说明                                                         |
| :------------------------ | :----------------------------------------------------------- |
| @Null                     | 限制只能为null                                               |
| @NotNull                  | 限制必须不为null                                             |
| @AssertFalse              | 限制必须为false                                              |
| @AssertTrue               | 限制必须为true                                               |
| @DecimalMax(value)        | 限制必须为一个不大于指定值的数字                             |
| @DecimalMin(value)        | 限制必须为一个不小于指定值的数字                             |
| @Digits(integer,fraction) | 限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction |
| @Future                   | 限制必须是一个将来的日期                                     |
| @Max(value)               | 限制必须为一个不大于指定值的数字                             |
| @Min(value)               | 限制必须为一个不小于指定值的数字                             |
| @Past                     | 限制必须是一个过去的日期                                     |
| @Pattern(value)           | 限制必须符合指定的正则表达式                                 |
| @Size(max,min)            | 限制字符长度必须在min到max之间                               |
| @Past                     | 验证注解的元素值（日期类型）比当前时间早                     |
| @NotEmpty                 | 验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0） |

### 4. 下一步计划

1. 引入sleuth zipkin是怎么输出traceId的。
2. 异步发送邮件。
3. 复杂邮件发送。

## 二、官方文档

### 1. JDK8 相关文档

* [JDK8 API](https://docs.oracle.com/javase/8/docs/api/index.html)
* [JDK8 语言规范](https://docs.oracle.com/javase/specs/jls/se8/html/index.html)
* [JDK8 虚拟机规范](https://docs.oracle.com/javase/specs/jvms/se8/html/index.html)
* [虚拟机参数速查](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html)

### 2. Spring 全家桶

* [Spring 官网](https://spring.io/)
* [Spring 5.3.4](https://docs.spring.io/spring-framework/docs/current/reference/html/)
* [Spring WebFlux 5.3.4](https://docs.spring.io/spring-framework/docs/5.3.4/reference/html/web-reactive.html)
* [Spring Boot 2.4.3](https://docs.spring.io/spring-boot/docs/2.4.3/reference/html/)
* [Spring Boot Web 2.4.3](https://docs.spring.io/spring-boot/docs/2.4.3/reference/html/spring-boot-features.html#boot-features-developing-web-applications)
* [Spring Boot Actuator 2.4.3](https://docs.spring.io/spring-boot/docs/2.4.3/reference/html/production-ready-features.html#production-ready)
* [Spring Boot Maven Plugin Reference Guide 2.4.3](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/)
* [Spring Cloud 2020.0.2](https://docs.spring.io/spring-cloud/docs/2020.0.2/reference/html/)
  * [spring-cloud-commons](https://docs.spring.io/spring-cloud-commons/docs/3.0.2/reference/html/)
    * [Load Balancer](https://docs.spring.io/spring-cloud-commons/docs/3.0.2/reference/html/#spring-cloud-loadbalancer)
  * [spring-cloud-openfeign](https://docs.spring.io/spring-cloud-openfeign/docs/3.0.2/reference/html/)
  * [spring-cloud-gateway](https://docs.spring.io/spring-cloud-gateway/docs/3.0.2/reference/html/)
  * [spring-cloud-sleuth](https://docs.spring.io/spring-cloud-sleuth/docs/3.0.2/reference/html/)
  * [spring-cloud-stream](https://docs.spring.io/spring-cloud-stream/docs/3.1.2/reference/html/)
* [Spring Cloud Alibaba 2.1.2.RELEASE](https://spring-cloud-alibaba-group.github.io/github-pages/greenwich/spring-cloud-alibaba.html)
* [Spring Cloud Alibaba 2.2.6.RELEASE](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/zh-cn/index.html)
  * [Nacos 官网](https://nacos.io/zh-cn/docs/quick-start.html)
  * [Sentinel 官网](https://sentinelguard.io/zh-cn/)
    * [Sentinel 介绍](https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D)
    * [Sentinel 新手指南](https://github.com/alibaba/Sentinel/wiki/新手指南#公网-demo)
    * [Sentinel 如何使用](https://github.com/alibaba/Sentinel/wiki/%E5%A6%82%E4%BD%95%E4%BD%BF%E7%94%A8)
  * [Seata 官网](https://seata.io/zh-cn/docs/overview/what-is-seata.html)
* [Spting Cloud Alibaba 版本说明 2021-12-06](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)
* [Spring Cloud 与 Spring Boot版本对应关系](https://start.spring.io/actuator/info)

#### 1. 组件版本关系

| Spring Cloud Alibaba Version                              | Sentinel Version | Nacos Version | RocketMQ Version | Dubbo Version | Seata Version |
| --------------------------------------------------------- | ---------------- | ------------- | ---------------- | ------------- | ------------- |
| 2.2.7.RELEASE                                             | 1.8.1            | 2.0.3         | 4.6.1            | 2.7.13        | 1.3.0         |
| 2.2.6.RELEASE                                             | 1.8.1            | 1.4.2         | 4.4.0            | 2.7.8         | 1.3.0         |
| 2021.1 or 2.2.5.RELEASE or 2.1.4.RELEASE or 2.0.4.RELEASE | 1.8.0            | 1.4.1         | 4.4.0            | 2.7.8         | 1.3.0         |
| 2.2.3.RELEASE or 2.1.3.RELEASE or 2.0.3.RELEASE           | 1.8.0            | 1.3.3         | 4.4.0            | 2.7.8         | 1.3.0         |
| 2.2.1.RELEASE or 2.1.2.RELEASE or 2.0.2.RELEASE           | 1.7.1            | 1.2.1         | 4.4.0            | 2.7.6         | 1.2.0         |
| 2.2.0.RELEASE                                             | 1.7.1            | 1.1.4         | 4.4.0            | 2.7.4.1       | 1.0.0         |
| 2.1.1.RELEASE or 2.0.1.RELEASE or 1.5.1.RELEASE           | 1.7.0            | 1.1.4         | 4.4.0            | 2.7.3         | 0.9.0         |
| 2.1.0.RELEASE or 2.0.0.RELEASE or 1.5.0.RELEASE           | 1.6.3            | 1.1.1         | 4.4.0            | 2.7.3         | 0.7.1         |

#### 2. 毕业版本依赖关系(推荐使用)

| Spring Cloud Version        | Spring Cloud Alibaba Version      | Spring Boot Version |
| --------------------------- | --------------------------------- | ------------------- |
| Spring Cloud 2020.0.1       | 2021.1                            | 2.4.2               |
| Spring Cloud Hoxton.SR12    | 2.2.7.RELEASE                     | 2.3.12.RELEASE      |
| Spring Cloud Hoxton.SR9     | 2.2.6.RELEASE                     | 2.3.2.RELEASE       |
| Spring Cloud Greenwich.SR6  | 2.1.4.RELEASE                     | 2.1.13.RELEASE      |
| Spring Cloud Hoxton.SR3     | 2.2.1.RELEASE                     | 2.2.5.RELEASE       |
| Spring Cloud Hoxton.RELEASE | 2.2.0.RELEASE                     | 2.2.X.RELEASE       |
| Spring Cloud Greenwich      | 2.1.2.RELEASE                     | 2.1.X.RELEASE       |
| Spring Cloud Finchley       | 2.0.4.RELEASE(停止维护，建议升级) | 2.0.X.RELEASE       |
| Spring Cloud Edgware        | 1.5.1.RELEASE(停止维护，建议升级) | 1.5.X.RELEASE       |



### 3. Mybatis

* [Mybatis3](https://mybatis.org/mybatis-3/zh/index.html)
* [Mybatis3 XML映射文件](https://mybatis.org/mybatis-3/zh/sqlmap-xml.html)
* [Mybatis-plus3.4.0](https://mp.baomidou.com/guide/)

### 4. 其他文档

* [Apache Maven 官方文档](https://maven.apache.org/guides/index.html)
* [JSR 303](https://jcp.org/en/jsr/detail?id=303)
  * spring-boot-starter-validation
* [Slf4j 官网](http://www.slf4j.org/)
* [Logback 官网](http://logback.qos.ch/)
* [Jackson Github](https://github.com/FasterXML/jackson)
  * [Jackson 1.9.9 API 开源中国](https://tool.oschina.net/apidocs/apidoc?api=jackson-1.9.9)
* [Junit 5官网](https://junit.org/junit5/docs/current/user-guide/)
* [Tomcat 官网](https://tomcat.apache.org/)