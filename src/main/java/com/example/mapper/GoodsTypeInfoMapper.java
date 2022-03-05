package com.example.mapper;

import com.example.entity.GoodsTypeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsTypeInfoMapper {
    @Select("select * from goods_type_info where goodsId = #{goodsId}")
    List<GoodsTypeInfo> selectGoodsTypeByGoodsId(@Param("goodsId")String goodsId);
}
