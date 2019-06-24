package com.mindiii.lasross.model;

public class MyAddressModel {

    String userName;
    String userAddress;
    String pinCode;
    String mobile;

    public MyAddressModel(String userName, String userAddress, String pinCode, String mobile) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.pinCode = pinCode;
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getMobile() {
        return mobile;
    }
}
