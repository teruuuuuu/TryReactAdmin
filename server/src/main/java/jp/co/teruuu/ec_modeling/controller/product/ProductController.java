package jp.co.teruuu.ec_modeling.controller.product;

import jp.co.teruuu.ec_modeling.app.app_service.ProductService;
import jp.co.teruuu.ec_modeling.controller.product.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

  @Autowired
  ProductService productService;

  @RequestMapping(value = "search", method = RequestMethod.GET)
  public List<ProductDto> search(@PathVariable(name = "name", required = false) Optional<String> name,
                                 @PathVariable(name = "limit", required = false) Optional<Integer> limit,
                                 @PathVariable(name = "offset", required = false) Optional<Integer> offset) throws Exception{
    return productService.searchByName(name.orElse(""), limit.orElse(20), offset.orElse(0));
  }
}
