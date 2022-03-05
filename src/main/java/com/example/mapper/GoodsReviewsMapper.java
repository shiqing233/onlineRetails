package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsReviewsMapper {

    @Select("select * from v_goods_comments where goodsId = #{id}" )
    List<Map> getGoodsAllReviewsByGoodsId(@Param("id") String goodsId);
}
