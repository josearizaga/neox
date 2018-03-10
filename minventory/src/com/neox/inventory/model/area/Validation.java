package com.neox.inventory.model.area;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "validation", catalog = DBUtils.catalog)
public class Validation implements Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private Integer idArea;
	private Integer idValidation = 0;
	private Double top;
	@Type(type="date")
	private Date creationDate;
	@Type(type="time")
	private Date creationTime;
	@Type(type="date")
	private Date modificationDate;
	@Type(type="time")
	private Date modificationTime;
	private String creationUser;
	private String modificationUser;
	private boolean active = true;
	
	public Validation() {}

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

	public Integer getIdValidation() {
		return idValidation;
	}

	public void setIdValidation(Integer idValidation) {
		this.idValidation = idValidation;
	}

	public Double getTop() {
		return top;
	}

	public void setTop(Double top) {
		this.top = top;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}
	
	@Override
	public String toString() {
		return "id:"+id+",idArea:"+idArea+",idValidation:"+idValidation+",top:"+top;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public String getModificationUser() {
		return modificationUser;
	}

	public void setModificationUser(String modificationUser) {
		this.modificationUser = modificationUser;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
