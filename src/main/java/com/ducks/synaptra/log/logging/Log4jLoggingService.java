package com.ducks.synaptra.log.logging;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Log4j-based implementation of {@link LoggingService}.
 *
 * <p>Uses Log4j Logger for structured logging with consistent format.
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @see LoggingService
 * @since 1.0.0
 */
@Component
public class Log4jLoggingService implements LoggingService {

  private static final String INPUT_LOG_FORMAT = "[{}] args={}";
  private static final String OUTPUT_LOG_FORMAT = "[{}] out={}";
  private static final String ERROR_LOG_FORMAT = "[{}] error={}";

  /** {@inheritDoc} */
  @Override
  public void logInput(Logger logger, String spanName, String arguments) {
    if (logger != null && spanName != null) {
      logger.info(INPUT_LOG_FORMAT, spanName, arguments);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void logOutput(Logger logger, String spanName, String output) {
    if (logger != null && spanName != null) {
      logger.info(OUTPUT_LOG_FORMAT, spanName, output);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void logError(Logger logger, String spanName, Throwable throwable) {
    if (logger != null && spanName != null && throwable != null) {
      logger.error(ERROR_LOG_FORMAT, spanName, throwable);
    }
  }
}
