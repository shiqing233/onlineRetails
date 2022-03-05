package com.example.entity;

public class ShoppingCart {
    private String cartId;
    private String userId;
    private Integer number;
    private Double price;
    private String type;
    private String goodsDescription;
    private String goodsName;
    private String goodsPhotosLink;
    private Boolean checked = false;

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsPhotosLink(String goodsPhotosLink) {
        this.goodsPhotosLink = goodsPhotosLink;
    }

    public String getCartId() {
        return cartId;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getNumber() {
        return number;
    }

    public Double getPrice() {
        return price;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsPhotosLink() {
        return goodsPhotosLink;
    }

    public String getType() {
        return type;
    }

    public Boolean getChecked() {
        return checked;
    }
}
