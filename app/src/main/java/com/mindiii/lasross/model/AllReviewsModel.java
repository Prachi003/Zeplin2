package com.mindiii.lasross.model;

public class AllReviewsModel {

    String userName;
    String date;
    String userComment;

    public AllReviewsModel(String userName, String date, String userComment) {
        this.userName = userName;
        this.date = date;
        this.userComment = userComment;
    }

    public String getUserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }

    public String getUserComment() {
        return userComment;
    }
}
