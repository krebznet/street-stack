package com.dunkware.street.model;

import java.net.URI;
import java.util.Objects;
import com.dunkware.street.model.GenDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ExchangeLayer
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-03T04:43:50.832023-05:00[America/Chicago]")
public class ExchangeLayer {

  private Integer id;

  private Integer exchangeId;

  private Integer indexId;

  private GenDate releaseDate;

  public ExchangeLayer() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ExchangeLayer(Integer id, Integer exchangeId, Integer indexId, GenDate releaseDate) {
    this.id = id;
    this.exchangeId = exchangeId;
    this.indexId = indexId;
    this.releaseDate = releaseDate;
  }

  public ExchangeLayer id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Numeric identifier for the exchange layer
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

  public ExchangeLayer exchangeId(Integer exchangeId) {
    this.exchangeId = exchangeId;
    return this;
  }

  /**
   * Numeric identifier for the exchange
   * @return exchangeId
  */
  @NotNull 
  @JsonProperty("exchangeId")
  public Integer getExchangeId() {
    return exchangeId;
  }

  public void setExchangeId(Integer exchangeId) {
    this.exchangeId = exchangeId;
  }

  public ExchangeLayer indexId(Integer indexId) {
    this.indexId = indexId;
    return this;
  }

  /**
   * Numeric identifier for the index
   * @return indexId
  */
  @NotNull 
  @JsonProperty("indexId")
  public Integer getIndexId() {
    return indexId;
  }

  public void setIndexId(Integer indexId) {
    this.indexId = indexId;
  }

  public ExchangeLayer releaseDate(GenDate releaseDate) {
    this.releaseDate = releaseDate;
    return this;
  }

  /**
   * Get releaseDate
   * @return releaseDate
  */
  @NotNull @Valid 
  @JsonProperty("releaseDate")
  public GenDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(GenDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExchangeLayer exchangeLayer = (ExchangeLayer) o;
    return Objects.equals(this.id, exchangeLayer.id) &&
        Objects.equals(this.exchangeId, exchangeLayer.exchangeId) &&
        Objects.equals(this.indexId, exchangeLayer.indexId) &&
        Objects.equals(this.releaseDate, exchangeLayer.releaseDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, exchangeId, indexId, releaseDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExchangeLayer {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    exchangeId: ").append(toIndentedString(exchangeId)).append("\n");
    sb.append("    indexId: ").append(toIndentedString(indexId)).append("\n");
    sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
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

