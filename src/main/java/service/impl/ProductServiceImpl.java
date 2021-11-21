package service.impl;

import model.Product;
import repository.ProductDao;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao;

    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public boolean removeProduct(Product product) {
        return productDao.removeProduct(product);
    }
}
