package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.Dao_NhanVien;
import entity.NhanVien;


public class Gui_TrangChu extends JPanel{

	private NhanVien nv;
	private int widthComp, heightComp;
	private JPanel pAvatarNV;
	private JPanel pInforNV;
	private JPanel pNorth;
 
	public Gui_TrangChu(NhanVien nv, int widthComp, int heightComp) {
		super();
		// TODO Auto-generated constructor stub
		this.nv = nv;
		this.widthComp = widthComp;
		this.heightComp = heightComp;
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(widthComp, heightComp));
		initComponent();
	}
	
	public void initComponent() {
		pNorth = getPanelNorth();
		pAvatarNV = getPanelAvatarNV();
		pInforNV = getPanelInforNV();
		
		this.add(pNorth, BorderLayout.NORTH);
		this.add(pAvatarNV, BorderLayout.WEST);
		this.add(pInforNV, BorderLayout.EAST);
	}
	
	public JPanel getPanelNorth() {
		JPanel pMain = new JPanel();
//		JLabel lbl1 = new JLabel();
		
		pMain.setPreferredSize(new Dimension((int)(widthComp*0.95), (int) (heightComp*0)));
		pMain.setBackground(Color.WHITE);
		return pMain;
	}
	
	public JPanel getPanelAvatarNV() {
	    JPanel pMain = new JPanel();
	    JLabel lblAnh = new JLabel();

	    pMain.setPreferredSize(new Dimension((int) (widthComp * 0.45), (int) (heightComp * 0.7)));
	    pMain.setBackground(Color.WHITE);
	    
	    // Tạo một GridBagLayout cho pMain
	    pMain.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.fill = GridBagConstraints.CENTER;

	    BufferedImage anh = null;
	    try {
	        anh = ImageIO.read(new ByteArrayInputStream((new Dao_NhanVien()).getAnhByMaNV(nv.getMaNV())));
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Có lỗi, không tải được ảnh nhân viên!");
	    }

	    int widthAvatar = (int) (widthComp * 0.4);
	    int heightAvatar = (int) (heightComp * 0.8);

	    // Tính toán kích thước hình ellipse
	    int diameter = Math.min(widthAvatar, heightAvatar);

	    // Tính toán vị trí Y của hình ellipse
	    int yEllipse = (heightAvatar - diameter) / 2;

	    // Tạo BufferedImage mới để chứa hình ellipse
	    BufferedImage roundedImage = new BufferedImage(widthAvatar, heightAvatar, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = roundedImage.createGraphics();

	    // Tạo hình ellipse
	    Ellipse2D.Float ellipse = new Ellipse2D.Float(0, yEllipse, diameter, diameter);

	    // Thiết lập các thông số vẽ
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setClip(ellipse);

	    // Vẽ hình ảnh đã được scaled lên hình ellipse
	    g2d.drawImage(anh.getScaledInstance(widthAvatar, heightAvatar, Image.SCALE_SMOOTH), 0, 0, null);

	    // Giải phóng đối tượng Graphics2D
	    g2d.dispose();
	    anh = null;
	    System.gc();
	    
	    ImageIcon imageIcon = new ImageIcon(roundedImage);
	    
	    lblAnh.setIcon(imageIcon);
	    lblAnh.setAlignmentX(CENTER_ALIGNMENT);
	    lblAnh.setAlignmentY(CENTER_ALIGNMENT);

	    // Thêm lblAnh vào pMain với ràng buộc để căn giữa
	    pMain.add(lblAnh, gbc);
	    
	    return pMain;
	}
	
	public JPanel getPanelInforNV() {
		JPanel pMain = new JPanel();
		JLabel lbl1 = new JLabel();
		JLabel lbl2 = new JLabel();
		JLabel lbl3 = new JLabel();
		JLabel lbl4 = new JLabel();
		JLabel lbl5 = new JLabel();
		JLabel lbl6 = new JLabel();
		JLabel lbl7 = new JLabel();

		pMain.setPreferredSize(new Dimension((int) (widthComp*0.45), (int) (heightComp*0.8)));
		pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));
		pMain.setBorder(BorderFactory.createEmptyBorder(100, 20, 20, 20));
		pMain.setBackground(Color.WHITE);
		lbl1.setText("Thông tin nhân viên trực ca");
		lbl1.setFont(new Font("Arial", Font.BOLD, 35));
		lbl2.setText("Mã nhân viên: " + nv.getMaNV());
		lbl2.setFont(new Font("Arial", Font.ITALIC, 30));
		lbl3.setText("Họ và tên: " + nv.getHoTen());
		lbl3.setFont(new Font("Arial", Font.ITALIC, 30));
		lbl4.setText("Ngày sinh: " + nv.getNgaySinh());
		lbl4.setFont(new Font("Arial", Font.ITALIC, 30));
		lbl5.setText("Giới tính: " + nv.getGioiTinh());
		lbl5.setFont(new Font("Arial", Font.ITALIC, 30));
		lbl6.setText("Ngày vào làm: " + nv.getNgayVaoLam());
		lbl6.setFont(new Font("Arial", Font.ITALIC, 30));
		lbl7.setText("Địa chỉ: " + nv.getDiaChi());
		lbl7.setFont(new Font("Arial", Font.ITALIC, 30));
		
		pMain.add(lbl1);
		pMain.add(Box.createVerticalStrut(40));
		pMain.add(lbl2);
		pMain.add(Box.createVerticalStrut(20));
		pMain.add(lbl3);
		pMain.add(Box.createVerticalStrut(20));
		pMain.add(lbl4);
		pMain.add(Box.createVerticalStrut(20));
		pMain.add(lbl5);
		pMain.add(Box.createVerticalStrut(20));
		pMain.add(lbl6);
		pMain.add(Box.createVerticalStrut(20));
		pMain.add(lbl7);
		
		return pMain;
	}
}
