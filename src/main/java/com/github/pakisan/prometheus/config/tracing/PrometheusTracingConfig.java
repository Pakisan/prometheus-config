package com.github.pakisan.prometheus.config.tracing;

import com.github.pakisan.prometheus.config.PrometheusTlsConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * Describes Prometheus tracing_config.
 * <p>
 * tracing_config configures exporting traces from Prometheus to a tracing backend via the OTLP protocol.
 * Tracing is currently an experimental feature and could change in the future.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#tracing_config">tracing_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusTracingConfig {

    /**
     * Client used to export the traces. Options are 'http' or 'grpc'.
     */
    @Builder.Default
    private PrometheusTracingClientType client_type = PrometheusTracingClientType.GRPC;

    /**
     * Endpoint to send the traces to. Should be provided in format <host>:<port>.
     */
    private String endpoint;

    /**
     * Sets the probability a given trace will be sampled. Must be a float from 0 through 1.
     */
    @Builder.Default
    private float sampling_fraction = 0;

    /**
     * If disabled, the client will use a secure connection.
     */
    @Builder.Default
    private boolean insecure = false;

    /**
     * Key-value pairs to be used as headers associated with gRPC or HTTP requests.
     */
    private Map<String, String> headers;

    /**
     * Compression key for supported compression types. Supported compression: gzip.
     */
    private String compression;

    /**
     * Maximum time the exporter will wait for each batch export.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String timeout = "10s";

    /**
     * TLS configuration.
     */
    private PrometheusTlsConfig tls_config;

}
