package gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
public class Gui_DangNhap extends JFrame {
    public Gui_DangNhap() {
        this.setTitle("Hệ thống Quản lý Nhà Thuốc Ân Cần");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBackground(new Color(40, 156, 164));
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(40,156,164));
        initComponents();
        this.setVisible(true);
        initComponents();
    }
                       
    private void initComponents() {

        lblTittle = new JLabel();
        txtDangNhap = new JTextField();
        txtMatKhau = new JTextField();
        btnDangNhap = new JButton();
        btnQuenMK = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ thống Quản lý Nhà thuốc Ân Cần");

        lblTittle.setFont(new Font("Arial", 1, 36)); 
        lblTittle.setForeground(new Color(255, 255, 255));
        lblTittle.setIcon(new ImageIcon(("images/logo1.png"))); 
        lblTittle.setText(" Nhà Thuốc Ân Cần");

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

        jLabel1.setIcon(new ImageIcon(("images/user.png"))); 

        jLabel2.setIcon(new ImageIcon(("images/pass.png"))); 

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
    
    
    
    
    public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        // Thực hiện các hoạt động giao diện người dùng ở đây
		        // Ví dụ: tạo và hiển thị một JFrame mới
		    	new Gui_DangNhap();
		    }
		});	}
    private JButton btnDangNhap;
    private JButton btnQuenMK;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel lblTittle;
    private JTextField txtDangNhap;
    private JTextField txtMatKhau;                 
}
