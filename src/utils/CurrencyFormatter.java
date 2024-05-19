package utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class CurrencyFormatter {
    private static final DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    static {
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
    }

    private static final DecimalFormat dfWithDecimals = new DecimalFormat("#,##0.00", symbols);
    private static final DecimalFormat dfWithoutDecimals = new DecimalFormat("#,##0", symbols);

    public static String formatVND(double amount) {
        return dfWithoutDecimals.format(amount) + " VNĐ";
    }

    public static String formatVNDWithDecimals(double amount) {
        return dfWithDecimals.format(amount) + " VNĐ";
    }

    public static double parseVND(String formattedAmount) throws ParseException {
        // Remove 'VNĐ' and any extra spaces
        String cleanedAmount = formattedAmount.replace("VNĐ", "").trim();
        // Determine if the string contains a decimal point to choose the right format
        if (cleanedAmount.contains(".")) {
            return dfWithDecimals.parse(cleanedAmount).doubleValue();
        } else {
            return dfWithoutDecimals.parse(cleanedAmount).doubleValue();
        }
    }

    public static void main(String[] args) {
        double amount = 12345678.90;
        double smallAmount = 60000.00;

        // Định dạng không có phần thập phân
        String formattedAmount = CurrencyFormatter.formatVND(amount);
        System.out.println(formattedAmount);  // Output: 12,345,679 VNĐ

        // Định dạng có phần thập phân
        String formattedAmountWithDecimals = CurrencyFormatter.formatVNDWithDecimals(amount);
        System.out.println(formattedAmountWithDecimals);  // Output: 12,345,678.90 VNĐ

        // Định dạng một số nhỏ hơn có phần thập phân
        String formattedSmallAmountWithDecimals = CurrencyFormatter.formatVNDWithDecimals(smallAmount);
        System.out.println(formattedSmallAmountWithDecimals);  // Output: 60,000.00 VNĐ

        try {
            // Phân tích chuỗi định dạng không có phần thập phân
            double parsedAmount = CurrencyFormatter.parseVND(formattedAmount);
            System.out.println(parsedAmount);  // Output: 12345679.0

            // Phân tích chuỗi định dạng có phần thập phân
            double parsedAmountWithDecimals = CurrencyFormatter.parseVND(formattedAmountWithDecimals);
            System.out.println(parsedAmountWithDecimals);  // Output: 12345678.9

            // Phân tích chuỗi định dạng nhỏ hơn có phần thập phân
            double parsedSmallAmountWithDecimals = CurrencyFormatter.parseVND(formattedSmallAmountWithDecimals);
            System.out.println(parsedSmallAmountWithDecimals);  // Output: 60000.0
        } catch (ParseException e) {
            System.err.println("Lỗi khi phân tích chuỗi định dạng: " + e.getMessage());
        }
    }
}
