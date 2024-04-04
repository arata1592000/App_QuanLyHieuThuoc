	package gui;
	
	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
	import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
	import javax.swing.BoxLayout;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.SwingConstants;
	
	public class Gui_TroGiup extends JPanel{
		
		private JLabel lblTrangChu;
		private JLabel lblSanPham;
		private JLabel lblHoaDon;
		private JLabel lblNhanVien;
		private JLabel lblKhuyenMai;
		private JLabel lblKhachHang;
		private JLabel lblThongKe;
		private JLabel lblHuongDan;
		private JPanel pHead;
		private JPanel pBot;
		private JPanel pShorcut1;
		private JPanel pShorcut2;
		private JPanel pShorcut3;
		private JLabel lblDel;
		private JLabel lblESC;
		private JLabel lblCTRL;
		private JLabel lblENTER;
		private JLabel lblHD;
		private JLabel lblNV;
		private JLabel lblKH;
		private JLabel lblSL;
		private JLabel lblSDT;
		private JLabel lblKMSP;
		private JLabel lblKMHD;
		private JLabel lblTT;
		private JLabel lblE;
		private JLabel lblDC;
		private int widthComp;
		private int heightComp;
		private JPanel pCenter;
		private Component verticalStrut;
	
		public Gui_TroGiup(int width, int height) {
			widthComp = width;
			heightComp = height;
			
			pHead = new JPanel();
			pBot = new JPanel();
			pCenter = new JPanel();

			pShorcut1 = new JPanel();
	        pShorcut1.setLayout(new BoxLayout(pShorcut1, BoxLayout.Y_AXIS)); // Thiết lập layout theo chiều dọc
			pShorcut2 = new JPanel();
	        pShorcut2.setLayout(new BoxLayout(pShorcut2, BoxLayout.Y_AXIS)); // Thiết lập layout theo chiều dọc
			pShorcut3 = new JPanel();
	        pShorcut3.setLayout(new BoxLayout(pShorcut3, BoxLayout.Y_AXIS)); // Thiết lập layout theo chiều dọc
	        Font font = new Font("Arial", Font.BOLD, 18); 
	        lblTrangChu = new JLabel("F1: Trang chủ");
			lblSanPham = new JLabel("F2: Sản phẩm");
			lblHoaDon = new JLabel("F3: Hóa đơn");
			lblNhanVien = new JLabel("F4 :Nhân viên");
			lblKhuyenMai = new JLabel("F5: Khuyến mãi");
			lblKhachHang = new JLabel("F6: Khách hàng");
			lblThongKe = new JLabel("F7: Thống kê");
			lblHuongDan = new JLabel("F8: Hướng dẫn");
			
			lblDel = new JLabel("DEL : Xóa dữ liệu đã chọn");
			lblESC = new JLabel("ESC + CTRL : Thoát khỏi chương trình");
			lblCTRL = new JLabel("CTRL + X : Đăng xuất");
			lblENTER = new JLabel("ENTER : Thực thi một công việt");
			
			lblHD = new JLabel("HD : Hóa đơn");
			lblNV = new JLabel("NV : Nhân viên");
			lblKH = new JLabel("KH : Khách hàng");
			lblSL = new JLabel("SL : Số lượng");
			lblSDT = new JLabel("SDT : Số điện thoại");
			lblKMSP = new JLabel("KMSP : Khuyễn mãi sản phẩm");
			lblKMHD = new JLabel("KMHD : Khuyến mãi hóa đơn");
			lblTrangChu.setFont(font);
			lblSanPham.setFont(font);
			lblHoaDon.setFont(font);
			lblNhanVien.setFont(font);
			lblKhuyenMai.setFont(font);
			lblKhachHang.setFont(font);
			lblThongKe.setFont(font);
			lblHuongDan.setFont(font);
			lblDel.setFont(font);
			lblENTER.setFont(font);
			lblESC.setFont(font);
			lblCTRL.setFont(font);
			lblHD.setFont(font);
			lblNV.setFont(font);
			lblKH.setFont(font);
			lblSL.setFont(font);
			lblSDT.setFont(font);
			lblKMSP.setFont(font);
			lblKMHD.setFont(font);
			pShorcut1.add(lblTrangChu);
			pShorcut1.add(lblSanPham);
			pShorcut1.add(lblHoaDon);
			pShorcut1.add(lblNhanVien);
			pShorcut1.add(lblKhuyenMai);
			pShorcut1.add(lblKhachHang);
			pShorcut1.add(lblThongKe);
			pShorcut1.add(lblHuongDan);
			
			pShorcut2.add(lblDel);
			pShorcut2.add(lblENTER);
			pShorcut2.add(lblESC);
			pShorcut2.add(lblCTRL);
			
			pShorcut3.add(lblHD);
			pShorcut3.add(lblNV);
			pShorcut3.add(lblKH);
			pShorcut3.add(lblSL);
			pShorcut3.add(lblSDT);
			pShorcut3.add(lblKMSP);
			pShorcut3.add(lblKMHD);
			pHead.add(pShorcut1);
			pHead.add(Box.createHorizontalStrut(290));
			pHead.add(pShorcut2);
			pHead.add(Box.createHorizontalStrut(290));
			pHead.add(pShorcut3);
			
			lblTT = new JLabel("Thông tin liên hệ");
			lblE = new JLabel("Email : anhthangdeptrai@gmail.com");
			lblSDT = new JLabel("SDT : 0881234586");
			lblDC = new JLabel("Địa chỉ : 12 Nguyễn Thái Sơn , Gò Vấp,Thành phố Hồ Chí Minh");
			lblTT.setFont(font);
			lblDC.setFont(font);
			lblE.setFont(font);
			lblSDT.setFont(font);
			pBot.add(lblTT);
			pBot.add(Box.createVerticalStrut(15));
			pBot.add(lblE);
			pBot.add(Box.createVerticalStrut(5));
			pBot.add(lblSDT);
			pBot.add(Box.createVerticalStrut(5));
			pBot.add(lblDC);
			pBot.add(Box.createVerticalStrut(10));
	        pBot.setLayout(new BoxLayout(pBot, BoxLayout.Y_AXIS)); // Thiết lập layout theo chiều dọc
	        pCenter.setBackground(Color.WHITE); // Đặt màu nền của pCenter là trắng
	        pCenter.add(Box.createVerticalStrut(375));
			this.setLayout(new BorderLayout());
			this.add(pHead,BorderLayout.NORTH);
			this.add(pCenter,BorderLayout.CENTER);
			this.add(pBot,BorderLayout.SOUTH);

		}
		
	}
	
	
