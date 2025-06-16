package cn.nbmly.shop.data.model;

public class AddToCartRequest {
    private long productId;
    private int quantity;

    public AddToCartRequest(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}