package com.neox.inventory.web.controller.inventory;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.InventoryHistory;
import com.neox.inventory.model.material.InventoryView;
import com.neox.inventory.service.InventoryService;
import com.neox.inventory.service.InventoryViewService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class InventoryViewEdit extends Main implements Serializable {
	
	@ManagedProperty(value = "#{selectedInventory}")
	InventoryView inventory;
	
	public InventoryViewEdit() {}

	public InventoryView getInventory() {
		return inventory;
	}

	public void setInventory(InventoryView inventory) {
		this.inventory = inventory;
	}
	
	public String save() {
		Inventory edited = InventoryService.byId(inventory.getId());
		edited.setQty(inventory.getQty());
		edited.setMinQty(inventory.getMinQty());
		edited.setOrderPoint(inventory.getOrderPoint());
		edited.setModificationUser(getUsername());
		
		
		
		if(!ObjectService.save(edited)) {
			addMessage("Ocurrió un error al guardar la información", true);
			return null;
		}
		
		InventoryHistory history = new InventoryHistory(edited.getIdProvider(), getProviderName(edited.getIdProvider()),edited.getPrice());
		history.setCreationUser(getUsername());
		history.setIdInventory(edited.getId());
		if(!ObjectService.save(history)) {
			addMessage("Ocurrió un error al guardar la información", true);
			return null;
		}
		return "index" + redirect;
	}
	
}
