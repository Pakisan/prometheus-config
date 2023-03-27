package com.github.pakisan.prometheus.config.write;

import com.github.pakisan.prometheus.config.PrometheusRelabelConfig;
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
 * Describes Prometheus remote write.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#remote_write">remote_write</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusRemoteWriteConfig {

    /**
     * The URL of the endpoint to send samples to.
     */
    private String url;

    /**
     * Timeout for requests to the remote write endpoint.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String remote_timeout = "30s";

    /**
     * Custom HTTP headers to be sent along with each remote write request.
     * <p>
     * Be aware that headers that are set by Prometheus itself can't be overwritten.
     */
    private Map<String, String> headers;

    /**
     * List of remote write relabel configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#relabel_config">relabel_config</a>
     */
    private List<PrometheusRelabelConfig> write_relabel_configs;

    /**
     * Name of the remote write config, which if specified must be unique among remote write configs.
     * <p>
     * The name will be used in metrics and logging in place of a generated value to help users distinguish between
     * <p>
     * remote write configs.
     */
    private String name;

    /**
     * Enables sending of exemplars over remote write. Note that exemplar storage itself must be enabled for exemplars to be scraped in the first place.
     */
    @Builder.Default
    private boolean send_exemplars = false;

    /**
     * Enables sending of native histograms, also known as sparse histograms, over remote write.
     */
    @Builder.Default
    private boolean send_native_histograms = false;

    /**
     * Sets the `Authorization` header on every remote write request with the
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
     * Optionally configures AWS's Signature Verification 4 signing process to
     * <p>
     * sign requests. Cannot be set at the same time as basic_auth, authorization, or oauth2.
     * <p>
     * To use the default credentials from the AWS SDK, use `sigv4: {}`.
     */
    private PrometheusRemoteWriteSigV4Config sigv4;

    /**
     * Optional OAuth 2.0 configuration.
     * <p>
     * Cannot be used at the same time as basic_auth, authorization, or sigv4.
     */
    private PrometheusOAuth2 oauth2;

    /**
     * Configures the remote write request's TLS settings.
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
     * Configures the queue used to write to remote storage.
     */
    private PrometheusRemoteWriteQueueConfig queue_config;

    /**
     * Configures the sending of series metadata to remote storage.
     * <p>
     * Metadata configuration is subject to change at any point
     * <p>
     * or be removed in future releases.
     */
    private PrometheusRemoteWriteMetadataConfig metadata_config;

}
