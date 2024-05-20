package entity;

public class ChiTietBangKetCa {
	private float menhGia;
	private int soLuong;
	public float getMenhGia() {
		return menhGia;
	}
	public void setMenhGia(float menhGia) {
		this.menhGia = menhGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public ChiTietBangKetCa(float menhGia, int soLuong) {
		super();
		this.menhGia = menhGia;
		this.soLuong = soLuong;
	}
}
