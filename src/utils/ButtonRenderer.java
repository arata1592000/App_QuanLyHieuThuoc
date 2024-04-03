package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
 
public class ButtonRenderer extends JButton implements TableCellRenderer{
 
	public ButtonRenderer() {
		ImageIcon image = new ImageIcon("images/delete.png");
	    Image img = image.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
	    setIcon(new ImageIcon(img));
        setFocusable(false);
        setRolloverEnabled(false);
    }
	
    public Component getTableCellRendererComponent( JTable table, Object value,
                                                    boolean isSelected, boolean isFocus,
                                                    int row, int col) {
    	
	    setBackground(new Color(240,236,236));
    	setOpaque(true);
        setContentAreaFilled(true);
        setBorderPainted(false);
        setFocusPainted(false);
        return this;
    }
}