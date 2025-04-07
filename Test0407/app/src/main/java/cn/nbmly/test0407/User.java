package cn.nbmly.test0407;

public class User {
    private String name;
    private int imgResId;

    public User(String name, int imgResId) {
        this.name = name;
        this.imgResId = imgResId;
    }

    public String getName() {
        return name;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    @Override
    public String toString() {
        return "User{" +
                "imgResId=" + imgResId +
                ", name='" + name + '\'' +
                '}';
    }
}
