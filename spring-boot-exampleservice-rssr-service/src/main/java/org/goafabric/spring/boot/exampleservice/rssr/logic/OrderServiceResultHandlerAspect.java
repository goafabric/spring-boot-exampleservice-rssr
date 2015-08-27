package org.goafabric.spring.boot.exampleservice.rssr.logic;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.goafabric.common.spring.service.sustainability.ResultHandlerAspect;
import org.goafabric.spring.boot.exampleservice.rssr.adapter.exception.AdapterSystemException;
import org.goafabric.spring.boot.exampleservice.rssr.logic.exception.InputValidationApplicationException;
import org.springframework.stereotype.Component;

/**
 * Created by amautsch on 26.06.2015.
 */

@Component
public class OrderServiceResultHandlerAspect extends ResultHandlerAspect {

    public OrderServiceResultHandlerAspect() {
        super(OrderServiceResultHandlerAspect.class.getName());
    }

    protected Exception mapException(final Exception e) {
        final Exception mappedException;
        if (e instanceof IllegalArgumentException) {
            mappedException = new InputValidationApplicationException(e.getMessage());
        } else if (e instanceof HystrixRuntimeException) {
            mappedException = new AdapterSystemException(e.getMessage(), e);
        } else {
            mappedException = e;
        }
        return mappedException;
    }


}
