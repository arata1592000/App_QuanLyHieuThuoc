package gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import dao.Dao_NhanVien;
import dao.Dao_TaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;
import utils.AES;
public class Gui_DangNhap extends JFrame {
    private ImageIcon image;
	private Image img;
	public Gui_DangNhap() {
        this.setTitle("Hệ thống Quản lý Nhà Thuốc Ân Cần");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBackground(new Color(40, 156, 164));
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(40,156,164));
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setAutoCreateAccount();
    }
	
	private void setAutoCreateAccount() {
		if ((new Dao_TaiKhoan()).readFromTaiKhoanSQL() == null) {
        	try {
				if ((new Dao_NhanVien()).createNhanVienADMIN()) {
					JOptionPane.showMessageDialog(null, "Hệ thống kiểm tra thấy không có bất kỳ tài khoản nào trong cơ sở dữ liệu.\n Tự động tạo tên tài khoản: NV000000\n Tự động tạo mật khẩu: 1111");
				} else {
					JOptionPane.showMessageDialog(null, "Hệ thống kiểm tra thấy không có bất kỳ tài khoản nào trong cơ sở dữ liệu.\n Lỗi hệ thống: \"Không thể tạo tài khoản ADMIN\"");

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
	}
                       
    private void initComponents() {

        lblTittle = new JLabel();
        txtDangNhap = new JTextField();
        txtMatKhau = new JPasswordField();
        btnDangNhap = new JButton();
        btnQuenMK = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ thống Quản lý Nhà thuốc Ân Cần");

        lblTittle.setFont(new Font("Arial", 1, 36)); 
        lblTittle.setForeground(new Color(255, 255, 255));
        image = new ImageIcon(("images/logo.png"));
		img = image.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblTittle.setIcon(new ImageIcon(img)); 
        lblTittle.setText(" Nhà Thuốc Ân Cần");

        txtDangNhap.setText("NV241001");
        txtMatKhau.setText("1111");
        
        txtDangNhap.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                txtDangNhapFocusGained(evt);
            }
            public void focusLost(FocusEvent evt) {
                txtDangNhapFocusLost(evt);
            }
        });
        
        txtMatKhau.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                txtMatKhauFocusGained(evt);
            }
            public void focusLost(FocusEvent evt) {
                txtMatKhauFocusLost(evt);
            }
        });
        

        btnDangNhap.setBackground(new Color(0, 0, 0));
        btnDangNhap.setFont(new Font("Arial", 1, 14)); 
        btnDangNhap.setForeground(new Color(255, 255, 255));
        btnDangNhap.setText("Đăng nhập");

        btnQuenMK.setText("Quên mật khẩu ?");
        btnQuenMK.setOpaque(true);
        btnQuenMK.setFocusable(false);
        btnQuenMK.setBackground(new Color(40, 156, 164));

        image = new ImageIcon(("images/user.png"));
		img = image.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        jLabel1.setIcon(new ImageIcon(img)); 

        image = new ImageIcon(("images/lock.png"));
		img = image.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        jLabel2.setIcon(new ImageIcon(img)); 

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, GroupLayout.Alignment.TRAILING))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMatKhau)
                    .addComponent(txtDangNhap)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnDangNhap, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(btnQuenMK)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(lblTittle)
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblTittle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDangNhap))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMatKhau))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuenMK)
                    .addComponent(btnDangNhap))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        pack();
        
        txtDangNhap.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();	
				if (keyCode == KeyEvent.VK_ENTER) {
					btnDangNhap.doClick();
				}
			}
		});
        
        txtMatKhau.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();	
				if (keyCode == KeyEvent.VK_ENTER) {
					btnDangNhap.doClick();
				}
			}
		});
        
        
        btnDangNhap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tenDangNhap = txtDangNhap.getText();
				TaiKhoan tk = new TaiKhoan(tenDangNhap, txtMatKhau.getText());
				try {
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				NhanVien nv = (new Dao_TaiKhoan()).authenticateTaiKhoanForNhanVien(tk);
				if (nv != null && nv.getTrangThai().equals("Làm Việc")) {
			    	JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");			    	
			    	new Gui_Chinh(nv);
			    	dispose();
				}else if (!nv.getTrangThai().equals("Làm Việc")) {
					JOptionPane.showMessageDialog(null, "Bạn đã không còn làm việc ở cửa hàng");
				}else {
					JOptionPane.showMessageDialog(null, "Đăng nhập thất bại!");

				}
			}
		});

    }                     
    public String getDangNhap() {
        return txtDangNhap.getText();
    }

    private void txtDangNhapFocusGained(FocusEvent evt) {                                        
	if (txtDangNhap.getText().equals("Nhập tên đăng nhập")){
            txtDangNhap.setText("");}
    }                                       

    private void txtDangNhapFocusLost(FocusEvent evt) {                                      
        if (txtDangNhap.getText().equals("")) {
        		txtDangNhap.setText("Nhập tên đăng nhập");}
    }                                     
    private void txtMatKhauFocusGained(FocusEvent evt) {                                        
    	if (txtMatKhau.getText().equals("Nhập mật khẩu")){
    			txtMatKhau.setText("");}
    }                                       

    private void txtMatKhauFocusLost(FocusEvent evt) {                                      
    	if (txtMatKhau.getText().equals("")) {
    			txtMatKhau.setText("Nhập mật khẩu");}
    }        
    private JButton btnDangNhap;
    private JButton btnQuenMK;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel lblTittle;
    private JTextField txtDangNhap;
    private JPasswordField txtMatKhau;                 
}
