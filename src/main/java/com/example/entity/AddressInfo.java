package com.example.entity;

public class AddressInfo {
    private String addressId;
    private String userId;
    private String address;
    private Boolean flag;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }
}
