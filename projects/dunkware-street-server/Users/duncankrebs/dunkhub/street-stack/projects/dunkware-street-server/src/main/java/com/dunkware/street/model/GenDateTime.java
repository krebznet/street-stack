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
 * GenDateTime
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenDateTime {

  private GenDate date;

  private GenTime time;

  public GenDateTime date(GenDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @Valid 
  @JsonProperty("date")
  public GenDate getDate() {
    return date;
  }

  public void setDate(GenDate date) {
    this.date = date;
  }

  public GenDateTime time(GenTime time) {
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
    GenDateTime genDateTime = (GenDateTime) o;
    return Objects.equals(this.date, genDateTime.date) &&
        Objects.equals(this.time, genDateTime.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, time);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenDateTime {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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

