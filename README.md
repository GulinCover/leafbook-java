# 项目leafbook后端工程

## 基础信息
  **springboot:2.3.2.RELEASE**

  **springcloud:Hoxton.SR6**

  **alibabacloud:2.2.3.RELEASE**

  **java:1.8**

## 使用到的功能
  **spring-gateway**

    全局网关,解析前端所有请求所带有的jwt加密串。提取当中的userId,csrf等数据,并添加到请求头传递给对外api服务。

  **nimbus-jose-jwt**

    作为用户登录后的jwt加密方法

  **shiro**

    登录认证,并采取bcyrpt密码加密(当前项目未使用授权功能)

  **mysql**

    使用到mybatis-plus

    druid连接池

  **openfeign**

    服务间通讯

## 使用到的中间件
  **nacos:1.3.2:**

    全局注册中心及配置中心

  **sentinel:1.8.0:**

    对外api熔断降级配置中心

  **seata:1.3.0:**

    分布式事务解决方案

  **rocketMQ:4.8.0:**

    邮箱验证码发送中间件
