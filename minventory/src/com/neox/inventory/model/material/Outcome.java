package com.neox.inventory.model.material;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "outcome", catalog = DBUtils.catalog)
public class Outcome {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private Integer idMaterialMovement;
	private Integer idInitialStatus;
	private Integer idFinalStatus;
	private Integer idMovement;
	private Integer idInventory;
	private Double qty;
	@Type(type="date")
	private Date creationDate;
	@Type(type="time")
	private Date creationTime;
	private String creationUser;
	
	@Transient
	private String username;
	@Transient
	private String name;
	
	public Outcome() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdMaterialMovement() {
		return idMaterialMovement;
	}

	public void setIdMaterialMovement(Integer idMaterialMovement) {
		this.idMaterialMovement = idMaterialMovement;
	}

	public Integer getIdInitialStatus() {
		return idInitialStatus;
	}

	public void setIdInitialStatus(Integer idInitialStatus) {
		this.idInitialStatus = idInitialStatus;
	}

	public Integer getIdFinalStatus() {
		return idFinalStatus;
	}

	public void setIdFinalStatus(Integer idFinalStatus) {
		this.idFinalStatus = idFinalStatus;
	}

	public Integer getIdMovement() {
		return idMovement;
	}

	public void setIdMovement(Integer idMovement) {
		this.idMovement = idMovement;
	}

	public Integer getIdInventory() {
		return idInventory;
	}

	public void setIdInventory(Integer idInventory) {
		this.idInventory = idInventory;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
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

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
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
	
	public void add(Double qty) {
		this.qty += qty;
	}
	
}
