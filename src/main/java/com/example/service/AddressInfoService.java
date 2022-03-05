package com.example.service;

import com.example.entity.AddressInfo;
import com.example.mapper.AddressInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressInfoService {
    @Autowired
    private AddressInfoMapper mapper;

    public void addAdd(String userId, String add){
        AddressInfo adds = new AddressInfo();
        adds.setAddress(add);
        adds.setUserId(userId);
        adds.setFlag(false);
        this.mapper.insertAddress(adds);
    }

    public List<AddressInfo> findAdds(String userId){
        return this.mapper.selectAddressByUserId(userId);
    }

    public int delAdds(String addressId){
        return this.mapper.deleteAddress(addressId);
    }

    public void updateDefault(String addressId){
        this.mapper.updateDefaultAddress(false,true);
        this.mapper.updateAddressByAddressId(true,addressId);
    }
}
