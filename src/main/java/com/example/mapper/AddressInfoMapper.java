package com.example.mapper;

import com.example.entity.AddressInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressInfoMapper {
    @Select("select * from address_info where userId = #{id}")
    List<AddressInfo> selectAddressByUserId(@Param("id")String userId);

    @Insert("insert into address_info values (null, #{a.userId},#{a.address},#{a.flag})")
    void insertAddress(@Param("a")AddressInfo adds);

    @Delete("delete from address_info where addressId = #{id}")
    int deleteAddress(@Param("id")String addressId);

    @Update("update address_info set flag=#{tag} where addressId=#{id}")
    int updateAddressByAddressId(@Param("tag")boolean tag ,@Param("id")String addressId);

    @Update("update address_info set flag=#{newTag} where flag=#{tag}")
    int updateDefaultAddress(@Param("newTag")boolean newTag,@Param("tag")boolean tag);
}
