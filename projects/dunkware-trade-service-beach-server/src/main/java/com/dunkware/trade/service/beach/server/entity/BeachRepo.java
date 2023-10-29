package com.dunkware.trade.service.beach.server.entity;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class BeachRepo {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@PersistenceContext(name = "trade")
	private EntityManager em;

	@Autowired
	@Qualifier("tradeEntityManagerFactory")
	private EntityManagerFactory emf;

	@PostConstruct
	public void load() {
		try {
			if(emf == null) { 
				System.err.println("Shit get this EMF!");
			}
			// emf = Persistence.createEntityManagerFactory("trade");
		} catch (Exception e) {
			System.err.println("emf fucked up  " + e.toString());
			e.printStackTrace();
		}

	}
	
	

	public List<BeachBrokerEnttity> getBrokers()   {
		List<BeachBrokerEnttity> results = em.createQuery("SELECT e FROM BeachBrokerEntity e", BeachBrokerEnttity.class)
				.getResultList();
		return results;

		
	}

	public BeachBrokerAccountEntity getAccount(String identifier) {
		List<BeachBrokerAccountEntity> act = em
				.createQuery("SELECT e FROM BeachBrokerAccountEntity e where e.identifier ='" + identifier + "'",
						BeachBrokerAccountEntity.class)
				.getResultList();
		if (act.size() > 0) {
			return act.get(0);
		}
		return null;
	}

	public EntityManager createEntityManager() { 
		return emf.createEntityManager();
	}
	
	public void persist(Object entity) throws Exception { 
		EntityManager em = createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("Exception Persisting Entity " + e.toString());
		} finally { 
			em.close();
		}
	}
}
