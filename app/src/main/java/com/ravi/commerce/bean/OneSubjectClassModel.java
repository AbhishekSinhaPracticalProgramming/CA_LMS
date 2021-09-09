package com.ravi.commerce.bean;

public class OneSubjectClassModel {

    public OneSubjectClassModel() {
    }

    @Override
    public String toString() {
        return "OneSubjectClassModel{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    private String name;
    private String price;

    public OneSubjectClassModel(String name, String price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
