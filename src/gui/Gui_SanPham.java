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
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import utils.ButtonEditor;
import utils.ButtonRenderer;

public class Gui_SanPham extends JPanel{
	private int widthComp;
	private int heightComp;
	private JPanel pMenu;
	private JButton btnBanThuoc;
	private JButton btnDoiTraThuoc;
	private JButton btnXemThongTin;
	private JPanel pTable;
	private JPanel pMain;
	private JTextField txtInput;
	private JButton btnThem;
	private JPanel pInfor;
	private JPanel pInforCustomer;
	private JLabel lbl2;
	private JLabel lbl1;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JPanel pInforOrder;
	private JLabel lbl3;
	private JLabel lbl4;
	private JTextField txtMaHD;
	private JTextField txtNgayLap;
	private JLabel lbl5;
	private JLabel lbl6;
	private JTextField txtTongTien;
	private JLabel lbl7;
	private JTextField txtVAT;
	private JLabel lbl8;
	private JLabel lbl9;
	private JTextField txtMaKM;
	private JTextField txtThanhTien;
	private JLabel lbl10;
	private JTextField txtTienKhachDua;
	private JLabel lbl11;
	private JTextField txtTienThua;
	private JPanel pButtonOrder;
	private JButton btnThanhToan;
	private JButton btnLamMoi;
	private JTextField txtPhuongThucThanhToan;
	private JLabel lbl12;
	private JLabel lbl13;
	private DefaultTableModel dataModel;
	private JTable tableModel;
	private Object constraints;

