package com.skilldistillery.exercises.data;

import java.util.Set;

import com.skilldistillery.exercises.entities.Day;

public interface DayDAO {
	public Day create(Day day);
	public Day update(int id, Day day);
	public boolean destroy(int id);
	public Day get(int id);
	public Set<Day> search(String words);

}
