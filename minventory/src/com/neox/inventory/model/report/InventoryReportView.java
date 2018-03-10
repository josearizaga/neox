package com.neox.inventory.model.report;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "inventoryreportview", catalog = DBUtils.catalog)
public class InventoryReportView {
	
	@Id
	private Integer id;
	private Integer idMaterial;
	private String material;
	private String category;
	private String location;
	private Double qty;
	private Double minQty;
	private Double orderPoint;
	private String provider;
	private Double price;
	
	public InventoryReportView() {}

	public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public String toString() {
		return "{"
				+ "id:"+id+",\n"
				+ "idMaterial:"+idMaterial+",\n"
				+ "material: "+material+",\n"
				+ "provider: "+provider+",\n"
				+ "location: "+location+",\n"
				+ "}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
