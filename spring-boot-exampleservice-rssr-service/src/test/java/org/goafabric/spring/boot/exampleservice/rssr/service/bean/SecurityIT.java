package org.goafabric.spring.boot.exampleservice.rssr.service.bean;

import org.goafabric.spring.boot.exampleservice.rssr.service.intf.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import javax.ws.rs.NotAuthorizedException;

/**
 * Created by amautsch on 30.07.2015.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class SecurityIT {
    @Autowired
    private OrderService orderServiceWrongUser;

    @Test(expected = NotAuthorizedException.class)
    public void testWrongUser() {
        orderServiceWrongUser.isAlive();
    }
}
