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
	
	public Gui_ChiTietHoaDon() {
		this.widthComp = 500;
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		initCompoent();
		this.setPreferredSize(new Dimension(this.widthComp, (int)(pContent.getPreferredSize().getHeight()+pHeader.getPreferredSize().getHeight()+pFooter.getPreferredSize().getHeight())));
		System.out.println(this.getPreferredSize().getWidth() + " " + this.getPreferredSize().getHeight());
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
        lbl2.setText("<html><div style='text-align: left;'>Mã HD:<br>Danh sách thuốc đã mua:</div></html>");
        setDataTable();
        pTableContent.setPreferredSize(new Dimension(this.widthComp, (int)((dataTable.size()+1)*30)));
        pTableContent.setLayout(new GridBagLayout());
        setPTableContent();
        pContent.setPreferredSize(new Dimension(this.widthComp, (int) (pTitleContent.getPreferredSize().getHeight() + pTableContent.getPreferredSize().getHeight())));
        pFooter.setLayout(new BorderLayout());
        pFooter.setBorder(new EmptyBorder(10, 10, 10, 10));
        pFooter.setPreferredSize(new Dimension(this.widthComp,100));
        pLeftFooter.setLayout(new GridLayout(3,1,5,5));
        lbl3.setText("VAT:");
        lbl3.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl4.setText("KM:");
        lbl4.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl5.setText("Tổng tiền");
        lbl5.setFont(new Font("Arial", Font.PLAIN, 14));
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
		headerTable.add("Tên");
        headerTable.add("SL");
        headerTable.add("Giá");
        headerTable.add("KM");
        headerTable.add("Tổng");
		Vector<Object> row1 = new Vector<>();
        row1.add("Tên thuốc 1");
        row1.add(10);
        row1.add(10000);
        row1.add(0);
        row1.add(100000);
        dataTable.add(row1);

        Vector<Object> row2 = new Vector<>();
        row2.add("Tên thuốc 2");
        row2.add(5);
        row2.add(20000);
        row2.add(0);
        row2.add(100000);
        dataTable.add(row2);

        Vector<Object> row3 = new Vector<>();
        row3.add("Tên thuốc 3");
        row3.add(8);
        row3.add(15000);
        row3.add(0);
        row3.add(120000);
        dataTable.add(row3);
        
        Vector<Object> row4 = new Vector<>();
        row4.add("Tên thuốc 4");
        row4.add(8);
        row4.add(15000);
        row4.add(0);
        row4.add(120000);
        dataTable.add(row4);
        
        Vector<Object> row5 = new Vector<>();
        row5.add("Tên thuốc 5");
        row5.add(8);
        row5.add(15000);
        row5.add(0);
        row5.add(120000);
        dataTable.add(row5);
        
        Vector<Object> row6 = new Vector<>();
        row6.add("Tên thuốc 6");
        row6.add(10);
        row6.add(10000);
        row6.add(0);
        row6.add(100000);
        dataTable.add(row6);

        Vector<Object> row7 = new Vector<>();
        row7.add("Tên thuốc 7");
        row7.add(5);
        row7.add(20000);
        row7.add(0);
        row7.add(100000);
        dataTable.add(row7);

        Vector<Object> row8 = new Vector<>();
        row8.add("Tên thuốc 8");
        row8.add(8);
        row8.add(15000);
        row8.add(0);
        row8.add(120000);
        dataTable.add(row8);
        
        Vector<Object> row9 = new Vector<>();
        row9.add("Tên thuốc 9");
        row9.add(8);
        row9.add(15000);
        row9.add(0);
        row9.add(120000);
        dataTable.add(row9);
        
        Vector<Object> row10 = new Vector<>();
        row10.add("Tên thuốc 10");
        row10.add(8);
        row10.add(15000);
        row10.add(0);
        row10.add(120000);
        dataTable.add(row10);

	}
}
