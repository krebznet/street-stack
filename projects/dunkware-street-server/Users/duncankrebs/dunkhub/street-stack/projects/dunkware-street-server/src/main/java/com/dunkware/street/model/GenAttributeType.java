package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.dunkware.street.model.GenDataFormat;
import com.dunkware.street.model.GenDataType;
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
 * GenAttributeType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenAttributeType {

  private String identifier;

  private String name;

  private GenDataType dataType;

  private GenDataFormat dataFormat;

  public GenAttributeType identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  /**
   * Unique string identifier for the attribute.
   * @return identifier
  */
  
  @JsonProperty("identifier")
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public GenAttributeType name(String name) {
    this.name = name;
    return this;
  }

  /**
   * User-friendly name for the attribute.
   * @return name
  */
  
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GenAttributeType dataType(GenDataType dataType) {
    this.dataType = dataType;
    return this;
  }

  /**
   * Get dataType
   * @return dataType
  */
  @Valid 
  @JsonProperty("dataType")
  public GenDataType getDataType() {
    return dataType;
  }

  public void setDataType(GenDataType dataType) {
    this.dataType = dataType;
  }

  public GenAttributeType dataFormat(GenDataFormat dataFormat) {
    this.dataFormat = dataFormat;
    return this;
  }

  /**
   * Get dataFormat
   * @return dataFormat
  */
  @Valid 
  @JsonProperty("dataFormat")
  public GenDataFormat getDataFormat() {
    return dataFormat;
  }

  public void setDataFormat(GenDataFormat dataFormat) {
    this.dataFormat = dataFormat;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenAttributeType genAttributeType = (GenAttributeType) o;
    return Objects.equals(this.identifier, genAttributeType.identifier) &&
        Objects.equals(this.name, genAttributeType.name) &&
        Objects.equals(this.dataType, genAttributeType.dataType) &&
        Objects.equals(this.dataFormat, genAttributeType.dataFormat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identifier, name, dataType, dataFormat);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenAttributeType {\n");
    sb.append("    identifier: ").append(toIndentedString(identifier)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    dataType: ").append(toIndentedString(dataType)).append("\n");
    sb.append("    dataFormat: ").append(toIndentedString(dataFormat)).append("\n");
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

