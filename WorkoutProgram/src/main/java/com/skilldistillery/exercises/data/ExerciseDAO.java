package com.skilldistillery.exercises.data;

import com.skilldistillery.exercises.entities.Exercises;

public interface ExerciseDAO {
	public Exercises create(Exercises exercise);
	public Exercises update(int id, Exercises exercise);
	public boolean destroy(int id);
	public Exercises get(int id);

}
