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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.AbstractCellEditor;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SingleSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.Dao_HoaDon;
import dao.Dao_KhachHang;
import dao.Dao_NhanVien;
import dao.Dao_Thuoc;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.Thuoc;
import utils.ButtonDeleteEditor;
import utils.ButtonDeleteRenderer;
import utils.CustomMenuItemThuoc;

public class Gui_BanThuoc extends JPanel{
	private int widthComp, heightComp;
	private JPanel pTable;
	private JTextField txtThem;
	private DefaultTableModel dataModel;
	private JTable tableModel;
	private JPanel pInfor;
	private JPanel pInforCustomer;
	private JLabel lbl1;
	private JLabel lbl2;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JPanel pInforOrder;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lbl5;
	private JTextField txtMaHD;
	private JLabel lbl6;
	private JTextField txtNgayLap;
	private JLabel lbl7;
	private JTextField txtTongTien;
	private JLabel lbl8;
	private JTextField txtVAT;
	private JLabel lbl9;
	private JTextField txtMaKM;
	private JLabel lbl10;
	private JTextField txtThanhTien;
	private JLabel lbl11;
	private JLabel lbl12;
	private JTextField txtPhuongThucThanhToan;
	private JTextField txtTienKhachDua;
	private JLabel lbl13;
	private JTextField txtTienThua;
	private JPanel pButtonOrder;
	private JButton btnThanhToan;
	private JButton btnLamMoi;
	private JPopupMenu suggestionMenu;
	private int indexPopupMenu;
	private NhanVien nv;
	
	public Gui_BanThuoc(NhanVien nv, int widthComp, int heightComp) {
		this.widthComp = widthComp;
		this.heightComp = heightComp;
		this.nv = nv;
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		initCompoent();
	}
	
