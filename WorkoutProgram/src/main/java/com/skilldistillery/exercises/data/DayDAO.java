package com.skilldistillery.exercises.data;

import java.util.List;
import java.util.Set;

import com.skilldistillery.exercises.entities.Day;
import com.skilldistillery.exercises.entities.Plan;

public interface DayDAO {
	public Day create(Day day);
	public Day update(int id, Day day);
	public boolean destroy(int id);
	public Day get(int id);
	public Set<Day> search(String words);
	public List<Plan> getAll();
}
