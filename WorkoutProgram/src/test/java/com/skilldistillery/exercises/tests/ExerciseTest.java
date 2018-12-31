package com.skilldistillery.exercises.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skilldistillery.exercises.data.ExerciseDAO;
import com.skilldistillery.exercises.data.ExercisesDAOImpl;
import com.skilldistillery.exercises.entities.Exercises;

class ExerciseTest {
	
	private static EntityManagerFactory emf; 
	private EntityManager em;
	private ExerciseDAO dao;
	private Exercises ex;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("ExercisesDB");
		
	}
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		 em = emf.createEntityManager();
		 dao = new ExercisesDAOImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

//	I updated the databse with another test so check the database for updated info
//	@Test
//	@DisplayName("test exercises dao get method")
//	void test_exercises_dao_get() {
//		
//		ex = dao.get(1);
//		
//		assertEquals("curls", ex.getName());
//		assertEquals("bicepts", ex.getCategory());
//		assertEquals("grab a dumbell and lift it", ex.getDescription());
//		assertEquals(10, ex.getReps());
//		assertEquals(4, ex.getSets());
//		assertEquals(30, ex.getRest());
//	}
	
	//makes new row, so only test when needed
//	@Test
//	@DisplayName("test exercises dao create method")
//	void test_exercises_dao_create() {
//		Exercises notManaged = new Exercises(0, "pullups", "back", "grab the bar above you and pull yourself up", 4, 10, 1);
//		Exercises exer = dao.create(notManaged);
//		
//		assertEquals(6, exer.getId());
//		assertEquals("pullups", exer.getName());
//		assertEquals("back", exer.getCategory());
//		assertEquals("grab the bar above you and pull yourself up", exer.getDescription());
//		assertEquals(10, exer.getReps());
//		assertEquals(4, exer.getSets());
//		assertEquals(1, exer.getRest());
//	}
	
	//deletes rows, so only test when needed
//	@Test
//	@DisplayName("test that the dao delete returns true")
//	public void test_dao_delete() {
//		assertTrue(dao.destroy(3));
//	}
	
	//updates database so only use when needed
//	@Test
//	@DisplayName("tests the dao for update and returns updated entity")
//	public void test_update_dao() {
//		
//		Exercises notManaged = new Exercises(1, "curls", "bicepts", "grab a weigth, with pulm up pull the weight upwards keeping your elbow by your side", 4, 10, 1);
//		Exercises exer = dao.update(1, notManaged);
//		assertEquals("curls", exer.getName());
//		assertEquals("bicepts", exer.getCategory());
//		assertEquals("grab a weigth, with pulm up pull the weight upwards keeping your elbow by your side", exer.getDescription());
//		assertEquals(10, exer.getReps());
//		assertEquals(4, exer.getSets());
//		assertEquals(1, exer.getRest());		
//	}
	
	@Test
	@DisplayName("test that the search function returns correct value")
	public void test_search() {
		String keyword = "cur Ups che";
		Set<Exercises> exercisteset = dao.search(keyword);
		
		assertFalse( exercisteset.isEmpty());
		assertEquals(4, exercisteset.size());
		assertTrue(exercisteset.contains(dao.get(1)));
		assertTrue(exercisteset.contains(dao.get(2)));
		assertTrue(exercisteset.contains(dao.get(3)));
		assertTrue(exercisteset.contains(dao.get(4)));
		
		
	}

}
