package org.launchcode.shield.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Equation extends AbstractEntity{
	
	//private float patients; //weekly number of patients per week
	//private float occupancy; //how frequently a is room occupied
	//private float limit; //permissible limit (workers or public)
	//private float distance; //distance from machine to wall
	private double answer; //the amount of radiation that is emitted through the barrier based on thickness
	private String location;
	private Barrier barrier;
	private User author;
	private Date created;
	private Date modified;
	
	public static enum Barrier {
		lead, concrete
	}
	
	
public Equation(String location, Barrier barrier, double answer, User author) {
		
		super();
		this.location = location; //user inputs what hospital, building, and floor, possibly room for where this equation is going to happen.
		this.barrier = barrier;
		this.answer = answer;
		
				//((this.patients * this.occupancy) / (this.limit * (float)Math.pow(this.distance, 2)));
		this.author = author;
		this.created = new Date();
		this.updated();
		
		author.addEquation(this);
	}

public Equation() {}

	@NotNull
	@Column (name = "location")
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
		this.updated();
	}
	
	@NotNull
	@Column (name="barrier")
	public Barrier getBarrier() {
		return barrier;
	}
	
	public void setBarrier(Barrier barrier) {
		this.barrier = barrier;
		this.updated();
	}

	@NotNull
	@Column (name = "answer")
	public double getAnswer() {
		return answer;
	}

	public void setAnswer(double answer) {
		this.answer = answer;
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

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	private void updated() {
		this.modified = new Date();
	}
	

}