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

import com.skilldistillery.exercises.data.PlanDAO;
import com.skilldistillery.exercises.data.PlanDAOImpl;
import com.skilldistillery.exercises.entities.Plan;

class PlanTest {
	
	private static EntityManagerFactory emf; 
	private EntityManager em;
	private PlanDAO dao;
	private Plan plan;

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
		 dao = new PlanDAOImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("test exercises dao get method")
	void test_exercises_dao_get() {
		
		plan = dao.get(1);
		
		assertEquals("Get Jacked", plan.getName());
	}
	
//	makes new row, so only test when needed
//	@Test
//	@DisplayName("test exercises dao create method")
//	void test_exercises_dao_create() {
//		Plan notManaged = new Plan(0, "Test");
//		Plan day = dao.create(notManaged);
//		
//		assertEquals(2, day.getId());
//		assertEquals("Test", day.getName());
//	}
	
//	deletes rows, so only test when needed
//	@Test
//	@DisplayName("test that the dao delete returns true")
//	public void test_dao_delete() {
//		assertTrue(dao.destroy(2));
//	}
	
//	updates database so only use when needed
//	@Test
//	@DisplayName("tests the dao for update and returns updated entity")
//	public void test_update_dao() {
//		
//		Plan notManaged = new Plan(2, "Test test");
//		Plan day = dao.update(2, notManaged);
//		assertEquals("Test test", day.getName());
//	}
	
	@Test
	@DisplayName("test that the search function returns correct value")
	public void test_search() {
		String keyword = "jack";
		Set<Plan> exercisteset = dao.search(keyword);
		
		assertFalse( exercisteset.isEmpty());
		assertEquals(1, exercisteset.size());
		assertTrue(exercisteset.contains(dao.get(1)));
		
		
	}
	
	@Test
	public void test_many_to_many_mapping_day() {
		plan = dao.get(1);
		
		assertNotNull(plan.getDays());
		assertFalse(plan.getDays().isEmpty());
		assertEquals(4, plan.getDays().size());
	}

}
