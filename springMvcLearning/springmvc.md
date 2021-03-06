##springmvc项目xml配置
###springmvc的web.xml配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
        http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
  <!-- 处理字符乱码问题 -->
  <filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>*.html</url-pattern>
  </filter-mapping>
  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>*.jsp</url-pattern>
  </filter-mapping>
  <!--找到上下文.xml-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/applicationContext.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  <!--DispatcherServlet会查询一个或多个处理器映射（handler mapping）并根据请求所携带的URL信息进行决策，将请求发送给哪个SpringMVC控制器（controller）-->
  <servlet>
    <servlet-name>DispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  </servlet>
  <!--拦截.do提交方式-->
  <servlet-mapping>
    <servlet-name>DispatcherServlet</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
  <!--rest风格-->
    <servlet-mapping>
      <servlet-name>DispatcherServlet</servlet-name>
      <url-pattern>/rest/*</url-pattern>
    </servlet-mapping>
</web-app>
```
###DispatcherServlet.xml配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.springframework.org/schema/context
                        http://www.springframework.org/schema/context/spring-context.xsd
                        http://www.springframework.org/schema/mvc
                        http://www.springframework.org/schema/mvc/spring-mvc.xsd
                        http://www.springframework.org/schema/tx
                        http://www.springframework.org/schema/tx/spring-tx.xsd
                        http://www.springframework.org/schema/aop
                        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--springmvc注解驱动-->
    <mvc:annotation-driven></mvc:annotation-driven>
    <!--扫描包-->
    <context:component-scan base-package="com.*"></context:component-scan>
    <!--配置url处理映射
    BeanNameUrlHandlerMapping:通过url名字，找到对应的bean的name的控制器
    -->
    <!--<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"></bean>-->

    <!--配置控制器处理适配器处理映射-->
    <!--<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"></bean>-->
    <!--配置控制器-相当于配置访问路径-->
    <!--<bean class="com.cc.controller.UserController"></bean>-->

    <!--配置资源视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!--前缀-->
        <property name="prefix" value="/WEB-INF/views/"></property>
        <!--后缀-->
        <property name="suffix" value=".jsp"></property>
    </bean>

</beans>
```
##springmvc注解方式的使用
使用注解后的网站访问路径会改变，通过controller来访问你需要访问的路径，如
```java
@Controller
@RequestMapping("/userController")
public class UserController{
    @RequestMapping("/getUser")
    public ModelAndView getUser(@RequestParam("id") Long id){
        System.out.println(id);
        ModelAndView mv = new ModelAndView("user/userList");
        mv.addObject("name","cc");
        return mv;
    }
    @RequestMapping("/toAddUser")
    public String toAddUser(){
        return "user/addUser";
    }
    @RequestMapping("/showUser")
    public String showUser(User user){
        System.out.println(user.toString());
        return "user/userList";
    }
}
```
当你需要访问到toAddUser.jsp页面的时候，你只需要在启动项目后，在后面加上/userController/toAddUser.do。return回来的user/addUser，是在dispatcherServlet-servlet.xml上配置好前缀和后缀了。
##通过注解接受请求参数
addUser.jsp
```jsp
<%--
  Created by IntelliJ IDEA.
  User: CD
  Date: 2019/7/24
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="../userController/showUser.do" method="get">
    <input name="username" type="text"><br>
    <input name="password"  type="password"><br>
    <input name="age" type="text"><br>.
    <input type="submit"> <br>
</form>
</body>
</html>
```
action="../userController/showUser.do"中的../是表示上一级目录开始，当然还有./表示当前同级目录开始，/表示根目录开始  
接受请求参数的方法主要用5种，常用的是前面2种，后3种也用，还有一些别的方法，可以参考：https://my.oschina.net/u/2331760/blog/1811725。
 1. 同过和提交的jsp页面的name同名来接受，如：
 ```java
@RequestMapping("/addUser")
    public String addUser(String username, String password, Integer age){};
```
 2.通过pojo来接收
 user.java
 ```java
@Component
public class User {
    @Value("cc")
    private String username;

    @Value("123456")
    private String password;

    @Value("12")
    private Integer age;
}
```
 controller里面的addUser代码
 ```java
@RequestMapping("/addUser")
 public String addUser(User user){};
```
 3.通过包装类型参数来接收
 UserExt.java
 ```java
public class UserExt {
    private User user;
}
```  
addUser.jsp
```jsp
<form action="../userController/addUser2.do" method="get">
    <input name="user.username" type="text"><br>
    <input name="user.password"  type="password"><br>
    <input name="user.age" type="text"><br>.
    <input type="submit"> <br>
</form>
```
 controller里面的addUser代码
 ```java
public String addUser2(UserExt userExt){};
```
与前面两种方法对比，jsp页面代码改变了，所有的name前面都加上user.,user名字是根据UserExt类里面的user来定的
 4.通过list类型参数来接收  
 addUser.jsp
 ```jsp
<form action="../userController/addUser3.do" method="get">
    user1.username:<input name="userList[0].username" type="text"><br>
    user1.password:<input name="userList[0].password"  type="password"><br>
    user1.age:<input name="userList[0].age" type="text"><br>
    user2.username:<input name="userList[1].username" type="text"><br>
    user2.password:<input name="userList[1].password"  type="password"><br>
    user2.age:<input name="userList[1].age" type="text"><br>
    <input type="submit" value="save"> <br>
</form>
```
 pojo的userExt代码
 ```java
public class UserExt {
    private List<User> userList = new ArrayList<User>();
}
```
 controller里面的addUser代码
 ```java
@RequestMapping("/addUser3")
    public String addUser3(UserExt userExt){
        System.out.println("list"+userExt.getUserList());
        return "user/userList";
    }
```
 5.通过map类型参数来接收
  addUser.jsp
  ```jsp
<form action="../userController/addUser4.do" method="get">
    user1.username:<input name="userMap['username']" type="text"><br>
    user1.password:<input name="userMap['password']"  type="password"><br>
    user1.age:<input name="userMap['age']" type="text"><br>
    <input type="submit" value="save"> <br>
</form>
 ```
  pojo的userExt代码
  ```java
    public class UserExt {
        private Map<String, Object> userMap = new HashMap<String, Object>();
    }
 ```
  controller里面的addUser代码
  ```java
 @RequestMapping("/addUser4")
     public String addUser4(UserExt userExt){
         System.out.println("map"+userExt.getUserMap());
         return "user/userList";
 }
 ```
 ***五种方式中，需要注意前面两种的代码和后面三种区别有点大***
 ##模拟数据库进行数据传输
  ```java
  @RequestMapping("/toListUser")
     public String toListUser(Model model){
         //模拟数据库存取数据
         List<User> userList = new ArrayList<User>();
         User user1 = new User("cc","123456",22);
         User user2 = new User("djy","123456",23);
         User user3 = new User("cc-djy","123456",2);
         userList.add(user1);
         userList.add(user2);
         userList.add(user3);
         model.addAttribute("userList",userList);
         return "user/userList";
     }
 ```
 ```jsp
 <table border="1">
     <tr>
         <td>姓名</td>
         <td>密码</td>
         <td>年龄</td>
     </tr>
     <c:forEach items="${userList}" var="user">
         <tr>
             <td>${user.username}</td>
             <td>${user.password}</td>
             <td>${user.age}</td>
         </tr>
     </c:forEach>
 </table>
 ```
 
 
 
 
 ##springmvc一些注解理解
 1. @RequestMapping("/userController")：针对访问路径，项目查找路径，如
 public class UserController{
     @RequestMapping("/toListUser")
     public String toListUser(Model model){}
 }
 访问的路径是/userController/toListUser.do，而不是/UserController/toListUser
 2. @RequestMapping("/toEditUser1/{username}")
    public String toEditUser1(@PathVariable String username, Model model)中的@PathVariable：针对前端传来的参数作为查找路径和参数，是用来获得请求url中的动态参数
 3.public String test1(@RequestParam(value = "uid",required = true,defaultValue = "30") Integer uid)
 /**
   * @RequestParam(value = "uid",required = true,defaultValue = "30")
   * required = true时，没有加入defaultValue时，uid为必传
   * efaultValue = "30",当uid没有传时，默认给30
  */
 
