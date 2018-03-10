package com.neox.inventory.model.material;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "category", catalog = DBUtils.catalog)
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -81617016429573288L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private Integer idCategory = 0;
	private String category;
	private String tabla;
	private String prefijo;
	@Column(name = "descripcion")
	private String description;
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
	
	public Category() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
				+ "\tidCategory:"+idCategory+",\n"
				+ "\tcategory:"+category+",\n"
				+ "\tdescription:"+description+",\n"
				+ "\tcreationDate:"+creationDate+",\n"
				+ "\tcreationTime:"+creationTime+",\n"
				+ "\tmodificationDate:"+modificationDate+",\n"
				+ "\tmodificationTime:"+modificationTime+"\n"
				+ "\n}";
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
