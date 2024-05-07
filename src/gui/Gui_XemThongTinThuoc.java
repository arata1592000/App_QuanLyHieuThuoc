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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.Dao_Thuoc;
import entity.Thuoc;

public class Gui_XemThongTinThuoc extends JPanel implements ActionListener{
	private int widthComp;
	private int heightComp;
	private JPanel pCenter;
	private JPanel pSouth;
	private JPanel pTable;
	private JPanel pInput;
	private JLabel lbl1;
	private JLabel lbl2;
	private JTextField txtMaThuoc;
	private JTextField txtTenThuoc;
	private JLabel lbl3;
	private JLabel lbl4;
	private JDateChooser dateNgayNhapVe;
	private JDateChooser dateNgaySanXuat;
	private JLabel lbl5;
	private JLabel lbl6;
	private JDateChooser dateNgayHetHan;
	private JTextField txtNoiSanXuat;
	private JLabel lbl7;
	private JLabel lbl8;
	private JTextField txtGia;
	private JLabel lbl9;
	private JLabel lbl10;
	private JTextField txtThanhPhan;
	private JLabel lbl11;
	private JTextField txtSoLuong;
	private JPanel pButton;
	private JButton btnXoaThuoc;
	private JButton btnThemThuoc;
	private JButton btnSuaThuoc;
	private JPanel pAction;
	private JTextField txt1;
	private JLabel lbl12;
	private JLabel lbl13;
	private JDateChooser dateTuNgay;
	private JLabel lbl14;
	private JDateChooser dateDenNgay;
	private JTextField txtTim;
	private JButton btnTim;
	private JLabel lbl15;
	private DefaultTableModel dataModel;
	private JTable tableModel;
	private JScrollPane scroll;
	private JPanel pFormLeft;
	private JTextField txtDonViTinh;
	private JLayeredPane layeredPane;
	private JPanel pContent;
	private JPanel pForm;
	
	public Gui_XemThongTinThuoc(int width, int height) {
		// TODO Auto-generated constructor stub
		widthComp = width;
		heightComp = height;
//		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		pForm = new JPanel();
		initCompoent();
		loadDataTable();
	}
	
