package com.dunkware.utils.core.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DunkURLBuilder {

	/**
	 * Constructs a URL given a type, string host(IP or domain), numeric port and
	 * than path
	 * 
	 * @param type
	 * @param host
	 * @param port
	 * @param path
	 * @return
	 */
	public static DunkURLBuilder builder(String protocol, String host, int port, String path) {
		// build the end point
		// okay test this 

		StringBuilder b = new StringBuilder();
		if (protocol.contains("://")) {
			protocol = protocol.substring(0, protocol.length() - 3);
		}
		b.append(protocol.toLowerCase());
		b.append("://");
		b.append(host);
		b.append(":").append(port);
		if (path.startsWith("/") == false)
			b.append("/");
		b.append(path);

		return new DunkURLBuilder(b.toString());

		// StreetURLModel
		// protocoL dfd
		// port://dfd
		// : paramss

	}
	
	public static DunkURLBuilder builder(String endpoint) { 
		return new DunkURLBuilder(endpoint);
	}

	private String endpoint;
	private Map<String, Object> params = new HashMap<String, Object>();
	private List<String> paths = new ArrayList<String>();

	private DunkURLBuilder(String endpoint) {
		this.endpoint = endpoint;
	}

	public DunkURLBuilder param(String name, Object value) {
		params.put(name, value);
		return this;

	}

	public DunkURLBuilder path(String path) {
		this.paths.add(path);
		return this;
	}

	public String build() {
		
		if (endpoint.endsWith("/")) {
			endpoint = endpoint.substring(0, endpoint.length() - 1);
		}
		// create builder for paths and params
		StringBuilder out = new StringBuilder();
		// build a path string if we have 1 or ore paths 
		if (paths.size() > 0) {
			for (int i = 0; i < paths.size(); i++) {
				String path = paths.get(i);
				if (path.startsWith("/") == false) {
					out.append("/");
				}
				out.append(path);
			}
		}
		// if we did have paths append to end point 
		if(paths.size() > 0) { 
			endpoint = endpoint + out.toString();
		}
		// reset builder 
		out.setLength(0);
		// append endpoint 
		out.append(endpoint);
		// if no params then just return end point 
		if (params.size() == 0) {
			return endpoint;
		}
		// append the params to the endpoint 
		boolean firstParam = true;
		if (params.size() > 0) {
			for (String param : params.keySet()) {
				if (firstParam)
					out.append("?");
				else
					out.append("&");
				firstParam = false;
				out.append(param).append("=").append(params.get(param));
			}
		}
		// return the endpoint as full url 
		return out.toString();
	}
}
