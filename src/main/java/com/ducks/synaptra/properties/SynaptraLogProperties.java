package com.ducks.synaptra.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Configuration properties for Synaptra logging and tracing functionality.
 *
 * <p>These properties are bound from application configuration files (e.g., application.yml) using
 * the prefix {@code synaptra.logging}.
 *
 * <p>Example configuration:
 *
 * <pre>
 * synaptra:
 *   logging:
 *     jaegerEndpoint: <a href="http://localhost:4318/v1/traces">...</a>
 *     serviceName: my-service
 *     scopeName: com.example.myservice
 * </pre>
 *
 * <p>All properties are validated to ensure they are not null or empty.
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @since 1.0.0
 */
@Setter
@Getter
@Validated
@ConfigurationProperties(prefix = "synaptra.logging")
public class SynaptraLogProperties {

  /**
   * OTLP HTTP endpoint URL for Jaeger or compatible trace collector.
   *
   * <p>Example: {@code http://localhost:4318/v1/traces}
   */
  @NotBlank(message = "Jaeger endpoint cannot be null or empty")
  private String jaegerEndpoint;

  /**
   * Service name to be used in OpenTelemetry resource attributes.
   *
   * <p>This name appears in trace visualization tools to identify the service that generated the
   * traces.
   */
  @NotBlank(message = "Service name cannot be null or empty")
  private String serviceName;

  /**
   * Tracer scope/instrumentation library name.
   *
   * <p>This name is used as the instrumentation library name when creating spans, helping to
   * identify the source of instrumentation in trace tools.
   */
  @NotBlank(message = "Scope name cannot be null or empty")
  private String scopeName;
}
