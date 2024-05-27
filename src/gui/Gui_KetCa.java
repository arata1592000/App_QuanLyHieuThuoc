package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.util.PaneInformation;

import dao.Dao_BangKetCa;
import dao.Dao_HoaDon;
import dao.Dao_NhanVien;
import entity.BangKetCa;
import entity.ChiTietBangKetCa;
import entity.NhanVien;
import utils.CurrencyFormatter;

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
	private NhanVien nv;
	private JTextField txtMa;
	private JTextField txtNV;
	private JTextField txtTien;
	private JTextField txtDT;
	private JTextField txtTienLay;
	private JTextField txtATM;
	private JLabel lblTien2;
	private JButton btnDangXuat;
	public Gui_KetCa(JButton btnDangXuat, NhanVien nv, int width, int height) {
		widthComp = width;
		heightComp = height;
		this.btnDangXuat = btnDangXuat;
		this.nv = nv;
		initCompoent();
		loadDataTable();
	}
	public void initCompoent() {
		pMain = new JPanel();
		pLeft = new JPanel();
		pRight = new JPanel();
		pTable = new JPanel();
		pAction = new JPanel();
		pInfor = new JPanel();
		lblMa = new JLabel("Mã kết ca : ");
		lblMa.setFont(new Font("Arial", Font.PLAIN, 18));
        txtMa = new JTextField();
        txtMa.setText((new Dao_BangKetCa()).autoCreateMaBangKetCa());
		txtMa.setFont(new Font("Arial", Font.PLAIN, 18));
		txtMa.setEditable(false);
		txtMa.setBorder(null);
		lblNV = new JLabel("Nhân viên : ");
		lblNV.setFont(new Font("Arial", Font.PLAIN, 18));
        txtNV = new JTextField();
        txtNV.setText(nv.getMaNV());
		txtNV.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNV.setEditable(false);
		txtNV.setBorder(null);
		lblTien = new JLabel("Tiền mặt hiện có: ");
		lblTien.setFont(new Font("Arial:", Font.PLAIN, 18));
        lblTien2 = new JLabel("0.00 VNĐ");
		lblTien2.setFont(new Font("Arial:", Font.PLAIN, 18));
//		lblTien.setEditable(false);
//		lblTien.setBorder(null);
		float totalCashRevenue = (new Dao_HoaDon()).calTotalCashRevenue(nv.getMaNV());
		lblDT = new JLabel("Số tiền mặt thu được trong ca: ");
		lblDT.setFont(new Font("Arial", Font.PLAIN, 18));
        txtDT = new JTextField();
        txtDT.setText((new CurrencyFormatter()).formatVNDWithDecimals(totalCashRevenue));
		txtDT.setFont(new Font("Arial:", Font.PLAIN, 18));
		txtDT.setEditable(false);
		txtDT.setBorder(null);
		float totalCashATM = (new Dao_HoaDon()).calTotalCashATM(nv.getMaNV());
		lblATM = new JLabel("ATM : ");
		lblATM.setFont(new Font("Arial", Font.PLAIN, 18));
        txtATM = new JTextField();
        txtATM.setText((new CurrencyFormatter()).formatVNDWithDecimals(totalCashATM));
		txtATM.setFont(new Font("Arial:", Font.PLAIN, 18));
		txtATM.setEditable(false);
		txtATM.setBorder(null);
		float totalMoneySpent = (new Dao_HoaDon()).calTotalMoneySpent(nv.getMaNV());
		lblTienLay = new JLabel("Tiền lấy ra : ");
		lblTienLay.setFont(new Font("Arial:", Font.PLAIN, 18));
        txtTienLay = new JTextField();
        txtTienLay.setText((new CurrencyFormatter()).formatVNDWithDecimals(totalMoneySpent));
		txtTienLay.setFont(new Font("Arial:", Font.PLAIN, 18));
		txtTienLay.setEditable(false);
		txtTienLay.setBorder(null);


		lblTua = new JLabel("Chi Tiết Kiểm Ca");
		lblTua.setFont(new Font("Arial:", Font.BOLD, 25));
		lblTong = new JLabel("Tổng: ");
		lblTong.setFont(new Font("Arial:", Font.PLAIN, 20));
		String headers[]= {"STT","Mệnh giá","Số lượng","Tổng"};
		dataModel = new DefaultTableModel(headers,0) {
			 @Override
	            public boolean isCellEditable(int row, int column) {
	                // Đặt tất cả các ô không thể chỉnh sửa
	                return column==2;
	            }
		};
		tableModel = new JTable(dataModel);
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
		tableModel.setRowHeight(30);
		pane = new JScrollPane(tableModel);
		btnKetCa = new JButton("Kết ca");
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

		pInfor.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 0, 20, 0);
        pInfor.add(lblMa, gbc);
        gbc.gridx = 1;
        pInfor.add(txtMa, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        pInfor.add(lblNV, gbc);
        gbc.gridx = 1;
        pInfor.add(txtNV, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        pInfor.add(lblTien, gbc);
        gbc.gridx = 1;
        pInfor.add(lblTien2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        pInfor.add(lblDT, gbc);
        gbc.gridx = 1;
        pInfor.add(txtDT, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        pInfor.add(lblATM, gbc);
        gbc.gridx = 1;
        pInfor.add(txtATM, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        pInfor.add(lblTienLay, gbc);
        gbc.gridx = 1;
        pInfor.add(txtTienLay, gbc);
		pAction.add(btnKetCa);
		pTable.add(lblTua);
		pTable.add(Box.createVerticalStrut(20));
		pTable.add(pane);
		pRight.add(pInfor);
		pRight.add(pAction);
		pLeft.add(pTable);
		pMain.add(pLeft);
		pMain.add(pRight);
		add(pMain);
		
		dataModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int column = e.getColumn();
				int row = e.getFirstRow();
				if (column == 2) {
					if (dataModel.getValueAt(row, 2).toString().equals("")) {
						dataModel.setValueAt("0",row, 3);
					}else {
						int soLuong = Integer.parseInt(dataModel.getValueAt(row, 2).toString());
						int menhGia = Integer.parseInt(dataModel.getValueAt(row, 1).toString());
						dataModel.setValueAt(CurrencyFormatter.formatVNDWithDecimals(menhGia * soLuong), row, 3);	
					}
					lblTien2.setText((new CurrencyFormatter()).formatVNDWithDecimals(calTotalMoney()));
				}
				
			}
		});
		
		btnKetCa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int choice = JOptionPane.showConfirmDialog(null, "Bạn xác nhận muốn kết ca?", "Xác nhận", JOptionPane.YES_NO_OPTION);

		        // Kiểm tra kết quả
		        if (choice == JOptionPane.YES_OPTION) {
		        	String maCa = (new Dao_BangKetCa()).autoCreateMaBangKetCa();
					NhanVien nv = (new Dao_NhanVien()).findNhanVienByMaNV(txtNV.getText());
					LocalDate ngayLap = LocalDate.now();
					float tienCoTrongCa = 0;
					float tienMatThuTrongCa = 0;
					float tienATMThuTrongCa = 0;
					float tienLayRa = 0;
					try {
						tienCoTrongCa = (float) (new CurrencyFormatter()).parseVND(lblTien2.getText());
						tienMatThuTrongCa = (float) (new CurrencyFormatter()).parseVND(txtDT.getText());
						tienATMThuTrongCa = (float) (new CurrencyFormatter()).parseVND(txtATM.getText());
						tienLayRa = (float) (new CurrencyFormatter()).parseVND(txtTienLay.getText());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					BangKetCa bkc = new BangKetCa(maCa, nv, ngayLap, tienCoTrongCa, tienMatThuTrongCa, tienATMThuTrongCa, tienLayRa);
					List<ChiTietBangKetCa> listCTBKC = new ArrayList();
					for (int i = 0 ; i < dataModel.getRowCount() ; i++) {
						if (dataModel.getValueAt(i, 2).toString().equals("")) {
							ChiTietBangKetCa ctbkc = new ChiTietBangKetCa(Float.valueOf(dataModel.getValueAt(i, 1).toString()), 0);
							listCTBKC.add(ctbkc);
						}else {
							ChiTietBangKetCa ctbkc = new ChiTietBangKetCa(Float.valueOf(dataModel.getValueAt(i, 1).toString()), 
									Integer.parseInt(dataModel.getValueAt(i, 2).toString()));
							listCTBKC.add(ctbkc);
						}
					}
					bkc.setChiTietBangKetCa(listCTBKC);
					if ((new Dao_BangKetCa()).addBangKetCa(bkc)) {
						JOptionPane.showMessageDialog(null, "Kết ca thành công");
						btnDangXuat.doClick();
					}
		        } else {
		            
		        }
				
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void loadDataTable() {
		tableModel.setRowHeight(50);
		tableModel.setFont(new Font("Arial", Font.PLAIN, 20));
		dataModel.addRow(new String[]{"1", "1000", "", "0"});
		dataModel.addRow(new String[]{"2", "2000", "", "0"});
		dataModel.addRow(new String[]{"3", "5000", "", "0"});
		dataModel.addRow(new String[]{"4", "10000", "", "0"});
		dataModel.addRow(new String[]{"5", "20000", "", "0"});
		dataModel.addRow(new String[]{"6", "50000", "", "0"});
		dataModel.addRow(new String[]{"7", "100000", "", "0"});
		dataModel.addRow(new String[]{"8", "200000", "", "0"});
		dataModel.addRow(new String[]{"9", "500000", "", "0"});
	}
	
	public float calTotalMoney() {
		float totalMoney = 0;
		for (int i = 0; i < dataModel.getRowCount() ; i++) {
			float totalMoneyI = 0;
			try {
				totalMoneyI = (float) (new CurrencyFormatter()).parseVND(dataModel.getValueAt(i, 3).toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			totalMoney += totalMoneyI;
		}
		System.out.println(totalMoney);
		return totalMoney;
	}
}
