package com.github.pakisan.prometheus.config.servicediscovery;

import com.github.pakisan.prometheus.config.PrometheusTlsConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Describes Prometheus triton_sd_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#triton_sd_config">triton_sd_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TritonSdConfig {

    /**
     * The account to use for discovering new targets.
     */
    private String account;

    /**
     * The type of targets to discover, can be set to:
     * <p>
     * * "container" to discover virtual machines (SmartOS zones, lx/KVM/bhyve branded zones) running on Triton
     * <p>
     * * "cn" to discover compute nodes (servers/global zones) making up the Triton infrastructure
     */
    private Role role = Role.CONTAINER;

    /**
     * The DNS suffix which should be applied to target.
     */
    private String dns_suffix;

    /**
     * The Triton discovery endpoint (e.g. 'cmon.us-east-3b.triton.zone'). This is
     * <p>
     * often the same value as dns_suffix.
     */
    private String endpoint;

    /**
     * A list of groups for which targets are retrieved, only supported when `role` == `container`.
     * <p>
     * If omitted all containers owned by the requesting account are scraped.
     */
    private List<String> groups;

    /**
     * The port to use for discovery and metric scraping.
     */
    private int port = 9163;

    /**
     * The interval which should be used for refreshing targets.
     */
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String refresh_interval = "60s";

    /**
     * The Triton discovery API version.
     */
    private int version = 1;

    /**
     * TLS configuration.
     */
    private PrometheusTlsConfig tls_config;

    public enum Role {
        CONTAINER,
        CN
    }

}
