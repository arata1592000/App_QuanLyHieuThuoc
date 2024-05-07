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
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import dao.Dao_KhuyenMai;
import dao.Dao_NhanVien;
import dao.Dao_Thuoc;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.Thuoc;


public class Gui_KhuyenMai extends JPanel implements ActionListener,DocumentListener{
	private int widthComp;
	private int heightComp;
	private JPanel pNorth;
	private JPanel pCenter;
	private JPanel pTable;
	private JPanel pButton;
	private DefaultTableModel dataModel;
	private JTable tableModel;
	private JButton btnThem;
	//private JButton btnXoa;
	//private JButton btnTim;
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
	private JLayeredPane layeredPane;
	private JPanel pContent;
	private Gui_ThemKhuyenMai guiThemKM;
	private int indexPopupMenu;
	private boolean isGuiCTHDDisplayed = false;
	private KhuyenMai khuyenMai;
	private Gui_SuaKhuyenMai guiSuaKM;
	private JLabel lblMa;
	private JLabel lblTyLe;
	private JPanel pGroup;
	private JLabel lblNBD;
	private JLabel lblNKT;
	private JLabel lblLoaiKM;
	private JLabel lblTitle;
	private JPanel pInfor;
	private JLabel lblTT;
	private JPopupMenu suggestionMenu;

