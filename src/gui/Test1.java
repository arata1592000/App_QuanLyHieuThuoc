//package gui;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import java.io.FileOutputStream;
//
//public class Test1 {
//    public static void main(String[] args) {
//        Document document = new Document();
//        try {
//            // Tạo một tệp PDF mới
//            PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
//            document.open();
//            
//            // Viết nội dung vào tệp PDF
//            document.add(new Paragraph("Đây là nội dung được viết vào tệp PDF bằng Java."));
//            document.add(new Paragraph("Xin chào, đây là một ví dụ đơn giản về việc sử dụng thư viện iText để tạo và viết nội dung vào tệp PDF."));
//            document.add(new Paragraph("Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!"));
//            
//            document.close();
//            System.out.println("Tệp PDF đã được tạo thành công!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
