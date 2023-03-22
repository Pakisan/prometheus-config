package com.github.pakisan.prometheus.config.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes Prometheus exemplars.
 * <p>
 * Note that exemplar storage is still considered experimental and must be enabled via --enable-feature=exemplar-storage.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#exemplars">exemplars</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusExemplarsConfig {

    /**
     * Configures the maximum size of the circular buffer used to store exemplars for all series. Resizable during runtime.
     */
    @Builder.Default
    private int max_exemplars = 100_000;

}
