package com.ducks.synaptra.config;

import com.ducks.synaptra.properties.SynaptraLogProperties;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenTelemetry distributed tracing.
 *
 * <p>This configuration sets up OpenTelemetry SDK with:
 *
 * <ul>
 *   <li>OTLP HTTP span exporter for sending traces to Jaeger or compatible backends
 *   <li>Resource attributes including service name
 *   <li>Batch span processor for efficient trace export
 *   <li>Tracer instance for creating spans in application code
 * </ul>
 *
 * <p>Configuration properties are read from {@link SynaptraLogProperties}:
 *
 * <ul>
 *   <li>{@code synaptra.logging.jaegerEndpoint} - OTLP endpoint URL
 *   <li>{@code synaptra.logging.serviceName} - Service name for resource attributes
 *   <li>{@code synaptra.logging.scopeName} - Tracer scope/instrumentation name
 * </ul>
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @see SynaptraLogProperties
 * @since 1.0.0
 */
@Configuration
public class TracingConfig {

  /** OpenTelemetry resource attribute key for service name. */
  private static final String SERVICE_NAME = "service.name";

  /**
   * Creates and configures the OpenTelemetry SDK instance.
   *
   * <p>Sets up:
   *
   * <ul>
   *   <li>OTLP HTTP exporter pointing to the configured Jaeger endpoint
   *   <li>Resource with service name from properties
   *   <li>Batch span processor for efficient trace export
   * </ul>
   *
   * @param props configuration properties containing Jaeger endpoint and service name
   * @return configured OpenTelemetry instance
   */
  @Bean
  public OpenTelemetry openTelemetry(SynaptraLogProperties props) {
    OtlpHttpSpanExporter exporter =
        OtlpHttpSpanExporter.builder().setEndpoint(props.getJaegerEndpoint()).build();

    Resource resource =
        Resource.getDefault()
            .merge(
                Resource.create(
                    Attributes.of(AttributeKey.stringKey(SERVICE_NAME), props.getServiceName())));

    SdkTracerProvider tracerProvider =
        SdkTracerProvider.builder()
            .setResource(resource)
            .addSpanProcessor(BatchSpanProcessor.builder(exporter).build())
            .build();

    return OpenTelemetrySdk.builder().setTracerProvider(tracerProvider).build();
  }

  /**
   * Creates a Tracer instance for creating spans.
   *
   * <p>The tracer uses the scope name from configuration properties as the instrumentation library
   * name, which helps identify the source of spans in trace visualization tools.
   *
   * @param otel OpenTelemetry instance
   * @param props configuration properties containing scope name
   * @return configured Tracer instance
   */
  @Bean
  public Tracer synaptraTracer(OpenTelemetry otel, SynaptraLogProperties props) {
    return otel.getTracer(props.getScopeName());
  }
}
