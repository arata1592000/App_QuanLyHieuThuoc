package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import com.toedter.calendar.JDateChooser;

import dao.Dao_HoaDon;
import dao.Dao_KhachHang;
import entity.HoaDon;
import entity.KhachHang;
import utils.ButtonDeleteEditor;
import utils.ButtonDeleteRenderer;
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
	private int widthComp;
	private int heightComp;
	private JLayeredPane layeredPane;
	private JPanel pMain;
	public Gui_HoaDon(int widthComp, int heightComp) {
		super();
		// TODO Auto-generated constructor stub
		this.widthComp = widthComp;
		this.heightComp = heightComp;
		this.setBackground(Color.WHITE);
		initCompoent();
		loadDataTable();
		
	}
	
	public void initCompoent() {
		layeredPane = new JLayeredPane();
		pMain = new JPanel();
		pAction = new JPanel();
		pSearch = new JPanel();
		txtSearch = new JTextField();
		btnTim = new JButton();
		pFillDate = new JPanel();
		lbl1 = new JLabel();
        dateChooserDate = new JDateChooser();
        pTable = new JPanel();
		lbl2 = new JLabel();
		
		layeredPane.setOpaque(true);
        layeredPane.setPreferredSize(new Dimension(widthComp, heightComp));
//        layeredPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		layeredPane.setBackground(Color.WHITE);
        pMain.setOpaque(false);
        pMain.setBounds(0,0, widthComp, heightComp);
		pMain.setBackground(Color.WHITE);
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
		pTable.setBackground(Color.WHITE);
		lbl2.setText("Danh sách hóa đơn");
		lbl2.setFont(new Font("Arial", Font.ITALIC, 30));
		String headers[] = {"Mã hóa đơn", "Ngày lập", "Tên nhân viên", "Tên KH", "Tổng thanh toán", "Xem chi tiết", "Loại HĐ", "Ghi chú"};
		dataModel = new DefaultTableModel(headers, 0) {
			@Override
            public boolean isCellEditable(int row, int column) {
                // Đặt tất cả các ô không thể chỉnh sửa
                return column==5;
            }
		};
		tableModel = new JTable(dataModel);
		tableModel.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableModel.setFont(new Font("Arial", Font.PLAIN, 15));
		tableModel.setRowHeight(30);
		tableModel.getColumn("Ngày lập").setPreferredWidth(20);
		tableModel.getColumn("Tên KH").setPreferredWidth(20);
		tableModel.getColumn("Loại HĐ").setPreferredWidth(20);
		tableModel.getColumn("Xem chi tiết").setCellRenderer(new ButtonShowOrderDetailsRenderer());
		tableModel.getColumn("Xem chi tiết").setCellEditor(new ButtonShowOrderDetailsEditor(new JCheckBox()));
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
		pMain.setLayout(new BorderLayout());
		pMain.add(pAction, BorderLayout.NORTH);
		pMain.add(pTable, BorderLayout.CENTER);
		layeredPane.add(pMain, JLayeredPane.DEFAULT_LAYER);
		this.add(layeredPane);
	}
	
	public void addRowHoaDon(HoaDon hd) {
		dataModel.addRow(new Object[] {hd.getMaHD(),
				hd.getNgayLap(),
				"An",
				hd.getKhachHang().getHoTen(),
				hd.getTongTien(),
				"Xem",
				hd.getLoaiHD(),
				hd.getGhiChu()
				});
	}
	
	public void loadDataTable() {
		List<HoaDon> listHD = new ArrayList();
		listHD = (new Dao_HoaDon()).readHoaDonFromSQL();
		for (HoaDon hd : listHD) {
			addRowHoaDon(hd);
		}
	}
	
	public class ButtonShowOrderDetailsEditor extends DefaultCellEditor{
		protected JButton button;
		protected JTable table;
		private ShowOrderDetailsButtonListener bListener = new ShowOrderDetailsButtonListener();


		/**
		 * Constructeur avec une checkBox
		 * @param checkBox
		 * @param count
		 */
		@SuppressWarnings("deprecation")
	    public ButtonShowOrderDetailsEditor(JCheckBox checkBox) {
	        super(checkBox);
	        button = new JButton();
	        button.setOpaque(true);
	        button.addActionListener(bListener);
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	    	bListener.setRow(row);
	    	bListener.setTable(table);
	        if (isSelected) {
	            button.setForeground(table.getSelectionForeground());
	            button.setBackground(table.getSelectionBackground());
	        } else {
	            button.setForeground(table.getForeground());
	            button.setBackground(table.getBackground());
	        }
	        button.setText((value == null) ? "" : value.toString());
	        return button;
	    }

	    @Override
	    public Object getCellEditorValue() {
	        return button.getText();
	    }
	}
	public class ButtonShowOrderDetailsRenderer extends JButton implements TableCellRenderer{
		public ButtonShowOrderDetailsRenderer() {
	        setOpaque(true);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        setText((value == null) ? "" : value.toString());
	        return this;
	    }
	}
	class ShowOrderDetailsButtonListener implements ActionListener{
		private int row;
	    private JTable table;
		private boolean isGuiCTHDDisplayed = false;
		private Gui_ChiTietHoaDon guiCTHD;

	    public void setRow(int row){
	    	this.row = row;
	    }
	    public void setTable(JTable table){
	    	this.table = table;
	    }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        
	    	if(layeredPane.getComponentsInLayer(JLayeredPane.PALETTE_LAYER).length == 0){
	    		isGuiCTHDDisplayed = false;
	    	}


	    	if (!isGuiCTHDDisplayed) {
	        // Xử lý sự kiện khi nhấn vào nút "Xem Chi Tiết"
	    		int row = table.convertRowIndexToModel(table.getEditingRow());
		        String maHD = (String) table.getModel().getValueAt(row, 0);
		        guiCTHD = new Gui_ChiTietHoaDon(maHD);
		        guiCTHD.setOpaque(true);
		        Dimension sizeGuiCTHD= guiCTHD.getPreferredSize(); // Lấy kích thước ưu tiên của guiCTHD
		        Dimension sizeGuiLayeredPane = layeredPane.getPreferredSize(); // Lấy container cha (ví dụ, JLayeredPane hoặc JPanel)

		        if (sizeGuiLayeredPane != null && sizeGuiLayeredPane.getWidth() > 0 && sizeGuiLayeredPane.getHeight() > 0) {
		            int x = (int) ((sizeGuiLayeredPane.getWidth() - sizeGuiCTHD.width) / 2);
		            int y = (int) ((sizeGuiLayeredPane.getHeight() - sizeGuiCTHD.height) / 2)-100;

		            guiCTHD.setBounds(x, y, sizeGuiCTHD.width, sizeGuiCTHD.height);
		        } else {
		            guiCTHD.setBounds(0, 0, sizeGuiCTHD.width, sizeGuiCTHD.height);
		        }
		        guiCTHD.addMouseListener(new MouseAdapter() {});
		        guiCTHD.addMouseMotionListener(new MouseAdapter() {});

		        layeredPane.add(guiCTHD, JLayeredPane.PALETTE_LAYER);
		     
		        isGuiCTHDDisplayed  = true;
	    	}
	    }
	}
	
}
