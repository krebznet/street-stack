package com.dunkware.trade.service.stream.server.blueprint.varmeta;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.helpers.DFileHelper;

public class VarMetaDataRegistry {
	
	private Map<Integer,VarMetaData> registry = new ConcurrentHashMap<Integer,VarMetaData>();
	
	public void load(File resource) throws Exception { 
		List<String> fileLines= DFileHelper.readFileLines(resource);
		for (String string : fileLines) {
			if(string.startsWith("#")) { 
				continue;
			}
			String[] split = string.split("~");
			int id = Integer.valueOf(split[0]);
			String ident = split[1];
			String name = split[2];
			String group = split[3];
			String expression = split[4];
			VarMetaData meta = new VarMetaData();
			meta.setId(id);
			meta.setGroup(group);;
			meta.setIdentifier(ident);
			meta.setName(name);;
			meta.setExpression(expression);
			registry.put(id, meta);
		}
			
	}
	
	
	public VarMetaData getVarMetaData(int id) throws Exception { 
		VarMetaData meta = registry.get(id);
		if(meta == null) { 
			throw new Exception("Var MetaDat not found for variable id " + id);
		}
		return meta;
	}
	
	
	public Collection<VarMetaData> getVarMetaData() { 
		return registry.values();
	}

}
