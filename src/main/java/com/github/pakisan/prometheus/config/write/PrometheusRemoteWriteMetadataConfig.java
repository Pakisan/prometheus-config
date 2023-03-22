package com.github.pakisan.prometheus.config.write;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * Describes Prometheus remote write metadata.
 * <p>
 * Configures the sending of series metadata to remote storage.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#remote_write">remote_write</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusRemoteWriteMetadataConfig {

    /**
     * Whether metric metadata is sent to remote storage or not.
     */
    @Builder.Default
    private boolean send = true;

    /**
     * How frequently metric metadata is sent to remote storage.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String send_interval = "1m";

    /**
     * Maximum number of samples per send.
     */
    @Builder.Default
    private int max_samples_per_send = 500;

}
