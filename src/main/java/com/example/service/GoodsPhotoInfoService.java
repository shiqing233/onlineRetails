package com.example.service;

import com.example.entity.GoodsPhotoInfo;
import com.example.mapper.GoodsPhotoInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsPhotoInfoService {
    public void setMapper(GoodsPhotoInfoMapper mapper) {
        this.mapper = mapper;
    }
    @Autowired
    private GoodsPhotoInfoMapper mapper;
    //按商品id获取图片列表
    public List<GoodsPhotoInfo> getGoodsPhotosByGoodsId(String goodsId){
        List<GoodsPhotoInfo> goods = mapper.getPhotoListByGoodsId(goodsId);
        return goods;
    }
    //插入图片列表
    public void insertPhotoList(List<GoodsPhotoInfo> list){
        for(GoodsPhotoInfo photo : list){
            mapper.insertOneGoodsPhotos(photo);
        }
    }
    //按图片id获取图片
    public GoodsPhotoInfo getGoodsPhotosByPhotoId(String photoId){
        GoodsPhotoInfo photo = mapper.getPhotoListByPhotoId(photoId);
        return photo;
    }
    //按图片id删除图片
    public void deleteGoodsPhotosByPhotoId(String photoId){
        mapper.deletePhotoListByPhotoId(photoId);
    }
}
