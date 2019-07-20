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

 5.<strong>aspectj使用注解方式（常用）</strong>
 xml配置
 ```xml
 <!--开启注解-->
    <context:annotation-config/>
    <context:component-scan base-package="service"></context:component-scan>
    <context:component-scan base-package="aspect"></context:component-scan>
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
    <aop:config>
        <aop:aspect ref="myAspectj"></aop:aspect>
    </aop:config>
```
aspect代码
```xml
@Component
@Aspect
public class myAspectj {

    @Before("execution(* service.*.*(..))")
    public void aspectBefore(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+".......前置通知");
    }
}
```
需要注意的是不同的编辑器也可能出错

##Spring 的jdbcTemplate学习
###c3p0与dbcp的xml配置：<br>
```xml
<!--&lt;!&ndash;配置DBCP数据源&ndash;&gt;-->
    <!--&lt;!&ndash;配置datasource对象&ndash;&gt;-->
    <!--<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">-->
        <!--<property name="driverClassName" value="com.mysql.jdbc.Driver"></property>-->
        <!--<property name="url" value="jdbc:mysql://localhost:3306/springJDBC"></property>-->
        <!--<property name="username" value="root"></property>-->
        <!--<property name="password" value="123456"></property>-->
    <!--</bean>-->

    <!--配置c3p0数据源-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/springJDBC"></property>
        <property name="user" value="root"></property>
        <property name="password" value="123456"></property>
    </bean>

    <!--配置jdbcTemp对象-->
    <bean id="jdbcTemp" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
    <!--配置dao-->
    <bean id="userDao" class="dao.UserDao">
        <property name="jdbcTemplate" ref="jdbcTemp"></property>
    </bean>
```
在连接数据库的时候，dbcp和c3p0两个数据源只能用一个<br>
###c3p0与dbcp区别：<br>
dbcp没有自动回收空闲连接的功能<br>
c3p0有自动回收空闲连接功能<br>
两者主要是对数据连接的处理不同:c3p0提供最大空闲时间，dbcp提供最大连接数。前者是如果连接时间超过最大连接时间，就会断开当前连接。dbcp如果超过最大连接数，就会断开所有连接。<br>
JDBC和OBDC都是链接数据库的

