package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import javax.swing.JLayeredPane;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.text.html.ImageView;

import com.toedter.calendar.JDateChooser;

import dao.Dao_NhanVien;
import dao.Dao_TaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;
import gui.Gui_HoaDon.ButtonShowOrderDetailsEditor;
import gui.Gui_HoaDon.ButtonShowOrderDetailsRenderer;
import gui.Gui_HoaDon.ShowOrderDetailsButtonListener;

public class Gui_NhanVien extends JPanel implements ActionListener , MouseListener{

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
	private JButton btnAdd; 
    private JButton btnSua;
    private JButton btnLuu; 
    private JButton btnNhapLai;
	private JPanel row1;
	private JPanel row2;
	private JLabel lblTT;
	private JLabel lblDSNV;
	private JTable tableModel;
	private DefaultTableModel dataModel;
	private JScrollPane scroll;
	private JButton btnThemAnh;
	private JLabel lblTTLV;
	private JComboBox comboBoxTrangThai;
	private JPanel pSouth;
	private JLabel lblHinhAnh;
	private int widthFrame;
	private Image img;
	private Image resizedImg;
	private JButton btnTaiKhoan;
	private JLayeredPane layeredPane;
	private JPanel pContent; 
	private Gui_TaiKhoan guiTK;

	private boolean isGuiTKDisplayed = false;
	private byte[] anhByte;
	private String maNV;
	private String chucVu;
	private NhanVien nhanVien;
	private String tenTaiKhoan;
	private String matKhau;
	
	public Gui_NhanVien(int width, int height) {
		widthComp = width;
		heightComp = height;
		initCompoent();
        loadDataTable();

	}
	public void initCompoent() {
		pImage = new JPanel();
		pNorth = new JPanel();
		pCenter = new JPanel();
		pSouth = new JPanel();
		pTable = new JPanel();
		layeredPane = new JLayeredPane();
		pContent = new JPanel();
	
		layeredPane.setOpaque(true);
        layeredPane.setPreferredSize(new Dimension(widthComp, heightComp));
        layeredPane.setBackground(Color.WHITE);
        
        pContent.setOpaque(false);
        pContent.setBounds(0,0, widthComp, heightComp);
		pContent.setBackground(Color.WHITE);
        
        setLayout(new BorderLayout());
		pInfor = new JPanel();
		pInfor.setLayout(new GridBagLayout());
		pImage.setLayout(new BoxLayout(pImage, BoxLayout.Y_AXIS));
		GridBagConstraints constraintsCustomer = new GridBagConstraints();
        constraintsCustomer.insets = new Insets(5,20, 5,20);
        
        ImageIcon imageIcon = new ImageIcon("images/user1.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Chỉnh kích thước ảnh theo ý muốn
        ImageIcon resizedImageIcon = new ImageIcon(newImage);

        lblHinhAnh = new JLabel( resizedImageIcon, JLabel.CENTER);
        pImage.add(lblHinhAnh);
        btnThemAnh = new JButton("Thêm Ảnh");
        pImage.add(btnThemAnh, constraintsCustomer);
        btnThemAnh.setForeground(Color.WHITE);
        btnThemAnh.setFont(new Font("Arial", Font.BOLD, 15));
        btnThemAnh.setBackground(new Color(40,156,164));
        btnThemAnh.setOpaque(true);
        btnThemAnh.setContentAreaFilled(true);
        btnThemAnh.setBorderPainted(false);
        btnThemAnh.setFocusPainted(false);
        btnThemAnh.addActionListener(this);
        lblMa = new JLabel("Mã nhân viên:");
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblMa, constraintsCustomer);
        txtMa= new JTextField(16);        
        txtMa.setEnabled(false);
        txtMa.setText(new Dao_NhanVien().autoCreateMaNhanVien("Quản Lý"));

        
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtMa, constraintsCustomer);
        
