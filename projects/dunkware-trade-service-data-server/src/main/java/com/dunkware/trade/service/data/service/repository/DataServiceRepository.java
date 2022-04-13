package com.dunkware.trade.service.data.service.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DataServiceRepository {
	
	@PersistenceContext(name = "data")
	private EntityManager em;

	@Autowired
	@Qualifier("dataEntityManagerFactory")
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
	
	public List<DataStreamEntity> getDataStreamEntities() {
		try {
			List<DataStreamEntity> results = em.createQuery("SELECT e FROM DataStreamEntity e", DataStreamEntity.class)
					.getResultList();
			return results;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}

	}
	

}
