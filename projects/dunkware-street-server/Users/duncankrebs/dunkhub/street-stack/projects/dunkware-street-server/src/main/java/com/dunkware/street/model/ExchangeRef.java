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
 * ExchangeRef
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class ExchangeRef {

  private Integer id;

  private String identifier;

  private String name;

  private Integer tickers;

  /**
   * Status of the exchange
   */
  public enum StatusEnum {
    OPEN("Open"),
    
    CLOSED("Closed"),
    
    PREMARKET("Premarket"),
    
    AFTERMARKET("Aftermarket");

    private String value;

    StatusEnum(String value) {
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
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private StatusEnum status;

  public ExchangeRef() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ExchangeRef(Integer id, String identifier, String name, Integer tickers, StatusEnum status) {
    this.id = id;
    this.identifier = identifier;
    this.name = name;
    this.tickers = tickers;
    this.status = status;
  }

  public ExchangeRef id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Numeric identifier for the exchange
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

  public ExchangeRef identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  /**
   * String identifier for the exchange
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

  public ExchangeRef name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the exchange
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

  public ExchangeRef tickers(Integer tickers) {
    this.tickers = tickers;
    return this;
  }

  /**
   * Number of tickers associated with the exchange
   * @return tickers
  */
  @NotNull 
  @JsonProperty("tickers")
  public Integer getTickers() {
    return tickers;
  }

  public void setTickers(Integer tickers) {
    this.tickers = tickers;
  }

  public ExchangeRef status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Status of the exchange
   * @return status
  */
  @NotNull 
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExchangeRef exchangeRef = (ExchangeRef) o;
    return Objects.equals(this.id, exchangeRef.id) &&
        Objects.equals(this.identifier, exchangeRef.identifier) &&
        Objects.equals(this.name, exchangeRef.name) &&
        Objects.equals(this.tickers, exchangeRef.tickers) &&
        Objects.equals(this.status, exchangeRef.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, identifier, name, tickers, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExchangeRef {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    identifier: ").append(toIndentedString(identifier)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    tickers: ").append(toIndentedString(tickers)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

