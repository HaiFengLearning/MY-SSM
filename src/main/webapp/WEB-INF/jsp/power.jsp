<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/5
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/dist/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/dist/css/bootstrap.css">
    <script src="/dist/js/bootstrap-select.js"></script>
    <link rel="stylesheet" href="/dist/css/bootstrap-select.css">
</head>
<body>
<div class="container">
    <div style="height: 500px;">
        <div class="col-lg-4" id="one">
            <div class="text-center"><kbd>一级权限</kbd></div>







        </div>
        <div class="col-lg-4 col-lg-push-0" id="two">
            <div class="text-center"><kbd>二级权限</kbd></div>



        </div>



        <div class="col-lg-4 col-lg-push-0" id="three">
            <div class="text-center"><kbd>三级级权限</kbd></div>



        </div>
    </div>


    <!-- Modal 修改-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改权限</h4>
                </div>
                <div class="modal-body">
                    <input id="powerId" type="hidden" >
                    <input id="powerName" type="text" class="form-control" placeholder="请输入权限名称" aria-describedby="sizing-addon1" >
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="save1" type="button" class="btn btn-primary" data-dismiss="modal" onclick="fn2()">保存</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal 新增-->
    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel1">新增权限</h4>
                </div>
                <div class="modal-body">


                    <select class="selectpicker" title="请选择上级菜单">

                    </select>



                    <input  type="hidden" >
                    <hr>
                    <input id="insertPowerName" type="text" class="form-control" placeholder="请输入权限名称" aria-describedby="sizing-addon1" >
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button  type="button" class="btn btn-primary" data-dismiss="modal" onclick="insert()">保存</button>
                </div>
            </div>
        </div>
    </div>



    <button id="insert"  type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal1" onclick="insertInit()">
        增加
    </button>

    <a href="/login/toIndex" class="btn btn-primary btn-lg active" role="button">返回</a>
</div>
<script type="application/javascript">


    //新增
    function insertInit() {

        $.ajax({
            url:"insertPowerInit",
            success:function (data) {
                //拼接下拉框
                for(var i=0;i<data.length;i++){
                    $(".selectpicker").append("<option value='"+data[i].powerId+"'>"+data[i].powerName+"</option>");
                }
                //这一步不要忘记 不然下拉框没有数据
                $(".selectpicker").selectpicker("refresh");
            }
        })
    }
    function insert() {
        var parentId = $(".selectpicker").val();
        var powerName = $("#insertPowerName").val();
        var power ={
            powerName:powerName,
            parentId:parentId
        }
        console.log(power)
        $.ajax({
            url:"insertPower",
            data:power,
            success:function (data) {
                //重新加载页面
                //location.reload()
            }
        })
    }

    //删除
    function del(powerId) {
        console.log(powerId);
        $.ajax({
            url:"delPower?powerId="+powerId,
            success:function () {
                //重新加载页面
                location.reload()
            }
        })
    }




//修改回显
    function fn1(powerId) {
       // console.log("修改"+powerId );
        $.ajax({
            url:"/updatePowerInit?powerId="+powerId,
            success:function (data) {
                //console.log(data.powerName);
                $("#powerName").val(data.powerName);
                $("#powerId").val(powerId);
            }
        })
    }
    //修改保存
    function fn2() {
        var powerId = $("#powerId").val();
        var powerName = $("#powerName").val();
        console.log("修改"+powerId );
        var power ={
            powerId:powerId ,
            powerName:powerName
        }
        $.ajax({
            url:"/updatePowerName",
            data:power,
            success:function (data) {
                    //重新加载页面
                    location.reload()
            }
        })
    }

//三级菜单 第一级菜单
    $(function () {
        $.ajax({
            url:"/selectPowerOne?parentId=0",
            success:function (data) {
                //console.log(data);
                for (var i in data){
                   // console.log(data[i].powerName);
                  //  console.log(data[i].powerId);
                     var html =' <a class="list-group-item text-center  one" onmouseover="showbtn(this)" onmouseout="hidebtn(this)" onclick="showMsg(this);two('+data[i].powerId+')" >'
                          +  data[i].powerName  +'<div style="float: right;" hidden="hidden" >'
                          +'  <button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#myModal" onclick="fn1('+ data[i].powerId +' )" >'
                          +'  <span class="glyphicon glyphicon-pencil"></span> 修改'
                          +'</button>'
                          +' <button type="button" class="btn btn-danger btn-xs" onclick="del('+ data[i].powerId +' )">'
                          +' <span class="	glyphicon glyphicon-remove"></span> 删除'
                          +' </button>'
                          +' </div>'
                          +'</a>'
                    $("#one").append(html);
                }

            }
        })
    });

// 第二级菜单
    function two(id) {
        $(".two").remove();
        $(".three").remove();
       // console.log("id="+id);
        $.ajax({
            url:"/selectPowerOne?parentId="+id,
            success:function (data) {
            console.log(data);
            for (var i in data){
//                console.log(data[i].powerName);
//                console.log(data[i].powerId);
                var html =' <a class="list-group-item text-center  two" onmouseover="showbtn(this)" onmouseout="hidebtn(this)" onclick="showMsg(this);three('+ data[i].powerId +')" >'
                    +  data[i].powerName  +'<div style="float: right;" hidden="hidden" >'
                    +'  <button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#myModal" onclick="fn1('+ data[i].powerId +' )" >'
                    +'  <span class="glyphicon glyphicon-pencil"></span> 修改'
                    +'</button>'
                    +' <button type="button" class="btn btn-danger btn-xs" onclick="del('+ data[i].powerId +' )">'
                    +' <span class="	glyphicon glyphicon-remove"></span> 删除'
                    +' </button>'
                    +' </div>'
                    +'</a>'
                $("#two").append(html);
            }

            }
        })
    }
    //第三级菜单
    function three(id) {
        $(".three").remove();
      //  console.log("id="+id);
        $.ajax({
            url:"/selectPowerOne?parentId="+id,
            success:function (data) {
                console.log(data);
                for (var i in data){
//                    console.log(data[i].powerName);
//                    console.log(data[i].powerId);
                    var html =' <a class="list-group-item text-center  three" onmouseover="showbtn(this)" onmouseout="hidebtn(this)" onclick="showMsg(this)" >'
                        +  data[i].powerName  +'<div style="float: right;" hidden="hidden" >'
                        +'  <button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#myModal" onclick="fn1('+ data[i].powerId +' )" >'
                        +'  <span class="glyphicon glyphicon-pencil"></span> 修改'
                        +'</button>'
                        +' <button type="button" class="btn btn-danger btn-xs" onclick="del('+ data[i].powerId +' )">'
                        +' <span class="	glyphicon glyphicon-remove"></span> 删除'
                        +' </button>'
                        +' </div>'
                        +'</a>'
                    $("#three").append(html);
                }

            }
        })
    }










    function showbtn(obj){
        $(obj).find("div").removeAttr("hidden")
    }

    function hidebtn(obj){
        $(obj).find("div").attr("hidden","hidden")
    }
    function showMsg(obj){
        $(obj).parent().find("a").removeClass("active")
        $(obj).addClass("active")
    }


</script>
</body>
</html>
