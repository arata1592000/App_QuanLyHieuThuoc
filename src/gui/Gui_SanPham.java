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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import utils.ButtonEditor;
import utils.ButtonRenderer;
import utils.RoundBorder;

public class Gui_SanPham extends JPanel implements ActionListener{
	private int widthComp;
	private int heightComp;
	private JPanel pMenu;
	private JButton btnBanThuoc;
	private JButton btnDoiTraThuoc;
	private JButton btnXemThongTin;
	private JPanel pMain;
	private JButton btnBack;


	public Gui_SanPham(int width, int height) {
		widthComp = width;
		heightComp = height;
		this.setLayout(new BorderLayout());
		initCompoent();
	}

	public void initCompoent() {
		pMenu = new JPanel();
		btnBanThuoc = new JButton();
		btnDoiTraThuoc = new JButton();
		btnXemThongTin = new JButton();
		pMain = new JPanel();
		
		pMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
		pMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		btnBanThuoc.setText("Bán thuốc");
		btnBanThuoc.setForeground(Color.WHITE);
		btnBanThuoc.setFont(new Font("Arial", Font.BOLD, 16));
		btnBanThuoc.setBackground(new Color (40,156,164));
		btnBanThuoc.setOpaque(true);
		btnBanThuoc.setContentAreaFilled(true);
        btnBanThuoc.setBorderPainted(false);
        btnBanThuoc.setFocusPainted(false);
		btnDoiTraThuoc.setText("Đổi trả thuốc");
		btnDoiTraThuoc.setForeground(Color.BLACK);
		btnDoiTraThuoc.setFont(new Font("Arial", Font.BOLD, 16));
		btnDoiTraThuoc.setBackground(new Color(224,220,220));
		btnDoiTraThuoc.setOpaque(true);
		btnDoiTraThuoc.setContentAreaFilled(true);
        btnDoiTraThuoc.setBorderPainted(false);
        btnDoiTraThuoc.setFocusPainted(false);
		btnXemThongTin.setText("Xem thông tin");
		btnXemThongTin.setForeground(Color.BLACK);
		btnXemThongTin.setFont(new Font("Arial", Font.BOLD, 16));
		btnXemThongTin.setBackground(new Color(224,220,220));
		btnXemThongTin.setOpaque(true);
		btnXemThongTin.setContentAreaFilled(true);
        btnXemThongTin.setBorderPainted(false);
        btnXemThongTin.setFocusPainted(false);
		pMenu.setBackground(Color.WHITE);
		pMenu.setPreferredSize(new Dimension((int) (widthComp),(int) (heightComp*0.07)));
		pMain.setBackground(Color.WHITE);
		pMain.setPreferredSize(new Dimension((int) (widthComp),(int) (heightComp*0.9)));

		btnBanThuoc.addActionListener(this);
		btnDoiTraThuoc.addActionListener(this);
		btnXemThongTin.addActionListener(this);
		
		pMenu.add(Box.createHorizontalStrut(40));
		pMenu.add(btnBanThuoc);
		pMenu.add(Box.createHorizontalStrut(40));
		pMenu.add(btnDoiTraThuoc);
		pMenu.add(Box.createHorizontalStrut(40));
		pMenu.add(btnXemThongTin);
		pMain.add(new Gui_BanThuoc(widthComp, (int) (heightComp*0.9)));
		btnBack = btnBanThuoc;
		this.add(pMenu, BorderLayout.NORTH);
		this.add(pMain, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object act = e.getSource();
		if (act.equals(btnBanThuoc)) {
			if (!btnBack.equals(btnBanThuoc)) {
				pMain.removeAll();
				pMain.add(new Gui_BanThuoc(pMain.getWidth(), pMain.getHeight()));
				btnBanThuoc.setBackground(new Color (40,156,164));
				btnBanThuoc.setForeground(Color.WHITE);
				btnBack.setBackground(new Color(224,220,220));
				btnBack.setForeground(Color.BLACK);
				btnBack = btnBanThuoc;
			}			
		}else if (act.equals(btnDoiTraThuoc)) {
			if (!btnBack.equals(btnDoiTraThuoc)) {
				pMain.removeAll();
				pMain.add(new Gui_DoiTraThuoc(pMain.getWidth(), pMain.getHeight()));
				btnDoiTraThuoc.setBackground(new Color (40,156,164));
				btnDoiTraThuoc.setForeground(Color.WHITE);
				btnBack.setBackground(new Color(224,220,220));
				btnBack.setForeground(Color.BLACK);
				btnBack = btnDoiTraThuoc;
			}
		}else if (act.equals(btnXemThongTin)) {
			if (!btnBack.equals(btnXemThongTin)) {
				pMain.removeAll();
				pMain.add(new Gui_XemThongTinThuoc(pMain.getWidth(), pMain.getHeight()));
				btnXemThongTin.setBackground(new Color (40,156,164));
				btnXemThongTin.setForeground(Color.WHITE);
				btnBack.setBackground(new Color(224,220,220));
				btnBack.setForeground(Color.BLACK);
				btnBack = btnXemThongTin;
			}
		}
		revalidate();
        repaint(); // Vẽ lại panel
	}
}
