<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="com.tvtien.entity.NhanVien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trang chu</title>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<div id="header_chitiet" class="container-fluid">
		<nav class="navbar navbar-expand-lg navbar-light  none_nav" style="backgroup-color:#FF0000">
		  <a class="navbar-brand" href="#"><img src='<c:url value="/resources/image/logoshop.png" />'/></a>
		  
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto navbar-centert">
		      <li class="nav-item active">
		        <a class="nav-link" href="#">TRANG CHỦ <span class="sr-only">(current)</span></a>
		      </li>
		       <li class="nav-item dropdown ">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          SẢN PHẨM
		        </a>
		        <div class="dropdown-menu open" aria-labelledby="navbarDropdown">
		          <a class="dropdown-item" href="#">Action</a>
		          <c:forEach var ="danhmuc" items="${listdanhmuc }">
					<a class="dropdown-item" href ="#">${danhmuc.getTenDanhMuc() }</a>
				</c:forEach>
		        </div>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">DỊCH VỤ</a>
		      </li>
		       <li class="nav-item">
		        <a class="nav-link" href="#">LIÊN HỆ</a>
		      </li>
		     
		    </ul>
		    <ul class ="nav navbar-nav navbar-ringht">
		    	<c:choose>
		    		<c:when test="${chudau != null }">
		    			<a class="circle_avatar" href="dangnhap/">${chudau}</a>
		    		</c:when>
		    		
		    		<c:otherwise>
		    			 <a class="nav-link" href="../dangnhap/">ĐĂNG NHẬP</a>
		    		</c:otherwise>
		    	</c:choose>
		    	
		    	<li><a  href="../giohang/"><img id="giohang" src='<c:url value="/resources/image/iconscart.png" />'/></a><span class="ic_giohang">${soluong}</span></li>
		    </ul>
		  </div>
		</nav>
		
	</div>
	
	<div class =container style = "padding-top:10px">
		<span><h3>${tendanhmuc}</h3></span>
		<div class=row>
			<c:forEach var="sanpham" items="${listsanpham}">
				<div class="card col-md-3 col-sm-6 sanpham " style="width: 18rem;">
					 <a href="/minishop/chitiet/${sanpham.getIdSanPham()}"><img id="image-sanpham" class="card-img-top"src='<c:url value="/resources/image/42.jpg" />' alt="Card image cap"></a>
					  <div class="card-body">
					    <h4 class="card-title">${sanpham.getTenSanPham()}</h4>
					    <p id = "price-sanpham">${sanpham.getGiatien()} đ</p>
					  </div>
			</div>
			</c:forEach>
		</div>
	</div>

	<div id="footer" class="container-fluid wow bounceInUp">
		<div class="row">
			<div class="col-md-4">
				<p><span id = "title-footer">THÔNG TIN SHOP</span></p>
				<span>là một thương hiệu thời trang nổi tiếng trên toàn quốc đi đầu trong nganh dịch vụ may mặc</span>
			</div>
			
			<div class="col-md-4">
				<p><span id = "title-footer">LIÊN HỆ</span></p>
				<span> 18b trần phú hà đông hà nội</span><br/>
				<span> tvtien.ptit@gmail.com</span><br/>
				<span> 0338451202</span><br/>
			</div>
			
			<div class="col-md-4">
				<p><span id = "title-footer">GÓP Ý</span></p>
				<form action="" method="post">
					<input name = "tenNhanVien" class="meterial-text-input" type="text" placeholder = "Email">
					<textarea name = "tuoiNhanVien" id= "footer-textarea" rows="4" cols="50" placeholder = "Nội dung "></textarea>
					<button class="meterial-button-primary">Gửi</button>
				</form>
			</div>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>