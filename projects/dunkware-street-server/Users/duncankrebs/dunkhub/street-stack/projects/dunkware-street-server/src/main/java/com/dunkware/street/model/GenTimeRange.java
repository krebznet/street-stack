package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.dunkware.street.model.GenTimeRangeType;
import com.dunkware.street.model.GenTimeUnit;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GenTimeRange
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenTimeRange {

  private GenTimeRangeType type;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date endDate;

  private Integer relativeValue;

  private GenTimeUnit relativeTimeUnit;

  private Boolean hasTimeWindow;

  private GenTimeRange timeWindow;

  public GenTimeRange() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GenTimeRange(GenTimeRangeType type) {
    this.type = type;
  }

  public GenTimeRange type(GenTimeRangeType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @NotNull @Valid 
  @JsonProperty("type")
  public GenTimeRangeType getType() {
    return type;
  }

  public void setType(GenTimeRangeType type) {
    this.type = type;
  }

  public GenTimeRange startDate(Date startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Start date and time of the range.
   * @return startDate
  */
  @Valid 
  @JsonProperty("startDate")
  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public GenTimeRange endDate(Date endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * End date and time of the range.
   * @return endDate
  */
  @Valid 
  @JsonProperty("endDate")
  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public GenTimeRange relativeValue(Integer relativeValue) {
    this.relativeValue = relativeValue;
    return this;
  }

  /**
   * The value for the relative time range.
   * @return relativeValue
  */
  
  @JsonProperty("relativeValue")
  public Integer getRelativeValue() {
    return relativeValue;
  }

  public void setRelativeValue(Integer relativeValue) {
    this.relativeValue = relativeValue;
  }

  public GenTimeRange relativeTimeUnit(GenTimeUnit relativeTimeUnit) {
    this.relativeTimeUnit = relativeTimeUnit;
    return this;
  }

  /**
   * Get relativeTimeUnit
   * @return relativeTimeUnit
  */
  @Valid 
  @JsonProperty("relativeTimeUnit")
  public GenTimeUnit getRelativeTimeUnit() {
    return relativeTimeUnit;
  }

  public void setRelativeTimeUnit(GenTimeUnit relativeTimeUnit) {
    this.relativeTimeUnit = relativeTimeUnit;
  }

  public GenTimeRange hasTimeWindow(Boolean hasTimeWindow) {
    this.hasTimeWindow = hasTimeWindow;
    return this;
  }

  /**
   * Boolean indicating if a specific time window is included in the range.
   * @return hasTimeWindow
  */
  
  @JsonProperty("hasTimeWindow")
  public Boolean getHasTimeWindow() {
    return hasTimeWindow;
  }

  public void setHasTimeWindow(Boolean hasTimeWindow) {
    this.hasTimeWindow = hasTimeWindow;
  }

  public GenTimeRange timeWindow(GenTimeRange timeWindow) {
    this.timeWindow = timeWindow;
    return this;
  }

  /**
   * Get timeWindow
   * @return timeWindow
  */
  @Valid 
  @JsonProperty("timeWindow")
  public GenTimeRange getTimeWindow() {
    return timeWindow;
  }

  public void setTimeWindow(GenTimeRange timeWindow) {
    this.timeWindow = timeWindow;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenTimeRange genTimeRange = (GenTimeRange) o;
    return Objects.equals(this.type, genTimeRange.type) &&
        Objects.equals(this.startDate, genTimeRange.startDate) &&
        Objects.equals(this.endDate, genTimeRange.endDate) &&
        Objects.equals(this.relativeValue, genTimeRange.relativeValue) &&
        Objects.equals(this.relativeTimeUnit, genTimeRange.relativeTimeUnit) &&
        Objects.equals(this.hasTimeWindow, genTimeRange.hasTimeWindow) &&
        Objects.equals(this.timeWindow, genTimeRange.timeWindow);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, startDate, endDate, relativeValue, relativeTimeUnit, hasTimeWindow, timeWindow);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenTimeRange {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    relativeValue: ").append(toIndentedString(relativeValue)).append("\n");
    sb.append("    relativeTimeUnit: ").append(toIndentedString(relativeTimeUnit)).append("\n");
    sb.append("    hasTimeWindow: ").append(toIndentedString(hasTimeWindow)).append("\n");
    sb.append("    timeWindow: ").append(toIndentedString(timeWindow)).append("\n");
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

