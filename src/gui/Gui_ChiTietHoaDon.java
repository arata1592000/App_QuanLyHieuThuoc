package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.Dao_HoaDon;
import entity.ChiTietHoaDon;
import entity.HoaDon;

public class Gui_ChiTietHoaDon extends JPanel{
	private JLabel lbl3;
	private JLabel lbl2;
	private JPanel pHeader;
	private JLabel lbl1;
	private JPanel pContent;
	private JLabel lbl4;
	private JLabel lbl5;
	private JLabel lbl6;
	private JPanel pTableContent;
	private JPanel pFooter;
	private JPanel pNorthFooter;
	private JPanel pLeftFooter;
	private JPanel pRightFooter;
	private JButton btnDong;
	private Vector<String> headerTable;
	private Vector<Vector<Object>> dataTable;
	private JPanel pTitleContent;
	private int widthComp;
	private String maHD;
	private HoaDon hd;
	
	public Gui_ChiTietHoaDon(String maHD) {
		this.widthComp = 500;
		this.maHD = maHD;
		hd = (new Dao_HoaDon()).findHoaDonByMaHD(maHD);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		initCompoent();
		this.setPreferredSize(new Dimension(this.widthComp, (int)(pContent.getPreferredSize().getHeight()+pHeader.getPreferredSize().getHeight()+pFooter.getPreferredSize().getHeight())));
	}
	
	public void initCompoent() {
		pHeader = new JPanel();
		lbl1 = new JLabel();
		pContent = new JPanel();
		pTitleContent = new JPanel();
		lbl2 = new JLabel();
		pTableContent = new JPanel();
		headerTable = new Vector<>();
		dataTable = new Vector<>();
		pFooter = new JPanel();
		pNorthFooter = new JPanel();
		pLeftFooter = new JPanel();
		pRightFooter = new JPanel();
		lbl3 = new JLabel();
		lbl4 = new JLabel();
		lbl5 = new JLabel();
		btnDong = new JButton();
		
		pHeader.setBackground(new Color(168, 164, 164));
		pHeader.setPreferredSize(new Dimension(this.widthComp, 50));
		lbl1.setText("Chi tiết hóa đơn");
		lbl1.setFont(new Font("Arial", Font.PLAIN, 25));
		pContent.setLayout(new BorderLayout());
		pContent.setBorder(new EmptyBorder(10, 10, 10, 10));
		pTitleContent.setLayout(new FlowLayout(FlowLayout.LEFT));
		pTitleContent.setPreferredSize(new Dimension(this.widthComp, 50));
        lbl2.setFont(new Font("Arial", Font.PLAIN, 15));        
        setDataTable();
        pTableContent.setPreferredSize(new Dimension(this.widthComp, (int)((dataTable.size()+1)*30)));
        pTableContent.setLayout(new GridBagLayout());
        setPTableContent();
        pContent.setPreferredSize(new Dimension(this.widthComp, (int) (pTitleContent.getPreferredSize().getHeight() + pTableContent.getPreferredSize().getHeight())));
        pFooter.setLayout(new BorderLayout());
        pFooter.setBorder(new EmptyBorder(10, 10, 10, 10));
        pFooter.setPreferredSize(new Dimension(this.widthComp,100));
        pLeftFooter.setLayout(new GridLayout(3,1,5,5));
        lbl3.setFont(new Font("Arial", Font.PLAIN, 14));
    	lbl3.setMaximumSize(new Dimension(100, 45));
        lbl4.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl5.setFont(new Font("Arial", Font.PLAIN, 14));
        if (hd.getLoaiHD().equals("Bán hàng")) {
            lbl2.setText("<html><div style='text-align: left;'>Mã HD:" + hd.getMaHD() +"<br>Danh sách thuốc đã mua:</div></html>");
        	lbl3.setText("VAT: " + hd.getThue());
            lbl4.setText("KM:");
            lbl5.setText("Tổng tiền: " + hd.getTongTien());
        }else if (hd.getLoaiHD().equals("Trả thuốc")) {
            lbl2.setText("<html><div style='text-align: left;'>Mã HD:" + hd.getMaHD() +"<br>Danh sách thuốc đã trả:</div></html>");
        	lbl3.setText(hd.getGhiChu() );
        	lbl4.setText("Được hoàn trả " + hd.getTongTien() + "đ");
        }else if (hd.getLoaiHD().equals("Đổi thuốc")) {
            lbl2.setText("<html><div style='text-align: left;'>Mã HD:" + hd.getMaHD() +"<br>Danh sách thuốc đã đổi:</div></html>");
        	lbl3.setText(hd.getGhiChu());

        }
        btnDong.setText("Đóng");
        

        
        pHeader.add(lbl1);
        pTitleContent.add(lbl2);
        pContent.add(pTitleContent, BorderLayout.NORTH);
        pContent.add(pTableContent, BorderLayout.CENTER);
        pLeftFooter.add(lbl3);
        pLeftFooter.add(lbl4);
        pLeftFooter.add(lbl5);
        pRightFooter.add(btnDong);
        pFooter.add(pLeftFooter, BorderLayout.WEST);
        pFooter.add(pRightFooter, BorderLayout.EAST);
        this.add(pHeader, BorderLayout.NORTH);
        this.add(pContent, BorderLayout.CENTER);
        this.add(pFooter, BorderLayout.SOUTH);
        
        btnDong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa panel khi nút "Đóng" được nhấn
            	JLayeredPane layeredPane = (JLayeredPane) getParent();
                layeredPane.remove(Gui_ChiTietHoaDon.this);
                // Cập nhật lại giao diện
                layeredPane.validate();
                layeredPane.repaint();
            }
        });
	}
	
	public void setPTableContent() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
		for (String columnName : headerTable) {
            JLabel headerLabel = new JLabel(columnName);
            headerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            pTableContent.add(headerLabel, constraints);
            constraints.gridx++;
        }
		for (Vector<Object> row : dataTable) {
    		constraints.gridx = 0;
    		constraints.gridy++;
            for (Object cell : row) {
                JLabel cellLabel = new JLabel(cell.toString());
                cellLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                pTableContent.add(cellLabel, constraints);
                constraints.gridx++;
            }
        }
	}
	
	public void setDataTable() {
		headerTable.add("Mã");
		headerTable.add("Tên");
        headerTable.add("SL");
        headerTable.add("Giá");
        headerTable.add("KM");
        headerTable.add("Tổng");
		for (ChiTietHoaDon cthd : hd.getChiTietHoaDon()) {
			Vector<Object> rowTable = new Vector<>();
			rowTable.add(cthd.getMaThuoc());
			rowTable.add(cthd.getTenThuoc());
			rowTable.add(cthd.getSoLuong() + " " + cthd.getDonViTinh());
			rowTable.add(cthd.getGia());
			rowTable.add(cthd.getKhuyenMai());
			rowTable.add(cthd.getTongTienSanPham());
			dataTable.add(rowTable);
		}
	}
}
