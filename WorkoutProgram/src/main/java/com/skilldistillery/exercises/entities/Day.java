package com.skilldistillery.exercises.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Day {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String day;
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
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Day other = (Day) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public Day(int id, String name, String day) {
		super();
		this.id = id;
		this.name = name;
		this.day = day;
	}
	public Day() {
		super();
	}
	@Override
	public String toString() {
		return "Day [id=" + id + ", name=" + name + ", day=" + day + "]";
	}
	
	
}
