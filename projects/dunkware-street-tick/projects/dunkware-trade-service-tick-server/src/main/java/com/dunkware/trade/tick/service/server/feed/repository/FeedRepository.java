package com.dunkware.trade.tick.service.server.feed.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository

public class FeedRepository  {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em; 
	
	
	@Transactional
	public void saveProvider(FeedProviderDO provider) { 
		em.persist(provider);
	}
	
	@Transactional
	public List<FeedProviderDO> getProviders() { 
		Query query = em.createNamedQuery("FeedProviderDO.All");
		return query.getResultList();
	}
}
