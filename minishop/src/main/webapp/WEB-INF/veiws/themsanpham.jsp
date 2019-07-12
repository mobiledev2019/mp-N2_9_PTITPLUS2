<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Pooled Admin Panel Category Flat Bootstrap Responsive Web Template | Home :: w3layouts</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Pooled Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href='<c:url value="/resources/template/css/bootstrap.min.css" />'/>

<!-- Custom CSS -->
<link rel="stylesheet" href='<c:url value="/resources/template/css/style.css" />'/>
<link rel="stylesheet" href='<c:url value="/resources/template/css/morris.css" />'/>

<link rel="stylesheet" href='<c:url value="/resources/Styles/styles.css" />'/>

<!-- Graph CSS -->
<link rel="stylesheet" href='<c:url value="/resources/template/css/font-awesome.css" />'/>

<!-- jQuery -->
<script src='<c:url value="/resources/template/js/jquery-2.1.4.min.js" />' ></script>
<script src='<c:url value="/resources/template/js/morris.js" />' ></script>
<!-- //jQuery -->
<link href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet' type='text/css'/>
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<!-- lined-icons -->
<link rel="stylesheet" href='<c:url value="/resources/template/css/icon-font.min.css" />'/>
<!-- //lined-icons -->
</head> 
<body>
   <div class="page-container ">
   <!--/content-inner-->
<div class="left-content container">
	<div class = "row">	
	<h3>Sản Phẩm</h3>
		<form action="" id = "form_sanpham">
			<div class = "col-md-5 col-sm-12 form-group">
			<br>
			<label for = "tenSanPham">Tên Sản Phẩm:</label><br>
			<input type="text" class = "form-control" name = "tenSanPham" id ="tenSanPham" placeholder="Nhập tên sản phẩm">
			<br>
			<label for = "giatien">Giá Tiền:</label><br>
			<input type="text" class = "form-control" name = "giatien" id ="giatien" placeholder="Nhập vào giá tiền san phẩm">
			<br>
			<label>Danh Mục Sản Phẩm :</label><br>
			<select name = "danhmuc" id = "danhmuc" class ="form-control" id = "sell">
				<c:forEach var="danhmuc" items="${listdanhmuc }">
					<option value ="${danhmuc.getIdDanhMuc()}">${danhmuc.getTenDanhMuc()}</option>
				</c:forEach>
			</select>
			<br>
			<label for = "mota">Mô tả:</label><br>
			<textarea rows = "5" type="text" class = "form-control" name = "mota" id ="mota" placeholder="Nhập vào mô tả sản phẩm"></textarea>
			<br>
			<label for = "hinhSanPham">Hình Ảnh:</label><br>
			<input type="file" class = "form-control" name = "hinhSanPham" id ="hinhSanPham" >
			<br>
			<span>Dành cho:</span>
			<br>
	 		<label class="radio-inline"><input id ="rd-nam" type="radio" value = "nam" name="gianhcho" checked>Nam</label>
			<label class="radio-inline"><input id ="rd-nu" type="radio" value = "nu" name="gianhcho">Nữ</label>
			<br>
			<br>
		</form>
		<div id = "containerchitietsanoham">
			<div class = "chitietsanpham">
			<span>Chi Tiết </span></br>
		
			<select name = "sizesanpham" id = "sizesanpham" class ="form-control" >
				<c:forEach var="size" items="${listsize }">
					<option value ="${size.getIdSizeSanPham()}">${size.getTenSizeSanPham()}</option>
				</c:forEach>
			</select>
			<br>
		
			<select name = "mausanpham" id = "mausanpham" class ="form-control" >
				<c:forEach var="mau" items="${listmau }">
					<option value ="${mau.getIdMauSanPham()}">${mau.getTenMauSanPham()}</option>
				</c:forEach>
			</select>
			<br>
			<input min = "1" value = "1" type="number" class = "form-control" name = "soluong" id ="soluong" placeholder="nhập vào số lượng" >
			<br>
			<button  id = "btn_themchitiet" class ="btn btb-primary">Thêm Chi tiết</button>
		</div>
		</div>
		<div id = "chitietsanpham" class = "chitietsanpham">
			<span>Chi Tiết </span></br>
		
			<select name = "sizesanpham" id = "sizesanpham" class ="form-control" >
				<c:forEach var="size" items="${listsize }">
					<option value ="${size.getIdSizeSanPham()}">${size.getTenSizeSanPham()}</option>
				</c:forEach>
			</select>
			<br>
		
			<select name = "mausanpham" id = "mausanpham" class ="form-control" >
				<c:forEach var="mau" items="${listmau }">
					<option value ="${mau.getIdMauSanPham()}">${mau.getTenMauSanPham()}</option>
				</c:forEach>
			</select>
			<br>
		
			<input min = "1" value = "1" type="number" class = "form-control" name = "soluong" id ="soluong" placeholder="nhập vào số lượng" >
			<br>
			<button id = "btn_themchitiet" class ="btn btb-primary">Thêm Chi tiết</button></br>
		</div>
		<button style="margin-right : 0 ; margin-left:auto ; display:block " id = "btn_themsanpham" class ="btn btn-primary">Thêm sản phẩm</button>
		<button style="margin-right : 0 ; margin-left:auto ; display:block " id = "btn_capnhatsanpham" class =" hidden btn btn-primary">Cập nhật</button>
		<button  id = "btn_thoat" class =" hidden btn btn-primary">Thoát</button>
		
	</div>
	
	<div class ="col-md-7 col-sm-12" >
		
		<div style = "float:right">
			<button id = "btn-xoasp" class ="btn btn-info">Xóa</button>	
		</div>
		<table class="table" id = "table-sanpham">
			<thead>
			 <tr>
			 	<th>
					<div class = "checkbox" >
						<label><input id = "checkall" type ="checkbox" value = ""></label>
					</div>		 	
			 	</th>
			 	<th>Tên Sản Phẩm</th>
			 	<th>Giá Tiền</th>
			 	<th>Giành Cho</th>
			 </tr>
			</thead>
			<tbody>
				<c:forEach var="value" items="${listsp}">
					<tr>
					<td>
						<div class = "checkbox" >
							<label><input class ="checkboxsanpham" type ="checkbox" value ="${value.getIdSanPham() }"></label>
							</div>		 	
			 			</td>
						<td class ="tensp" data-masp = "${value.getIdSanPham() }">${value.getTenSanPham() }</td>
						<td class ="giatien" data-giasp = "${value.getGiatien() }">${value.getGiatien() }</td>
						<td class ="giotinh" data-sex = "${value.getGianhcho() }">${value.getGianhcho() }</td>
						<td class ="capnhatsanpham btn btn-info" data-id ="${value.getIdSanPham() }" >Sửa</td>
					</tr> 
				</c:forEach>
			</tbody>
		</table>
		
		<!--phan trang  -->
		<ul class="pagination">
			<c:forEach var ="i" begin="1" end = "${tongsopage }">
				<c:choose>
					<c:when test="${i == 1 }">
						<li class =" paging-item active"><a href="#">${i}</a></li>
					</c:when>	
					<c:otherwise>
						<li class ="paging-item"><a href="#">${i}</a></li>
					</c:otherwise>			
				</c:choose>
			</c:forEach>
 			 
		</ul>
	</div>
	</div>
	
