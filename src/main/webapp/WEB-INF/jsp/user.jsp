<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/29
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/js/jqPaginator.js"></script>
    <link rel="stylesheet" href="/dist/css/bootstrap.css">
    <script src="/js/layer/layer.js"></script>
</head>
<body>
<table class="table table-bordered" style="margin: 10px">
    <tr>
        <th>ID</th>
        <th>用户</th>

        <th >操作</th>
    </tr>


    <c:forEach items="${pageInfo.list}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.userName}</td>

            <td>
                <button id="addOption" type="button" class="btn btn-warning  update"  onclick="fn1(${user.id})">修改</button>
                <button type="button" class="btn btn-warning"><a href="/delUser?userName=${user.userName}">删除</a></button>
                <button type="button" class="btn btn-default"><a href="/updateStatus?userName=${user.userName}">用户状态：${user.status}</a></button>

            </td>
        </tr>
    </c:forEach>
</table>


<div class="pagination-layout">
    <div class="pagination">
        <ul class="pagination" total-items="pageInfo.totalRows" max-size="5" boundary-links="true">

        </ul>
    </div>
</div>

<a href="/login/toIndex" class="btn btn-primary btn-lg active" role="button">返回</a>


<script type="text/javascript">


    function fn1(id) {
        layer.open({
            type: 2,
            area: ['850px', '450px'],
            maxmin: true,
            title: "角色修改",
            content: ['/updateInit?id='+id, 'yes'],
            btn: ['保存', '取消'],
            yes: function (index, layero) { //或者使用btn1
                var ifname="layui-layer-iframe"+index;//获得layer层的名字
                window[ifname].submitForm();
                //js代码执行顺序不一定为书写顺序 所以没有提交就关闭了弹窗
                setTimeout(function () {
                    layer.close(index)
                },700)

            },
            btn1: function (index) {
                layer.close(index);
            }
        });


    };





//
    var if_firstime = true;

    window.onload = function () {








        $('.pagination').jqPaginator({
            totalPages: ${pageInfo.pages},
            visiblePages: 5,
            currentPage: ${pageInfo.pageNum},

            first: '<li class="first"><a href="javascript:void(0);">第一页</a></li>',
            prev: '<li class="prev"><a href="javascript:void(0);">上一页</a></li>',
            next: '<li class="next"><a href="javascript:void(0);">下一页</a></li>',
            last: '<li class="last"><a href="javascript:void(0);">最末页 </a></li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',

            onPageChange: function (num) {
                if (if_firstime) {
                    if_firstime = false;
                } else if (!if_firstime) {
                    changePage(num);
                }

            }
        });
    }



    function changePage(num) {

        location.href = "/select?pageNum=" + num;

    }
</script>
</body>
</html>
