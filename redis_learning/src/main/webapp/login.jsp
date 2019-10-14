<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2019/10/10
  Time: 上午9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<base href="<%=basePath%>">
<head>
    <title>Title</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.login').click(function () {
                var username = $('.username').val();
                var password = $('.password').val();
                $.ajax({
                    type: "post",
                    url: "user/login.do",
                    data: {"username": "username", "password": "password", "valCode": "1222"},
                    dataType:"text",
                    async:false,
                    success: function (data) {
                        $("#msg").html("<p style='color: red'>"+data+"</p>")
                    },
                })
            })
        });
    </script>
</head>
<body>
<form action="">
    <label>账号：</label><input name="username" type="text">
    <label>密码：</label><input name="password" type="password">
    <div id="msg"></div>
    <input type="button" value="登录" class="login">
</form>
</body>

</html>
