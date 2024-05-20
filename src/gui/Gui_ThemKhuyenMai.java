package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.Dao_KhuyenMai;
import dao.Dao_NhanVien;
import dao.Dao_Thuoc;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.Thuoc;

public class Gui_ThemKhuyenMai extends JPanel implements ActionListener {
	private JTextField txtMaKM;
	private JDateChooser txtNgayBD;
	private JButton btnThem;
	private JButton btnHuy;
	private JTextField txtTyLeKM;
	private JDateChooser txtNgayKT;
	
	private JComboBox<String> cbbLoai ;
	private JTextField txtAddTien;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane;
	private JButton btnLeft;
	private JButton btnRight;
	private int widthComp;
	private int heightComp;
	private DefaultTableModel modelLeft;
	private JTable tableLeft;
	private DefaultTableModel modelRight;
	private JTable tableRight;
	private JLabel lblMa;
	private JLabel lblBD;
	private JLabel lblKT;
	private JLabel lblTLKM;
	private JLabel lblLoai;
	private JLabel lblAddTien;
	private JPanel pInput;
	private JPanel pTableLeft;
	private JPanel pCenter;
	private JPanel pTableRight;
	private JPanel pButtonSouth;
	public Gui_ThemKhuyenMai()
	{
		this.widthComp = 1000;
		this.heightComp = 600;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(widthComp, heightComp));
		initCompoent();
		loadDataTableLeft();
	}
	
	private void initCompoent() {
		// Tạo JPanel
		pInput = getPanelInput();
		pTableLeft = getPanelTableLeft();
		pCenter = getPanelCenter();
		pTableRight = getPanelTableRight();
		pButtonSouth = getPanelButtonSouth();
		
		this.add(pInput, BorderLayout.NORTH);
		this.add(pTableLeft, BorderLayout.WEST);
		this.add(pTableRight, BorderLayout.EAST);
		this.add(pCenter, BorderLayout.CENTER);
		this.add(pButtonSouth, BorderLayout.SOUTH);
        
	}
	
	private JPanel getPanelInput() {
		// Tạo JLabel
		JPanel pMain = new JPanel();
				
        lblMa = new JLabel("Mã khuyến mãi");
        lblBD = new JLabel("Ngày bắt đầu");
        lblTLKM = new JLabel("Tỷ lệ khuyến mãi");
        lblKT = new JLabel("Ngày kết thúc");
        lblLoai = new JLabel("Loại khuyến mãi");
        lblAddTien = new JLabel("Giá trị hóa đơn");
        cbbLoai= new JComboBox<>();

        // Tạo JTextField
        txtMaKM = new JTextField(10);
        txtNgayBD = new JDateChooser();
        txtTyLeKM = new JTextField(10);
        txtNgayKT = new JDateChooser();
        txtAddTien = new JTextField(10);
        cbbLoai.addItem("Khuyến mãi trên sản phẩm");
        cbbLoai.addItem("Khuyến mãi trên hóa đơn");
        
        
        pMain.setPreferredSize(new Dimension((int) (widthComp*0.9), (int) (heightComp*0.3)));
        pMain.setLayout(new GridBagLayout());
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thêm thông tin khuyến mãi", TitledBorder.CENTER, TitledBorder.TOP);
        Font titleFont = UIManager.getFont("TitledBorder.font");
        Font boldFont = new Font(titleFont.getName(), Font.BOLD, titleFont.getSize() + 5);
        ((TitledBorder) border).setTitleFont(boldFont);
        pMain.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(20, 20, 20, 20))); // Thêm EmptyBorder
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 20, 5, 0);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        pMain.add(lblMa, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        pMain.add(txtMaKM, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        pMain.add(lblTLKM, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        pMain.add(txtTyLeKM, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        pMain.add(lblAddTien, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        pMain.add(txtAddTien, gbc);
        txtAddTien.setEditable(false);
        gbc.gridx = 3;
        gbc.gridy = 0;
        pMain.add(lblBD, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        pMain.add(txtNgayBD, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        pMain.add(lblKT, gbc);
        gbc.gridx = 4;
        gbc.gridy = 1;
        pMain.add(txtNgayKT, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        pMain.add(lblLoai, gbc);
        gbc.gridx = 4;
        gbc.gridy = 2;
        pMain.add(cbbLoai, gbc);
                
        cbbLoai.addActionListener(this);
        
        return pMain;
	}

	private JPanel getPanelCenter() {
		// Tạo JButton
		JPanel pMain = new JPanel();
		pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));
		pMain.setBackground(Color.WHITE);
		pMain.setPreferredSize(new Dimension((int) (widthComp*0.1), (int) (heightComp*0.55)));
        btnLeft = new JButton("<-");
        btnRight = new JButton("->");
        btnLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRight.setAlignmentX(Component.CENTER_ALIGNMENT);

        int verticalSpace = (int)((pMain.getPreferredSize().getHeight() - btnRight.getPreferredSize().getHeight() - btnLeft.getPreferredSize().getHeight()) / 2);
        pMain.add(Box.createVerticalStrut(verticalSpace));
        pMain.add(btnRight);
        pMain.add(btnLeft);
        pMain.add(Box.createVerticalStrut(verticalSpace));
        
        btnRight.addActionListener(this);
        btnLeft.addActionListener(this);
        return pMain;
	}
	
	private JPanel getPanelTableLeft() {
		JPanel pMain = new JPanel();
		pMain.setPreferredSize(new Dimension((int) (widthComp*0.43), (int) (heightComp*0.55)));
		pMain.setBackground(Color.WHITE);
        String[] columnNames = {"Mã thuốc", "Tên thuốc", "Đơn giá"};
        modelLeft = new DefaultTableModel(columnNames, 0);
        tableLeft = new JTable(modelLeft);
        scrollPane = new JScrollPane(tableLeft);
	    scrollPane.setPreferredSize(new Dimension((int) (widthComp*0.40), (int) (heightComp*0.55)));
        pMain.add(scrollPane);
        
        return pMain;
	}
	
	private JPanel getPanelTableRight() {
		JPanel pMain = new JPanel();
		pMain.setPreferredSize(new Dimension((int) (widthComp*0.43), (int) (heightComp*0.55)));
		pMain.setBackground(Color.WHITE);
	    String[] columnNames = {"Mã thuốc", "Tên thuốc", "Đơn giá"};
	    modelRight = new DefaultTableModel(columnNames, 0);
	    tableRight = new JTable(modelRight);
	    scrollPane = new JScrollPane(tableRight);
	    scrollPane.setPreferredSize(new Dimension((int) (widthComp*0.40), (int) (heightComp*0.55)));
	    pMain.add(scrollPane);
	    
	    return pMain;
	}
	
	private JPanel getPanelButtonSouth() {
		JPanel pMain = new JPanel();
		
		pMain.setPreferredSize(new Dimension((int) (widthComp*0.9), (int) (heightComp*0.1)));
		pMain.setBackground(Color.WHITE);
		pMain.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnThem = new JButton("Thêm");
		btnHuy = new JButton("Hủy");
		
		pMain.add(btnThem);
		pMain.add(btnHuy);
		pMain.add(Box.createHorizontalStrut(50));

		btnThem.addActionListener(this);
        btnHuy.addActionListener(this);
		
		return pMain;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(cbbLoai)) {
			int select =cbbLoai.getSelectedIndex();
			if (select == 1) {
				txtAddTien.setEnabled(true);
				txtAddTien.setEditable(true);
				pTableLeft.setVisible(false);
				pCenter.setVisible(false);
				pTableRight.setVisible(false);
			} else {
				txtAddTien.setEnabled(false); 
				txtAddTien.setText("");
				pTableLeft.setVisible(true);
				pCenter.setVisible(true);
				pTableRight.setVisible(true);
			}
		}
		if(o.equals(btnHuy))
			{
			 int confirmed = JOptionPane.showConfirmDialog(null, 
	                    "Bạn chắc chắn không? Nội dung bạn đã nhập sẽ bị mất.", 
	                    "Xác nhận đóng", JOptionPane.YES_NO_OPTION);
	                
	                // Nếu người dùng chọn "Đồng ý"
	                if (confirmed == JOptionPane.YES_OPTION) {
	                	JLayeredPane layeredPane = (JLayeredPane) getParent(); 
	        			Gui_KhuyenMai pMain = (Gui_KhuyenMai) layeredPane.getParent();
	        			pMain.loadDataTable();
	                    layeredPane.remove(Gui_ThemKhuyenMai.this);
	                    layeredPane.validate();
	                    layeredPane.repaint();
	                    
	                }
			
			}
		if(o.equals(btnThem))
		{
			if(validData())
			{
			String ma = txtMaKM.getText().trim();
			float tyle = Float.parseFloat(txtTyLeKM.getText().trim());
			LocalDate ngayBD = txtNgayBD.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate ngayKT = txtNgayKT.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        String loai =(String) cbbLoai.getSelectedItem();
	        KhuyenMai khuyenMai = new KhuyenMai(ma,ngayBD,ngayKT,tyle,loai);
	        if (loai.equals("Khuyến mãi trên hóa đơn")) {
	        	khuyenMai.setGiaTriHD(Float.valueOf(txtAddTien.getText()));
	        }else {
	        	khuyenMai.setGiaTriHD(0);
	        }
	        Dao_KhuyenMai daoKM = new Dao_KhuyenMai();
	        boolean success = daoKM.addKM(khuyenMai);

	        if (success) {
	        	(new Dao_KhuyenMai()).setListThuocDiscount(ma, getListMaThuocDiscount(), getListMaThuocNoDiscount());
	        	
	            JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công!");
	            JLayeredPane layeredPane = (JLayeredPane) getParent(); 
    			Gui_KhuyenMai pMain = (Gui_KhuyenMai) layeredPane.getParent();
    			pMain.loadDataTable();
                layeredPane.remove(Gui_ThemKhuyenMai.this);
                layeredPane.validate();
                layeredPane.repaint();
	        } else {
	            JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thất bại!");
	        }
		}
			else
			{
				
			}
		}
		if(o.equals(btnRight))
		{
			int selectedRow = tableLeft.getSelectedRow();
            if (selectedRow != -1) {
                // Lấy dữ liệu từ hàng đã chọn
                Object[] rowData = new Object[modelLeft.getColumnCount()];
                for (int i = 0; i < rowData.length; i++) {
                    rowData[i] = modelLeft.getValueAt(selectedRow, i);
                }
                // Thêm dữ liệu vào bảng phải
                modelRight.addRow(rowData);
                // Xóa dữ liệu khỏi bảng trái
                modelLeft.removeRow(selectedRow);
            	}
		}
		if(o.equals(btnLeft))
		{
			int selectedRow = tableRight.getSelectedRow();
            if (selectedRow != -1) {
                // Lấy dữ liệu từ hàng đã chọn
                Object[] rowData = new Object[modelRight.getColumnCount()];
                for (int i = 0; i < rowData.length; i++) {
                    rowData[i] = modelRight.getValueAt(selectedRow, i);
                }

                // Thêm dữ liệu vào bảng trái
                modelLeft.addRow(rowData);
                
                // Xóa dữ liệu khỏi bảng phair
                modelRight.removeRow(selectedRow);
            	}
		}
	}
	private boolean validData() {
		boolean flag = true;
		LocalDate ngayBD = txtNgayBD.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayKT = txtNgayKT.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String loai =(String) cbbLoai.getSelectedItem();
		if(txtMaKM.getText().isEmpty())
    	{
    		JOptionPane.showMessageDialog(this, "Mã khuyến mãi không được rỗng");
    		txtMaKM.requestFocus();
            flag = false;
    	}
		else if(txtTyLeKM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tỷ lệ khuyến mãi");
            txtTyLeKM.requestFocus();
            flag = false;
        } 
		
    	
    	// Lấy giá trị từ text field và chuyển đổi thành số thực
		else if(!kiemTraSoThucDuong(txtTyLeKM.getText()))
        {
        	JOptionPane.showMessageDialog(this, "Tỷ lệ giảm giá phải là một số thực dương ");
        	flag = false;
        }
        

        // Kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu
		else if (ngayKT.isEqual(ngayBD) ||ngayKT.isBefore(ngayBD)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
            flag = false;
        }
		else if (ngayKT.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày hiện tại");
            flag = false;
        }
        
        else if (loai.equals("Khuyến mãi trên hóa đơn")) {
        	String giaTriHoaDon = txtAddTien.getText();
            if (!kiemTraSoThucDuong(giaTriHoaDon)) {
                JOptionPane.showMessageDialog(this, "Giá trị hóa đơn phải là một số thực dương");
                flag = false;
            }
        }

       
        
       
    
		return flag;
	}

	 private boolean kiemTraNgayHopLe(String ngay) {
	        try {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	            LocalDate.parse(ngay, formatter);
	            return true;
	        } catch (DateTimeParseException e) {
	        	
	            return false;
	        }
	    }
	    //Kiểm tra số nguyên dương
	    private boolean kiemTraSoThucDuong(String str) {
	        if (str == null || str.isEmpty()) {
	            return false; // Hoặc true tùy thuộc vào yêu cầu của ứng dụng
	        }
	        try {
	            float number = Float.valueOf(str);
	            return number >= 0; // Số không âm là số dương hoặc số không
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }

	private void loadDataTableLeft()
	{
		List<Thuoc> listThuoc  = new ArrayList<Thuoc>();
		listThuoc = (new Dao_Thuoc()).listThuocNoDiscount();
		for(Thuoc thuoc : listThuoc)
		{
			modelLeft.addRow(new Object[]
					{
							thuoc.getMaThuoc(),
							thuoc.getTenThuoc(),
							thuoc.getGia()
					});
		}
	}
	public List<String> getListMaThuocDiscount()
	{
		List<String> listMaThuocDiscount = new ArrayList<String>();
		for(int i=0;i<tableRight.getRowCount();i++)
		{
			listMaThuocDiscount.add((String) modelRight.getValueAt(i, 0));
		}
		return listMaThuocDiscount;
	}
	public List<String> getListMaThuocNoDiscount(){
		List<String> listMaThuocNoDiscount = new ArrayList<String>();
		for(int i=0;i<tableLeft.getRowCount();i++)
		{
			listMaThuocNoDiscount.add((String) modelLeft.getValueAt(i, 0));
		}
		return listMaThuocNoDiscount;
	}
	
}
