<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/1
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-3.4.1.js"></script>
    <link rel="stylesheet" href="/dist/css/bootstrap.css">
    <script src="/js/layer/layer.js"></script>
</head>
<body>

<form action="/update"  id="form" method="post">
    <input type="hidden" name="id" value="${id}">
<c:forEach  items="${role}" var="li">
    <input type="checkbox" name="roleId"  value="${li.roleId}" <c:forEach items="${myRole}" var="myRole"><c:if test="${myRole.roleName==li.roleName}">checked</c:if></c:forEach>/>${li.roleName}<br>
</c:forEach>

</form>


<script type="text/javascript">
   function submitForm() {
       console.log("提交表单");
       $("#form").submit();
   }

</script>


</body>


</html>

