package entity;

import java.time.LocalDate;
import java.util.List;

public class HoaDon {
	private String maHD;
	private LocalDate ngayLap;
	private String maNV;
	private String maKH;
	private float tongTien;
	private String loaiHD;
	private KhuyenMai khuyenMai;
	private float thue;
	private String ghiChu;
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
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
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
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
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
	public HoaDon(String maHD, LocalDate ngayLap, String maNV, String maKH, float tongTien, String loaiHD,
			KhuyenMai khuyenMai, float thue, String ghiChu) {
		super();
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.maNV = maNV;
		this.maKH = maKH;
		this.tongTien = tongTien;
		this.loaiHD = loaiHD;
		this.khuyenMai = khuyenMai;
		this.thue = thue;
		this.ghiChu = ghiChu;
	}
	
	
}
