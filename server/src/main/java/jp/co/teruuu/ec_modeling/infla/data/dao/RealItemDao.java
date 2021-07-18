package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.controller.item.RealItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

public interface RealItemDao {

    @Select("SELECT count(1) FROM item")
    int count();

    @Insert("INSERT INTO item (order_id, product_id, price, number, update_date)\n" +
            "VALUES ( #{orderId}, #{productId}, #{price}, #{number}, #{updateDate})")
    @Options(useGeneratedKeys=true, keyProperty="itemId")
    int insert(RealItem realItem);

    @Update("UPDATE item SET order_id = #{orderId}, product_id = #{productId}, price = #{price}, number = #{number}, update_date = #{updateDate}\n" +
            "WHERE item_id = #{itemId}")
    int update(RealItem realItem);

    @Select({"<script>",
            "SELECT item_id as itemId, order_id as orderId, product_id as productId, price, number, update_date as updateDate ",
            "FROM item",
            "<if test='sortOpt.isPresent()'>order by ${sortOpt.get()}",
            "  <if test='orderOpt.isPresent()'>${orderOpt.get()}</if>",
            "</if>",
            "<if test='limitOpt.isPresent()'> limit ${limitOpt.get()}</if>",
            "<if test='offsetOpt.isPresent()'> offset ${offsetOpt.get()}</if>",
            "</script>"})
    List<RealItem> select(Optional<String> sortOpt, Optional<String> orderOpt, Optional<Integer> offsetOpt, Optional<Integer> limitOpt);

    @Select("SELECT item_id as itemId, order_id as orderId, product_id as productId, price, number, update_date as updateDate\n" +
            "FROM item\n" +
            "WHERE item_id = #{itemId}")
    RealItem findByItemId(int itemId);

    @Delete("DELETE FROM item where item_id = #{item.itemId}")
    int delete(int itemId);
}
