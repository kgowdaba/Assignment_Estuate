package com.ex.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eid;
	
	private String name;
	
	private String rating;

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Long getEid() {
		return eid;
	}

	public String getName() {
		return name;
	}

	public String getRating() {
		return rating;
	}
	

}
