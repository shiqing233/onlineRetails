package com.example.controller;

import com.example.service.ShoppingCartService;
import com.example.util.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService service;

    public void setService(ShoppingCartService service) {
        this.service = service;
    }

    public ShoppingCartService getService() {
        return service;
    }

    @RequestMapping("/find")
    public String findAllCarts(String userId){
        return JsonTools.querySuccess("carts",this.service.findCarts(userId));
    }
    @RequestMapping("/del")
    public String delCarts(String[] cartIds){
        int total = 0;
        for (String cartId: cartIds){
            if(this.service.deleteCart(cartId)==1){
                total ++ ;
            }
            else return JsonTools.error(JsonTools.DEL_ERROR);
        }
        return JsonTools.querySuccess("total",total);
    }
}
