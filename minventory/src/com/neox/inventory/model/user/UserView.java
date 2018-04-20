package com.neox.inventory.model.user;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "userview", catalog = DBUtils.catalog)
public class UserView implements Serializable {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "username", nullable = false, length = 16)
	private String username;
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	@Column(name = "active")
	private Boolean active;
	
	private String firstname;
	private String lastname;
	
	@Transient
	private String passwd;
	
	public UserView() {}
	
	public UserView(Integer id, String username, String name, boolean active) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.active = active;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return String.format("[%d %s:%s]", id, username, name);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		passwd.toUpperCase();
		this.passwd = passwd;
	}
}
