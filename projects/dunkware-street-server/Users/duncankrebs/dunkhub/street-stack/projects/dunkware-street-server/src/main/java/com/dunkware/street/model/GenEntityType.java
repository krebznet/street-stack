package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.dunkware.street.model.GenAttributeType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GenEntityType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class GenEntityType {

  private Integer id;

  private String identifier;

  private String name;

  @Valid
  private List<@Valid GenAttributeType> attributes;

  public GenEntityType id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * The numeric ID of the entity type.
   * @return id
  */
  
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public GenEntityType identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  /**
   * Unique string identifier for the entity type.
   * @return identifier
  */
  
  @JsonProperty("identifier")
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public GenEntityType name(String name) {
    this.name = name;
    return this;
  }

  /**
   * User-friendly name for the entity type.
   * @return name
  */
  
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GenEntityType attributes(List<@Valid GenAttributeType> attributes) {
    this.attributes = attributes;
    return this;
  }

  public GenEntityType addAttributesItem(GenAttributeType attributesItem) {
    if (this.attributes == null) {
      this.attributes = new ArrayList<>();
    }
    this.attributes.add(attributesItem);
    return this;
  }

  /**
   * A list of attributes for the entity type.
   * @return attributes
  */
  @Valid 
  @JsonProperty("attributes")
  public List<@Valid GenAttributeType> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<@Valid GenAttributeType> attributes) {
    this.attributes = attributes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenEntityType genEntityType = (GenEntityType) o;
    return Objects.equals(this.id, genEntityType.id) &&
        Objects.equals(this.identifier, genEntityType.identifier) &&
        Objects.equals(this.name, genEntityType.name) &&
        Objects.equals(this.attributes, genEntityType.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, identifier, name, attributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenEntityType {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    identifier: ").append(toIndentedString(identifier)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
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

