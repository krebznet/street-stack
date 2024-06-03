package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.dunkware.street.model.GenDate;
import com.dunkware.street.model.GenTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ExchangeSession
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class ExchangeSession {

  private Integer id;

  private Integer exchangeId;

  private GenTime startTime;

  private GenTime stopTime;

  private GenDate date;

  private Integer entityCount;

  private Double version;

  public ExchangeSession() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ExchangeSession(Integer id, Integer exchangeId, GenTime startTime, GenTime stopTime, GenDate date, Integer entityCount, Double version) {
    this.id = id;
    this.exchangeId = exchangeId;
    this.startTime = startTime;
    this.stopTime = stopTime;
    this.date = date;
    this.entityCount = entityCount;
    this.version = version;
  }

  public ExchangeSession id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Numeric identifier for the exchange session
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

  public ExchangeSession exchangeId(Integer exchangeId) {
    this.exchangeId = exchangeId;
    return this;
  }

  /**
   * Numeric identifier for the exchange
   * @return exchangeId
  */
  @NotNull 
  @JsonProperty("exchangeId")
  public Integer getExchangeId() {
    return exchangeId;
  }

  public void setExchangeId(Integer exchangeId) {
    this.exchangeId = exchangeId;
  }

  public ExchangeSession startTime(GenTime startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Get startTime
   * @return startTime
  */
  @NotNull @Valid 
  @JsonProperty("startTime")
  public GenTime getStartTime() {
    return startTime;
  }

  public void setStartTime(GenTime startTime) {
    this.startTime = startTime;
  }

  public ExchangeSession stopTime(GenTime stopTime) {
    this.stopTime = stopTime;
    return this;
  }

  /**
   * Get stopTime
   * @return stopTime
  */
  @NotNull @Valid 
  @JsonProperty("stopTime")
  public GenTime getStopTime() {
    return stopTime;
  }

  public void setStopTime(GenTime stopTime) {
    this.stopTime = stopTime;
  }

  public ExchangeSession date(GenDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @NotNull @Valid 
  @JsonProperty("date")
  public GenDate getDate() {
    return date;
  }

  public void setDate(GenDate date) {
    this.date = date;
  }

  public ExchangeSession entityCount(Integer entityCount) {
    this.entityCount = entityCount;
    return this;
  }

  /**
   * Number of entities associated with the session
   * @return entityCount
  */
  @NotNull 
  @JsonProperty("entityCount")
  public Integer getEntityCount() {
    return entityCount;
  }

  public void setEntityCount(Integer entityCount) {
    this.entityCount = entityCount;
  }

  public ExchangeSession version(Double version) {
    this.version = version;
    return this;
  }

  /**
   * Version of the exchange session
   * @return version
  */
  @NotNull 
  @JsonProperty("version")
  public Double getVersion() {
    return version;
  }

  public void setVersion(Double version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExchangeSession exchangeSession = (ExchangeSession) o;
    return Objects.equals(this.id, exchangeSession.id) &&
        Objects.equals(this.exchangeId, exchangeSession.exchangeId) &&
        Objects.equals(this.startTime, exchangeSession.startTime) &&
        Objects.equals(this.stopTime, exchangeSession.stopTime) &&
        Objects.equals(this.date, exchangeSession.date) &&
        Objects.equals(this.entityCount, exchangeSession.entityCount) &&
        Objects.equals(this.version, exchangeSession.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, exchangeId, startTime, stopTime, date, entityCount, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExchangeSession {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    exchangeId: ").append(toIndentedString(exchangeId)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    stopTime: ").append(toIndentedString(stopTime)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    entityCount: ").append(toIndentedString(entityCount)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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

