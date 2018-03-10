package com.neox.inventory.web.controller.area;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.area.Area;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class AreaConfig extends Main implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2162888875208395846L;
	private Area area;
	
	public AreaConfig() {
		area = new Area();
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public String save() {
		area.setCreationUser(getUsername());
		area.setModificationUser(getUsername());
		if(!ObjectService.save(area)) {
			addMessage("Ocurrió un error al agregar el área", true);
			return null;
		}
		return "index"+redirect;
	}
	
}
