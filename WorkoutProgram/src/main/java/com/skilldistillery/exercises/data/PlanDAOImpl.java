package com.skilldistillery.exercises.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.exercises.entities.Plan;

public class PlanDAOImpl implements PlanDAO {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExercisesDB");
	private EntityManager em = emf.createEntityManager();

	@Override
	public Plan create(Plan plan) {
		em.getTransaction().begin();
		
		em.persist(plan);
		
		em.flush();
		
		em.getTransaction().commit();
		return plan;
	}

	@Override
	public Plan update(int id, Plan plan) {
		em.getTransaction().begin();
		Plan managed = em.find(Plan.class, id);
		
		managed.setName(plan.getName());
		
		em.flush();
		em.getTransaction().commit();
		
		return managed;
	}

	@Override
	public boolean destroy(int id) {
		em.getTransaction().begin();
		Plan plan = em.find(Plan.class, id);
		em.remove(plan);
		
		em.getTransaction().commit();
		
		return !em.contains(plan);
	}

	@Override
	public Plan get(int id) {
		
		
		return em.find(Plan.class, id);
	}
	@Override
	public Set<Plan> search(String words) {
		words = "%"+words+"%";
		words = words.replaceAll(" ", "% %");
		String wordsarr[] = words.split(" ");
		Set<Plan> planSet = new HashSet<>();
		
		for (String searchword : wordsarr) {
			String query = "select p from Plan p where name like:search";
			planSet.addAll(em.createQuery(query, Plan.class ).setParameter
					("search", searchword).getResultList());
		}
		
		return planSet;
	}
	@Override
	public List<Plan> getAll() {
		
		String query = "select p from Plan p";
		
		return em.createQuery(query, Plan.class).getResultList();
	}

}
