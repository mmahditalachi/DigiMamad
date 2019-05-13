package com.digimamad.model;

public class Cart {

    private String title;
    private String username;
    private String image;
    private String details;
    private String color;
    private int discount;
    private int price;
    private int number;
    private int id;

    public Cart(String title, String details, String color, int price, int id,int number,String image,int discount,String username) {
        this.title = title;
        this.username = username;
        this.details = details;
        this.color = color;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.id = id;
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
