package com.neox.inventory.model.user;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.neox.inventory.util.DBUtils;


@Entity
@Table(name = "user", catalog = DBUtils.catalog, uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private String username;
	private String passwd;
	private boolean active;
	private boolean admin;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private UserInfo userInfo;

	public User() {
	}

	public User(String username, String passwd) {
		this.username = username;
		this.passwd = passwd;
	}
	
	public User(String username, String passwd, Boolean active) {
		this.username = username;
		this.passwd = passwd;
		this.active = active;
	}
	
	
	public User(String username, String passwd, Boolean active, UserInfo userInfo) {
		this.username = username;
		this.passwd = passwd;
		this.active = active;
		this.userInfo = userInfo;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public String toString() {
		return id + ": " + username + ": ["+passwd+"] " +  userInfo;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public void newInfo(String firstName, String lastName) {
		userInfo = new UserInfo(id, firstName, lastName);
	}

}
