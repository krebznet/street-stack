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
 * TickerSession
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class TickerSession {

  private GenDate date;

  private GenTime startTime;

  private GenTime stopTime;

  private Integer signalCount;

  private Double version;

  public TickerSession() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TickerSession(GenDate date, GenTime startTime, GenTime stopTime, Integer signalCount, Double version) {
    this.date = date;
    this.startTime = startTime;
    this.stopTime = stopTime;
    this.signalCount = signalCount;
    this.version = version;
  }

  public TickerSession date(GenDate date) {
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

  public TickerSession startTime(GenTime startTime) {
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

  public TickerSession stopTime(GenTime stopTime) {
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

  public TickerSession signalCount(Integer signalCount) {
    this.signalCount = signalCount;
    return this;
  }

  /**
   * Number of signals associated with the session
   * @return signalCount
  */
  @NotNull 
  @JsonProperty("signalCount")
  public Integer getSignalCount() {
    return signalCount;
  }

  public void setSignalCount(Integer signalCount) {
    this.signalCount = signalCount;
  }

  public TickerSession version(Double version) {
    this.version = version;
    return this;
  }

  /**
   * Version of the ticker session
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
    TickerSession tickerSession = (TickerSession) o;
    return Objects.equals(this.date, tickerSession.date) &&
        Objects.equals(this.startTime, tickerSession.startTime) &&
        Objects.equals(this.stopTime, tickerSession.stopTime) &&
        Objects.equals(this.signalCount, tickerSession.signalCount) &&
        Objects.equals(this.version, tickerSession.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, startTime, stopTime, signalCount, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TickerSession {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    stopTime: ").append(toIndentedString(stopTime)).append("\n");
    sb.append("    signalCount: ").append(toIndentedString(signalCount)).append("\n");
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

