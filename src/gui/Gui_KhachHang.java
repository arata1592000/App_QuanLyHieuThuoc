package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.toedter.calendar.JDateChooser;

import dao.Dao_KhachHang;
import dao.Dao_Thuoc;
import entity.KhachHang;
import entity.Thuoc;





public class Gui_KhachHang extends JPanel{

	private JPanel pTable;
	private JPanel pAction;
	private JLabel lbl1;
	private DefaultTableModel dataModel;
	private JTable tableModel;

	private int widthComp;
	private int heightComp;
	private JTextField txtSearch;
	private JButton btnTim;
	private JPanel pSearch;
	private JPanel pFillDate;
	private JLabel lbl2;
	private Properties p;
	private JDatePanelImpl datePanel;
	private UtilDateModel model;
	private JButton showDatePickerButton;
	private JPopupMenu datePickerPopup;
	private JDatePickerImpl datePicker;
	private JTextField dateTextField;
	private Component dateChooserStartDate;
	private JLabel lbl3;
	private JDateChooser dateChooserEndDate;
	public Gui_KhachHang(int widthComp, int heightComp) {
		// TODO Auto-generated constructor stub
		this.widthComp = widthComp;
		this.heightComp = heightComp;
		initCompoent();
		loadDataTable();
	}
	public void initCompoent() {
		pAction = new JPanel();
		pSearch = new JPanel();
		txtSearch = new JTextField(20);
		btnTim = new JButton();
		pFillDate = new JPanel();
		lbl1 = new JLabel();
        dateChooserStartDate = new JDateChooser();
        lbl2 = new JLabel();
        dateChooserEndDate = new JDateChooser();
        pTable = new JPanel();
		lbl3 = new JLabel();
		
		pSearch.setBackground(Color.WHITE);
		pFillDate.setBackground(Color.WHITE);
		pAction.setBackground(Color.WHITE);
		pAction.setPreferredSize(new Dimension((int) (widthComp*0.95),(int) (heightComp*0.1)));
		txtSearch.setPreferredSize(new Dimension(150, 25));
		txtSearch.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSearch.setText("Nhập mã hoặc tên của khách hàng");
		btnTim.setText("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Arial", Font.BOLD, 15));
		btnTim.setBackground(new Color(40,156,164));
		btnTim.setOpaque(true);
		btnTim.setContentAreaFilled(true);
        btnTim.setBorderPainted(false);
        btnTim.setFocusPainted(false);
		lbl1.setText("Lọc từ ngày:");
		dateChooserStartDate.setPreferredSize(new Dimension(200,25));
		lbl2.setText("Đến ngày:");
		dateChooserEndDate.setPreferredSize(new Dimension(200,25));
		pTable.setLayout(new FlowLayout());
		pTable.setPreferredSize(new Dimension((int) (widthComp*0.95),(int) (heightComp*0.8)));
		lbl3.setText("Danh sách khách hàng");
		lbl3.setFont(new Font("Arial", Font.ITALIC, 30));
		String headers[] = {"Mã khách hàng", "Họ và tên", "Số điện thoại"};
		dataModel = new DefaultTableModel(headers, 0) {
			@Override
            public boolean isCellEditable(int row, int column) {
                // Đặt tất cả các ô không thể chỉnh sửa
                return false;
            }
		};
		tableModel = new JTable(dataModel);
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 16));
		tableModel.setRowHeight(30);
		JScrollPane pane = new JScrollPane(tableModel);
		pane.setPreferredSize(new Dimension((int)(widthComp*0.9),(int) (heightComp*0.7)));
		
		pSearch.add(txtSearch);
		pSearch.add(btnTim);
		pFillDate.add(lbl1);
		pFillDate.add(dateChooserStartDate);
		pFillDate.add(lbl2);
		pFillDate.add(dateChooserEndDate);
		pAction.add(pSearch);
		pAction.add(Box.createHorizontalStrut(300));
		pAction.add(pFillDate);
		pTable.add(lbl3);
		pTable.add(pane);
		this.setLayout(new BorderLayout());
		this.add(pAction, BorderLayout.NORTH);
		this.add(pTable, BorderLayout.CENTER);
		txtSearch.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (txtSearch.getText().equals("")) {
                	txtSearch.setText("Nhập mã hoặc tên của khách hàng");
                }
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (txtSearch.getText().equals("Nhập mã hoặc tên của khách hàng")) {
                	txtSearch.setText("");
                }
			}
		});

	}
	
	public void addRowKhachHang(KhachHang kh) {
		dataModel.addRow(new Object[] {kh.getMaKH(),
				kh.getHoTen(),
				kh.getsDT()}
				);
	}
	
	public void loadDataTable() {
		List<KhachHang> listKH = new ArrayList();
		listKH = (new Dao_KhachHang()).readKhachHangFromSQL();
		for (KhachHang kh : listKH) {
			addRowKhachHang(kh);
		}
	}
	
	
}
