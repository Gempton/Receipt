import repository.ProductDao;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Manager {

    private static final double VAT = 0.2;
    private static final String QUANTITY = "QTY";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String PRICE = "PRICE";
    private static final String TOTAL_PRICE = "TOTAL";
    private static final double DISCOUNT_CARD = 0.15;
    private static final double DISCOUNT = 0.1;

    private ProductDao productDao;
    private PrintStream outputStream;

    public Manager(ProductDao productDao, PrintStream outputStream) {
        this.productDao = productDao;
        this.outputStream = outputStream;
    }

    public void printInformation() {
        outputStream.printf("%32s\n", "CASH RECEIPT");
        outputStream.printf("%33s\n", "SUPERMARKET 123");
        outputStream.printf("%39s\n", "12, MILKYWAY Galaxy/ Earth");
        outputStream.printf("%34s\n", "Tel: 123-456-7890");
        outputStream.printf("%s", "CASHIER: N1520");
    }

    public void printDate() {
        LocalDate localDate = LocalDate.now();
        String localDateString = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        outputStream.printf("%37s\n", localDateString);
    }

    public void printTime() {
        LocalTime localTime = LocalTime.now();
        String localTimeString = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        outputStream.printf("%51s\n", localTimeString);
        outputStream.println("---------------------------------------------------");
    }

    public void runProgram(String[] args) {
        Map<String, String> map = new HashMap<>();

        for (String arg : args) {
            String[] str = arg.split("-");
            map.put(str[0], str[1]);
        }

        double sum = printLineOfProduct(map);

        outputStream.println("---------------------------------------------------");
        outputStream.printf("%s%38.2f$\n", "TAXABLE TOT.", sum);

        double totalVat = sum * VAT;
        outputStream.printf("%s%42.2f$\n", "VAT" + (VAT * 100) + "%", totalVat);

        double totalSum = sum + totalVat;
        outputStream.printf("%s%45.2f$\n", "TOTAL", totalSum);
    }

    private double printLineOfProduct(Map<String, String> map) {
        outputStream.printf("%s%14s%23s%11s\n", QUANTITY, DESCRIPTION, PRICE, TOTAL_PRICE);
        double sum = 0;
        try {
            for (String key : map.keySet()) {
                // Discount if card present
                if (key.equals("card")) {
                    double discountSumCard = sum * DISCOUNT_CARD;
                    sum -= discountSumCard;
                    outputStream.println("---------------------------------------------------");
                    outputStream.printf("%s%33.2f$\n", "DISCOUNT SUM CARD", discountSumCard);
                    break;
                }

                String quantity = map.get(key);
                int intKey = Integer.parseInt(key);
                String description = productDao.findById(intKey).getName();
                double price = productDao.findById(intKey).getPrice();
                double totalPrice = productDao.findById(intKey).getPrice() * Double.parseDouble(quantity);

                // Discount if more then 5 products
                if (Integer.parseInt(quantity) > 5) {
                    double discount = totalPrice * DISCOUNT;
                    totalPrice -= discount;
                }

                sum += totalPrice;
                outputStream.printf("%2s%12s%25.2f$%10.2f$\n", quantity, description, price, totalPrice);
            }
            return sum;
        } catch (IndexOutOfBoundsException e) {
            outputStream.println("Product Not Found");
        }
        return sum;
    }
}
