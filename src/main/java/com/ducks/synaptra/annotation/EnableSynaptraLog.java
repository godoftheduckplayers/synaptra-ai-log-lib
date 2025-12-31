package com.ducks.synaptra.annotation;

import com.ducks.synaptra.config.SynaptraLogAutoConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * Enables Synaptra logging and tracing functionality in Spring Boot applications.
 *
 * <p>This annotation should be placed on a configuration class or the main application class to
 * activate automatic configuration of logging and tracing components.
 *
 * <p>When enabled, the library provides:
 *
 * <ul>
 *   <li>Automatic configuration of OpenTelemetry tracing
 *   <li>LogTracer aspect for method-level logging and tracing
 *   <li>ObjectMapper configuration for JSON serialization
 * </ul>
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * @SpringBootApplication
 * @EnableSynaptraLog
 * public class Application {
 *     public static void main(String[] args) {
 *         SpringApplication.run(Application.class, args);
 *     }
 * }
 * }</pre>
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @see SynaptraLogAutoConfiguration
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SynaptraLogAutoConfiguration.class)
public @interface EnableSynaptraLog {}
