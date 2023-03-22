package com.github.pakisan.prometheus.config.auth;

import com.github.pakisan.prometheus.config.PrometheusTlsConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Describes Prometheus oauth2.
 * <p>
 * OAuth 2.0 authentication using the client credentials grant type. Prometheus fetches an access token from the
 * specified endpoint with the given client access and secret keys.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#oauth2">oauth2</a>
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#scrape_config">scrape_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusOAuth2 {


    private String client_id;

    private String client_secret;

    /**
     * Read the client secret from a file.
     * <p>
     * It is mutually exclusive with `client_secret`.
     */
    private String client_secret_file;

    /**
     * Scopes for the token request.
     */
    private List<String> scopes;

    /**
     * The URL to fetch the token from.
     */
    private String token_url;

    /**
     * Optional parameters to append to the token URL.
     */
    private Map<String, String> endpoint_params;

    /**
     * Configures the token request's TLS settings.
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

}
