package com.ducks.synaptra.log;

import java.lang.annotation.*;

/**
 * Annotation for enabling automatic logging and tracing on method execution.
 *
 * <p>When applied to a method, this annotation triggers:
 *
 * <ul>
 *   <li>Creation of an OpenTelemetry span for distributed tracing
 *   <li>Logging of method arguments (if enabled)
 *   <li>Logging of method return value (if enabled)
 *   <li>Error logging and span error tagging on exceptions
 * </ul>
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * @LogTracer(spanName = "processOrder", logInput = true, logOutput = true)
 * public Order processOrder(OrderRequest request) {
 *     // method implementation
 * }
 * }</pre>
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @see LogTracerImpl
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogTracer {

  /**
   * The name of the span to be created for this method.
   *
   * @return span name
   */
  String spanName();

  /**
   * Whether to log method input arguments.
   *
   * <p>Defaults to {@code true}. When enabled, method arguments are serialized to JSON and logged
   * at INFO level, and added as a span event.
   *
   * @return true if input logging is enabled, false otherwise
   */
  boolean logInput() default true;

  /**
   * Whether to log method return value.
   *
   * <p>Defaults to {@code false}. When enabled, the return value is serialized to JSON and logged
   * at INFO level, and added as a span event.
   *
   * @return true if output logging is enabled, false otherwise
   */
  boolean logOutput() default false;
}
