package com.github.pakisan.prometheus.config.servicediscovery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Describes Prometheus dns_sd_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#dns_sd_config">dns_sd_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DnsSdConfig {

    /**
     * A list of DNS domain names to be queried.
     */
    private List<String> names;

    /**
     * The type of DNS query to perform. One of SRV, A, AAAA or MX.
     */
    private DnsType type;

    /**
     * The port number used if the query type is not SRV.
     */
    private int port;

    /**
     * The time after which the provided names are refreshed.
     */
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String refresh_interval = "30s";

    public enum DnsType {
        SRV,
        A,
        AAAA,
        MX
    }

}
