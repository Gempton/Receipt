import model.Product;
import repository.ProductDao;
import service.DiscountService;
import service.ProductService;

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

    private ProductDao productDao;
    private PrintStream outputStream;
    private DiscountService discountService = new DiscountService(false);
    private ProductService productService = new ProductService();

    public ReceiptManager(ProductDao productDao, PrintStream outputStream) {
        this.productDao = productDao;
        this.outputStream = outputStream;
    }

    // отсавить
    public void printInformation() {
        outputStream.printf("%32s%n", "CASH RECEIPT");
        outputStream.printf("%33s%n", "SUPERMARKET 123");
        outputStream.printf("%39s%n", "12, MILKYWAY Galaxy/ Earth");
        outputStream.printf("%34s%n", "Tel: 123-456-7890");
        outputStream.printf("%s", "CASHIER: N1520");
    }

    public void printHeader() {
        LocalDate localDate = LocalDate.now();
        String localDateString = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        outputStream.printf("%37s%n", localDateString);
        LocalTime localTime = LocalTime.now();
        String localTimeString = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        outputStream.printf("%51s%n", localTimeString);
        outputStream.println(LINE);
    }

    private Map<String, String> processInput(String[] args) {
        String card = "card";
        Map<String, String> map = new HashMap<>();

        for (String arg : args) {
            String[] str = arg.split("-");
            map.put(str[0], str[1]);
        }

        if (map.containsKey(card)) {
            discountService.setHasCard(true);
            map.remove(card);
        }
        return map;
    }

    public void printProducts(String[] args) {
        Map<String, String> map = processInput(args);
        outputStream.printf("%s%14s%23s%11s%n", QUANTITY, DESCRIPTION, PRICE, TOTAL_PRICE);

        for (String key : map.keySet()) {
            Product product = productDao.findById(Integer.parseInt(key));
            List<Object> outputList = productService.calculatePrice(product, Integer.parseInt(map.get(key)));

            Integer quantity = (Integer) outputList.get(1);
            String description = product.getName();
            Double price = product.getPrice();
            Double totalPrice = (Double) outputList.get(2);
            outputStream.printf("%2s%12s%25.2f$%10.2f$%n", quantity, description, price, totalPrice);
        }
        outputStream.println(LINE);
    }

    public void printTotal() {
        double sumOfTotalPrice = productService.getSumOfTotalPrice();
        sumOfTotalPrice = discountService.calculateDiscount(sumOfTotalPrice);
        outputStream.printf("%s%45.2f$%n", TOTAL_PRICE, sumOfTotalPrice);
    }
}
