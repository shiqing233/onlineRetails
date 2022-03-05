package com.example.service;

import com.example.entity.OrderInfo;
import com.example.mapper.OrderInfoMapper;
import com.example.util.IdTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public List<Map> getAllOrders(String userId){
        return this.orderInfoMapper.selectAllOrders(userId);
    }

    public List<OrderInfo> getNoPayOrders(String userId){
        return this.orderInfoMapper.selectNoPayOrders(userId);
    }

    public List<OrderInfo> getNoReceiveOrders(String userId){
        return this.orderInfoMapper.selectNoReceiveOrders(userId);
    }

    public List<OrderInfo> getNoCommentOrders(String userId){
        return this.orderInfoMapper.selectNoCommentOrders(userId);
    }

    public void addOrder(String userId, String goodsTypeId, Integer number){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(IdTools.getId());
        orderInfo.setUserId(userId);
        orderInfo.setGoodsTypeId(goodsTypeId);
        orderInfo.setNumber(number);
        orderInfo.setStartTime(new Timestamp(System.currentTimeMillis()));
        orderInfoMapper.insertOrder(orderInfo);
    }

    public void addPayTime(String orderId){
        orderInfoMapper.updateOrderAddPayTime(orderId, new Timestamp(System.currentTimeMillis()));
    }

    public void addCommentTime(String orderId, Timestamp time){
        orderInfoMapper.updateOrderAddCommentTime(orderId, time);
    }

    public void addOrderAndPayTime(String userId, String goodsTypeId, Integer number){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(IdTools.getId());
        orderInfo.setUserId(userId);
        orderInfo.setGoodsTypeId(goodsTypeId);
        orderInfo.setNumber(number);
        orderInfo.setStartTime(new Timestamp(System.currentTimeMillis()));
        orderInfo.setPayTime(orderInfo.getStartTime());
        orderInfoMapper.insertOrder(orderInfo);
    }

}
