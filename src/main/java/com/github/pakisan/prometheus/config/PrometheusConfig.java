package com.github.pakisan.prometheus.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes Prometheus configuration file.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/">CONFIGURATION</a>
 * @author Pavel Bodiachevskii
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusConfig {

    /**
     * The global configuration specifies parameters that are valid in all other configuration contexts.
     * They also serve as defaults for other configuration sections.
     */
    private PrometheusGlobalSection global;

    /**
     * Rule files specifies a list of globs. Rules and alerts are read from all matching files.
     */
    private List<String> rule_files;

    /**
     * Scrape config files specifies a list of globs. Scrape configs are read from
     * all matching files and appended to the list of scrape configs.
     */
    private List<String> scrape_config_files;

    /**
     * A list of scrape configurations.
     */
    private List<Object> scrape_configs;

    /**
     * Alerting specifies settings related to the Alertmanager.
     */
    private Object alerting;

    /**
     * Settings related to the remote write feature.
     */
    private List<Object> remote_write;

    /**
     * Settings related to the remote read feature.
     */
    private List<Object> remote_read;

    /**
     * Storage related settings that are runtime reloadable.
     */
    private Object storage;

    /**
     * Configures exporting traces.
     */
    private Object tracing;

}