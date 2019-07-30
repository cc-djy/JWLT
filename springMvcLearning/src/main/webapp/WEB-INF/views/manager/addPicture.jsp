<%--
  Created by IntelliJ IDEA.
  User: CD
  Date: 2019/7/29
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>图片上传</title>
</head>
<body>
<h2>图片上传</h2>
<form action="${pageContext.request.contextPath}/managerController/addManagerPicture.do" method="post" enctype="multipart/form-data">
    图片:<input type="file"  name="file"/><br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>