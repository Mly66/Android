package cn.nbmly.shop.data.model;

import java.util.List;

public class CreateOrderRequest {
    private List<CartItem> items;
    private String address;

    public CreateOrderRequest(List<CartItem> items, String address) {
        this.items = items;
        this.address = address;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public String getAddress() {
        return address;
    }
}