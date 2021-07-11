package jp.co.teruuu.ec_modeling.controller.product.dto;

import lombok.Data;

@Data
public class ProductDto {
  int productId;
  String name;
  int price;
  String description;
}
