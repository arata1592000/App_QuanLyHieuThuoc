package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.util.PaneInformation;

import entity.NhanVien;

public class Gui_KetCa extends JPanel implements ActionListener{

	private int widthComp;
	private int heightComp;
	private JPanel pMain;
	private JPanel pLeft;
	private JPanel pRight;
	private JPanel pTable;
	private JLabel lblMa;
	private JLabel lblNV;
	private JLabel lblTien;
	private JLabel lblDT;
	private JLabel lblATM;
	private JLabel lblTienLay;
	private JLabel lblCL;
	private DefaultTableModel dataModel;
	private JTable tableModel;
	private JScrollPane pane;
	private JPanel pAction;
	private JButton btnKetCa;
	private JPanel pInfor;
	private JLabel lblTua;
	private JLabel lblTong;
	public Gui_KetCa(int width, int height) {
		widthComp = width;
		heightComp = height;
		initCompoent();
	}
	public void initCompoent() {
		pMain = new JPanel();
		pLeft = new JPanel();
		pRight = new JPanel();
		pTable = new JPanel();
		pAction = new JPanel();
		pInfor = new JPanel();
		lblMa = new JLabel("Mã kết ca : ");
		lblMa.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNV = new JLabel("Nhân viên : ");
		lblNV.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTien = new JLabel("Tiền mặt : ");
		lblTien.setFont(new Font("Arial:", Font.PLAIN, 20));
		lblDT = new JLabel("Doanh thu : ");
		lblDT.setFont(new Font("Arial", Font.PLAIN, 20));
		lblATM = new JLabel("ATM : ");
		lblATM.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTienLay = new JLabel("Tiền lấy ra : ");
		lblTienLay.setFont(new Font("Arial:", Font.PLAIN, 20));
		lblCL = new JLabel("Chênh lệch : ");
		lblCL.setFont(new Font("Arial", Font.PLAIN, 20));

		lblTua = new JLabel("Chi Tiết Kiểm Ca");
		lblTua.setFont(new Font("Arial:", Font.BOLD, 25));
		lblTong = new JLabel("Tổng: ");
		lblTong.setFont(new Font("Arial:", Font.PLAIN, 20));
		String headers[]= {"STT","Mệnh giá","Số lượng","Tổng"};
		dataModel = new DefaultTableModel(headers,0);
		tableModel = new JTable(dataModel);
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
		tableModel.setRowHeight(30);
		pane = new JScrollPane(tableModel);
		btnKetCa = new JButton("Thông Tin Kết Ca");
		btnKetCa.setPreferredSize(new Dimension(250, 40));
		btnKetCa.setForeground(Color.WHITE);
		btnKetCa.setFont(new Font("Arial", Font.BOLD, 20));
		btnKetCa.setBackground(new Color(40,156,164));
		btnKetCa.setOpaque(true);
		btnKetCa.setContentAreaFilled(true);
		btnKetCa.setBorderPainted(false);
		btnKetCa.setFocusPainted(false);
		this.setBackground(Color.white);
		
		pMain.setBackground(Color.white);
		pMain.setPreferredSize(new Dimension((int)(widthComp*0.98), (int)(heightComp*0.85)));
		pLeft.setBackground(Color.WHITE);
		pLeft.setPreferredSize(new Dimension((int)(widthComp*0.55), (int)(heightComp*0.95)));
		pRight.setPreferredSize(new Dimension((int)(widthComp*0.37), (int)(heightComp*0.95)));
		
		Border titledBorder = BorderFactory.createTitledBorder("Thông Tin Kết Ca");
		if (titledBorder instanceof TitledBorder) {
		    Font titleFont = ((TitledBorder) titledBorder).getTitleFont();
		    Font newTitleFont = titleFont.deriveFont(titleFont.getSize() + 15f);
		    ((TitledBorder) titledBorder).setTitleFont(newTitleFont);
		}
		Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border compoundBorder = BorderFactory.createCompoundBorder(titledBorder, emptyBorder);
		
		pInfor.setPreferredSize(new Dimension((int)(widthComp*0.35), (int)(heightComp*0.7)));
		pInfor.setLayout(new BoxLayout(pInfor, BoxLayout.Y_AXIS));
		pInfor.setBorder(compoundBorder);
		pAction.setPreferredSize(new Dimension((int)(widthComp*0.35), (int)(heightComp*0.06)));
		
		pTable.setPreferredSize(new Dimension((int)(widthComp*0.52), (int)(heightComp*0.85)));
		pTable.setLayout(new BoxLayout(pTable, BoxLayout.Y_AXIS));
		pTable.setBorder(new EmptyBorder(10, 10, 10, 10)); 

		pInfor.add(lblMa);
		pInfor.add(Box.createVerticalStrut(20));
		pInfor.add(lblNV);
		pInfor.add(Box.createVerticalStrut(20));
		pInfor.add(lblTien);
		pInfor.add(Box.createVerticalStrut(20));
		pInfor.add(lblDT);
		pInfor.add(Box.createVerticalStrut(20));
		pInfor.add(lblATM);
		pInfor.add(Box.createVerticalStrut(20));
		pInfor.add(lblTienLay);
		pInfor.add(Box.createVerticalStrut(20));
		pInfor.add(lblCL);
		pAction.add(btnKetCa);
		pTable.add(lblTua);
		pTable.add(Box.createVerticalStrut(20));
		pTable.add(pane);
		pTable.add(lblTong);

		pRight.add(pInfor);
		pRight.add(pAction);
		pLeft.add(pTable);
		pMain.add(pLeft);
		pMain.add(pRight);
		add(pMain);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
