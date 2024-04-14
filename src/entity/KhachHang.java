package entity;

import dao.Dao_KhachHang;

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
	public int getCountOrderIn30Days() {
		return (new Dao_KhachHang()).getCountHoaDonIn30DayOfKhachHang(this.maKH);
	}
	public String getHangKH() {
		int countOrder = getCountOrderIn30Days();
		if (countOrder >= 10 ) {
			return "Vàng";
		}else if (countOrder >= 5) {
			return "Bạc";
		}else if (countOrder >= 3) {
			return "Đồng";
		}
		return "Không có";
	}
	
	
	public KhachHang(String maKH, String hoTen, String sDT) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.sDT = sDT;
	}
	public KhachHang(String maKH) {
		this.maKH = maKH;
	}
}
