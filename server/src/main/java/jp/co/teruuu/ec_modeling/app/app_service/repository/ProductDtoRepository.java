package jp.co.teruuu.ec_modeling.app.app_service.repository;

import jp.co.teruuu.ec_modeling.controller.product.dto.ProductDto;
import jp.co.teruuu.ec_modeling.infla.data.dao.ProductDtoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDtoRepository {
  @Autowired
  ProductDtoDao productDtoDao;

  public List<ProductDto> searchByName(String name, int limit, int offset){
    return productDtoDao.searchByName("%" + name + "%", limit, offset);
  }

}
