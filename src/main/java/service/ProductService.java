package service;

import model.Product;
import model.ReceiptProduct;
import repository.ProductDao;

import java.util.*;

public class ProductService {
    private static final double DISCOUNT = 0.1;
    private ProductDao productDao = new ProductDao();

    public List<ReceiptProduct> calculatePrice(Map<Integer, Integer> productIdToCountMap) {

        List<ReceiptProduct> receiptProducts = new ArrayList<>();

        for (Integer key : productIdToCountMap.keySet()) {
            Product product = productDao.findById(key);
            Integer count = productIdToCountMap.get(key);
            double totalPrice = product.getPrice() * count;

            // Discount if more then 5 products
            if (count > 5) {
                totalPrice = totalPrice - (totalPrice * DISCOUNT);
            }

            receiptProducts.add(new ReceiptProduct(product, count, totalPrice));
        }
        return receiptProducts;
    }
}