##springmvc ajax传输和请求json数据方法
jar包依赖，版本不要太高
pom.xml
```xml
       <dependency>
          <groupId>com.fasterxml.jackson.core</groupId>
          <artifactId>jackson-core</artifactId>
          <version>2.9.5</version>
      </dependency>
      <dependency>
          <groupId>com.fasterxml.jackson.core</groupId>
          <artifactId>jackson-annotations</artifactId>
          <version>2.9.5</version>
      </dependency>
      <dependency>
          <groupId>com.fasterxml.jackson.core</groupId>
          <artifactId>jackson-databind</artifactId>
          <version>2.9.5</version>
      </dependency>
```
###第一种,只能获取json格式
DispatcherServlet-servlet.xml在mvc:annotation-driven中加入
```xml
        <mvc:message-converters>
            <bean class="org.springframework.http.converter.StringHttpMessageConverter"/>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html; charset=UTF-8</value>
                        <value>application/json;charset=UTF-8</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
```
Java用法
```java
/**
     * @RequestBody：把json数据转成模型对象
     * @ResponseBody：返回json数据，把模型对象转成json
     */
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    @ResponseBody
    public Student register(@RequestBody Student student){
        System.out.println(student);
        return student;
    }
```
ajax请求路径
```jsp
<script type="text/javascript">
        function register() {
            var name = $('#name').val();
            var password = $('#password').val();
            var jsonObj={"name":name,"password":password};
            //把json对象转字符串
            var parameters = JSON.stringify(jsonObj);
            console.log(jsonObj);
            console.log("parameters="+parameters);
            var url='${pageContext.request.contextPath}/stuController/register.do';
            $.ajax({
                type:"post",
                url:url,
                data:parameters,
                contentType:"application/json;charset=utf-8",
                success:function (msg) {
                    console.log("success:"+ msg.name);
                    // location.href="../userController/toListUser.do"
                }
            });
        }
    </script>
```
###第二种，多视图，可以获取多种格式
```xml
<!--配置多视图-->
    <bean class="org.springframework.web.servlet.view.ContentNegotiatingViewResolver">
        <!-- 配置支持媒体类型 -->
        <property name="contentNegotiationManager">
            <bean class="org.springframework.web.accept.ContentNegotiationManagerFactoryBean">
                <property name="mediaTypes">
                    <map>
                        <entry key="json" value="application/json"></entry>
                        <entry key="xml" value="application/xml"></entry>
                        <entry key="pdf" value="application/pdf"></entry>
                    </map>
                </property>
            </bean>
        </property>
        <!-- 指定默认视图 -->
        <property name="defaultViews">
            <!-- 支持多个视图 -->
            <list>
                <!-- 对josn格式视图支持 -->
                <bean class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"></bean>
                <!-- xml格式视图支持 -->
                <bean class="org.springframework.web.servlet.view.xml.MarshallingView">
                    <constructor-arg>
                        <bean class="org.springframework.oxm.jaxb.Jaxb2Marshaller">
                            <property name="classesToBeBound">
                                <list>
                                    <!--需要用到多视图的pojo类路径-->
                                    <value>com.cc.pojo.Student</value>
                                </list>
                            </property>
                        </bean>
                    </constructor-arg>
                </bean>
            </list>
        </property>
    </bean>
```
访问路径，用rest风格，如果用.do会被拦截，在web.xml有配置,路径写法  
如果要获取json数据，路径写法为
```jsp
var url='${pageContext.request.contextPath}/rest/stuController/register.json';
```
如果要获取xml,需要在pojo类加上@XmlRootElement(name = "Student")，Student为类名，访问路径写法为
```jsp
var url='${pageContext.request.contextPath}/rest/stuController/register.xml';
```

