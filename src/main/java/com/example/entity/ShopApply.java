package com.example.entity;

import java.sql.Timestamp;

public class ShopApply {
    private String shopApplyId;
    private String userId;
    private String applyInfoLink;
    private Timestamp applyTime;
    private Timestamp replyTime;
    private Integer agreed;

    public ShopApply() {
    }

    public String getShopApplyId() {
        return shopApplyId;
    }

    public void setShopApplyId(String shopApplyId) {
        this.shopApplyId = shopApplyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplyInfoLink() {
        return applyInfoLink;
    }

    public void setApplyInfoLink(String applyInfoLink) {
        this.applyInfoLink = applyInfoLink;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getAgreed() {
        return agreed;
    }

    public void setAgreed(Integer agreed) {
        this.agreed = agreed;
    }
}
