# JWLT
JavaWeb Learning Tour

## spring Ioc学习
主要学习到用注解替换xml的配置：如：
action层用 @Controller
service层用 @Service
dao层用 @Repository

xml文件如下
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.springframework.org/schema/context
                        http://www.springframework.org/schema/context/spring-context.xsd">

    <!--开启注解-->
    <context:annotation-config/>
    <!--注解的位置-->
    <context:component-scan base-package="action"/>
    <context:component-scan base-package="dao"/>
    <context:component-scan base-package="service"/>
</beans>
```

base-package指向必须是package,不能是其他类型
如果xml文件上的xmlns的网站路径显示错误，但是路径没错，可以通过以下步骤解决
1. File -> settings -> Languages & Frameworks -> schemas and DTDs 的右下方的+号添加报错的url

当该层调用别的层时候可以通过@Autowired  
Autowired根据类型注入值  
如果是一个接口，从容器找接口实现类  
如果是一个实现类，就找类  
