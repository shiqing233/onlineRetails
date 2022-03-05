package com.example.entity;

public class GoodsInfo {
    private String goodsId;
    private String goodsName;
    private String goodsDescription;
    private String goodsPhotosLink;
    private Integer isSale;
    private String goodsLabels;
    private String shopId;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String description) {
        this.goodsDescription = description;
    }

    public String getGoodsPhotosLink() {
        return goodsPhotosLink;
    }

    public void setGoodsPhotosLink(String goodsPhotosLink) {
        this.goodsPhotosLink = goodsPhotosLink;
    }

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }

    public String getGoodsLabels() {
        return goodsLabels;
    }

    public void setGoodsLabels(String goodsLabels) {
        this.goodsLabels = goodsLabels;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}