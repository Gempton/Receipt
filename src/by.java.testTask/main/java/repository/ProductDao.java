package repository;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    List<Product> listOfProduct = new ArrayList<>();

    public ProductDao() {
        this.listOfProduct.add(new Product(1, "Product1", 4.5));
        this.listOfProduct.add(new Product(2, "Product2", 100.0));
        this.listOfProduct.add(new Product(3, "Product3", 2.5));
        this.listOfProduct.add(new Product(4, "Product4", 1.5));
        this.listOfProduct.add(new Product(5, "Product5", 65.4));
        this.listOfProduct.add(new Product(6, "Product6", 8.3));
        this.listOfProduct.add(new Product(7, "Product7", 2.1));
        this.listOfProduct.add(new Product(8, "Product8", 3.7));
        this.listOfProduct.add(new Product(9, "Product9", 5.1));
        this.listOfProduct.add(new Product(10, "Product10", 100.63));
    }

    public Product findById(Integer id) {
        return listOfProduct.get(id);
    }

    public List<Product> findAll() {
        return listOfProduct;
    }

}
