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
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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





public class Gui_KhachHang extends JPanel{

	private JPanel pTable;
	private JPanel pAction;
	private JLabel lbl1;
	private DefaultTableModel dataModel;
	private JTable tableModel;

	private int widthComp;
	private int heightComp;
	private JTextField txtSearch;
	private JButton btnSearch;
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
		super();
		// TODO Auto-generated constructor stub
		pAction = new JPanel();
		pSearch = new JPanel();
		txtSearch = new JTextField();
		btnSearch = new JButton();
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
		txtSearch.setPreferredSize(new Dimension(150, 25));
		btnSearch.setText("Tìm");
		lbl1.setText("Lọc từ ngày:");
		dateChooserStartDate.setPreferredSize(new Dimension(200,25));
		lbl2.setText("Đến ngày:");
		dateChooserEndDate.setPreferredSize(new Dimension(200,25));
		pTable.setLayout(new FlowLayout());
		lbl3.setText("Danh sách khách hàng");
		lbl3.setFont(new Font("Arial", Font.ITALIC, 30));
		String headers[] = {"Mã khách hàng", "Họ và tên", "Số điện thoại", "Ngày mua gần nhất", "Số lần mua"};
		Object[][] data = {
                {"KH001", "John Doe", "123456789", "2024-03-30", 5},
                {"KH002", "Jane Smith", "987654321", "2024-03-28", 3},
                {"KH003", "Tom Brown", "456123789", "2024-03-25", 2}
        };
		dataModel = new DefaultTableModel( data, headers);
		tableModel = new JTable(dataModel);
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 16));
		tableModel.setRowHeight(30);
		JScrollPane pane = new JScrollPane(tableModel);
		
		pSearch.add(txtSearch);
		pSearch.add(btnSearch);
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
		
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				pAction.setPreferredSize(new Dimension((int) (widthComp*0.95),(int) (heightComp*0.1)));
				pTable.setPreferredSize(new Dimension((int) (widthComp*0.95),(int) (heightComp*0.9)));
				pane.setPreferredSize(new Dimension((int)(widthComp*0.9),(int) (pTable.getHeight()*1.3)));
				revalidate();
			}
		});
	}

	
	
}
