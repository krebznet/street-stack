package com.dunkware.trade.tick.service.server.feed.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

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
