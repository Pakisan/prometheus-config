package com.github.pakisan.prometheus.config.servicediscovery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Describes Prometheus serverset_sd_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#serverset_sd_config">serverset_sd_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServersetSdConfig {

    /**
     * The Zookeeper servers.
     */
    private List<String> servers;

    /**
     * Paths can point to a single serverset, or the root of a tree of serversets.
     */
    private List<String> paths;

    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String timeout = "10s";

}
