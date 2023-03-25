package com.github.pakisan.prometheus.config.servicediscovery;

import com.github.pakisan.prometheus.config.PrometheusTlsConfig;
import com.github.pakisan.prometheus.config.auth.PrometheusAuthorization;
import com.github.pakisan.prometheus.config.auth.PrometheusBasicAuth;
import com.github.pakisan.prometheus.config.auth.PrometheusOAuth2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * Describes Prometheus kubernetes_sd_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#kubernetes_sd_config">kubernetes_sd_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KubernetesSdConfig {

    /**
     * The API server addresses. If left empty, Prometheus is assumed to run inside
     * <p>
     * of the cluster and will discover API servers automatically and use the pod's
     * <p>
     * CA certificate and bearer token file at /var/run/secrets/kubernetes.io/serviceaccount/.
     */
    private String api_server;

    /**
     * The Kubernetes role of entities that should be discovered.
     * <p>
     * One of endpoints, endpointslice, service, pod, node, or ingress.
     */
    private Role role;

    /**
     * Optional path to a kubeconfig file.
     * <p>
     * Note that api_server and kube_config are mutually exclusive.
     */
    private String kubeconfig_file;

    /**
     * Optional namespace discovery. If omitted, all namespaces are used.
     */
    private Namespaces namespaces;

    /**
     * Optional label and field selectors to limit the discovery process to a subset of available resources.
     * See
     * <ul>
     *     <li><a href="https://kubernetes.io/docs/concepts/overview/working-with-objects/field-selectors/">Field Selectors</a></li>
     *     <li><a href="https://kubernetes.io/docs/concepts/overview/working-with-objects/labels/">Labels</a></li>
     * </ul>
     * to learn more about the possible filters that can be used.
     * <p>
     * The endpoints role supports pod, service and endpoints selectors.
     * <p>
     * The pod role supports node selectors when configured with `attach_metadata: {node: true}`.
     * <p>
     * Other roles only support selectors matching the role itself (e.g. node role can only contain node selectors).
     * <p>
     * <p>
     * <b>Note:</b> When making decision about using field/label selector make sure that this
     * <p>
     * is the best approach - it will prevent Prometheus from reusing single list/watch
     * <p>
     * for all scrape configs. This might result in a bigger load on the Kubernetes API,
     * <p>
     * because per each selector combination there will be additional LIST/WATCH. On the other hand,
     * <p>
     * if you just want to monitor small subset of pods in large cluster it's recommended to use selectors.
     * <p>
     * Decision, if selectors should be used or not depends on the particular situation.
     */
    private List<Selector> selectors;

    /**
     * Optional metadata to attach to discovered targets. If omitted, no additional metadata is attached.
     */
    private MetadataConfig attach_metadata;

    /**
     * Optional HTTP basic authentication information, currently not support by Azure.
     */
    private PrometheusBasicAuth basic_auth;

    /**
     * Optional `Authorization` header configuration, currently not supported by Azure.
     */
    private PrometheusAuthorization authorization;

    /**
     * Optional OAuth 2.0 configuration, currently not supported by Azure.
     */
    private PrometheusOAuth2 oauth2;

    /**
     * Optional proxy URL.
     */
    private String proxy_url;

    /**
     * Comma-separated string that can contain IPs, CIDR notation, domain names
     * <p>
     * that should be excluded from proxying. IP and domain names can
     * <p>
     * contain port numbers.
     */
    private String no_proxy;

    /**
     * Use proxy URL indicated by environment variables (HTTP_PROXY, https_proxy, HTTPs_PROXY, https_proxy, and no_proxy)
     */
    @Builder.Default
    private boolean proxy_from_environment = false;

    /**
     * Specifies headers to send to proxies during CONNECT requests.
     */
    private Map<String, List<String>> proxy_connect_header;

    /**
     * Configure whether HTTP requests follow HTTP 3xx redirects.
     */
    @Builder.Default
    private boolean follow_redirects = true;

    /**
     * Whether to enable HTTP2.
     */
    @Builder.Default
    private boolean enable_http2 = true;

    /**
     * TLS configuration.
     */
    private PrometheusTlsConfig tls_config;

    public enum Role {
        ENDPOINTS,
        ENDPOINTSLICE,
        SERVICE,
        POD,
        NODE,
        INGRESS
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Namespaces {

        private boolean own_namespace;

        private List<String> names;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Selector {

        private String role;

        private String label;

        private String field;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetadataConfig {

        /**
         * Attaches node metadata to discovered targets. Valid for roles: pod, endpoints, endpointslice.
         * When set to true, Prometheus must have permissions to get Nodes.
         */
        private boolean node = false;

    }

}
