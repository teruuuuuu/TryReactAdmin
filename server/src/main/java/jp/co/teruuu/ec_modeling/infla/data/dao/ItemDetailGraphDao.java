package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.controller.product.graph.ItemDetailGraph;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemDetailGraphDao {

    @Select("select" +
            "  i.item_id as item_id,\n" +
            "  p.product_id as product_id,\n" +
            "  p.name as name,\n" +
            "  i.price as price,\n" +
            "  i.number as number,\n" +
            "  p.price as current_price,\n" +
            "  pi.description as description\n" +
            "from item i \n" +
            "left join product p on i.product_id = p.product_id \n" +
            "left join product_info pi on pi.product_id = p.product_id\n" +
            "where i.order_id = #{orderId}")
    @Results(id = "getItemDetailGraph", value = {
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "name", property = "name"),
            @Result(column = "price", property = "price"),
            @Result(column = "number", property = "number"),
            @Result(column = "current_price", property = "currentPrice")})
    List<ItemDetailGraph> get(String orderId);
}
