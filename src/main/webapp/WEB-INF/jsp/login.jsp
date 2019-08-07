<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script type="application/javascript" src="/js/jquery-3.4.1.js"></script>
    <script type="application/javascript" src="/dist/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/dist/css/bootstrap.css">
</head>
<body>
<div class="container" style="margin-top: 50px" >
    <form class="form-horizontal" action="" method="post">
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">用户名：</span>
            <input type="text" class="form-control" placeholder="用户名" aria-describedby="basic-addon1" name="userName">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon2">密  码：</span>
            <input type="password" class="form-control" placeholder="密码" aria-describedby="basic-addon2" name="password">
        </div>
        <br>
        <div class="rem-for-agile">
            <input type="checkbox" name="rememberMe" class="remember" value="true">记住我<br>

        </div>
        <br>
        <div class="input-group">
            <input type="button" value="登录" class="btn btn-primary" onclick="changeAction(1)">
            &nbsp;&nbsp;&nbsp;
            <input type="button" value="注册" class="btn btn-warning" onclick="changeAction(0)">
        </div>
    </form>
</div>
<script type="application/javascript">
    function changeAction(flag) {
        if (flag){
            $(".form-horizontal").attr("action","/login/check").submit();
        }else {
            $(".form-horizontal").attr("action","/login/register").submit();
        }
    }
</script>
</body>
</html>