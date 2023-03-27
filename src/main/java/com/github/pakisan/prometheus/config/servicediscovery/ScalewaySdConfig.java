package com.github.pakisan.prometheus.config.servicediscovery;

import com.github.pakisan.prometheus.config.PrometheusTlsConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * Describes Prometheus scaleway_sd_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#scaleway_sd_config">scaleway_sd_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScalewaySdConfig {

    /**
     * Access key to use.
     *
     * @see <a href="https://console.scaleway.com/project/credentials">Scaleway console</a>
     */
    private String access_key;

    /**
     * Secret key to use when listing targets.
     * <p>
     * It is mutually exclusive with `secret_key_file`.
     *
     * @see <a href="https://console.scaleway.com/project/credentials">Scaleway console</a>
     */
    private String secret_key;

    /**
     * Sets the secret key with the credentials read from the configured file.
     * <p>
     * It is mutually exclusive with `secret_key`.
     */
    private String secret_key_file;

    /**
     * Project ID of the targets.
     */
    private String project_id;

    /**
     *  Role of the targets to retrieve. Must be `instance` or `baremetal`.
     */
    private Role role;

    /**
     * The port to scrape metrics from.
     */
    private int port = 80;

    /**
     * API URL to use when doing the server listing requests.
     */
    private String api_url = "https://api.scaleway.com";

    /**
     * Zone is the availability zone of your targets (e.g. fr-par-1).
     */
    private String zone = "fr-par-1";

    /**
     * TagsFilter specify a tag filter (a server needs to have all defined tags to be listed) to apply on the server listing request.
     */
    private List<String> tags_filter;

    /**
     * Refresh interval to re-read the targets list.
     */
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String refresh_interval = "60s";

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

    private enum Role {
        INSTANCE,
        BAREMETAL
    }

}
