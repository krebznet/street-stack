package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.dunkware.street.model.GenDataFormat;
import com.dunkware.street.model.GenDataType;
import com.dunkware.street.model.TickerVariableValue;
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
 * TickerVariable
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class TickerVariable {

  private Integer id;

  private String identifier;

  private String name;

  private String group;

  private GenDataType dataType;

  private GenDataFormat dataFormat;

  private Double baseVersion;

  private Integer sessionCount;

  private TickerVariableValue value;

  public TickerVariable() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TickerVariable(Integer id, String identifier, String name, String group, GenDataType dataType, GenDataFormat dataFormat, Double baseVersion, Integer sessionCount) {
    this.id = id;
    this.identifier = identifier;
    this.name = name;
    this.group = group;
    this.dataType = dataType;
    this.dataFormat = dataFormat;
    this.baseVersion = baseVersion;
    this.sessionCount = sessionCount;
  }

  public TickerVariable id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Numeric identifier for the ticker variable
   * @return id
  */
  @NotNull 
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public TickerVariable identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  /**
   * String identifier for the ticker variable
   * @return identifier
  */
  @NotNull 
  @JsonProperty("identifier")
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public TickerVariable name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the ticker variable
   * @return name
  */
  @NotNull 
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TickerVariable group(String group) {
    this.group = group;
    return this;
  }

  /**
   * Group to which the ticker variable belongs
   * @return group
  */
  @NotNull 
  @JsonProperty("group")
  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public TickerVariable dataType(GenDataType dataType) {
    this.dataType = dataType;
    return this;
  }

  /**
   * Get dataType
   * @return dataType
  */
  @NotNull @Valid 
  @JsonProperty("dataType")
  public GenDataType getDataType() {
    return dataType;
  }

  public void setDataType(GenDataType dataType) {
    this.dataType = dataType;
  }

  public TickerVariable dataFormat(GenDataFormat dataFormat) {
    this.dataFormat = dataFormat;
    return this;
  }

  /**
   * Get dataFormat
   * @return dataFormat
  */
  @NotNull @Valid 
  @JsonProperty("dataFormat")
  public GenDataFormat getDataFormat() {
    return dataFormat;
  }

  public void setDataFormat(GenDataFormat dataFormat) {
    this.dataFormat = dataFormat;
  }

  public TickerVariable baseVersion(Double baseVersion) {
    this.baseVersion = baseVersion;
    return this;
  }

  /**
   * Base version of the ticker variable
   * @return baseVersion
  */
  @NotNull 
  @JsonProperty("baseVersion")
  public Double getBaseVersion() {
    return baseVersion;
  }

  public void setBaseVersion(Double baseVersion) {
    this.baseVersion = baseVersion;
  }

  public TickerVariable sessionCount(Integer sessionCount) {
    this.sessionCount = sessionCount;
    return this;
  }

  /**
   * Number of sessions associated with the ticker variable
   * @return sessionCount
  */
  @NotNull 
  @JsonProperty("sessionCount")
  public Integer getSessionCount() {
    return sessionCount;
  }

  public void setSessionCount(Integer sessionCount) {
    this.sessionCount = sessionCount;
  }

  public TickerVariable value(TickerVariableValue value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
  */
  @Valid 
  @JsonProperty("value")
  public TickerVariableValue getValue() {
    return value;
  }

  public void setValue(TickerVariableValue value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TickerVariable tickerVariable = (TickerVariable) o;
    return Objects.equals(this.id, tickerVariable.id) &&
        Objects.equals(this.identifier, tickerVariable.identifier) &&
        Objects.equals(this.name, tickerVariable.name) &&
        Objects.equals(this.group, tickerVariable.group) &&
        Objects.equals(this.dataType, tickerVariable.dataType) &&
        Objects.equals(this.dataFormat, tickerVariable.dataFormat) &&
        Objects.equals(this.baseVersion, tickerVariable.baseVersion) &&
        Objects.equals(this.sessionCount, tickerVariable.sessionCount) &&
        Objects.equals(this.value, tickerVariable.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, identifier, name, group, dataType, dataFormat, baseVersion, sessionCount, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TickerVariable {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    identifier: ").append(toIndentedString(identifier)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    group: ").append(toIndentedString(group)).append("\n");
    sb.append("    dataType: ").append(toIndentedString(dataType)).append("\n");
    sb.append("    dataFormat: ").append(toIndentedString(dataFormat)).append("\n");
    sb.append("    baseVersion: ").append(toIndentedString(baseVersion)).append("\n");
    sb.append("    sessionCount: ").append(toIndentedString(sessionCount)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

