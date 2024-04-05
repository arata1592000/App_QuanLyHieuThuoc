package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.ImageView;

import com.toedter.calendar.JDateChooser;

public class Gui_NhanVien extends JPanel{

	private int widthComp;
	private int heightComp;
	private JPanel pNorth;
	private JPanel pCenter;
	private JPanel pTable;
	private JPanel pInfor;
	private JLabel lblMa;
	private JTextField txtMa;
	private JLabel lblHoTen;
	private JTextField txtHoTen;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private ButtonGroup gioiTinhGroup;
	private JPanel gioiTinhPanel;
	private JLabel lblGioiTinh;
	private JLabel lblSDT;
	private JTextField txtSDT;
	private Component lblNS;
	private JDateChooser ngaySinhDate;
	private JLabel lblNL;
	private JDateChooser ngayLamDate;
	private JComboBox comboBoxChucVu;
	private JLabel lblCV;
	private JLabel lblCC;
	private JTextField txtCC;
	private JLabel lblDC;
	private JTextField txtDC;
	private JButton btnImage;
	private JPanel pImage;
	private JComboBox comboBoxCV;
	private JComboBox comboBoxTT;
	private JButton btnTim;
	private JTextField txtTim;
	private JButton btn1; 
    private JButton btn2;
    private JButton btn3; 
    private JButton btn4;
	private JPanel row1;
	private JPanel row2;
	private JLabel lblTT;
	private JLabel lblDSNV;
	private JTable tableModel;
	private DefaultTableModel dataModel;
	private JScrollPane scroll;
	private JButton btnThemAnh; 



	public Gui_NhanVien(int width, int height) {
		widthComp = width;
		heightComp = height;
		
		pImage = new JPanel();
		pNorth = new JPanel();
		pCenter = new JPanel();
		pTable = new JPanel();
		
        setLayout(new BorderLayout());
		pInfor = new JPanel();
		pInfor.setLayout(new GridBagLayout());
		GridBagConstraints constraintsCustomer = new GridBagConstraints();
        constraintsCustomer.insets = new Insets(5,30, 5,30);
        
        btnThemAnh = new JButton("Thêm Ảnh");
        constraintsCustomer.gridx = 0;
        constraintsCustomer.gridy = 4;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(btnThemAnh, constraintsCustomer);
        btnThemAnh.setForeground(Color.WHITE);
        btnThemAnh.setFont(new Font("Arial", Font.BOLD, 15));
        btnThemAnh.setBackground(new Color(40,156,164));
        btnThemAnh.setOpaque(true);
        btnThemAnh.setContentAreaFilled(true);
        btnThemAnh.setBorderPainted(false);
        btnThemAnh.setFocusPainted(false);
        
        lblMa = new JLabel("Mã nhân viên:");
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblMa, constraintsCustomer);
        txtMa= new JTextField(13);
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtMa, constraintsCustomer);
        
        lblHoTen = new JLabel("Họ và tên:");
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 1;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblHoTen, constraintsCustomer);
        txtHoTen = new JTextField(13);
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 1;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtHoTen, constraintsCustomer);
        
        lblGioiTinh = new JLabel("Giới tính:");
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 2; 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblGioiTinh, constraintsCustomer);
        radNam = new JRadioButton("Nam");
        radNu = new JRadioButton("Nữ");
        gioiTinhGroup = new ButtonGroup();
        gioiTinhGroup.add(radNam);
        gioiTinhGroup.add(radNu);
        pInfor.add(lblGioiTinh, constraintsCustomer);
        gioiTinhPanel = new JPanel();
        gioiTinhPanel.add(radNam);
        gioiTinhPanel.add(radNu);
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 2;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(gioiTinhPanel, constraintsCustomer);
        
        lblSDT = new JLabel("Số Điện Thoại:");
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 3; 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblSDT,constraintsCustomer);
        txtSDT = new JTextField(13);
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 3;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtSDT,constraintsCustomer);
        
        lblNS = new JLabel("Ngày sinh:");
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 4; // 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblNS,constraintsCustomer);
        ngaySinhDate = new JDateChooser();
        ngaySinhDate.setPreferredSize(new Dimension(170,25));
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 4;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(ngaySinhDate,constraintsCustomer);
        
        lblNL = new JLabel("Ngày vào làm:");
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 0; 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblNL,constraintsCustomer);
        ngayLamDate  = new JDateChooser();
        ngayLamDate.setPreferredSize(new Dimension(170,25));
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(ngayLamDate,constraintsCustomer);
        
        lblCV = new JLabel("Chức vụ");
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 1;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblCV,constraintsCustomer);
        comboBoxChucVu = new JComboBox<>(new String[]{"Nhân Viên", "Quản lý"});
        comboBoxChucVu.setPreferredSize(new Dimension(135,25));
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 1; 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(comboBoxChucVu, constraintsCustomer);
        
        lblCC = new JLabel("CCCD:");
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 2;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblCC, constraintsCustomer);
        txtCC = new JTextField(13);
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 2;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtCC, constraintsCustomer);
        
        lblDC = new JLabel("Địa Chỉ:");
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 3;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblDC, constraintsCustomer);
        txtDC = new JTextField(13);
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 3;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtDC,constraintsCustomer);
        
