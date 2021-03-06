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
	private double thickness; // the thickness of the barrier needed.
	private String location;
	private String barrier;
	private String preshield;
	private String walltype;
	private User author;
	private Date created;
	private Date modified;
	
	public static enum Barrier {
		lead, concrete
	}
	
	
public Equation(String location, String barrier, String preshield, String walltype, double answer, double thickness, User author) {
		
		super();
		this.location = location; //user inputs what hospital, building, and floor, possibly room for where this equation is going to happen.
		this.barrier = barrier;
		this.preshield = preshield;
		this.walltype = walltype;
		this.answer = answer;
		this.thickness = thickness;
		
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
	public String getBarrier() {
		return barrier;
	}
	
	public void setBarrier(String barrier) {
		this.barrier = barrier;
		this.updated();
	}
	
	@NotNull
	@Column (name="preshield")
	public String getPreshield() {
		return preshield;
	}
	
	public void setPreshield(String preshield) {
		this.preshield = preshield;
		this.updated();
	}
	
	@NotNull
	@Column (name = "walltype")
	public String getWalltype() {
		return walltype;
	}
	
	public void setWalltype(String walltype) {
		this.walltype = walltype;
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
	
	@NotNull
	@Column (name = "thickness")
	public double getThickness() {
		return thickness;
	}
	
	public void setThickness(double thickness) {
		this.thickness = thickness;
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