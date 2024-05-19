package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import javax.swing.border.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolTipUI;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import dao.Dao_HoaDon;
import dao.Dao_NhanVien;
import dao.Dao_Thuoc;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.Thuoc;
import utils.ButtonDeleteEditor;
import utils.ButtonDeleteRenderer;
import utils.PrintOrder;
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
	private JComboBox<String> cbbLyDo;
	private JLabel lbl11;
	private JButton btnTaoLaiHoaDon;
	private JPanel pAction;
	private JPanel pTable;
	private ButtonGroup radGroup;
	private HoaDon hd;
	private NhanVien nv;
	
	private String[] itemsDoiThuoc = {"Thuốc bị lỗi do nhà sản xuất", 
			"Sai sót trong đơn thuốc", 
			"Thuốc đã quá hạn"};
	private String[] tooltipsDoiThuoc = {"Bao bì bị hỏng hóc, rách nát. \nThuốc bị biến màu, biến chất, có mùi lạ. \nThuốc bị sai sót về nhãn mác hoặc thông tin trên bao bì.",
			"Nhân viên bán hàng cấp sai liều lượng hoặc sai loại thuốc so với đơn thuốc của bác sĩ.",
			"Nếu khách hàng phát hiện thuốc đã quá hạn sử dụng khi mua về, nhà thuốc nên đổi thuốc mới cho khách hàng."};
	
	private String[] itemsTraThuoc = {"Thuốc không cần thiết nữa",
			"Thuốc bị lỗi nghiêm trọng"};
	
	private String[] tooltipsTraThuoc = {"Khách hàng có thể yêu cầu trả lại thuốc nếu họ nhận ra thuốc không phù hợp với nhu cầu hoặc không còn cần thiết nữa. Tuy nhiên, trường hợp này cần tuân theo chính sách của nhà thuốc và thường chỉ áp dụng với thuốc chưa mở bao bì và còn nguyên vẹn.\r\n", 
			"Trong trường hợp lỗi nghiêm trọng (như thuốc bị nhiễm bẩn, có tạp chất nguy hiểm...), khách hàng có quyền trả lại thuốc và yêu cầu hoàn tiền.\r\n"};

	public Gui_DoiTraThuoc(NhanVien nv, int widthComp, int heightComp) {
		this.widthComp = widthComp;
		this.heightComp = heightComp;
		UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Arial", Font.PLAIN, 16));
		this.nv = nv;
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
		String headers[] = {"Chọn", "Mã thuốc", "Tên thuốc", "Số lượng cũ", "Số lượng đổi/trả", "Đơn vị tính"};
		dataModel = new DefaultTableModel(headers, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Đặt tất cả các ô không thể chỉnh sửa
                return column==0 || column==4;
            }
        };
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
        cbbLyDo = new JComboBox<>(itemsDoiThuoc);
        cbbLyDo.setRenderer(new CustomComboBoxRenderer(tooltipsDoiThuoc));
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
		    Font titleFont = ((TitledBorder) titledBorder).getTitleFont();
		    Font newTitleFont = titleFont.deriveFont(titleFont.getSize() + 15f);
		    ((TitledBorder) titledBorder).setTitleFont(newTitleFont);
		}
		Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border compoundBorder = BorderFactory.createCompoundBorder(titledBorder, emptyBorder);
		pRight.setPreferredSize(new Dimension((int)(widthComp*0.62), (int)(heightComp*0.8)));
        pInforOrderDetail.setPreferredSize(new Dimension((int)(widthComp*0.3), (int)(heightComp*0.5)));
		pInforOrderDetail.setLayout(new BoxLayout(pInforOrderDetail, BoxLayout.Y_AXIS));
		pInforOrderDetail.setBorder(compoundBorder);
		pTable.setPreferredSize(new Dimension((int)(widthComp*0.6), (int)(heightComp*0.6)));
		pTable.setLayout(new BoxLayout(pTable, BoxLayout.Y_AXIS));
		pTable.setBorder(new EmptyBorder(10, 10, 10, 10)); // Đặt độ lề cho panel
		lbl1.setText("Mã hóa đơn:");
		lbl1.setFont(new Font("Arial:", Font.PLAIN, 23));
		lbl2.setText("Mã khách hàng:");
		lbl2.setFont(new Font("Arial:", Font.PLAIN, 23));
		lbl3.setText("Tên khách hàng:");
		lbl3.setFont(new Font("Arial", Font.PLAIN, 23));
		lbl4.setText("Tên nhân viên:");
		lbl4.setFont(new Font("Arial", Font.PLAIN, 23));
		lbl5.setText("Ngày:");
		lbl5.setFont(new Font("Arial", Font.PLAIN, 23));
		lbl6.setText("Giờ:");		
		lbl6.setFont(new Font("Arial", Font.PLAIN, 23));
		lbl7.setText("Danh sách thuốc mà khách hàng đã mua");
		lbl7.setFont(new Font("Arial", Font.BOLD, 25));
		pAction.setLayout(new FlowLayout(FlowLayout.LEFT));
		pAction.setPreferredSize(new Dimension(new Dimension((int)(widthComp*0.58), (int)(heightComp*0.2))));
		radDoiThuoc.setSelected(true);
		radTraThuoc.setText("Trả thuốc");
		radTraThuoc.setFont(new Font ("Arial", Font.PLAIN, 20));
		radDoiThuoc.setText("Đổi thuốc");
		radDoiThuoc.setFont(new Font ("Arial", Font.PLAIN, 20));
		lbl10.setText("Lý do:");
		lbl10.setFont(new Font ("Arial", Font.PLAIN, 20));
		cbbLyDo.setFont(new Font ("Arial", Font.PLAIN, 20));
		cbbLyDo.setPreferredSize(new Dimension(300, 35));
