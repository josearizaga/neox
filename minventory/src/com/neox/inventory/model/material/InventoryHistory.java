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
@Table(name = "inventoryhistory", catalog = DBUtils.catalog)
public class InventoryHistory implements Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private Double qty;
	private Integer idInventory;
	private Integer idMovement;
	private Integer idMaterial;
	private Integer idLocation;
	private Integer idProvider;
	private String material;
	private String location;
	private String provider;
	private Double price;
	@Type(type="date")
	private Date creationDate;
	@Type(type="time")
	private Date creationTime;
	private String creationUser;
	
	public InventoryHistory() {}
	
	public InventoryHistory(Integer idMaterial, String material, Integer idLocation, String location, Integer idProvider, 
			String provider, Double price, Double qty) {
		this.idMaterial = idMaterial;
		this.material = material;
		this.idLocation = idLocation;
		this.location = location;
		this.idProvider = idProvider;
		this.provider = provider;
		this.price = price;
		this.qty = qty;
	}
	
	public InventoryHistory(Integer idProvider, String provider, Double price) {
		this.idProvider = idProvider;
		this.provider = provider;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdInventory() {
		return idInventory;
	}

	public void setIdInventory(Integer idInventory) {
		this.idInventory = idInventory;
	}

	public Integer getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(Integer idProvider) {
		this.idProvider = idProvider;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Integer getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Integer idLocation) {
		this.idLocation = idLocation;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getIdMovement() {
		return idMovement;
	}

	public void setIdMovement(Integer idMovement) {
		this.idMovement = idMovement;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}
	
}