	public Gui_KhuyenMai(int width, int height) {
		
		this.widthComp = width;
		this.heightComp = height;
		this.setLayout(new BorderLayout());
		initCompoent();
		loadDataTable();
		
	}
	public void initCompoent() {
		suggestionMenu = new JPopupMenu();
		layeredPane = new JLayeredPane();
		pContent = new JPanel();
		pNorth = new JPanel();
		lblTitle  = new JLabel("Thông tin tìm kiếm");
		pInfor = new JPanel();
        lblMa = new JLabel("Mã khuyến mãi:");
        lblTyLe = new JLabel("Tỷ lệ khuyến mãi:");
        lblTT = new JLabel("Trạng Thái:");
        radTatCa = new JRadioButton("Tất cả");
        radHD = new JRadioButton("Hoạt Động");
        radKHD = new JRadioButton("Không Hoạt Động");
        pGroup = new JPanel();
        lblNBD = new JLabel("Ngày bắt đầu:");
        lblNKT = new JLabel("Ngày kết thúc:");
        radGroup = new ButtonGroup();
        lblLoaiKM = new JLabel("Loại khuyến mãi");
        cbbLoaiKM = new JComboBox(new String[]{"Tất cả","Khuyến mãi trên hóa đơn", "Khuyến mãi trên sản phẩm"});
		pCenter = new JPanel();
        pButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
		btnThem = new JButton("Thêm khuyến mãi");
	    btnSua = new JButton("Sửa khuyến mãi");
		pTable = new JPanel();
        JLabel lblDSKM = new JLabel("Danh sách khuyến mãi");
        String headers[] = {"Mã khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc","Tỷ lệ khuyến mãi", "Loại khuyến mãi","Trạng Thái"};
		dataModel = new DefaultTableModel(headers,0);
		tableModel = new JTable(dataModel);
		JScrollPane scroll = new JScrollPane(tableModel);
		
		
		layeredPane.setOpaque(true);
        layeredPane.setPreferredSize(new Dimension(widthComp, heightComp));
        layeredPane.setBackground(Color.WHITE);
        
		pContent.setOpaque(true);
        pContent.setBounds(0,0, widthComp, heightComp);
		pContent.setBackground(Color.WHITE);
		
		pNorth.setPreferredSize(new Dimension((int)(widthComp*0.93),(int) (heightComp*0.05)));
	    pNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblTitle.setBorder(new EmptyBorder(10, 10, 10, 10));
        pInfor.setLayout(new GridBagLayout());
        
        setPanelInput();
		
		pCenter.setPreferredSize(new Dimension((int)(widthComp*0.93),(int) (heightComp*0.40)));
		pCenter.setLayout(new BorderLayout());
         
		pButton.setBackground(Color.WHITE); 
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Arial", Font.BOLD, 16));
		btnThem.setBackground(new Color (40,156,164));
	    btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Arial", Font.BOLD, 16));
		btnSua.setBackground(new Color (40,156,164));
		
        pTable.setPreferredSize(new Dimension((int)(widthComp*0.93),(int) (heightComp*0.45)));
		lblDSKM.setFont(new Font("Times New Roman", Font.BOLD, 30));
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 16));
		tableModel.setRowHeight(30);
		tableModel.setModel(dataModel);
		scroll.setPreferredSize(new Dimension((int)(widthComp*0.9),(int) (heightComp*0.35)));
        
        pNorth.add(lblTitle,FlowLayout.LEFT);
        pButton.add(btnThem);
		pButton.add(btnSua);
		pCenter.add(pInfor,BorderLayout.CENTER);
		pCenter.add(pButton,BorderLayout.SOUTH);
		pTable.add(lblDSKM); 
		pTable.add(scroll);
		
		pContent.add(pNorth,BorderLayout.NORTH);
		pContent.add(pCenter,BorderLayout.CENTER);
		pContent.add(pTable,BorderLayout.SOUTH);
		layeredPane.add(pContent, JLayeredPane.DEFAULT_LAYER);
        this.add(layeredPane);
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		cbbLoaiKM.addActionListener(this);	
		radHD.addActionListener(this);
		radKHD.addActionListener(this);
		radTatCa.addActionListener(this);
		txtTyLe.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        filterByTyLeKM(txtTyLe.getText().trim());
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		    	filterByTyLeKM(txtTyLe.getText().trim());
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        filterByTyLeKM(txtTyLe.getText().trim());
		    }
		});
		
		txtMa.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        filterByMaKM(txtMa.getText().trim());
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        filterByMaKM(txtMa.getText().trim());
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        filterByMaKM(txtMa.getText().trim());
		    }
		});
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem))
		{
			if(layeredPane.getComponentsInLayer(JLayeredPane.PALETTE_LAYER).length == 0){
	    		isGuiCTHDDisplayed = false;
	    	}
	    	if (!isGuiCTHDDisplayed) {
		        guiThemKM = new Gui_ThemKhuyenMai();
		        guiThemKM.setOpaque(true);
		        Dimension sizeGuiCTHD= guiThemKM.getPreferredSize(); // Lấy kích thước ưu tiên của guiCTHD
		        Dimension sizeGuiLayeredPane = layeredPane.getPreferredSize(); // Lấy container cha (ví dụ, JLayeredPane hoặc JPanel)

		        if (sizeGuiLayeredPane != null && sizeGuiLayeredPane.getWidth() > 0 && sizeGuiLayeredPane.getHeight() > 0) {
		            int x = (int) ((sizeGuiLayeredPane.getWidth() - sizeGuiCTHD.width) / 2);
		            int y = (int) ((sizeGuiLayeredPane.getHeight() - sizeGuiCTHD.height) / 2);

		            guiThemKM.setBounds(x, y, sizeGuiCTHD.width, sizeGuiCTHD.height);
		        } else {
		            guiThemKM.setBounds(0, 0, sizeGuiCTHD.width, sizeGuiCTHD.height);
		        }
		        guiThemKM.addMouseListener(new MouseAdapter() {});
		        guiThemKM.addMouseMotionListener(new MouseAdapter() {});

		        layeredPane.add(guiThemKM, JLayeredPane.PALETTE_LAYER);
		     
		        isGuiCTHDDisplayed  = true;
		  
	    	}
		}
		if(o.equals(btnSua))
		{
			int row = tableModel.getSelectedRow();
			if (row != -1) { 
                String maKM = tableModel.getValueAt(row, 0).toString(); // Assume Mã khuyến mãi is at column index 1
                if(layeredPane.getComponentsInLayer(JLayeredPane.PALETTE_LAYER).length == 0){
    	    		isGuiCTHDDisplayed = false;
    	    	}
    	    	if (!isGuiCTHDDisplayed) {
    	    		guiSuaKM = new Gui_SuaKhuyenMai(maKM);
    		        
    		        guiSuaKM.setOpaque(true);
    		        Dimension sizeGuiCTHD= guiSuaKM.getPreferredSize(); // Lấy kích thước ưu tiên của guiCTHD
    		        Dimension sizeGuiLayeredPane = layeredPane.getPreferredSize(); // Lấy container cha (ví dụ, JLayeredPane hoặc JPanel)

    		        if (sizeGuiLayeredPane != null && sizeGuiLayeredPane.getWidth() > 0 && sizeGuiLayeredPane.getHeight() > 0) {
    		            int x = (int) ((sizeGuiLayeredPane.getWidth() - sizeGuiCTHD.width) / 2);
    		            int y = (int) ((sizeGuiLayeredPane.getHeight() - sizeGuiCTHD.height) / 2);

    		            guiSuaKM.setBounds(x, y, sizeGuiCTHD.width, sizeGuiCTHD.height);
    		        } else {
    		            guiSuaKM.setBounds(0, 0, sizeGuiCTHD.width, sizeGuiCTHD.height);
    		        }
    		        guiSuaKM.addMouseListener(new MouseAdapter() {});
    		        guiSuaKM.addMouseMotionListener(new MouseAdapter() {});

    		        layeredPane.add(guiSuaKM, JLayeredPane.PALETTE_LAYER);
    		     
    		        isGuiCTHDDisplayed  = true;
    		  
    	    	}
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng cần xem chi tiết!");
            }
		}
		if(o.equals(cbbLoaiKM)||o.equals(radGroup))
		{
			String selectedLoaiKM = (String) cbbLoaiKM.getSelectedItem();
			String selectedTrangThai  = "";
			if (radHD.isSelected()) {
		        selectedTrangThai = "Hoạt động";
		    } else if (radKHD.isSelected()) {
		        selectedTrangThai = "Không hoạt động";
		    } else if (radTatCa.isSelected()) {
		        selectedTrangThai = "Tất cả";
		    }
		    filterByTrangThaiLoai(selectedTrangThai, selectedLoaiKM);
		    
		}
		
