package org.goafabric.spring.boot.exampleservice.rssr.service.configuration;

import org.goafabric.common.spring.service.rest.AccessDeniedExceptionMapper;
import org.goafabric.common.spring.service.rest.JacksonDateMapperResolver;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.goafabric.spring.boot.exampleservice.rssr.service.bean.OrderServiceBean;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.constants.ExampleServiceConstants;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Created by amautsch on 26.06.2015.
 */
@Component
@ApplicationPath(ExampleServiceConstants.ROOT_URL)
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        register(OrderServiceBean.class);
        register(JacksonFeature.class);
        register(JacksonDateMapperResolver.class);
        register(AccessDeniedExceptionMapper.class); //needed otherwise access denied will result in error 500
    }
}
