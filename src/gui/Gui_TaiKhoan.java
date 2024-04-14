package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;

import dao.Dao_TaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;
//
//public class Gui_TaiKhoan extends JPanel implements ActionListener{
//	private JTextField txtPass;
//	private JButton btnXacNhan;
//	private int widthComp;
//	private int heightComp;
//	private JButton btnHuy;
//	private JPanel pane2;
//	private JPanel panel3;
//	private JLabel lblDoi;
//	private JLabel lblTen;
//	private JPanel pane1;
//	private JTextField txtTen;
//	private JLabel lblPass;
//	private JLabel lblDoiPass;
//	private JTextField txtDoiPass;
//	private JLabel lblNhapLai;
//	private JTextField txtNhapLai;
//	private JLabel lblChucVu;
//	private JLabel lblCV;
//	private JTextField txtCV;
//	private String maNV;
//
//	public Gui_TaiKhoan(String maNV) {
//		this.widthComp = 1000;
//		this.heightComp =500;
//		this.maNV = maNV;
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
//
//		pane1 = new JPanel(new BorderLayout());
//
//		lblDoi = new JLabel("Đổi Mật Khẩu");
//		lblDoi.setFont(new Font("Arial", Font.BOLD,26));
//		
//		lblTen = new JLabel("Tên tài khoản");
//		txtTen = new JTextField(15);
//		txtTen.setEnabled(false);
//		lblPass = new JLabel("Mật khẩu");
//		txtPass = new JPasswordField(15);
//		txtPass.setText("1111");
//		lblDoiPass = new JLabel("Mật khẩu mới");
//		txtDoiPass = new JPasswordField(15);
//		btnXacNhan = new JButton("Xác Nhận");
//		btnHuy = new JButton("Hủy");
//		
//		pane2 = new JPanel();
//        pane2 = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 20, 5, 0);
//        gbc.anchor = GridBagConstraints.WEST;
//        
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        pane2.add(lblDoi,gbc);
//        
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        pane2.add(lblTen , gbc);
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        pane2.add(txtTen , gbc);
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        pane2.add(lblPass , gbc);
//        gbc.gridx = 1;
//        gbc.gridy = 2;
//        pane2.add(txtPass,gbc);
//        
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        pane2.add(lblDoiPass , gbc);
//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        pane2.add(txtDoiPass, gbc);
//		
//        
//        add(pane2);
//        
//        panel3 = new JPanel();        
//        panel3.add(btnXacNhan);
//        panel3.add(btnHuy);
//        add(panel3);
//        
//        pane1.add(pane2 , BorderLayout.NORTH);
//        pane1.add(panel3, BorderLayout.SOUTH);
//        add(pane1);
//        
//        btnHuy.addActionListener(this);
//        btnXacNhan.addActionListener(this);
//        setMaNV(maNV);
//	}
//
//	private void setMaNV(String maNV2) {
//		// TODO Auto-generated method stub
//		txtTen.setText(maNV);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		Object o = e.getSource();
//		if(o.equals(btnHuy)) {
//			JLayeredPane layeredPane = (JLayeredPane) getParent();
//            layeredPane.remove(Gui_TaiKhoan.this);
//            // Cập nhật lại giao diện
//            layeredPane.validate();
//            layeredPane.repaint();
//		}
//		if (o.equals(btnXacNhan)) {
//	        String tenTaiKhoan = txtTen.getText();
//	        String matKhauHienTai = txtPass.getText();
//	        String matKhauMoi = txtDoiPass.getText();
//
//	        // Kiểm tra xem người dùng đã nhập mật khẩu mới hay chưa
//	        if (matKhauMoi.isEmpty()) {
//	        	JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu");
//	            return; // Dừng lại nếu mật khẩu mới chưa được nhập
//	        }
//
//	        // Thực hiện cập nhật mật khẩu mới
//	        Dao_TaiKhoan daoTaiKhoan = new Dao_TaiKhoan();
//	        boolean result = daoTaiKhoan.changePassword(tenTaiKhoan, matKhauMoi);
//
//	        // Hiển thị thông báo kết quả
//	        if (result) {
////	            System.out.println("Đổi mật khẩu thành công!");
//	        	JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
//	        } else {
//	        	JOptionPane.showMessageDialog(this, "Đổi mật khẩu không thành công");
//	        }
//	    }
//	}
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui_TaiKhoan extends JPanel implements ActionListener{
    private JTextField txtPass;
    private JButton btnXacNhan;
    private int widthComp;
    private int heightComp;
    private JButton btnHuy;
    private JPanel pane2;
    private JPanel panel3;
    private JLabel lblDoi;
    private JLabel lblTen;
    private JPanel pane1;
    private JTextField txtTen;
    private JLabel lblPass;
    private JLabel lblDoiPass;
    private JTextField txtDoiPass;
    private JLabel lblNhapLai;
    private JTextField txtNhapLai;
    private JLabel lblChucVu;
    private JLabel lblCV;
    private JTextField txtCV;
    private String maNV;
    private JCheckBox showPasswordCheckbox; // Thêm JCheckBox để hiển thị mật khẩu

    public Gui_TaiKhoan(String maNV) {
        this.widthComp = 1000;
        this.heightComp =500;
        this.maNV = maNV;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        pane1 = new JPanel(new BorderLayout());

        lblDoi = new JLabel("Đổi Mật Khẩu");
        lblDoi.setFont(new Font("Arial", Font.BOLD,26));

        lblTen = new JLabel("Tên tài khoản");
        txtTen = new JTextField(15);
        txtTen.setEnabled(false);
        lblPass = new JLabel("Mật khẩu");
        txtPass = new JPasswordField(15);
        txtPass.setText("1111");
        lblDoiPass = new JLabel("Mật khẩu mới");
        txtDoiPass = new JPasswordField(15);
        btnXacNhan = new JButton("Xác Nhận");
        btnHuy = new JButton("Hủy");
        showPasswordCheckbox = new JCheckBox("Hiển thị mật khẩu"); 

        pane2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pane2.add(lblDoi,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pane2.add(lblTen , gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        pane2.add(txtTen , gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        pane2.add(lblPass , gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        pane2.add(txtPass,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pane2.add(lblDoiPass , gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        pane2.add(txtDoiPass, gbc);

        add(pane2);

        panel3 = new JPanel();
        panel3.add(btnXacNhan);
        panel3.add(btnHuy);
        panel3.add(showPasswordCheckbox); 

        add(panel3);

        pane1.add(pane2 , BorderLayout.NORTH);
        pane1.add(panel3, BorderLayout.SOUTH);
        add(pane1);

        btnHuy.addActionListener(this);
        btnXacNhan.addActionListener(this);
        showPasswordCheckbox.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    ((JPasswordField) txtPass).setEchoChar((char) 0); 
                    ((JPasswordField) txtDoiPass).setEchoChar((char) 0);
                } else {
                    ((JPasswordField) txtPass).setEchoChar('*'); 
                    ((JPasswordField) txtDoiPass).setEchoChar('*');
                }
            }
        });
        setMaNV(maNV);
    }

    private void setMaNV(String maNV2) {
        txtTen.setText(maNV);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnHuy)) {
            JLayeredPane layeredPane = (JLayeredPane) getParent();
            layeredPane.remove(Gui_TaiKhoan.this);
            layeredPane.validate();
            layeredPane.repaint();
        }
        if (o.equals(btnXacNhan)) {
            String tenTaiKhoan = txtTen.getText();
            String matKhauHienTai = txtPass.getText();
            String matKhauMoi = txtDoiPass.getText();

            if (matKhauMoi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu");
                return;
            }

            Dao_TaiKhoan daoTaiKhoan = new Dao_TaiKhoan();
            boolean result = daoTaiKhoan.changePassword(tenTaiKhoan, matKhauMoi);

            if (result) {
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu không thành công");
            }
        }
    }
}
