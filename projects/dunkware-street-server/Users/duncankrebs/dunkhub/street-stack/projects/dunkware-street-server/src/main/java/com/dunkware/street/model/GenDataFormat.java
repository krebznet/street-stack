package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GenDataFormat
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenDataFormat {

  /**
   * The type of data formatting to apply.
   */
  public enum FormatTypeEnum {
    ROUNDTODECIMALPLACES("roundToDecimalPlaces"),
    
    DISPLAYASPERCENT("displayAsPercent"),
    
    ADDCOMMAS("addCommas"),
    
    TRUNCATESTRING("truncateString"),
    
    CURRENCY("currency"),
    
    ABBREVIATENUMBER("abbreviateNumber");

    private String value;

    FormatTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static FormatTypeEnum fromValue(String value) {
      for (FormatTypeEnum b : FormatTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private FormatTypeEnum formatType;

  private Integer precision;

  private Integer truncateLength;

  private String currencySymbol;

  private String abbreviationStyle;

  public GenDataFormat formatType(FormatTypeEnum formatType) {
    this.formatType = formatType;
    return this;
  }

  /**
   * The type of data formatting to apply.
   * @return formatType
  */
  
  @JsonProperty("formatType")
  public FormatTypeEnum getFormatType() {
    return formatType;
  }

  public void setFormatType(FormatTypeEnum formatType) {
    this.formatType = formatType;
  }

  public GenDataFormat precision(Integer precision) {
    this.precision = precision;
    return this;
  }

  /**
   * Number of decimal places for rounding (used with roundToDecimalPlaces).
   * @return precision
  */
  
  @JsonProperty("precision")
  public Integer getPrecision() {
    return precision;
  }

  public void setPrecision(Integer precision) {
    this.precision = precision;
  }

  public GenDataFormat truncateLength(Integer truncateLength) {
    this.truncateLength = truncateLength;
    return this;
  }

  /**
   * Maximum length for truncating strings (used with truncateString).
   * @return truncateLength
  */
  
  @JsonProperty("truncateLength")
  public Integer getTruncateLength() {
    return truncateLength;
  }

  public void setTruncateLength(Integer truncateLength) {
    this.truncateLength = truncateLength;
  }

  public GenDataFormat currencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
    return this;
  }

  /**
   * Symbol for currency formatting (used with currency).
   * @return currencySymbol
  */
  
  @JsonProperty("currencySymbol")
  public String getCurrencySymbol() {
    return currencySymbol;
  }

  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  public GenDataFormat abbreviationStyle(String abbreviationStyle) {
    this.abbreviationStyle = abbreviationStyle;
    return this;
  }

  /**
   * Style for abbreviating large numbers (used with abbreviateNumber).
   * @return abbreviationStyle
  */
  
  @JsonProperty("abbreviationStyle")
  public String getAbbreviationStyle() {
    return abbreviationStyle;
  }

  public void setAbbreviationStyle(String abbreviationStyle) {
    this.abbreviationStyle = abbreviationStyle;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenDataFormat genDataFormat = (GenDataFormat) o;
    return Objects.equals(this.formatType, genDataFormat.formatType) &&
        Objects.equals(this.precision, genDataFormat.precision) &&
        Objects.equals(this.truncateLength, genDataFormat.truncateLength) &&
        Objects.equals(this.currencySymbol, genDataFormat.currencySymbol) &&
        Objects.equals(this.abbreviationStyle, genDataFormat.abbreviationStyle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(formatType, precision, truncateLength, currencySymbol, abbreviationStyle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenDataFormat {\n");
    sb.append("    formatType: ").append(toIndentedString(formatType)).append("\n");
    sb.append("    precision: ").append(toIndentedString(precision)).append("\n");
    sb.append("    truncateLength: ").append(toIndentedString(truncateLength)).append("\n");
    sb.append("    currencySymbol: ").append(toIndentedString(currencySymbol)).append("\n");
    sb.append("    abbreviationStyle: ").append(toIndentedString(abbreviationStyle)).append("\n");
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

