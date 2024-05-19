package utils;

import java.awt.image.BufferedImage;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class BarcodeGenerator {

    public static BufferedImage generateBarcode(String barcodeText) throws Exception {
        Barcode barcode = BarcodeFactory.createCode128B(barcodeText);

        return BarcodeImageHandler.getImage(barcode);
    }

}
