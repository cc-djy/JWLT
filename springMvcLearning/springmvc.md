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