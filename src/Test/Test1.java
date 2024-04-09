package Test;

import javax.swing.*;
import java.awt.*;

public class Test1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("LayeredPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Tạo một JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300, 200));

        // Thêm các thành phần vào các lớp khác nhau của JLayeredPane
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        layeredPane.add(button1, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(button3, JLayeredPane.MODAL_LAYER);

        // Đặt vị trí và kích thước của các thành phần
        button1.setBounds(50, 50, 100, 30);
        button2.setBounds(100, 100, 100, 30);
        button3.setBounds(150, 150, 100, 30);

        frame.add(layeredPane);
        frame.pack();
        frame.setVisible(true);
    }
}
