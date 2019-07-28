<%--
  Created by IntelliJ IDEA.
  User: CD
  Date: 2019/7/28
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/managerController/addUser.do">
    姓名：<input name="name" type="text"><br>
    密码：<input name="password"  type="password"><br>
    性别：<select name="gender">
                <option>男</option>
                <option>女</option>
          </select><br>
    年龄：<input name="age" type="text"><br>
    <input type="submit" value="提交"> <br>
</form>
</body>
</html>
