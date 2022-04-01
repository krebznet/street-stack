package com.dunkware.net.data.builders;

import com.dunkware.net.proto.core.GBean;
import com.dunkware.net.proto.core.GList;

public class GListBuilder {
	
	public static GListBuilder newBuilder(int id) { 
		return new GListBuilder(id);
	}
	
	
	private int id; 
	private String name; 
	
	GList.Builder builder = GList.newBuilder();
	
	private GListBuilder(int id) { 
		this.id = id; 
		builder.setListId(id);
		
	}
	
	public GListBuilder addBean(GBean bean) { 
		builder.addBeans(bean);
		return this;
	}
	
	public GList build() { 
		return builder.build();
	}

}
