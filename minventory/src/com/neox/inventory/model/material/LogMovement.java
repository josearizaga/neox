package com.neox.inventory.model.material;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "logmovement", catalog = DBUtils.catalog)
public class LogMovement {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private Integer idHeader;
	private Integer position;
	private Integer idStatus;
	private String user;
	@Type(type="date")
	private Date creationDate;
	@Type(type="time")
	private Date creationTime;
	
	public LogMovement() {
		creationDate = new Date();
		creationTime = new Date();
	}
	
	public LogMovement(MaterialMovement mm) {
		this.idHeader = mm.getIdHeader();
		this.position = mm.getPosition();
		this.idStatus = mm.getIdStatus();
		creationDate = new Date();
		creationTime = new Date();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdHeader() {
		return idHeader;
	}
	public void setIdHeader(Integer idHeader) {
		this.idHeader = idHeader;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	
}
