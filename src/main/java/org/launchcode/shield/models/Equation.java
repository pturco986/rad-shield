package org.launchcode.shield.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


	//NOTE
	//For the purposes of this program, the first version will only do the equation
	//And assume that the wall is the primary wall, that the machine is the same,
	//And that everything else beyond what is input by a user remains constant
@Entity
@Table(name = "equation")
public class Equation {
	
	private float patients; //weekly number of patients per week
	private float occupancy; //how frequently a is room occupied
	private float limit; //permissible limit (workers or public)
	private float distance; //distance from machine to wall
	private float transmission; //the amount of radiation that is emitted through the barrier based on thickness
	
	private User author;
	private Date created;
	private Date modified;
	
	
public Equation(String patients, String occupancy, String limit, String distance, User author) {
		
		super();
	
		this.patients = Float.parseFloat(patients);
		this.occupancy = Float.parseFloat(occupancy);
		this.limit = Float.parseFloat(limit);
		this.distance = Float.parseFloat(distance);
		this.transmission = ((this.patients * this.occupancy) / (this.limit * (float)Math.pow(this.distance, 2)));
		this.author = author;
		this.created = new Date();
		this.updated();
		
		author.addEquation(this);
	}

public Equation() {}

private int uid;

@Id
@GeneratedValue
@NotNull
@Column(name = "uid", unique = true)
public int getUid() {
	return this.uid;
}

protected void setUid(int uid) {
	this.uid = uid;
}


	@NotNull
	@Column(name = "patients")
	public float getPatients() {
		return patients;
	}
	
	public void setPatients(float patients) {
		this.patients = patients;
	}
	
	@NotNull
	@Column (name = "occupancy")
	public float getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(float occupancy) {
		this.occupancy = occupancy;
	}
	
	@NotNull
	@Column (name = "limit")
	public float getLimit() {
		return limit;
	}

	public void setLimit(float limit) {
		this.limit = limit;
	}
	
	@NotNull
	@Column (name = "distance")
	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	@NotNull
	@Column (name = "transmission")
	public float getTransmission() {
		return transmission;
	}

	public void setTransmission(float transmission) {
		this.transmission = transmission;
	}
	
	@ManyToOne
	public User getAuthor() {
		return author;
	}

	@SuppressWarnings("unused")
	private void setAuthor(User author) {
		this.author = author;
	}
	
	@NotNull
	@OrderColumn
	@Column(name = "created")
	public Date getCreated() {
		return created;
	}

	@SuppressWarnings("unused")
	private void setCreated(Date created) {
		this.created = created;
	}
	
	@NotNull
	@Column(name = "modified")
	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	private void updated() {
		this.modified = new Date();
	}
	

}