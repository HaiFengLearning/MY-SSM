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
    <script src="/dist/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/dist/css/bootstrap.css">
    <script src="/js/layer/layer.js"></script>
    <script src="/js/jquery-ztree/3.5/js/jquery.ztree.all-3.5.js"></script>
    <link rel="stylesheet" href="/js/jquery-ztree/3.5/css/default/zTreeStyle.css">
</head>
<body>
<table class="table table-bordered" style="margin: 10px">
    <tr>
        <th>ID</th>
        <th>角色</th>
        <th>状态</th>
        <th >操作</th>
    </tr>


    <c:forEach items="${pageInfo.list}" var="role">
        <tr>
            <td>${role.roleId}</td>
            <td>${role.roleName}</td>
            <td>${role.status}</td>
            <td>
                <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick="fn1(${role.roleId})">
                   修改
                </button>
                <button id="delRole" type="button" class="btn btn-default btn-lg"><a href="/delRoleStatus?roleId=${role.roleId}" >删除</a></button>
                <button id="useRole" type="button" class="btn btn-default btn-lg"><a href="/updateRoleStatus?roleId=${role.roleId}">启用</a></button>
            </td>
        </tr>
    </c:forEach>
</table>


<!-- Modal 修改-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改角色权限</h4>
            </div>
            <div class="modal-body">
                <div>
                    <ul id="tree" class="ztree"></ul>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="save" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"  onclick="fn2()">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal 增加-->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel1">增加角色</h4>
            </div>
            <div class="modal-body">

                <input id="role" type="text" class="form-control" placeholder="请输入角色" aria-describedby="sizing-addon1" >
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="save1" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="fn3()">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="pagination-layout">
    <div class="pagination">
        <ul class="pagination" total-items="pageInfo.totalRows" max-size="5" boundary-links="true">

        </ul>
    </div>
</div>

<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal1" >
    增加
</button>
<a href="/login/toIndex" class="btn btn-primary btn-lg active" role="button">返回</a>


<script type="text/javascript">


    //增加角色
    function fn3() {
            var roleName = $("#role").val();
            console.log(roleName);
            var role = {};
            role.roleName = roleName ;
            $.ajax({
                type : "POST",
                url : "/insertRole",
                dataType:"json",
                contentType: "application/json;charset=UTF-8",
                //data:JSON.stringify(roleName)
                data:JSON.stringify(role),
                success:function (data) {
                    //重新加载页面
                   // location.reload()
                    init();

                }
            })


    }


 //修改保存
 function fn2() {

         //获得ztree对象
         var treeObj = $.fn.zTree.getZTreeObj("tree");
         //获得当前ztree对象选中的节点数组
         var nodes = treeObj.getCheckedNodes(true);//在提交表单之前将选中的checkbox收集
         //循环数组，获得节点的ID，拼接成字符串使用逗号分隔
         var array = new Array();
         for(var i=0;i<nodes.length;i++){
             array.push(nodes[i].powerId);
         }
         var ids = array.join(",");
         console.log(ids);

         console.log(currId);
         var power = {
             id:currId,
             ids:ids

         }

         //var formData = new FormData($("#formproject")[0]);

         $.ajax({
             type : "POST",
             url : "/updatePower",
             dataType:"json",
             contentType: "application/json;charset=UTF-8",
             data:JSON.stringify(power)
         });

 };


var currId;
//回显
    function fn1(id){

       currId = id;
        $.ajax({
            async : false,
            cache:false,
            type: 'POST',
            dataType : "json",
            url: "/updateRole?roleId="+id ,//请求的action路径
            error: function () {//请求失败处理函数
                alert('请求失败');
            },
            success:function(data){ //请求成功后处理函数。
                console.log(data);
               // treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodese/
                var setting = {
                    view: {
                        dblClickExpand: false,
                        showLine: true,
                        selectedMulti: false
                    },
                    check:{
                        enable:true,
                    },
                    data: {
                        simpleData: {
                            enable: true,//是否使用简单数据类型
                            idKey: "powerId",//设置主键
                            pIdKey: "parentId",//设置父键
                            rootPId: "0"
                        },
                        key:{
                            name:"powerName"
                        }
                    },
                    callback:{//表示tree的一些事件处理函数

                        onClick:function(event, treeId, data) {
                            alert(data.powerName);
                        }
                    }
                };
                console.log(data);

                //核心api使用
                var  zTreeObj = $.fn.zTree.init($("#tree"), setting, data);

                //通过ztree对象的方法，展开所有节点
                zTreeObj.expandAll(true);
            }


        });


    }









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

        location.href = "/selectAllRole?pageNum=" + num;

    }
</script>
</body>
</html>
