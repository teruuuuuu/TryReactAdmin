package jp.co.teruuu.ec_modeling.controller.item;

import jp.co.teruuu.ec_modeling.infla.data.dao.RealItemDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "items")
public class ItemController {

    @Autowired
    RealItemDao realItemDao;

    Map<String, String> columnMap = new HashMap<String, String>() {{
        put("id", "item_id");
        put("itemId", "item_id");
        put("orderId", "order_id");
        put("productId", "product_id");
        put("price", "price");
        put("number", "number");
        put("updateDate", "update_date");
    }};

    @RequestMapping(method = RequestMethod.GET)
    public List<RealItem> get(HttpServletResponse response,
                              @RequestParam(name = "_sort", required = false) Optional<String> sortOpt,
                              @RequestParam (name = "_order", required = false) Optional<String> orderOpt,
                              @RequestParam (name = "_start", required = false) Optional<Integer> startOpt,
                              @RequestParam (name = "_end", required = false) Optional<Integer> endOpt) {
        response.setHeader("X-Total-Count", String.valueOf(realItemDao.count()));
        return realItemDao.select(sortOpt.map(columnMap::get), orderOpt, startOpt, endOpt.map(end -> end - startOpt.orElse(0)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public RealItem post(HttpServletResponse response, @RequestBody ItemForm itemForm) {
        RealItem item = itemForm.toItem();
        realItemDao.insert(item);
        response.setHeader("X-Total-Count", String.valueOf(realItemDao.count()));
        return item;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public RealItem getItem(HttpServletResponse response, @PathVariable(name = "id", required = true) int id) {
        RealItem item = realItemDao.findByItemId(id);
        response.setHeader("X-Total-Count", String.valueOf(realItemDao.count()));
        return item;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public RealItem updateItem(HttpServletResponse response, @RequestBody ItemForm itemForm) {
        RealItem item = itemForm.toItem();
        realItemDao.update(item);
        response.setHeader("X-Total-Count", String.valueOf(realItemDao.count()));
        return item;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public List<RealItem> deleteItem(HttpServletResponse response, @PathVariable(name = "id", required = true) int id) {
        RealItem item = realItemDao.findByItemId(id);
        realItemDao.delete(item.getItemId());
        response.setHeader("X-Total-Count", String.valueOf(realItemDao.count()));
        return Collections.singletonList(item);
    }

    @Data
    @AllArgsConstructor
    static class ItemForm {
        int itemId;
        int orderId;
        int productId;
        int price;
        int number;

        public RealItem toItem() {
            return new RealItem(itemId, orderId, productId, price, number, LocalDateTime.now());
        }
    }

}
