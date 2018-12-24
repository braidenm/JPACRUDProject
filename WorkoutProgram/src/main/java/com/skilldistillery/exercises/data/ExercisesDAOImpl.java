package com.skilldistillery.exercises.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.exercises.entities.Exercises;

public class ExercisesDAOImpl implements ExerciseDAO {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExercisesDB");
	private EntityManager em = emf.createEntityManager();

	@Override
	public Exercises create(Exercises exercise) {
		em.getTransaction().begin();
		
		em.persist(exercise);
		
		em.flush();
		
		em.getTransaction().commit();
		return exercise;
	}

	@Override
	public Exercises update(int id, Exercises exercise) {
		em.getTransaction().begin();
		Exercises managed = em.find(Exercises.class, id);
		
		managed.setName(exercise.getName());
		managed.setCategory(exercise.getCategory());
		managed.setDescription(exercise.getDescription());
		managed.setSets(exercise.getSets());
		managed.setReps(exercise.getReps());
		managed.setRest(exercise.getRest());
		
		em.flush();
		em.getTransaction().commit();
		
		return managed;
	}

	@Override
	public boolean destroy(int id) {
		em.getTransaction().begin();
		Exercises ex = em.find(Exercises.class, id);
		em.remove(ex);
		
		em.getTransaction().commit();
		
		return !em.contains(ex);
	}

	@Override
	public Exercises get(int id) {
		
		
		return em.find(Exercises.class, id);
	}

}