//		if(o.equals(radHD)) {
//			filterByTrangThai("Hoạt động");
//		}else if (o.equals(radKHD)) {
//			filterByTrangThai("Không hoạt động");
//		}else if (o.equals(radTatCa)) {
//			filterByTrangThai("Tất cả");
//		}
	}
	
	private void setPanelInput() {
		GridBagConstraints constraintsCustomer = new GridBagConstraints();
        constraintsCustomer.insets = new Insets(5, 5, 5, 5);
        
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblMa, constraintsCustomer);
        txtMa= new JTextField(10);
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtMa,constraintsCustomer);
        
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblTyLe, constraintsCustomer);
        txtTyLe= new JTextField(10);
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(txtTyLe,constraintsCustomer);
        
        
        radTatCa.setSelected(true);
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 1; 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblTT, constraintsCustomer);
        
        radGroup.add(radTatCa);
        radGroup.add(radHD);
        radGroup.add(radKHD);
        pGroup.add(radTatCa);
        pGroup.add(radHD);
        pGroup.add(radKHD);
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 1;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(pGroup, constraintsCustomer);
        
//        constraintsCustomer.gridx = 3;
//        constraintsCustomer.gridy = 0;
//        constraintsCustomer.anchor = GridBagConstraints.WEST;
//        pInfor.add(lblNBD,constraintsCustomer);
//        ngayBatDauDate = new JDateChooser();
//        ngayBatDauDate.setPreferredSize(new Dimension(170,25));
//        constraintsCustomer.gridx = 4;
//        constraintsCustomer.gridy = 0;
//        constraintsCustomer.anchor = GridBagConstraints.WEST;
//        pInfor.add(ngayBatDauDate,constraintsCustomer);
//        
//
//        constraintsCustomer.gridx = 3;
//        constraintsCustomer.gridy = 1; 
//        constraintsCustomer.anchor = GridBagConstraints.WEST;
//        pInfor.add(lblNKT,constraintsCustomer);
//        ngayKetThucDate  = new JDateChooser();
//        ngayKetThucDate.setPreferredSize(new Dimension(170,25));
//        constraintsCustomer.gridx = 4;
//        constraintsCustomer.gridy = 1;
//        constraintsCustomer.anchor = GridBagConstraints.WEST;
//        pInfor.add(ngayKetThucDate,constraintsCustomer);
        


        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 1;
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(lblLoaiKM,constraintsCustomer);
        cbbLoaiKM.setPreferredSize(new Dimension(200,25));
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 1; 
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        pInfor.add(cbbLoaiKM, constraintsCustomer);
	}
	private void addRowKM(KhuyenMai khuyenMai) {
		dataModel.addRow(new Object[] {
					khuyenMai.getMaKM(),khuyenMai.getNgayBatDau(),khuyenMai.getNgayKetThuc(),khuyenMai.getTyLeKM(),
					khuyenMai.getLoaiKM(),khuyenMai.getTrangThai()
					});
	}
	public void loadDataTable() {
		List<KhuyenMai> listKM = new ArrayList();
		listKM = (new Dao_KhuyenMai()).readKMFromSQL();
		removeDataTable();
		for (KhuyenMai km : listKM) {
			addRowKM(km);
		}
	}
	
	public void removeDataTable() {
		while (dataModel.getRowCount() > 0) {
			dataModel.removeRow(0);
		}
	}
	
