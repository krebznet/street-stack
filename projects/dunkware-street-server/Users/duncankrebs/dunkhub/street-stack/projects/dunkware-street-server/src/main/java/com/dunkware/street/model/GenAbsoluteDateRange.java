package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.dunkware.street.model.GenDate;
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
 * GenAbsoluteDateRange
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenAbsoluteDateRange {

  private GenDate startDate;

  private GenDate endDate;

  private Boolean hasTimeWindow;

  private GenTimeRange timeWindow;

  public GenAbsoluteDateRange() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GenAbsoluteDateRange(GenDate startDate, GenDate endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public GenAbsoluteDateRange startDate(GenDate startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
  */
  @NotNull @Valid 
  @JsonProperty("startDate")
  public GenDate getStartDate() {
    return startDate;
  }

  public void setStartDate(GenDate startDate) {
    this.startDate = startDate;
  }

  public GenAbsoluteDateRange endDate(GenDate endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
  */
  @NotNull @Valid 
  @JsonProperty("endDate")
  public GenDate getEndDate() {
    return endDate;
  }

  public void setEndDate(GenDate endDate) {
    this.endDate = endDate;
  }

  public GenAbsoluteDateRange hasTimeWindow(Boolean hasTimeWindow) {
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

  public GenAbsoluteDateRange timeWindow(GenTimeRange timeWindow) {
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
    GenAbsoluteDateRange genAbsoluteDateRange = (GenAbsoluteDateRange) o;
    return Objects.equals(this.startDate, genAbsoluteDateRange.startDate) &&
        Objects.equals(this.endDate, genAbsoluteDateRange.endDate) &&
        Objects.equals(this.hasTimeWindow, genAbsoluteDateRange.hasTimeWindow) &&
        Objects.equals(this.timeWindow, genAbsoluteDateRange.timeWindow);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startDate, endDate, hasTimeWindow, timeWindow);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenAbsoluteDateRange {\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
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

