package com.dunkware.street.stream.model.session.sxits;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

//TODO: AVINASHANV-34 Trade Exit Type
/**
 * the pattern of classes that end with Type usually are models to runtime components and 
 * given a instance of a type the registry creates the runtime object associated with it 
 * through annotatios. 
 * 
 * this the base model for trade exit types, like a SignalExitType xtends this and the you
 * have runtime SignalExit class that will use signal subscription showed earlier. 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class TimeExitType {

}
