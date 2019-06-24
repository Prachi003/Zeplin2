package com.mindiii.lasross.model;

public class MyCartModel {

    String itemNameVariety;
    String itemName;
    String size;
    String itemPrice;

    public MyCartModel(String itemNameVariety, String itemName, String size, String itemPrice) {
        this.itemNameVariety = itemNameVariety;
        this.itemName = itemName;
        this.size = size;
        this.itemPrice = itemPrice;
    }

    public String getItemNameVariety() {
        return itemNameVariety;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSize() {
        return size;
    }

    public String getItemPrice() {
        return itemPrice;
    }
}
