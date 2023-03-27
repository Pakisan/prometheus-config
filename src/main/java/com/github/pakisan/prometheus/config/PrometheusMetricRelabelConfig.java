package com.github.pakisan.prometheus.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * Describes Prometheus metric_relabel_configs.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#metric_relabel_configs">metric_relabel_configs</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusMetricRelabelConfig {

    /**
     * The source labels select values from existing labels. Their content is concatenated
     * <p>
     * using the configured separator and matched against the configured regular expression
     * <p>
     * for the replace, keep, and drop actions.
     */
    private Map<@Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]*") String, List<String>> source_labels;

    /**
     * Separator placed between concatenated source label values.
     */
    private String separator = ";";

    /**
     * Label to which the resulting value is written in a replace action.
     * <p>
     * It is mandatory for replace actions. Regex capture groups are available.
     */
    @Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]*")
    private String target_label;

    /**
     * Regular expression against which the extracted value is matched.
     * <p>
     * <p>
     * regex is any valid <a href="https://github.com/google/re2/wiki/Syntax">RE2 regular expression</a>. It is required for the replace, keep, drop,
     * <p>
     * labelmap,labeldrop and labelkeep actions. The regex is anchored on both ends. To un-
     * <p>
     * anchor the regex, use .*<regex>.*.
     */
    private String regex = "(.*)";

    /**
     * Modulus to take of the hash of the source label values.
     */
    private int modulus;

    /**
     * Replacement value against which a regex replace is performed if the
     * <p>
     * regular expression matches. Regex capture groups are available.
     */
    private String replacement = "$1";

    /**
     * Action to perform based on regex matching.
     */
    private Action action = Action.REPLACE;

    public enum Action {
        /**
         * Match regex against the concatenated source_labels. Then, set
         * <p>
         * target_label to replacement, with match group references (${1}, ${2}, ...) in
         * <p>
         * replacement substituted by their value. If regex does not match, no replacement takes place.
         */
        REPLACE,
        /**
         * Maps the concatenated source_labels to their lower case.
         */
        LOWERCASE,
        /**
         * Maps the concatenated source_labels to their upper case.
         */
        UPPERCASE,
        /**
         * Drop targets for which regex does not match the concatenated source_labels.
         */
        KEEP,
        /**
         * Drop targets for which regex matches the concatenated source_labels.
         */
        DROP,
        /**
         * Drop targets for which the concatenated source_labels do not match target_label.
         */
        KEEPEQUAL,
        /**
         * Drop targets for which the concatenated source_labels do match target_label.
         */
        DROPEQUAL,
        /**
         * Set target_label to the modulus of a hash of the concatenated source_labels.
         */
        HASHMOD,
        /**
         * Match regex against all source label names, not just those specified in
         * <p>
         * source_labels. Then copy the values of the matching labels to label names given by
         * <p>
         * replacement with match group references (${1}, ${2}, ...) in replacement
         * <p>
         * substituted by their value.
         */
        LABELMAP,
        /**
         * Match regex against all label names. Any label that matches will be removed from the set of labels.
         */
        LABELDROP,
        /**
         * Match regex against all label names. Any label that does not match will be removed from the set of labels.
         */
        LABELKEEP
    }

}
