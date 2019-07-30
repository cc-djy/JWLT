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
<form action="${pageContext.request.contextPath}/managerController/addUser.do" enctype="multipart/form-data">
    <table border="1">
        <tr>
            <td>学号</td>
            <td><input name="mid" type="text" readonly="readonly"></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input name="name" type="text"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input name="password"  type="text"></td>
        </tr>
        <tr>
            <td>性别</td>
            <td><input name="gender"  type="text"></td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input name="age" type="text"></td>
        </tr>
        <tr>
            <td>个人照</td>
            <td><input type="file" name="picture" onchange="submitImage()"></td>
        </tr>
        <tr><input type="submit" value="提交"></tr>
    </table>
</form>
</body>
</html>