//	public void filterByLoaiKM(String loai) {
//	    DefaultTableModel model = (DefaultTableModel) tableModel.getModel();
//	    TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
//	    tableModel.setRowSorter(rowSorter);
//	    
//	    RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
//	        @Override
//	        public boolean include(Entry<?, ?> entry) {
//	            String tt = (String) entry.getValue(4); // Trạng thái ở cột 6 trong bảng
//	            return tt.equalsIgnoreCase(loai);
//	        }
//	    };
//	    if (loai.equalsIgnoreCase("Khuyến mãi trên hóa đơn") || loai.equalsIgnoreCase("Khuyến mãi trên sản phẩm")) {
//	        rowSorter.setRowFilter(filter);
//	    } else if (loai.equalsIgnoreCase("Tất cả")){
//	        rowSorter.setRowFilter(null); 
//	    } else {
//	    	JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!");
//	    }
//	}
//	public void filterByTrangThai(String trangThai) {
//	    DefaultTableModel model = (DefaultTableModel) tableModel.getModel();
//	    TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
//	    tableModel.setRowSorter(rowSorter);
//	    
//	    RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
//	        @Override
//	        public boolean include(Entry<?, ?> entry) {
//	        
//	        	String tt = (String) entry.getValue(5); // Trạng thái ở cột 6 trong bảng
//	            return tt.equalsIgnoreCase(trangThai);
//	   
//	        }
//	    };
//	    if (trangThai.equalsIgnoreCase("Hoạt động") 
//	    	||trangThai.equalsIgnoreCase("Không hoạt động")) {
//	        rowSorter.setRowFilter(filter);
//	    } else if (trangThai.equalsIgnoreCase("Tất cả")){
//	        rowSorter.setRowFilter(null); 
//	    } else {
//	    	JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!");
//	    }
//	}
	
	public void filterByTrangThaiLoai(String trangThai,String loai) {
	    DefaultTableModel model = (DefaultTableModel) tableModel.getModel();
	    TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
	    tableModel.setRowSorter(rowSorter);
	    
	    RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
	        @Override
	        public boolean include(Entry<?, ?> entry) {
	            String tt = (String) entry.getValue(5);
	            String l = (String) entry.getValue(4);
	            
	            if (trangThai.equalsIgnoreCase("Hoạt Động") && loai.equalsIgnoreCase("Tất Cả")) {
	                return tt.equalsIgnoreCase(trangThai);
	            } else if (trangThai.equalsIgnoreCase("Không Hoạt Động") && loai.equalsIgnoreCase("Tất Cả")) {
	                return tt.equalsIgnoreCase(trangThai);
	            } else if (trangThai.equalsIgnoreCase("Tất Cả") && loai.equalsIgnoreCase("Tất Cả")) {
	                return true;
	            }else if (trangThai.equalsIgnoreCase("Hoạt Động") && loai.equalsIgnoreCase("Khuyến mãi trên hóa đơn")) {
		                return tt.equalsIgnoreCase(trangThai) && l.equalsIgnoreCase(loai);
		            } else if (trangThai.equalsIgnoreCase("Không Hoạt Động") && loai.equalsIgnoreCase("Khuyến mãi trên hóa đơn")) {
		                return tt.equalsIgnoreCase(trangThai)&& l.equalsIgnoreCase(loai);
		            } else if (trangThai.equalsIgnoreCase("Tất Cả") && loai.equalsIgnoreCase("Khuyến mãi trên hóa đơn")) {
		                return l.equalsIgnoreCase(loai);
		            }else if (trangThai.equalsIgnoreCase("Hoạt Động") && loai.equalsIgnoreCase("Khuyến mãi trên sản phẩm")) {
		                return tt.equalsIgnoreCase(trangThai)&& l.equalsIgnoreCase(loai);
		            } else if (trangThai.equalsIgnoreCase("Không Hoạt Động") && loai.equalsIgnoreCase("Khuyến mãi trên sản phẩm")) {
		                return tt.equalsIgnoreCase(trangThai)&& l.equalsIgnoreCase(loai);
		            } else if (trangThai.equalsIgnoreCase("Tất Cả") && loai.equalsIgnoreCase("Khuyến mãi trên sản phẩm")) {
		                return l.equalsIgnoreCase(loai);
	            }else 
	            	return true;
	        }
	    };
	    rowSorter.setRowFilter(filter);
	}
	
	public void filterByMaKM(String MaKM) {
	    DefaultTableModel model = (DefaultTableModel) tableModel.getModel();
	    TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
	    tableModel.setRowSorter(rowSorter);
	    
	    RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
	        @Override
	        public boolean include(Entry<?, ?> entry) {
	            String tt = (String) entry.getValue(0); // Trạng thái ở cột 6 trong bảng
	            return tt.toLowerCase().contains(MaKM.toLowerCase());
	        }
	    };
	    rowSorter.setRowFilter(filter);
	}
	public void filterByTyLeKM(String tyleKM) {
	    if (tyleKM.isEmpty()) {
	    	tableModel.setRowSorter(null);
	    } else {
	        try {
	            Float tyLeKMFloat = Float.valueOf(tyleKM);
	            DefaultTableModel model = (DefaultTableModel) tableModel.getModel();
	            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
	            tableModel.setRowSorter(rowSorter);
	            
	            RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
	                @Override
	                public boolean include(Entry<?, ?> entry) {
	                    Float value = (Float) entry.getValue(3); // Giả sử cột chứa tỷ lệ khuyến mãi là cột thứ 2 (index 1)
	                    //return value.equals(tyLeKMFloat) && value != null;
	                    return Math.abs(value - tyLeKMFloat) < 0.001;
	                }
	            };
	            rowSorter.setRowFilter(filter);
	        } catch (NumberFormatException ex) {
	            // Xử lý ngoại lệ khi giá trị nhập vào không phải là số
	            JOptionPane.showMessageDialog(null, "Vui lòng nhập vào một số hợp lệ cho tỷ lệ khuyến mãi.");
	        }
	    }
	}
