package com.example.service;

import com.example.mapper.AddressInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class QueryAddressService {
    @Autowired
    private AddressInfoMapper addressInfoMapper;

    public Map getAddressByUserId(String userId){
        Map map = new LinkedHashMap();
        map.put("addressInfos" ,addressInfoMapper.selectAddressByUserId(userId));
        return map;
    }
}
