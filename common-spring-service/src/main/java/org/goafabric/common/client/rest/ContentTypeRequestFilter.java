package org.goafabric.common.client.rest;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 */
class ContentTypeRequestFilter implements ClientRequestFilter {

    private final List<Object> contentTypes;

    public ContentTypeRequestFilter() {
        contentTypes = new LinkedList<Object>();
    }

    public void addContentType(final MediaType contentType) {
        contentTypes.add(contentType);
    }

    @Override
    public void filter(final ClientRequestContext clientRequestContext) throws IOException {
        clientRequestContext.getHeaders().put(HttpHeaders.ACCEPT, contentTypes);
    }
}
