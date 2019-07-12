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
		    	
		    	<li><a  href="#"><img id="giohang" src='<c:url value="/resources/image/iconscart.png" />'/></a><span class="ic_giohang">${soluong}</span></li>
		    </ul>
		  </div>
		</nav>
		
	</div>
	
	<div class =container style = "padding-top:10px">
		<div class=row>
			<div class ="col-md-6 col-sm-12">
			<h3>Danh Sách Sản Phẩm Trong Giỏ Hàng</h3>
				<table class ="table">
					<thead>
						<tr>
							<td><h5>Tên Sản Phẩm</h5></td>
							<td><h5>Màu Sản Phẩm</h5></td>
							<td><h5>Size</h5></td>
							<td><h5>Số Lượng</h5></td>
							<td><h5>Giá Tiền</h5></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sanpham" items="${giohang}">
							<tr data-machitiet ="${sanpham.getMachitiet() }">
								<td class="tensp" data-masp = "${sanpham.getIdSanPham() }">${sanpham.getTenSanPham() }</td>
								<td class = "mau" data-mamau = "${sanpham.getIdMaMauSanPham() }" >${sanpham.getTenMauSanPham() }</td>
								<td class = "size" data-masize = "${sanpham.getIdSizeSanpham()}">${sanpham.getTenSizeSanPham() }</td>
								<td class = "soluong"><input min ="1" class="soluong-giohang" type="number" value="${sanpham.getSoluong() }"></td>
								<td class = "giatien"  data_value = "${sanpham.getGiatien() }">${sanpham.getGiatien() }</td>
								<td><button class ="btn btn-danger btn_xoa">Xóa</button></td>
							</tr>
					</c:forEach>
					</tbody>
				</table>
				<h4 >Tổng Tiền : <span id = "tongtien" style = "color:red"></span></h4>
			</div>
			<div class = "col-md-6 col-sm-12">
				<h3>Thông Tin Người Nhận \ Mua</h3>
				<div class="form-group">
					<form action="" method="post">
						<label for ="tennguoimua">Tên Người Mua / nhận</label>
						<input class ="form-control" id="tennguoimua" name = "tenKhacHang" type ="text">
						
						<label for ="sdthoai">Điện Thoại Liên Lạc</label>
						<input class ="form-control" id="sdthoai" name = "sodt" type ="text">
						
						<div class="radio">
						  <label class ="active"><input value ="giao hàng tận nơi"  type="radio" name="hinhthucgiaohang" checked="">giao hàng tận nơi</label>
						</div>
						<div class="radio">
						  <label><input value = "nhận taị cửa hàng" type="radio" name="hinhthucgiaohang">nhận taị cửa hàng</label>
						</div>
						
						<label for ="diachi">địa chỉ</label>
						<input class ="form-control" id="diachi" name = "diachigiaohang" type ="text">
						
						<label for ="tennguoimua">ghi chú</label>
						<textarea class ="form-control" rows="5" id="ghichu" name = "ghichu" ></textarea>
						<br>
					
						<input type="submit" class ="btn btn-primary" value ="Đặt Hàng">
					</form>
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