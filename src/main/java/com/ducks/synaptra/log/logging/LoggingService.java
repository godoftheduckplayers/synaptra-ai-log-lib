package com.ducks.synaptra.log.logging;

import org.apache.logging.log4j.Logger;

/**
 * Interface for logging operations in the context of method tracing.
 *
 * <p>Provides structured logging for method execution, including input arguments, output values,
 * and errors.
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @since 1.0.0
 */
public interface LoggingService {

  /**
   * Logs method input arguments.
   *
   * @param logger the logger instance to use
   * @param spanName the name of the span/method being logged
   * @param arguments the serialized arguments string
   */
  void logInput(Logger logger, String spanName, String arguments);

  /**
   * Logs method output/return value.
   *
   * @param logger the logger instance to use
   * @param spanName the name of the span/method being logged
   * @param output the serialized output string
   */
  void logOutput(Logger logger, String spanName, String output);

  /**
   * Logs an error that occurred during method execution.
   *
   * @param logger the logger instance to use
   * @param spanName the name of the span/method being logged
   * @param throwable the exception that occurred
   */
  void logError(Logger logger, String spanName, Throwable throwable);
}
