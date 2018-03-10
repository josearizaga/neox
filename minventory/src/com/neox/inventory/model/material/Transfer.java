package com.neox.inventory.model.material;

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
@Table(name = "transfer", catalog = DBUtils.catalog)
public class Transfer implements Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private Integer idMaterial;
	private Integer idInventorySource;
	private Integer idInventoryDestination;
	private Double qty;
	@Type(type="date")
	private Date creationDate;
	@Type(type="time")
	private Date creationTime;
	private String creationUser;
	
	public Transfer() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdInventorySource() {
		return idInventorySource;
	}

	public void setIdInventorySource(Integer idInventorySource) {
		this.idInventorySource = idInventorySource;
	}

	public Integer getIdInventoryDestination() {
		return idInventoryDestination;
	}

	public void setIdInventoryDestination(Integer idInventoryDestination) {
		this.idInventoryDestination = idInventoryDestination;
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

	public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}
	

}
