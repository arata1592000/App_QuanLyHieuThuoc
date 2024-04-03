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
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
public class Gui_DangNhap extends JFrame {

    public Gui_DangNhap() {
    	this.setTitle("Hệ thống Quản lý Nhà Thuốc Ân Cần");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBackground(new Color(40, 156, 164));
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(40,156,164));
        initComponents();
        this.setVisible(true);
    }
                      
    private void initComponents() {

        lbl1 = new JLabel();
        lblTenDangNhap = new JLabel();
        lblMatKhau = new JLabel();
        txtTenDangNhap = new JTextField();
        txtMatKhau = new JTextField();
        btnDangNhap = new JButton();
        btnQuenMatKhau = new JButton();

        lbl1.setFont(new Font("Arial", 1, 36)); 
        lbl1.setText("Nhà Thuốc Ân Cần");

        lblTenDangNhap.setFont(new Font("Arial", 1, 14)); 
        lblTenDangNhap.setText("Tên Đăng Nhập :");

        lblMatKhau.setFont(new Font("Arial", 1, 14)); 
        lblMatKhau.setText("Mật Khẩu :");
        
        btnDangNhap.setText("Đăng Nhập");

        btnQuenMatKhau.setText("Quên mật khẩu ?");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenDangNhap)
                            .addComponent(lblMatKhau))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenDangNhap)
                            .addComponent(txtMatKhau, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDangNhap, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnQuenMatKhau)
                        .addGap(11, 11, 11)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(lbl1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenDangNhap)
                    .addComponent(txtTenDangNhap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatKhau)
                    .addComponent(txtMatKhau, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQuenMatKhau)
                    .addComponent(btnDangNhap))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        lbl1.getAccessibleContext().setAccessibleName("");

        pack();
    }                      

    private JButton btnDangNhap;
    private JButton btnQuenMatKhau;
    private JLabel lbl1;
    private JLabel lblMatKhau;
    private JLabel lblTenDangNhap;
    private JTextField txtMatKhau;
    private JTextField txtTenDangNhap; 
    
    public static void main(String args[]) {
       
                new Gui_DangNhap();
    }
                    
               
}