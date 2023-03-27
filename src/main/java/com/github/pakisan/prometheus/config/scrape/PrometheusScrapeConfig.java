package com.github.pakisan.prometheus.config.scrape;

import com.github.pakisan.prometheus.config.PrometheusProtocolScheme;
import com.github.pakisan.prometheus.config.PrometheusTlsConfig;
import com.github.pakisan.prometheus.config.auth.PrometheusAuthorization;
import com.github.pakisan.prometheus.config.auth.PrometheusBasicAuth;
import com.github.pakisan.prometheus.config.auth.PrometheusOAuth2;
import com.github.pakisan.prometheus.config.servicediscovery.AzureSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.ConsulSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.DigitaloceanSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.DnsSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.DockerSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.DockerSwarmSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.Ec2SdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.EurekaSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.FileSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.GceSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.HetznerSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.HttpSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.IonosSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.KubernetesSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.KumaSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.LightsailSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.LinodeSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.MarathonSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.NerveSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.NomadSdConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * Describes Prometheus scrape_config section.
 * <p>
 * A scrape_config section specifies a set of targets and parameters describing how to scrape them.
 * In the general case, one scrape configuration specifies a single job. In advanced configurations, this may change.
 * <p>
 * Targets may be statically configured via the static_configs parameter or dynamically discovered using one of the
 * supported service-discovery mechanisms.
 * <p>
 * Additionally, relabel_configs allow advanced modifications to any target and its labels before scraping.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#scrape_config">scrape_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusScrapeConfig {

    /**
     * The job name assigned to scraped metrics by default.
     */
    private String job_name;

    /**
     * How frequently to scrape targets from this job.
     * <p>
     * default = &lt;global_config.scrape_interval&gt;
     */
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String scrape_interval;

    /**
     * Per-scrape timeout when scraping this job.
     * <p>
     * default = &lt;global_config.scrape_timeout&gt;
     */
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String scrape_timeout;

    /**
     * The HTTP resource path on which to fetch metrics from targets.
     */
    @Builder.Default
    private String metrics_path = "/metrics";

    /**
     * honor_labels controls how Prometheus handles conflicts between labels that are
     * already present in scraped data and labels that Prometheus would attach
     * server-side ("job" and "instance" labels, manually configured target
     * labels, and labels generated by service discovery implementations).
     * <p>
     * If honor_labels is set to "true", label conflicts are resolved by keeping label
     * values from the scraped data and ignoring the conflicting server-side labels.
     * <p>
     * If honor_labels is set to "false", label conflicts are resolved by renaming
     * conflicting labels in the scraped data to "exported_<original-label>" (for
     * example "exported_instance", "exported_job") and then attaching server-side
     * labels.
     * <p>
     * Setting honor_labels to "true" is useful for use cases such as federation and
     * scraping the Pushgateway, where all labels specified in the target should be
     * preserved.
     * <p>
     * Note that any globally configured "external_labels" are unaffected by this
     * setting. In communication with external systems, they are always applied only
     * when a time series does not have a given label yet and are ignored otherwise.
     */
    private boolean honor_labels = false;

    /**
     * honor_timestamps controls whether Prometheus respects the timestamps present
     * in scraped data.
     * <p>
     * If honor_timestamps is set to "true", the timestamps of the metrics exposed
     * by the target will be used.
     * <p>
     * If honor_timestamps is set to "false", the timestamps of the metrics exposed
     * by the target will be ignored.
     */
    private boolean honor_timestamps = true;

    /**
     * Configures the protocol scheme used for requests.
     */
    private PrometheusProtocolScheme scheme = PrometheusProtocolScheme.HTTP;

    /**
     * Optional HTTP URL parameters.
     */
    private Map<String, List<String>> params;

    /**
     * Sets the `Authorization` header on every scrape request with the
     * configured username and password.
     * password and password_file are mutually exclusive.
     */
    private PrometheusBasicAuth basic_auth;

    /**
     * Sets the `Authorization` header on every scrape request with
     * the configured credentials.
     */
    private PrometheusAuthorization authorization;

    /**
     * Optional OAuth 2.0 configuration.
     * <p>
     * Cannot be used at the same time as basic_auth or authorization.
     */
    private PrometheusOAuth2 oauth2;

    /**
     * Configure whether scrape requests follow HTTP 3xx redirects.
     */
    private boolean follow_redirects = true;

    /**
     * Whether to enable HTTP2.
     */
    private boolean enable_http2 = true;

    /**
     * Configures the scrape request's TLS settings.
     */
    private PrometheusTlsConfig tls_config;

    /**
     * Optional proxy URL.
     */
    private String proxy_url;

    /**
     * Comma-separated string that can contain IPs, CIDR notation, domain names
     * that should be excluded from proxying. IP and domain names can
     * contain port numbers.
     */
    private String no_proxy;

    /**
     * Use proxy URL indicated by environment variables (HTTP_PROXY, https_proxy, HTTPs_PROXY, https_proxy, and no_proxy)
     */
    private boolean proxy_from_environment = false;

    /**
     * Specifies headers to send to proxies during CONNECT requests.
     */
    private Map<String, List<String>> proxy_connect_header;

    /**
     * List of Azure service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#azure_sd_config">azure_sd_config</a>
     */
    private List<AzureSdConfig> azure_sd_configs;

    /**
     * List of Consul service discovery configurations.
     * <p>
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#consul_sd_config">consul_sd_config</a>
     */
    private List<ConsulSdConfig> consul_sd_configs;

    /**
     * List of DigitalOcean service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#digitalocean_sd_config">digitalocean_sd_config</a>
     */
    private List<DigitaloceanSdConfig> digitalocean_sd_configs;

    /**
     * List of Docker service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#docker_sd_config">docker_sd_config</a>
     */
    private List<DockerSdConfig> docker_sd_configs;

    /**
     * List of Docker Swarm service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#dockerswarm_sd_config">dockerswarm_sd_config</a>
     */
    private List<DockerSwarmSdConfig> dockerswarm_sd_configs;

    /**
     * List of DNS service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#dns_sd_config">dns_sd_config</a>
     */
    private List<DnsSdConfig> dns_sd_configs;

    /**
     * List of EC2 service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#ec2_sd_config">ec2_sd_config</a>
     */
    private List<Ec2SdConfig> ec2_sd_configs;

    /**
     * List of Eureka service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#eureka_sd_config">eureka_sd_config</a>
     */
    private List<EurekaSdConfig> eureka_sd_configs;

    /**
     * List of file service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#file_sd_config">file_sd_config</a>
     */
    private List<FileSdConfig> file_sd_configs;

    /**
     * List of GCE service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#gce_sd_config">gce_sd_config</a>
     */
    private List<GceSdConfig> gce_sd_configs;

    /**
     * List of Hetzner service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#hetzner_sd_config">hetzner_sd_config</a>
     */
    private List<HetznerSdConfig> hetzner_sd_configs;

    /**
     * List of HTTP service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#http_sd_config">http_sd_config</a>
     */
    private List<HttpSdConfig> http_sd_configs;

    /**
     * List of IONOS service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#ionos_sd_config">ionos_sd_config</a>
     */
    private List<IonosSdConfig> ionos_sd_configs;

    /**
     * List of Kubernetes service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#kubernetes_sd_config">kubernetes_sd_config</a>
     */
    private List<KubernetesSdConfig> kubernetes_sd_configs;

    /**
     * List of Kuma service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#kuma_sd_config">kuma_sd_config</a>
     */
    private List<KumaSdConfig> kuma_sd_configs;

    /**
     * List of Lightsail service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#lightsail_sd_config">lightsail_sd_config</a>
     */
    private List<LightsailSdConfig> lightsail_sd_configs;

    /**
     * List of Linode service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#linode_sd_config">linode_sd_config</a>
     */
    private List<LinodeSdConfig> linode_sd_configs;

    /**
     * List of Marathon service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#marathon_sd_config">marathon_sd_config</a>
     */
    private List<MarathonSdConfig> marathon_sd_configs;

    /**
     * List of AirBnB's Nerve service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#nerve_sd_config">nerve_sd_config</a>
     */
    private List<NerveSdConfig> nerve_sd_configs;

    /**
     * List of Nomad service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#nomad_sd_config">nomad_sd_config</a>
     */
    private List<NomadSdConfig> nomad_sd_configs;

    /**
     * List of OpenStack service discovery configurations.
     * <p>
     * TODO: implement
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#openstack_sd_config">openstack_sd_config</a>
     */
    private List<Object> openstack_sd_configs;

    /**
     * List of OVHcloud service discovery configurations.
     * <p>
     * TODO: implement
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#ovhcloud_sd_config">ovhcloud_sd_config</a>
     */
    private List<Object> ovhcloud_sd_configs;

    /**
     * List of PuppetDB service discovery configurations.
     * <p>
     * TODO: implement
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#puppetdb_sd_config">puppetdb_sd_config</a>
     */
    private List<Object> puppetdb_sd_configs;

    /**
     * List of Scaleway service discovery configurations.
     * <p>
     * TODO: implement
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#scaleway_sd_config">scaleway_sd_config</a>
     */
    private List<Object> scaleway_sd_configs;

    /**
     * List of Zookeeper Serverset service discovery configurations.
     * <p>
     * TODO: implement
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#serverset_sd_config">serverset_sd_config</a>
     */
    private List<Object> serverset_sd_configs;

    /**
     * List of Triton service discovery configurations.
     * <p>
     * TODO: implement
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#triton_sd_config">triton_sd_config</a>
     */
    private List<Object> triton_sd_configs;

    /**
     * List of Uyuni service discovery configurations.
     * <p>
     * TODO: implement
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#uyuni_sd_config">uyuni_sd_config</a>
     */
    private List<Object> uyuni_sd_configs;

    /**
     * List of labeled statically configured targets for this job.
     * <p>
     * TODO: implement
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#static_config">static_config</a>
     */
    private List<Object> static_configs;

    /**
     * List of target relabel configurations.
     * <p>
     * TODO: implement
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#relabel_config">relabel_config</a>
     */
    private List<Object> relabel_configs;

    /**
     * List of metric relabel configurations.
     * <p>
     * TODO: implement
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#relabel_config">relabel_config</a>
     */
    private List<Object> metric_relabel_configs;

    /**
     * An uncompressed response body larger than this many bytes will cause the
     * <p>
     * scrape to fail. 0 means no limit. Example: 100MB.
     * <p>
     * This is an experimental feature, this behaviour could
     * <p>
     * change or be removed in the future.
     */
    @Pattern(regexp = "\\d+B|\\d+KB|\\d+MB|\\d+GB|\\d+TB|\\d+PB|\\d+EB")
    private String body_size_limit = "0";

    /**
     * Per-scrape limit on number of scraped samples that will be accepted.
     * <p>
     * If more than this number of samples are present after metric relabeling
     * <p>
     * the entire scrape will be treated as failed. 0 means no limit.
     */
    private int sample_limit = 0;

    /**
     * Per-scrape limit on number of labels that will be accepted for a sample. If
     * <p>
     * more than this number of labels are present post metric-relabeling, the
     * <p>
     * entire scrape will be treated as failed. 0 means no limit.
     */
    private int label_limit = 0;

    /**
     * Per-scrape limit on length of labels name that will be accepted for a sample.
     * <p>
     * If a label name is longer than this number post metric-relabeling, the entire
     * <p>
     * scrape will be treated as failed. 0 means no limit.
     */
    private int label_name_length_limit = 0;

    /**
     * Per-scrape limit on length of labels value that will be accepted for a sample.
     * <p>
     * If a label value is longer than this number post metric-relabeling, the
     * <p>
     * entire scrape will be treated as failed. 0 means no limit.
     */
    private int label_value_length_limit = 0;

    /**
     * Per-scrape config limit on number of unique targets that will be
     * <p>
     * accepted. If more than this number of targets are present after target
     * <p>
     * relabeling, Prometheus will mark the targets as failed without scraping them.
     * <p>
     * 0 means no limit. This is an experimental feature, this behaviour could
     * <p>
     * change in the future.
     */
    private int target_limit = 0;

}
