package cn.nbmly.shop.data.model;

public class Address {
    private long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    public long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }
}