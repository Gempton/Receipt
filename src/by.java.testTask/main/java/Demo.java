import model.ReceiptProduct;
import service.ProductService;

import java.util.List;
import java.util.Map;

public class Demo {
    //mvn exec:java -Dexec.mainClass=Demo -Dexec.args="1-2 6-7 3-3 4-1 6-8 card-1234"
    //mvn exec:java -Dexec.mainClass=Demo -Dexec.args="1-2 6-7 3-3 7-8 8-11"
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
