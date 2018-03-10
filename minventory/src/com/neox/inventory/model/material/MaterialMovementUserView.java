package com.neox.inventory.model.material;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "materialmovementuserview", catalog = DBUtils.catalog)
public class MaterialMovementUserView {

	@Id
	private Integer id;
	private String username;
	private String name;
	
	public MaterialMovementUserView() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
}
