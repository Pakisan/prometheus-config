package com.github.pakisan.prometheus.config.storage;

/**
 * Describes Prometheus storage.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/">CONFIGURATION</a>
 * @author Pavel Bodiachevskii
 */
public class PrometheusStorageConfig {

    /**
     * Lets you configure the runtime-reloadable configuration settings of the TSDB.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#tsdb">tsdb</a>
     */
    private PrometheusTsdbConfig tsdb;

    /**
     * Lets you configure exemplars.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#exemplars">exemplars</a>
     */
    private PrometheusExemplarsConfig exemplars;

}
