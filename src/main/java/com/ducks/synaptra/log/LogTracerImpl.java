package com.ducks.synaptra.log;

import com.ducks.synaptra.log.logging.LoggingService;
import com.ducks.synaptra.log.serializer.JsonSerializer;
import com.ducks.synaptra.log.tracing.SpanManager;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect implementation for {@link LogTracer} annotation.
 *
 * <p>This aspect intercepts method executions annotated with {@link LogTracer} and provides:
 *
 * <ul>
 *   <li>Automatic span creation and management for distributed tracing
 *   <li>Method argument logging (JSON serialized)
 *   <li>Return value logging (JSON serialized)
 *   <li>Exception logging and span error tagging
 * </ul>
 *
 * <p>The aspect uses AOP (Aspect-Oriented Programming) to wrap method execution without requiring
 * changes to the target method implementation.
 *
 * <p>This implementation follows SOLID principles:
 *
 * <ul>
 *   <li>Single Responsibility: Delegates to specialized services
 *   <li>Dependency Inversion: Depends on abstractions (interfaces)
 *   <li>Open/Closed: Extensible through interface implementations
 * </ul>
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @see LogTracer
 * @since 1.0.0
 */
@Aspect
@Component
@RequiredArgsConstructor
public class LogTracerImpl {

  private static final String ARGS_EVENT_PREFIX = "args - ";
  private static final String OUTPUT_EVENT_PREFIX = "out - ";

  /** Service for JSON serialization of method arguments and return values. */
  private final JsonSerializer jsonSerializer;

  /** Service for managing OpenTelemetry spans. */
  private final SpanManager spanManager;

  /** Service for structured logging operations. */
  private final LoggingService loggingService;

  /** Tracer for managing span scope. */
  private final Tracer tracer;

  /**
   * Around advice that intercepts methods annotated with {@link LogTracer}.
   *
   * <p>This method:
   *
   * <ol>
   *   <li>Creates a new span with the configured span name
   *   <li>Logs method arguments if {@code logInput} is enabled
   *   <li>Executes the target method
   *   <li>Logs return value if {@code logOutput} is enabled
   *   <li>Handles exceptions by logging and tagging the span
   *   <li>Always ends the span in a finally block
   * </ol>
   *
   * @param pjp ProceedingJoinPoint providing access to method execution
   * @param logTracer the LogTracer annotation instance with configuration
   * @return the result of the method execution
   * @throws Throwable any exception thrown by the target method
   */
  @Around("@annotation(logTracer)")
  public Object around(ProceedingJoinPoint pjp, LogTracer logTracer) throws Throwable {
    Logger logger = LogManager.getLogger(pjp.getTarget().getClass());
    String spanName = logTracer.spanName();
    Span span = spanManager.createSpan(spanName);

    try (Tracer.SpanInScope ignored = tracer.withSpan(span)) {
      if (logTracer.logInput()) {
        String args = jsonSerializer.toJsonArray(pjp.getArgs());
        loggingService.logInput(logger, spanName, args);
        spanManager.addEvent(span, ARGS_EVENT_PREFIX + args);
      }

      try {
        Object result = pjp.proceed();

        if (logTracer.logOutput()) {
          String out = jsonSerializer.toJson(result);
          loggingService.logOutput(logger, spanName, out);
          spanManager.addEvent(span, OUTPUT_EVENT_PREFIX + out);
        }

        return result;

      } catch (Throwable ex) {
        loggingService.logError(logger, spanName, ex);
        spanManager.markError(span, ex);
        throw ex;
      }
    } finally {
      spanManager.endSpan(span);
    }
  }
}