//		lbl11.setText("Số tiền được hoàn trả: " + getTotalRefund());
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
		pInforOrderDetail.add(Box.createVerticalStrut(15));
		pInforOrderDetail.add(lbl2);
		pInforOrderDetail.add(Box.createVerticalStrut(15));
		pInforOrderDetail.add(lbl3);
		pInforOrderDetail.add(Box.createVerticalStrut(15));
		pInforOrderDetail.add(lbl4);
		pInforOrderDetail.add(Box.createVerticalStrut(15));
		pInforOrderDetail.add(lbl5);
//		pInforOrderDetail.add(Box.createVerticalStrut(15));
//		pInforOrderDetail.add(lbl6);
		
		pTable.add(lbl7);
		pTable.add(Box.createVerticalStrut(20));
		pTable.add(pane);
		radGroup.add(radTraThuoc);
		radGroup.add(radDoiThuoc);
		pAction.add(radDoiThuoc);
		pAction.add(lbl9);
		pAction.add(radTraThuoc);
		pAction.add(lbl8);
		pAction.add(Box.createHorizontalStrut(100));
		pAction.add(lbl10);
		pAction.add(cbbLyDo);
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
		
		dataModel.addTableModelListener(new TableModelListener() {
			private boolean isShowingDialog = false;

			@Override
			public void tableChanged(TableModelEvent e) {
		        if (e.getType() == TableModelEvent.UPDATE) {
		            int column = e.getColumn();
		            int row = e.getFirstRow();
		            if ((column == 0 || column == 4) && row <= dataModel.getRowCount() - 1 ) {
		            	if (Integer.parseInt(dataModel.getValueAt(row, 3).toString()) < Integer.parseInt(dataModel.getValueAt(row, 4).toString()) && dataModel.getValueAt(row, 4).toString().length() > 0) {
		            		JOptionPane.showMessageDialog(null, "Số lượng đổi/trả không thể lớn hơn số lượng cũ");
		            	}else {
		            		if (getItemSelectedRadGroup().equals("Đổi thuốc")) {
			            		if (!isShowingDialog) { // Kiểm tra xem hộp thoại đã được hiển thị hay chưa
				                    Thuoc thuoc = (new Dao_Thuoc()).getThuocByTenSoLuongDonViTinh(dataModel.getValueAt(row, 2).toString(), 
				                                Integer.parseInt(dataModel.getValueAt(row, 4).toString()), 
				                                dataModel.getValueAt(row, 5).toString());
				                    if (thuoc == null  && getItemSelectedRadGroup().equals("Đổi thuốc")) {
				                        isShowingDialog = true; // Đặt biến thành true để chỉ ra rằng hộp thoại đang được hiển thị
				                        JOptionPane.showMessageDialog(null, "Số lượng của thuốc này không còn đủ để đổi!");
				                        dataModel.setValueAt(0, row, 4);
				                        
				                        isShowingDialog = false; // Đặt lại biến thành false sau khi hộp thoại được đóng
				                    }else {
				                    	String maThuoc = dataModel.getValueAt(row, 1).toString();
				                    	if (maThuoc.length()>8) {
				                    		maThuoc = maThuoc.substring(0, maThuoc.length() - 8);
				                    	}
				                        dataModel.setValueAt(maThuoc + "/" +thuoc.getMaThuoc(), row, 1);
				                    }
			            		}
			            	} else {
			                        lbl11.setText("Số tiền được hoàn trả: " + getTotalRefund());
			                        repaint();
			                }
		            	}
		            }
		        }
			}
		});
		
		radTraThuoc.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(lbl11.getText().isEmpty()) {
		            removeDataTable();
		            loadDataTable();
		            lbl11.setText("Số tiền được hoàn trả: " + getTotalRefund());
		            
		            // Cập nhật model và renderer cho cbbLyDo
		            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(itemsTraThuoc);
		            cbbLyDo.setModel(model);
		            cbbLyDo.setRenderer(new CustomComboBoxRenderer(tooltipsTraThuoc));
		            repaint();
		        }
		    }
		});

		radDoiThuoc.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(!lbl11.getText().isEmpty()) {
		            lbl11.setText("");
		            removeDataTable();
		            loadDataTable();
		            
		            // Cập nhật model và renderer cho cbbLyDo
		            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(itemsDoiThuoc);
		            cbbLyDo.setModel(model);
		            cbbLyDo.setRenderer(new CustomComboBoxRenderer(tooltipsDoiThuoc));
		            repaint();
		        }
		    }
		});
		
		btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String maHD = txtTim.getText();
				HoaDon hd = (new Dao_HoaDon()).findHoaDonByMaHD(maHD); 
				Gui_DoiTraThuoc.this.hd = hd;
				if(hd != null) {
					lbl1.setText("Mã hóa đơn: " + hd.getMaHD());
					lbl2.setText("Mã khách hàng: " + hd.getKhachHang().getMaKH());
					lbl3.setText("Tên khách hàng: " + hd.getKhachHang().getHoTen());
					lbl4.setText("Tên nhân viên: " + hd.getNhanVien().getHoTen());
					lbl5.setText("Ngày: " + hd.getNgayLap());
					removeDataTable();
					loadDataTable();
					
				}else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn này!");
				}
			}
		});
		btnTaoLaiHoaDon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HoaDon hdNew = new HoaDon();
				hdNew.setMaHD((new Dao_HoaDon()).autoCreateMaHD());
				hdNew.setKhachHang(hd.getKhachHang());
				hdNew.setNhanVien(nv);
				hdNew.setKhuyenMai(null);
				if (radDoiThuoc.isSelected()) {
					hdNew.setLoaiHD("Đổi thuốc");
					hdNew.setGhiChu("Đổi từ " + hd.getMaHD() + ", Lý do: " + cbbLyDo.getSelectedItem().toString());
					hdNew.setPhuongThucTT(null);
					hdNew.setTienThua(0);
					hdNew.setThue(0);
					hdNew.setThanhTien(0);
					hdNew.setTienKhachDua(0);
					List<Object> listThuocTrade = getListThuocTrade();
					hdNew.setChiTietHoaDon(getListCTHDDoiThuoc());
					boolean n = (new Dao_Thuoc()).setCountByMaThuoc((List<Thuoc>)listThuocTrade.get(0), (List<Integer>)listThuocTrade.get(1));
					if ( n == false) {
						JOptionPane.showMessageDialog(null, "Có lỗi xảy ra");
					}
				}else if (radTraThuoc.isSelected()) {
					hdNew.setLoaiHD("Trả thuốc");
					hdNew.setGhiChu("Trả từ " + hd.getMaHD() + ", Lý do: " + cbbLyDo.getSelectedItem().toString());
					hdNew.setTongTien(getTotalRefund());
					hdNew.setThue(0.3f);
					hdNew.setThanhTien(hdNew.getTongTien());
					hdNew.setPhuongThucTT("Tiền mặt");
					hdNew.setTienKhachDua(0);
					hdNew.setTienThua(0);
					hdNew.setChiTietHoaDon(getListCTHDTraThuoc());
				}
				hdNew.setNgayLap(LocalDate.now());
				hdNew.setKhuyenMai(hd.getKhuyenMai());
				
				if ((new Dao_HoaDon()).addHoaDon(hdNew)) {
					JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công!");
					(new PrintOrder()).PrintOrder(hdNew);
					cleanForm();
				}else {
					JOptionPane.showMessageDialog(null, "Có lỗi xảy ra!");
				}
			}
		});
	}
	
	public List<ChiTietHoaDon> getListCTHDDoiThuoc(){
		List<Object> listThuocTrade = getListThuocTrade();
		List<Thuoc> listThuocMoi = (List<Thuoc>) listThuocTrade.get(0);
		List<Integer> listSoLuongMoi = (List<Integer>) listThuocTrade.get(1);
		List<ChiTietHoaDon> listCTHDMoi = new ArrayList();
		for (int i = 0 ; i < listThuocMoi.size() ; i ++) {
				ChiTietHoaDon cthd = new ChiTietHoaDon(listThuocMoi.get(i).getMaThuoc(),
						listThuocMoi.get(i).getTenThuoc(),
						listSoLuongMoi.get(i),
						listThuocMoi.get(i).getDonViTinh(),
						listThuocMoi.get(i).getGia(),
						listThuocMoi.get(i).getThanhPhan(),
						listThuocMoi.get(i).getNgayHetHan(),
						0,
						0
						);
				listCTHDMoi.add(cthd);
		}
		return listCTHDMoi;
	}
	
	public List<ChiTietHoaDon> getListCTHDTraThuoc(){
		List<ChiTietHoaDon> listCTHD = new ArrayList();
		for (int i = 0 ; i < tableModel.getRowCount() ; i ++) {
			if (dataModel.getValueAt(i, 0).equals(true)) {
				Thuoc thuoc = (new Dao_Thuoc()).findThuocByMaThuoc(dataModel.getValueAt(i, 1).toString().toString());
				ChiTietHoaDon cthd = new ChiTietHoaDon(thuoc.getMaThuoc(),
						thuoc.getTenThuoc(),
						Integer.parseInt(dataModel.getValueAt(i, 4).toString()),
						thuoc.getDonViTinh(),
						thuoc.getGia(),
						thuoc.getThanhPhan(),
						thuoc.getNgayHetHan(),
						0,
						thuoc.getGia()*Integer.parseInt(dataModel.getValueAt(i, 4).toString())
						);
				listCTHD.add(cthd);
			}
		}
		return listCTHD;
	}
	
	public List<Object> getListThuocTrade(){
		List<Object> list = new ArrayList<Object>();
		List<Thuoc> listThuocMoi = new ArrayList();
		List<Integer> listSoLuong = new ArrayList<Integer>();
		for (int i = 0 ; i < tableModel.getRowCount() ; i ++) {
			if (dataModel.getValueAt(i, 0).equals(true)) {
				Thuoc thuoc = (new Dao_Thuoc()).getThuocByTenSoLuongDonViTinh(dataModel.getValueAt(i, 2).toString(), 
						Integer.parseInt(dataModel.getValueAt(i, 4).toString()), 
						dataModel.getValueAt(i, 5).toString());
				listThuocMoi.add(thuoc);
				listSoLuong.add(Integer.parseInt(dataModel.getValueAt(i, 4).toString()));
			}
		}
		list.add(listThuocMoi);
		list.add(listSoLuong);
		return list;
	}
	
	public float getTotalRefund() {
		float total = 0;
		for (int i = 0 ; i < tableModel.getRowCount() ; i ++) {
			if (dataModel.getValueAt(i, 0).equals(true)) {
				float soLuongTra;

				if (dataModel.getValueAt(i, 4).toString().equals("")) {
					soLuongTra = 0;
				} else {
					soLuongTra = Float.valueOf(dataModel.getValueAt(i, 4).toString());
				}
				
				Thuoc thuoc = (new Dao_Thuoc()).findThuocByMaThuoc(dataModel.getValueAt(i, 1).toString());
				float gia = thuoc.getGia(); 
				total += soLuongTra*gia;
			}
		}
		return total;
	}
	
	public String getItemSelectedRadGroup() {
		if (radDoiThuoc.isSelected()) {
			return "Đổi thuốc";
		}else if (radTraThuoc.isSelected()) {
			return "Trả thuốc";
		}
		return "";
	}
	
	public void removeDataTable() {
		while (dataModel.getRowCount() > 0) {
			dataModel.removeRow(0);
		}
	}
	
	public void cleanForm() {
		txtTim.setText("");
		lbl1.setText("Mã hóa đơn:");
		lbl2.setText("Mã khách hàng:");
		lbl3.setText("Tên khách hàng:");
		lbl4.setText("Tên nhân viên:");
		lbl5.setText("Ngày:");
		removeDataTable();
		radDoiThuoc.setSelected(true);
	}
	
	public void loadDataTable() {
		if (hd != null) {
			for (ChiTietHoaDon cthd : hd.getChiTietHoaDon()) {
				dataModel.addRow(new Object[] {false,
						cthd.getMaThuoc(),
						cthd.getTenThuoc(),
						cthd.getSoLuong()+"",
						0,
						cthd.getDonViTinh()});
			}
		}
	}		

	static class CustomComboBoxRenderer extends DefaultListCellRenderer {
        private final String[] tooltips;

        public CustomComboBoxRenderer(String[] tooltips) {
            this.tooltips = tooltips;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (index > -1 && index < tooltips.length) {
                list.setToolTipText(tooltips[index]);
            } else {
                list.setToolTipText(null);
            }
            
            return component;
        }
    }
}
