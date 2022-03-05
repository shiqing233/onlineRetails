package com.example.mapper;

import com.example.entity.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsInfoMapper {
     List<GoodsInfo> selectGoodsByInput(@Param("input") String input);

     @Select("select goodsName from goods_info")
     List<String> selectAllGoodsName();

     @Select("select * from goods_info where goodsId = #{id}")
     GoodsInfo selectGoodsInfoById(@Param("id")String goodsId);

     @Select("select shopAddress from v_goods_address where goodsId = #{id}")
     String selectGoodsAddressByGoodsId(@Param("id")String goodsId);

     @Select("select goodsId, goodsName, type, userId from v_order_goods_info where orderId = #{id}")
     Map selectGoodsInfoByOrderId(@Param("id") String orderId);
}
