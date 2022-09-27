package com.apapp.school;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Seller implements Serializable {
    private String name;
    private final ArrayList<Product> products = new ArrayList<>();
    private final ArrayList<Product> productsSold = new ArrayList<>();
    private final LocalDate dateAdded;

    public Seller(String name) {
        this.name = name;
        this.dateAdded = LocalDate.now();
    }

    public Seller() {
        this.dateAdded = LocalDate.now();
    }

    public LocalDate getDateAdded() {
        return this.dateAdded;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public ArrayList<Product> getProductsSold() {
        return this.productsSold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addProductSold(Product product) {
        this.productsSold.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}
