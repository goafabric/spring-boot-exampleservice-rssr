package org.goafabric.spring.boot.exampleservice.rssr.logic.bean;

import org.goafabric.common.spring.service.security.annotation.AuditLog;
import org.goafabric.common.spring.service.sustainability.annotation.ResultHandler;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.goafabric.spring.boot.exampleservice.rssr.adapter.CancelationServiceAdapter;
import org.goafabric.spring.boot.exampleservice.rssr.security.SecurityRoleConstants;
import org.goafabric.spring.boot.exampleservice.rssr.service.configuration.CacheConfiguration;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.dto.Order;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.result.BasicResult;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.result.OrderIdResult;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.result.OrderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by amautsch on 26.06.2015.
 */
@Component
@Slf4j
@AuditLog
@ResultHandler
public class OrderLogicBean {
    @Autowired
    private CancelationServiceAdapter cancelationServiceAdapter;

    @PreAuthorize(SecurityRoleConstants.STANDARD_READ_ROLE)
    public BasicResult isAlive() {
        return new BasicResult();
    }

    @Cacheable(value = CacheConfiguration.BUSINESS, unless = CacheConfiguration.UNLESS)
    @PreAuthorize(SecurityRoleConstants.STANDARD_READ_ROLE)
    public OrderResult getOrder(@NonNull final Long orderId) {
        log.info("get order with orderId : " + orderId);

        final Order order = Order.builder().
                orderId(1000l).orderDescription("test order").orderDate(new Date(1000))
                .build();

        return OrderResult.builder().value(order).build();
    }

    @PreAuthorize(SecurityRoleConstants.STANDARD_WRITE_ROLE)
    public OrderIdResult createOrder(@NonNull final Order order) {
        log.info("create order with order : " + order.toString());
        return OrderIdResult.builder().value(1000l).build();
    }

    @PreAuthorize(SecurityRoleConstants.STANDARD_WRITE_ROLE)
    public BasicResult updateOrder(@NonNull final Long orderId, @NonNull final Order order) {
        log.info("update order with order : " + order.toString());
        return new BasicResult();
    }

    @PreAuthorize(SecurityRoleConstants.STANDARD_WRITE_ROLE)
    public BasicResult deleteOrder(@NonNull final Long orderId) {
        log.info("delete order with orderid : " + orderId);
        cancelationServiceAdapter.cancelOrder(orderId);
        return new BasicResult();
    }

}
