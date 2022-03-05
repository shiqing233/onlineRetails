package com.example.controller;

import com.example.service.GoodsInfoService;
import com.example.util.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsInfoController {
    @Autowired
    private GoodsInfoService service;

    public GoodsInfoService getService() {
        return service;
    }

    public void setService(GoodsInfoService service) {
        this.service = service;
    }
    @RequestMapping("/search")
    public String selectGoods(String input){
        return JsonTools.querySuccess("goods",this.service.selectGoods(input));
    }

    @RequestMapping("/name")
    public String getGoodsName(){
        return JsonTools.querySuccess("name",this.service.getAllGoodsName());
    }
}