</div>
	   <div class="mother-grid-inner">
				<div class="sidebar-menu">
					<header class="logo1">
						<a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> 
					</header>
						<div style="border-top:1px ridge rgba(255, 255, 255, 0.15)"></div>
                           <div class="menu">
									<ul id="menu" >
										<li><a href="index.html"><i class="fa fa-tachometer"></i> <span>Dashboard</span><div class="clearfix"></div></a></li>
										
										
										 <li id="menu-academico" ><a href="#"><i class="fa fa-envelope nav_icon"></i><span>Sản Phẩm</span><div class="clearfix"></div></a></li>
									<li><a href="gallery.html"><i class="fa fa-picture-o" aria-hidden="true"></i><span>Gallery</span><div class="clearfix"></div></a></li>
									<li id="menu-academico" ><a href="charts.html"><i class="fa fa-bar-chart"></i><span>Charts</span><div class="clearfix"></div></a></li>
									 <li id="menu-academico" ><a href="#"><i class="fa fa-list-ul" aria-hidden="true"></i><span> Short Codes</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
										   <ul id="menu-academico-sub" >
										   <li id="menu-academico-avaliacoes" ><a href="icons.html">Icons</a></li>
											<li id="menu-academico-avaliacoes" ><a href="typography.html">Typography</a></li>
											<li id="menu-academico-avaliacoes" ><a href="faq.html">Faq</a></li>
										  </ul>
										</li>
									<li id="menu-academico" ><a href="errorpage.html"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i><span>Error Page</span><div class="clearfix"></div></a></li>
									  <li id="menu-academico" ><a href="#"><i class="fa fa-cogs" aria-hidden="true"></i><span> UI Components</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
										   <ul id="menu-academico-sub" >
										   <li id="menu-academico-avaliacoes" ><a href="button.html">Buttons</a></li>
											<li id="menu-academico-avaliacoes" ><a href="grid.html">Grids</a></li>
										  </ul>
										</li>
									 <li><a href="tabels.html"><i class="fa fa-table"></i>  <span>Tables</span><div class="clearfix"></div></a></li>
									<li><a href="maps.html"><i class="fa fa-map-marker" aria-hidden="true"></i>  <span>Maps</span><div class="clearfix"></div></a></li>
							        <li id="menu-academico" ><a href="#"><i class="fa fa-file-text-o"></i>  <span>Pages</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
										 <ul id="menu-academico-sub" >
											<li id="menu-academico-boletim" ><a href="calendar.html">Calendar</a></li>
											<li id="menu-academico-avaliacoes" ><a href="signin.html">Sign In</a></li>
											<li id="menu-academico-avaliacoes" ><a href="signup.html">Sign Up</a></li>
											

										  </ul>
									 </li>
									<li><a href="#"><i class="fa fa-check-square-o nav_icon"></i><span>Forms</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
									  <ul>
										<li><a href="input.html"> Input</a></li>
										<li><a href="validation.html">Validation</a></li>
									</ul>
									</li>
								  </ul>
								</div>
							  </div>
							  <div class="clearfix"></div>		
							</div>
							<script>
							var toggle = true;
										
							$(".sidebar-icon").click(function() {                
							  if (toggle)
							  {
								$(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
								$("#menu span").css({"position":"absolute"});
							  }
							  else
							  {
								$(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
								setTimeout(function() {
								  $("#menu span").css({"position":"relative"});
								}, 400);
							  }
											
											toggle = !toggle;
										});
							</script>
<!--js -->
	<script src='<c:url value="/resources/template/js/jquery.nicescroll.js" />' ></script>
	<script src='<c:url value="/resources/template/js/scripts.js" />' ></script>
	
	<script src='<c:url value="/resources/JS/custom.js" />' ></script>

<!-- Bootstrap Core JavaScript -->
	<script src='<c:url value="/resources/template/js/bootstrap.min.js" />' ></script>
   
   <!-- /Bootstrap Core JavaScript -->	   
<!-- morris JavaScript -->	
<script src='<c:url value="/resources/template/js/raphael-min.js" />' ></script>
<script src='<c:url value="/resources/template/js/morris.js" />' ></script>

<script>
	$(document).ready(function() {
		//BOX BUTTON SHOW AND CLOSE
	   jQuery('.small-graph-box').hover(function() {
		  jQuery(this).find('.box-button').fadeIn('fast');
	   }, function() {
		  jQuery(this).find('.box-button').fadeOut('fast');
	   });
	   jQuery('.small-graph-box .box-close').click(function() {
		  jQuery(this).closest('.small-graph-box').fadeOut(200);
		  return false;
	   });
	   
	    //CHARTS
	    function gd(year, day, month) {
			return new Date(year, month - 1, day).getTime();
		}
		
		graphArea2 = Morris.Area({
			element: 'hero-area',
			padding: 10,
        behaveLikeLine: true,
        gridEnabled: false,
        gridLineColor: '#dddddd',
        axes: true,
        resize: true,
        smooth:true,
        pointSize: 0,
        lineWidth: 0,
        fillOpacity:0.85,
			data: [
				{period: '2014 Q1', iphone: 2668, ipad: null, itouch: 2649},
				{period: '2014 Q2', iphone: 15780, ipad: 13799, itouch: 12051},
				{period: '2014 Q3', iphone: 12920, ipad: 10975, itouch: 9910},
				{period: '2014 Q4', iphone: 8770, ipad: 6600, itouch: 6695},
				{period: '2015 Q1', iphone: 10820, ipad: 10924, itouch: 12300},
				{period: '2015 Q2', iphone: 9680, ipad: 9010, itouch: 7891},
				{period: '2015 Q3', iphone: 4830, ipad: 3805, itouch: 1598},
				{period: '2015 Q4', iphone: 15083, ipad: 8977, itouch: 5185},
				{period: '2016 Q1', iphone: 10697, ipad: 4470, itouch: 2038},
				{period: '2016 Q2', iphone: 8442, ipad: 5723, itouch: 1801}
			],
			lineColors:['#ff4a43','#a2d200','#22beef'],
			xkey: 'period',
            redraw: true,
            ykeys: ['iphone', 'ipad', 'itouch'],
            labels: ['All Visitors', 'Returning Visitors', 'Unique Visitors'],
			pointSize: 2,
			hideHover: 'auto',
			resize: true
		});
		
	   
	});
	</script>
</body>
</html>