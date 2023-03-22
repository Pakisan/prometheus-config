package com.github.pakisan.prometheus.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * Describes Prometheus configuration's global section.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/">CONFIGURATION</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusGlobalSection {

    /**
     * How frequently to scrape targets by default.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String scrape_interval = "1m";

    /**
     * How long until a scrape request times out.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String scrape_timeout = "10s";

    /**
     * How frequently to evaluate rules.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String evaluation_interval = "1m";

    /**
     * The labels to add to any time series or alerts when communicating with
     * external systems (federation, remote storage, Alertmanager).
     */
    private Map<@Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]*") String, String> external_labels;

    /**
     * File to which PromQL queries are logged.
     * Reloading the configuration will reopen the file.
     */
    private String query_log_file;

}
