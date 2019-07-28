<%--
  Created by IntelliJ IDEA.
  User: CD
  Date: 2019/7/28
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/managerController/editManager.do">
    学号：<input name="mid" type="text" value="${manager.mid}" readonly="readonly"><br>
    姓名：<input name="name" type="text" value="${manager.name}"><br>
    密码：<input name="password"  type="text" value="${manager.password}"><br>
    性别：<input name="gender"  type="text" value="${manager.gender}"><br>
    年龄：<input name="age" type="text" value="${manager.age}"><br>
    <input type="submit" value="提交"> <br>
</form>
</body>
</html>
