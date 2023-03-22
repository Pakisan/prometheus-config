package com.github.pakisan.prometheus.config.write;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes Prometheus remote write AWS's Signature Verification 4 signing process to sign requests.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#remote_write">remote_write</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusRemoteWriteSigV4Config {

    /**
     * The AWS region. If blank, the region from the default credentials chain
     * <p>
     * is used.
     */
    private String region;

    /**
     * The AWS API keys. If blank, the environment variables `AWS_ACCESS_KEY_ID`
     * <p>
     * and `AWS_SECRET_ACCESS_KEY` are used.
     */
    private String access_key;

    /**
     * The AWS API keys. If blank, the environment variables `AWS_ACCESS_KEY_ID`
     * <p>
     * and `AWS_SECRET_ACCESS_KEY` are used.
     */
    private String secret_key;

    /**
     * Named AWS profile used to authenticate.
     */
    private String profile;

    /**
     * AWS Role ARN, an alternative to using AWS API keys.
     */
    private String role_arn;

}
