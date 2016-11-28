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
	
	private float patients; //number of patients
	private float workload; //average workload (dose) per patient (typically .6)
	private float limit; //permissible limit (is it open for public or only workers?)
	private float useFactor; //User factor should always equal 1
	private float distance; //distance from machine to wall
	
	private User author;
	private Date created;
	private Date modified;
	
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
	
	public Equation(String patients, String workload, String limit, String useFactor, String distance, User author) {
		
		super();
		
		float barrier; //The final result of these
		this.patients = Float.parseFloat(patients);
		this.workload = Float.parseFloat(workload);
		this.limit = Float.parseFloat(limit);
		this.useFactor = Float.parseFloat(useFactor);
		this.distance = Float.parseFloat(distance);
		this.author = author;
		this.created = new Date();
		this.updated();
		barrier = (this.limit)*((float)(Math.pow(this.distance, 2)))/(this.workload*this.patients);
		author.addEquation(this);
	}
	
	@NotNull
	@Column(name = "patients")
	public Float getPatients() {
		return patients;
	}

	public void setPatients(Float patients) {
		this.patients = patients;
		this.updated();
	}
	
	@NotNull
	@Column(name = "Workload")
	public float getWorkload() {
		return workload;
	}

	public void setWorkload(float workload) {
		this.workload = workload;
		this.updated();
	}
	
	@NotNull
	@Column(name = "limit")
	public float getLimit() {
		return limit;
	}

	public void setLimit(float limit) {
		this.limit = limit;
		this.updated();
	}
	
	@NotNull
	@Column(name = "usefactor")
	public float getUseFactor() {
		return useFactor;
	}

	public void setUseFactor(float useFactor) {
		this.useFactor = useFactor;
		this.updated();
	}
	
	@NotNull
	@Column(name = "distance")
	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
		this.updated();
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
	
	@SuppressWarnings("unused")
	private void setModified(Date modified) {
		this.modified = modified;
	}
	
	private void updated() {
		this.modified = new Date();
	}
	
	
}
