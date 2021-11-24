import model.ReceiptProduct;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptManager {

    private static final String QUANTITY = "QTY";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String PRICE = "PRICE";
    private static final String TOTAL_PRICE = "TOTAL";
    private static final String LINE = "---------------------------------------------------";
    private static final Double CARD_DISCOUNT = 0.15;

    private PrintStream outputStream;

    public ReceiptManager(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    private void printHeader() {
        outputStream.printf("%32s%n", "CASH RECEIPT");
        outputStream.printf("%33s%n", "SUPERMARKET 123");
        outputStream.printf("%39s%n", "12, MILKYWAY Galaxy/ Earth");
        outputStream.printf("%34s%n", "Tel: 123-456-7890");
        outputStream.printf("%s", "CASHIER: N1520");

        LocalDate localDate = LocalDate.now();
        String localDateString = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        outputStream.printf("%37s%n", localDateString);
        LocalTime localTime = LocalTime.now();
        String localTimeString = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        outputStream.printf("%51s%n", localTimeString);
        outputStream.println(LINE);
    }

    private void printBody(List<ReceiptProduct> receiptProducts) {
        outputStream.printf("%s%14s%23s%11s%n", QUANTITY, DESCRIPTION, PRICE, TOTAL_PRICE);
        for (int i = 0; i < receiptProducts.size(); i++) {
            ReceiptProduct receiptProduct = receiptProducts.get(i);

            Integer count = receiptProduct.getCount();
            String description = receiptProduct.getProduct().getName();
            Double price = receiptProduct.getProduct().getPrice();
            Double totalPrice = receiptProduct.getTotalPrice();

            outputStream.printf("%2s%12s%25.2f$%10.2f$%n", count, description, price, totalPrice);
        }
    }

    private void printFooter(Double totalPrice) {
        outputStream.printf("%s%45.2f$%n", TOTAL_PRICE, totalPrice);
    }

    public void printReceipt(List<ReceiptProduct> receiptProducts, Double totalPrice) {
        printHeader();
        printBody(receiptProducts);
        outputStream.println(LINE);
        printFooter(totalPrice);
    }

    public Map<Integer, Integer> readProducts(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();

        for (String arg : args) {
            String[] str = arg.split("-");
            if (str[0].equals("card")) {
                continue;
            }
            map.put(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }
        return map;
    }

    public double calculateTotal(List<ReceiptProduct> receiptProducts, Double discount) {
        double totalPrice = 0;

        for (int i = 0; i < receiptProducts.size(); i++) {
            ReceiptProduct receiptProduct = receiptProducts.get(i);
            totalPrice += receiptProduct.getTotalPrice();
        }
        totalPrice = totalPrice - (totalPrice * discount);
        return totalPrice;
    }

    public Double getCardDiscount(String[] args) {
        double discount = 0.0;
        for (String arg : args) {
            String[] str = arg.split("-");
            if (str[0].equals("card")) {
                discount += CARD_DISCOUNT;
            }
        }
        return discount;
    }
}
