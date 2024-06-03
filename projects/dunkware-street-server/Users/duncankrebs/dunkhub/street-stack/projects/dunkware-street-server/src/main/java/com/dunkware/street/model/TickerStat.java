package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
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
 * TickerStat
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class TickerStat {

  private Integer statId;

  private String statName;

  private Integer tickerId;

  private Integer exchangeId;

  private Integer elementId;

  private Double statValue;

  private GenTime time;

  public TickerStat() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TickerStat(Integer statId, String statName, Integer tickerId, Integer exchangeId, Integer elementId, Double statValue) {
    this.statId = statId;
    this.statName = statName;
    this.tickerId = tickerId;
    this.exchangeId = exchangeId;
    this.elementId = elementId;
    this.statValue = statValue;
  }

  public TickerStat statId(Integer statId) {
    this.statId = statId;
    return this;
  }

  /**
   * Numeric identifier for the statistic
   * @return statId
  */
  @NotNull 
  @JsonProperty("statId")
  public Integer getStatId() {
    return statId;
  }

  public void setStatId(Integer statId) {
    this.statId = statId;
  }

  public TickerStat statName(String statName) {
    this.statName = statName;
    return this;
  }

  /**
   * Name of the statistic
   * @return statName
  */
  @NotNull 
  @JsonProperty("statName")
  public String getStatName() {
    return statName;
  }

  public void setStatName(String statName) {
    this.statName = statName;
  }

  public TickerStat tickerId(Integer tickerId) {
    this.tickerId = tickerId;
    return this;
  }

  /**
   * Numeric identifier for the ticker
   * @return tickerId
  */
  @NotNull 
  @JsonProperty("tickerId")
  public Integer getTickerId() {
    return tickerId;
  }

  public void setTickerId(Integer tickerId) {
    this.tickerId = tickerId;
  }

  public TickerStat exchangeId(Integer exchangeId) {
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

  public TickerStat elementId(Integer elementId) {
    this.elementId = elementId;
    return this;
  }

  /**
   * Numeric identifier for the element
   * @return elementId
  */
  @NotNull 
  @JsonProperty("elementId")
  public Integer getElementId() {
    return elementId;
  }

  public void setElementId(Integer elementId) {
    this.elementId = elementId;
  }

  public TickerStat statValue(Double statValue) {
    this.statValue = statValue;
    return this;
  }

  /**
   * Value of the statistic
   * @return statValue
  */
  @NotNull 
  @JsonProperty("statValue")
  public Double getStatValue() {
    return statValue;
  }

  public void setStatValue(Double statValue) {
    this.statValue = statValue;
  }

  public TickerStat time(GenTime time) {
    this.time = time;
    return this;
  }

  /**
   * Get time
   * @return time
  */
  @Valid 
  @JsonProperty("time")
  public GenTime getTime() {
    return time;
  }

  public void setTime(GenTime time) {
    this.time = time;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TickerStat tickerStat = (TickerStat) o;
    return Objects.equals(this.statId, tickerStat.statId) &&
        Objects.equals(this.statName, tickerStat.statName) &&
        Objects.equals(this.tickerId, tickerStat.tickerId) &&
        Objects.equals(this.exchangeId, tickerStat.exchangeId) &&
        Objects.equals(this.elementId, tickerStat.elementId) &&
        Objects.equals(this.statValue, tickerStat.statValue) &&
        Objects.equals(this.time, tickerStat.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statId, statName, tickerId, exchangeId, elementId, statValue, time);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TickerStat {\n");
    sb.append("    statId: ").append(toIndentedString(statId)).append("\n");
    sb.append("    statName: ").append(toIndentedString(statName)).append("\n");
    sb.append("    tickerId: ").append(toIndentedString(tickerId)).append("\n");
    sb.append("    exchangeId: ").append(toIndentedString(exchangeId)).append("\n");
    sb.append("    elementId: ").append(toIndentedString(elementId)).append("\n");
    sb.append("    statValue: ").append(toIndentedString(statValue)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
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

