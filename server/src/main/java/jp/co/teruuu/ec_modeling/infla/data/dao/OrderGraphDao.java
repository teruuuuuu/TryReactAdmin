package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.controller.product.graph.OrderGraph;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderGraphDao {

    @Select("SELECT ORDER_ID, ORDER_STATUS, USER_ID FROM ORDER_T Where ORDER_ID = #{orderId}")
    @Results(id = "getOrderGraph", value = {
            @Result(column = "ORDER_ID", property = "orderId"),
            @Result(column = "ORDER_STATUS", property = "orderStatus"),
            @Result(column = "USER_ID", property = "userId")})
    OrderGraph get(String orderId);
}
