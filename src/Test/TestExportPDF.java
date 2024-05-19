package Test;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfBody;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import dao.Dao_HoaDon;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import utils.BarcodeGenerator;
import utils.CurrencyFormatter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.apache.poi.sl.usermodel.TextParagraph.TextAlign;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;



public class TestExportPDF {

    public static final String font = "libs/arial-unicode-ms.ttf";
    private static final String filePath = "lastOrder.pdf";

	
    public static void main(String[] args) {

    	HoaDon hd = (new Dao_HoaDon()).findHoaDonByMaHD("HD1905240001");
    	
        try {
            //Create Document instance.
            Document document = new Document();
            document.setMargins(16, 16, 32, 24);

            //Create OutputStream instance.
            OutputStream outputStream
                    = new FileOutputStream(new File(filePath));

            //Create PDFWriter instance.
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setLanguage("VN");

            //Open the document.
            document.open();
            BaseFont bf = BaseFont.createFont(font, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//            Header
            Font headingFont = new Font(bf, 18, Font.BOLD);
            Font subHeadingFont = new Font(bf, 16, Font.BOLD);
            Font bold = new Font(bf, 14, Font.BOLD);
            Font italic = new Font(bf, 14, Font.ITALIC);
            Font font = new Font(bf, 14);
            LineSeparator separator = new LineSeparator(font);

            Paragraph sofware = new Paragraph("NHÀ THUỐC ÂN CẦN", headingFont);
            Paragraph desc = new Paragraph("\"Sức khỏe là ưu tiên – Chúng tôi là lựa chọn.\"", italic);
            Paragraph store = new Paragraph("12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Hồ Chí Minh", bold);

            sofware.setAlignment(TextAlign.CENTER.ordinal());
            desc.setAlignment(TextAlign.CENTER.ordinal());
            store.setAlignment(TextAlign.CENTER.ordinal());
            document.add(sofware);
            document.add(desc);
            document.add(store);
            document.add(separator);

//            Content
            Paragraph orderTitle = new Paragraph("HÓA ĐƠN BÁN HÀNG", subHeadingFont);
            orderTitle.setAlignment(TextAlign.CENTER.ordinal());
            document.add(orderTitle);
            document.add(separator);

//            ReturnOrder info
            document.add(new Paragraph(String.format("Mã hóa đơn:  %s", hd.getMaHD()), font));
            document.add(new Paragraph(String.format("Ngày tạo:  %s", hd.getNgayLap().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))), font));
            document.add(new Paragraph(String.format("Nhân viên:  %s", hd.getNhanVien().getHoTen()), font));
            if (hd.getKhachHang().getHoTen().equals("Mặc định")) {
                document.add(new Paragraph(String.format("Khách hàng:  %s", "Ẩn danh"), font));
            } else {
                document.add(new Paragraph(String.format("Khách hàng:  %s", hd.getKhachHang().getHoTen()), font));
            }
            document.add(separator);

//          ReturnOrder detail  
            PdfPTable table = new PdfPTable(9);
            table.setSpacingBefore(20);
            table.setWidthPercentage(100);
            addTableHeader(table, subHeadingFont);

            int index = 0;
            for (ChiTietHoaDon cthd : hd.getChiTietHoaDon()) {
                table.addCell(new PdfPCell(new Phrase(cthd.getMaThuoc(), font)));
                table.addCell(new PdfPCell(new Phrase(cthd.getTenThuoc(), font)));
                table.addCell(new PdfPCell(new Phrase(cthd.getSoLuong()+"", font)));
                table.addCell(new PdfPCell(new Phrase(cthd.getDonViTinh(), font)));
                table.addCell(new PdfPCell(new Phrase(CurrencyFormatter.formatVNDWithDecimals(cthd.getGia()), font)));
                table.addCell(new PdfPCell(new Phrase(cthd.getThanhPhan(), font)));
                table.addCell(new PdfPCell(new Phrase(cthd.getNgayHetHan().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), font)));
                table.addCell(new PdfPCell(new Phrase(CurrencyFormatter.formatVNDWithDecimals(cthd.getKhuyenMai()), font)));
                table.addCell(new PdfPCell(new Phrase(CurrencyFormatter.formatVNDWithDecimals(cthd.getTongTienSanPham()), font)));

            }

            document.add(table);

//            Order footer    
            document.add(new Paragraph("Tổng tiền: " + CurrencyFormatter.formatVNDWithDecimals(hd.getTongTien()), font));
            document.add(new Paragraph("VAT: " + hd.getThue() + "%", font));
            document.add(new Paragraph("Thành tiền: " + CurrencyFormatter.formatVNDWithDecimals(hd.getThanhTien()), subHeadingFont));
            document.add(separator);

            document.add(new Paragraph("Phương thức thanh toán " + hd.getPhuongThucTT(), font));
            document.add(new Paragraph("Tiền khách đưa: " + CurrencyFormatter.formatVNDWithDecimals(hd.getTienKhachDua()), font));
            document.add(new Paragraph("Tiền thừa: " + CurrencyFormatter.formatVNDWithDecimals(hd.getTienThua()), font));

//            Footer
            document.add(separator);
            Paragraph hotline = new Paragraph("Tổng đài góp ý/khiếu nại: 1900 1818", bold);
            Paragraph note = new Paragraph("Lưu ý: Nhà thuốc Ân Cần có thể xuất lại hóa đơn, vui lòng liên hệ nhân viên bán hàng để được in lại.", italic);

            Paragraph note2 = new Paragraph("Cảm ơn quý khách. Hẹn gặp lại", font);

            hotline.setAlignment(TextAlign.CENTER.ordinal());
            note.setAlignment(TextAlign.CENTER.ordinal());
            note.setIndentationLeft(50);
            note.setIndentationRight(50);
            note2.setAlignment(TextAlign.CENTER.ordinal());

            document.add(hotline);
            document.add(note);
            document.add(note2);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(BarcodeGenerator.generateBarcode(hd.getMaHD()), "PNG", baos);
            byte[] bytes = resizeImage(baos.toByteArray(), 500, 200);
            // Create an Image from the byte array
            Image image = Image.getInstance(bytes);
            image.setAlignment(1);
            document.add(image);

            //Close document and outputStream.
            document.close();
            outputStream.close();

            Desktop d = Desktop.getDesktop();
            d.open(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    
    public static byte[] resizeImage(byte[] imageData, int targetWidth, int targetHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageData));
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        double widthRatio = (double) targetWidth / originalWidth;
        double heightRatio = (double) targetHeight / originalHeight;

        double ratio = Math.min(widthRatio, heightRatio);

        int scaledWidth = (int) Math.round(originalWidth * ratio);
        int scaledHeight = (int) Math.round(originalHeight * ratio);

        BufferedImage resizedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.SCALE_SMOOTH);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        byte[] resizedImageData;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(resizedImage, "PNG", baos);
            resizedImageData = baos.toByteArray();
        }

        return resizedImageData;
    }

    private static void addTableHeader(PdfPTable table, Font font) {
        Stream.of("Mã thuốc", "Tên thuốc", "Số lượng", "Đơn vị tính", "Giá", "Thành phần", "Ngày hết hạn", "Khuyến mãi", "Tổng tiền")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.WHITE);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle, font));
                    header.setPadding(4);
                    table.addCell(header);
                });
    }
}
