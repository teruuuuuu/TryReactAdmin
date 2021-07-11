package jp.co.teruuu.ec_modeling.controller.product.graph;

import graphql.kickstart.tools.GraphQLQueryResolver;
import jp.co.teruuu.ec_modeling.controller.product.dto.ItemDetail;
import jp.co.teruuu.ec_modeling.infla.data.dao.OrderGraphDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ItemDetailResolver implements GraphQLQueryResolver {

    public ItemDetail getItemDetail(String itemId) {
        return new ItemDetail(1, "name", 1, 1, 1, "description", LocalDateTime.now());
    }

}
