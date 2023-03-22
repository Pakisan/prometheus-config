package com.github.pakisan.prometheus.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes Prometheus tls_config.
 * <p>
 * A tls_config allows configuring TLS connections.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#tls_config">tls_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusTlsConfig {

    /**
     * CA certificate to validate API server certificate with.
     */
    private String ca_file;

    /**
     * Certificate file for client cert authentication to the server.
     */
    private String cert_file;

    /**
     * Key file for client cert authentication to the server.
     */
    private String key_file;

    /**
     * ServerName extension to indicate the name of the server.
     *
     * @see <a href="https://tools.ietf.org/html/rfc4366#section-3.1">rfc4366#section-3.1</a>
     */
    private String server_name;

    /**
     * Disable validation of the server certificate.
     */
    private boolean insecure_skip_verify = false;

    /**
     * Minimum acceptable TLS version. Accepted values: TLS10 (TLS 1.0), TLS11 (TLS
     * <p>
     * 1.1), TLS12 (TLS 1.2), TLS13 (TLS 1.3).
     * <p>
     * If unset, Prometheus will use Go default minimum version, which is TLS 1.2.
     *
     * @see <a href="https://pkg.go.dev/crypto/tls#Config">TLS MinVersion in GO</a>.
     */
    private String min_version;

    /**
     * If unset, Prometheus will use Go default maximum version, which is TLS 1.3.
     *
     * @see <a href="https://pkg.go.dev/crypto/tls#Config">TLS MaxVersion in GO</a>.
     */
    private String max_version;

}
