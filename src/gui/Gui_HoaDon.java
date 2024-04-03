
package gui;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
public class Gui_HoaDon extends javax.swing.JPanel {
    public Gui_HoaDon() {
        initComponents();
    }                         
    private void initComponents() {

        txtTimTheoHD = new JTextField();
        btnTim = new JButton();
        jFillDate = new JDateChooser();
        lblLocTheoNgay = new JLabel();
        jTable = new JScrollPane();
        jTable1 = new JTable();
        lblDsHD = new JLabel();

        
        txtTimTheoHD.setText("Tìm kiếm theo hóa đơn");

        btnTim.setText("Tìm");

        lblLocTheoNgay.setText("Lọc theo ngày lập");

        jTable.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        jTable.setViewportBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        jTable.setFont(new Font("Arial", 0, 12)); 

        jTable1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        jTable1.setFont(new Font("Arial", 0, 12)); 
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {"HD2312220001", "23/03/2024", "Nguyễn Hạnh Bảo Ân", "Nguyễn Trà Bảo Trân", "310.000", "Xem", "Bán hàng", null},
                {"HD2312220002", "23/03/2023", "Nguyễn Hạnh Bảo Ân", "Nguyễn Trà Bảo Trân", "0", "Xem", "Đổi", "Đổi từ HD2312220001"}
            },
            new String [] {
                "Mã hóa đơn", "Ngày lập", "Tên nhân viên", "Tên khách hàng", "Tổng thanh toán", "Xem chi tiết", "Loại hóa đơn", "Ghi chú"
            }
        ));
        jTable1.setToolTipText("");
        jTable.setViewportView(jTable1);

        lblDsHD.setText("Danh sách hóa đơn");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jTable)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblDsHD)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTimTheoHD, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTim, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(lblLocTheoNgay)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFillDate, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jFillDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimTheoHD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTim)
                        .addComponent(lblLocTheoNgay)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDsHD)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTable, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
        );
    }                 
    private JButton btnTim;
    private JDateChooser jFillDate;
    private JScrollPane jTable;
    private JTable jTable1;
    private JLabel lblDsHD;
    private JLabel lblLocTheoNgay;
    private JTextField txtTimTheoHD;                   
}
