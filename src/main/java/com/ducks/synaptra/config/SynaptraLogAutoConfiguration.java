package com.ducks.synaptra.config;

import com.ducks.synaptra.annotation.EnableSynaptraLog;
import com.ducks.synaptra.properties.SynaptraLogProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Auto-configuration class for Synaptra logging and tracing functionality.
 *
 * <p>This class is automatically imported when {@link EnableSynaptraLog} annotation is present. It
 * enables configuration properties binding and activates all related components for logging and
 * distributed tracing.
 *
 * <p>The configuration includes:
 *
 * <ul>
 *   <li>Configuration properties binding for SynaptraLogProperties
 *   <li>OpenTelemetry tracing setup (via TracingConfig)
 *   <li>ObjectMapper bean configuration (via ObjectMapperConfig)
 *   <li>LogTracer aspect for method-level instrumentation (via LogTracerImpl)
 * </ul>
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @see EnableSynaptraLog
 * @see SynaptraLogProperties
 * @since 1.0.0
 */
@AutoConfiguration
@EnableConfigurationProperties(SynaptraLogProperties.class)
public class SynaptraLogAutoConfiguration {}
