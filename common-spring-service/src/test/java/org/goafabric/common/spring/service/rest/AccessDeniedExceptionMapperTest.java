package org.goafabric.common.spring.service.rest;

import org.junit.Test;
import org.springframework.security.access.AccessDeniedException;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by amautsch on 07.08.2015.
 */
public class AccessDeniedExceptionMapperTest {

    @Test
    public void testToResponse() {
        final AccessDeniedExceptionMapper testInstance = new AccessDeniedExceptionMapper();
        final Response response = testInstance.toResponse(new AccessDeniedException(""));
        assertThat(response.getStatus()).isEqualTo(Response.Status.FORBIDDEN.getStatusCode());
    }

}