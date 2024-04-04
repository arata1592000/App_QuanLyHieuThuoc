package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Properties;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import com.toedter.calendar.JDateChooser;
public class Gui_HoaDon extends JPanel {
	private JPanel pTable;
	private JPanel pAction;
	private JLabel lbl1;
	private DefaultTableModel dataModel;
	private JTable tableModel;
	private JTextField txtSearch;
	private JButton btnTim;
	private JPanel pSearch;
	private JPanel pFillDate;
	private Component dateChooserDate;
	private JLabel lbl2;
	public Gui_HoaDon(int widthComp, int heightComp) {
		super();
		// TODO Auto-generated constructor stub
		pAction = new JPanel();
		pSearch = new JPanel();
		txtSearch = new JTextField();
		btnTim = new JButton();
		pFillDate = new JPanel();
		lbl1 = new JLabel();
        dateChooserDate = new JDateChooser();
        pTable = new JPanel();
		lbl2 = new JLabel();
		
		pSearch.setBackground(Color.WHITE);
		pFillDate.setBackground(Color.WHITE);
		pAction.setBackground(Color.WHITE);
		pAction.setPreferredSize(new Dimension((int) (widthComp*0.95),(int) (heightComp*0.1)));
		txtSearch.setPreferredSize(new Dimension(150, 25));
		btnTim.setText("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Arial", Font.BOLD, 16));
		btnTim.setBackground(new Color(40,156,164));
		btnTim.setOpaque(true);
		btnTim.setContentAreaFilled(true);
        btnTim.setBorderPainted(false);
        btnTim.setFocusPainted(false);
		lbl1.setText("Lọc theo ngày lập:");
		dateChooserDate.setPreferredSize(new Dimension(200,25));
		pTable.setLayout(new FlowLayout());
		pTable.setPreferredSize(new Dimension((int) (widthComp*0.95),(int) (heightComp*0.8)));
		lbl2.setText("Danh sách hóa đơn");
		lbl2.setFont(new Font("Arial", Font.ITALIC, 30));
		String headers[] = {"Mã hóa đơn", "Ngày lập", "Tên nhân viên", "Tên KH", "Tổng thanh toán", "Xem chi tiết", "Loại HĐ", "Ghi chú"};
		Object[][] data = {
				{"HD2312220001", "23/03/2024", "Nguyễn Hạnh Bảo Ân", "Trân", "310.000", "Xem", "Bán hàng", null},
                {"HD2312220002", "23/03/2024", "Nguyễn Hạnh Bảo Ân", "Trân", "0", "Xem", "Đổi","Đổi từ HD2312220001, Lý do: Thuốc hết hạn"}
        };
		dataModel = new DefaultTableModel( data, headers);
		tableModel = new JTable(dataModel);
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 15));
		tableModel.setRowHeight(30);
		tableModel.getColumn("Ngày lập").setPreferredWidth(20);
		tableModel.getColumn("Tên KH").setPreferredWidth(20);
		tableModel.getColumn("Loại HĐ").setPreferredWidth(20);
		JScrollPane pane = new JScrollPane(tableModel);
		pane.setPreferredSize(new Dimension((int)(widthComp*0.9),(int) (heightComp*0.7)));
		
		pSearch.add(txtSearch);
		pSearch.add(btnTim);
		pFillDate.add(lbl1);
		pFillDate.add(dateChooserDate);
		pAction.add(pSearch);
		pAction.add(Box.createHorizontalStrut(300));
		pAction.add(pFillDate);
		pTable.add(lbl2);
		pTable.add(pane);
		this.setLayout(new BorderLayout());
		this.add(pAction, BorderLayout.NORTH);
		this.add(pTable, BorderLayout.CENTER);
		
	}
}
