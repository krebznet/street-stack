package com.dunkware.xstream.model.stats.proto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.time.DunkTime;

public class EntityStatRespBuilder {
	
	public static EntityStatRespBuilder newInstance() { 
		return new EntityStatRespBuilder();
	}
	
	private EntityStatResp resp = new EntityStatResp();
	
	
	public EntityStatRespBuilder resolved(Number value, DDate date, DTime time) { 
		resp.setType(EntityStatRespType.Resolved);
		resp.setValue(value);
		resp.setDate(date);
		resp.setTime(time);
		
		return this;
	}
	
	public EntityStatRespBuilder resolved(Number value, String dateTimeString) { 
		resp.setType(EntityStatRespType.Resolved);
		resp.setValue(value);
		LocalDateTime dt = LocalDateTime.parse(dateTimeString,DateTimeFormatter.ofPattern(DunkTime.YYYY_MM_DD_HH_MM_SS));
		resp.setTime(DTime.from(dt.toLocalTime()));
		resp.setDate(DDate.from(dt.toLocalDate()));
		return this;
	}
	
	public EntityStatRespBuilder exception(String exception) { 
		resp.setType(EntityStatRespType.Exception);
		resp.setException(exception);
		return this;
	}
	
	public EntityStatRespBuilder unresolved() { 
		resp.setType(EntityStatRespType.Unresolved);
		return this;
	}
	
	public EntityStatResp build() { 
		return resp;
	}

	public void validate() throws Exception { 
		if(resp.getType() == null)  {
			throw new Exception("Resp type not set");
		}
		if(resp.getType() == EntityStatRespType.Resolved) { 
			if(resp.getValue() == null) { 
				throw new Exception("Resolved response needs a value");
			}
		}
		if(resp.getType() == EntityStatRespType.Exception) { 
			if(resp.getException() == null) { 
				throw new Exception("Exception resp needs a exception string");
			}
		}
	}
}
