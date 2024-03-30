package entity;

public class KhachHang {
	private String maKH;
	private String hoTen;;
	private String sDT;
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getsDT() {
		return sDT;
	}
	public void setsDT(String sDT) {
		this.sDT = sDT;
	}
	public KhachHang(String maKH, String hoTen, String sDT) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.sDT = sDT;
	}
	
}
