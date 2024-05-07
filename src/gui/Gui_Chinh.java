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
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import database.ConnectDB;
import entity.NhanVien;

public class Gui_Chinh extends JFrame implements ActionListener{

	private JPanel pMain;
	private JPanel pHeader;
	private JPanel pLeft;
	private JButton btnTrangChu;
	private JButton btnSanPham;
	private JButton btnHoaDon;
	private JPanel pMenu;
	private JLabel lbl1;
	private JButton btnNhanVien;
	private JButton btnKhuyenMai;
	private JButton btnKhachHang;
	private JButton btnThongKe;
	private JButton btnDangXuat;
	private JButton btnHuongDan;
	private JPanel pAvatar;
	private JPanel pContent;
	
	private int widthFrame, heightFrame;
	private Gui_TrangChu Gui_TrangChu;
	private JButton actButtonBack = new JButton();
	private JLabel lbl2;
	private JLabel lbl3;
	private NhanVien nv;
	private JButton btnTaiKhoan;
	
	public Gui_Chinh(NhanVien nv) {
		this.setTitle("Hệ thống quản lý hiệu thuốc Ân Cần");
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bounds = ge.getMaximumWindowBounds();
        this.setBounds(bounds);		
        widthFrame = getWidth();
        heightFrame = getHeight();
        this.nv = nv;
        this.setLayout(new BorderLayout());
	    this.setLocationRelativeTo(null);
		this.setResizable(false);
		initCompoent();
		phimTat();
		try {
            ConnectDB.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		pContent.add(new Gui_TrangChu(nv, pContent.getWidth(), pContent.getHeight()));

	}
	
	public void initCompoent() {
		
		pLeft = new JPanel();
		pAvatar = new JPanel();
		lbl2 = new JLabel();
		lbl3 = new JLabel();
		pMenu = new JPanel();
		pMain = new JPanel();
		pMain.setBackground(Color.WHITE);
		pHeader = new JPanel();
		pContent = new JPanel();
		lbl1 = new JLabel();
		
		pLeft.setBackground(new Color(40,156,164));
		pLeft.setPreferredSize(new Dimension((int) ((int)widthFrame*0.10), heightFrame));
		pAvatar.setBackground(new Color(40,156,164));
        pAvatar.setPreferredSize(new Dimension((int) ((int)widthFrame*0.10), (int) ((int)heightFrame *0.15)));
        ImageIcon image = new ImageIcon("images/logo.png");
		Image img = image.getImage().getScaledInstance((int) ((int)widthFrame*0.05), (int) ((int)widthFrame*0.05), Image.SCALE_SMOOTH);
        lbl2.setIcon(new ImageIcon(img));
        int labelWidth = (int) ((int)widthFrame*0.05);

        lbl3.setText("<html><body style='width: "+ labelWidth +"px; text-align: center'>NHÀ THUỐC ÂN CẦN</body></html>");
        lbl3.setFont(new Font("Arial", Font.BOLD, 15));
		pMenu.setBackground(new Color(40,156,164));
		pMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
        pMenu.setPreferredSize(new Dimension((int) ((int)widthFrame*0.10), (int) ((int)heightFrame *0.8)));
		btnTrangChu = this.createButtonMenu("Trang chủ (F1)", new ImageIcon("images/home.png"));
		btnSanPham = this.createButtonMenu("Sản phẩm (F2)", new ImageIcon("images/medicine.png"));
		btnHoaDon = this.createButtonMenu("Hóa đơn (F3)", new ImageIcon("images/bill.png"));
		btnNhanVien = this.createButtonMenu("Nhân Viên (F4)", new ImageIcon("images/employee.png"));
		btnKhuyenMai = this.createButtonMenu("Khuyến Mãi (F5)", new ImageIcon("images/discount.png"));
		btnKhachHang = this.createButtonMenu("Khách Hàng (F6)", new ImageIcon("images/customer.png"));
		btnThongKe = this.createButtonMenu("Thống Kê (F7)", new ImageIcon("images/statistics.png"));
		btnHuongDan = this.createButtonMenu("Hướng Dẫn (F8)", new ImageIcon("images/help.png"));
		btnTaiKhoan = this.createButtonMenu("Đổi mật khẩu (F9)", new ImageIcon("images/change-password.png"));
		btnDangXuat = this.createButtonMenu("Đăng Xuất", new ImageIcon("images/singout.png"));
		pMain.setBackground(new Color(40,156,164));
		pMain.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pMain.setPreferredSize(new Dimension((int) ((int)widthFrame*0.9), heightFrame));
		pHeader.setBackground(new Color(40,156,164));
		pHeader.setLayout(new BorderLayout());
		pHeader.setPreferredSize(new Dimension((int) ((int)widthFrame*0.9), 75));
		lbl1.setText("TRANG CHỦ");
		lbl1.setFont(new Font("Arial", Font.BOLD, 20));
		lbl1.setForeground(Color.WHITE);
		pContent.setBackground(Color.WHITE);
		pContent.setPreferredSize(new Dimension((int) ((int)widthFrame*0.9), heightFrame-75));


		btnTrangChu.addActionListener(this);
		btnSanPham.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnTaiKhoan.addActionListener(this);
		btnKhuyenMai.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnHuongDan.addActionListener(this);
		btnDangXuat.addActionListener(this);
		
		btnTrangChu.setBackground(new Color(224,255,255));
		actButtonBack = btnTrangChu;
		
		pAvatar.add(lbl2);
		pAvatar.add(lbl3);
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
		pMenu.add(btnTaiKhoan);
        pMenu.add(Box.createRigidArea(new Dimension(0, 50))); // Khoảng cách đầu tiên
		pMenu.add(btnDangXuat);
        pMenu.add(Box.createRigidArea(new Dimension(0, 50))); // Khoảng cách đầu tiên
        pLeft.add(pAvatar);
		pLeft.add(pMenu);
		pHeader.add(lbl1, BorderLayout.WEST);
		pMain.add(pHeader);
		pMain.add(pContent);
		this.add(pLeft, BorderLayout.WEST);
		this.add(pMain, BorderLayout.CENTER);
		
		
	}
	
	private JButton createButtonMenu(String titleButton, ImageIcon image) {
		Image img = image.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btn = new JButton(titleButton, new ImageIcon(img));
		btn.setBackground(new Color(224,220,220));
		btn.setOpaque(true);
		btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false); // Loại bỏ trạng thái focus
        btn.setPreferredSize(new Dimension((int) ((int)widthFrame*0.093), 40));
        btn.setFont(new Font("Arial", Font.BOLD, 10));
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
				System.gc();
				pContent.add(new Gui_TrangChu(this.nv, pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("TRANG CHỦ");
			}
			actButtonBack = btnTrangChu;
		}else if (act.equals(btnSanPham)) {
			if (!actButtonBack.equals(btnSanPham)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnSanPham.setBackground(new Color(224,255,255));
				pContent.removeAll();
				System.gc();
				pContent.add(new Gui_SanPham(nv, pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("SẢN PHẨM");
			}
			actButtonBack = btnSanPham;
		}else if (act.equals(btnHoaDon)) {
			if (!actButtonBack.equals(btnHoaDon)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnHoaDon.setBackground(new Color(224,255,255));
				pContent.removeAll();				
				System.gc();
				pContent.add(new Gui_HoaDon(pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("HÓA ĐƠN");
			}
			actButtonBack = btnHoaDon;
		}else if (act.equals(btnNhanVien)) {
			if (!actButtonBack.equals(btnNhanVien)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnNhanVien.setBackground(new Color(224,255,255));
				pContent.removeAll();
				System.gc();
				pContent.add(new Gui_NhanVien(pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("NHÂN VIÊN");
			}
			actButtonBack = btnNhanVien;
		}else if (act.equals(btnTaiKhoan)) {
			if (!actButtonBack.equals(btnTaiKhoan)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnTaiKhoan.setBackground(new Color(224,255,255));
				pContent.removeAll();
				System.gc();
				pContent.add(new Gui_TaiKhoan(nv, pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("TÀI KHOẢN");
			}
			actButtonBack = btnTaiKhoan;
		}else if (act.equals(btnKhachHang)) {
			if (!actButtonBack.equals(btnKhachHang)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnKhachHang.setBackground(new Color(224,255,255));
				pContent.removeAll();
				System.gc();
				pContent.add(new Gui_KhachHang(pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("KHÁCH HÀNG");
			}
			actButtonBack = btnKhachHang;
		}else if (act.equals(btnKhuyenMai)) {
			if (!actButtonBack.equals(btnKhuyenMai)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnKhuyenMai.setBackground(new Color(224,255,255));
				pContent.removeAll();
				System.gc();
				pContent.add(new Gui_KhuyenMai(pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("KHUYẾN MÃI");
			}
			actButtonBack = btnKhuyenMai;
		}else if (act.equals(btnThongKe)) {
			if (!actButtonBack.equals(btnSanPham)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnThongKe.setBackground(new Color(224,255,255));
				pContent.removeAll();
				System.gc();
				pContent.add(new Gui_ThongKe(pContent.getWidth(), pContent.getHeight()));
				lbl1.setText("THỐNG KÊ");
			}
			actButtonBack = btnThongKe;
		}else if (act.equals(btnHuongDan)) {
			if (!actButtonBack.equals(btnHuongDan)) {
				actButtonBack.setBackground(new Color(224,220,220));
				btnHuongDan.setBackground(new Color(224,255,255));
				pContent.removeAll();
				System.gc();
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
	                    new Gui_DangNhap();
	                    dispose();
	    				System.gc();
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
	public void phimTat() {
		InputMap inputMap = pContent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap actionMap = pContent.getActionMap();
		
	    KeyStroke keyTrangChu = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
	    inputMap.put(keyTrangChu, "trangChu");
	    actionMap.put("trangChu", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            btnTrangChu.doClick(); 
	        }
	    });
	    
	    KeyStroke keySanPham = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0);
	    inputMap.put(keySanPham, "sanPham");
	    actionMap.put("sanPham", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            btnSanPham.doClick(); 
	        }
	    });
	    KeyStroke keyHoaDon = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0);
	    inputMap.put(keyHoaDon, "hoaDon");
	    actionMap.put("hoaDon", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            btnHoaDon.doClick();
	        }
	    });
	    KeyStroke keyNhanVien = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0);
	    inputMap.put(keyNhanVien, "nhanVien");
	    actionMap.put("nhanVien", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            btnNhanVien.doClick(); 
	        }
	    });
	    KeyStroke keyKhuyenMai = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
	    inputMap.put(keyKhuyenMai, "khuyenMai");
	    actionMap.put("khuyenMai", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            btnKhuyenMai.doClick();
	        }
	    });
	    KeyStroke keyKhachHang = KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0);
	    inputMap.put(keyKhachHang, "khachHang");
	    actionMap.put("khachHang", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            btnKhachHang.doClick();
	        }
	    });
	    KeyStroke keyThongKe = KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0);
	    inputMap.put(keyThongKe, "thongKe");
	    actionMap.put("thongKe", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            btnThongKe.doClick(); 
	        }
	    });
	    KeyStroke keyHuongDan = KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0);
	    inputMap.put(keyHuongDan, "huongDan");
	    actionMap.put("huongDan", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {     
	            btnHuongDan.doClick(); 
	        }
	    });
	    KeyStroke keyDangXuat = KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
	    inputMap.put(keyDangXuat, "dangXuat");
	    actionMap.put("dangXuat", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {               
	            btnDangXuat.doClick(); 
	        }
	    });
	}
	public NhanVien getNhanVien() {
		return nv;
	}
}
