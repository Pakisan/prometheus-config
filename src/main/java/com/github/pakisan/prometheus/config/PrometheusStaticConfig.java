package com.github.pakisan.prometheus.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * Describes Prometheus static_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#static_config">static_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusStaticConfig {

    /**
     * The targets specified by the static config.
     */
    private List<String> targets;

    /**
     * Labels assigned to all metrics scraped from the targets.
     */
    private Map<@Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]*") String, String> labels;

}