        lblHoTen = new JLabel("Họ và tên:");
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 1;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblHoTen, constraintsCustomer);
        txtHoTen = new JTextField(16);
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
        txtSDT = new JTextField(16);
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
        comboBoxChucVu = new JComboBox<>(new String[]{"Quản Lý", "Nhân Viên"});
        comboBoxChucVu.setPreferredSize(new Dimension(168,25));

        comboBoxChucVu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		        txtMa.setText(new Dao_NhanVien().autoCreateMaNhanVien((String) comboBoxChucVu.getSelectedItem()));
			}
		});
        
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 1; 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(comboBoxChucVu, constraintsCustomer);
        
        lblCC = new JLabel("CCCD:");
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 2;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblCC, constraintsCustomer);
        txtCC = new JTextField(16);
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 2;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtCC, constraintsCustomer);
        
        lblDC = new JLabel("Địa Chỉ:");
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 3;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblDC, constraintsCustomer);
        txtDC = new JTextField(16);
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 3;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtDC,constraintsCustomer);
        
        lblTTLV = new JLabel("Trạng thái làm việc");
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 4;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblTTLV, constraintsCustomer);
        comboBoxTrangThai = new JComboBox<>(new String[]{"Làm Việc", "Không làm việc"});
        comboBoxTrangThai.setPreferredSize(new Dimension(168,25));
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 4; 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        
        pInfor.add(comboBoxTrangThai, constraintsCustomer);
        pNorth.add(pImage,BorderLayout.WEST);
        pNorth.add(pInfor,BorderLayout.WEST);
        
		row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
		row1.setBackground(Color.WHITE); 
		btnAdd = new JButton("Thêm nhân viên");
		btnAdd.setFont(new Font("Arial", Font.BOLD, 15));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(40,156,164));
		btnAdd.setOpaque(true);
		btnAdd.setContentAreaFilled(true);
		btnAdd.setBorderPainted(false);
		btnAdd.setFocusPainted(false);
	    btnSua = new JButton("Sửa nhân viên");
	    btnSua.setFont(new Font("Arial", Font.BOLD, 15));
	    btnSua.setForeground(Color.WHITE);
	    btnSua.setBackground(new Color(40,156,164));
	    btnSua.setOpaque(true);
	    btnSua.setContentAreaFilled(true);
	    btnSua.setBorderPainted(false);
	    btnSua.setFocusPainted(false);
	    btnLuu = new JButton("Xóa");
	    btnLuu.setFont(new Font("Arial", Font.BOLD, 15));
	    btnLuu.setForeground(Color.WHITE);
	    btnLuu.setBackground(new Color(40,156,164));
	    btnLuu.setOpaque(true);
	    btnLuu.setContentAreaFilled(true);
	    btnLuu.setBorderPainted(false);
	    btnLuu.setFocusPainted(false);
	    btnNhapLai = new JButton("Nhập lại");
	    btnNhapLai.setFont(new Font("Arial", Font.BOLD, 15));
	    btnNhapLai.setForeground(Color.WHITE);
		btnNhapLai.setBackground(new Color(40,156,164));
		btnNhapLai.setOpaque(true);
		btnNhapLai.setContentAreaFilled(true);
		btnNhapLai.setBorderPainted(false);
		btnNhapLai.setFocusPainted(false);
		btnTaiKhoan = new JButton("Tài Khoản");
		btnTaiKhoan.setFont(new Font("Arial", Font.BOLD, 15));
		btnTaiKhoan.setForeground(Color.WHITE);
		btnTaiKhoan.setBackground(new Color(40,156,164));
		btnTaiKhoan.setOpaque(true);
		btnTaiKhoan.setContentAreaFilled(true);
		btnTaiKhoan.setBorderPainted(false);
		btnTaiKhoan.setFocusPainted(false);
		row1.add(btnAdd);
		row1.add(btnSua);
		row1.add(btnLuu);
		row1.add(btnNhapLai);
		row1.add(btnTaiKhoan);
		
		btnAdd.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnNhapLai.addActionListener(this);
		btnTaiKhoan.addActionListener(this);
		
		row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20));
        row2.setBackground(Color.WHITE); 
        lblCV = new JLabel("Lọc theo chức vụ");
        comboBoxCV = new JComboBox<>(new String[]{"Nhân Viên", "Quản Lý"});
        comboBoxCV.setPreferredSize(new Dimension(135,25));
        lblTT = new JLabel("Lọc theo trạng thái");
        comboBoxTT = new JComboBox<>(new String[]{"Làm việc", "Không làm việc"});
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
        
        btnTim.addActionListener(this);
        comboBoxCV.addActionListener(this);
        comboBoxTT.addActionListener(this);
        
        pSouth.setPreferredSize(new Dimension((int) (widthComp*0.9),(int) (heightComp*0.45)));
		pSouth.setBackground(Color.WHITE);
		pTable.setLayout(new FlowLayout());
		pTable.setPreferredSize(new Dimension((int) (widthComp*1),(int) (heightComp*0.5)));
        lblDSNV = new JLabel("Danh sách nhân viên");
		lblDSNV.setFont(new Font("Arial", Font.ITALIC, 30));
		String headers[] = {"Mã nhân viên", "Họ và tên", "Giới tính", "SDT", "Ngày sinh","Ngày vào làm","Chức vụ","CCCD","Địa chỉ","Trạng thái"};
		dataModel = new DefaultTableModel(headers,0);
		tableModel = new JTable(dataModel);
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 15));
		tableModel.setRowHeight(25);
		tableModel.setModel(dataModel);
		tableModel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
		        // Lấy chỉ mục của dòng được chọn
		        int row = tableModel.getSelectedRow();
		        // Kiểm tra xem dòng đã được chọn chưa
		        if(row >= 0) {
			        txtMa.setText(tableModel.getValueAt(row, 0).toString());
			        txtHoTen.setText(tableModel.getValueAt(row, 1).toString());
			        String gioiTinh = tableModel.getValueAt(row, 2).toString();
			        if (gioiTinh.equals("Nam")) {
			            radNam.setSelected(true);
			        } else {
			            radNu.setSelected(true);
			        }
			        txtSDT.setText(tableModel.getValueAt(row, 3).toString());
			        LocalDate ngaySinh = (LocalDate) tableModel.getValueAt(row, 4);
			        ngaySinhDate.setDate(java.sql.Date.valueOf(ngaySinh));
			        LocalDate ngayVaoLam = (LocalDate) tableModel.getValueAt(row, 5);
			        ngayLamDate.setDate(java.sql.Date.valueOf(ngayVaoLam));
			        comboBoxChucVu.setSelectedItem(tableModel.getValueAt(row, 6).toString());
			        txtCC.setText(tableModel.getValueAt(row, 7).toString());
			        txtDC.setText(tableModel.getValueAt(row, 8).toString());
			        comboBoxTrangThai.setSelectedItem(tableModel.getValueAt(row, 9).toString());
		        }
		    }
		});
		scroll = new JScrollPane(tableModel);
		scroll.setPreferredSize(new Dimension((int)(widthComp*0.85),(int) (heightComp*0.35)));
		tableModel.addMouseListener(this);

		
		pTable.add(lblDSNV, BorderLayout.NORTH); 
		pTable.add(scroll, BorderLayout.CENTER); 
		pSouth.add(pTable);
		
		pContent.add(pNorth,BorderLayout.NORTH);
		pContent.add(pCenter,BorderLayout.CENTER);
		pContent.add(pSouth,BorderLayout.SOUTH);
		layeredPane.add(pContent, JLayeredPane.DEFAULT_LAYER);
        this.add(layeredPane);
        
        
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnAdd)) {
		    if (validData()) {
		        Dao_NhanVien daoNhanVien = new Dao_NhanVien();

//		        String maNV = daoNhanVien.autoCreateMaNhanVien();
		        
		        // Lấy thông tin khác từ giao diện
		        String maNV = txtMa.getText();
		        String hoTen = txtHoTen.getText();
		        String gioiTinh = radNam.isSelected() ? "Nam" : "Nữ";
		        String soDienThoai = txtSDT.getText();
		        LocalDate ngaySinh = ngaySinhDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        LocalDate ngayVaoLam = ngayLamDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        String chucVu = (String) comboBoxChucVu.getSelectedItem();
		        String soCCCD = txtCC.getText();
		        String diaChi = txtDC.getText();
		        String trangThai = (String) comboBoxTrangThai.getSelectedItem();

		        NhanVien nhanVien = new NhanVien(maNV, hoTen, gioiTinh, soDienThoai, ngaySinh, ngayVaoLam, chucVu, soCCCD, diaChi, trangThai, anhByte);

		        boolean success = daoNhanVien.addNhanVien(nhanVien);

		        if (success) {
		            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
		            addRowNhanVien(nhanVien);
		            clearFields();
		        } else {
		            JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!");
		        }
		    }
		}

		if(o.equals(btnThemAnh)) {
			themAnh();
		}
		if(o.equals(btnTim)) {
			String maNV = txtTim.getText();
			if(!maNV.equals("Nhập mã nhân viên")) {
				NhanVien nhanVien = (new Dao_NhanVien()).findNhanVienByMaNV(maNV);
				if(nhanVien != null) {
					dataModel.setRowCount(0);
	                addRowNhanVien(nhanVien);
				}else {
	                JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với mã " + maNV);
				}
			}
		}
		if(o.equals(btnLuu)) {
	        int selectRow = tableModel.getSelectedRow();
	        // Kiểm tra nếu có hàng được chọn
	        if (selectRow != -1) {
        		String maNV = (String) tableModel.getValueAt(selectRow, 0);
	        	if ((new Dao_NhanVien()).removeNhanVien(maNV)) {
		            ((DefaultTableModel) tableModel.getModel()).removeRow(selectRow);
	            // Xóa hàng từ  JTable
	        	}else {
		            JOptionPane.showMessageDialog(null, "Hệ thống đang xảy ra lỗi");
	        	}
	        } else {
	            // Hiển thị thông báo nếu không có hàng nào được chọn
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
	        }
		}
		if (o.equals(btnSua)) {
		    String maNV = txtMa.getText();
		    String hoTen = txtHoTen.getText();
		    String gioiTinh = radNam.isSelected() ? "Nam" : "Nữ";
		    String soDienThoai = txtSDT.getText();
		    LocalDate ngaySinh = ngaySinhDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    LocalDate ngayVaoLam = ngayLamDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    String chucVu = (String) comboBoxChucVu.getSelectedItem();
		    String soCCCD = txtCC.getText();
		    String diaChi = txtDC.getText();
		    String trangThai = (String) comboBoxTrangThai.getSelectedItem();

		    NhanVien nhanVien = new NhanVien(maNV, hoTen, gioiTinh, soDienThoai, ngaySinh, ngayVaoLam, chucVu, soCCCD, diaChi, trangThai);
		    Dao_NhanVien daoNhanVien = new Dao_NhanVien();
		    boolean success = daoNhanVien.updateNhanVien(nhanVien);

		    if (success) {
		        JOptionPane.showMessageDialog(this, "Sửa thông tin nhân viên thành công!");
		        updateRowNhanVien(nhanVien);
		    } else {
		        JOptionPane.showMessageDialog(this, "Sửa thông tin nhân viên thất bại!");
		    }
		}
		if (o.equals(btnNhapLai)) {
		    // Xóa nội dung của các JTextField
			clearFields();
		}
		if (o.equals(comboBoxCV)) {
			String selectedChucVu = (String) comboBoxCV.getSelectedItem();
	        filterByChucVu(selectedChucVu);
	    }
		if(o.equals(comboBoxTT)) {
			String selectedTrangThai = (String) comboBoxTT.getSelectedItem();
		    filterByTrangThai(selectedTrangThai);
		}

		if (o.equals(btnTaiKhoan)) {
		    int selectedRow = tableModel.getSelectedRow();
		    if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
		        String maNV = (String) tableModel.getValueAt(selectedRow, 0); // Lấy giá trị mã nhân viên từ cột 0
		        String chucVu = (String) tableModel.getValueAt(selectedRow, 6);
		        if (layeredPane.getComponentsInLayer(JLayeredPane.PALETTE_LAYER).length == 0) {
		            isGuiTKDisplayed = false;
		        }
		        if (!isGuiTKDisplayed) {
		            guiTK = new Gui_TaiKhoan(maNV);
		            guiTK.setOpaque(true);
		            Dimension sizeGuiTK = guiTK.getPreferredSize(); // Lấy kích thước ưu tiên của guiTK
		            Dimension sizeGuiLayeredPane = layeredPane.getPreferredSize(); //

		            if (sizeGuiLayeredPane != null && sizeGuiLayeredPane.getWidth() > 0 && sizeGuiLayeredPane.getHeight() > 0) {
		                int x = (int) ((sizeGuiLayeredPane.getWidth() - sizeGuiTK.width) / 2);
		                int y = (int) ((sizeGuiLayeredPane.getHeight() - sizeGuiTK.height) / 2);

		                guiTK.setBounds(x, y, sizeGuiTK.width, sizeGuiTK.height);
		            } else {
		                guiTK.setBounds(0, 0, sizeGuiTK.width, sizeGuiTK.height);
		            }
		            guiTK.addMouseListener(new MouseAdapter() {
		            });
		            guiTK.addMouseMotionListener(new MouseAdapter() {
		            });
		            layeredPane.add(guiTK, JLayeredPane.PALETTE_LAYER);

		            isGuiTKDisplayed = true;
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng từ bảng để xem tài khoản.");
		    }
		}


	}

	private String autoCreateMaNV() {
		// TODO Auto-generated method stub
		return null;
	}
	private void clearFields() {
		txtMa.setText("");
	    txtHoTen.setText("");
	    txtSDT.setText("");
	    txtCC.setText("");
	    txtDC.setText("");
	    radNam.setSelected(true);
	    comboBoxChucVu.setSelectedIndex(0); 
	    comboBoxTrangThai.setSelectedIndex(0); 
	    if(ngaySinhDate.getDate() == null && ngayLamDate.getDate() == null) {
		    ngaySinhDate.setDate(null);
		    ngaySinhDate.setDate(null);
	    }
	    ImageIcon userIcon = new ImageIcon("images/user1.png");
	    img = userIcon.getImage();
	    resizedImg = img.getScaledInstance(120,120, java.awt.Image.SCALE_SMOOTH);
	    userIcon = new ImageIcon(resizedImg);
	    lblHinhAnh.setIcon(userIcon);	
	}
	private boolean validData() {
	    boolean isValid = true;
//	    if (txtMa.getText().isEmpty()) {
//	        isValid = false;
//	        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên.");
//	        txtMa.requestFocus();
//	    } else 
	    if (txtHoTen.getText().isEmpty()) {
	        isValid = false;
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập họ và tên nhân viên.");
	        txtHoTen.requestFocus();
	    } else if (!radNam.isSelected() && !radNu.isSelected()) {
	        isValid = false;
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính của nhân viên.");
	    } else if(txtSDT.getText().isEmpty()) {
	    	isValid = false;
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại.");
	    } else if(txtCC.getText().isEmpty()) {
	    	isValid = false;
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập căn cước công dân.");
	    } else if(txtDC.getText().isEmpty()) {
	    	isValid = false;
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ.");
	    }
	    return isValid;
	}
	public void filterByChucVu(String chucVu) {
	    DefaultTableModel model = (DefaultTableModel) tableModel.getModel();
	    TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
	    tableModel.setRowSorter(rowSorter);
	    
	    RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
	        @Override
	        public boolean include(Entry<?, ?> entry) {
	            String cv = (String) entry.getValue(6); // Chức vụ ở cột 6 trong bảng
	            return cv.equalsIgnoreCase(chucVu);
	        }
	    };
	    if (chucVu.equalsIgnoreCase("Quản Lý") || chucVu.equalsIgnoreCase("Nhân Viên")) {
	        rowSorter.setRowFilter(filter);
	    } else {
	        rowSorter.setRowFilter(null); 
	    }
	}

	public void filterByTrangThai(String trangThai) {
	    DefaultTableModel model = (DefaultTableModel) tableModel.getModel();
	    TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
	    tableModel.setRowSorter(rowSorter);
	    
	    RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
	        @Override
	        public boolean include(Entry<?, ?> entry) {
	            String tt = (String) entry.getValue(9); // Trạng thái ở cột 9 trong bảng
	            return tt.equalsIgnoreCase(trangThai);
	        }
	    };
	    if (trangThai.equalsIgnoreCase("Làm việc") || trangThai.equalsIgnoreCase("Không làm việc")) {
	        rowSorter.setRowFilter(filter);
	    } else {
	        rowSorter.setRowFilter(null); 
	    }
	}
	private void updateRowNhanVien(NhanVien nhanVien) {
	    int rowCount = dataModel.getRowCount();
	    for (int i = 0; i < rowCount; i++) {
	        if (dataModel.getValueAt(i, 0).toString().equals(nhanVien.getMaNV())) {
	            dataModel.setValueAt(nhanVien.getHoTen(), i, 1);
	            dataModel.setValueAt(nhanVien.getGioiTinh(), i, 2);
	            dataModel.setValueAt(nhanVien.getSoDienThoai(), i, 3);
	            dataModel.setValueAt(nhanVien.getNgaySinh(), i, 4);
	            dataModel.setValueAt(nhanVien.getNgayVaoLam(), i, 5);
	            dataModel.setValueAt(nhanVien.getChucVu(), i, 6);
	            dataModel.setValueAt(nhanVien.getSoCCCD(), i, 7);
	            dataModel.setValueAt(nhanVien.getDiaChi(), i, 8);
	            dataModel.setValueAt(nhanVien.getTrangThai(), i, 9);
	            break;
	        }
	    }
	}

	private void themAnh() {
	    JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	            "JPG, PNG, GIF Images", "jpg", "jpeg", "png", "gif");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(this);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	        File file = chooser.getSelectedFile();
	        ImageIcon imageIcon = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	        lblHinhAnh.setIcon(imageIcon);
	        
	        anhByte = new byte[(int) (file.length())];
	        
	        try(FileInputStream fie = new FileInputStream(file)) {
	        	fie.read(anhByte);
	        }catch (Exception e) {
	        	e.printStackTrace();
			}

	   }
	    
	}
	
	private void addRowNhanVien(NhanVien nhanVien) {
		dataModel.addRow(new Object[] {
					nhanVien.getMaNV(),
					nhanVien.getHoTen(),
					nhanVien.getGioiTinh(),
					nhanVien.getSoDienThoai(),
					nhanVien.getNgaySinh(),
					nhanVien.getNgayVaoLam(),
					nhanVien.getChucVu(),
					nhanVien.getSoCCCD(),
					nhanVien.getDiaChi(),
					nhanVien.getTrangThai(),
					nhanVien.getAnh(),
					});
	}
	public void loadDataTable() {
		List<NhanVien> listNhanVien = new ArrayList();
		listNhanVien = (new Dao_NhanVien()).readFromSQL();
		for (NhanVien nhanVien : listNhanVien) {
			addRowNhanVien(nhanVien);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableModel.getSelectedRow();
		if(row >= 0) {
			NhanVien nhanVien = (new Dao_NhanVien()).findNhanVienByMaNV(tableModel.getValueAt(row, 0).toString());
	        if (nhanVien.getAnh() == null) {
	        	System.out.println("1");
	        }
	        
	        try {
	        	if (nhanVien.getAnh() != null) {
	        		BufferedImage anh = ImageIO.read(new ByteArrayInputStream(nhanVien.getAnh()));
	        		if (anh == null) {
	        			System.out.println("2");
	        		}
	        		ImageIcon imageIcon = new ImageIcon(anh.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	        		lblHinhAnh.setIcon(imageIcon);
	        	}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//	        txtMa.setText(tableModel.getValueAt(row, 0).toString());
//	        txtHoTen.setText(tableModel.getValueAt(row, 1).toString());
//	        String gioiTinh = tableModel.getValueAt(row, 2).toString();
//	        if (gioiTinh.equals("Nam")) {
//	            radNam.setSelected(true);
//	        } else {
//	            radNu.setSelected(true);
//	        }
//	        txtSDT.setText(tableModel.getValueAt(row, 3).toString());
//	        LocalDate ngaySinh = (LocalDate) tableModel.getValueAt(row, 4);
//	        ngaySinhDate.setDate(java.sql.Date.valueOf(ngaySinh));
//	        LocalDate ngayVaoLam = (LocalDate) tableModel.getValueAt(row, 5);
//	        ngayLamDate.setDate(java.sql.Date.valueOf(ngayVaoLam));
//	        comboBoxChucVu.setSelectedItem(tableModel.getValueAt(row, 6).toString());
//	        txtCC.setText(tableModel.getValueAt(row, 7).toString());
//	        txtDC.setText(tableModel.getValueAt(row, 8).toString());
//	        comboBoxTrangThai.setSelectedItem(tableModel.getValueAt(row, 9).toString());
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