	public Gui_SanPham(int width, int height) {
		widthComp = width;
		heightComp = height;
		
		pMenu = new JPanel();
		btnBanThuoc = new JButton();
		btnDoiTraThuoc = new JButton();
		btnXemThongTin = new JButton();
		pMain = new JPanel();
		pTable = new JPanel();
		txtInput = new JTextField();
		btnThem = new JButton();
		String headers[] = {"Mã thuốc", "Tên thuốc", "Số lượng", "Đơn vị tính", "Giá bán", "KM", "Tổng tiền", "Xóa"};
		dataModel = new DefaultTableModel(headers, 0);
		Object[] newRow = {"", ""};
		dataModel.addRow(newRow);
		tableModel = new JTable(dataModel);
		tableModel.getColumn("Xóa").setPreferredWidth(10);
		tableModel.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
		tableModel.getColumn("Xóa").setCellEditor(new ButtonEditor(new JCheckBox()));
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 13));
		tableModel.setRowHeight(30);
		tableModel.setRowHeight(tableModel.getRowCount()-1, 1);
		JScrollPane pane = new JScrollPane(tableModel);
		tableModel.addComponentListener(new ComponentListener() {
			
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				updateInforOrder();

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				updateInforOrder();

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		pInfor = new JPanel();
		pInforCustomer = new JPanel();
		lbl1 = new JLabel();
		lbl2 = new JLabel();
		txtHoTen = new JTextField(10);
		txtSDT = new JTextField(10);
		pInforOrder = new JPanel();
		lbl3 = new JLabel();
		lbl4 = new JLabel();
		lbl5 = new JLabel();
		txtMaHD = new JTextField(10);
		lbl6 = new JLabel();
		txtNgayLap = new JTextField(10);
		lbl7 = new JLabel();
		txtTongTien = new JTextField(10);
		lbl8 = new JLabel();
		txtVAT = new JTextField(10);
		lbl9 = new JLabel();
		txtMaKM = new JTextField(10);
		lbl10 = new JLabel();
		txtThanhTien = new JTextField(10);
		lbl11 = new JLabel();
		txtPhuongThucThanhToan = new JTextField(10);
		lbl12 = new JLabel();
		txtTienKhachDua = new JTextField(10);
		lbl13 = new JLabel();
		txtTienThua = new JTextField(10);
		pButtonOrder = new JPanel();
		btnThanhToan = new JButton();
		btnLamMoi = new JButton();
		
        
		
		pMain.setLayout(new BorderLayout());
		pTable.setBackground(Color.WHITE);
		btnBanThuoc.setText("Bán thuốc");
		btnDoiTraThuoc.setText("Đổi trả thuốc");
		btnXemThongTin.setText("Xem thông tin");
		btnThem.setText("Thêm");
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tableModel.setRowHeight(tableModel.getRowCount()-1, 30);
				
				dataModel.setValueAt("KH003", tableModel.getRowCount()-1, 0);
				dataModel.setValueAt("Thuốc phá thai", tableModel.getRowCount()-1, 1);
				dataModel.setValueAt("8", tableModel.getRowCount()-1, 2);
				dataModel.setValueAt("Hộp", tableModel.getRowCount()-1, 3);
				dataModel.setValueAt("1500000", tableModel.getRowCount()-1, 4);
				dataModel.setValueAt("0", tableModel.getRowCount()-1, 5);
				dataModel.setValueAt("1500000", tableModel.getRowCount()-1, 6);


				dataModel.addRow(new Object[] {"", "", "", "", 0, "", ""});
				tableModel.setRowHeight(tableModel.getRowCount()-1, 1);
				updateInforOrder();
				revalidate();
			}
		});
		
		//Form thông tin khách hàng
		GridBagConstraints constraintsCustomer = new GridBagConstraints();//khởi tạo constraintsCustomer để thiết lập vị trí cho các trường
        constraintsCustomer.insets = new Insets(5, 1, 5, 1);
		pInforCustomer.setLayout(new GridBagLayout());
		lbl1.setText("Thông tin khách hàng");
		lbl1.setFont(new Font("Arial", Font.ITALIC, 20));
		constraintsCustomer.gridx = 0;
        constraintsCustomer.gridy = 0;
        constraintsCustomer.gridwidth = 2;
        constraintsCustomer.anchor = GridBagConstraints.CENTER;//Thiết lập căn giữa cho lbl
        pInforCustomer.add(lbl1, constraintsCustomer);
        constraintsCustomer.gridwidth = 1;
        constraintsCustomer.anchor = GridBagConstraints.WEST;//Thiết lập căn trái cho content
        lbl2.setText("Tên khách hàng:");//Field Họ tên
        lbl2.setFont(new Font("Arial", Font.PLAIN, 14));;
        constraintsCustomer.gridx = 0;
        constraintsCustomer.gridy = 1;
        pInforCustomer.add(lbl2, constraintsCustomer);
        constraintsCustomer.gridx = 1;
        txtHoTen.setFont(new Font("Arial", Font.PLAIN, 14));
        pInforCustomer.add(txtHoTen, constraintsCustomer);
        lbl3.setText("Số điện thoại:");//Field SĐT
        lbl3.setFont(new Font("Arial", Font.PLAIN, 14));;
        constraintsCustomer.gridx = 0;
        constraintsCustomer.gridy = 2;
        pInforCustomer.add(lbl3, constraintsCustomer);
        constraintsCustomer.gridx = 1;
        txtSDT.setFont(new Font("Arial", Font.PLAIN, 14));
        pInforCustomer.add(txtSDT, constraintsCustomer);
     
        //Form thông tin hóa đơn
        pInforOrder.setLayout(new GridBagLayout());
        GridBagConstraints constraintsOrder = new GridBagConstraints();
        constraintsOrder.insets = new Insets(5, 1, 5, 1);
        lbl4 = new JLabel("Thông Tin Hóa Đơn");
        lbl4.setFont(new Font("Arial", Font.ITALIC, 20));
        constraintsOrder.gridx = 0;
        constraintsOrder.gridy = 0;
        constraintsOrder.gridwidth = 2;
        constraintsOrder.anchor = GridBagConstraints.CENTER;
        pInforOrder.add(lbl4, constraintsOrder);
        constraintsOrder.gridwidth = 1;
        constraintsOrder.anchor = GridBagConstraints.WEST;
        lbl5.setText("Mã hóa đơn:");        //Field mã HD
        lbl5.setFont(new Font("Arial", Font.PLAIN, 14));;
        constraintsOrder.gridx = 0;
        constraintsOrder.gridy = 1;
        pInforOrder.add(lbl5, constraintsOrder);
        constraintsOrder.gridx = 1;
        txtMaHD.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMaHD.setEditable(false);
        txtMaHD.setBorder(null);
        pInforOrder.add(txtMaHD, constraintsOrder);
        lbl6.setText("Ngày lập:");       //Field ngày lập
        lbl6.setFont(new Font("Arial", Font.PLAIN, 14));;
        constraintsOrder.gridx = 0;
        constraintsOrder.gridy = 2;
        pInforOrder.add(lbl6, constraintsOrder);
        constraintsOrder.gridx = 1;
        txtNgayLap.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNgayLap.setEditable(false);
        txtNgayLap.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        txtNgayLap.setBorder(null);
        pInforOrder.add(txtNgayLap, constraintsOrder);
        lbl7.setText("Tổng tiền:");          //Field Tổng tiền
        lbl7.setFont(new Font("Arial", Font.PLAIN, 14));;
        constraintsOrder.gridx = 0;
        constraintsOrder.gridy = 3;
        pInforOrder.add(lbl7, constraintsOrder);
        constraintsOrder.gridx = 1;
        txtTongTien.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTongTien.setEditable(false);
        txtTongTien.setText(getTongTienHD()+"");
        txtTongTien.setBorder(null);
        pInforOrder.add(txtTongTien, constraintsOrder);
        lbl8.setText("VAT:");      //Field VAT
        lbl8.setFont(new Font("Arial", Font.PLAIN, 14));;
        constraintsOrder.gridx = 0;
        constraintsOrder.gridy = 4;
        pInforOrder.add(lbl8, constraintsOrder);
        constraintsOrder.gridx = 1;
        txtVAT.setFont(new Font("Arial", Font.PLAIN, 14));
        txtVAT.setEditable(false);
        txtVAT.setText("3%");
        txtVAT.setBorder(null);
        pInforOrder.add(txtVAT, constraintsOrder);
        lbl9.setText("Mã KM:");        //Field Mã KM
        lbl9.setFont(new Font("Arial", Font.PLAIN, 14));;
        constraintsOrder.gridx = 0;
        constraintsOrder.gridy = 5;
        pInforOrder.add(lbl9, constraintsOrder);
        constraintsOrder.gridx = 1;
        txtMaKM.setFont(new Font("Arial", Font.PLAIN, 14));
        pInforOrder.add(txtMaKM, constraintsOrder);
        lbl10.setText("Thành tiền:");      //Field Thành tiền
        lbl10.setFont(new Font("Arial", Font.PLAIN, 14));;
        constraintsOrder.gridx = 0;
        constraintsOrder.gridy = 6;
        pInforOrder.add(lbl10, constraintsOrder);
        constraintsOrder.gridx = 1;
        txtThanhTien.setFont(new Font("Arial", Font.PLAIN, 14));
        txtThanhTien.setEditable(false);
        txtThanhTien.setText(0+"");
        txtThanhTien.setBorder(null);
        pInforOrder.add(txtThanhTien, constraintsOrder);
        lbl11.setText("Phương thức:");//Field Phương thức thanh toán
        lbl11.setFont(new Font("Arial", Font.PLAIN, 14));;
        constraintsOrder.gridx = 0;
        constraintsOrder.gridy = 7;
        pInforOrder.add(lbl11, constraintsOrder);
        constraintsOrder.gridx = 1;
        txtPhuongThucThanhToan.setFont(new Font("Arial", Font.PLAIN, 14));
        pInforOrder.add(txtPhuongThucThanhToan, constraintsOrder);
        lbl12.setText("Tiền khách đưa:");       //Field Tiền khách đưa
        lbl12.setFont(new Font("Arial", Font.PLAIN, 14));;
        constraintsOrder.gridx = 0;
        constraintsOrder.gridy = 8;
        pInforOrder.add(lbl12, constraintsOrder);
        constraintsOrder.gridx = 1;
        txtTienKhachDua.setFont(new Font("Arial", Font.PLAIN, 14));
        pInforOrder.add(txtTienKhachDua, constraintsOrder);
        lbl13.setText("Tiền thừa:");       //Field Tiền thừa
        lbl13.setFont(new Font("Arial", Font.PLAIN, 14));;
        constraintsOrder.gridx = 0;
        constraintsOrder.gridy = 9;
        pInforOrder.add(lbl13, constraintsOrder);
        constraintsOrder.gridx = 1;
        txtTienThua.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTienThua.setEditable(false);
        txtTienThua.setText(0+"");
        txtTienThua.setBorder(null);
        pInforOrder.add(txtTienThua, constraintsOrder);
        
        btnThanhToan.setText("Thanh toán");
        btnLamMoi.setText("Làm mới");
        
        pMenu.setBackground(Color.WHITE);
        pInfor.setLayout(new FlowLayout(FlowLayout.CENTER));
        pInfor.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		
		pMenu.add(btnBanThuoc);
		pMenu.add(Box.createHorizontalStrut(20));
		pMenu.add(btnDoiTraThuoc);
		pMenu.add(Box.createHorizontalStrut(20));
		pMenu.add(btnXemThongTin);
		pTable.add(txtInput);
		pTable.add(btnThem);
		pTable.add(pane);
		pButtonOrder.add(btnThanhToan);
		pButtonOrder.add(btnLamMoi);
		pInfor.add(pInforCustomer);
		pInfor.add(Box.createHorizontalStrut(100));
		pInfor.add(pInforOrder);
		pInfor.add(Box.createHorizontalStrut(100));
		pInfor.add(pButtonOrder);
		pMain.add(pTable, BorderLayout.CENTER);
		pMain.add(pInfor, BorderLayout.EAST);
		this.add(pMenu, BorderLayout.NORTH);
		this.add(pMain, BorderLayout.CENTER);
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				pMenu.setPreferredSize(new Dimension((int) (widthComp),(int) (heightComp*0.1)));
				pMain.setPreferredSize(new Dimension((int) (widthComp),(int) (heightComp*0.9)));
				pTable.setPreferredSize(new Dimension((int)(widthComp*0.8),(int) (heightComp*0.85)));
				pane.setPreferredSize(new Dimension((int)(widthComp*0.75),(int)(heightComp*0.8)));
				pInfor.setPreferredSize(new Dimension((int) (widthComp*0.2), (int)(heightComp*0.85)));
				pInforCustomer.setPreferredSize(new Dimension((int) (widthComp*0.18), (int)(heightComp*0.15)));
				pInforOrder.setPreferredSize(new Dimension((int) (widthComp*0.18), (int)(heightComp*0.6)));
				pButtonOrder.setPreferredSize(new Dimension((int) (widthComp*0.18), (int)(heightComp*0.1)));
				revalidate();
			}
		});
	}
	
	public void updateInforOrder() {
		txtTongTien.setText(getTongTienHD()+"");
	}
	
	public float getTongTienHD() {
		float tongTienHD = 0;
		for (int i = 0 ; i < tableModel.getRowCount()-1 ; i++) {
			tongTienHD+=Float.parseFloat((String) dataModel.getValueAt(i, 6));
		}
		return tongTienHD;
	}
}
