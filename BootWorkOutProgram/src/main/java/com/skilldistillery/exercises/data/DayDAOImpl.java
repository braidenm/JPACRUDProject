package com.skilldistillery.exercises.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.exercises.entities.Day;

@Service
@Transactional
public class DayDAOImpl implements DayDAO {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExercisesDB");
	private EntityManager em = emf.createEntityManager();

	@Override
	public Day create(Day day) {
		em.getTransaction().begin();
		
		em.persist(day);
		
		em.flush();
		
		em.getTransaction().commit();
		return day;
	}

	@Override
	public Day update(int id, Day day) {
		em.getTransaction().begin();
		Day managed = em.find(Day.class, id);
		
		managed.setName(day.getName());
		managed.setDay(day.getDay());
		
		em.flush();
		em.getTransaction().commit();
		
		return managed;
	}

	@Override
	public boolean destroy(int id) {
		em.getTransaction().begin();
		Day ex = em.find(Day.class, id);
		em.remove(ex);
		
		em.getTransaction().commit();
		
		return !em.contains(ex);
	}

	@Override
	public Day get(int id) {
		
		
		return em.find(Day.class, id);
	}
	
	@Override
	public List<Day> getAll() {
		
		String query = "select d from Day d";
		
		return em.createQuery(query, Day.class).getResultList();
	}
	
	@Override
	public Set<Day> search(String words) {
		words = "%"+words+"%";
		words = words.replaceAll(" ", "% %");
		String wordsarr[] = words.split(" ");
		Set<Day> daySet = new HashSet<>();
		
		for (String searchword : wordsarr) {
			String query = "select d from Day d where name like:search or day like:search";
			daySet.addAll(em.createQuery(query, Day.class ).setParameter
					("search", searchword).getResultList());
		}
		
		return daySet;
	}

}
