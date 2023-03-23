package com.github.pakisan.prometheus.config.servicediscovery;

import com.github.pakisan.prometheus.config.PrometheusProtocolScheme;
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
 * Describes Prometheus azure_sd_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#azure_sd_config">azure_sd_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsulSdConfig {

    @Builder.Default
    private String server = "localhost:8500";

    private String token;

    private String datacenter;

    /**
     * Namespaces are only supported in Consul Enterprise.
     */
    private String namespace;

    /**
     * Admin Partitions are only supported in Consul Enterprise.
     */
    private String partition;

    private PrometheusProtocolScheme scheme = PrometheusProtocolScheme.HTTP;

    /**
     * The username and password fields are deprecated in favor of the basic_auth configuration.
     */
    private String username;

    /**
     * The username and password fields are deprecated in favor of the basic_auth configuration.
     */
    private String password;

    /**
     * A list of services for which targets are retrieved. If omitted, all services
     * <p>
     * are scraped.
     */
    private List<String> services;

    /**
     * An optional list of tags used to filter nodes for a given service. Services must contain all tags in the list.
     *
     * @see <a href="https://www.consul.io/api/catalog.html#list-nodes-for-service">ist-nodes-for-service</a> to know more about the possible filters that can be used.
     */
    private List<String> tags;

    /**
     * Node metadata key/value pairs to filter nodes for a given service.
     */
    private Map<String, String> node_meta;

    /**
     * The string by which Consul tags are joined into the tag label.
     */
    private String tag_separator = ",";

    /**
     * Allow stale Consul results. Will reduce load on Consul.
     *
     * @see <a href="https://www.consul.io/api/features/consistency.html">consistency</a>
     */
    private boolean allow_stale = true;

    /**
     * The time after which the provided names are refreshed.
     * <p>
     * On large setup it might be a good idea to increase this value because the catalog will change all the time.
     */
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String refresh_interval = "30s";

    /**
     * Optional HTTP basic authentication information, currently not support by Azure.
     */
    private PrometheusBasicAuth basic_auth;

    /**
     * Optional `Authorization` header configuration, currently not supported by Azure.
     */
    private PrometheusAuthorization authorization;

    /**
     * Optional OAuth 2.0 configuration, currently not supported by Azure.
     */
    private PrometheusOAuth2 oauth2;

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
     * TLS configuration.
     */
    private PrometheusTlsConfig tls_config;

}
