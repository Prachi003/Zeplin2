package com.mindiii.lasross.model;

public class FilterSizeModel {

    private String Itemsize;
    private String totalItem;
    private boolean isTicked;

    public FilterSizeModel(String itemsize, String totalItem) {
        Itemsize = itemsize;
        this.totalItem = totalItem;
        isTicked = false;
    }

    public boolean isTicked() {
        return isTicked;
    }

    public void setTicked(boolean ticked) {
        isTicked = ticked;
    }

    public String getItemsize() {
        return Itemsize;
    }

    public String getTotalItem() {
        return totalItem;
    }
}