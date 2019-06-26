package com.mindiii.lasross.model;

public class HeaderListModel1 {

    String itemNameVariety;
    String itemName;
    String itemPrice;
    String picURL;
    String id;

    public HeaderListModel1(String itemNameVariety, String itemName, String itemPrice, String picURL, String id) {
        this.itemNameVariety = itemNameVariety;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.picURL = picURL;
        this.id = id;
    }

    public String getItemNameVariety() {
        return itemNameVariety;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getPicURL() {
        return picURL;
    }

    public String getId() {
        return id;
    }
}
