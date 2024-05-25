package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.openxmlformats.schemas.drawingml.x2006.diagram.DataModelDocument;

import com.toedter.calendar.JDateChooser;

public class Gui_QuanLyKetCa extends JPanel{

	private int widthComp;
	private int heightComp;
	private JPanel pFillter;
	private JPanel pMain;
	private JPanel pLeft;
	private JPanel pRight;
	private JPanel pTable1;
	private DefaultTableModel dataModel;
	private JTable tableModel;
	private JLabel lblLocNgay;
	private JComboBox comboBoxLoc;
	private DefaultTableModel dataModel2;
	private JTable tableModel2;
	private JPanel pTable2;
	private JLabel lblTieuDe;
	private JDateChooser dateTheoNgay;
	private JLabel lblLocMa;
	private JTextField txtLocMa;

	public Gui_QuanLyKetCa(int widthComp, int heightComp) {
		this.widthComp = widthComp;
		this.heightComp = heightComp;
		this.setLayout(new BorderLayout());
		initCompoent();
	}

	public void initCompoent() {
		pFillter = new JPanel();
		pMain = new JPanel();
		pLeft = new JPanel();
		pRight = new JPanel();
		pTable1 = new JPanel();
		pTable2 = new JPanel();
		lblTieuDe = new JLabel("Danh sách ... ");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblLocMa = new JLabel("Lọc theo mã:");
		lblLocMa.setFont(new Font("Arial", Font.BOLD, 20));
		txtLocMa = new JTextField(20);
		lblLocNgay = new JLabel("Lọc theo ngày : ");
		lblLocNgay.setFont(new Font("Arial", Font.BOLD, 20));
        dateTheoNgay = new JDateChooser();
        dateTheoNgay.setPreferredSize(new Dimension(135,25));
		
		String headers[] = {"Mã", "Mã nhân viên", "Ngày lập", "Tiền trong ca", "Tiền thu trong ca"};
		dataModel = new DefaultTableModel(headers, 0);
		tableModel = new JTable(dataModel);
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 13));
		tableModel.setRowHeight(30);
		JScrollPane pane = new JScrollPane(tableModel);
		
		String headers2[] = {"Mệnh giá", "Số lượng"};
		dataModel2 = new DefaultTableModel(headers2, 0);
		tableModel2 = new JTable(dataModel2);
		tableModel2.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
		tableModel2.setFont(new Font("Arial", Font.PLAIN, 13));
		tableModel2.setRowHeight(30);
		JScrollPane pane2 = new JScrollPane(tableModel2);
		
		pTable1.setPreferredSize(new Dimension((int)(widthComp*0.59), (int)(heightComp*0.7)));
		pTable1.setLayout(new BoxLayout(pTable1, BoxLayout.Y_AXIS));
		pTable1.setBorder(new EmptyBorder(10, 10, 10, 10)); 
		
		pTable2.setPreferredSize(new Dimension((int)(widthComp*0.27), (int)(heightComp*0.7)));
		pTable2.setLayout(new BoxLayout(pTable2, BoxLayout.Y_AXIS));
		pTable2.setBorder(new EmptyBorder(10, 10, 10, 10)); 
		
		this.setBackground(Color.white);
		pMain.setBackground(Color.WHITE);
		pMain.setPreferredSize(new Dimension((int)(widthComp*0.98), (int)(heightComp*0.85)));
		pFillter.setBackground(Color.white);
		pFillter.setPreferredSize(new Dimension((int)(widthComp*0.98), (int)(heightComp*0.1)));
		pFillter.setLayout(new FlowLayout(FlowLayout.LEFT));
		pLeft.setBackground(Color.WHITE);
		pLeft.setPreferredSize(new Dimension((int)(widthComp*0.62), (int)(heightComp*0.95)));
		pRight.setBackground(Color.WHITE);
		pRight.setPreferredSize(new Dimension((int)(widthComp*0.30), (int)(heightComp*0.95)));
		
		pFillter.add(Box.createHorizontalStrut(50));
		pFillter.add(lblLocMa);
		pFillter.add(txtLocMa);
		pFillter.add(Box.createHorizontalStrut(150));
		pFillter.add(lblLocNgay);
		pFillter.add(dateTheoNgay);
		pTable1.add(pane);
		pTable2.add(pane2);
		pLeft.add(pTable1);
		pRight.add(pTable2);
		pMain.add(pFillter);
		pMain.add(pLeft);
		pMain.add(pRight);
		add(pMain);
	}

}
