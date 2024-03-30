package entity;

import java.time.LocalDate;

public class Thuoc {
	private String maThuoc;
	private String tenThuoc;
	private float gia;
	private int soLuong;
	private LocalDate ngayNhapVe;
	private LocalDate ngayHetHan;
	private String noiSanXuat;
	private LocalDate ngaySanXuat;
	private String nhaCungCap;
	private String donViTinh;
	private String thanhPhan;
	private String maKM;
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
	public float getGia() {
		return gia;
	}
	public void setGia(float gia) {
		this.gia = gia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public LocalDate getNgayNhapVe() {
		return ngayNhapVe;
	}
	public void setNgayNhapVe(LocalDate ngayNhapVe) {
		this.ngayNhapVe = ngayNhapVe;
	}
	public LocalDate getNgayHetHan() {
		return ngayHetHan;
	}
	public void setNgayHetHan(LocalDate ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}
	public String getNoiSanXuat() {
		return noiSanXuat;
	}
	public void setNoiSanXuat(String noiSanXuat) {
		this.noiSanXuat = noiSanXuat;
	}
	public LocalDate getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(LocalDate ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}
	public String getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public String getThanhPhan() {
		return thanhPhan;
	}
	public void setThanhPhan(String thanhPhan) {
		this.thanhPhan = thanhPhan;
	}
	public String getMaKM() {
		return maKM;
	}
	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}
	public Thuoc(String maThuoc, String tenThuoc, float gia, int soLuong, LocalDate ngayNhapVe, LocalDate ngayHetHan,
			String noiSanXuat, LocalDate ngaySanXuat, String nhaCungCap, String donViTinh, String thanhPhan) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.gia = gia;
		this.soLuong = soLuong;
		this.ngayNhapVe = ngayNhapVe;
		this.ngayHetHan = ngayHetHan;
		this.noiSanXuat = noiSanXuat;
		this.ngaySanXuat = ngaySanXuat;
		this.nhaCungCap = nhaCungCap;
		this.donViTinh = donViTinh;
		this.thanhPhan = thanhPhan;
		this.maKM = null;
	}
	public Thuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
