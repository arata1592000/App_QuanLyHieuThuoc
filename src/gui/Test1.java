package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Test1 extends JFrame {
    private BufferedImage originalImage;

    public Test1() {
        try {
            originalImage = ImageIO.read(new File("C:\\Users\\truon\\OneDrive\\Hình ảnh\\133549934930738840.jpg")); // Đọc hình ảnh từ tập tin
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Ellipse Image");

        // Kích thước của JFrame sẽ là kích thước của hình ảnh
        setSize(300, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Tạo một hình ellipse với kích thước của hình ảnh
                Ellipse2D.Double ellipse = new Ellipse2D.Double(0, 0, originalImage.getWidth(), originalImage.getHeight());
                g2d.setClip(ellipse);

                // Vẽ hình ảnh vào hình ellipse với kích thước đã được định nghĩa
                g2d.drawImage(originalImage, 0, 0, 150, 300, this);
            }
        };
        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Test1 ex = new Test1();
            ex.setVisible(true);
        });
    }
}
