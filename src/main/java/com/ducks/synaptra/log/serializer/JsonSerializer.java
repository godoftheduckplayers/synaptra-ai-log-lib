package com.ducks.synaptra.log.serializer;

/**
 * Interface for JSON serialization of objects.
 *
 * <p>Provides a safe way to serialize objects to JSON strings, with fallback handling for
 * serialization failures.
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @since 1.0.0
 */
public interface JsonSerializer {

  /**
   * Safely serializes an object to JSON string.
   *
   * <p>If serialization fails, returns a fallback string representation.
   *
   * @param obj the object to serialize (can be null)
   * @return JSON string representation or fallback string
   */
  String toJson(Object obj);

  /**
   * Safely serializes an array of objects to a JSON-like string.
   *
   * <p>Each object is serialized and joined into a comma-separated list enclosed in square
   * brackets.
   *
   * @param args the array of objects to serialize (can be null)
   * @return JSON-like string representation of the array
   */
  String toJsonArray(Object[] args);
}
