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

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
	private JPanel panel3;
    private JCheckBox showPasswordCheckbox; // Thêm JCheckBox để hiển thị mật khẩu
    private String maNV;


	public Gui_TaiKhoan(int width, int height) {
		widthComp = width;
		heightComp = height;
		initCompoent();
	}
	public void initCompoent() {
		pane1 = new JPanel();
		pane1.setBackground(Color.WHITE);
		pane1.setForeground(Color.WHITE);
		pane1 = new JPanel(new BorderLayout());
		lbl1 = new JLabel("ĐỔI MẬT KHẨU");
		lbl1.setFont(new Font("Arial", Font.BOLD, 25));
		
        lblTen = new JLabel("Tên tài khoản");
        txtTen = new JTextField(15);
        txtTen.setEnabled(false);
        lblPass = new JLabel("Mật khẩu");
        txtPass = new JPasswordField(15);

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

        add(pane2);

        panel3 = new JPanel();
        panel3.add(btnXacNhan);
        panel3.add(btnHuy);
        panel3.add(showPasswordCheckbox); 

        add(panel3);

        pane1.add(pane2 , BorderLayout.NORTH);
        pane1.add(panel3, BorderLayout.SOUTH);
        pane1.add(Box.createHorizontalStrut(300));
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
    private void setMaNV(String maNV) {
        txtTen.setText(maNV);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
