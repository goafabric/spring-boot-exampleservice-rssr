package org.goafabric.spring.boot.exampleservice.rssr.adapter;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

/**
 * Created by amautsch on 29.06.2015.
 */

@Log4j
@Component
public class CancelationServiceAdapter {
    @HystrixCommand(groupKey = AdapterConstants.CANCELATION_SERVICE, commandKey = AdapterConstants.CANCELATION_SERVICE)
    public void cancelOrder(final Long orderId) {
        log.info("canceling order with id : " + orderId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
