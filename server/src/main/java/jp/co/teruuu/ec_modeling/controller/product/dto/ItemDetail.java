package jp.co.teruuu.ec_modeling.controller.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ItemDetail {
  int itemId;
  String name;
  int price;
  int number;
  int currentPrice;
  String description;
  LocalDateTime updateDate;
}
