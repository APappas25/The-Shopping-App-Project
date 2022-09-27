package com.apapp.school;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {
    private final Product product;
    private final Customer customer;
    private final LocalDate date;

    public Transaction(Product product, Customer customer) {
        this.customer = customer;
        this.product = product;
        this.date = LocalDate.now();
    }

    public String getProductName() {
        return this.product.getName();
    }

    public String getCustomerName() {
        return this.customer.getName();
    }

    public String getAmount() {
        return this.product.getPrice();
    }

    public LocalDate getDate() {
        return this.date;
    }
}
