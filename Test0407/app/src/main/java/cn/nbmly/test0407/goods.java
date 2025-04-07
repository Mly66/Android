package cn.nbmly.test0407;

public class goods {
    @Override
    public String toString() {
        return "goods{" +
                "imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public goods(String name, int imgUrl, float price) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    private int imgUrl;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    private float price;

}
