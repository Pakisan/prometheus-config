package com.github.pakisan.prometheus.config.servicediscovery;

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
 * Describes Prometheus ec2_sd_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#ec2_sd_config">ec2_sd_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ec2SdConfig {

    /**
     * The AWS region. If blank, the region from the instance metadata is used.
     */
    private String region;

    /**
     * Custom endpoint to be used.
     */
    private String endpoint;

    /**
     * The AWS API keys. If blank, the environment variables `AWS_ACCESS_KEY_ID`
     */
    private String access_key;

    /**
     * The AWS API keys. If blank, the environment variables `AWS_SECRET_ACCESS_KEY`
     */
    private String secret_key;

    /**
     * AWS Role ARN, an alternative to using AWS API keys.
     */
    private String role_arn;

    /**
     * The port to scrape metrics from. If using the public IP address, this must
     * <p>
     * instead be specified in the relabeling rule.
     */
    private int port = 80;

    /**
     * The time after which the droplets are refreshed.
     */
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String refresh_interval = "60s";

    /**
     * Filters can be used optionally to filter the instance list by other criteria.
     *
     * @see <a href="https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_DescribeInstances.html">Available filter criteria</a>
     * @see <a href="https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_Filter.html">Filter API documentation</a>
     */
    private Map<String, List<String>> filters;

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
