package Test;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;

public class Test1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Date Picker Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        JDateChooser dateChooser = new JDateChooser();

        panel.add(dateChooser);
        frame.add(panel);
        frame.setVisible(true);
    }
}
