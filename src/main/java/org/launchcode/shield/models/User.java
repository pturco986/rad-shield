package org.launchcode.shield.models;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "user")
public class User extends AbstractEntity {
	
	private String username;
	private String pwHash;
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	private List<Equation> equations;
	
	public User() {}
	
	public User(String username, String password) {
		
		super();
		
		if (!isValidUsername(username)) {
			throw new IllegalArgumentException("Invalid username");
		}
		
		this.username = username;
		this.pwHash = hashPassword(password);
		
	}
	
	@NotNull
    @Column(name = "pwhash")
	public String getPwHash() {
		return pwHash;
	}
	
	@SuppressWarnings("unused")
	private void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}
	
	@NotNull
    @Column(name = "username", unique = true)
	public String getUsername() {
		return username;
	}
	
	private static String hashPassword(String password) {		
		return encoder.encode(password);
	}
	
	@SuppressWarnings("unused")
	private void setUsername(String username) {
		this.username = username;
	}
	// checks that the given password is correct for the user
	//user.is
	public boolean isMatchingPassword(String password) {
		return encoder.matches(password, pwHash);
	}
	//checks the that the password meets minimum standards
	public static boolean isValidPassword(String password) {
		Pattern validUsernamePattern = Pattern.compile("(\\S){6,20}");
		Matcher matcher = validUsernamePattern.matcher(password);
		return matcher.matches();
	}
	//checks that the username meets minimum standards
	public static boolean isValidUsername(String username) {
		Pattern validUsernamePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]{4,11}");
		Matcher matcher = validUsernamePattern.matcher(username);
		return matcher.matches();
	}
	
	protected void addEquation(Equation equation) {
		equations.add(equation);
	}
	
	@OneToMany
    @JoinColumn(name = "author_uid")
	public List<Equation> getEquations() {
		return equations;
	}
	
	public void setEquations(List<Equation> equations) {
		this.equations = equations;
	}
   
}
