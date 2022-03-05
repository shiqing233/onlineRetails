package com.example.mapper;

import com.example.entity.GoodsPhotoInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsPhotoInfoMapper {

    @Select("select goodsPhotoLink from goods_photo_info where goodsId = #{goodsId}")
    List<String> selectGoodsPhotosLink(@Param("goodsId")String goodsId);
    //按商品id查找图片
    @Select("select * from goods_photo_info where goodsId=#{id}")
    List<GoodsPhotoInfo> getPhotoListByGoodsId(@Param("id") String goodsId);
    //按商品id删除图片
    @Delete("delete from goods_photo_info where goodsId=#{id}")
    int deletePhotoListByGoodsId(@Param("id") String id);
    //按图片id查找图片
    @Select("select * from goods_photo_info where goodsPhotoId=#{id}")
    GoodsPhotoInfo getPhotoListByPhotoId(@Param("id") String photoId);
    //按图片id删除图片
    @Delete("delete from goods_photo_info where goodsPhotoId=#{id}")
    int deletePhotoListByPhotoId(@Param("id") String photoId);
    //插入图片
    @Insert("insert into goods_photo_info values(#{photo.goodsId},#{photo.goodsPhotoId},#{photo.goodsPhotoLink})")
    int insertOneGoodsPhotos(@Param("photo") GoodsPhotoInfo goodsPhoto);
}