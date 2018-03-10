package com.neox.inventory.web.controller.lending;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.material.Income;
import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.InventoryHistory;
import com.neox.inventory.model.material.InventoryView;
import com.neox.inventory.model.material.Movement;
import com.neox.inventory.model.material.Outcome;
import com.neox.inventory.model.material.OutcomeView;
import com.neox.inventory.model.material.Status;
import com.neox.inventory.service.InventoryService;
import com.neox.inventory.service.InventoryViewService;
import com.neox.inventory.service.MovementService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.OutcomeService;
import com.neox.inventory.service.OutcomeViewService;
import com.neox.inventory.service.StatusService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class LendingList extends Main implements Serializable {
	
	List<OutcomeView> list;
	List<OutcomeView> filtered;
	OutcomeView selected;
	private Double qty;
	
	public LendingList() {}

	public List<OutcomeView> getList() {
		if(list == null) {
			Movement movement = MovementService.byName("Prestamo");
			list = OutcomeViewService.byIdMovement(movement.getId());
		}
		return list;
	}

	public void setList(List<OutcomeView> list) {
		this.list = list;
	}

	public List<OutcomeView> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<OutcomeView> filtered) {
		this.filtered = filtered;
	}

	public OutcomeView getSelected() {
		return selected;
	}

	public void setSelected(OutcomeView selected) {
		System.out.println("Selected: "+selected);
		this.selected = selected;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}
	
	public String saveIncome() {
		if(qty <= 0) {
			addMessage("Debes escribir una cantidad mayor a cero",true);
			return null;
		}
		Outcome outcome = OutcomeService.byId(selected.getId());
		Inventory inventory = InventoryService.byId(selected.getIdInventory());
		Income income = new Income();
		outcome.add((qty*-1));
		inventory.add(qty);
		Movement in = MovementService.byName("Devolución");
		Status dev = StatusService.byName("Devuelto");
		
		income.setIdInventory(selected.getIdInventory());
		income.setIdMaterialMovement(outcome.getIdMaterialMovement());
		income.setIdMovement(in.getId());
		income.setIdStatus(dev.getId());
		income.setQty(qty);
		
		InventoryHistory history = new InventoryHistory(inventory.getIdMaterial(), getMaterialName(inventory.getIdMaterial()), inventory.getIdLocation(), 
				getLocationName(inventory.getIdLocation()), inventory.getIdProvider(), getProviderName(inventory.getIdProvider()),inventory.getPrice(),qty);
		history.setIdMovement(in.getId());
		history.setCreationUser(getUsername());
		history.setIdInventory(inventory.getId());
		
		if(!ObjectService.save(outcome,income,inventory,history)) {
			addMessage("Ocurrió un error al actualizar la información", true);
			return null;
		}
		return "index" + redirect;
	}
	
	public String saveOutcome() {
		if(qty <= 0) {
			addMessage("Debes escribir una cantidad mayor a cero",true);
			return null;
		}
		Outcome outcome = OutcomeService.byId(selected.getId());
		outcome.add((qty*-1));
		
		Movement in = MovementService.byName("Perdido");
		outcome.setIdMovement(in.getId());
		
		Inventory inventory = InventoryService.byId(selected.getIdInventory());
		InventoryHistory history = new InventoryHistory(inventory.getIdMaterial(), getMaterialName(inventory.getIdMaterial()), inventory.getIdLocation(), 
				getLocationName(inventory.getIdLocation()), inventory.getIdProvider(), getProviderName(inventory.getIdProvider()),inventory.getPrice(),qty);
		history.setIdMovement(in.getId());
		history.setCreationUser(getUsername());
		history.setIdInventory(inventory.getId());
		
		if(!ObjectService.save(outcome,history)) {
			addMessage("Ocurrió un error al actualizar la información", true);
			return null;
		}
		return "index" + redirect;
	}
}
