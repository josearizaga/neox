package com.neox.inventory.web.controller.area;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.area.Area;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class AreaEdit extends Main implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7120519459047940421L;
	@ManagedProperty(value = "#{selectedArea}")
	private Area selectedArea;
	
	public AreaEdit() {}
		
		public Area getSelectedArea() {
		return selectedArea;
	}

	public void setSelectedArea(Area selectedArea) {
		this.selectedArea = selectedArea;
	}
	
	public String save() {
		if(!ObjectService.save(selectedArea)) {
			addMessage("Ocurrió un error al actualizar los datos", true);
		}
		return "index" + redirect;
	}
	
}
