package com.neox.inventory.web.controller.location;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.area.Location;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class LocationConfig extends Main implements Serializable {
	
	private Location location;
	
	public LocationConfig() {
		location = new Location();
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String save() {
		String user = getUsername();
		location.setCreationUser(user);
		location.setModificationUser(user);
		if(!ObjectService.save(location)) {
			addMessage("Ocurrió un error al agregar la ubicación", true);
			return null;
		}
		return "index"+redirect;
	}
	
}
