package com.example.mapper;

import com.example.entity.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderInfoMapper {
    @Select("select * from v_order where userId = #{id}")
    List<Map> selectAllOrders(@Param("id") String userId);

    List<OrderInfo> selectNoPayOrders(@Param("id") String userId);

    List<OrderInfo> selectNoReceiveOrders(@Param("id") String userId);

    List<OrderInfo> selectNoCommentOrders(@Param("id") String userId);

    @Insert("insert into order_info values(#{order.orderId}, #{order.goodsTypeId}, #{order.userId}, #{order.number}, " +
            "#{order.startTime}, null, null, null, null, null)")
    void insertOrder(@Param("order")OrderInfo order);

    @Update("update order_info set payTime = #{payTime} where orderId = #{orderId}")
    void updateOrderAddPayTime(@Param("orderId")String orderId, @Param("payTime") Timestamp payTime);

    @Update("update order_info set commentTime = #{commentTime} where orderId = #{orderId}")
    void updateOrderAddCommentTime(@Param("orderId")String orderId, @Param("commentTime")Timestamp commentTime);


}
