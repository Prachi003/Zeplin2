package com.mindiii.lasross.model;

public class HeaderListModel1 {

    String itemNameVariety;
    String itemName;
    String itemPrice;
    String picURL;

    public HeaderListModel1(String itemNameVariety, String itemName, String itemPrice, String picURL) {
        this.itemNameVariety = itemNameVariety;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.picURL = picURL;
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
}
