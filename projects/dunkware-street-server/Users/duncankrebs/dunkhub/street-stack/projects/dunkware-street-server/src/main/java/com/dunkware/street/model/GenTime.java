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
 * GenTime
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenTime {

  private Integer hour;

  private Integer minute;

  private Integer second;

  private String timeZone;

  public GenTime() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GenTime(Integer hour, Integer minute, Integer second) {
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  public GenTime hour(Integer hour) {
    this.hour = hour;
    return this;
  }

  /**
   * The hour component of the time.
   * @return hour
  */
  @NotNull 
  @JsonProperty("hour")
  public Integer getHour() {
    return hour;
  }

  public void setHour(Integer hour) {
    this.hour = hour;
  }

  public GenTime minute(Integer minute) {
    this.minute = minute;
    return this;
  }

  /**
   * The minute component of the time.
   * @return minute
  */
  @NotNull 
  @JsonProperty("minute")
  public Integer getMinute() {
    return minute;
  }

  public void setMinute(Integer minute) {
    this.minute = minute;
  }

  public GenTime second(Integer second) {
    this.second = second;
    return this;
  }

  /**
   * The second component of the time.
   * @return second
  */
  @NotNull 
  @JsonProperty("second")
  public Integer getSecond() {
    return second;
  }

  public void setSecond(Integer second) {
    this.second = second;
  }

  public GenTime timeZone(String timeZone) {
    this.timeZone = timeZone;
    return this;
  }

  /**
   * Optional string representing the time zone.
   * @return timeZone
  */
  
  @JsonProperty("timeZone")
  public String getTimeZone() {
    return timeZone;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenTime genTime = (GenTime) o;
    return Objects.equals(this.hour, genTime.hour) &&
        Objects.equals(this.minute, genTime.minute) &&
        Objects.equals(this.second, genTime.second) &&
        Objects.equals(this.timeZone, genTime.timeZone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hour, minute, second, timeZone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenTime {\n");
    sb.append("    hour: ").append(toIndentedString(hour)).append("\n");
    sb.append("    minute: ").append(toIndentedString(minute)).append("\n");
    sb.append("    second: ").append(toIndentedString(second)).append("\n");
    sb.append("    timeZone: ").append(toIndentedString(timeZone)).append("\n");
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

