package com.neox.inventory.web.controller.inventory;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.InventoryView;
import com.neox.inventory.service.InventoryService;
import com.neox.inventory.service.InventoryViewService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class InventoryViewList extends Main implements Serializable {
	
	private List<InventoryView> list;
	private List<InventoryView> filtered;
	private InventoryView selected;
	
	public InventoryViewList() {}

	public List<InventoryView> getList() {
		if(list == null) {
			list = InventoryViewService.getList();
		}
		return list;
	}

	public void setList(List<InventoryView> list) {
		this.list = list;
	}

	public List<InventoryView> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<InventoryView> filtered) {
		this.filtered = filtered;
	}

	public InventoryView getSelected() {
		return selected;
	}

	public void setSelected(InventoryView selected) {
		this.selected = selected;
		setAttribute("selectedInventory", selected);
	}
	
	public String delete() {
		Inventory edited = InventoryService.byId(selected.getId());
		edited.setQty(selected.getQty());
		edited.setMinQty(selected.getMinQty());
		edited.setOrderPoint(selected.getOrderPoint());
		edited.setModificationUser(getUsername());
		if(!ObjectService.delete(edited)) {
			addMessage("Ocurrió un error al eliminar el elemento", true);
			return null;
		}
		return "index"+redirect;
	}
	
	public String edit() {
		return "edit"+redirect;
	}
	
	public void onCellEdit(RowEditEvent event) {
//		Object oldValue = event.getOldValue();
//		Object newValue = event.getNewValue();
//		
//		
//		if(newValue != null && !newValue.equals(oldValue)) {
		
		InventoryView iv = (InventoryView) event.getObject();
		Inventory edited = InventoryService.byId(iv.getId());
		edited.setQty(iv.getQty());
		edited.setMinQty(iv.getMinQty());
		edited.setOrderPoint(iv.getOrderPoint());
		/*
		if(edited.getOrderPoint() < edited.getMinQty()) {
			addMessage("El punto de reorden no puede ser menor a la cantidad mínima", true);
			return;
		}
		*/
		if(!ObjectService.save(edited)) {
			addMessage("Ocurrió un error al editar el elemento", true);
		}
//		}
    }
	
}
