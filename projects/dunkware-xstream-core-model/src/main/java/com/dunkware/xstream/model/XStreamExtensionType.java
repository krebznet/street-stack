package com.dunkware.xstream.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Marker interface for now 
 * @author dkrebs
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class XStreamExtensionType {

}
