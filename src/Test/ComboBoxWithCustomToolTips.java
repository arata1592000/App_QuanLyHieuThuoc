package Test;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolTipUI;
import java.awt.*;

public class ComboBoxWithCustomToolTips {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Thiết lập thuộc tính UI cho tooltip
            UIManager.put("ToolTip.background", Color.YELLOW);
            UIManager.put("ToolTip.foreground", Color.RED);
            UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 16));
            UIManager.put("ToolTipUI", CustomToolTipUI.class.getName());

            JFrame frame = new JFrame("ComboBox with Custom Tooltips");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Tạo combobox với các mục
            String[] items = { "Apple", "Banana", "Cherry", "Date", "Elderberry" };
            String[] tooltips = {
                "A sweet red fruit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "A long yellow fruit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "A small red fruit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "A sweet brown fruit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "A small dark purple fruit. Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            };

            JComboBox<String> comboBox = new JComboBox<>(items);
            comboBox.setRenderer(new CustomComboBoxRenderer(tooltips));

            frame.add(comboBox, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }

    static class CustomComboBoxRenderer extends DefaultListCellRenderer {
        private final String[] tooltips;

        public CustomComboBoxRenderer(String[] tooltips) {
            this.tooltips = tooltips;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Thiết lập tooltip cho từng mục
            if (index > -1 && index < tooltips.length) {
                list.setToolTipText("<html><body width='300'>" + tooltips[index] + "</html></body>");
            } else {
                list.setToolTipText(null);
            }

            return component;
        }
    }

    public static class CustomToolTipUI extends BasicToolTipUI {
        private static final CustomToolTipUI sharedInstance = new CustomToolTipUI();
        private static JToolTip tip;
        private static Dimension size = new Dimension(300, 50); // Set kích thước cố định cho tooltip

        public static ComponentUI createUI(JComponent c) {
            return sharedInstance;
        }

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            tip = (JToolTip) c;
            tip.setOpaque(true);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Font font = c.getFont();
            FontMetrics metrics = c.getFontMetrics(font);
            Dimension size = c.getSize();
            g.setColor(c.getBackground());
            g.fillRect(0, 0, size.width, size.height);
            g.setColor(c.getForeground());
            g.setFont(font);
            String tipText = ((JToolTip) c).getTipText();
            if (tipText != null) {
                g.drawString(tipText, 3, metrics.getHeight());
            }
        }

        @Override
        public Dimension getPreferredSize(JComponent c) {
            return size;
        }
    }
}
