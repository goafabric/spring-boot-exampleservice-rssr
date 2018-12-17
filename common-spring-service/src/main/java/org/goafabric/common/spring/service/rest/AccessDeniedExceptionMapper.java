package org.goafabric.common.spring.service.rest;

import org.springframework.security.access.AccessDeniedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *         Date: 28.05.15
 *         Time: 14:48
 */
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {
    @Override
    public Response toResponse(final AccessDeniedException ex) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
