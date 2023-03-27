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
 * Describes Prometheus openstack_sd_config.
 *
 * @since  1.0.0
 * @see <a href="https://prometheus.io/docs/prometheus/latest/configuration/configuration/#openstack_sd_config">openstack_sd_config</a>
 * @author Pavel Bodiachevskii
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenstackSdConfig {

    /**
     * The OpenStack role of entities that should be discovered.
     */
    private Role role;

    /**
     * The OpenStack Region.
     */
    private String region;

    /**
     * identity_endpoint specifies the HTTP endpoint that is required to work with
     * <p>
     * the Identity API of the appropriate version. While it's ultimately needed by
     * <p>
     * all of the identity services, it will often be populated by a provider-level
     * <p>
     * function.
     */
    private String identity_endpoint;

    /**
     * username is required if using Identity V2 API. Consult with your provider's
     * <p>
     * control panel to discover your account's username.
     */
    private String username;

    /**
     * In Identity V3, either userid or a combination of {@link #username} and {@link #domain_id} or {@link #domain_name} are needed.
     */
    private String userid;

    /**
     * password for the Identity V2 and V3 APIs. Consult with your provider's
     * <p>
     * control panel to discover your account's preferred method of authentication.
     */
    private String password;

    /**
     * At most one of {@link #domain_id} and domain_name must be provided if using {@link #username}
     * <p>
     * with Identity V3. Otherwise, either are optional.
     */
    private String domain_name;

    /**
     * At most one of domain_id and {@link #domain_name} must be provided if using {@link #username}
     * <p>
     * with Identity V3. Otherwise, either are optional.
     */
    private String domain_id;

    /**
     * The {@link #project_id} and project_name fields are optional for the Identity V2 API.
     * <p>
     * Some providers allow you to specify a project_name instead of the {@link #project_id}.
     * <p>
     * Some require both. Your provider's authentication policies will determine
     * <p>
     * how these fields influence authentication.
     */
    private String project_name;

    /**
     * The project_id and {@link #project_name} fields are optional for the Identity V2 API.
     * <p>
     * Some providers allow you to specify a {@link #project_name} instead of the project_id.
     * <p>
     * Some require both. Your provider's authentication policies will determine
     * <p>
     * how these fields influence authentication.
     */
    private String project_id;

    /**
     * The {@link #application_credential_id} or application_credential_name fields are
     * <p>
     * required if using an application credential to authenticate. Some providers
     * <p>
     * allow you to create an application credential to authenticate rather than a
     * <p>
     * password.
     */
    private String application_credential_name;

    /**
     * The application_credential_id or {@link #application_credential_name} fields are
     * <p>
     * required if using an application credential to authenticate. Some providers
     * <p>
     * allow you to create an application credential to authenticate rather than a
     * <p>
     * password.
     */
    private String application_credential_id;

    /**
     * The application_credential_secret field is required if using an application
     * <p>
     * credential to authenticate.
     */
    private String application_credential_secret;

    /**
     * Whether the service discovery should list all instances for all projects.
     * <p>
     * It is only relevant for the 'instance' role and usually requires admin permissions.
     */
    private boolean all_tenants = false;

    /**
     * Refresh interval to re-read the instance list.
     */
    @Pattern(regexp = "((([0-9]+)y)?(([0-9]+)w)?(([0-9]+)d)?(([0-9]+)h)?(([0-9]+)m)?(([0-9]+)s)?(([0-9]+)ms)?|0)")
    private String refresh_interval = "60s";

    /**
     * The port to scrape metrics from. If using the public IP address, this must
     * <p>
     * instead be specified in the relabeling rule.
     */
    private int port = 80;

    /**
     * The availability of the endpoint to connect to. Must be one of public, admin or internal.
     */
    private Availability availability = Availability.PUBLIC;

    /**
     * TLS configuration.
     */
    private PrometheusTlsConfig tls_config;

    public enum Role {
        HYPERVISOR,
        INSTANCE
    }

    public enum Availability {
        PUBLIC,
        ADMIN,
        INTERNAL
    }

}
