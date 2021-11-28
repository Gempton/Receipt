package by.java.testTask.repository;

import by.java.testTask.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ProductDaoTest {

    private ProductDao productDao = new ProductDao();

    @Test
    void findAllTest() {
        List<Product> products = productDao.findAll();

        Assertions.assertEquals(10, products.size());
    }

    @Test
    void shouldFindByIdProduct() {
        Product testProduct = new Product(3, "Product3", 2.5);
        Product product = productDao.findById(2);

        Assertions.assertEquals(testProduct, product);
    }
}
