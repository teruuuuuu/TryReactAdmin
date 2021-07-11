package jp.co.teruuu.ec_modeling.controller.product.graph;

import lombok.Data;

@Data
public class ItemDetailGraph {
    int itemId;
    int productId;
    String name;
    int price;
    int number;
    int currentPrice;
    String description;
}
