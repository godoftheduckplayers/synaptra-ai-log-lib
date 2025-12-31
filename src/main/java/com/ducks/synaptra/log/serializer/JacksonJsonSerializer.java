package com.ducks.synaptra.log.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Jackson-based implementation of {@link JsonSerializer}.
 *
 * <p>Uses Jackson ObjectMapper to serialize objects to JSON, with fallback to string representation
 * on serialization failures.
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @see JsonSerializer
 * @since 1.0.0
 */
@Component
public class JacksonJsonSerializer implements JsonSerializer {

  private static final String NULL_STRING = "null";
  private static final String EMPTY_ARRAY = "[]";
  private static final String ARRAY_START = "[";
  private static final String ARRAY_END = "]";
  private static final String ARRAY_SEPARATOR = ", ";

  private final ObjectMapper objectMapper;

  /**
   * Creates a new JacksonJsonSerializer with the provided ObjectMapper.
   *
   * @param objectMapper Jackson ObjectMapper for JSON serialization
   */
  public JacksonJsonSerializer(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Attempts to serialize using ObjectMapper. If serialization fails, returns a fallback string
   * using class name and toString().
   */
  @Override
  public String toJson(Object obj) {
    if (obj == null) {
      return NULL_STRING;
    }

    try {
      return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
      return createFallbackString(obj);
    }
  }

  /** {@inheritDoc} */
  @Override
  public String toJsonArray(Object[] args) {
    if (args == null) {
      return EMPTY_ARRAY;
    }

    return Arrays.stream(args)
        .map(this::toJson)
        .collect(Collectors.joining(ARRAY_SEPARATOR, ARRAY_START, ARRAY_END));
  }

  /**
   * Creates a fallback string representation when JSON serialization fails.
   *
   * @param obj the object that failed to serialize
   * @return fallback string representation
   */
  private String createFallbackString(Object obj) {
    return obj.getClass().getSimpleName() + "(" + obj + ")";
  }
}
