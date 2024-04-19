package utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageUtils {
    // Bo tròn hình ảnh
    public static ImageIcon roundImageIcon(ImageIcon icon, int width, int height) {
        BufferedImage roundedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setClip(new RoundRectangle2D.Double(0, 0, width, height, width, height));
        g2d.drawImage(icon.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(roundedImage);
    }
}