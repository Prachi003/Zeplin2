package com.mindiii.lasross.model;

public class FooterListModel {

    String itemName;
    String itemPrice;
    String picURL;

    public FooterListModel(String itemName, String itemPrice, String picURL) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.picURL = picURL;
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
