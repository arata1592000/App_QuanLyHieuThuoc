package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.concurrent.Flow;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import utils.ButtonDeleteEditor;
import utils.ButtonDeleteRenderer;
import utils.RadioButtonEditor;
import utils.RadioButtonRenderer;

public class Gui_DoiTraThuoc extends JPanel{
	private int widthComp, heightComp;
	private JPanel pLeft;
	private JPanel pSearch;
	private JTextField txtTim;
	private JPanel pInforOrderDetail;
	private JLabel lbl1;
	private JLabel lbl2;
	private JButton btnTim;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lbl5;
	private JLabel lbl6;
	private JPanel pRight;
	private DefaultTableModel dataModel;
	private JTable tableModel;
	private JPanel pMain;
	private JPanel pWest;
	private JLabel lbl7;
	private JRadioButton radTraThuoc;
	private JLabel lbl8;
	private JRadioButton radDoiThuoc;
	private JLabel lbl9;
	private JLabel lbl10;
	private JTextField txtLyDo;
	private JLabel lbl11;
	private JButton btnTaoLaiHoaDon;
	private JPanel pAction;
	private JPanel pTable;
	private ButtonGroup radGroup;

	public Gui_DoiTraThuoc(int widthComp, int heightComp) {
		this.widthComp = widthComp;
		this.heightComp = heightComp;
		this.setLayout(new BorderLayout());
		initCompoent();
	}
	
