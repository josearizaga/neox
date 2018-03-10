package com.neox.inventory.web.controller.uom;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.material.UOM;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class UomConfig extends Main implements Serializable {
	
	private UOM uom;
	
	public UomConfig() {
		uom = new UOM();
	}

	public UOM getUom() {
		return uom;
	}
	
	public String save() {
		uom.setCreationUser(getUsername());
		uom.setModificationUser(getUsername());
		if(!ObjectService.save(uom)) {
			addMessage("Ocurrió un error al agregar la unidad", true);
			return null;
		}
		return "index"+redirect;
	}
	
}
