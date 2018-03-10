package com.neox.inventory.web.controller.inventory;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;

import com.neox.inventory.model.area.Area;
import com.neox.inventory.model.area.Location;
import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.Material;
import com.neox.inventory.service.InventoryService;
import com.neox.inventory.service.LocationService;
import com.neox.inventory.service.MaterialService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class InventoryList extends Main implements Serializable {
	
	private List<Inventory> list;
	private List<Inventory> filtered;
	private Inventory selected;
	
	public InventoryList() {}
	
	public List<Inventory> getList() {
		if(list == null) {
			list = InventoryService.getList();
		}
		return list;
	}
	public void setList(List<Inventory> list) {
		this.list = list;
	}
	public List<Inventory> getFiltered() {
		return filtered;
	}
	public void setFiltered(List<Inventory> filtered) {
		this.filtered = filtered;
	}
	public Inventory getSelected() {
		return selected;
	}
	public void setSelected(Inventory selected) {
		this.selected = selected;
	}
	
	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		
		
		if(newValue != null && !newValue.equals(oldValue)) {
			int index = event.getRowIndex();
			Inventory edited = null;
			if(filtered == null) {
				edited = list.get(index);
			} else {
				edited = filtered.get(index);
			}
			edited.setModificationUser(getUsername());
			if(edited.getOrderPoint() < edited.getMinQty()) {
				addMessage("El punto de reorden no puede ser menor a la cantidad mínima", true);
				return;
			}
			if(!ObjectService.save(edited)) {
				addMessage("Ocurrió un error al editar el elemento", true);
			}
		}
    }
	
	public void onValueChange(Object e) {
		Inventory edited = (Inventory)e;
		edited.setModificationUser(getUsername());
		if(!ObjectService.save(e)) {
			addMessage("Ocurrió un error al editar el elemento", true);
		}
	}
	
	public String getMaterialName(Integer idMaterial) {
		Material material = MaterialService.getById(idMaterial);
		return material.getMaterial();
	}
	
	public String getLocationName(Integer idLocation) {
		Location location = LocationService.getById(idLocation);
		return location.getLocation();
	}
	
	public String delete() {
		if(!ObjectService.delete(selected)) {
			addMessage("Ocurrió un error al eliminar el elemento", true);
			return null;
		}
		return "index"+redirect;
	}
	
}
