package com.neox.inventory.model.menu;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "usermenu", catalog = DBUtils.catalog)
public class UserMenu implements Serializable {
	
	@Id
	private String id;
	private Integer idMenu;
	private Integer idUser;
	
	public UserMenu() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	
}
