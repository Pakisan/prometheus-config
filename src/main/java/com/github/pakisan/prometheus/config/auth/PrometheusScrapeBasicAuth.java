package com.github.pakisan.prometheus.config.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes Prometheus basic_auth.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#scrape_config">scrape_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusScrapeBasicAuth {

    private String username;
    private String password;
    private String password_file;

}
