package com.neox.inventory.model.area;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "areauserview", catalog = DBUtils.catalog)
public class AreaUserView {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private Integer idArea;
	private Integer idUser;
	private String area;
	private String username;
	private String name;
	
	public AreaUserView() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdArea() {
		return idArea;
	}
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	
	public String toString() {
		return "{"
				+ "id:"+id+",\n"
				+ "idArea:"+idArea+",\n"
				+ "idUser:"+idUser+",\n"
				+ "area:"+area+",\n"
				+ "username:"+username+",\n"
				+ "name:"+name+",\n"
				+ "}";
	}
	
}
