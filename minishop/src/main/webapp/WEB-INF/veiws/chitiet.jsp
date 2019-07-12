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
		<div class=row>
			<div class ="col-sm-2 col-md-2">
				<h4 style ="padding:0">Danh Mục San Pham</h4>
				<ul class = "mymenu">
					<c:forEach var ="danhmuc" items="${listdanhmuc }">
						<li><a href ="#">${danhmuc.getTenDanhMuc() }</a></li>
					</c:forEach>
				</ul>
			</div>
			
			<div class ="col-sm-8 col-md-8">
				<div class = row>
					<div class ="col-sm-4 col-md-4">
						<img id="image-sanpham_chitet" class="card-img-top"src='<c:url value="/resources/image/42.jpg" />' alt="Card image cap">
					</div>
					<div class="col-sm-8 col-md-8">
						<h4 id = "tensanpham" data_idsanpham = "${sanpham.getIdSanPham()}">${sanpham.getTenSanPham()}</h4>
						<h4 id="giatien">${sanpham.getGiatien()}</h4>
						<table class = "table">
							<thead>
								<tr>
									<td>Mau San Pham</td>
									<td>Size</td>
									<td>So Luong</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="chitietsanpham" items="${sanpham.getChitiets()}">
									<tr>
										<td class = "mau" data_idmau = "${chitietsanpham.getMausanpham().getIdMauSanPham() }">${chitietsanpham.getMausanpham().getTenMauSanPham() }</td>
										<td class = "size" data_idsize = "${chitietsanpham.getSizesanpham().getIdSizeSanPham() }">${chitietsanpham.getSizesanpham().getTenSizeSanPham() }</td>
										<td class = "soluong">${chitietsanpham.getSoluong() }</td>
										<td><button class ="btn btn-success btn_themgiohang" data-chitiet = "${chitietsanpham.getIdChiTietSanPham() }">Them Gio Hang</button></td>
									</tr>
								</c:forEach>
										
							</tbody>
						</table>
					</div>
				</div>
				
			</div>
			<div class ="col-sm-2 col-md-2">
				<p>${sanpham.getMota()}</p>
				<p>Áo dài là một loại trang phục cách tân từ áo Ngũ thân của Việt Nam, mặc cùng với quần dài, che thân từ cổ đến  hiện nay thường được biết đến nhiều hơn với tư cách là trang phục nữ.[1] Áo dài thường được mặc vào các dịp lễ hội, trình diễn; hoặc tại những mô </p>
			</div>
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