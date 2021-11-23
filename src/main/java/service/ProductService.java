package service;

import model.Product;

import java.util.*;

public class ProductService {
    private static final double DISCOUNT = 0.1;
    private double sumOfTotalPrice;

    public List<Object> calculatePrice(Product product, Integer count) {
        double totalPrice = product.getPrice() * count;

        // Discount if more then 5 products
        if (count > 5) {
            totalPrice -= (totalPrice * DISCOUNT);
        }
        sumOfTotalPrice += totalPrice;

        return Arrays.asList(product, count, totalPrice, sumOfTotalPrice);
    }

    public Double getSumOfTotalPrice() {
        return sumOfTotalPrice;
    }

}
