<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/2
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-3.4.1.js"></script>
    <link rel="stylesheet" href="/dist/css/bootstrap.css">
</head>
<body>
<shiro:hasRole name="董事长">
    <a href="/select" class="btn btn-primary btn-lg active" role="button">用户管理</a>
</shiro:hasRole>
<shiro:hasRole name="管理员">
    <a href="/selectAllRole" class="btn btn-primary btn-lg active" role="button">角色管理</a>
</shiro:hasRole>
<shiro:hasRole name="普通员工">
    <a href="/toPower" class="btn btn-primary btn-lg active" role="button">权限管理</a>
</shiro:hasRole>


</body>
</html>
