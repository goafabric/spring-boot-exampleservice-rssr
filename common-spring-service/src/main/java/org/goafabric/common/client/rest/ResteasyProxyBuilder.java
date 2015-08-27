package org.goafabric.common.client.rest;

import lombok.NonNull;
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

/**
 * A simple REST clientbuilder class to provide a convenient way to build client proxies for a REST API. Encapsulates
 * the underlying JAX-RS implementation of the client.
 * <p>
 * The simplest way to create a proxy client for your REST API is the following:
 * <pre>
 * ServiceInterface service = ResteasyProxyBuilder.newBuilder("http://yourendpoint.de/rest")
 *     .withConnectionPoolSize(10)
 *     .build(ServiceInterface.class);
 * </pre>
 * In this example we create a client for the resource <code>http://yourendpoint.de/rest/&lt;resource&gt;</code> and all
 * supported operations on this resource, and we want an internal connectionpool size of 10. The actual resource name is
 * defined as the @Path-annotation in the ServiceInterface.
 * <br>
 * The connectionpool is mandatory for the Resteasy implementation and it's recommended so set it to the size of the
 * allowed incoming connections to your application.
 * </p>
 * @author lthielmann
 */
public final class ResteasyProxyBuilder {

    private final String endpointUrl;
    private final ResteasyClientBuilder resteasyBuilder;
    private final ContentTypeRequestFilter contentTypeRequestFilter;
    private Integer connectionPoolSize = 0;

    private ResteasyProxyBuilder(final String endpointUrl) {
        this.endpointUrl = endpointUrl;
        resteasyBuilder = new ResteasyClientBuilder();
        contentTypeRequestFilter = new ContentTypeRequestFilter();
    }

    /**
     * Create a new ResteasyProxyBuilder for a given REST endpoint.
     * <p>
     * The endpoint uri is the root path of the REST API, not the particular resource you want to consume. The resource
     * is defined by the service interface you choose.
     * </p>
     * @param uri Mustn't be null.
     * @return A new ResteasyProxyBuilder.
     */
    public static ResteasyProxyBuilder newBuilder(@NonNull final String uri) {
        return new ResteasyProxyBuilder(uri);
    }

    /**
     * Set your preferred content-type of the representation.
     * <p>
     *     If no content-type is set, the server chooses the representation and the client has to deal with it.
     * </p>
     * @param contentType mustn't be null.
     * @return ResteasyProxyBuilder
     */
    public ResteasyProxyBuilder acceptContentType(@NonNull final MediaType contentType) {
        contentTypeRequestFilter.addContentType(contentType);
        return this;
    }

    /**
     * Set your user credentials for every request.
     * <p>
     *     <b>NOTE: Only HTTP basic authentication is currently supported.</b>
     * </p>
     * @param username mustn't be null.
     * @param password mustn't be null.
     * @return JerseyProxyBuilder
     */
    public ResteasyProxyBuilder authenticationCredentials(
            @NonNull final String username, @NonNull final String password) {
        resteasyBuilder.register(new BasicAuthentication(username, password));
        return this;
    }

    /**
     * Set the connection timeout for your client.
     * @param timeoutInMillis mustn't be null.
     * @return ResteasyProxyBuilder
     */
    public ResteasyProxyBuilder connectionTimeout(@NonNull final Integer timeoutInMillis) {
        resteasyBuilder.establishConnectionTimeout(timeoutInMillis.longValue(), TimeUnit.MILLISECONDS);
        return this;
    }

    /**
     * Set the read timeout for every request.
     * @param timeoutInMillis mustn't be null.
     * @return ResteasyProxyBuilder
     */
    public ResteasyProxyBuilder requestTimeout(@NonNull final Integer timeoutInMillis) {
        resteasyBuilder.socketTimeout(timeoutInMillis.longValue(), TimeUnit.MILLISECONDS);
        return this;
    }

    /**
     * Set the internal connectionpool of your proxy client.
     * <p>
     *     <b>NOTE: For Resteasy it's required to set a connectionpool of at least 1.</b>
     * </p>
     * @return ResteasyProxyBuilder
     */
    public ResteasyProxyBuilder connectionPool(@NonNull final Integer connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
        return this;
    }

    /**
     * Create a proxy which implements your given interface.
     * @param serviceInterface mustn't be null.
     * @param <T> Type of your JAX-RS annotated interface.
     * @return A proxy for that particular resource.
     */
    public <T> T build(@NonNull final Class<T> serviceInterface) {
        if (connectionPoolSize < 1) {
            throw new IllegalStateException("A connectionPoolSize must be set.");
        }

        resteasyBuilder.connectionPoolSize(connectionPoolSize);
        resteasyBuilder.register(contentTypeRequestFilter);
        final ResteasyWebTarget target = resteasyBuilder.build().target(endpointUrl);
        return target.proxy(serviceInterface);
    }
}
