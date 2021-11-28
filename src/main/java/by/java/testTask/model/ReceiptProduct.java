package by.java.testTask.model;

public class ReceiptProduct {

    private Product product;
    private Integer count;
    private Double totalPrice;

    public ReceiptProduct(Product product, Integer count, Double totalPrice) {
        this.product = product;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public ReceiptProduct() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
