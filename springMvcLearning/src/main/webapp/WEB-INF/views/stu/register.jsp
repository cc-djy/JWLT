<%--
  Created by IntelliJ IDEA.
  User: CD
  Date: 2019/7/25
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
        function register() {
            var name = $('#name').val();
            var password = $('#password').val();
            var jsonObj={"name":name,"password":password};
            //把json对象转字符串
            var parameters = JSON.stringify(jsonObj);
            console.log(jsonObj);
            console.log(parameters);
            var url='${pageContext.request.contextPath}/stuController/register.do';
            $.ajax({
                url:url,
                data:jsonObj,
                contentType:'application/json;charset=utf-8',
                success:function (respData) {
                    alert("success"+respData.name);
                }
            })
        }
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/stuController/register.do">
    学生姓名:<input name="name" type="text" id="name"><br>
    学生密码:<input name="password" type="password" id="password"><br>
    <input type="button" value="提交Json数据格式" onclick="register()">
    <input type="submit" value="save"><br>
</form>
</body>
</html>
