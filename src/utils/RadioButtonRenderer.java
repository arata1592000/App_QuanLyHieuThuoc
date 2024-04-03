package utils;

import java.awt.Component;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RadioButtonRenderer extends JRadioButton implements TableCellRenderer {
    public RadioButtonRenderer() {
        setHorizontalAlignment(JRadioButton.CENTER);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // Kiểm tra nếu giá trị là một boolean
        if (value instanceof Boolean) {
            setSelected((Boolean) value); // Cập nhật trạng thái của nút radio dựa trên giá trị boolean
        }
        return this;
    }
}
