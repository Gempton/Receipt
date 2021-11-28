package by.java.testTask;

import by.java.testTask.model.ReceiptProduct;
import by.java.testTask.service.ProductService;

import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        ReceiptManager receiptManager = new ReceiptManager(System.out);
        Map<Integer, Integer> productIdToCountMap = receiptManager.readProducts(args);
        Double discount = receiptManager.getCardDiscount(args);

        ProductService productService = new ProductService();
        List<ReceiptProduct> receiptProducts = productService.calculatePrice(productIdToCountMap);
        double totalPrice = receiptManager.calculateTotal(receiptProducts, discount);
        receiptManager.printReceipt(receiptProducts, totalPrice);
    }
}
