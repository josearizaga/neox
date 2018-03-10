package com.neox.inventory.model.area;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "areamaterialview", catalog = DBUtils.catalog)
public class AreaMaterialView {
	
	@Id
	private Integer id;
	private Integer idArea;
	private Integer idMaterial;
	private String area;
	private String areadesc;
	private String material;
	private String category;
	private String uom;
	private String uomdesc;
	
	public AreaMaterialView() {}
	
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAreadesc() {
		return areadesc;
	}
	public void setAreadesc(String areadesc) {
		this.areadesc = areadesc;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getUomdesc() {
		return uomdesc;
	}
	public void setUomdesc(String uomdesc) {
		this.uomdesc = uomdesc;
	}

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}
	
	@Override
	public String toString() {
		return "{\n"
				+ "\tid:"+id+",\n"
				+ "\tidArea:"+idArea+",\n"
				+ "\tarea:"+area+",\n"
				+ "\tmaterial:"+material+",\n"
				+ "}";
	}
}
