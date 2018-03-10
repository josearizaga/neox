package com.neox.inventory.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.neox.inventory.util.DBUtils;


@Entity
@Table(name = "userinfo", catalog = DBUtils.catalog)
public class UserInfo implements Serializable {
	
	@Id
	private int idUser;
	@Column(name = "name", nullable = false, length = 64)
	private String name;
	@Column(name = "lastname", length = 64)
	private String lastname;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;

	public UserInfo() {
	}

	public UserInfo(int idUser, String name) {
		this.idUser = idUser;
		this.name = name;
	}

	public UserInfo(int idUser, String name, String lastname) {
		this.idUser = idUser;
		this.name = name;
		this.lastname = lastname;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserInfo))
			return false;
		UserInfo castOther = (UserInfo) other;

		return (this.getIdUser() == castOther.getIdUser())
				&& ((this.getName() == castOther.getName()) || (this.getName() != null && castOther.getName() != null
						&& this.getName().equals(castOther.getName())))
				&& ((this.getLastname() == castOther.getLastname()) || (this.getLastname() != null
						&& castOther.getLastname() != null && this.getLastname().equals(castOther.getLastname())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdUser();
		result = 37 * result + (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result + (getLastname() == null ? 0 : this.getLastname().hashCode());
		return result;
	}
	
	public String toString() {
		return name + " " + lastname;
	}
	
}