	public void initCompoent() {
		pSearch = new JPanel();
		pMain = new JPanel();
		pLeft = new JPanel();
		txtTim = new JTextField(15);
		btnTim = new JButton();
		pInforOrderDetail = new JPanel();
		lbl1 = new JLabel();
		lbl2 = new JLabel();
		lbl3 = new JLabel();
		lbl4 = new JLabel();
		lbl5 = new JLabel();
		lbl6 = new JLabel();
		pRight = new JPanel();
		pTable = new JPanel();
		lbl7 = new JLabel();
		String headers[] = {"Chọn", "Tên thuốc", "Số lượng", "Giá", "Khuyến mãi", "Tổng tiền"};
		dataModel = new DefaultTableModel(headers, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Đặt tất cả các ô không thể chỉnh sửa
                return column==0;
            }
        };
		Object[] newRow = {"", ""};
		dataModel.addRow(newRow);
		tableModel = new JTable(dataModel);
		tableModel.getColumn("Chọn").setPreferredWidth(10);
		tableModel.getColumn("Chọn").setCellRenderer(new RadioButtonRenderer());
		tableModel.getColumn("Chọn").setCellEditor(new RadioButtonEditor(new JCheckBox()));
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 13));
		tableModel.setRowHeight(30);
		JScrollPane pane = new JScrollPane(tableModel);
		pAction = new JPanel();
		radTraThuoc = new JRadioButton();
		lbl8 = new JLabel();
		radDoiThuoc = new JRadioButton();
		lbl9 = new JLabel();
		radGroup = new ButtonGroup();
		lbl10 = new JLabel();
		txtLyDo = new JTextField(15);
		lbl11 = new JLabel();
		btnTaoLaiHoaDon = new JButton();
		
		this.setBackground(Color.WHITE);
		pSearch.setBackground(Color.WHITE);
		pSearch.setPreferredSize(new Dimension((int)(widthComp*0.98), (int)(heightComp*0.1)));
		pSearch.setLayout(new FlowLayout(FlowLayout.LEFT));
		pMain.setBackground(Color.WHITE);
		pMain.setPreferredSize(new Dimension((int)(widthComp*0.98), (int)(heightComp*0.85)));
		pLeft.setBackground(Color.WHITE);
		pLeft.setPreferredSize(new Dimension((int)(widthComp*0.3), (int)(heightComp*0.8)));

		txtTim.setFont(new Font ("Arial", Font.PLAIN, 20));
		btnTim.setText("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Arial", Font.BOLD, 16));
		btnTim.setBackground(new Color(40,156,164));
		btnTim.setOpaque(true);
		btnTim.setContentAreaFilled(true);
        btnTim.setBorderPainted(false);
        btnTim.setFocusPainted(false);
		Border titledBorder = BorderFactory.createTitledBorder("Thông tin hóa đơn");
		if (titledBorder instanceof TitledBorder) {
		    // Lấy phông chữ của tiêu đề hiện tại
		    Font titleFont = ((TitledBorder) titledBorder).getTitleFont();
		    // Tạo một phông chữ mới với kích thước mới (ở đây tăng 4 điểm)
		    Font newTitleFont = titleFont.deriveFont(titleFont.getSize() + 15f);
		    // Đặt phông chữ mới cho tiêu đề
		    ((TitledBorder) titledBorder).setTitleFont(newTitleFont);
		}
		
		pRight.setPreferredSize(new Dimension((int)(widthComp*0.62), (int)(heightComp*0.8)));
        pInforOrderDetail.setBorder(titledBorder);
        pInforOrderDetail.setPreferredSize(new Dimension((int)(widthComp*0.3), (int)(heightComp*0.5)));
		pInforOrderDetail.setLayout(new BoxLayout(pInforOrderDetail, BoxLayout.Y_AXIS));
		pTable.setPreferredSize(new Dimension((int)(widthComp*0.6), (int)(heightComp*0.6)));
		pTable.setLayout(new BoxLayout(pTable, BoxLayout.Y_AXIS));
		pTable.setBorder(new EmptyBorder(10, 10, 10, 10)); // Đặt độ lề cho panel
		lbl2.setText("    Mã khách hàng");
		lbl2.setFont(new Font("Arial", Font.PLAIN, 23));
		lbl3.setText("    Tên khách hàng");
		lbl3.setFont(new Font("Arial", Font.PLAIN, 23));
		lbl4.setText("    Tên nhân viên");
		lbl4.setFont(new Font("Arial", Font.PLAIN, 23));
		lbl5.setText("    Ngày");
		lbl5.setFont(new Font("Arial", Font.PLAIN, 23));
		lbl6.setText("    Giờ");		
		lbl6.setFont(new Font("Arial", Font.PLAIN, 23));
		lbl7.setText("Danh sách thuốc mà khách hàng đã mua");
		lbl7.setFont(new Font("Arial", Font.BOLD, 25));
		pAction.setLayout(new FlowLayout(FlowLayout.LEFT));
		pAction.setPreferredSize(new Dimension(new Dimension((int)(widthComp*0.58), (int)(heightComp*0.2))));
		radTraThuoc.setSelected(true);
		radTraThuoc.setText("Trả thuốc");
		radTraThuoc.setFont(new Font ("Arial", Font.PLAIN, 20));
		radDoiThuoc.setText("Đổi thuốc");
		radDoiThuoc.setFont(new Font ("Arial", Font.PLAIN, 20));
		lbl10.setText("Lý do:");
		lbl10.setFont(new Font ("Arial", Font.PLAIN, 20));
		txtLyDo.setFont(new Font ("Arial", Font.PLAIN, 20));
		lbl11.setText("Số tiền được hoàn trả:" + "1.500.000đ");
		lbl11.setFont(new Font ("Arial", Font.PLAIN, 20));
		btnTaoLaiHoaDon.setText("Tạo lại hóa đơn");
		btnTaoLaiHoaDon.setForeground(Color.WHITE);
		btnTaoLaiHoaDon.setFont(new Font("Arial", Font.BOLD, 20));
		btnTaoLaiHoaDon.setBackground(new Color(40,156,164));
		btnTaoLaiHoaDon.setOpaque(true);
		btnTaoLaiHoaDon.setContentAreaFilled(true);
        btnTaoLaiHoaDon.setBorderPainted(false);
        btnTaoLaiHoaDon.setFocusPainted(false);
		
		pSearch.add(Box.createHorizontalStrut(30));
		pSearch.add(txtTim);
		pSearch.add(Box.createHorizontalStrut(10));
		pSearch.add(btnTim);
		
		pInforOrderDetail.add(lbl1);
		pInforOrderDetail.add(Box.createVerticalStrut(25));
		pInforOrderDetail.add(lbl2);
		pInforOrderDetail.add(Box.createVerticalStrut(15));
		pInforOrderDetail.add(lbl3);
		pInforOrderDetail.add(Box.createVerticalStrut(15));
		pInforOrderDetail.add(lbl4);
		pInforOrderDetail.add(Box.createVerticalStrut(15));
		pInforOrderDetail.add(lbl5);
		pInforOrderDetail.add(Box.createVerticalStrut(15));
		pInforOrderDetail.add(lbl6);
		
		pTable.add(lbl7);
		pTable.add(Box.createVerticalStrut(20));
		pTable.add(pane);
		radGroup.add(radTraThuoc);
		radGroup.add(radDoiThuoc);
		pAction.add(radTraThuoc);
		pAction.add(lbl8);
		pAction.add(radDoiThuoc);
		pAction.add(lbl9);
		pAction.add(Box.createHorizontalStrut(100));
		pAction.add(lbl10);
		pAction.add(txtLyDo);
		pAction.add(lbl11);
		pAction.add(Box.createHorizontalStrut(1000));
		pAction.add(btnTaoLaiHoaDon);
		
		
		pLeft.add(pInforOrderDetail);
		pRight.add(pTable);
		pRight.add(pAction);
		pMain.add(pLeft);
		pMain.add(pRight);
		this.add(pSearch, BorderLayout.NORTH);
		this.add(pMain, BorderLayout.CENTER);
		
		radTraThuoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(lbl11.getText().equals("")) {
					lbl11.setText("Số tiền được hoàn trả: 1.500.000đ");
				}
			}
		});
		
		radDoiThuoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!lbl11.getText().equals("")) {
					lbl11.setText("");
				}
			}
		});
	}
}
