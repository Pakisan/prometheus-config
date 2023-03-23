package com.github.pakisan.prometheus.config.read;

import com.github.pakisan.prometheus.config.PrometheusTlsConfig;
import com.github.pakisan.prometheus.config.auth.PrometheusAuthorization;
import com.github.pakisan.prometheus.config.auth.PrometheusBasicAuth;
import com.github.pakisan.prometheus.config.auth.PrometheusOAuth2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * Describes Prometheus remote read.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#remote_read">remote_read</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusRemoteReadConfig {

    /**
     * The URL of the endpoint to query from.
     */
    private String url;

    /**
     * Name of the remote read config, which if specified must be unique among remote read configs.
     * <p>
     * The name will be used in metrics and logging in place of a generated value to help users distinguish between
     * <p>
     * remote read configs.
     */
    private String name;

    /**
     * An optional list of equality matchers which have to be
     * <p>
     * present in a selector to query the remote read endpoint.
     */
    private Map<@Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]*") String, List<String>> required_matchers;

    /**
     * Timeout for requests to the remote read endpoint.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String remote_timeout = "1m";

    /**
     * Custom HTTP headers to be sent along with each remote read request.
     * <p>
     * Be aware that headers that are set by Prometheus itself can't be overwritten.
     */
    private Map<String, List<String>> headers;

    /**
     * Whether reads should be made for queries for time ranges that
     * <p>
     * the local storage should have complete data for.
     */
    @Builder.Default
    private boolean read_recent = false;

    /**
     * Sets the `Authorization` header on every remote read request with the
     * <p>
     * configured username and password.
     * <p>
     * password and password_file are mutually exclusive.
     */
    private PrometheusBasicAuth basic_auth;

    /**
     * Optional `Authorization` header configuration.
     */
    private PrometheusAuthorization authorization;

    /**
     * Optional OAuth 2.0 configuration.
     * <p>
     * Cannot be used at the same time as basic_auth or authorization.
     */
    private PrometheusOAuth2 oauth2;

    /**
     * Configures the remote read request's TLS settings.
     */
    private PrometheusTlsConfig tls_config;

    /**
     * Optional proxy URL.
     */
    private String proxy_url;

    /**
     * Comma-separated string that can contain IPs, CIDR notation, domain names
     * <p>
     * that should be excluded from proxying. IP and domain names can
     * <p>
     * contain port numbers.
     */
    private String no_proxy;

    /**
     * Use proxy URL indicated by environment variables (HTTP_PROXY, https_proxy, HTTPs_PROXY, https_proxy, and no_proxy)
     */
    @Builder.Default
    private boolean proxy_from_environment = false;

    /**
     * Specifies headers to send to proxies during CONNECT requests.
     */
    private Map<String, List<String>> proxy_connect_header;

    /**
     * Configure whether HTTP requests follow HTTP 3xx redirects.
     */
    @Builder.Default
    private boolean follow_redirects = true;

    /**
     * Whether to enable HTTP2.
     */
    @Builder.Default
    private boolean enable_http2 = true;

    /**
     * Whether to use the external labels as selectors for the remote read endpoint.
     */
    @Builder.Default
    private boolean filter_external_labels = true;

}
