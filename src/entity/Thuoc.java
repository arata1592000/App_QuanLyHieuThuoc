package entity;

import java.time.LocalDate;

public class Thuoc {
	private String maThuoc;
	private String tenThuoc;
	private LocalDate ngayNhapVe;
	private LocalDate ngayHetHan;
	private LocalDate ngaySanXuat;
	private String noiSanXuat;
	private float gia;
	private String donViTinh;
	private String thanhPhan;
	private int soLuong;
	private KhuyenMai khuyenMai;
	private String trangThai;
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
	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	public String getTrangThai() {
		String trangThai = "";
		if (soLuong == 0) {
			trangThai += "Đã hết thuốc";
		}
		if (!ngayHetHan.isAfter(LocalDate.now())) {
			if (trangThai.equals("")) {
				trangThai += "Đã hết hạn";
			}else {
				trangThai += ", đã hết hạn";
			}
		}
		return trangThai;
	}
	public Thuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Thuoc(String maThuoc, String tenThuoc, LocalDate ngayNhapVe, LocalDate ngaySanXuat, LocalDate ngayHetHan,
			String noiSanXuat, float gia, String donViTinh, String thanhPhan, int soLuong) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.ngayNhapVe = ngayNhapVe;
		this.ngayHetHan = ngayHetHan;
		this.ngaySanXuat = ngaySanXuat;
		this.noiSanXuat = noiSanXuat;
		this.gia = gia;
		this.donViTinh = donViTinh;
		this.thanhPhan = thanhPhan;
		this.soLuong = soLuong;
		this.khuyenMai = new KhuyenMai();
	}
	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", ngayNhapVe=" + ngayNhapVe + ", ngayHetHan="
				+ ngayHetHan + ", ngaySanXuat=" + ngaySanXuat + ", noiSanXuat=" + noiSanXuat + ", gia=" + gia
				+ ", donViTinh=" + donViTinh + ", thanhPhan=" + thanhPhan + ", soLuong=" + soLuong + ", khuyenMai="
				+ khuyenMai + "]";
	}
	
		
	
	
}
