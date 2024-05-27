package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dao.Dao_Thuoc;
import entity.Thuoc;

public class CustomMenuItemThuoc extends JMenuItem {
    private JPanel panel;
    private int widthComp;
    private int heightComp;
	private JLabel lblMaThuoc;
	private JLabel lblTenThuoc;
	private JLabel lblThanhPhan;
	private JLabel lblDonViTinh;
	private JLabel lblTrangThai;

    public CustomMenuItemThuoc(int widthComp, Thuoc thuoc) {
        super(thuoc.getMaThuoc());
        this.widthComp = widthComp;
        heightComp = 100;
        panel = createPanel(thuoc);
        setPreferredSize(new Dimension(widthComp, heightComp));
    }

    private JPanel createPanel(Thuoc thuoc) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(widthComp, heightComp));
        lblMaThuoc = new JLabel(String.format("%-35s", ("Mã thuốc: " + thuoc.getMaThuoc())));
        lblTenThuoc = new JLabel(String.format("%-35s", ("Tên thuốc: " + thuoc.getTenThuoc())));
        lblThanhPhan = new JLabel(String.format("%-35s", ("Thành phần: " + thuoc.getThanhPhan())));
        lblDonViTinh = new JLabel(String.format("%-35s", ("Đơn vị tính: "+thuoc.getDonViTinh())));
        lblTrangThai = new JLabel(String.format("%-35s", ("Trạng thái: "+thuoc.getTrangThai())));
        
        lblMaThuoc.setFont(new Font("Arial", Font.PLAIN, 17));
        lblTenThuoc.setFont(new Font("Arial", Font.PLAIN, 17));
        lblThanhPhan.setFont(new Font("Arial", Font.PLAIN, 17));
        lblDonViTinh.setFont(new Font("Arial", Font.PLAIN, 17));
        lblTrangThai.setFont(new Font("Arial", Font.PLAIN, 17));


        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);

        panel.add(lblMaThuoc);
        panel.add(lblTenThuoc);
        panel.add(lblDonViTinh);
        panel.add(lblThanhPhan);
        panel.add(lblTrangThai);

        return panel;
    }

	    public JPanel getCustomPanel() {
	        return panel;
	    }
}