package jp.co.teruuu.ec_modeling.controller.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealItem {
  int itemId;
  int orderId;
  int productId;
  int price;
  int number;
  LocalDateTime updateDate;



}
