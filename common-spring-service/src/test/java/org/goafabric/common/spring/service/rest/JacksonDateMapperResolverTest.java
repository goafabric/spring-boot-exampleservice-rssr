/*
package org.goafabric.common.spring.service.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonDateMapperResolverTest {

    private final JacksonDateMapperResolver jacksonDateMapperResolver
            = new JacksonDateMapperResolver();

    @Test
    public void testWriteDatesAsTimestampsIsDisabled() {
        final ObjectMapper objectMapper = jacksonDateMapperResolver.getContext(Object.class);

        assertThat(objectMapper).isNotNull();

        int allFeatures = objectMapper.getSerializationConfig().getSerializationFeatures();
        int isDisabled = SerializationFeature.WRITE_DATES_AS_TIMESTAMPS.getMask() & allFeatures;
        assertThat(isDisabled).isEqualTo(0);
    }
}
*/
