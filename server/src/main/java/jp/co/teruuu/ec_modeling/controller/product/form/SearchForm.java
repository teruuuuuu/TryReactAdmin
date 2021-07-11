package jp.co.teruuu.ec_modeling.controller.product.form;

import lombok.Data;

@Data
public class SearchForm {
  String name;
  int limit;
  int offset;
}
