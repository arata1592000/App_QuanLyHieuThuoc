package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class Gui_ThemKhuyenMai extends JPanel{
	private int widthComp;
	private int heightComp;
	private JPanel pNorth;
	private JPanel pCenter;
	private JPanel pTable;
	private JPanel pBut;
	private DefaultTableModel dataModel;
	private JTable tableModel;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnTim;
	private JButton btnSua;
	private JTextField txtMa;
	private JTextField txtTyLe;
	private JRadioButton radTatCa;
	private JRadioButton radHD;
	private JRadioButton radKHD;
	private ButtonGroup radGroup;
	private JDateChooser ngayBatDauDate;
	private JDateChooser ngayKetThucDate;
	private JComboBox cbbLoaiKM;

	public Gui_ThemKhuyenMai() {
		pNorth = new JPanel();
		pCenter = new JPanel();
		pTable = new JPanel();
		
		
		
		pCenter.setLayout(new BorderLayout());
	    pNorth.setLayout(new FlowLayout());
	    pTable.setLayout(new BorderLayout());
	    
	    JLabel lblTitle  = new JLabel("Thông tin tìm kiếm");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblTitle.setBorder(new EmptyBorder(10, 10, 10, 10));
        pNorth.add(lblTitle,FlowLayout.LEFT);
        
        
        JPanel pInfor = new JPanel();
        pInfor.setLayout(new GridBagLayout());
		GridBagConstraints constraintsCustomer = new GridBagConstraints();
        constraintsCustomer.insets = new Insets(5, 5, 5, 5);
        
        JLabel lblMa = new JLabel("Mã khuyến mãi:");
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblMa, constraintsCustomer);
        txtMa= new JTextField(10);
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtMa,constraintsCustomer);
        
        JLabel lblTyLe = new JLabel("Tỷ lệ khuyến mãi:");
        //lblTyLe.setPreferredSize(lblMa.getPreferredSize());
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 1;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblTyLe, constraintsCustomer);
        txtTyLe= new JTextField(10);
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 1;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtTyLe,constraintsCustomer);
        
        JLabel lblTT = new JLabel("Trạng Thái:");
        radTatCa = new JRadioButton("Tất cả");
        radHD = new JRadioButton("Hoạt Động");
        radKHD = new JRadioButton("Không Hoạt Động");
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 2; 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblTT, constraintsCustomer);
        
        radGroup = new ButtonGroup();
        radGroup.add(radTatCa);
        radGroup.add(radHD);
        radGroup.add(radKHD);
        JPanel pGroup = new JPanel();
        pGroup.add(radTatCa);
        pGroup.add(radHD);
        pGroup.add(radKHD);
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 2;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(pGroup, constraintsCustomer);
        
        JLabel lblNBD = new JLabel("Ngày bắt đầu:");
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 0; // 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblNBD,constraintsCustomer);
        ngayBatDauDate = new JDateChooser();
        ngayBatDauDate.setPreferredSize(new Dimension(170,25));
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(ngayBatDauDate,constraintsCustomer);
        
        JLabel lblNKT = new JLabel("Ngày kết thúc:");
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 1; 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblNKT,constraintsCustomer);
        ngayKetThucDate  = new JDateChooser();
        ngayKetThucDate.setPreferredSize(new Dimension(170,25));
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 1;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(ngayKetThucDate,constraintsCustomer);
        
        JLabel lblLoaiKM = new JLabel("Loại khuyến mãi");
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 2;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblLoaiKM,constraintsCustomer);
        cbbLoaiKM = new JComboBox(new String[]{"Khuyến mãi trên hóa đơn", "Khuyến mãi trên sản phẩm"});
        cbbLoaiKM.setPreferredSize(new Dimension(200,25));
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 2; 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(cbbLoaiKM, constraintsCustomer);
        
        
        pCenter.add(pInfor,BorderLayout.CENTER);
        
        pBut = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
		pBut.setBackground(Color.WHITE); 
		btnThem = new JButton("Thêm khuyến mãi");
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Arial", Font.BOLD, 16));
		btnThem.setBackground(new Color (40,156,164));
	    btnXoa = new JButton("Xóa khuyến mãi");
	    btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Arial", Font.BOLD, 16));
		btnXoa.setBackground(new Color (40,156,164));
	    btnSua = new JButton("Sửa khuyến mãi");
	    btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Arial", Font.BOLD, 16));
		btnSua.setBackground(new Color (40,156,164));
	    btnTim = new JButton("Tìm khuyến mãi");
	    btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Arial", Font.BOLD, 16));
		btnTim.setBackground(new Color (40,156,164));
		pBut.add(btnThem);
		pBut.add(btnXoa);
		pBut.add(btnSua);
		pBut.add(btnTim);
		pCenter.add(pBut,BorderLayout.SOUTH);
		
        
        
        
       
       
        JLabel lblDSKM = new JLabel("Danh sách khuyến mãi");
		lblDSKM.setFont(new Font("Times New Roman", Font.BOLD, 30));
		String headers[] = {"Mã khuyến mãi", "Tỷ lệ khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Loại khuyến mãi","Trạng Thái"};
		Object[][] data = {
				{"KMSP24","20%","20/10/2024","15/12/2024","khuyến mãi trên sản phẩm","Hoạt động"}

        };
		dataModel = new DefaultTableModel(data,headers);
		tableModel = new JTable(dataModel);
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 16));
		tableModel.setRowHeight(30);
		tableModel.setModel(dataModel);
		
		JScrollPane scroll = new JScrollPane(tableModel);
		
		
		pTable.add(lblDSKM,BorderLayout.NORTH); 
		pTable.add(scroll,BorderLayout.CENTER);
		this.setLayout(new BorderLayout());
		this.add(pTable,BorderLayout.SOUTH);
		this.add(pNorth,BorderLayout.NORTH);
		this.add(pCenter,BorderLayout.CENTER);
		this.addComponentListener(new ComponentAdapter() {
	        @Override
	        public void componentResized(ComponentEvent e) {
	        	
	            revalidate(); // Cần gọi revalidate() để đảm bảo cập nhật layout
	        }
	    });
	}
		
	}


