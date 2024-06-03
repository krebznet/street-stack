package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.dunkware.street.model.GenDateTime;
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
 * GenDateTimeRange
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenDateTimeRange {

  private GenDateTime start;

  private GenDateTime end;

  private Boolean hasTimeWindow;

  private GenTimeRange timeWindow;

  public GenDateTimeRange() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GenDateTimeRange(GenDateTime start, GenDateTime end) {
    this.start = start;
    this.end = end;
  }

  public GenDateTimeRange start(GenDateTime start) {
    this.start = start;
    return this;
  }

  /**
   * Get start
   * @return start
  */
  @NotNull @Valid 
  @JsonProperty("start")
  public GenDateTime getStart() {
    return start;
  }

  public void setStart(GenDateTime start) {
    this.start = start;
  }

  public GenDateTimeRange end(GenDateTime end) {
    this.end = end;
    return this;
  }

  /**
   * Get end
   * @return end
  */
  @NotNull @Valid 
  @JsonProperty("end")
  public GenDateTime getEnd() {
    return end;
  }

  public void setEnd(GenDateTime end) {
    this.end = end;
  }

  public GenDateTimeRange hasTimeWindow(Boolean hasTimeWindow) {
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

  public GenDateTimeRange timeWindow(GenTimeRange timeWindow) {
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
    GenDateTimeRange genDateTimeRange = (GenDateTimeRange) o;
    return Objects.equals(this.start, genDateTimeRange.start) &&
        Objects.equals(this.end, genDateTimeRange.end) &&
        Objects.equals(this.hasTimeWindow, genDateTimeRange.hasTimeWindow) &&
        Objects.equals(this.timeWindow, genDateTimeRange.timeWindow);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end, hasTimeWindow, timeWindow);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenDateTimeRange {\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
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

