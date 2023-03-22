package com.github.pakisan.prometheus.config.scrape;

/**
 * Describes Prometheus scrape scheme.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#scrape_config">scrape_config</a>
 * @author Pavel Bodiachevskii
 */
public enum PrometheusScrapeScheme {

    HTTP,
    HTTPS

}
