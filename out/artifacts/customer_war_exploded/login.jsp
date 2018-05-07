<%--
  Created by IntelliJ IDEA.
  User: 曾鑫
  Date: 2018/3/20
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录界面</title>
    <style type="text/css">
      *{
        padding: 0;
        margin: 0;
      }
      .top{
        background-image: url("image/1.jpg");
        height: 520px;
        position: relative;
      }
      .bg-box {
        background-repeat: no-repeat;
        background-position: top center;
        position: relative;
      }
      .top .login{
        position: absolute;
        top: 100px;
        left: 1000px;
        height: 314px;
        width: 328px;
        background: white;
      }
      .top .login p{
        margin-top: 30px;
        font-size: 20px;
        color: #63b4e9;
      }
      .top .login #username{
        margin-top: 20px;
        margin-left: 15px;
        width: 286px;
        height: 38px;
        font-size: 14px;
        outline: none;
        border-radius: 8px;
        border:2px solid #B1D2EC;
        text-align: center;
      }
      .top .login #password{
        margin-top: 20px;
        margin-left: 15px;
        width:286px;
        height: 38px;
        outline: none;
        border-radius: 8px;
        border:2px solid #B1D2EC;
        text-align: center;
      }
      .top .login #yzm{
        margin-left: 15px;
        margin-top: 20px;
        width: 150px;
        height: 38px;
        outline: none;
        border-radius: 8px;
        border:2px solid #B1D2EC;
        text-align: center;
      }
      .top .login #btn{
        margin-top: 20px;
        margin-left: 15px;
        color: #fff;
        border:1px solid #FF9052;
        background:#FF9052;
        font-size: 14px;
        border-radius: 8px;
        width: 288px;
        height: 40px;
        cursor: pointer;
      }
      .top .right_image{
        float: right;
      }
      #fk{
        position: relative;
      }
      #fk img{
        width:100%;
        height:auto;
        position: absolute;
        top:0px;
        left:0px;
        display: none;
      }
      #fk ul{
        position: absolute;
        top: 370px;
        left: 630px;
        bottom: 18px;
      }
      #fk ul li{
        list-style: none;
        width:6px;
        height:6px;
        background: white;
        float: left;
        margin-right: 8px;
        border-radius: 50%;
      }
    </style>
    <script src = "jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(function () {
            var c = 0;
            function run() {
                c++;
                if(c==3){
                    c=0;
                }
                //让c号图片显示
                $("#fk img").eq(c).fadeIn(2000).siblings('img').fadeOut(2000);
                //当c号li变红
                $("#fk ul li").eq(c).css({'background':'red'}).siblings('li').css({'background':'white'});
            }
            var timer= setInterval(run,2000)

            //li鼠标移入事件 mouseenter
            $("#fk ul li").mouseenter(function () {
                clearInterval(timer);
                var fk = $(this);
                tt = setInterval(function () {
                    //清除定时器
                    clearInterval(timer);
                    //获取li元素序号。
                    var c = fk.index();
                    //让c号图片显示
                    $("#fk img").eq(c).stop().fadeIn(2000).siblings('img').stop().fadeOut(2000);
                    //当c号li变红
                    $("#fk ul li").eq(c).css({'background':'red'}).siblings('li').css({'background':'white'});
                },200)
            })

            //鼠标移出事件
            $("#fk ul li").mouseleave(function () {
                clearInterval(tt);
                //恢复定时器
                timer =  setInterval(run,2000);
            })

            if("${msg}"!=null && "${msg}"!=""){
                alert("${msg}");
            }

        });

        function login() {
            var code = $("#username").val();
            var pwd = $("#password").val();
            if(code==""||pwd=="") {
                alert("请填写完整用户名和密码");
                return false;
            }
            return true;
        }

        //刷新登录界面的验证码
        function refresh(){
            document.getElementById("codes").src= '${pageContext.request.contextPath }/enimg.jsp?'+Math.random();
        }
    </script>
  </head>
  <body>
    <div class="top bg-box">
        <div class="login">
          <p align = center>填写注册信息</p>
          <form action="${pageContext.request.contextPath}/usersservlet?action=login" method="post" name = "form1" onsubmit="return login();">
            <input type="text" id="username" name="username" placeholder = "  用户名">
            <input type="password" id = "password" name = "password" placeholder = "  密码">
            <input type="text" id = "yzm" name = "yzm" placeholder = "  验证码">
            <img src="${pageContext.request.contextPath }/enimg.jsp" width="72" height="35" style="margin-left: 25px;margin-top: 20px" id="codes" align="top" alt="点击换一张"  onclick="refresh();">
            <input type="submit" value = "免费体验" id = "btn">
          </form>
          <div class="right_image">
            <img src="image/5.png">
          </div>
        </div>
    </div>
    <div id = "fk">
      <img src = "image/2.jpg" style="display: block">
      <img src = "image/3.jpg">
      <img src = "image/4.jpg">
      <ul>
        <li style="background:red"></li>
        <li></li>
        <li></li>
      </ul>
    </div>
  </body>
</html>
