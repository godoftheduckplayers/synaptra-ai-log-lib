package com.ducks.synaptra.log.tracing;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * Micrometer-based implementation of {@link SpanManager}.
 *
 * <p>Uses Micrometer Tracing API to manage OpenTelemetry spans.
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @see SpanManager
 * @since 1.0.0
 */
@Component
public class MicrometerSpanManager implements SpanManager {

  private final Tracer tracer;

  /**
   * Creates a new MicrometerSpanManager with the provided Tracer.
   *
   * @param tracer Micrometer Tracer for span operations
   */
  public MicrometerSpanManager(Tracer tracer) {
    this.tracer = tracer;
  }

  /** {@inheritDoc} */
  @Override
  public Span createSpan(String spanName) {
    Objects.requireNonNull(spanName, "Span name cannot be null");

    Span parent = tracer.currentSpan();
    Span nextSpan = tracer.nextSpan(parent);

    if (nextSpan == null) {
      throw new IllegalStateException("Failed to create span: nextSpan returned null");
    }

    return nextSpan.name(spanName).start();
  }

  /** {@inheritDoc} */
  @Override
  public void addEvent(Span span, String eventName) {
    if (span != null && eventName != null) {
      span.event(eventName);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void markError(Span span, Throwable throwable) {
    if (span != null && throwable != null) {
      span.error(throwable);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void endSpan(Span span) {
    if (span != null) {
      span.end();
    }
  }
}
