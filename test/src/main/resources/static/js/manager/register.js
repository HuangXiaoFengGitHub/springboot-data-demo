/**
 * 1，从后台获取区域，分类等信息，转发到前台，填充到控件
 * 2，将前台的信息获取并转发到后台并注册店铺
 * 3，验证表单输入
 */
//从函数开头
$(function() {
	var registerUrl = '/myo2o/shop/ownerregister';//后台方法,在controller层中实现，并写出对应的mappering
	$('#submit').click(function() { //点击提交的时候响应，获取提交的内容，表单提交的属性id是“submit”
		var localAuth = {};//变量，json对象
		var personInfo = {};//变量，一个json对象
		localAuth.userName = $('#userName').val();//前端规定的控件名字userName
		localAuth.password = $('#password').val();//前端规定的控件名字password
		personInfo.phone = $('#phone').val();
		personInfo.email = $('#email').val();
		personInfo.name = $('#name').val();
		localAuth.personInfo = personInfo;
		//获取提交的头像文件
		var thumbnail = $('#small-img')[0].files[0]; //这里是上传的文件
		console.log(thumbnail);
		var formData = new FormData(); //接受表单内容
		formData.append('thumbnail', thumbnail);//传输参数
		formData.append('localAuthStr', JSON.stringify(localAuth)); //传送参数
		var verifyCodeActual = $('#j_captcha').val(); //接收验证码，验证码的控件id是j_captcha
		if (!verifyCodeActual) {
			$.toast('请输入验证码！');
			return;
		}
		formData.append("verifyCodeActual", verifyCodeActual);
		//用Ajax将表单提交到后台
		$.ajax({
			url : registerUrl, //
			type : 'POST', //用post方法提交
			data : formData, //传送formData
			contentType : false,//文件，名字
			processData : false,
			cache : false,
			success : function(data) { //接受到后台返回的data信息，这里要实现定义一个后台
				if (data.success) {
					$.toast('提交成功！');
					window.location.href = '/myo2o/shop/ownerlogin';
				} else {
					$.toast('提交失败！');
					$('#captcha_img').click();
				}
			}
		});
	});

	$('#back').click(function() {
		window.location.href = '/myo2o/shop/ownerlogin';
	});
});
