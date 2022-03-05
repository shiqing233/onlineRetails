package com.example.mapper;

import com.example.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select userPassword from user_info where phone = #{phone}")
    String selectPasswordByTel(@Param("phone")String phone);
    @Select("select userId from user_info where phone = #{phone}")
    String selectUserIdByPhone(@Param("phone")String phone);

    @Insert("insert into user_info values(#{user.userId}, #{user.userName}, #{user.userPassword}, " +
            "#{user.userPhotoLink}, #{user.phone})")
    void insertUser(@Param("user") UserInfo user);
}