	public void initCompoent() {
		layeredPane = new JLayeredPane();
		pContent = new JPanel();
		pCenter = new JPanel();
		pInput = new JPanel();
		pFormLeft = new JPanel();
		lbl1 = new JLabel();
		lbl2 = new JLabel();
		txtMaThuoc = new JTextField(15);
		lbl3 = new JLabel();
		txtTenThuoc = new JTextField(15);
		lbl4 = new JLabel();;
		dateNgayNhapVe = new JDateChooser();
		lbl5 = new JLabel();
		dateNgaySanXuat = new JDateChooser();
		lbl6 = new JLabel();
		dateNgayHetHan = new JDateChooser();
		lbl7 = new JLabel();
		txtNoiSanXuat = new JTextField(15);
		lbl8 = new JLabel();
		txtGia = new JTextField(15);
		lbl9 = new JLabel();
		txtDonViTinh = new JTextField(15);
		lbl10 = new JLabel();
		txtThanhPhan = new JTextField(15);
		lbl11 = new JLabel();
		txtSoLuong = new JTextField(15);
		pButton = new JPanel();
		btnThemThuoc = new JButton();
		btnXoaThuoc = new JButton();
		btnSuaThuoc = new JButton();
		pSouth = new JPanel();
		pAction = new JPanel();
		lbl12 = new JLabel();
		txt1 = new JTextField();//
		lbl13 = new JLabel();
		dateTuNgay = new JDateChooser();
		lbl14 = new JLabel();
		dateDenNgay = new JDateChooser();
		txtTim = new JTextField();
		btnTim = new JButton();
		pTable = new JPanel();
		lbl15 = new JLabel();
		dataModel = new DefaultTableModel();
		String headers[] = {"Mã thuốc", "Tên thuốc", "Ngày nhập về", "Ngày sản xuất", "Ngày hết hạn","Nơi sản xuất", "Giá", "Đơn vị tính", "Thành phần", "Khuyến mãi", "Số  lượng", "Trạng thái"};
		dataModel = new DefaultTableModel(headers, 0);
		tableModel = new JTable(dataModel);
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 11));
		tableModel.setRowHeight(25);
		tableModel.setModel(dataModel);
		scroll = new JScrollPane(tableModel);
        
		layeredPane.setOpaque(true);
        layeredPane.setPreferredSize(new Dimension(widthComp, heightComp));
        layeredPane.setBackground(Color.WHITE);
        
		pContent.setOpaque(true);
        pContent.setBounds(0,0, widthComp, heightComp);
		pContent.setBackground(Color.WHITE);

		pCenter.setLayout(new BorderLayout());
		pCenter.setPreferredSize(new Dimension((int)(widthComp*0.95), (int)(heightComp*0.4)));
		
		pInput.setLayout(new FlowLayout(FlowLayout.LEFT));
		pInput.setPreferredSize(new Dimension((int)(widthComp*0.90), (int)(heightComp*0.3)) );
		Border titledBorder = BorderFactory.createTitledBorder("Biểu mẫu thông tin thuốc");
		if (titledBorder instanceof TitledBorder) {
		    // Lấy phông chữ của tiêu đề hiện tại
		    Font titleFont = ((TitledBorder) titledBorder).getTitleFont();
		    // Tạo một phông chữ mới với kích thước mới (ở đây tăng 4 điểm)
		    Font newTitleFont = titleFont.deriveFont(titleFont.getSize() + 13f);
		    // Đặt phông chữ mới cho tiêu đề
		    ((TitledBorder) titledBorder).setTitleFont(newTitleFont);
		}
		pInput.setBorder(titledBorder);
		pFormLeft.setLayout(new GridBagLayout());
		GridBagConstraints constraintsCustomer = new GridBagConstraints();
        constraintsCustomer.anchor = GridBagConstraints.WEST;
        constraintsCustomer.insets = new Insets(10, 20, 5, 0);
        lbl2.setText("Mã thuốc");
        lbl2.setFont(new Font("Arial", Font.PLAIN, 16));
        constraintsCustomer.gridx = 0;
        constraintsCustomer.gridy = 1;
        pFormLeft.add(lbl2, constraintsCustomer);
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 1;
        pFormLeft.add(txtMaThuoc, constraintsCustomer);
        lbl3.setText("Tên thuốc:");
        lbl3.setFont(new Font("Arial", Font.PLAIN, 16));
        constraintsCustomer.gridx = 0;
        constraintsCustomer.gridy = 2;
        pFormLeft.add(lbl3, constraintsCustomer);
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 2;
        pFormLeft.add(txtTenThuoc, constraintsCustomer);
        lbl4.setText("Ngày nhập về:");
        lbl4.setFont(new Font("Arial", Font.PLAIN, 16));
        constraintsCustomer.gridx = 0;
        constraintsCustomer.gridy = 3; 
        pFormLeft.add(lbl4, constraintsCustomer);
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 3;
        pFormLeft.add(dateNgayNhapVe, constraintsCustomer);
        lbl5.setText("Ngày sản xuất");
        lbl5.setFont(new Font("Arial", Font.PLAIN, 16));
        constraintsCustomer.gridx = 0;
        constraintsCustomer.gridy = 4; 
        pFormLeft.add(lbl5,constraintsCustomer);
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 4;
        pFormLeft.add(dateNgaySanXuat,constraintsCustomer);
        lbl6.setText("Ngày hết hạn");
        lbl6.setFont(new Font("Arial", Font.PLAIN, 16));
        constraintsCustomer.gridx = 0;
        constraintsCustomer.gridy = 5;
        pFormLeft.add(lbl6,constraintsCustomer);
        constraintsCustomer.gridx = 1;
        constraintsCustomer.gridy = 5;
        pFormLeft.add(dateNgayHetHan,constraintsCustomer);
        constraintsCustomer.gridx = 2;
        constraintsCustomer.gridy = 1;
        pFormLeft.add(Box.createHorizontalStrut(100),constraintsCustomer);
        lbl7.setText("Nơi sản xuất");
        lbl7.setFont(new Font("Arial", Font.PLAIN, 16));
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 1; 
        pFormLeft.add(lbl7,constraintsCustomer);
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 1;
        pFormLeft.add(txtNoiSanXuat,constraintsCustomer);
        lbl8.setText("Giá");
        lbl8.setFont(new Font("Arial", Font.PLAIN, 16));
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 2;
        pFormLeft.add(lbl8,constraintsCustomer);
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 2; 
        pFormLeft.add(txtGia, constraintsCustomer);
        lbl9.setText("Đơn vị tính");
        lbl9.setFont(new Font("Arial", Font.PLAIN, 16));
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 3;
        pFormLeft.add(lbl9, constraintsCustomer);
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 3;
        pFormLeft.add(txtDonViTinh, constraintsCustomer);
        lbl10.setText("Thành phần");
        lbl10.setFont(new Font("Arial", Font.PLAIN, 16));
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 4;
        pFormLeft.add(lbl10, constraintsCustomer);
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 4;
        pFormLeft.add(txtThanhPhan,constraintsCustomer);
        lbl11.setText("Số lượng");
        lbl11.setFont(new Font("Arial", Font.PLAIN, 16));
        constraintsCustomer.gridx = 3;
        constraintsCustomer.gridy = 5;
        pFormLeft.add(lbl11, constraintsCustomer);
        constraintsCustomer.gridx = 4;
        constraintsCustomer.gridy = 5;
        pFormLeft.add(txtSoLuong,constraintsCustomer);
        
        pButton.setBackground(Color.WHITE);
        btnThemThuoc.setText("Thêm thuốc");
        btnThemThuoc.setForeground(Color.BLACK);
		btnThemThuoc.setFont(new Font("Arial", Font.BOLD, 16));
		btnThemThuoc.setBackground(new Color(40,156,164));
		btnThemThuoc.setOpaque(true);
		btnThemThuoc.setContentAreaFilled(true);
        btnThemThuoc.setBorderPainted(false);
        btnThemThuoc.setFocusPainted(false);     
        btnThemThuoc.setForeground(Color.WHITE);
        btnXoaThuoc.setText("Xóa thuốc");
        btnXoaThuoc.setForeground(Color.BLACK);
		btnXoaThuoc.setFont(new Font("Arial", Font.BOLD, 16));
		btnXoaThuoc.setBackground(new Color(40,156,164));
		btnXoaThuoc.setOpaque(true);
		btnXoaThuoc.setContentAreaFilled(true);
        btnXoaThuoc.setBorderPainted(false);
        btnXoaThuoc.setFocusPainted(false);
        btnXoaThuoc.setForeground(Color.WHITE);
        
