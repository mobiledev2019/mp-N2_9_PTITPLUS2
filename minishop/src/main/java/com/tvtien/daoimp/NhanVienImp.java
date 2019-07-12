package com.tvtien.daoimp;

import com.tvtien.entity.NhanVien;

public interface NhanVienImp {
	boolean checkDangNhap(String username , String password);
	boolean themNhanViem(NhanVien nhanvien);
}