	private void initCompoent() {
		pTable = new JPanel();
		txtThem = new JTextField();
        suggestionMenu = new JPopupMenu();
		String headers[] = {"Mã thuốc", "Tên thuốc", "Số lượng", "Đơn vị tính", "Giá bán", "KM", "Tổng tiền", "Xóa"};
		dataModel = new DefaultTableModel(headers, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Đặt tất cả các ô không thể chỉnh sửa
                return column==2 || column==7;
            }
        };
		Object[] newRow = {"", ""};
		dataModel.addRow(newRow);
		tableModel = new JTable(dataModel);
		tableModel.getColumn("Xóa").setPreferredWidth(10);
		tableModel.getColumn("Xóa").setCellRenderer(new ButtonDeleteRenderer());
		tableModel.getColumn("Xóa").setCellEditor(new ButtonDeleteEditor(new JCheckBox()));
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 13));
		tableModel.setRowHeight(30);
		tableModel.setRowHeight(tableModel.getRowCount()-1, 1);
		JScrollPane pane = new JScrollPane(tableModel);
		pane.setPreferredSize(new Dimension((int)(widthComp*0.70),(int)(heightComp*0.8)));
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
		
  		pTable.setBackground(Color.WHITE);
		pTable.setPreferredSize(new Dimension((int)(widthComp*0.75),(int) (heightComp*0.9)));
		txtThem.setPreferredSize(new Dimension((int)(widthComp*0.70),(int) (heightComp*0.05)));
  		txtThem.setFont(new Font("Arial", Font.BOLD, 22));
		
		
        pInfor.setPreferredSize(new Dimension((int) (widthComp*0.2), (int)(heightComp*0.85)));
        pInfor.setLayout(new FlowLayout(FlowLayout.CENTER));
        pInfor.setBackground(Color.WHITE);
		//Form thông tin khách hàng
		GridBagConstraints constraintsCustomer = new GridBagConstraints();//khởi tạo constraintsCustomer để thiết lập vị trí cho các trường
        constraintsCustomer.insets = new Insets(5, 1, 5, 1);
		pInforCustomer.setLayout(new GridBagLayout());
		pInforCustomer.setPreferredSize(new Dimension((int) (widthComp*0.18), (int)(heightComp*0.15)));
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
		pInforOrder.setPreferredSize(new Dimension((int) (widthComp*0.18), (int)(heightComp*0.55)));
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
        txtMaHD.setText((new Dao_HoaDon()).autoCreateMaHD());
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
        btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFont(new Font("Arial", Font.BOLD, 16));
		btnThanhToan.setBackground(new Color(40,156,164));
		btnThanhToan.setOpaque(true);
		btnThanhToan.setContentAreaFilled(true);
        btnThanhToan.setBorderPainted(false);
        btnThanhToan.setFocusPainted(false);
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 16));
		btnLamMoi.setBackground(new Color(40,156,164));
		btnLamMoi.setOpaque(true);
		btnLamMoi.setContentAreaFilled(true);
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.setFocusPainted(false);
        pButtonOrder.setBackground(Color.WHITE);
		pButtonOrder.setPreferredSize(new Dimension((int) (widthComp*0.18), (int)(heightComp*0.1)));
        
		pTable.add(txtThem);
		pTable.add(pane);
		pButtonOrder.add(btnThanhToan);
		pButtonOrder.add(btnLamMoi);
		pInfor.add(pInforCustomer);
		pInfor.add(Box.createHorizontalStrut(100));
		pInfor.add(pInforOrder);
		pInfor.add(Box.createHorizontalStrut(100));
		pInfor.add(pButtonOrder);
		
		this.add(pTable, BorderLayout.CENTER);
		this.add(pInfor, BorderLayout.EAST);
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
		  		txtThem.requestFocus();
			}
		});
		

		suggestionMenu.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();
				char keyChar = e.getKeyChar();
				if (Character.isLetter(keyChar) || Character.isDigit(keyChar)) {
					txtThem.requestFocus();
					txtThem.setText(txtThem.getText() + keyChar);
					if (txtThem.getText().length() >=1) {
	                    showSuggestions(txtThem.getText());
					}
				} else if (keyCode == KeyEvent.VK_BACK_SPACE){
					txtThem.requestFocus();
					String newText = txtThem.getText().substring(0, txtThem.getText().length() - 1);
				    txtThem.setText(newText); // Gán chuỗi mới vào txtThem để cập nhật nội dung
				    if (newText.length() >= 1) {
				        showSuggestions(newText);
				    }
				} else {
					if (keyCode == KeyEvent.VK_DOWN) {
						if (indexPopupMenu == suggestionMenu.getComponentCount()-1) {
							suggestionMenu.getSelectionModel().setSelectedIndex(0);
							indexPopupMenu = suggestionMenu.getSelectionModel().getSelectedIndex();
						}else {
							suggestionMenu.getSelectionModel().setSelectedIndex(indexPopupMenu+1);
							indexPopupMenu = suggestionMenu.getSelectionModel().getSelectedIndex();
						}
					}else if (keyCode == KeyEvent.VK_UP) {
						if (indexPopupMenu == 0) {
							suggestionMenu.getSelectionModel().setSelectedIndex(suggestionMenu.getComponentCount()-1);
							indexPopupMenu = suggestionMenu.getSelectionModel().getSelectedIndex();
						}else {
							suggestionMenu.getSelectionModel().setSelectedIndex(indexPopupMenu-1);
							indexPopupMenu = suggestionMenu.getSelectionModel().getSelectedIndex();
						}
					}else if (keyCode == KeyEvent.VK_ENTER) {
						handleSelectedItem(indexPopupMenu);
					}else if(keyCode == KeyEvent.VK_ESCAPE) {
						suggestionMenu.setVisible(false);
					}
				}
			}
		});
		
		txtThem.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_DOWN) {
					suggestionMenu.requestFocusInWindow();
					indexPopupMenu = 0;
				}else if(keyCode == KeyEvent.VK_UP) {
					suggestionMenu.requestFocusInWindow();
					indexPopupMenu = suggestionMenu.getComponentCount()-1;
				}else if(keyCode == KeyEvent.VK_ESCAPE) {
					suggestionMenu.setVisible(false);
				}
			}
			
		    @Override
		    public void keyReleased(KeyEvent e) {
                String text = txtThem.getText();
                if (text.length() == 7) {
                    showSuggestions(text);
                    suggestionMenu.validate();
                    suggestionMenu.repaint();
                } else {
                    suggestionMenu.setVisible(false);
                }
            }
		});
		btnThanhToan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					KhachHang kh = (new Dao_KhachHang()).findKhachHangBySDT(txtSDT.getText());
					if (kh == null) {
						kh = new KhachHang((new Dao_KhachHang()).autoCreateMaKH(),
								txtHoTen.getText(),
								txtSDT.getText());
						(new Dao_KhachHang()).addKhachHang(kh);
					}
					List<ChiTietHoaDon> listCTHD= new ArrayList();
					List<String> listMaThuoc = new ArrayList();
					List<Integer> listSoLuong = new ArrayList<Integer>();
					for (int row = 0 ; row < tableModel.getRowCount()-1 ; row++) {
						ChiTietHoaDon cthd = new ChiTietHoaDon(dataModel.getValueAt(row, 0).toString(),
								(String)dataModel.getValueAt(row, 1),
								Integer.parseInt(dataModel.getValueAt(row, 2).toString()),
								(String)dataModel.getValueAt(row, 3),
								Float.valueOf(dataModel.getValueAt(row, 4).toString()),
								Float.valueOf(dataModel.getValueAt(row, 5).toString()),
								Float.valueOf(dataModel.getValueAt(row, 6).toString())
								);						
						listCTHD.add(cthd);
						listMaThuoc.add(dataModel.getValueAt(row, 0).toString());
						listSoLuong.add(Integer.parseInt(dataModel.getValueAt(row, 2).toString()));
					}
					HoaDon hd = new HoaDon(txtMaHD.getText(),
							LocalDate.parse(txtNgayLap.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
							Float.parseFloat(txtTongTien.getText()),
							"Bán hàng",
							(float)0.3,
							""
							);
					hd.setNhanVien(nv);
					hd.setKhachHang(kh);
					hd.setChiTietHoaDon(listCTHD);
					hd.setKhuyenMai(new KhuyenMai());
					if ((new Dao_HoaDon().addHoaDon(hd))) {
						(new Dao_Thuoc()).setCountByMaThuoc(listMaThuoc, listSoLuong);
			            JOptionPane.showMessageDialog(null, "Thanh toán thành công");
			            

					}else {
			            JOptionPane.showMessageDialog(null, "Không thanh toán thành công.");
					}
					resetForm();
					validate();
					repaint();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
		
		dataModel.addTableModelListener(new TableModelListener() {
		    @Override
		    public void tableChanged(TableModelEvent e) {
		        if (e.getType() == TableModelEvent.UPDATE) {
		            int row = e.getFirstRow();
		            int column = e.getColumn();		            
		            if (column == 2 && !dataModel.getValueAt(row, 2).equals("")) { // Kiểm tra xem cột thay đổi có phải là cột số lượng không
		            	if (!(new Dao_Thuoc()).checkCountByMaThuoc(dataModel.getValueAt(row, 0).toString(), Integer.parseInt((String) dataModel.getValueAt(row, 2)))) {
		            		JOptionPane.showMessageDialog(null, "Số lượng bạn nhập đã vượt qua số lượng tồn kho của loại thuốc này!");
		            		dataModel.setValueAt("", row, 2);
		            	}else {
		            		Thuoc thuoc = (new Dao_Thuoc()).findThuocByMaThuoc(dataModel.getValueAt(row, 0).toString());
		            		int quantity = Integer.parseInt((String) dataModel.getValueAt(row, 2));
			                float price = Float.valueOf(dataModel.getValueAt(row, 4).toString());
			                float discountMoney = quantity * price * thuoc.getKhuyenMai().getTyLeKM()/100;
			                float totalPrice = quantity * price - discountMoney;
			                tableModel.setValueAt(discountMoney, row, 5);
			                tableModel.setValueAt(totalPrice, row, 6);
			                updateInforOrder();
			                if (row == tableModel.getRowCount()-2) {
			                	SwingUtilities.invokeLater(new Runnable() {
				                    @Override
				                    public void run() {
				                        txtThem.requestFocus();
				                    }
				                }); 
			                }
		            	}   
		            }
		        }
		    }
		});
	}
	
	public void resetForm() {
		while(tableModel.getRowCount()>1) {
			dataModel.removeRow(0);
		}
		txtThem.setText("");
		txtHoTen.setText("");
		txtSDT.setText("");
        txtMaHD.setText((new Dao_HoaDon()).autoCreateMaHD());
	}
	
	public void updateInforOrder() {
		txtTongTien.setText(getTongTienHD()+"");
	}
	
	public float getTongTienHD() {
		float tongTienHD = 0;
		for (int i = 0 ; i < tableModel.getRowCount()-1 ; i++) {
			tongTienHD+=Float.valueOf(dataModel.getValueAt(i, 6).toString());
		}
		return tongTienHD;
	}
	
	public void addRowTable() {
		Thuoc thuoc = (new Dao_Thuoc()).findThuocByMaThuoc(txtThem.getText());
		if (thuoc == null){
            JOptionPane.showMessageDialog(null, "Không có loại thuốc này.");
            return;
		}
		tableModel.setRowHeight(tableModel.getRowCount()-1, 30);
		
		dataModel.setValueAt(thuoc.getMaThuoc(), tableModel.getRowCount()-1, 0);
		dataModel.setValueAt(thuoc.getTenThuoc(), tableModel.getRowCount()-1, 1);
		dataModel.setValueAt("", tableModel.getRowCount()-1, 2);
		dataModel.setValueAt(thuoc.getDonViTinh(), tableModel.getRowCount()-1, 3);
		dataModel.setValueAt(thuoc.getGia(), tableModel.getRowCount()-1, 4);
		dataModel.setValueAt("0", tableModel.getRowCount()-1, 5);
		dataModel.setValueAt("0", tableModel.getRowCount()-1, 6);
		
		tableModel.editCellAt(tableModel.getRowCount()-1, 2);
		Component editor = tableModel.getEditorComponent();
		if (editor != null) {
		    editor.requestFocusInWindow();
		}
		dataModel.addRow(new Object[] {"", "", "", "", 0, "", ""});
		tableModel.setRowHeight(tableModel.getRowCount()-1, 1);
		txtThem.setText("");
		updateInforOrder();
		revalidate();
	}
	
	
	private void showSuggestions(String text) {

	    suggestionMenu.removeAll();
	    List<Thuoc> listThuoc = (new Dao_Thuoc()).listThuocContainTenThuocOrMaThuoc(text);
	    	for (int i = 0; i < listThuoc.size(); i++) {
	    	final int index = i;
	        Thuoc thuoc = listThuoc.get(i);
	        CustomMenuItemThuoc item = new CustomMenuItemThuoc(txtThem.getPreferredSize().width, thuoc);
	        item.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					suggestionMenu.setVisible(false);
					txtThem.requestFocusInWindow();
                    handleSelectedItem(index);
				}
			});
	        item.add(item.getCustomPanel());
	        item.setOpaque(true);
	        item.setEnabled(true);
	        
	        suggestionMenu.add(item);
	    }

	    suggestionMenu.pack();

	    if (!listThuoc.isEmpty()) {
	        suggestionMenu.setFocusable(true);
	        suggestionMenu.show(txtThem, 0, txtThem.getHeight());
	        txtThem.requestFocusInWindow();
	    } else {
	        suggestionMenu.setVisible(false);
	    }
	}

	// Phương thức để xử lý mục đã chọn
	private void handleSelectedItem(int selectedIndex) {
	    if (selectedIndex >= 0 && selectedIndex < suggestionMenu.getComponentCount()) {
	        JMenuItem selectedItem = (JMenuItem) suggestionMenu.getComponent(selectedIndex);
	        txtThem.setText(selectedItem.getText().split(" - ")[0]);
	        selectedIndex = -1; // Reset selectedIndex sau khi đã xử lý mục đã chọn(áp dụng cho click chọn thẳng vào item)
	        suggestionMenu.setVisible(false);
	        addRowTable();
	    }
	}


}
