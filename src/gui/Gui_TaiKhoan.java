package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.Dao_TaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;

public class Gui_TaiKhoan extends JPanel implements ActionListener{

	
	private int widthComp;
	private int heightComp;
	private JPanel pane1;
	private JLabel lbl1;
	private JLabel lblTen;
	private JTextField txtTen;
	private JLabel lblPass;
	private JPasswordField txtPass;
	private JPasswordField txtDoiPass;
	private JLabel lblDoiPass;
	private JButton btnXacNhan;
	private JButton btnHuy;
	private JPanel pane2;
	private JPanel pane3;
    private JCheckBox showPasswordCheckbox; // Thêm JCheckBox để hiển thị mật khẩu
    private String maNV;
	private JLabel lblDoiPass1;
	private JPasswordField txtDoiPass1;
	private String tenTaiKhoan;
	private NhanVien nv;

	public Gui_TaiKhoan(NhanVien nv, int width, int height) {
		this.nv = nv;
		widthComp = width;
		heightComp = height;
		initCompoent();
	}
	public void initCompoent() {
		pane1 = new JPanel();
		pane1 = new JPanel(new BorderLayout());
		pane1.setPreferredSize(new Dimension((int)(widthComp*1),(int) (heightComp*0.95)));
		lbl1 = new JLabel("ĐỔI MẬT KHẨU");
		lbl1.setFont(new Font("Arial", Font.BOLD, 35));
		
        lblTen = new JLabel("Tên tài khoản");
		lblTen.setFont(new Font("Arial", Font.BOLD, 15));
        txtTen = new JTextField(15);
        txtTen.setEnabled(false);
        txtTen.setText(nv.getMaNV());
        lblPass = new JLabel("Mật khẩu hiện tại");
		lblPass.setFont(new Font("Arial", Font.BOLD, 15));
        txtPass = new JPasswordField(15);

        lblDoiPass = new JLabel("Mật khẩu mới");
		lblDoiPass.setFont(new Font("Arial", Font.BOLD, 15));
        txtDoiPass = new JPasswordField(15);
        lblDoiPass1 = new JLabel("Nhập lại mật khẩu mới");
		lblDoiPass1.setFont(new Font("Arial", Font.BOLD, 15));
        txtDoiPass1 = new JPasswordField(15);
        btnXacNhan = new JButton("Xác Nhận");
        btnHuy = new JButton("Hủy");
        showPasswordCheckbox = new JCheckBox("Hiển thị mật khẩu"); 
        showPasswordCheckbox.setFont(new Font("Arial", Font.BOLD, 15));
        
		pane2 = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pane2.add(lbl1,gbc);

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
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        pane2.add(lblDoiPass1 , gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        pane2.add(txtDoiPass1, gbc);

        add(pane2);
        
        pane3 = new JPanel();
        pane3.add(btnXacNhan);
        pane3.add(btnHuy);
        pane3.add(showPasswordCheckbox); 
        add(pane3);

        pane1.add(pane2 , BorderLayout.NORTH);
        pane1.add(pane3, BorderLayout.CENTER);
        add(pane1);

        btnHuy.addActionListener(this);
        btnXacNhan.addActionListener(this);
        showPasswordCheckbox.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    ((JPasswordField) txtPass).setEchoChar((char) 0); 
                    ((JPasswordField) txtDoiPass).setEchoChar((char) 0);
                    ((JPasswordField) txtDoiPass1).setEchoChar((char) 0);
                } else {
                    ((JPasswordField) txtPass).setEchoChar('*'); 
                    ((JPasswordField) txtDoiPass).setEchoChar('*');
                    ((JPasswordField) txtDoiPass1).setEchoChar('*');
                }
            }
        });
        
	}
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnXacNhan)) {
            String tenTaiKhoan = txtTen.getText();
            char[] matKhauHienTai = txtPass.getPassword();
            char[] matKhauMoi = txtDoiPass.getPassword();
            char[] matKhauMoiNhapLai = txtDoiPass1.getPassword();
            if (tenTaiKhoan.isEmpty() || matKhauHienTai.length == 0 || matKhauMoi.length == 0 || matKhauMoiNhapLai.length == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!Arrays.equals(matKhauMoi, matKhauMoiNhapLai)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu mới và mật khẩu nhập lại không khớp nhau", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            TaiKhoan taiKhoan = new TaiKhoan(tenTaiKhoan, new String(matKhauHienTai));
            NhanVien nv = new Dao_TaiKhoan().authenticateTaiKhoanForNhanVien(taiKhoan);
            
            if (nv == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại tên tài khoản và mật khẩu hiện tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String matKhauMoiString = new String(matKhauMoi);
            boolean doiMatKhauThanhCong = false;
			try {
				doiMatKhauThanhCong = new Dao_TaiKhoan().changePassword(tenTaiKhoan, new String(matKhauHienTai), matKhauMoiString);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            if (doiMatKhauThanhCong) {
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
