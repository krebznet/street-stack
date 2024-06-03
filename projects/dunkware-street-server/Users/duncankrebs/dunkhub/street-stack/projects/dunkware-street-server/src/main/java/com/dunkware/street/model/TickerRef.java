package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TickerRef
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class TickerRef {

  private Integer id;

  private String identifier;

  private Integer exchange;

  private String name;

  private Integer sessionCount;

  private Double baseVersion;

  public TickerRef() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TickerRef(Integer id, String identifier, Integer exchange, String name, Integer sessionCount, Double baseVersion) {
    this.id = id;
    this.identifier = identifier;
    this.exchange = exchange;
    this.name = name;
    this.sessionCount = sessionCount;
    this.baseVersion = baseVersion;
  }

  public TickerRef id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Numeric identifier for the ticker
   * @return id
  */
  @NotNull 
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public TickerRef identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  /**
   * String identifier for the ticker
   * @return identifier
  */
  @NotNull 
  @JsonProperty("identifier")
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public TickerRef exchange(Integer exchange) {
    this.exchange = exchange;
    return this;
  }

  /**
   * Numeric identifier for the exchange
   * @return exchange
  */
  @NotNull 
  @JsonProperty("exchange")
  public Integer getExchange() {
    return exchange;
  }

  public void setExchange(Integer exchange) {
    this.exchange = exchange;
  }

  public TickerRef name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the ticker
   * @return name
  */
  @NotNull 
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TickerRef sessionCount(Integer sessionCount) {
    this.sessionCount = sessionCount;
    return this;
  }

  /**
   * Number of sessions associated with the ticker
   * @return sessionCount
  */
  @NotNull 
  @JsonProperty("sessionCount")
  public Integer getSessionCount() {
    return sessionCount;
  }

  public void setSessionCount(Integer sessionCount) {
    this.sessionCount = sessionCount;
  }

  public TickerRef baseVersion(Double baseVersion) {
    this.baseVersion = baseVersion;
    return this;
  }

  /**
   * Base version of the ticker
   * @return baseVersion
  */
  @NotNull 
  @JsonProperty("baseVersion")
  public Double getBaseVersion() {
    return baseVersion;
  }

  public void setBaseVersion(Double baseVersion) {
    this.baseVersion = baseVersion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TickerRef tickerRef = (TickerRef) o;
    return Objects.equals(this.id, tickerRef.id) &&
        Objects.equals(this.identifier, tickerRef.identifier) &&
        Objects.equals(this.exchange, tickerRef.exchange) &&
        Objects.equals(this.name, tickerRef.name) &&
        Objects.equals(this.sessionCount, tickerRef.sessionCount) &&
        Objects.equals(this.baseVersion, tickerRef.baseVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, identifier, exchange, name, sessionCount, baseVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TickerRef {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    identifier: ").append(toIndentedString(identifier)).append("\n");
    sb.append("    exchange: ").append(toIndentedString(exchange)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    sessionCount: ").append(toIndentedString(sessionCount)).append("\n");
    sb.append("    baseVersion: ").append(toIndentedString(baseVersion)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

