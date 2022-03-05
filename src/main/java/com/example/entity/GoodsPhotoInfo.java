package com.example.entity;

public class GoodsPhotoInfo {
    private String goodsId;
    private String goodsPhotoId;
    private String goodsPhotoLink;

    public GoodsPhotoInfo() {
    }

    public String getGoodsPhotoLink() {
        return goodsPhotoLink;
    }

    public void setGoodsPhotoLink(String goodsPhotoLink) {
        this.goodsPhotoLink = goodsPhotoLink;
    }

    public String getGoodsPhotoId() {
        return goodsPhotoId;
    }

    public void setGoodsPhotoId(String goodsPhotoId) {
        this.goodsPhotoId = goodsPhotoId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
