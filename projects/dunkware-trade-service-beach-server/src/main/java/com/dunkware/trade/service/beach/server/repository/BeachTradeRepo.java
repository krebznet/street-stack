package com.dunkware.trade.service.beach.server.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BeachTradeRepo {

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

	public List<BeachBrokerDO> getBrokers() {
		try {
			List<BeachBrokerDO> results = em.createQuery("SELECT e FROM BeachBrokerDO e", BeachBrokerDO.class)
					.getResultList();
			return results;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}

	}

	public BeachAccountDO getAccount(String identifier) {
		List<BeachAccountDO> act = em
				.createQuery("SELECT e FROM BeachAccountDO e where e.identifier ='" + identifier + "'",
						BeachAccountDO.class)
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
