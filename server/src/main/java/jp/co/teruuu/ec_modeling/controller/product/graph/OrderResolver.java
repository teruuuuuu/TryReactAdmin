package jp.co.teruuu.ec_modeling.controller.product.graph;

import graphql.kickstart.tools.GraphQLQueryResolver;
import jp.co.teruuu.ec_modeling.infla.data.dao.ItemDetailGraphDao;
import jp.co.teruuu.ec_modeling.infla.data.dao.OrderGraphDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderResolver implements GraphQLQueryResolver {

    @Autowired
    OrderGraphDao orderGraphDao;
    @Autowired
    ItemDetailGraphDao itemDetailGraphDao;

    public OrderGraph getOrder(String orderId) {
        OrderGraph orderGraph = orderGraphDao.get(orderId);
        orderGraph.setItems(itemDetailGraphDao.get(orderId));
        return orderGraph;
    }
}
