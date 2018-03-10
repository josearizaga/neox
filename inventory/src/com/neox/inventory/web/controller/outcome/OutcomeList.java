package com.neox.inventory.web.controller.outcome;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.InventoryHistory;
import com.neox.inventory.model.material.InventoryView;
import com.neox.inventory.model.material.MaterialMovement;
import com.neox.inventory.model.material.MaterialMovementFullView;
import com.neox.inventory.model.material.MaterialMovementView;
import com.neox.inventory.model.material.Movement;
import com.neox.inventory.model.material.Reorder;
import com.neox.inventory.model.material.Status;
import com.neox.inventory.service.InventoryService;
import com.neox.inventory.service.InventoryViewService;
import com.neox.inventory.service.MaterialMovementService;
import com.neox.inventory.service.MaterialMovementViewService;
import com.neox.inventory.service.MovementService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.OutcomeService;
import com.neox.inventory.service.StatusService;
import com.neox.inventory.web.controller.Main;
import com.neox.inventory.ws.bean.Outcome;

@ViewScoped
@ManagedBean
public class OutcomeList extends Main implements Serializable {
	
	private List<Outcome> list;
	private List<Outcome> filtered;
	private MaterialMovementFullView selected;
	private InventoryView selectedInventory;
	private Double qty;
	
	public OutcomeList() {}
	
	@PostConstruct
	public void init() {
		System.out.println("init");
		if(list == null) {
			list = new ArrayList<Outcome>();
			List<MaterialMovementView> vlist = MaterialMovementViewService.getList();
			
			for(MaterialMovementView element:vlist) {
				Outcome o = new Outcome(element);
				o.load();
				if(o.hasShowElements()) {
					list.add(o);
				}
			}
		}
	}
	
	public List<Outcome> getList() {
		return list;
	}

	public void setList(List<Outcome> list) {
		this.list = list;
	}

	public List<Outcome> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Outcome> filtered) {
		this.filtered = filtered;
	}

	public MaterialMovementFullView getSelected() {
		return selected;
	}

	public void setSelected(MaterialMovementFullView selected) {
		System.out.println("setSelected: " + selected);
		this.selected = selected;
	}
	
	public List<InventoryView> getInventoryList() {
		if(selected == null) {
			return new ArrayList<InventoryView>();
		}
		return InventoryViewService.getListByIdMaterial(selected.getIdMaterial());
	}

	public InventoryView getSelectedInventory() {
		return selectedInventory;
	}

	public void setSelectedInventory(InventoryView selectedInventory) {
		System.out.println(selectedInventory);
		this.selectedInventory = selectedInventory;
	}
	
	public String deliver() {
		if(selectedInventory == null) {
			addMessage("Debes seleccionar la ubicación del material", true);
			return null;
		}
		if(qty <= 0) {
			addMessage("Debes ingresar una cantidad positiva mayor a cero", true);
			return null;
		}
		Double inventoryQty = selectedInventory.getQty();
		Double selectedQty = selected.getQty();
		
		if(qty > inventoryQty) {
			addMessage("La ubicación seleccionada no cuenta con el número suficiente de elementos", true);
			return null;
		}
		if(qty > selectedQty) {
			addMessage("Ocurrió un error con las cantidades seleccionads", true);
			return null;
		}
		
		Movement prestamo = MovementService.byName("Prestamo");
		Movement cambio = MovementService.byName("Cambio");
		Status concluido = StatusService.byName("Concluido");
		Status entregado = StatusService.byName("Entregado");
		Status aprobado = StatusService.byName("Aprobado");
		
//		com.neox.inventory.model.material.Outcome outcome = OutcomeService.byIds(selected.getId(),selectedInventory.getId());
//		if(outcome == null) {
//			outcome = new com.neox.inventory.model.material.Outcome();
//			
//			outcome.setIdMaterialMovement(selected.getId());
//			outcome.setIdInitialStatus(selected.getIdStatus());
//			outcome.setIdInventory(selectedInventory.getId());
//			outcome.setQty(0.0);
//			outcome.setCreationUser(getUsername());
//		} 
		com.neox.inventory.model.material.Outcome outcome = new com.neox.inventory.model.material.Outcome();
		
		outcome.setIdMaterialMovement(selected.getId());
		outcome.setIdInitialStatus(selected.getIdStatus());
		outcome.setIdInventory(selectedInventory.getId());
		outcome.setQty(qty);
		outcome.setCreationUser(getUsername());

		MaterialMovement mm = MaterialMovementService.byId(selected.getId());
		selectedQty -= qty;
		mm.setQty(selectedQty);
		mm.setModificationUser(getUsername());
		if(selectedQty == 0) {
			mm.setIdStatus(concluido.getId());
			if(mm.getIdMovement() == prestamo.getId()) {
				mm.setIdStatus(entregado.getId());
			}
		}
		
		outcome.setIdFinalStatus(mm.getIdStatus());
		outcome.setIdMovement(mm.getIdMovement());
		
		Inventory inventory = InventoryService.byId(selectedInventory.getId());
		inventory.setQty(inventory.getQty() - qty);
		
		Reorder reorder = null;
		if(inventory.getQty() <= inventory.getOrderPoint()) {
			reorder = new Reorder(inventory.getId());
			if(inventory.getQty() <= inventory.getMinQty()) {
				reorder.setCritical(true);
			}
		}
		InventoryHistory history = new InventoryHistory(inventory.getIdMaterial(), getMaterialName(inventory.getIdMaterial()), inventory.getIdLocation(), 
				getLocationName(inventory.getIdLocation()), inventory.getIdProvider(), getProviderName(inventory.getIdProvider()),inventory.getPrice(),qty);
		history.setIdMovement(mm.getIdMovement());
		history.setCreationUser(getUsername());
		history.setIdInventory(inventory.getId());
		
		if(!OutcomeService.save(outcome,mm,inventory,reorder,history)) {
			addMessage("Ocurrió un error al guardar los datos", true);
			return null;
		}
		
		return "index" + redirect;
	}
	
	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}
}
