package com.neox.inventory.web.controller.material;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.material.MaterialProviderPrice;
import com.neox.inventory.service.MaterialProviderPriceService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class MaterialProviderConfig extends Main implements Serializable {
	
	private MaterialProviderPrice material;
	
	public MaterialProviderConfig() {
		if(material == null) {
			material = new MaterialProviderPrice();
		}
	}

	public MaterialProviderPrice getMaterial() {
		return material;
	}

	public void setMaterial(MaterialProviderPrice material) {
		this.material = material;
	}
	
	public String save() {
		MaterialProviderPrice valid = MaterialProviderPriceService.byIds(material.getIdMaterial(),material.getIdProvider());
		if(valid != null) {
			material.setId(valid.getId());
		} else {
			material.setCreationUser(getUsername());
		}
		material.setModificationUser(getUsername());
		if(!ObjectService.save(material)) {
			addMessage("Ocurrió un error al agregar el elemento", true);
			return null;
		}
		return "materialProviderPrice" + redirect;
	}
}
