$(function(){
    init();
    //大写提示
    capitalTip('password');
    //登录
    $('#login').on('click',login);
    //注册
    $('#reg').on('click',reg);
});

//注册
function reg(){
    $('.bubble p').text("注册");
    $('input').parent().removeClass('has-error');
    var reg_username = $.trim($("#reg_username").val());
    var reg_password = $("#reg_password").val();
    var code = $("#code").val();
    if(reg_username == ""){
        $('#reg_username').focus().val("").parent().addClass('has-error');
        return;
    }else if(reg_password == ""){
        $('#reg_password').focus().parent().addClass('has-error');
        return;
    }else if(code == ""){
        $('#code').focus().parent().addClass('has-error');
        return;
    }
    $.ajax({
        type:"POST",
        dataType: "json",
        url: basePath + '/user/addUser',
        data:{
            username: reg_username,
            password: reg_password
        },
        success: function (data) {
        	if(data.status == "200"){
        		$('#reg').button('loading');
                $('.bubble p').text(data.message);
        		setTimeout(function() {
        			location.href = 'index.jsp';
        			$('#reg').button('reset');
    			}, 3000);
        	}else{
                $('#reg_username').focus().parent().addClass('has-error');
                $('.bubble p').text(data.message);
        	}
        },
        error: function (){
    		alert("服务器异常！");
        }
    });
}

//登录
function login(){
    $('.bubble p').text("登录");
    $('input').parent().removeClass('has-error');
    var mail = $.trim($("#mail").val());
    var password = $("#password").val();
    if(mail == ""){
        $('#mail').focus().val("").parent().addClass('has-error');
        return;
    }else if(password == ""){
        $('#password').focus().parent().addClass('has-error');
        return;
    }
    rememberNameAndPwd();
    $.ajax({
        type:"POST",
        dataType: "json",
        url: basePath + '/user/login',
        data:{
            username: mail,
            password: password
        },
        success: function (data) {
        	if(data.status == "200"){
        		$('#login').button('loading');
        		setTimeout(function() {
        			location.href = 'index.jsp';
        			$('#login').button('reset');
    			}, 3000);
        	}else{
                $('#mail,#password').parent().addClass('has-error');
                $('.bubble p').text(data.message);
        	}
        },
        error: function (){
    		alert("服务器异常！");
        }
    });
}

//下次自动登录
function rememberNameAndPwd() {
    if ($("#remember").prop("checked")) {
        var mail = $("#mail").val();
        var password = $("#password").val(); 
        var expiresDate= new Date();
        //设置cookie过期时间：30分钟
        expiresDate.setTime(expiresDate.getTime() + (30 * 60 * 1000));
        $.cookie("mail", mail, { expires: expiresDate });
        $.cookie("password", password, { expires: expiresDate }); 
    }
};

//大写提示
function capitalTip(id){
    $('#' + id).after('<div class="capslock" id="capital_password"><span>大写锁定已开启</span></div>');
    var capital = false; //聚焦初始化，防止刚聚焦时点击Caps按键提示信息显隐错误
    // 获取大写提示的标签，并提供大写提示显示隐藏的调用接口
    var capitalTip = {
        $elem: $('#capital_'+id),
        toggle: function (s) {
            if(s === 'none'){
                this.$elem.hide();
            }else if(s === 'block'){
                this.$elem.show();
            }else if(this.$elem.is(':hidden')){
                this.$elem.show();
            }else{
                this.$elem.hide();
            }
        }
    }
    $('#' + id).on('keydown.caps',function(e){
        if (e.keyCode === 20 && capital) { // 点击Caps大写提示显隐切换
            capitalTip.toggle();
        }
    }).on('focus.caps',function(){capital = false}).on('keypress.caps',function(e){capsLock(e)}).on('blur.caps',function(e){
        //输入框失去焦点，提示隐藏
        capitalTip.toggle('none');
    });
    function capsLock(e){
        var keyCode = e.keyCode || e.which;// 按键的keyCode
        var isShift = e.shiftKey || keyCode === 16 || false;// shift键是否按住
        if(keyCode === 9){
            capitalTip.toggle('none');
        }else{
            //指定位置的字符的 Unicode 编码 , 通过与shift键对于的keycode，就可以判断capslock是否开启了
            // 90 Caps Lock 打开，且没有按住shift键
            if (((keyCode >= 65 && keyCode <= 90) && !isShift) || ((keyCode >= 97 && keyCode <= 122) && isShift)) {
                // 122 Caps Lock打开，且按住shift键
                capitalTip.toggle('block'); // 大写开启时弹出提示框
                capital = true;
            } else {
                capitalTip.toggle('none');
                capital = true;
            }
        }
    }
};

//图片轮播
function carousel(){
    var index=0;
    var $_picn=$(".item").length;
    $(".item").eq(0).show().siblings(".item").hide();
    setInterval(function(){
        show(index);
        index++;
        if(index==$_picn){index=0;}
    },3000);
    function show(index){
        $(".item").eq(index).fadeIn(1000).siblings(".item").fadeOut(1000);
    }
}

function init(){
    carousel();
    if($.cookie("mail") != "" && $.cookie("password") != ""){
        $('#mail').val($.cookie("mail"));
        $('#password').val($.cookie("password"));
    }
    //输入时去除错误提示
    $('input').bind('input propertychange', function() { 
        $(this).parent().removeClass('has-error');
    });
    //初始化提示
    $('[data-toggle="tooltip"]').tooltip();
    //登录、注册模块切换
    $('#apply_account').click(function(){
        $('#login_view').hide();
        $('#reg_view').fadeIn(500);
        $('.bubble p').text('注册');
        $('#login_view input').val('');
        $('input').parent().removeClass('has-error');
    });
    $('#retrun_login').click(function(){
        $('#reg_view').hide();
        $('#login_view').fadeIn(500);
        $('.bubble p').text('登录');
        $('#reg_view input').val('');
        $('input').parent().removeClass('has-error');
    });
}