//        btnSuaThuoc.setText("Sửa thuốc");

		pSouth.setPreferredSize(new Dimension((int) (widthComp*0.95),(int) (heightComp*0.55)));
		pSouth.setBackground(Color.WHITE);
		pAction.setPreferredSize(new Dimension((int) (widthComp*0.95),(int) (heightComp*0.05)));
		pAction.setBackground(Color.WHITE);
		lbl12.setText("Lọc: ");
        lbl13.setText("Lọc ngày nhập về từ:");
        lbl14.setText("Đến ngày");
        txtTim.setText("Tìm thuốc theo mã");
        btnTim.setText("Tìm");
        lbl15.setText("Danh sách thuốc");
        lbl15.setFont(new Font("Arial", Font.ITALIC, 26));
		pTable.setPreferredSize(new Dimension((int) (widthComp*0.95),(int) (heightComp*0.49)));
		pTable.setLayout(new FlowLayout(FlowLayout.LEFT));
		pTable.setBackground(Color.WHITE);
		scroll.setPreferredSize(new Dimension((int) (widthComp*0.94),(int) (heightComp*0.40)));

		btnThemThuoc.addActionListener(this);
		btnXoaThuoc.addActionListener(this);
//		btnSuaThuoc.addActionListener(this);

		pInput.add(pFormLeft);
		pButton.add(btnThemThuoc);
		pButton.add(btnXoaThuoc);
