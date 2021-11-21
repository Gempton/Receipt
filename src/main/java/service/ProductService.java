package service;

import model.Product;

import java.util.List;

public interface ProductService {

    Product findById(Integer id);

    List<Product> findAll();

    Product addProduct(Product product);

    boolean removeProduct(Product product);
}
