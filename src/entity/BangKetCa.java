package entity;

import java.time.LocalDate;
import java.util.List;

public class BangKetCa {
	private String maCa;
	private NhanVien nhanVien;
	private LocalDate ngayLap;
	private float tienCoTrongCa;
	private float tienMatThuTrongCa;
	private float tienATMThuTrongCa;
	private float tienLayRa;
	private List<ChiTietBangKetCa> chiTietBangKetCa;
	public String getMaCa() {
		return maCa;
	}
	public void setMaCa(String maCa) {
		this.maCa = maCa;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNv(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public LocalDate getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}
	public float getTienCoTrongCa() {
		return tienCoTrongCa;
	}
	public void setTienCoTrongCa(float tienCoTrongCa) {
		this.tienCoTrongCa = tienCoTrongCa;
	}
	public float getTienMatThuTrongCa() {
		return tienMatThuTrongCa;
	}
	public void setTienMatThuTrongCa(float tienMatThuTrongCa) {
		this.tienMatThuTrongCa = tienMatThuTrongCa;
	}
	public float getTienATMThuTrongCa() {
		return tienATMThuTrongCa;
	}
	public void setTienATMThuTrongCa(float tienATMThuTrongCa) {
		this.tienATMThuTrongCa = tienATMThuTrongCa;
	}
	public float getTienLayRa() {
		return tienLayRa;
	}
	public void setTienLayRa(float tienLayRa) {
		this.tienLayRa = tienLayRa;
	}
	public List<ChiTietBangKetCa> getChiTietBangKetCa() {
		return chiTietBangKetCa;
	}
	public void setChiTietBangKetCa(List<ChiTietBangKetCa> chiTietBangKetCa) {
		this.chiTietBangKetCa = chiTietBangKetCa;
	}
	public BangKetCa() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BangKetCa(String maCa, NhanVien nhanVien, LocalDate ngayLap, float tienCoTrongCa, float tienMatThuTrongCa,
			float tienATMThuTrongCa, float tienLayRa) {
		super();
		this.maCa = maCa;
		this.nhanVien = nhanVien;
		this.ngayLap = ngayLap;
		this.tienCoTrongCa = tienCoTrongCa;
		this.tienMatThuTrongCa = tienMatThuTrongCa;
		this.tienATMThuTrongCa = tienATMThuTrongCa;
		this.tienLayRa = tienLayRa;
	}
	
	
}
