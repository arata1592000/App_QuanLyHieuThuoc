package entity;

import java.time.LocalDate;
import java.util.List;

public class HoaDon {
	private String maHD;
	private LocalDate ngayLap;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private float tongTien;
	private String loaiHD;
	private KhuyenMai khuyenMai;
	private float thue;
	private String ghiChu;
	private List<ChiTietHoaDon> chiTietHoaDon;
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public LocalDate getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public float getTongTien() {
		return tongTien;
	}
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public float getThue() {
		return thue;
	}
	public void setThue(float thue) {
		this.thue = thue;
	}
	public String getLoaiHD() {
		return loaiHD;
	}
	public void setLoaiHD(String loaiHD) {
		this.loaiHD = loaiHD;
	}
	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	public List<ChiTietHoaDon> getChiTietHoaDon() {
		return chiTietHoaDon;
	}
	public void setChiTietHoaDon(List<ChiTietHoaDon> chiTietHoaDon) {
		this.chiTietHoaDon = chiTietHoaDon;
	}
	public HoaDon(String maHD, LocalDate ngayLap, float tongTien, String loaiHD, float thue, String ghiChu) {
		super();
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
		this.loaiHD = loaiHD;
		this.thue = thue;
		this.ghiChu = ghiChu;
	}
	
	
}
