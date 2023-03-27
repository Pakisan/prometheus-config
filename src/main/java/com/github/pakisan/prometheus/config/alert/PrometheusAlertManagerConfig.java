package com.github.pakisan.prometheus.config.alert;

import com.github.pakisan.prometheus.config.PrometheusProtocolScheme;
import com.github.pakisan.prometheus.config.PrometheusRelabelConfig;
import com.github.pakisan.prometheus.config.PrometheusStaticConfig;
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
import com.github.pakisan.prometheus.config.servicediscovery.OpenstackSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.OvhcloudSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.PuppetdbSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.ScalewaySdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.ServersetSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.TritonSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.UyuniSdConfig;
import com.github.pakisan.prometheus.config.servicediscovery.VultrSdConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * Describes Prometheus alerting.
 * <p>
 * An alertmanager_config section specifies Alertmanager instances the Prometheus server sends alerts to.
 * It also provides parameters to configure how to communicate with these Alertmanagers.
 * <p>
 * Alertmanagers may be statically configured via the static_configs parameter or dynamically discovered
 * using one of the supported service-discovery mechanisms.
 * <p>
 * Additionally, relabel_configs allow selecting Alertmanagers from discovered entities and provide
 * advanced modifications to the used API path, which is exposed through the __alerts_path__ label.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#alertmanager_config">alertmanager_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrometheusAlertManagerConfig {

    /**
     * Per-target Alertmanager timeout when pushing alerts.
     */
    @Builder.Default
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String timeout = "10s";

    /**
     * The api version of Alertmanager.
     */
    @Builder.Default
    private String api_version = "v2";

    /**
     * Prefix for the HTTP path alerts are pushed to.
     */
    @Builder.Default
    private String path_prefix = "/";

    /**
     * Configures the protocol scheme used for requests.
     */
    @Builder.Default
    private PrometheusProtocolScheme scheme = PrometheusProtocolScheme.HTTP;

    /**
     * Sets the `Authorization` header on every request with the
     * <p>
     * configured username and password.
     * <p>
     * password and password_file are mutually exclusive.
     */
    private PrometheusBasicAuth basic_auth;

    /**
     * Optional `Authorization` header configuration.
     */
    private PrometheusAuthorization authorization;

    /**
     * Optional OAuth 2.0 configuration.
     * <p>
     * Cannot be used at the same time as basic_auth or authorization.
     */
    private PrometheusOAuth2 oauth2;

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
     * <p>
     * that should be excluded from proxying. IP and domain names can
     * <p>
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
     * Configure whether HTTP requests follow HTTP 3xx redirects.
     */
    private boolean follow_redirects = true;

    /**
     * Whether to enable HTTP2.
     */
    private boolean enable_http2 = true;

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
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#openstack_sd_config">openstack_sd_config</a>
     */
    private List<OpenstackSdConfig> openstack_sd_configs;

    /**
     * List of OVHcloud service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#ovhcloud_sd_config">ovhcloud_sd_config</a>
     */
    private List<OvhcloudSdConfig> ovhcloud_sd_configs;

    /**
     * List of PuppetDB service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#puppetdb_sd_config">puppetdb_sd_config</a>
     */
    private List<PuppetdbSdConfig> puppetdb_sd_configs;

    /**
     * List of Scaleway service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#scaleway_sd_config">scaleway_sd_config</a>
     */
    private List<ScalewaySdConfig> scaleway_sd_configs;

    /**
     * List of Zookeeper Serverset service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#serverset_sd_config">serverset_sd_config</a>
     */
    private List<ServersetSdConfig> serverset_sd_configs;

    /**
     * List of Triton service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#triton_sd_config">triton_sd_config</a>
     */
    private List<TritonSdConfig> triton_sd_configs;

    /**
     * List of Uyuni service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#uyuni_sd_config">uyuni_sd_config</a>
     */
    private List<UyuniSdConfig> uyuni_sd_configs;

    /**
     * List of Vultr service discovery configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#vultr_sd_config">vultr_sd_config</a>
     */
    private List<VultrSdConfig> vultr_sd_configs;

    /**
     * List of labeled statically configured targets for this job.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#static_config">static_config</a>
     */
    private List<PrometheusStaticConfig> static_configs;

    /**
     * List of target relabel configurations.
     *
     * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#relabel_config">relabel_config</a>
     */
    private List<PrometheusRelabelConfig> relabel_configs;

}
