package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

public class Gui_Chinh extends JFrame implements ActionListener{

	private JPanel pNoiDung;
	private JLabel lblNV;
	private JLabel lblKH;
	private JLabel lblBD;
	private JLabel lblThueBD;
	private JLabel lblBC;
	private JLabel lblTraBD;
	private JPanel pMain;
	private JPanel pHeader;
	private JPanel pLeft;
	private JPopupMenu popMenu;
	private JButton btnTrangChu;
	private JButton btnSanPham;
	private JButton item4;
	private JButton btnHoaDon;
	private JButton item5;
	private JPanel pMenu;
	private JLabel lbl1;
	private JButton btnNhanVien;
	private JButton btnKhuyenMai;
	private JButton btnKhachHang;
	private JButton btnThongKe;
	private JButton btnDangXuat;
	private JButton btnHuongDan;
	private JPanel pLogo;
	private JPanel pApplet;
	private JPanel pContent;
	
	private int widthFrame, heightFrame;
	private Gui_TrangChu Gui_TrangChu;
	private JButton actButtonBack = new JButton();

	public Gui_Chinh() {
		this.setTitle("Hệ thống quản lý hiệu thuốc Ân Cần");
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bounds = ge.getMaximumWindowBounds();
        this.setBounds(bounds);		
        widthFrame = getWidth();
        heightFrame = getHeight();
        this.setLayout(new BorderLayout());
	    this.setLocationRelativeTo(null);
		this.setResizable(false);
		initCompoent();
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void initCompoent() {
		
		pLeft = new JPanel();
		pLogo = new JPanel();
		pMenu = new JPanel();
		pMain = new JPanel();
		pMain.setBackground(Color.WHITE);
		pHeader = new JPanel();
		pContent = new JPanel();
		lbl1 = new JLabel();
		
		pLeft.setBackground(new Color(40,156,164));
		pLogo.setBackground(new Color(40,156,164));
		pMenu.setBackground(new Color(40,156,164));
		btnTrangChu = this.createButtonMenu("Trang chủ", new ImageIcon(""));
		btnSanPham = this.createButtonMenu("Sản phẩm", new ImageIcon(""));
		btnHoaDon = this.createButtonMenu("Hóa đơn", new ImageIcon(""));
		btnNhanVien = this.createButtonMenu("Nhân Viên", new ImageIcon(""));
		btnKhuyenMai = this.createButtonMenu("Khuyến Mãi", new ImageIcon(""));
		btnKhachHang = this.createButtonMenu("Khách Hàng", new ImageIcon(""));
		btnThongKe = this.createButtonMenu("Thống Kê", new ImageIcon(""));
		btnHuongDan = this.createButtonMenu("Hướng Dẫn", new ImageIcon(""));
		btnDangXuat = this.createButtonMenu("Đăng Xuất", new ImageIcon(""));
		pMain.setBackground(new Color(40,156,164));
		pHeader.setBackground(new Color(40,156,164));
		pHeader.setLayout(new BorderLayout());
		lbl1.setText("TRANG CHỦ");
		lbl1.setFont(new Font("Arial", Font.BOLD, 20));
		lbl1.setForeground(Color.WHITE);
		pContent.setBackground(Color.WHITE);

		btnTrangChu.addActionListener(this);
		btnSanPham.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnKhuyenMai.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnHuongDan.addActionListener(this);
		btnDangXuat.addActionListener(this);
		
		pContent.add(new Gui_TrangChu());
		btnTrangChu.setBackground(new Color(224,255,255));
		actButtonBack = btnTrangChu;

		
		pMenu.add(btnTrangChu);
        pMenu.add(Box.createRigidArea(new Dimension(0, 50))); // Khoảng cách đầu tiên
		pMenu.add(btnSanPham);
        pMenu.add(Box.createRigidArea(new Dimension(0, 50))); // Khoảng cách đầu tiên
		pMenu.add(btnHoaDon);
        pMenu.add(Box.createRigidArea(new Dimension(0, 50))); // Khoảng cách đầu tiên
		pMenu.add(btnNhanVien);
        pMenu.add(Box.createRigidArea(new Dimension(0, 50))); // Khoảng cách đầu tiên
		pMenu.add(btnKhuyenMai);
        pMenu.add(Box.createRigidArea(new Dimension(0, 50))); // Khoảng cách đầu tiên
		pMenu.add(btnKhachHang);
        pMenu.add(Box.createRigidArea(new Dimension(0, 50))); // Khoảng cách đầu tiên
		pMenu.add(btnThongKe);
        pMenu.add(Box.createRigidArea(new Dimension(0, 50))); // Khoảng cách đầu tiên
		pMenu.add(btnHuongDan);
        pMenu.add(Box.createRigidArea(new Dimension(0, 50))); // Khoảng cách đầu tiên
		pMenu.add(btnDangXuat);
        pMenu.add(Box.createRigidArea(new Dimension(0, 50))); // Khoảng cách đầu tiên
        pLeft.add(pLogo);
		pLeft.add(pMenu);
		pHeader.add(lbl1, BorderLayout.WEST);
		pMain.add(pHeader);
		pMain.add(pContent);
		this.add(pLeft, BorderLayout.WEST);
		this.add(pMain, BorderLayout.CENTER);
		this.addComponentListener(new ComponentAdapter() {
	        @Override
	        public void componentResized(ComponentEvent e) {
	            pLeft.setPreferredSize(new Dimension((int) ((int)widthFrame*0.1), heightFrame));
	            pLogo.setPreferredSize(new Dimension(pLeft.getWidth(), (int)(pLeft.getHeight() * 0.20)));
	    		pMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	            pMenu.setPreferredSize(new Dimension(pLeft.getWidth(), (int)(pLeft.getHeight() * 0.8)));
	    		pMain.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	            pMain.setPreferredSize(new Dimension((int) ((int)widthFrame*0.9), heightFrame));
	    		pHeader.setPreferredSize(new Dimension(pMain.getWidth(), 75));
	    		pContent.setPreferredSize(new Dimension(pMain.getWidth(), pMain.getHeight()-75));
	            revalidate(); // Cần gọi revalidate() để đảm bảo cập nhật layout
	        }
	    });
	}
	
	private JButton createButtonMenu(String titleButton, ImageIcon image) {
		Image img = image.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JButton btn = new JButton(titleButton, new ImageIcon(img));
		btn.setBackground(new Color(224,220,220));
		btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false); // Loại bỏ trạng thái focus
        btn.setPreferredSize(new Dimension(140, 35));
        btn.setFont(new Font("Arial", Font.PLAIN, 17));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
		return btn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object act = e.getSource();
		if (act.equals(btnTrangChu)) {
			if (!actButtonBack.equals(btnTrangChu)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnTrangChu.setBackground(new Color(224,255,255));
				pContent.removeAll();
				pContent.add(new Gui_TrangChu());
				lbl1.setText("TRANG CHỦ");
			}
			actButtonBack = btnTrangChu;
		}else if (act.equals(btnSanPham)) {
			if (!actButtonBack.equals(btnSanPham)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnSanPham.setBackground(new Color(224,255,255));
				pContent.removeAll();
				pContent.add(new Gui_SanPham(pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("SẢN PHẨM");
			}
			actButtonBack = btnSanPham;
		}else if (act.equals(btnHoaDon)) {
			if (!actButtonBack.equals(btnHoaDon)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnHoaDon.setBackground(new Color(224,255,255));
				pContent.removeAll();
				pContent.add(new Gui_HoaDon());
				lbl1.setText("HÓA ĐƠN");
			}
			actButtonBack = btnHoaDon;
		}else if (act.equals(btnNhanVien)) {
			if (!actButtonBack.equals(btnNhanVien)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnNhanVien.setBackground(new Color(224,255,255));
				pContent.removeAll();
				pContent.add(new Gui_NhanVien(pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("NHÂN VIÊN");
			}
			actButtonBack = btnNhanVien;
		}else if (act.equals(btnKhachHang)) {
			if (!actButtonBack.equals(btnKhachHang)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnKhachHang.setBackground(new Color(224,255,255));
				pContent.removeAll();
				pContent.add(new Gui_KhachHang(pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("KHÁCH HÀNG");
			}
			actButtonBack = btnKhachHang;
		}else if (act.equals(btnKhuyenMai)) {
			if (!actButtonBack.equals(btnKhuyenMai)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnKhuyenMai.setBackground(new Color(224,255,255));
				pContent.removeAll();
				pContent.add(new Gui_KhuyenMai(pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("KHUYẾN MÃI");
			}
			actButtonBack = btnKhuyenMai;
		}else if (act.equals(btnThongKe)) {
			if (!actButtonBack.equals(btnSanPham)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnThongKe.setBackground(new Color(224,255,255));
				pContent.removeAll();
				pContent.add(new Gui_ThongKe(pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("THỐNG KÊ");
			}
			actButtonBack = btnThongKe;
		}else if (act.equals(btnHuongDan)) {
			if (!actButtonBack.equals(btnHuongDan)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnHuongDan.setBackground(new Color(224,255,255));
				pContent.removeAll();
				pContent.add(new Gui_TroGiup(pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("HƯỚNG DẪN");
			}
			actButtonBack = btnHuongDan;
		}else if (act.equals(btnDangXuat)) {
			actButtonBack.setBackground(new Color(224,220,220));
			btnDangXuat.setBackground(new Color(224,255,255));
            Object[] options = {"Đăng Xuất", "Hủy", "Thoát"};
			int response = JOptionPane.showOptionDialog(this, 
	                "Bạn muốn làm gì?", 
	                "Xác nhận",
	                JOptionPane.YES_NO_CANCEL_OPTION, 
	                JOptionPane.QUESTION_MESSAGE,
	                null, // Không sử dụng icon tùy chỉnh
	                options, // Tiêu đề các nút
	                options[0]); // Mặc định là nút "Đăng Xuất"
	            
	            switch (response) {
	                case JOptionPane.YES_OPTION:
	                    System.out.println("Đăng xuất thành công!");
	                    // Thực hiện đăng xuất
	                    break;
	                case JOptionPane.NO_OPTION:
	                    System.out.println("Hủy thao tác.");
	                    // Không làm gì cả
	                    btnDangXuat.setBackground(new Color(224,220,220));
	                    actButtonBack.setBackground(new Color(224,255,255));
	                    break;
	                case JOptionPane.CANCEL_OPTION:
	                    System.out.println("Thoát ứng dụng!");
	                    // Thực hiện thoát ứng dụng
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("Không có hành động nào được thực hiện.");
	                    btnDangXuat.setBackground(new Color(224,220,220));
	                    actButtonBack.setBackground(new Color(224,255,255));
	                    break;
	            }
		}
		revalidate();
		repaint();
	}
	

}
