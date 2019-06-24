package com.mindiii.lasross.model;

public class MyOrdersModel {

    String itemNameVariety;
    String itemName;
    String itemDeliverDate;

    public MyOrdersModel(String itemNameVariety, String itemName, String itemDeliverDate) {
        this.itemNameVariety = itemNameVariety;
        this.itemName = itemName;
        this.itemDeliverDate = itemDeliverDate;
    }

    public String getItemNameVariety() {
        return itemNameVariety;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDeliverDate() {
        return itemDeliverDate;
    }
}