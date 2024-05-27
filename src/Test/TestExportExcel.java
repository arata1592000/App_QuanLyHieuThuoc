package test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestExportExcel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Export Excel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton jButtonPDF = new JButton("Export to Excel");
            jButtonPDF.addActionListener((ActionEvent evt) -> {
                exportToExcel();
            });

            JPanel panel = new JPanel();
            panel.add(jButtonPDF);
            frame.add(panel);

            frame.pack();
            frame.setVisible(true);
        });
    }

    private static void exportToExcel() {
        try {
            String fileName = "Test_File";
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Test Sheet");
            XSSFRow row = null;

            for (int i = 0; i < 10; i++) {
                row = sheet.createRow(i);
                for (int j = 0; j < 5; j++) {
                    Cell cell = row.createCell(j, CellType.STRING);
                    cell.setCellValue("Row " + (i + 1) + ", Column " + (j + 1));
                }
            }

            File file = new File(fileName + ".xlsx");
            try (FileOutputStream fis = new FileOutputStream(file)) {
                workbook.write(fis);
            }

            JOptionPane.showMessageDialog(null, "File exported successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while exporting the file.");
        }
    }
}
