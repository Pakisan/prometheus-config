package com.github.pakisan.prometheus.config.write;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * Describes Prometheus remote write queue.
 * <p>
 * Configures the queue used to write to remote storage.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#remote_write">remote_write</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusRemoteWriteQueueConfig {

    /**
     * Number of samples to buffer per shard before we block reading of more
     * <p>
     * samples from the WAL. It is recommended to have enough capacity in each
     * <p>
     * shard to buffer several requests to keep throughput up while processing
     * <p>
     * occasional slow remote requests.
     */
    @Builder.Default
    private int capacity = 2_500;

    /**
     * Maximum number of shards, i.e. amount of concurrency.
     */
    @Builder.Default
    private int max_shards = 200;

    /**
     * Minimum number of shards, i.e. amount of concurrency.
     */
    @Builder.Default
    private int min_shards = 1;

    /**
     * Maximum number of samples per send.
     */
    @Builder.Default
    private int max_samples_per_send = 500;

    /**
     * Maximum time a sample will wait in buffer.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String batch_send_deadline = "5s";

    /**
     * Initial retry delay. Gets doubled for every retry.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String min_backoff = "30ms";

    /**
     * Maximum retry delay.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String max_backoff = "5s";

    /**
     * Retry upon receiving a 429 status code from the remote-write storage.
     * <p>
     * This is experimental and might change in the future.
     */
    private boolean retry_on_http_429 = false;

}
