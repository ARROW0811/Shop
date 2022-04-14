package com.example.shop.bean;

public class GoodsBean {
    private String name;
    private int imageId;
    private String price;
    private String describe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public GoodsBean(String name, int imageId, String price) {
        this.name = name;
        this.imageId = imageId;
        this.price = price;
    }

    public GoodsBean(String name, int imageId, String price, String describe) {
        this.name = name;
        this.imageId = imageId;
        this.price = price;
        this.describe = describe;
    }

    public GoodsBean(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
}
