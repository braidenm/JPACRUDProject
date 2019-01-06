package com.skilldistillery.exercises.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="workout")
public class Exercises {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String category;
	private String description;
	private int sets;
	private int reps;
	@Column(name="rest_in_sec")
	private int rest;
	@ManyToMany(mappedBy="exercises")
	private List<Day> days;
	
	
	
	public List<Day> getDays() {
		return days;
	}
	public void setDays(List<Day> days) {
		this.days = days;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
	public int getRest() {
		return rest;
	}
	public void setRest(int rest) {
		this.rest = rest;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + reps;
		result = prime * result + rest;
		result = prime * result + sets;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exercises other = (Exercises) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reps != other.reps)
			return false;
		if (rest != other.rest)
			return false;
		if (sets != other.sets)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Exercises [id=" + id + ", name=" + name + ", category=" + category + ", description=" + description
				+ ", sets=" + sets + ", reps=" + reps + ", rest=" + rest + "]";
	}
	public Exercises(int id, String name, String category, String description, int sets, int reps, int rest) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.sets = sets;
		this.reps = reps;
		this.rest = rest;
	}
	public Exercises() {
		super();
	}
	
	

}


