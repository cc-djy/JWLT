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

## git同步
<a href="https://www.cnblogs.com/schaepher/p/4933873.html" >github团队项目合作流程</a>

## spring Aop学习
### 什么是AOP
AOP(Aspect-Oriented Programming), 即 面向切面编程, 它与 OOP( Object-Oriented Programming, 面向对象编程) 相辅相成, 提供了与 OOP 不同的抽象软件结构的视角.
在 OOP 中, 我们以类(class)作为我们的基本单元, 而 AOP 中的基本单元是 Aspect(切面)，纯Java语言

### AOP有什么用
 1.对业务逻辑的各个部分进行隔离，降低耦合度，提高程序可重用性
 2.经典应用：事物管理、性能测试、安全检查、缓存
 
###AOP学习问题总结
 1.aop用JDK产生代理拦截时，业务类一定要用接口
 2.cglib代理拦截时，业务类可以是接口也可以不是接口
 3.aop全自动配置
 ```xml
 <!--全自动aop配置
     1.在bean中配置aop约束
     2.配置aop:config,把切入点和和通知结合
     -->
     <aop:config proxy-target-class="true">
         <!--切入点
             expression:表达式
             每个事务的都切入
         -->
         <aop:pointcut id="myPointcut" expression="execution(* service.*.*(..))"/>
         <!--通知 关联切入点
         advice-ref：链接你的切入点的方法
         pointcut-ref：自己定义所需要切入的位置
         -->
         <aop:advisor advice-ref="aspect" pointcut-ref="myPointcut"></aop:advisor>
     </aop:config>
 ```

###Maven使用总结
 1.maven有一些jar包没有，需要自行下载放到项目下
 2.jar包可以到https://maven.aliyun.com/mvn/search或者https://mvnrepository.com查找相关的依赖

###xml配置文件list和map用法
 ```xml
 <bean id="" class="">
     <property name="sets">
         <set>
             <value>set1</value>
             <value>set2</value>
         </set>
     </property>
     <property name="lists">
         <list>
             <value>list1</value>
             <value>list2</value>
         </list>
     </property>
     <property name="maps">
         <map>
             <entry key="map1" value="map1" />
             <entry key="map2" value="map2" />
             <entry key="map3" value="map3" />
         </map>
     </property>
 </bean>
 ```