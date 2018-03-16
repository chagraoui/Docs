package org.tux.entites;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	private String email;
	private String password;
	
	public User(String login, String pasword) {
		super();
		this.email = login;
		this.password = pasword;
	}
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String login) {
		this.email = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPasword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [login=" + email + ", pasword=" + password + "]";
	}
	
}