//		pButton.add(btnSuaThuoc);
		pAction.add(lbl12);
		pAction.add(txt1);
		pAction.add(lbl13);
		pAction.add(dateTuNgay);
		pAction.add(lbl14);
		pAction.add(dateDenNgay);
		pAction.add(txtTim);
		pAction.add(btnTim);
		pCenter.add(pInput,BorderLayout.CENTER);
        pCenter.add(pButton,BorderLayout.SOUTH);
		pTable.add(lbl15); 
		pTable.add(scroll); 
		pSouth.add(pAction);
		pSouth.add(pTable);
		
		pContent.add(pCenter,BorderLayout.CENTER);
        pContent.add(pSouth,BorderLayout.SOUTH);
        pContent.setPreferredSize(getPreferredSize());
        
        
        layeredPane.add(pContent, JLayeredPane.DEFAULT_LAYER);
        this.add(layeredPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object act = e.getSource();
		if(act.equals(btnThemThuoc)) {
			Thuoc thuoc = new Thuoc(txtMaThuoc.getText(),
					txtTenThuoc.getText(),
					dateNgayNhapVe.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					dateNgaySanXuat.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					dateNgayHetHan.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					txtNoiSanXuat.getText(),
					Float.parseFloat(txtGia.getText()),
					txtDonViTinh.getText(),
					txtThanhPhan.getText(),
					Integer.parseInt(txtSoLuong.getText()));
			try {
				if ((new Dao_Thuoc()).addThuoc(thuoc)) {
					addRowThuoc(thuoc);
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}else if(act.equals(btnXoaThuoc)) {
	        int selectRow = tableModel.getSelectedRow();
	        // Kiểm tra nếu có hàng được chọn
	        if (selectRow != -1) {
        		String maThuoc = (String) tableModel.getValueAt(selectRow, 0);
	        	if ((new Dao_Thuoc()).removeThuoc(maThuoc)) {
		            ((DefaultTableModel) tableModel.getModel()).removeRow(selectRow);
	            // Xóa hàng từ  JTable
	        	}else {
		            JOptionPane.showMessageDialog(null, "Hệ thống đang xảy ra lỗi");
	        	}
	        } else {
	            // Hiển thị thông báo nếu không có hàng nào được chọn
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
	        }
		}else if(act.equals(btnSuaThuoc)) {
		}
	}
	
	public void addRowThuoc(Thuoc thuoc ) {
		dataModel.addRow(new Object[] {thuoc.getMaThuoc(),
				thuoc.getTenThuoc(),
				thuoc.getNgayNhapVe(),
				thuoc.getNgaySanXuat(),
				thuoc.getNgayHetHan(),
				thuoc.getNoiSanXuat(),
				thuoc.getGia()+"",
				thuoc.getDonViTinh(),
				thuoc.getThanhPhan(),
				thuoc.getKhuyenMai().getMaKM(),
				thuoc.getSoLuong()+"",
				thuoc.getTrangThai()});
	}
	
	public void loadDataTable() {
		List<Thuoc> listThuoc = new ArrayList();
		listThuoc = (new Dao_Thuoc()).readThuocFromSQL();
		for (Thuoc thuoc : listThuoc) {
			addRowThuoc(thuoc);
		}
	}
}
