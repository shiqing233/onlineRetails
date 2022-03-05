package com.example.service;

import com.example.entity.UserInfo;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public String getUserIdByPhone(String phone){
        return userMapper.selectUserIdByPhone(phone);
    }


    public Boolean verifyPassword(String phone, String password){
        String userPassword = userMapper.selectPasswordByTel(phone);
        if(userPassword == null){
            return false;
        }
        if(userPassword.equals(password)){
            return true;
        }
        return false;
    }

    public void addUser(String userId, String phone, String userName, String userPassword){
        UserInfo user = new UserInfo();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserPhotoLink("");
        user.setPhone(phone);
        userMapper.insertUser(user);
    }
}
