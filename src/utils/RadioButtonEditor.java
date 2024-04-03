package utils;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;

public class RadioButtonEditor extends DefaultCellEditor implements ItemListener {
    private JRadioButton button;

    public RadioButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JRadioButton();
        button.setHorizontalAlignment(JRadioButton.CENTER);
        button.addItemListener(this);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
            int column) {
        if (value instanceof Boolean) {
            button.setSelected((boolean) value);
        } else if (value instanceof String) {
            button.setSelected(Boolean.parseBoolean((String) value));
        }

        return button;
    }

    public Object getCellEditorValue() {
        return button.isSelected();
    }

    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}
