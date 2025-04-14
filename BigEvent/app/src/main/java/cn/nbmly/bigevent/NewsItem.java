package cn.nbmly.bigevent;

import java.util.ArrayList;
import java.util.List;

public class NewsItem {
    private String title;
    private String userName;
    private int commentCount;
    private String publishTime;
    private List<String> imageUrls = new ArrayList<>();

    public NewsItem(String title, String userName, int commentCount, String publishTime) {
        this.title = title;
        this.userName = userName;
        this.commentCount = commentCount;
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void addImageUrl(String imageUrl) {
        this.imageUrls.add(imageUrl);
    }

    public int getImageCount() {
        return imageUrls.size();
    }
}