//        pImage.setBackground(Color.WHITE);
//        pImage.add(btnThemAnh);
        pNorth.add(pImage,BorderLayout.WEST);
        pNorth.add(pInfor,BorderLayout.WEST);
        
		row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
		row1.setBackground(Color.WHITE); 
		btn1 = new JButton("Thêm nhân viên");
		btn1.setFont(new Font("Arial", Font.BOLD, 15));
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(new Color(40,156,164));
		btn1.setOpaque(true);
		btn1.setContentAreaFilled(true);
        btn1.setBorderPainted(false);
        btn1.setFocusPainted(false);
	    btn2 = new JButton("Sửa nhân viên");
		btn2.setFont(new Font("Arial", Font.BOLD, 15));
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(new Color(40,156,164));
		btn2.setOpaque(true);
		btn2.setContentAreaFilled(true);
        btn2.setBorderPainted(false);
        btn2.setFocusPainted(false);
	    btn3 = new JButton("Lưu");
		btn3.setFont(new Font("Arial", Font.BOLD, 15));
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(new Color(40,156,164));
		btn3.setOpaque(true);
		btn3.setContentAreaFilled(true);
        btn3.setBorderPainted(false);
        btn3.setFocusPainted(false);
	    btn4 = new JButton("Nhập lại");
		btn4.setFont(new Font("Arial", Font.BOLD, 15));
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(new Color(40,156,164));
		btn4.setOpaque(true);
		btn4.setContentAreaFilled(true);
        btn4.setBorderPainted(false);
        btn4.setFocusPainted(false);
		row1.add(btn1);
		row1.add(btn2);
		row1.add(btn3);
		row1.add(btn4);

		row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20));
        row2.setBackground(Color.WHITE); 
        lblCV = new JLabel("Lọc theo chức vụ");
        comboBoxCV = new JComboBox<>(new String[]{"Nhân Viên", "Quản Lý"});
        comboBoxCV.setPreferredSize(new Dimension(135,25));
        lblTT = new JLabel("Lọc theo trạng thái");
        comboBoxTT = new JComboBox<>(new String[]{"Đang làm việc", "Tạm thời nghỉ"});
        comboBoxTT.setPreferredSize(new Dimension(135,25));
        btnTim = new JButton("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Arial", Font.BOLD, 15));
		btnTim.setBackground(new Color(40,156,164));
		btnTim.setOpaque(true);
		btnTim.setContentAreaFilled(true);
        btnTim.setBorderPainted(false);
        btnTim.setFocusPainted(false);
        txtTim = new JTextField("Nhập mã nhân viên",20);
        txtTim.setPreferredSize(new Dimension(135,25));
    	txtTim.addFocusListener(new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			if (txtTim.getText().equals("")) {
            	txtTim.setText("Nhập mã nhân viên");
            }
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (txtTim.getText().equals("Nhập mã nhân viên")) {
            	txtTim.setText("");
            	}
			}
    	});
        row2.add(lblCV);
        row2.add(comboBoxCV);
        row2.add(Box.createHorizontalStrut(200)); 
        row2.add(lblTT);
        row2.add(comboBoxTT);
        row2.add(Box.createHorizontalStrut(200)); 
        row2.add(txtTim);
        row2.add(btnTim);
        pCenter.add(row2);
        pCenter.setLayout(new BorderLayout());
        pCenter.add(row1, BorderLayout.NORTH); 
        pCenter.add(row2, BorderLayout.CENTER);
        
        
		pTable.setLayout(new FlowLayout());
		pTable.setPreferredSize(new Dimension((int) (widthComp*0.95),(int) (heightComp*0.9)));
        lblDSNV = new JLabel("Danh sách nhân viên");
		lblDSNV.setFont(new Font("Arial", Font.ITALIC, 30));
		String headers[] = {"Mã nhân viên", "Họ và tên", "Giới tính", "SDT", "Ngày sinh","Ngày vào làm","Chức vụ","CCCD","Địa chỉ","Trạng thái"};
		Object[][] data = {
                {"NV001", "John Doe", "Nam", "012343425", "12/03/2001","25/09/2023","Quản lý","079xxxxxxx","Củ Chi","Làm việc"},
                {"NV001", "John Doe", "Nam", "012343425", "12/03/2001","25/09/2023","Quản lý","079xxxxxxx","Củ Chi","Làm việc"},
                {"NV001", "John Doe", "Nam", "012343425", "12/03/2001","25/09/2023","Quản lý","079xxxxxxx","Củ Chi","Làm việc"},
                {"NV001", "John Doe", "Nam", "012343425", "12/03/2001","25/09/2023","Quản lý","079xxxxxxx","Củ Chi","Làm việc"},
                {"NV001", "John Doe", "Nam", "012343425", "12/03/2001","25/09/2023","Quản lý","079xxxxxxx","Củ Chi","Làm việc"},
                
        };
		dataModel = new DefaultTableModel(data,headers);
		tableModel = new JTable(dataModel);
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 15));
		tableModel.setRowHeight(25);
		tableModel.setModel(dataModel);
		scroll = new JScrollPane(tableModel);
		scroll.setPreferredSize(new Dimension((int)(widthComp*0.9),(int) (heightComp*0.5)));
		pTable.add(lblDSNV, BorderLayout.NORTH); 
		pTable.add(scroll, BorderLayout.CENTER); 
		
		this.add(pNorth,BorderLayout.NORTH);
        this.add(pCenter,BorderLayout.CENTER);
        this.add(pTable,BorderLayout.SOUTH);
	}

}
