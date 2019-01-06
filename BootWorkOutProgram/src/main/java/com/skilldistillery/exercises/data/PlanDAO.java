package com.skilldistillery.exercises.data;

import java.util.Set;

import com.skilldistillery.exercises.entities.Plan;

public interface PlanDAO {
	public Plan create(Plan plan);
	public Plan update(int id, Plan plan);
	public boolean destroy(int id);
	public Plan get(int id);
	public Set<Plan> search(String words);

}
