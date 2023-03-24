package com.github.pakisan.prometheus.config.servicediscovery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * Describes Prometheus gce_sd_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#gce_sd_config">gce_sd_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GceSdConfig {

    /**
     * The GCP Project
     */
    private String project;

    /**
     * The zone of the scrape targets. If you need multiple zones use multiple
     * <p>
     * gce_sd_configs.
     */
    private String zone;

    /**
     * Filter can be used optionally to filter the instance list by other criteria
     *
     * @see <a href="https://cloud.google.com/compute/docs/reference/latest/instances/list">Syntax of this filter string</a>
     */
    private String filter;

    /**
     * The port to scrape metrics from. If using the public IP address, this must
     * <p>
     * instead be specified in the relabeling rule.
     */
    private int port = 80;

    /**
     * Refresh interval to re-read the instance list
     */
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String refresh_interval = "60s";

    /**
     * The tag separator is used to separate the tags on concatenation
     */
    private String tag_separator = ",";

}
