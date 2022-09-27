package com.apapp.school;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;

public class Product implements Serializable {
    private String name;
    private String description;
    private final LocalDate date;
    private double price;
    private Seller seller;

    public Product(String product, Seller seller, String description, double price) {
        this.name = product;
        this.description = description;
        this.price = price;
        this.seller = seller;
        this.date = LocalDate.now();
    }

    public Product() {
        this.date = LocalDate.now();
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Seller getSeller() {
        return this.seller;
    }

    public String getPrice() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(this.price);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setName(String product) {
        this.name = product;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}