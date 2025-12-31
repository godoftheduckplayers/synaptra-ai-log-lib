package com.ducks.synaptra.log.tracing;

import io.micrometer.tracing.Span;

/**
 * Interface for managing OpenTelemetry spans.
 *
 * <p>Provides operations for creating, managing, and ending spans in a distributed tracing context.
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @since 1.0.0
 */
public interface SpanManager {

  /**
   * Creates and starts a new span with the given name.
   *
   * <p>The new span will be a child of the current active span, if one exists.
   *
   * @param spanName the name of the span
   * @return a new started span
   * @throws IllegalStateException if span creation fails
   */
  Span createSpan(String spanName);

  /**
   * Adds an event to the span.
   *
   * @param span the span to add the event to
   * @param eventName the name/description of the event
   */
  void addEvent(Span span, String eventName);

  /**
   * Marks the span with an error.
   *
   * @param span the span to mark as error
   * @param throwable the exception that caused the error
   */
  void markError(Span span, Throwable throwable);

  /**
   * Ends the span.
   *
   * @param span the span to end
   */
  void endSpan(Span span);

  /** Functional interface for code that may throw exceptions. */
  @FunctionalInterface
  interface ThrowingRunnable {
    void run() throws Throwable;
  }
}
