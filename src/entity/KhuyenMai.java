package entity;

import java.time.LocalDate;

public class KhuyenMai {
	private String maKM;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private float tyLeKM;
	private String loaiKM;
	public String getMaKM() {
		return maKM;
	}
	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}
	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public float getTyLeKM() {
		return tyLeKM;
	}
	public void setTyLeKM(float tyLeKM) {
		this.tyLeKM = tyLeKM;
	}
	public String getLoaiKM() {
		return loaiKM;
	}
	public void setLoaiKM(String loaiKM) {
		this.loaiKM = loaiKM;
	}
	public KhuyenMai(String maKM, LocalDate ngayBatDau, LocalDate ngayKetThuc, float tyLeKM, String loaiKM) {
		super();
		this.maKM = maKM;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.tyLeKM = tyLeKM;
		this.loaiKM = loaiKM;
	}
	public KhuyenMai() {
		super();
		// TODO Auto-generated constructor stub
		this.maKM = null;
		this.ngayBatDau = null;;
		this.ngayKetThuc = null;;;
		this.tyLeKM = 0;
		this.loaiKM = null;
	}
	
	
}
