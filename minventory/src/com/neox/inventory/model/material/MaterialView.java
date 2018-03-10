package com.neox.inventory.model.material;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "materialview", catalog = DBUtils.catalog)
public class MaterialView {
	
	@Id	
	private int id;
	private String material;
	private String category;
	private String uom;
	private String agrupador;
	@Type(type="date")
	@Column(name = "fecha_creacion")
	private Date creationDate;
	@Type(type="time")
	@Column(name = "hora_creacion")
	private Date creationTime;
	@Type(type="date")
	@Column(name = "fecha_modificacion")
	private Date modificationDate;
	@Type(type="time")
	@Column(name = "hora_modificacion")
	private Date modificationTime;
	private String description;
	private boolean active;
	@Transient
	private Integer idCategory;
	@Transient
	private Integer idUOM;
	
	
	public MaterialView() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getAgrupador() {
		return agrupador;
	}

	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "{\n"
				+ "\tid:"+id+",\n"
				+ "\tmaterial:"+material+",\n"
				+ "\tcategory:"+category+",\n"
				+ "\tuom:"+uom+"\n"
				+ "}";
	}

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	public Integer getIdUOM() {
		return idUOM;
	}

	public void setIdUOM(Integer idUOM) {
		this.idUOM = idUOM;
	}
}
