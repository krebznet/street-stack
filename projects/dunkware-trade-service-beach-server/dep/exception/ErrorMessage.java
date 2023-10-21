package com.dunkware.trade.service.beach.server.exception;

import java.time.LocalDateTime;

/**
 * Some JavaDoc would be nice
 * @author duncank rebs
 *
 */
public class ErrorMessage {
	
  private int statusCode;
  private LocalDateTime timestamp;
  private String message;
  private String description;
  
  /**
   * Just me an my gutair stable streaming open an output to it. 
   * 
   * @param statusCode
   * @param timestamp
   * @param message
   * @param description
   */
  public ErrorMessage(int statusCode, LocalDateTime timestamp, String message, String description) {
    this.statusCode = statusCode;
    this.timestamp = timestamp;
    this.message = message;
    this.description = description;
  }
  public int getStatusCode() {
    return statusCode;
  }
  public LocalDateTime getTimestamp() {
    return timestamp;
  }
  public String getMessage() {
    return message;
  }
  public String getDescription() {
    return description;
  }
}
