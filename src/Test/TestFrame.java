package Test;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TestFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Customized JTable Example");

        // Data for the table
        Object[][] data = {
                {"Row 1 - Col 1", "Row 1 - Col 2", "Row 1 - Col 3"},
                {"Row 2 - Col 1", "Row 2 - Col 2", "Row 2 - Col 3"},
                {"Row 3 - Col 1", "Row 3 - Col 2", "Row 3 - Col 3"}
        };

        // Column headers
        String[] columns = {"Column 1", "Column 2", "Column 3"};

        // Create a default table model with non-editable cells
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Create the table
        JTable table = new JTable(model);

        // Set cell renderer to remove cell borders and disable header resizing
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Remove borders
                ((JComponent) c).setBorder(null);

                // Disable header resizing
                table.getTableHeader().setResizingAllowed(false);
                c.setBackground(Color.YELLOW);
                c.setForeground(Color.RED);

                return c;
            }
        });

        // Set table properties
        table.setShowGrid(false); // Remove cell borders
        table.setRowSelectionAllowed(false); // Disable row selection
        table.getTableHeader().setReorderingAllowed(false); // Disable column reordering

        // Add the table to a scroll pane and to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
