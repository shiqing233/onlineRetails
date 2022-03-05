package com.example.service;

import com.example.entity.ShoppingCartInfo;
import com.example.mapper.ShoppingCartMapper;
import com.example.entity.ShoppingCart;
import com.example.util.IdTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    public void setMapper(ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
    }
    public ShoppingCartMapper getMapper() {
        return shoppingCartMapper;
    }
    public List<ShoppingCart> findCarts(String userId){
        return this.shoppingCartMapper.selectCartsByUserId(userId);
    }
    public int deleteCart(String cartId){
        return this.shoppingCartMapper.deleteCarts(cartId);
    }
    public void addGoodsIntoShoppingCart(String userId, String goodsTypeId, Integer number){
        ShoppingCartInfo cart = new ShoppingCartInfo();
        cart.setUserId(userId);
        cart.setCartId(IdTools.getId());
        cart.setTypeId(0);
        cart.setGoodsTypeId(goodsTypeId);
        cart.setNumber(number);
        cart.setAddTime(new Timestamp(System.currentTimeMillis()));
        shoppingCartMapper.insertGoodsIntoShoppingCart(cart);
    }
    public List<Map> getCartInfosFromGoodsInfoByCartIds(String[] cartIds){
        List<Map> list = new LinkedList<>();
        for(Object cartId : cartIds){
            Map map = shoppingCartMapper.selectCartInfosFromGoodsInfoByCartId((String) cartId);
            list.add(map);
        }
        return list;
    }

    public Map getCartInfosFromGoodsInfoByCartId(String cartId){
        return shoppingCartMapper.selectCartInfosFromGoodsInfoByCartId(cartId);
    }


    public void removeCartInfoByCartId(String cartId){
        shoppingCartMapper.deleteCartInfoByCartId(cartId);
    }

}
