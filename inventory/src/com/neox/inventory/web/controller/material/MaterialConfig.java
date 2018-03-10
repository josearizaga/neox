package com.neox.inventory.web.controller.material;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.material.Material;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class MaterialConfig extends Main {
	
	private Material material;
	
	public MaterialConfig() {
		material = new Material();
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public String save() {
		material.setCreationUser(getUsername());
		material.setModificationUser(getUsername());
		if(!ObjectService.save(material)) {
			addMessage("Ocurrió un error al agregar el material", true);
			return null;
		}
		return "index"+redirect;
	}
	
}