##事务管理器
主要是xml文件的区别
###手动事务管理器配置
```xml
 <!--读取db.properties-->
    <context:property-placeholder location="classpath:db.properties"></context:property-placeholder>
    <!--配置c3p0数据源-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${driverClass}"></property>
        <property name="jdbcUrl" value="${jdbcUrl}"></property>
        <property name="user" value="${user}"></property>
        <property name="password" value="${password}"></property>
    </bean>
    <!--配置dao-->
    <bean id="acconutDao" class="Dao.impl.AccountDaoImpl">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--配置事务管理器-->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--配置dataSource-->
        <property name="dataSource" ref="dataSource"></property>
    </bean>
    <!--配置事务模板-->
    <bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
        <!--事务管理器-->
        <property name="transactionManager" ref="txManager"></property>
    </bean>

    <!--配置Service-->
    <bean id="accountService" class="Service.impl.AccountServiceImpl">
        <property name="acconutDao" ref="acconutDao"></property>
        <!--配置事务模板-->
        <property name="transactionTemplate" ref="transactionTemplate"></property>
    </bean>
```
使用的函数代码
```xml
@Override
    //spring配置事务模板
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    public void transferMoney(final String outer, final String inner, final Integer money) {
        this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                //扣钱
                accountDao.out(outer,money);
                //进账
                accountDao.in(inner,money);
            }
        });
    }
```
###spring事务管理器半自动
```xml
<!--读取db.properties-->
    <context:property-placeholder location="classpath:db.properties"></context:property-placeholder>
    <!--配置c3p0数据源-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${driverClass}"></property>
        <property name="jdbcUrl" value="${jdbcUrl}"></property>
        <property name="user" value="${user}"></property>
        <property name="password" value="${password}"></property>
    </bean>
    <!--配置dao-->
    <bean id="acconutDao" class="Dao.impl.AccountDaoImpl">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
    <!--配置事务管理器-->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--配置dataSource-->
        <property name="dataSource" ref="dataSource"></property>
    </bean>
    <!--配置Service-->
    <bean id="accountService" class="Service.impl.AccountServiceImpl2">
        <property name="acconutDao" ref="acconutDao"></property>
    </bean>

    <!--配置工厂代理-->
    <bean id="proxyService" class="org.springframework.transaction.interceptor.TransactionProxyFactoryBean">
        <!--接口-->
        <property name="proxyInterfaces" value="Service.IAccountService"></property>
        <!--目标对象-->
        <property name="target" ref="accountService"></property>
        <!--切面对象：spring做-->

        <!--事务管理器-->
        <property name="transactionManager" ref="txManager"></property>
        <!--transactionAttributes事务的属性配置
            key:写方法名
            value:写事务配置
            参数 readOnly：只能读
                 +所报的异常代表继续提交
                 -所报的异常代表回滚
        -->
        <property name="transactionAttributes">
            <props>
                <prop key="transferMoney">PROPAGATION_REQUIRED,ISOLATION_DEFAULT,+java.lang.ArithmeticException</prop>
                <prop key="add">PROPAGATION_REQUIRED,ISOLATION_DEFAULT</prop>
                <prop key="delete">PROPAGATION_REQUIRED,ISOLATION_DEFAULT</prop>
            </props>
        </property>
    </bean>
```
工厂bean生产代理，事务半自动管理只要写xml文件
使用时通过context获取代理，如context.getBean("proxyService");
###aop事务配置（掌握）
```xml
<!--读取db.properties-->
    <context:property-placeholder location="classpath:db.properties"></context:property-placeholder>
    <!--配置c3p0数据源-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${driverClass}"></property>
        <property name="jdbcUrl" value="${jdbcUrl}"></property>
        <property name="user" value="${user}"></property>
        <property name="password" value="${password}"></property>
    </bean>
    <!--配置dao-->
    <bean id="acconutDao" class="Dao.impl.AccountDaoImpl">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
    <!--配置事务管理器-->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--配置dataSource-->
        <property name="dataSource" ref="dataSource"></property>
    </bean>
    <!--配置Service-->
    <bean id="accountService" class="Service.impl.AccountServiceImpl2">
        <property name="acconutDao" ref="acconutDao"></property>
    </bean>

    <!--使用spring-aop标签来配置-->
    <!--配置通知事务管理器-->
    <tx:advice id="interceptor" transaction-manager="txManager">
        <!--事务详情:传播行为，隔离级别-->
        <tx:attributes>
            <tx:method name="transferMoney" propagation="REQUIRED" isolation="DEFAULT"/>
        </tx:attributes>
    </tx:advice>
    <!--把事务的通知与切入点关联-->
    <aop:config>
        <!--切入点-->
        <aop:pointcut id="myPointCut" expression="execution(* Service..*.*(..))"></aop:pointcut>
        <!--事务与切入点关联-->
        <aop:advisor advice-ref="interceptor" pointcut-ref="myPointCut"></aop:advisor>
    </aop:config>
```
使用时，通过context获取，如context.getBean("accountService");
###基于注解的事务管理器配置
```xml
<!--读取db.properties-->
    <context:property-placeholder location="classpath:db.properties"></context:property-placeholder>
    <!--配置c3p0数据源-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${driverClass}"></property>
        <property name="jdbcUrl" value="${jdbcUrl}"></property>
        <property name="user" value="${user}"></property>
        <property name="password" value="${password}"></property>
    </bean>
<!--配置事务管理器-->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--配置dataSource-->
        <property name="dataSource" ref="dataSource"></property>
    </bean>
    <!--开启事务注解驱动-->
    <tx:annotation-driven transaction-manager="txManager"></tx:annotation-driven>
```
可以在整个类的前面或者某个方法前面上添加@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)，里面的参数由自己定，作用不一样。不同的位置上添加的作用区域不同
###对于事务管理器推荐使用aop和注解方式
##Maven使用总结
 1.maven有一些jar包没有，需要自行下载放到项目下
 2.jar包可以到https://maven.aliyun.com/mvn/search或者https://mvnrepository.com查找相关的依赖

##xml配置文件list和map用法
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
 