//	public void filterChung(String MaKM , String tyleKM,String loai, String trangThai)
//	{
//		DefaultTableModel model = (DefaultTableModel) tableModel.getModel();
//	    TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
//	    tableModel.setRowSorter(rowSorter);
//	    
//	    RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
//	        @Override
//	        public boolean include(Entry<?, ?> entry) {
//	            boolean loaiMatch = loai.equalsIgnoreCase("Tất cả") || ((String) entry.getValue(4)).equalsIgnoreCase(loai);
//	            boolean trangThaiMatch = trangThai.equalsIgnoreCase("Tất cả") || ((String) entry.getValue(5)).equalsIgnoreCase(trangThai);
//	            boolean MaKMMatch = MaKM.isEmpty() || ((String) entry.getValue(0)).toLowerCase().contains(MaKM.toLowerCase());
//	            boolean tyleKMMatch = tyleKM.isEmpty();
//	            if (!tyleKMMatch) {
//	                try {
//	                    Float tyLeKMFloat = Float.valueOf(tyleKM);
//	                    Float value = (Float) entry.getValue(3);
//	                    tyleKMMatch = Math.abs(value - tyLeKMFloat) < 0.001;
//	                } catch (NumberFormatException ex) {
//	                    JOptionPane.showMessageDialog(null, "Vui lòng nhập vào một số hợp lệ cho tỷ lệ khuyến mãi.");
//	                }
//	            }
//	            return loaiMatch && trangThaiMatch && MaKMMatch && tyleKMMatch;
//	        }
//	    };
//	    rowSorter.setRowFilter(filter);
//	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}