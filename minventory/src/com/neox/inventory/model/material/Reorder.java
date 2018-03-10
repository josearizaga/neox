package com.neox.inventory.model.material;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "reorder", catalog = DBUtils.catalog)
public class Reorder {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private Integer idInventory;
	private boolean critical;
	
	public Reorder() {}
	
	public Reorder (Integer idInventory) {
		this.idInventory = idInventory;
		critical = false;
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

	public boolean isCritical() {
		return critical;
	}

	public void setCritical(boolean critical) {
		this.critical = critical;
	}
}
