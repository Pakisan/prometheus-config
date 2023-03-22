package com.github.pakisan.prometheus.config.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes Prometheus authorization.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#scrape_config">scrape_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusAuthorization {

    /**
     * Sets the authentication type of the request.
     */
    @Builder.Default
    private String type = "Bearer";

    /**
     * Sets the credentials of the request. It is mutually exclusive with
     * `credentials_file`.
     */
    private String credentials;

    /**
     * Sets the credentials of the request with the credentials read from the
     * configured file. It is mutually exclusive with `credentials`.
     */
    private String credentials_file;

}
