package jp.co.teruuu.ec_modeling.controller.product.graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderGraph {
    int orderId;
    int userId;
    int orderStatus;
    List<ItemDetailGraph> items = new ArrayList<>();
}
