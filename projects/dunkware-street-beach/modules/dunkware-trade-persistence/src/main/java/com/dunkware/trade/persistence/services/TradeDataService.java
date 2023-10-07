package com.dunkware.trade.persistence.services;

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

import com.dunkware.trade.persistence.asset.AssetEntity;


@Component
public class TradeDataService {

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
	
	
	
	

	public List<AssetEntity> getAssets()   {
		List<AssetEntity> results = em.createQuery("SELECT e FROM AssetEntity e", AssetEntity.class)
				.getResultList();
		return results;

		
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
