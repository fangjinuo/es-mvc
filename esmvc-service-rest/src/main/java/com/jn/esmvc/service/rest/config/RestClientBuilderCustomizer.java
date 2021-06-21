package com.jn.esmvc.service.rest.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClientBuilder;

public interface RestClientBuilderCustomizer {
    /**
     * Customize the {@link RestClientBuilder}.
     * <p>
     * Possibly overrides customizations made with the {@code "rest"}
     * configuration properties namespace. For more targeted changes, see
     * {@link #customize(HttpAsyncClientBuilder)} and
     * {@link #customize(RequestConfig.Builder)}.
     *
     * @param builder the builder to customize
     */
    void customize(RestClientBuilder builder);

    /**
     * Customize the {@link HttpAsyncClientBuilder}.
     *
     * @param builder the builder
     */
    void customize(HttpAsyncClientBuilder builder);

    /**
     * Customize the {@link RequestConfig.Builder}.
     *
     * @param builder the builder
     */
    void customize(RequestConfig.Builder builder);

}
