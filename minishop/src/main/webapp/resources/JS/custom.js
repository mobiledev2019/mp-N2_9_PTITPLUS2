$(document).ready(function(){
	
	var masanpham = 0;
	
	$("#btn_dangnhap").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		$.ajax({
			url:"/minishop/api/kiemtradangnhap",
			type:"GET",
			data:{
				username : username,
				password : password
			},
			success: function(value){
				if(value == "true"){
					$("#ketqua").text("thanh cong")
//					chuyen sang trang chu
					duongdanhientai = window.location.href;
					duongDan = duongdanhientai.replace("dangnhap/","");
					window.location = duongDan;
				}
				else{
					$("#ketqua").text("that bai")
				}
			}
		})
	});
	
	
	$("#login").click(function(){
		$(this).addClass("actived");
		$("#sigup").removeClass("actived");
		$("#container_dangnhap").show();
		$("#container_sigup").hide();
	});
	

	$("#sigup").click(function(){
		$(this).addClass("actived");
		$("#login").removeClass("actived");
		$("#container_dangnhap").hide();
		$("#container_sigup").show();
	});
	
	$(".btn_themgiohang").click(function(){
		var mamau = $(this).closest("tr").find(".mau").attr("data_idmau");
		var tenmau =  $(this).closest("tr").find(".mau").text();
		var masize = $(this).closest("tr").find(".size").attr("data_idsize");
		var tensize = $(this).closest("tr").find(".size").text();
		var tensanpham = $("#tensanpham").text();
		var giatien = $("#giatien").text();
		var masanpham = $("#tensanpham").attr("data_idsanpham");
		var soluong = $(this).closest("tr").find(".soluong").text();
		var machitiet = $(this).attr("data-chitiet");
		$.ajax({
			url:"/minishop/api/themgiohang",
			type:"GET",
			data:{
				idSanPham : masanpham,
				idMaMauSanPham : mamau,
				idSizeSanpham : masize,
				tenSanPham : tensanpham,
				tenMauSanPham : tenmau ,
				tenSizeSanPham : tensize,
				giatien : giatien ,
				soluong : soluong,
				idChiTietSanPham : machitiet
			},
			success: function(value){
				$(".ic_giohang").html("<span>" + value + "</span>");
			}
		}).done(function(){
			$.ajax({
				url:"/minishop/api/laysoluonggiohang",
				type:"GET",
				success: function(value){
					$(".ic_giohang").html("<span>" + value + "</span>");
				}
			})
		});
	});
	
	var tongtiensp = 0;
	$(".giatien").each(function(){
		var giatien = $(this).attr("data_value");
		var soluong =  $(this).closest("tr").find(".soluong-giohang").val();
		giatien = parseInt(soluong) * parseInt(giatien) ;
		$(this).closest("tr").find(".giatien").html(giatien);
		tongtiensp = parseInt(tongtiensp) +parseInt( giatien);
		$("#tongtien").html(tongtiensp);
	});
	
	// gan gia tri cho tong tien khi so luong thay doi
	function gangiatritongtien(){
		var tongtiensanpham = 0;
		$(".giatien").each(function(){
			var giatien = $(this).text();

			tongtiensanpham = parseInt(tongtiensanpham) + parseInt(giatien);
			$("#tongtien").html(tongtiensanpham);
		})
	};
	
	// thay doi so luong trong gio hang
	$(".soluong-giohang").change(function(){
		var soluong = $(this).val();
		var giatien = $(this).closest("tr").find(".giatien").attr("data_value");
		
		var tongtien = soluong * giatien;
		$(this).closest("tr").find(".giatien").html(tongtien);
		gangiatritongtien();
		
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var masp = $(this).closest("tr").find(".tensp").attr("data-masp");
		
		$.ajax({
			url:"/minishop/api/capnhatgiohang",
			type:"GET",
			data:{
				masp : masp ,
				masize : masize,
				mamau : mamau,
				soluong : soluong
			},
			success: function(value){
				
			}
		})
		
	});
	
	// xoa san pham trong gio hang
	$(".btn_xoa").click(function(){
		var self = $(this);
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var masp = $(this).closest("tr").find(".tensp").attr("data-masp");
		
		$.ajax({
			url:"/minishop/api/xoagiohang",
			type:"GET",
			data:{
				masp : masp ,
				masize : masize,
				mamau : mamau,
			},
			success: function(value){
				self.closest("tr").remove();
				gangiatritongtien();
			}
		})
	});
	//phan trang 
	$("body").on("click",".paging-item",function(){
		$(".paging-item").removeClass("active");
		$(this).addClass("active");
		var sotrang = $(this).text();
		var spbatdau = (sotrang - 1)*5;
		$.ajax({
			url:"/minishop/api/laysanphamlimit",
			type:"GET",
			data:{
				spbatdau : spbatdau,
			},
			success: function(value){
				var tbodysp = $("#table-sanpham").find("tbody");
				tbodysp.empty();
				tbodysp.append(value);
			}
		})
	});
	
	// khi check all duoc click
	
	$("#checkall").change(function() {
		if(this.checked){
			$("#table-sanpham input").each(function() {
				$(this).attr("checked",true);
			})
		}else{
			$("#table-sanpham input").each(function() {
				$(this).attr("checked",false);
			})
		}
	});
	
	// xoa cac san pham duoc lua chon trong checkbox
	
	$("#btn-xoasp").click(function() {
		$("#table-sanpham > tbody input:checked").each(function() {
			var masanpham = $(this).val();
			var This = $(this);
			
			$.ajax({
				url:"/minishop/api/xoasanpham",
				type:"GET",
				data:{
					masanpham : masanpham,
				},
				success: function(value){
					This.closest("tr").remove();
				}
			})
		})
	});
	
	// up load hinh anh
	var files = [];
	var hinhSanPham = "";
	$("#hinhSanPham").change(function(event) {
		files = event.target.files;
		hinhSanPham = files[0].name;
		forms = new FormData();
		forms.append("file",files[0]);
		
		$.ajax({
			url:"/minishop/api/uploadfile",
			type:"POST",
			data:forms,
			contentType:false,
			processData:false,
			enctype: "multipart/form-data",
			success: function(value){
				
			}
		})
	});
	
	// them html chi tiet san pham
	$("body").on("click","#btn_themchitiet" ,function(){
		var chitietclone = $("#chitietsanpham").clone().removeAttr("id");
		$(this).remove();
		$("#containerchitietsanoham").append(chitietclone);
	})
	
	// them san pham
	$("#btn_themsanpham").click(function(event) {
		event.preventDefault();
		var form_data = $("#form_sanpham").serializeArray();
		// dua ve dang co ban du liue cua san pham
		json = {};
		arraychitiet = [];
		objectChitiet = {};
		$.each(form_data, function(i, field){
			json[field.name] = field.value; 
		  });
		
		// lay du lieu cua cac chi tiet san pham
		$("#containerchitietsanoham > .chitietsanpham").each(function() {
			objectChitiet = {};
			sizesanpham = $(this).find('select[name="sizesanpham"]').val();
			mausanpham = $(this).find('select[name="mausanpham"]').val();
			soluong = $(this).find('input[name = "soluong"]').val();
			objectChitiet["sizesanpham"] =sizesanpham;
			objectChitiet["mausanpham"] =mausanpham;
			objectChitiet["soluong"] =soluong;
			
			arraychitiet.push(objectChitiet);
		})
		
		// dua du lieu cac chi tiet san pham vao trong san pham
		json["chitietsanpham"] = arraychitiet;
		json["hinhSanPham"] = hinhSanPham;
		console.log(JSON.stringify(json));
		
		$.ajax({
			url:"/minishop/api/themsanpham",
			type:"POST",
			data:{
				dataJson : JSON.stringify(json)
			},
			success: function(value){
				
			}
		})
	});
	
	
	$("body").on("click",".capnhatsanpham",function(){
		masanpham= $(this).attr("data-id");
		
		$("#btn_capnhatsanpham").removeClass("hidden");
		$("#btn_thoat").removeClass("hidden");
		
		$("#btn_themsanpham").addClass("hidden");
		$.ajax({
			url:"/minishop/api/GetSanPhamByID",
			type:"POST",
			data:{
				masanpham : masanpham
			},		
			success: function(value){
				$("#tenSanPham").val(value.tenSanPham);
				$("#giatien").val(value.giatien);
				$("#mota").val(value.mota);
				if(value.gianhcho === "nam"){
					$("#rd-name").prop("checked",true);
				}else{
					$("#rd-nu").prop("checked",true);
				}
				
				$("#danhmuc").val(value.danhmuc.idDanhMuc);
				
				$("#containerchitietsanoham").empty();
				
				for(i = 0 ;i< value.chitiets.length; i++){
					var chitietclone = $("#chitietsanpham").clone().removeAttr("id");
					if( i < value.chitiets.length - 1){
						chitietclone.find("#btn_themchitiet").remove();
					}
					chitietclone.find("#mausanpham").val(value.chitiets[i].mausanpham.idMauSanPham);
					chitietclone.find("#sizesanpham").val(value.chitiets[i].sizesanpham.idSizeSanPham);
					chitietclone.find("#soluong").val(value.chitiets[i].soluong);
					$("#containerchitietsanoham").append(chitietclone);
				}
				
				console.log(value)
			}
		})
	})
	
	
	// cap nhat san pham
	$("#btn_capnhatsanpham").click(function(event) {
		event.preventDefault();
		var form_data = $("#form_sanpham").serializeArray();
		// dua ve dang co ban du liue cua san pham
		json = {};
		arraychitiet = [];
		objectChitiet = {};
		$.each(form_data, function(i, field){
			json[field.name] = field.value; 
		  });
		
		// lay du lieu cua cac chi tiet san pham
		$("#containerchitietsanoham > .chitietsanpham").each(function() {
			objectChitiet = {};
			sizesanpham = $(this).find('select[name="sizesanpham"]').val();
			mausanpham = $(this).find('select[name="mausanpham"]').val();
			soluong = $(this).find('input[name = "soluong"]').val();
			objectChitiet["sizesanpham"] =sizesanpham;
			objectChitiet["mausanpham"] =mausanpham;
			objectChitiet["soluong"] =soluong;
			
			arraychitiet.push(objectChitiet);
		})
		
		// dua du lieu cac chi tiet san pham vao trong san pham
		json["idsanpham"] = masanpham;
		json["chitietsanpham"] = arraychitiet;
		json["hinhSanPham"] = hinhSanPham;
		console.log(JSON.stringify(json));
		
		$.ajax({
			url:"/minishop/api/capnhatsanpham",
			type:"POST",
			data:{
				dataJson : JSON.stringify(json)
			},
			success: function(value){
				
			}
		})
	});
	
})