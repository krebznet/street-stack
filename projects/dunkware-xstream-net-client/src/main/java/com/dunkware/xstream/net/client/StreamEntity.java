package com.dunkware.xstream.net.client;

import java.util.Collection;

public interface StreamEntity {
	
	int getId();
	
	String getIdent();
	
	Collection<StreamField> getFields();
	
	StreamField getField(String ident) throws StreamException;
	
	void addEventListener(Collection<String> types) throws StreamException;
	
	void removeEventListener(Collection<String> types) throws StreamException;

}
