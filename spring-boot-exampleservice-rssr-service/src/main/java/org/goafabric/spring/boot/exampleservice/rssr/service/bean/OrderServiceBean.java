package org.goafabric.spring.boot.exampleservice.rssr.service.bean;

import org.goafabric.spring.boot.exampleservice.rssr.logic.bean.OrderLogicBean;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.OrderService;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.dto.Order;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.result.BasicResult;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.result.OrderIdResult;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.result.OrderResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by amautsch on 26.06.2015.
 */
public class OrderServiceBean implements OrderService {
    @Autowired
    private OrderLogicBean orderLogicBean;

    @Override
    public BasicResult isAlive() {
        return orderLogicBean.isAlive();
    }

    @Override
    public OrderResult getOrder(final Long orderId) {
        return orderLogicBean.getOrder(orderId);
    }

    @Override
    public OrderIdResult createOrder(final Order order) {
        return orderLogicBean.createOrder(order);
    }

    @Override
    public BasicResult updateOrder(final Long orderId, final Order order) {
        return orderLogicBean.updateOrder(orderId, order);
    }

    @Override
    public BasicResult deleteOrder(final Long orderId) {
        return orderLogicBean.deleteOrder(orderId);
    }
}
