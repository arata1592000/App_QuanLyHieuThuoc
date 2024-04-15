package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private String gioiTinh;
	private String soDienThoai;
	private LocalDate ngaySinh;
	private LocalDate ngayVaoLam;
	private String chucVu;
	private String soCCCD;
	private String diaChi;
	private String trangThai;
	private byte[] anh;
	public NhanVien(String maNV, String hoTen, String gioiTinh, String soDienThoai, LocalDate ngaySinh,
			LocalDate ngayVaoLam, String chucVu, String soCCCD, String diaChi, String trangThai) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.ngayVaoLam = ngayVaoLam;
		this.chucVu = chucVu;
		this.soCCCD = soCCCD;
		this.diaChi = diaChi;
		this.trangThai = trangThai;
		this.anh = null;
	}
	
	public NhanVien(String maNV, String hoTen, String gioiTinh, String soDienThoai, LocalDate ngaySinh,
			LocalDate ngayVaoLam, String chucVu, String soCCCD, String diaChi, String trangThai, byte[] anh) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.ngayVaoLam = ngayVaoLam;
		this.chucVu = chucVu;
		this.soCCCD = soCCCD;
		this.diaChi = diaChi;
		this.trangThai = trangThai;
		this.anh = anh;
	}

	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public LocalDate getNgayVaoLam() {
		return ngayVaoLam;
	}
	public void setNgayVaoLam(LocalDate ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public String getSoCCCD() {
		return soCCCD;
	}
	public void setSoCCCD(String soCCCD) {
		this.soCCCD = soCCCD;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public byte[] getAnh() {
		return anh;
	}
	public void setAnh(byte[] anh) {
		this.anh = anh;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", soDienThoai=" + soDienThoai
				+ ", ngaySinh=" + ngaySinh + ", ngayVaoLam=" + ngayVaoLam + ", chucVu=" + chucVu + ", soCCCD=" + soCCCD
				+ ", diaChi=" + diaChi + ", trangThai=" + trangThai + ", anh=" + Arrays.toString(anh) + "]";
	}
	public NhanVien(String maNV) {
		this.maNV = maNV;
	}
	
	
}

