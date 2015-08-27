package org.goafabric.spring.boot.exampleservice.rssr.service.bean;

import org.goafabric.spring.boot.exampleservice.rssr.service.intf.OrderService;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.dto.Order;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.result.OrderIdResult;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.result.OrderResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by amautsch on 26.06.2015.
 */

//needed because hystrix gets instantiated twice by local tests
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class OrderServiceBeanIT {

    @Autowired
    private OrderService orderService;

    @Test
    public void testIsAlive() {
        assertThat(orderService.isAlive().isExecuted()).isTrue();
    }

    @Test
    public void testGetOrder() {
        //http://localhost:8080/orderservice1_0/orders/1
        final OrderResult orderResult = orderService.getOrder(1000l);
        assertThat(orderResult.isExecuted()).isTrue();

        assertThat(orderResult.getValue().getOrderId()).isEqualTo(1000l);
        assertThat(orderResult.getValue().getOrderDescription()).isEqualTo("test order");
        assertThat(orderResult.getValue().getOrderDate()).isEqualTo(new Date(1000));
    }

    @Test
    public void testCreateOrder() {
        final Order order = Order.builder()
                .orderDescription("test order").orderDate(new Date())
                .build();

        final OrderIdResult orderIdResult = orderService.createOrder(order);

        assertThat(orderIdResult.isExecuted()).isTrue();
        assertThat(orderIdResult.getValue()).isEqualTo(1000l);
    }

    @Test
    public void testUpdateOrder() {
        final Order order = Order.builder().
                orderId(1000l).orderDescription("new order").orderDate(new Date())
                .build();

        assertThat(orderService.updateOrder(1000l, order).isExecuted()).isTrue();
    }

    @Test
    public void testDelete() {
        assertThat(orderService.deleteOrder(1000l).isExecuted()).isTrue();
    }
}