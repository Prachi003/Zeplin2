package com.mindiii.lasross.home.model;

import java.util.List;

public class GetProduct {

    private String status;
    private List<Products> products;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
