package com.example.entity;

import java.util.List;

public class UserInfo {
    private String userId;
    private String userName;
    private String userPassword;
    private String userPhotoLink;
    private String phone;
    private List<AddressInfo> adds;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserPhotoLink(String userPhotoLink) {
        this.userPhotoLink = userPhotoLink;
    }

    public String getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserPhotoLink() {
        return userPhotoLink;
    }

    public void setAdds(List<AddressInfo> adds) {
        this.adds = adds;
    }

    public List<AddressInfo> getAdds() {
        return adds;
    }
}
