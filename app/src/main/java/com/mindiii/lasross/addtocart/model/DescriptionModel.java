package com.mindiii.lasross.addtocart.model;

public class DescriptionModel {

    String header;
    String detail;

    public DescriptionModel(String header, String detail) {
        this.header = header;
        this.detail = detail;
    }

    public String getHeader() {
        return header;
    }

    public String getDetail() {
        return detail;
    }

    public static class HeaderListModel {

        String itemNameVariety;
        String itemName;
        String itemPrice;

        public HeaderListModel(String itemNameVariety, String itemName, String itemPrice) {
            this.itemNameVariety = itemNameVariety;
            this.itemName = itemName;
            this.itemPrice = itemPrice;
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
    }
}
