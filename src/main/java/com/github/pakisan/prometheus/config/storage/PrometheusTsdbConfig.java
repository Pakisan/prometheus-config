package com.github.pakisan.prometheus.config.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * Describes Prometheus TSDB.
 * <p>
 * tsdb lets you configure the runtime-reloadable configuration settings of the TSDB.
 * <p>
 * <b>NOTE:</b> Out-of-order ingestion is an experimental feature, but you do not need any additional flag to enable it.
 * Setting out_of_order_time_window to a positive duration enables it.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#tsdb">tsdb</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusTsdbConfig {

    /**
     * Configures how old an out-of-order/out-of-bounds sample can be w.r.t. the TSDB max time.
     * <p>
     * An out-of-order/out-of-bounds sample is ingested into the TSDB as long as the timestamp
     * <p>
     * of the sample is >= TSDB.MaxTime-out_of_order_time_window.
     * <p>
     * <p>
     * When out_of_order_time_window is >0, the errors out-of-order and out-of-bounds are
     * <p>
     * combined into a single error called 'too-old'; a sample is either (a) ingestible
     * <p>
     * into the TSDB, i.e. it is an in-order sample or an out-of-order/out-of-bounds sample
     * <p>
     * that is within the out-of-order window, or (b) too-old, i.e. not in-order
     * <p>
     * and before the out-of-order window.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String out_of_order_time_window = "0s";

}
