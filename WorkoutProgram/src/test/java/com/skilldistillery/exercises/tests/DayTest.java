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

import com.skilldistillery.exercises.data.DayDAO;
import com.skilldistillery.exercises.data.DayDAOImpl;
import com.skilldistillery.exercises.data.ExerciseDAO;
import com.skilldistillery.exercises.data.ExercisesDAOImpl;
import com.skilldistillery.exercises.entities.Day;
import com.skilldistillery.exercises.entities.Exercises;

class DayTest {
	
	private static EntityManagerFactory emf; 
	private EntityManager em;
	private DayDAO dao;
	private Day day;

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
		 dao = new DayDAOImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("test exercises dao get method")
	void test_exercises_dao_get() {
		
		day = dao.get(1);
		
		assertEquals("Back and Biceps", day.getName());
		assertEquals("Monday", day.getDay());
	}
	
	//makes new row, so only test when needed
//	@Test
//	@DisplayName("test exercises dao create method")
//	void test_exercises_dao_create() {
//		Day notManaged = new Day(0, "Test", "Testerday");
//		Day day = dao.create(notManaged);
//		
//		assertEquals(7, day.getId());
//		assertEquals("Test", day.getName());
//		assertEquals("Testerday", day.getDay());
//	}
	
	//deletes rows, so only test when needed
//	@Test
//	@DisplayName("test that the dao delete returns true")
//	public void test_dao_delete() {
//		assertTrue(dao.destroy(7));
//	}
	
	//updates database so only use when needed
//	@Test
//	@DisplayName("tests the dao for update and returns updated entity")
//	public void test_update_dao() {
//		
//		Day notManaged = new Day(7, "Test test", "TestyMcTestDay");
//		Day day = dao.update(7, notManaged);
//		assertEquals("Test test", day.getName());
//	}
	
	@Test
	@DisplayName("test that the search function returns correct value")
	public void test_search() {
		String keyword = "ack ice leg ab";
		Set<Day> exercisteset = dao.search(keyword);
		
		assertFalse( exercisteset.isEmpty());
		assertEquals(4, exercisteset.size());
		assertTrue(exercisteset.contains(dao.get(1)));
		assertTrue(exercisteset.contains(dao.get(2)));
		assertTrue(exercisteset.contains(dao.get(3)));
		assertTrue(exercisteset.contains(dao.get(4)));
		
		
	}
	
	@Test
	public void test_many_to_many_mapping_plan() {
		day = dao.get(1);
		
		assertNotNull(day.getPlans());
		assertFalse(day.getPlans().isEmpty());
		assertEquals(1, day.getPlans().size());
	}
	@Test
	public void test_many_to_many_mapping_exercises() {
		day = dao.get(1);
		ExerciseDAO exerdao = new ExercisesDAOImpl();
		//all the days exercises should contain number 2
		Exercises exer2 = exerdao.get(2);
		Exercises exer = exerdao.get(1);
		
		assertNotNull(day.getExercises());
		assertFalse(day.getExercises().isEmpty());
		assertEquals(9, day.getExercises().size());
		assertTrue(day.getExercises().contains(exer));
		assertTrue(day.getExercises().contains(exer2));
	}

}
