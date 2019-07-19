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
 切面代码
 public class MyAspect implements MethodInterceptor {
     //aop联盟
     @Override
     public Object invoke(MethodInvocation methodInvocation) throws Throwable {
         //拦截方法

         System.out.println("开启事务");
         //放行
         Object retobj = methodInvocation.proceed();
         System.out.println(retobj);
         System.out.println("提交事务");
         return retobj;
     }
 }
 ```
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
 4.对于cglib和JDK尽量使用aspectj的自由开发(没有使用注解),使用方法如下，可以参考https://www.cnblogs.com/whgk/p/6627187.html
 xml文件配置，aspectj是基于Java的aop框架
 ```xml
 <?xml version="1.0" encoding="UTF-8"?>
 <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:aop="http://www.springframework.org/schema/aop"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
                         http://www.springframework.org/schema/beans/spring-beans.xsd
                         http://www.springframework.org/schema/context
                         http://www.springframework.org/schema/context/spring-context.xsd
                         http://www.springframework.org/schema/aop
                         http://www.springframework.org/schema/aop/spring-aop.xsd">

     <!--配置userService-->
     <bean id="userService" class="service.UserService"></bean>
     <!--配置切面对象-->
     <bean id="myAspectj" class="Java.MaAspectjbean>
     <!--配置aop-->
     <aop:config>
         <!--aop指定切面-->
         <aop:aspect ref="myAspectj">
              <!--定义切入点-->
             <aop:pointcut id="myPointcut" expression="execution(* service.*.*(..))"/>
             <!--配置前置通知-->
             <aop:before method="aspectBefore" pointcut-ref="myPointcut"></aop:before>
             <!--配置后置通知-->
             <aop:after-returning method="aspectAfterReturning" pointcut-ref="myPointcut"></aop:after-returning>
             <!--配置环绕通知-->
             <aop:around method="aspectAround" pointcut-ref="myPointcut"></aop:around>
         </aop:aspect>
     </aop:config>
 </beans>
 ```
 切面代码ASPECT
 ```xml
  public class MaAspectj {

      //前置通知
      public void aspectBefore(){
          System.out.println("前置通知");
      }
      //后置通知
      public void aspectAfterReturning(){
          System.out.println("后置通知");
      }

      //环绕通知
      public Object aspectAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
          System.out.println(proceedingJoinPoint.getSignature().getName());
          Object retobj = proceedingJoinPoint.proceed();
          System.out.println(retobj+"操作了"+proceedingJoinPoint.getSignature().getName());

          return retobj;
      }
  }
  ```
 如果使用了环绕通知，就不用使用前面两个了,expression有多个表达式，可以上网查一下

 5.aspectj使用注解方式

###Spring 的jdbcTemplate学习
c3p0与dbcp区别：<br>
dbcp没有自动回收空闲连接的功能<br>
c3p0有自动回收空闲连接功能<br>
两者主要是对数据连接的处理不同c3p0提供最大空闲时间，dbcp提供最大连接数。前者是如果连接时间超过最大连接时间，就会断开当前连接。dbcp如果超过最大连接数，就会断开所有连接。<br>
JDBC和OBDC都是链接数据库的




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
 