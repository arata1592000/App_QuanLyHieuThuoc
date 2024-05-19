package entity;

import java.time.LocalDate;

public class ChiTietHoaDon {
	private String maThuoc;
	private String tenThuoc;
	private int soLuong;
	private String donViTinh;
	private float gia;
	private String thanhPhan;
	private LocalDate ngayHetHan;
	private float khuyenMai;
	private float tongTienSanPham;
	
	public String getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public float getGia() {
		return gia;
	}
	public void setGia(float gia) {
		this.gia = gia;
	}
	public String getThanhPhan() {
		return thanhPhan;
	}
	public void setThanhPhan(String thanhPhan) {
		this.thanhPhan = thanhPhan;
	}
	public LocalDate getNgayHetHan() {
		return ngayHetHan;
	}
	public void setNgayHetHan(LocalDate ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}
	public float getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(float khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	public float getTongTienSanPham() {
		return tongTienSanPham;
	}
	public void setTongTienSanPham(float tongTienSanPham) {
		this.tongTienSanPham = tongTienSanPham;
	}
	public ChiTietHoaDon(String maThuoc, String tenThuoc, int soLuong, String donViTinh, float gia, String thanhPhan, LocalDate ngayHetHan, float khuyenMai,
			float tongTienSanPham) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
		this.gia = gia;
		this.thanhPhan = thanhPhan;
		this.ngayHetHan = ngayHetHan;
		this.khuyenMai = khuyenMai;
		this.tongTienSanPham = tongTienSanPham;
	}
	public ChiTietHoaDon() {
		// TODO Auto-generated constructor stub
		super();
		this.maThuoc = "";
		this.tenThuoc = "";
		this.soLuong = 0;
		this.donViTinh = "";
		this.gia = 0;
		this.khuyenMai = 0;
		this.tongTienSanPham = 0;
	}
	
	
	
	
}