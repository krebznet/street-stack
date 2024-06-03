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
 * GenDate
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenDate {

  private Integer year;

  private Integer month;

  private Integer dayOfMonth;

  public GenDate() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GenDate(Integer year, Integer month, Integer dayOfMonth) {
    this.year = year;
    this.month = month;
    this.dayOfMonth = dayOfMonth;
  }

  public GenDate year(Integer year) {
    this.year = year;
    return this;
  }

  /**
   * The year component of the date.
   * @return year
  */
  @NotNull 
  @JsonProperty("year")
  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public GenDate month(Integer month) {
    this.month = month;
    return this;
  }

  /**
   * The month component of the date.
   * @return month
  */
  @NotNull 
  @JsonProperty("month")
  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public GenDate dayOfMonth(Integer dayOfMonth) {
    this.dayOfMonth = dayOfMonth;
    return this;
  }

  /**
   * The day of the month component.
   * @return dayOfMonth
  */
  @NotNull 
  @JsonProperty("dayOfMonth")
  public Integer getDayOfMonth() {
    return dayOfMonth;
  }

  public void setDayOfMonth(Integer dayOfMonth) {
    this.dayOfMonth = dayOfMonth;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenDate genDate = (GenDate) o;
    return Objects.equals(this.year, genDate.year) &&
        Objects.equals(this.month, genDate.month) &&
        Objects.equals(this.dayOfMonth, genDate.dayOfMonth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(year, month, dayOfMonth);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenDate {\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    month: ").append(toIndentedString(month)).append("\n");
    sb.append("    dayOfMonth: ").append(toIndentedString(dayOfMonth)).append("\n");
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

