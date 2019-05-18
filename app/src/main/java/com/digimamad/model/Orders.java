package com.digimamad.model;

public class Orders {
    private String first_name;
    private String last_name;
    private String username;
    private String title;
    private int id;
    private int product_id;
    private int number;
    private int price;

    public Orders(String first_name, String last_name, String username, String title, int id, int product_id, int number, int price) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.title = title;
        this.id = id;
        this.product_id = product_id;
        this.number = number;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
