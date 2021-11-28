package by.java.testTask.service;

import by.java.testTask.model.Product;
import by.java.testTask.model.ReceiptProduct;
import by.java.testTask.repository.ProductDao;

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

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}