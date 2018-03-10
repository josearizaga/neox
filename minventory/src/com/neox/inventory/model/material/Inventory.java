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
@Table(name = "inventory", catalog = DBUtils.catalog)
public class Inventory implements Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private Integer idMaterial;
	private Integer idLocation;
	private int idProvider;
	private Double price;
	private Double qty;
	private Double minQty;
	private Double orderPoint;
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
	
	public Inventory(Inventory source) {
		super();
		this.minQty = source.getMinQty();
		this.orderPoint = source.getOrderPoint();
		this.price = source.getPrice();
		this.idMaterial = source.getIdMaterial();
		this.idLocation = source.getIdLocation();
		this.idProvider = source.getIdProvider();
	}
	
	public Inventory() {
		qty = minQty = orderPoint = new Double(0);
		creationDate = creationTime = modificationDate = modificationTime = new Date();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Double getMinQty() {
		return minQty;
	}
	public void setMinQty(Double minQty) {
		this.minQty = minQty;
	}
	public Double getOrderPoint() {
		return orderPoint;
	}
	public void setOrderPoint(Double orderPoint) {
		this.orderPoint = orderPoint;
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

	public int getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(int idProvider) {
		this.idProvider = idProvider;
	}
	
	@Override
	public String toString() {
		return "{\n"
				+ "\tid:"+id+",\n"
				+ "\tidMaterial:"+idMaterial+",\n"
				+ "\tidLocation:"+idLocation+",\n"
				+ "\tidProvider:"+idProvider+",\n"
				+ "\tqty:"+qty+",\n"
				+ "\tminQty:"+minQty+",\n"
				+ "\torderPoint:"+orderPoint+"\n"
				+ "}";
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void add(Double qty) {
		this.qty += qty;
	}
	
}
