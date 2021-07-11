package jp.co.teruuu.ec_modeling.controller.order;

import jp.co.teruuu.ec_modeling.app.app_service.OrderService;
import jp.co.teruuu.ec_modeling.controller.product.dto.ItemDetail;
import jp.co.teruuu.ec_modeling.app.domain.order.model.PaymentType;
import jp.co.teruuu.ec_modeling.controller.order.form.OrderConfirmForm;
import jp.co.teruuu.ec_modeling.controller.order.form.UpdateItemForm;
import jp.co.teruuu.ec_modeling.infla.security.AuthUser;
import jp.co.teruuu.ec_modeling.infla.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
  @Autowired
  OrderService orderService;

  @RequestMapping(value = "updateItem", method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
  public boolean updateItem(@RequestBody UpdateItemForm form, @AuthenticationPrincipal AuthUser user) {
    LoginUser u = user.getUser();
    return orderService.updateItem(u, form.getProductId(), form.getNumber());
  }

  @RequestMapping(value = "confirm", method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
  public boolean confirm(@RequestBody OrderConfirmForm form, @AuthenticationPrincipal AuthUser user) {
    LoginUser u = user.getUser();
    return orderService.confirm(u, PaymentType.byCode(form.getPaymentType()));
  }

  @RequestMapping(value = "curt_info", method = RequestMethod.GET)
  public List<ItemDetail> curntInfo(@AuthenticationPrincipal AuthUser user) {
    LoginUser u = user.getUser();
    return orderService.curtInfo(u);
  }
}
