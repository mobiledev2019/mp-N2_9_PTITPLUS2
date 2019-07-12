<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng Nhập</title>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body id="body-login">
	<div id ="body-flex-login">
		<div id = "container-login">
			<div id="container-login-left"> 
				<div id= "header-left" class="header-login">
					<span id = "text-logo">Welcome</span><br/>
					<span id = "hint-text-logo">Hãy tạo nên phong cách của bạn với Minishop !</span>
				</div>
				<div id="header-bottom-left">
					<p>  O <span>Luôn cập nhật xu hướng thời trang mới nhất</span></p>
					<p>  O <span>Giảm 50% tất các sản phẩm cho khách hàng thân thiết</span></p>
					<p>  O <span>Tư vấn tận tình cho mọi người</span></p>
				</div>
			</div>
			<div id="container-login-right">
				<div id="header-right" class="header-login">
					<span class="actived" id="login">Đăng nhập </span>/<span id= "sigup"> Đăng ký</span>
				</div>
				
				<div id="container-center-login-right">
					<div id="container_dangnhap">
						<input id = "username" name = "username"  class="meterial-text-input input-ic-email" type="text" placeholder = "Email"/><br>
						<input id="password" name="password" class="meterial-text-input input-ic-pass" type="text" placeholder = "Password"/><br>
						<input id = "btn_dangnhap" class="meterial-button-primary" type="submit" value="Đăng Nhập" />
						
					</div>
					<div id="container_sigup">
						<form action="" method="post">
							<input id = "username" name = "username"  class="meterial-text-input input-ic-email" type="text" placeholder = "Email"/><br>
							<input id="password"name="password"  class="meterial-text-input input-ic-pass" type="text" placeholder = "Password"/><br>
							<input id="password-again"name="password_again" class="meterial-text-input input-ic-pass" type="text" placeholder = "Password"/><br>
							<input id = "btn_dangnhap" class="meterial-button-primary" type="submit" value="Sing up" />
						</form>
					</div>
					<span id="ketqua">${ketqua}</span>
				</div>
				<div id="container-social-login">
					<img style="height: 30px" alt="" src='<c:url value="/resources/image/ic_facebook.png" />'/>
					<img style="height: 30px" alt="" src='<c:url value="/resources/image/ic_google.png" />'/>
				</div>
			
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>