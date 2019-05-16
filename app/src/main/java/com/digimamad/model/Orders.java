package com.digimamad.model;

public class Orders {
    private String first_name;
    private String last_name;
    private String useername;
    private int id;
    private int number;
    private int price;

    public Orders(String first_name, String last_name, String useername, int id, int number, int price) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.useername = useername;
        this.id = id;
        this.number = number;
        this.price = price;
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

    public String getUseername() {
        return useername;
    }

    public void setUseername(String useername) {
        this.useername = useername;
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
