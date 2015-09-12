package org.goafabric.spring.boot.exampleservice.rssr.service.bean;

import org.goafabric.common.client.rest.JerseyProxyBuilder;
import org.goafabric.spring.boot.exampleservice.rssr.Application;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.OrderService;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.constants.ExampleServiceConstants;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

/**
 * Created by amautsch on 27.07.2015.
 */
public class ApplicationNRIT {

    @Test
    public void test() throws InterruptedException {
        Application.main(new String[]{});

        //initHystrix();

        while (true) {
            Thread.sleep(1000);
        }
    }

    private void initHystrix() {
        OrderService orderService = JerseyProxyBuilder.newBuilder("http://localhost:8080" + ExampleServiceConstants
                .ROOT_URL)
                .acceptContentType(MediaType.APPLICATION_JSON_TYPE)
                .authenticationCredentials("admin", "admin")
                .build(OrderService.class);
        orderService.deleteOrder(1000l);
    }
}
