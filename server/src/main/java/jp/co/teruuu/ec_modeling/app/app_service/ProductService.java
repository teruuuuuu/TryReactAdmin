package jp.co.teruuu.ec_modeling.app.app_service;

import jp.co.teruuu.ec_modeling.controller.product.dto.ProductDto;
import jp.co.teruuu.ec_modeling.app.app_service.repository.ProductDtoRepository;
import jp.co.teruuu.ec_modeling.infla.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  @Autowired
  SessionService sessionService;

  @Autowired
  ProductDtoRepository productDtoRepository;

  public List<ProductDto> searchByName(String name, int limit, int offset){
    return productDtoRepository.searchByName(name, limit, offset);
  }

}
