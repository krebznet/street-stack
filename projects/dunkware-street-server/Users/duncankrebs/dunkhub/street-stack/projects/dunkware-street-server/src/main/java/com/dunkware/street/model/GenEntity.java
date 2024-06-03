package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.dunkware.street.model.GenEntityType;
import com.dunkware.street.model.GenEntityValuesValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GenEntity
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenEntity {

  private GenEntityType type;

  @Valid
  private Map<String, GenEntityValuesValue> values = new HashMap<>();

  public GenEntity type(GenEntityType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @Valid 
  @JsonProperty("type")
  public GenEntityType getType() {
    return type;
  }

  public void setType(GenEntityType type) {
    this.type = type;
  }

  public GenEntity values(Map<String, GenEntityValuesValue> values) {
    this.values = values;
    return this;
  }

  public GenEntity putValuesItem(String key, GenEntityValuesValue valuesItem) {
    if (this.values == null) {
      this.values = new HashMap<>();
    }
    this.values.put(key, valuesItem);
    return this;
  }

  /**
   * A map of attribute identifiers to their actual values.
   * @return values
  */
  @Valid 
  @JsonProperty("values")
  public Map<String, GenEntityValuesValue> getValues() {
    return values;
  }

  public void setValues(Map<String, GenEntityValuesValue> values) {
    this.values = values;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenEntity genEntity = (GenEntity) o;
    return Objects.equals(this.type, genEntity.type) &&
        Objects.equals(this.values, genEntity.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, values);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenEntity {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
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

