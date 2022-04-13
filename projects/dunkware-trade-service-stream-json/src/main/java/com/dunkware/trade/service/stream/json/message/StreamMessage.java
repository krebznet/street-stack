package com.dunkware.trade.service.stream.json.message;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base class for a StreamMessage
 * @author duncankrebs
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class StreamMessage {

}
