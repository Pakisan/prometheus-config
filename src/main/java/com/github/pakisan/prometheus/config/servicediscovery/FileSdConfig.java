package com.github.pakisan.prometheus.config.servicediscovery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Describes Prometheus file_sd_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#file_sd_config">file_sd_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileSdConfig {

    /**
     * Patterns for files from which target groups are extracted.
     */
    private List<@Pattern(regexp ="(.+\\.json)|(.+\\.yml)|(.+\\.yaml)") String> files;

    /**
     * Refresh interval to re-read the files.
     */
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String refresh_interval = "5m";

}
