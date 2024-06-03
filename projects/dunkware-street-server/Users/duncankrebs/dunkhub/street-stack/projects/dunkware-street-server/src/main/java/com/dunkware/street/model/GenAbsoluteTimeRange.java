package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.dunkware.street.model.GenTime;
import com.dunkware.street.model.GenTimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GenAbsoluteTimeRange
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenAbsoluteTimeRange {

  private GenTime startTime;

  private GenTime endTime;

  private Boolean hasTimeWindow;

  private GenTimeRange timeWindow;

  public GenAbsoluteTimeRange() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GenAbsoluteTimeRange(GenTime startTime, GenTime endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public GenAbsoluteTimeRange startTime(GenTime startTime) {
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

  public GenAbsoluteTimeRange endTime(GenTime endTime) {
    this.endTime = endTime;
    return this;
  }

  /**
   * Get endTime
   * @return endTime
  */
  @NotNull @Valid 
  @JsonProperty("endTime")
  public GenTime getEndTime() {
    return endTime;
  }

  public void setEndTime(GenTime endTime) {
    this.endTime = endTime;
  }

  public GenAbsoluteTimeRange hasTimeWindow(Boolean hasTimeWindow) {
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

  public GenAbsoluteTimeRange timeWindow(GenTimeRange timeWindow) {
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
    GenAbsoluteTimeRange genAbsoluteTimeRange = (GenAbsoluteTimeRange) o;
    return Objects.equals(this.startTime, genAbsoluteTimeRange.startTime) &&
        Objects.equals(this.endTime, genAbsoluteTimeRange.endTime) &&
        Objects.equals(this.hasTimeWindow, genAbsoluteTimeRange.hasTimeWindow) &&
        Objects.equals(this.timeWindow, genAbsoluteTimeRange.timeWindow);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startTime, endTime, hasTimeWindow, timeWindow);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenAbsoluteTimeRange {\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
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

