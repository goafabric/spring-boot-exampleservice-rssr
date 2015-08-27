package org.goafabric.common.spring.service.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.ext.ContextResolver;
import java.util.TimeZone;

public class JacksonDateMapperResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    public JacksonDateMapperResolver() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setTimeZone(TimeZone.getDefault());
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {
        return objectMapper;
    }
}

