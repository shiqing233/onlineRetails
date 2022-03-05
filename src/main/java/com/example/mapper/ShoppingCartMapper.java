package com.example.mapper;

import com.example.entity.ShoppingCartInfo;
import com.example.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShoppingCartMapper {
    @Select("select * from v_cart where userId = #{id}")
    List<ShoppingCart> selectCartsByUserId(@Param("id")String userId);

    @Delete("delete from shopping_cart where cartId = #{id}")
    int deleteCarts(@Param("id")String cartId);

    @Insert("insert into shopping_cart values(#{goods.cartId}, #{goods.goodsTypeId}, #{goods.userId},#{goods.number})")
    void insertGoodsIntoShoppingCart(@Param("goods") ShoppingCartInfo goods);

    @Select("select goodsTypeId, goodsName, type, number, price from v_cart_goods where cartId = #{id}")
    Map selectCartInfosFromGoodsInfoByCartId(@Param("id") String cartId);

    @Delete("delete from shopping_cart where cartId = #{id}")
    void deleteCartInfoByCartId(@Param("id")String cartId);
}
