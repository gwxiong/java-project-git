<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="plugins/zui/zui.min.css">
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css"> <!-- 图标库 -->
    <script type="text/javascript" src="plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="plugins/zui/zui.min.js"></script>
    <script type="text/javascript" src="plugins/jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/login.js"></script> <!-- 自定义脚本 -->
    <style>
        body{
            background-color: rgb(50, 128, 252);
        }
        .content{
            position: absolute;
            margin-left: -500px;
            margin-top: -200px;
            left:50%;
            top:50%;
        }
        a:hover{
            cursor: pointer;
        }
        i:hover{
            cursor: pointer;
        }
        .login{
            width:300px;
            float: left;
            margin-top: 20px;
            margin-left: 100px;
        }
        .login_title{
            margin-bottom: 10px;
        }
        .login_title span{
            font-size: 16px;
            font-style:oblique;
            color: #fff;
        }
        .login_content{
            padding:20px;
            background-color: #fff;
            border-radius: 3%;
        }
        .capslock{
            position:absolute;
            margin: 0 0 0 60px;
            display:none;
            color:#124fed;
        }
        .exhibition{
            border:2px skyblue solid; 
            width:700px;
            height:400px;
            background-color: #fff;
            padding:5px;
            border-radius: 1%;
            float: left;
        }
        .item{
            position:absolute;
        }
        .exhibition img{
            width:685px;
            height:385px;
            border-radius: 1%; 
        }
        
        /*********感叹号提示**********/
        .icon_info{
            height:32px;
            padding: 0;
            width:15px;
        }
        .icon_info i{
            line-height: 32px;
            color: gold;
        }
        .icon_info i:hover{
            color: moccasin;
        }
        
        /*********气泡**********/
        .bubble { line-height: 100px; background-color: #fff;  position: relative; left:200px;  top:-60px;   margin-left: -150px;    width: 200px;    height: 100px;     border-radius: 50%;  }    
        .bubble:before {  background-color: #fff;  content: "";  position: absolute;    top:100px;   width: 30px;    height: 30px;     border-radius: 50%;  }    
        .bubble:after {   background-color: #fff; content: "";   position: absolute;    top:130px;  left:-20px;    width: 20px;    height: 20px;    border-radius: 50%;    }
        .bubble p{
            text-align: center;
            font-weight: bold;
            font-size: 23px;
            color: rgb(50, 128, 252);
            font-style:oblique;
            letter-spacing: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
    	<div class="content">
	        <div class="exhibition">
	            <div class="item">
	                <img src="images/exhibition-1.jpg" alt="">
	            </div>
	            <div class="item">
	                <img src="images/exhibition-2.jpg" alt="">
	            </div>
	            <div class="item">
	                <img src="images/exhibition-3.jpg" alt="">
	            </div>
	            <div class="item">
	                <img src="images/exhibition-4.jpg" alt="">
	            </div>
	        </div>
	        <div class="login">
	            <div class="bubble">
	                <p>登录</p>
	            </div>
	            <div class="login_title">
	                <img src="images/icon/icon_message.png" alt="">
	                <span>：</span>
	                <span class="message">生活如此无聊，我想变成丧尸！</span>
	            </div>
	            <div class="col-md-12 login_content" id="login_view">
	                <form class="form-horizontal">
	                    <div class="form-group">
	                        <div class="col-sm-12">
	                            <input type="text" class="form-control" id="mail" placeholder="用户名">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="col-sm-12">
	                            <input type="password" class="form-control" id="password" placeholder="密码">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="col-sm-12">
	                            <div class="checkbox">
	                                <label>
	                                    <input id="remember" type="checkbox"> 下次自动登录
	                                </label>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="col-sm-12">
	                            <button id="login" type="button" class="btn btn-primary btn-block" data-loading-text="正在登录...">登录</button>
	                        </div>
	                    </div>
	                </form>
	                <a id="apply_account" style="margin-left: 180px; color: rgb(50, 128, 252);">没有帐号？</a>
	            </div>
	            <div class="col-md-12 login_content" style="display:none;" id="reg_view">
	                <form class="form-horizontal">  
	                    <div class="form-group">
	                        <div class="col-sm-11">
	                            <input type="text" class="form-control" id="reg_username" placeholder="帐号">
	                        </div>
	                        <div class="col-sm-1 icon_info" data-toggle="tooltip" data-placement="bottom" title="由3-18个英文、数字、下划线组成">
	                            <i class="icon-exclamation-sign"></i>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="col-sm-11">
	                            <input type="password" class="form-control" id="reg_password" placeholder="密码">
	                        </div>
	                        <div class="col-sm-1 icon_info" data-toggle="tooltip" data-placement="bottom" title="长度为6-16个字符、不能包含空格">
	                            <i class="icon-exclamation-sign"></i>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="col-sm-4">
	                            <input type="text" class="form-control" id="code" placeholder="验证码">
	                        </div>
	                        <div class="col-sm-4">
	                            <button type="button" class="btn btn-primary ">获取</button>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="col-sm-12">
	                            <button id="reg" type="button" class="btn btn-primary btn-block" data-loading-text="正在登录...">注册</button>
	                        </div>
	                    </div>
	                </form>
	                <a id="retrun_login" style="margin-left: 180px; color: rgb(50, 128, 252);">返回登录</a>
	            </div>
	        </div>
        </div>
    </div>
    <script>
    	var basePath = '<%=basePath%>';
    </script>
</body>
</html>