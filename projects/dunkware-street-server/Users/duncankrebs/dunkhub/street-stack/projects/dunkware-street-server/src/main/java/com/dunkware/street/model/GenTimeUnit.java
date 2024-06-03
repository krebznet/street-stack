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
 * GenTimeUnit
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenTimeUnit {

  private Integer seconds;

  private Integer minutes;

  private Integer hours;

  private Integer days;

  public GenTimeUnit seconds(Integer seconds) {
    this.seconds = seconds;
    return this;
  }

  /**
   * The number of seconds.
   * @return seconds
  */
  
  @JsonProperty("seconds")
  public Integer getSeconds() {
    return seconds;
  }

  public void setSeconds(Integer seconds) {
    this.seconds = seconds;
  }

  public GenTimeUnit minutes(Integer minutes) {
    this.minutes = minutes;
    return this;
  }

  /**
   * The number of minutes.
   * @return minutes
  */
  
  @JsonProperty("minutes")
  public Integer getMinutes() {
    return minutes;
  }

  public void setMinutes(Integer minutes) {
    this.minutes = minutes;
  }

  public GenTimeUnit hours(Integer hours) {
    this.hours = hours;
    return this;
  }

  /**
   * The number of hours.
   * @return hours
  */
  
  @JsonProperty("hours")
  public Integer getHours() {
    return hours;
  }

  public void setHours(Integer hours) {
    this.hours = hours;
  }

  public GenTimeUnit days(Integer days) {
    this.days = days;
    return this;
  }

  /**
   * The number of days.
   * @return days
  */
  
  @JsonProperty("days")
  public Integer getDays() {
    return days;
  }

  public void setDays(Integer days) {
    this.days = days;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenTimeUnit genTimeUnit = (GenTimeUnit) o;
    return Objects.equals(this.seconds, genTimeUnit.seconds) &&
        Objects.equals(this.minutes, genTimeUnit.minutes) &&
        Objects.equals(this.hours, genTimeUnit.hours) &&
        Objects.equals(this.days, genTimeUnit.days);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seconds, minutes, hours, days);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenTimeUnit {\n");
    sb.append("    seconds: ").append(toIndentedString(seconds)).append("\n");
    sb.append("    minutes: ").append(toIndentedString(minutes)).append("\n");
    sb.append("    hours: ").append(toIndentedString(hours)).append("\n");
    sb.append("    days: ").append(toIndentedString(days)).append("\n");
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

