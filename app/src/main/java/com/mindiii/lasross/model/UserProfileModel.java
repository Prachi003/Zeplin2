package com.mindiii.lasross.model;

public class UserProfileModel {

    String userDetailHeader;
    String userDetailInfo;

    public UserProfileModel(String userDetailHeader, String userDetailInfo) {
        this.userDetailHeader = userDetailHeader;
        this.userDetailInfo = userDetailInfo;
    }

    public String getUserDetailHeader() {
        return userDetailHeader;
    }

    public String getUserDetailInfo() {
        return userDetailInfo;
    }
}
