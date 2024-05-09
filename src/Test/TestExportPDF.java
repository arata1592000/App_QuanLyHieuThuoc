//package Test;
//
//import org.apache.pdfbox.pdmodel.*;
//import org.apache.pdfbox.pdmodel.font.*;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import java.io.IOException;
//
//public class TestExportPDF {
//    public static void main(String[] args) {
//        try {
//            // Tạo một document mới
//            PDDocument document = new PDDocument();
//            PDPage page = new PDPage();
//            document.addPage(page);
//
//            // Tạo một font mặc định
////            PDFont font = PDType1Font.HELVETICA;
//
//            // Bắt đầu viết nội dung vào trang PDF
//            PDPageContentStream contentStream = new PDPageContentStream(document, page);
////            contentStream.setFont(font, 12);
//            contentStream.beginText();
//            contentStream.newLineAtOffset(100, 700); // Vị trí bắt đầu viết
//            contentStream.showText("Hello, World!"); // Nội dung
//            contentStream.endText();
//            contentStream.close();
//
//            // Lưu document vào một file
//            document.save("/images/examples.pdf");
//            document.close();
//            System.out.println("PDF file has been generated successfully!");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
