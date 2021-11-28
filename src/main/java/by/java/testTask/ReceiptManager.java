package by.java.testTask;

import by.java.testTask.model.ReceiptProduct;

import java.io.PrintStream;
import java.time.LocalDateTime;
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

    public ReceiptManager() {
    }

    private void printHeader() {
        outputStream.printf("%32s%n", "CASH RECEIPT");
        outputStream.printf("%33s%n", "SUPERMARKET 123");
        outputStream.printf("%39s%n", "12, MILKYWAY Galaxy/ Earth");
        outputStream.printf("%34s%n", "Tel: 123-456-7890");
        outputStream.printf("%s", "CASHIER: N1520");

        LocalDateTime localDateTime = LocalDateTime.now();
        String localDateAndTimeString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        outputStream.printf("%37s%n", localDateAndTimeString);
        outputStream.println(LINE);
    }

    private void printBody(List<ReceiptProduct> receiptProducts) {
        outputStream.printf("%s%14s%23s%11s%n", QUANTITY, DESCRIPTION, PRICE, TOTAL_PRICE);
        for (ReceiptProduct receiptProduct : receiptProducts) {
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

    public Double calculateTotal(List<ReceiptProduct> receiptProducts, Double discount) {
        Double totalPrice = 0.0;

        for (ReceiptProduct receiptProduct : receiptProducts) {
            totalPrice += receiptProduct.getTotalPrice();
        }
        totalPrice = totalPrice - (totalPrice * discount);
        return totalPrice;
    }

    public double getCardDiscount(String[] args) {
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
