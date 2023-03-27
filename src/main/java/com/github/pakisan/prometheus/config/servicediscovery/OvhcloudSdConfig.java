package com.github.pakisan.prometheus.config.servicediscovery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * Describes Prometheus ovhcloud_sd_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#ovhcloud_sd_config">ovhcloud_sd_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OvhcloudSdConfig {

    /**
     * Access key to use.
     *
     * @see <a href="https://api.ovh.com">OVHcloud API</a>
     */
    private String application_key;

    /**
     * Access key to use.
     *
     * @see <a href="https://api.ovh.com">OVHcloud API</a>
     */
    private String application_secret;

    /**
     * Access key to use.
     *
     * @see <a href="https://api.ovh.com">OVHcloud API</a>
     */
    private String consumer_key;

    /**
     * Service of the targets to retrieve. Must be `vps` or `dedicated_server`.
     */
    private Target service;

    /**
     * API endpoint.
     *
     * @see <a href="https://github.com/ovh/go-ovh#supported-apis">Supported APIs</a>
     */
    private String endpoint;

    /**
     * Refresh interval to re-read the resources list.
     */
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String refresh_interval = "60s";

    public enum Target {
        VPS,
        DEDICATED_SERVER
    }

}
