package entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class KhuyenMai {
	private String maKM;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private float tyLeKM;
	private String loaiKM;
	private float giaTriHD;
	
	public KhuyenMai(String maKM, LocalDate ngayBatDau, LocalDate ngayKetThuc, float tyLeKM, String loaiKM
			) {
		super();
		this.maKM = maKM;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.loaiKM = loaiKM;
		this.tyLeKM = tyLeKM;
		this.giaTriHD = 0;
	}
	

	public KhuyenMai(String maKM, LocalDate ngayBatDau, LocalDate ngayKetThuc, float tyLeKM, String loaiKM,
			float giaTriHD) {
		super();
		this.maKM = maKM;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.tyLeKM = tyLeKM;
		this.loaiKM = loaiKM;
		this.giaTriHD = giaTriHD;
	}


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
	public float getGiaTriHD() {
		return giaTriHD;
	}
	public void setGiaTriHD(float giaTriHD) {
		this.giaTriHD = giaTriHD;
	}
	public String getTrangThai()
	{
		if(ngayKetThuc.isAfter(ngayBatDau))
			return "Hoạt động";
		else
			return "Không hoạt động";
	}
	public KhuyenMai() {
		super();
		// TODO Auto-generated constructor stub
		this.maKM = null;
		this.ngayBatDau = null;
		this.ngayKetThuc = null;
		this.tyLeKM = 0;
		this.loaiKM = null;
		this.giaTriHD = 0;
	}

	
}
