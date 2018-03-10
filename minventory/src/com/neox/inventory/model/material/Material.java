package com.neox.inventory.model.material;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.neox.inventory.model.user.UserInfo;
import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "material", catalog = DBUtils.catalog)
public class Material implements Serializable {
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String material;
	private Integer idCategory;
	private Integer idUOM;
	private Integer idMaterialExterno = 1;
	private String agrupador;
	@Type(type="date")
	private Date creationDate;
	@Type(type="time")
	private Date creationTime;
	@Type(type="date")
	private Date modificationDate;
	@Type(type="time")
	private Date modificationTime;
	private String description;
	private String creationUser;
	private String modificationUser;
	private boolean active = true;
	
	
	public Material() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
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
	public Integer getIdMaterialExterno() {
		return idMaterialExterno;
	}
	public void setIdMaterialExterno(Integer idMaterialExterno) {
		this.idMaterialExterno = idMaterialExterno;
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
	
	public void update(MaterialView m) {
		this.material = m.getMaterial();
		this.idCategory = m.getIdCategory();
		this.idUOM = m.getIdUOM();
		this.description = m.getDescription();
		this.agrupador = m.getAgrupador();
		this.active = m.isActive();
	}
	
	@Override
	public String toString() {
		return "{\n"
				+ "\tid:"+id+",\n"
				+ "\tmaterial:"+material+"\n"
				+ "}";
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
	
}
