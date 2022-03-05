package com.example.mapper;

import com.example.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    @Select("select * from user_info where userId=#{id}")
    UserInfo selectUserById(@Param("id") String userId);
    @Update("update user_info set userPhotoLink=#{u.userPhotoLink}, userName=#{u.userName}, phone=#{u.phone} where userId=#{u.userId}")
    int updateUserInfo(@Param("u") UserInfo user);
    @Select("select * from user_info where userName=#{name}")
    UserInfo selectUserByName(@Param("name") String userName);

    List<UserInfo> getCouldList(@Param("list") List<String> list);

    @Select("select * from user_info where userId=#{id}")
    UserInfo selectByPrimaryKey(@Param("id") String id);


    List<String> getClientList(@Param("list") List<String> list);
}
