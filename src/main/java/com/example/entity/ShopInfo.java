package com.example.entity;

public class ShopInfo {
    private String shopId;
    private String userId;
    private String shopName;
    private String shopDescription;
    private String shopAddress;
    private String shopPhotoLink;

    public ShopInfo() {
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopPhotoLink() {
        return shopPhotoLink;
    }

    public void setShopPhotoLink(String shopPhotoLink) {
        this.shopPhotoLink = shopPhotoLink;
    }
}
