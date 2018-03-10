package com.neox.inventory.web.controller.validation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.area.Validation;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.ValidationService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class ValidationConfig extends Main implements Serializable {
	
	private Validation validation;
	
	public ValidationConfig() {
		validation = new Validation();
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}
	
	public String save() {
		Validation exists = ValidationService.byIdArea(validation.getIdArea());
		if(exists != null) {
			addMessage("Ya existe una validación para esa área", true);
			return null;
		}
		if(!ObjectService.save(validation)) {
			addMessage("Ocurrió un error al agregar la validación", true);
			return null;
		}
		return "index" + redirect;
	}
	
}
