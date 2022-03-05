package com.example.controller;

import com.example.service.OrderInfoService;
import com.example.util.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    private OrderInfoService service;

    public OrderInfoService getService() {
        return service;
    }

    public void setService(OrderInfoService service) {
        this.service = service;
    }
    @RequestMapping("/find/all")
    public String selectOrders(String userId){
        return JsonTools.querySuccess("orders",this.service.getAllOrders(userId));
    }
    @RequestMapping("/find/nopay")
    public String selectNoPayOrders(String userId){
        return JsonTools.querySuccess("orders",this.service.getNoPayOrders(userId));
    }
    @RequestMapping("/find/noreceive")
    public String selectNoReceiveOrders(String userId){
        return JsonTools.querySuccess("orders",this.service.getNoReceiveOrders(userId));
    }
    @RequestMapping("/find/nocomment")
    public String selectNoCommentOrders(String userId){
        return JsonTools.querySuccess("orders",this.service.getNoCommentOrders(userId));
    }
}
