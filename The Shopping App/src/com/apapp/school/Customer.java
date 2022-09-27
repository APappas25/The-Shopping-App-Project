package com.apapp.school;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Customer implements Serializable {
    private String name;
    private final ArrayList<Product> productsPurchased = new ArrayList<>();
    private final LocalDate date;

    public Customer(String name) {
        this.name = name;
        this.date = LocalDate.now();
    }

    public Customer() {
        this.date = LocalDate.now();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Product> getProductsPurchased() {
        return this.productsPurchased;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProductPurchased(Product productPurchased) {
        this.productsPurchased.add(productPurchased);
    }
}
