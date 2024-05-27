package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.Dao_ChiTietHoaDon;
import dao.Dao_HoaDon;
import test.BarChartExample;

public class Gui_ThongKe extends JPanel{

	private int width, height;
	private JTextField txtMaThuoc;
	private JTextField txtTenThuoc;
	private JTextField txtDonViTinh;
	private JTextField txtGia;
	private JTextField txtSoLuong;
	private JTextField txtDoanhThu;
	private JComboBox<String> cbbTieuChi1;
	private JLabel lblTieuChi2;
	private JComboBox<String> cbbTieuChi2;
	private DefaultTableModel dataModel;
	private JTable table;
	private JScrollPane scroll;
	private JButton btnXuatExcel;
	private JPanel pTable;
	private JButton btnThongKe;
	private List<Object[]> listObj;
	private JPanel pChart;
	private ChartPanel barChar;
	private JComboBox<Integer> cbbNam;
	private JRadioButton radDoanhThu;
	private JRadioButton radSoLuong;
	private String tieuChi4;


	public Gui_ThongKe(int width, int height) {
		// TODO Auto-generated constructor stub
		this.width = width;
		this.height = height;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension((int) (width), (int) (height)));
		this.setBackground(Color.WHITE);
		initComponent();
	}

	public void initComponent() {
		pTable = getPanelTable();
		pChart = getPanelChart();
		
		
		
		this.add(pTable);
		this.add(pChart);
		
		cbbTieuChi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbTieuChi1.getSelectedItem().toString().equals("Quý")) {
                    lblTieuChi2.setText("Quý thứ:");
                    cbbTieuChi2.setModel(new DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4"}));
                }
                if (cbbTieuChi1.getSelectedItem().toString().equals("Tháng")) {
                    lblTieuChi2.setText("Tháng thứ:");
                    cbbTieuChi2.setModel(new DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
                }
            }
        });
		
		btnThongKe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int nam = Integer.parseInt(cbbNam.getSelectedItem().toString());
				if (cbbTieuChi1.getSelectedItem().toString().equals("Tháng")) {
					Dao_ChiTietHoaDon daoCTHD = new Dao_ChiTietHoaDon();
					int thang = Integer.parseInt(cbbTieuChi2.getSelectedItem().toString());
					listObj = daoCTHD.statisticalThuocByThang(thang, nam);
					loadDataTable();
					pChart.removeAll();
					pChart.add(getBarChart());
					pChart.revalidate();
					pChart.repaint();
				} else if (cbbTieuChi1.getSelectedItem().toString().equals("Quý")) {
					Dao_ChiTietHoaDon daoCTHD = new Dao_ChiTietHoaDon();
					int quy = Integer.parseInt(cbbTieuChi2.getSelectedItem().toString());
					listObj = daoCTHD.statisticalThuocByQuy(quy, nam);
					loadDataTable();
					pChart.removeAll();
					pChart.add(getBarChart());
					pChart.revalidate();
					pChart.repaint();
				}
				
			}
		});
		
		btnXuatExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exportToExcel();
			}
		});
	}

	private JPanel getPanelTable() {
		JPanel pMain = new JPanel();
		JPanel pCriteria = new JPanel();
		JLabel lblTieuChi1 = new JLabel("Theo:");
		cbbTieuChi1 = new JComboBox<>(new String[]{"Tháng", "Quý"});
		lblTieuChi2 = new JLabel("Tháng thứ:");
		cbbTieuChi2 = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
		JLabel lblTieuChi3 = new JLabel("Năm:");
		List<Integer> listNam = (new Dao_HoaDon()).getListYearOfOrder();
		cbbNam = new JComboBox<>(listNam.toArray(new Integer[0]));
		List<JRadioButton> groupCKBTieuChi4 = new ArrayList<>();
		radDoanhThu = new JRadioButton();
		JLabel lblDoanhThu = new JLabel("Doanh thu");
		radSoLuong = new JRadioButton();
		JLabel lblSoLuong = new JLabel("Số lượng");
		groupCKBTieuChi4.add(radDoanhThu);
		groupCKBTieuChi4.add(radSoLuong);
		JPanel pTable = new JPanel();
		String header[] = {"Mã thuốc", "Tên thuốc", "Đơn vị tính", "Giá", "Số lượng", "Doanhh thu"}; 
		dataModel = new DefaultTableModel(header, 0);
		table = new JTable(dataModel);
		scroll = new JScrollPane(table);
		JPanel pInfor = getPanelInfor();
		JPanel pTotal = new JPanel();
		btnThongKe = new JButton ("Thống Kê");
		btnXuatExcel = new JButton("Xuất Excel");
		
		pMain.setLayout(new BorderLayout());
		pMain.setPreferredSize(new Dimension((int) (width*0.95), (int) (height*0.40)));
		pMain.setBackground(Color.WHITE);
		
		pCriteria.setPreferredSize(new Dimension((int) (width*0.60), (int) (height*0.05)));
		pCriteria.setBackground(Color.WHITE);
		pCriteria.setLayout(new FlowLayout(FlowLayout.LEFT));
		cbbTieuChi1.setPreferredSize(new Dimension(100,30));
		cbbTieuChi2.setPreferredSize(new Dimension(100,30));
		cbbNam.setPreferredSize(new Dimension(100,30));
		radDoanhThu.setBackground(Color.WHITE);
		radSoLuong.setBackground(Color.WHITE);

		pTable.setPreferredSize(new Dimension((int) (width*0.70), (int) (height*0.30)));
		pTable.setBackground(Color.WHITE);
		scroll.setPreferredSize(new Dimension((int) (width*0.70), (int) (height*0.25)));
		pTotal.setPreferredSize(new Dimension((int) (width*0.70), (int) (height*0.05)));
		pTotal.setBackground(Color.WHITE);
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setFont(new Font("Arial", Font.BOLD, 16));
		btnThongKe.setBackground(new Color (40,156,164));
		btnThongKe.setOpaque(true);
		btnThongKe.setContentAreaFilled(true);
        btnThongKe.setBorderPainted(false);
        btnThongKe.setFocusPainted(false);
        btnXuatExcel.setForeground(Color.WHITE);
		btnXuatExcel.setFont(new Font("Arial", Font.BOLD, 16));
		btnXuatExcel.setBackground(new Color (40,156,164));
		btnXuatExcel.setOpaque(true);
		btnXuatExcel.setContentAreaFilled(true);
        btnXuatExcel.setBorderPainted(false);
        btnXuatExcel.setFocusPainted(false);

		pCriteria.add(Box.createHorizontalStrut(50));
		pCriteria.add(lblTieuChi1);
		pCriteria.add(cbbTieuChi1);
		pCriteria.add(Box.createHorizontalStrut(50));
		pCriteria.add(lblTieuChi2);
		pCriteria.add(cbbTieuChi2);
		pCriteria.add(Box.createHorizontalStrut(50));
		pCriteria.add(lblTieuChi3);
		pCriteria.add(cbbNam);
		pCriteria.add(Box.createHorizontalStrut(50));
		pCriteria.add(radDoanhThu);
		pCriteria.add(lblDoanhThu);
		pCriteria.add(radSoLuong);
		pCriteria.add(lblSoLuong);
		pTable.add(scroll);
		pTotal.add(btnThongKe);
		pTotal.add(Box.createHorizontalStrut(50));
		pTotal.add(btnXuatExcel);
		
		pMain.add(pCriteria, BorderLayout.NORTH);
		pMain.add(pTable, BorderLayout.CENTER);
//		pMain.add(pInfor, BorderLayout.EAST);
		pMain.add(pTotal, BorderLayout.SOUTH);
		
		ActionListener listenerGroupCheckBox = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JRadioButton selectedCheckbox = (JRadioButton) e.getSource();
            	
                if (selectedCheckbox.isSelected()) {
                    // Unselect other checkboxes
                	tieuChi4 = selectedCheckbox.toString();
                    for (JRadioButton radioBtn : groupCKBTieuChi4) {
                        if (radioBtn != selectedCheckbox) {
                            radioBtn.setSelected(false);
                        }
                    }
                }
            }
        };

        // Add listener to each checkbox
        for (JRadioButton radioBtn : groupCKBTieuChi4) {
            radioBtn.addActionListener(listenerGroupCheckBox);
        }
        
		radDoanhThu.setSelected(true);

		
		return pMain;
	}
	
	private JPanel getPanelInfor() {
		JPanel pMain = new JPanel();
		JLabel lblMaThuoc = new JLabel("Mã thuốc");
		txtMaThuoc = new JTextField(15);
		JLabel lblTenThuoc = new JLabel("Tên thuốc");
		txtTenThuoc = new JTextField(15);
		JLabel lblDonViTinh = new JLabel("Đơn vị tính");
		txtDonViTinh = new JTextField(15);
		JLabel lblGia = new JLabel("Giá");
		txtGia = new JTextField(15);
		JLabel lblSoLuong = new JLabel("Số lượng");
		txtSoLuong = new JTextField(15);
		JLabel lblDoanhThu = new JLabel("Doanh thu");
		txtDoanhThu = new JTextField(15);
		
		pMain.setPreferredSize(new Dimension((int)(width*0.3), (int)(height*0.4)));
		pMain.setLayout(new GridBagLayout());
		Border titledBorder = BorderFactory.createTitledBorder("Thông tin sản phẩm");
		if (titledBorder instanceof TitledBorder) {
		    Font titleFont = ((TitledBorder) titledBorder).getTitleFont();
		    Font newTitleFont = titleFont.deriveFont(titleFont.getSize() + 10f);
		    ((TitledBorder) titledBorder).setTitleFont(newTitleFont);
		}
		Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border compoundBorder = BorderFactory.createCompoundBorder(titledBorder, emptyBorder);
		pMain.setBorder(compoundBorder);
		GridBagConstraints cons = new GridBagConstraints();
		cons.anchor = GridBagConstraints.WEST;		
		cons.insets = new Insets(1, 5, 1, 5);

		cons.gridx = 0;
		cons.gridy = 0;
		lblMaThuoc.setFont(new Font("Arial", Font.PLAIN, 17));
		pMain.add(lblMaThuoc, cons);
		
		cons.gridx = 1;
		cons.gridy = 0;
		txtMaThuoc.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaThuoc.setEnabled(false);
		pMain.add(txtMaThuoc, cons);
		
		cons.gridx = 0;
		cons.gridy = 1;
		lblTenThuoc.setFont(new Font("Arial", Font.PLAIN, 17));
		pMain.add(lblTenThuoc, cons);
		
		cons.gridx = 1;
		cons.gridy = 1;
		txtTenThuoc.setEnabled(false);
		txtTenThuoc.setFont(new Font("Arial", Font.PLAIN, 15));
		pMain.add(txtTenThuoc, cons);
		
		cons.gridx = 0;
		cons.gridy = 2;
		lblDonViTinh.setFont(new Font("Arial", Font.PLAIN, 17));
		pMain.add(lblDonViTinh, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		txtDonViTinh.setEnabled(false);
		txtDonViTinh.setFont(new Font("Arial", Font.PLAIN, 15));
		pMain.add(txtDonViTinh, cons);
		
		cons.gridx = 0;
		cons.gridy = 3;
		lblGia.setFont(new Font("Arial", Font.PLAIN, 17));
		pMain.add(lblGia, cons);
		
		cons.gridx = 1;
		cons.gridy = 3;
		txtGia.setEnabled(false);
		txtGia.setFont(new Font("Arial", Font.PLAIN, 15));
		pMain.add(txtGia, cons);
		
		cons.gridx = 0;
		cons.gridy = 4;
		lblSoLuong.setFont(new Font("Arial", Font.PLAIN, 17));
		pMain.add(lblSoLuong, cons);
		
		cons.gridx = 1;
		cons.gridy = 4;
		txtSoLuong.setEnabled(false);
		txtSoLuong.setFont(new Font("Arial", Font.PLAIN, 15));
		pMain.add(txtSoLuong, cons);
		
		cons.gridx = 0;
		cons.gridy = 5;
		lblDoanhThu.setFont(new Font("Arial", Font.PLAIN, 17));
		pMain.add(lblDoanhThu, cons);
		
		cons.gridx = 1;
		cons.gridy = 5;
		txtDoanhThu.setEnabled(false);
		txtDoanhThu.setFont(new Font("Arial", Font.PLAIN, 15));
		pMain.add(txtDoanhThu, cons);
		
		return pMain;
	}
	
	private JPanel getPanelChart() {
		JPanel pMain = new JPanel();
		barChar = getBarChart();
		pMain.setPreferredSize(new Dimension((int)(width*0.95), (int) (height*0.5)));
//		pMain.setLayout(new FlowLayout(FlowLayout.LEFT));
		pMain.setBackground(Color.WHITE);
		pMain.add(barChar, BorderLayout.CENTER);
		return pMain;
	}
	
	
	
	private ChartPanel getBarChart() {
		
		ChartPanel barChart = new ChartPanel(null);
		String labelY = null;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if (radDoanhThu.isSelected()) {
        	labelY = "Doanh thu";
        	for (int i = 0 ; i < dataModel.getRowCount(); i ++) {
            	dataset.addValue(Float.valueOf(dataModel.getValueAt(i, 5).toString()), "Doanh thu", dataModel.getValueAt(i, 0).toString());
            }
        }else if (radSoLuong.isSelected()) {
        	labelY = "Số lượng";
        	for (int i = 0 ; i < dataModel.getRowCount(); i ++) {
            	dataset.addValue(Integer.parseInt(dataModel.getValueAt(i, 4).toString()), "Số lượng", dataModel.getValueAt(i, 0).toString());
            }
        }
        

        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
                getTitleBarChart(),  // Chart title
                "Mã thuốc",           // X-axis label
                labelY,              // Y-axis label
                dataset,              // Dataset
                PlotOrientation.VERTICAL,  // Orientation: VERTICAL
                false,                 // Show legend
                true,                 // Show tooltips
                false                 // Generate URLs
        );
        
        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 20)); // Font size 20

        // Set chart to this ChartPanel
        barChart.setChart(chart);
        double aspectRatio = (double) width / height;
        barChart.setPreferredSize(new Dimension((int)(width*0.7), (int) (height*0.5)));		
        return barChart;
	}

	
	public void loadDataTable() {
		clearTable();
		for (int i = 0 ; i < listObj.size(); i++ ) {
			dataModel.addRow(listObj.get(i));
		}
	}
	
	public void clearTable() {
		while(dataModel.getRowCount()>0) {
			dataModel.removeRow(0);
		}
	}
	
	private String getTitleBarChart() {
		String strTieuChi1 = cbbTieuChi1.getSelectedItem().toString();
		String strTieuChi2 = cbbTieuChi2.getSelectedItem().toString();
		String strTieuChi3 = cbbNam.getSelectedItem().toString();
		String strTieuChi4 = "";
		if (radDoanhThu.isSelected()) {
			strTieuChi4 = "Doanh Thu";
		}else if (radSoLuong.isSelected()){
			strTieuChi4 = "Số Lượng Bán Ra";
		}

		return "Biểu Đồ " + strTieuChi4 + " " + strTieuChi1 + " " + strTieuChi2 + " Năm " + strTieuChi3;
	}
	
	private void exportToExcel() {
        String fileName = "Test_File";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Test Sheet");
		XSSFRow row = null;

		row = sheet.createRow(0);

		for (int j = 0; j < 5; j++) {
			Cell cell = row.createCell(j, CellType.STRING);
		    cell.setCellValue(table.getTableHeader()
		    		.getColumnModel()
		    		.getColumn(j)
		    		.getHeaderValue()
		    		.toString());
		}
		
		for (int i = 0; i < dataModel.getRowCount(); i++) {
		    row = sheet.createRow(i+1);
		    for (int j = 0; j < 5; j++) {
		        Cell cell = row.createCell(j, CellType.STRING);
		        cell.setCellValue(dataModel.getValueAt(i, j).toString());
		    }
		}

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Chọn vị trí lưu file Excel");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Tệp Excel (*.xlsx)", "xlsx"));
		int returnValue = fileChooser.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String filePath = selectedFile.getAbsolutePath();

			try (FileOutputStream fos = new FileOutputStream(filePath + ".xlsx")) {
				workbook.write(fos);
				JOptionPane.showMessageDialog(null, "In thành công", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    }
}
