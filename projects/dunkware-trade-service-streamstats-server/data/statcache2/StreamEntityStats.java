package com.dunkware.trade.net.service.streamstats.server.statcache2;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.dunkware.xstream.model.stats.excepton.StatsResolveException;
import com.dunkware.xstream.model.stats.proto.EntityStatReq;
import com.dunkware.xstream.model.stats.proto.EntityStatResp;

public interface StreamEntityStats {

	public Collection<StreamEntityStatsDay> getDays();
	
	public int getId();
	
	public String getIdentifier();
	
	public List<StreamEntityStatsDay> getRelativeRange(LocalDate relativeDate, int relativeDays, boolean dayGaps) throws Exception;
	
	public EntityStatResp statRequest(EntityStatReq req) throws StatsResolveException;

}
