package cn.nbmly.shop.data.model;

public class CartItem {
    private long id;
    private Product product;
    private int quantity;

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}