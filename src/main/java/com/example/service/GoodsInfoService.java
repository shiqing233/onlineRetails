package com.example.service;

import com.example.entity.GoodsTypeInfo;
import com.example.mapper.GoodsInfoMapper;
import com.example.mapper.GoodsPhotoInfoMapper;
import com.example.mapper.GoodsReviewsMapper;
import com.example.mapper.GoodsTypeInfoMapper;
import com.example.entity.GoodsInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class GoodsInfoService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Autowired
    private GoodsTypeInfoMapper goodsTypeInfoMapper;

    @Autowired
    private GoodsReviewsMapper goodsReviewsMapper;

    @Autowired
    private GoodsPhotoInfoMapper goodsPhotoInfoMapper;


    public List<GoodsInfo> selectGoods(String input){
        return this.goodsInfoMapper.selectGoodsByInput(input);
    }

    public List<String> getAllGoodsName() {
        return this.goodsInfoMapper.selectAllGoodsName();
    }
    public Map getGoodsInfoById(String goodsId){
        GoodsInfo info = goodsInfoMapper.selectGoodsInfoById(goodsId);
        if(info == null){
            // TODO
            return null;
        }
        String address = goodsInfoMapper.selectGoodsAddressByGoodsId(goodsId);
        Map map = new LinkedHashMap();
        map.put("goodsId", info.getGoodsId());
        map.put("goodsName", info.getGoodsName());
        map.put("isSale", info.getIsSale());
        map.put("goodsDescription", info.getGoodsDescription());
        map.put("goodsPhotosLink", info.getGoodsPhotosLink());
        map.put("address", address);
        return map;
    }

    public List<Map> getGoodInfoTypeByGoodsId(String goodsId) {
        List<GoodsTypeInfo> list = goodsTypeInfoMapper.selectGoodsTypeByGoodsId(goodsId);
        List<Map> ret = new LinkedList<>();
        for (GoodsTypeInfo info : list) {
            Map map = new LinkedHashMap();
            map.put("goodsTypeId", info.getGoodsTypeId());
            map.put("type", info.getType());
            map.put("stock", info.getStock());
            map.put("price", info.getPrice());
            ret.add(map);
        }
        return ret;
    }

    public Map getNewComments(String goodsId, int pageNum, int rowNum){
        PageHelper.startPage(pageNum, rowNum);
        List<Map> list = goodsReviewsMapper.getGoodsAllReviewsByGoodsId(goodsId);

        Map map = new LinkedHashMap();
        map.put("newComments", new PageInfo<>(list));
        return map;
    }

    public Map getGoodsPhotosLink(String goodsId){
        Map map = new LinkedHashMap();
        map.put("links", goodsPhotoInfoMapper.selectGoodsPhotosLink(goodsId));
        return map;
    }

    public Map getGoodsInfoByOrderId(String orderId){
        return goodsInfoMapper.selectGoodsInfoByOrderId(orderId);
    }

}
