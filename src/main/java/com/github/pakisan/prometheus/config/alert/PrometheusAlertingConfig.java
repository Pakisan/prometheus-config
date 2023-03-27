package com.github.pakisan.prometheus.config.alert;

import com.github.pakisan.prometheus.config.PrometheusRelabelConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes Prometheus alerting.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#alertmanager_config">alertmanager_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusAlertingConfig {

    private List<PrometheusRelabelConfig> alert_relabel_configs;

    private List<PrometheusAlertManagerConfig> alertmanagers;

}
