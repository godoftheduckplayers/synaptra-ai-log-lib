package com.ducks.synaptra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Jackson ObjectMapper bean.
 *
 * <p>This configuration provides a customized ObjectMapper instance with:
 *
 * <ul>
 *   <li>Automatic module discovery and registration (Java 8 time modules, etc.)
 *   <li>Disabled failure on empty beans to prevent serialization errors
 * </ul>
 *
 * <p>The ObjectMapper is used by the LogTracer aspect to serialize method arguments and return
 * values to JSON format for logging purposes.
 *
 * @author Leandro Marques
 * @version 1.0.0
 * @see ObjectMapper
 * @since 1.0.0
 */
@Configuration
public class ObjectMapperConfig {

  /**
   * Creates and configures a Jackson ObjectMapper bean.
   *
   * <p>The ObjectMapper is configured to:
   *
   * <ul>
   *   <li>Automatically find and register Jackson modules (e.g., JavaTimeModule)
   *   <li>Disable serialization failure on empty beans
   * </ul>
   *
   * @return configured ObjectMapper instance
   */
  @Bean
  ObjectMapper objectMapper() {
    ObjectMapper om = new ObjectMapper();
    om.findAndRegisterModules();
    om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    return om;
  }
}
