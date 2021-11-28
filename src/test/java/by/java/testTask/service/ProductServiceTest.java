package by.java.testTask.service;

import by.java.testTask.model.Product;
import by.java.testTask.model.ReceiptProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import by.java.testTask.repository.ProductDao;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    private ProductService productService = new ProductService();

    @BeforeEach
    public void init() {
        productService.setProductDao(productDao);
    }

    @Test
    void shouldCalculateTotalPriceWithNoDiscount() {
        Product product = new Product();
        product.setPrice(67.88);
        Integer productId = 1;
        Integer count = 4;

        when(productDao.findById(productId)).thenReturn(product);

        List<ReceiptProduct> receiptProducts = productService.calculatePrice(Map.of(productId, count));

        Assertions.assertEquals(1, receiptProducts.size());
        Assertions.assertEquals(product, receiptProducts.get(0).getProduct());
        Assertions.assertEquals(count, receiptProducts.get(0).getCount());

        Double totalPrice = product.getPrice() * count;
        Assertions.assertEquals(totalPrice, receiptProducts.get(0).getTotalPrice());
    }

    @Test
    void shouldCalculateTotalPriceWithDiscount() {
        Product product = new Product();
        product.setPrice(50.0);
        Integer productId = 3;
        Integer count = 8;

        when(productDao.findById(productId)).thenReturn(product);

        List<ReceiptProduct> receiptProducts = productService.calculatePrice(Map.of(productId, count));

        Assertions.assertEquals(1, receiptProducts.size());
        Assertions.assertEquals(product, receiptProducts.get(0).getProduct());
        Assertions.assertEquals(count, receiptProducts.get(0).getCount());

        Double totalPrice = product.getPrice() * count;
        double discount = 0.1;
        totalPrice = totalPrice - (totalPrice * discount);
        Assertions.assertEquals(totalPrice, receiptProducts.get(0).getTotalPrice());
    }
}
