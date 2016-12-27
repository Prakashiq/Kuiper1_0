package com.kuiper.common.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement(name = "Login")
@Document(collection = "User")
public class User {

	private String username;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}
	
	@XmlElement(name="password")
	public void setPassword(String password) {
		this.password = password;
	}
	
	@XmlElement(name="username")
	public void setUsername(String username) {
		this.username = username;
	}
	
}
