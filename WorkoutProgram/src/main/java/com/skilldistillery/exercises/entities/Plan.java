package com.skilldistillery.exercises.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@ManyToMany
	@JoinTable(name="day_plan",
			joinColumns=@JoinColumn(name="plan_id"),
			inverseJoinColumns=@JoinColumn(name="day_id"))
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Plan other = (Plan) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public Plan(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Plan() {
		super();
	}
	@Override
	public String toString() {
		return "Plan [id=" + id + ", name=" + name + "]";
	}

}