##找不到mapper映射文件解决方法，
 1. 如果mapper的xml放在Java的mapper下，需要在pom.xml的build下加入下面代码
```xml
<!--如果不添加此节点mybatis的mapper.xml文件都会被漏掉。-->
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
```
mybaits.xml写法
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <!--别名配置-->
    <typeAliases>
        <package name="com.ssm.pojo"></package>
    </typeAliases>
    <mappers>
        <!--加载映射文件,和ssm里的接口一致-->
        <package name="com.ssm.mapper"></package>
    </mappers>

</configuration>
```
 2. 如果不加入这一句话也ok，把mapper.xml文件移动到resource文件夹下
mabatis.xml的写法改为
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <!--别名配置-->
    <typeAliases>
        <package name="com.ssm.pojo"></package>
    </typeAliases>
    <mappers>
        <!--加载映射文件,和ssm里的接口一致-->
        <mapper resource="mapper/ManagerMapper.xml"></mapper>
    </mappers>

</configuration>
```

##springmvc拦截器
在springmvc下添加xml如
```xml
    <!--配置拦截器-->
    <mvc:interceptors>
        <!--配置登录拦截器-->
        <mvc:interceptor>
            <mvc:mapping path="/**" />
            <bean class="com.ssm.interceptor.LoginInterceptor"></bean>
        </mvc:interceptor>
    </mvc:interceptors>
```
新建一个拦截包和拦截器的类代码为
```java
package com.ssm.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截
        //排除不需要拦截路径
        if (request.getRequestURI().endsWith("managerController/managerLogin.do")){
            return true;
        }
//        如果已经登录,放行
        if (request.getSession().getAttribute("manager")!=null){
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/views/login.html").forward(request,response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("未返回视图前 后处理.......");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("返回视图前 后处理.......");
    }
}

```
拦截器主要用为避免不合法访问

##页面缓存
主要作用：缓存经常访问但不经常更改内容的页面，来达到优化项目的目的
新建一个oscache.properties文件放在资源文件下，配置如下
```properties
#不保存在内存
cache.memory=false
cache.persistence.class=com.opensymphony.oscache.plugins.diskpersistence.DiskPersistenceListener
#缓存路径
cache.path=F:\\study\\JavaEE\\JWLT\\springMvcLearning\\src\\main\\webapp\\cache
```
在web.xml文件下加入
```xml
<!--页面缓存,一般缓存不经常更新的-->
  <filter>
    <filter-name>oscache</filter-name>
    <filter-class>com.opensymphony.oscache.web.filter.CacheFilter</filter-class>
    <init-param>
      <param-name>time</param-name>
      <!--页面缓存时间设置-->
      <param-value>10</param-value>
    </init-param>
    <init-param>
      <param-name>scope</param-name>
      <param-value>application</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>oscache</filter-name>
    <!--需要进行页面缓存的路径-->
    <url-pattern>/managerController/toManagerList.do</url-pattern>
  </filter-mapping>
```

