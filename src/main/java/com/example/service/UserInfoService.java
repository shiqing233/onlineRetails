package com.example.service;

import com.example.entity.UserInfo;
import com.example.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper mapper;

    public void setMapper(UserInfoMapper mapper) {
        this.mapper = mapper;
    }

    public UserInfo getUser(String userId){
        return this.mapper.selectUserById(userId);
    }
    public int uploadUserInfo(UserInfo user){
        return this.mapper.updateUserInfo(user);
    }
}
