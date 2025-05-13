package com.tuapp.model;

import java.util.List;

public class OrderRequest {
    private String email;
    private List<Product> products;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}