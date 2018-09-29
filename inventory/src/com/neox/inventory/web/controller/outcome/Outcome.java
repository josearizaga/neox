package com.neox.inventory.web.controller.outcome;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

import com.neox.inventory.model.area.Location;
import com.neox.inventory.model.material.Header;
import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.InventoryHistory;
import com.neox.inventory.model.material.InventoryView;
import com.neox.inventory.model.material.Material;
import com.neox.inventory.model.material.MaterialMovement;
import com.neox.inventory.model.material.Movement;
import com.neox.inventory.model.material.Reorder;
import com.neox.inventory.model.material.Status;
import com.neox.inventory.service.InventoryService;
import com.neox.inventory.service.InventoryViewService;
import com.neox.inventory.service.LocationService;
import com.neox.inventory.service.MaterialService;
import com.neox.inventory.service.MovementService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.OutcomeService;
import com.neox.inventory.service.StatusService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class Outcome extends Main implements Serializable {
	
	private Integer idMaterial;
	private Integer idInventory;
	private Integer idUser;
	private Double qty;
	private Inventory inventory;
	private boolean value;
	private String workOrder;
	
	public String getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}

	public Outcome() {}
	
	public String onFlowProcess(FlowEvent event) {
		System.out.println(event);
        return event.getNewStep();
    }

	public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}
	
	public List<InventoryView> getInventoryList() {
		return InventoryViewService.getListByIdMaterial(idMaterial);
	}

	public Integer getIdInventory() {
		return idInventory;
	}

	public void setIdInventory(Integer idInventory) {
		this.idInventory = idInventory;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Inventory getInventory() {
		inventory = InventoryService.byId(idInventory);
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public Material getMaterial() {
		return MaterialService.getById(idMaterial);
	}
	
	public Location getLocation() {
		System.out.println(inventory);
		return LocationService.getById(inventory.getIdLocation());
	}
	
	public String save() {
		Header h = new Header();
		
		h.setIdUser(idUser);
		
		if(!ObjectService.save(h)) {
			addMessage("Ocurrió un error al guardar los datos", true);
			return null;
		}
		
		Movement prestamo = MovementService.byName("Prestamo");
		Movement cambio = MovementService.byName("Cambio");
		
		MaterialMovement mm = new MaterialMovement();
		Status entregado = StatusService.byName("Entregado");
		
		mm.setCreationUser(getUsername());
		mm.setDqty(qty);
		mm.setIdHeader(h.getId());
		mm.setIdMaterial(idMaterial);
		if(value) {
			mm.setIdMovement(prestamo.getId());
		} else {
			mm.setIdMovement(cambio.getId());
		}
		mm.setIdStatus(entregado.getId());
		Material m = getMaterial();
		mm.setIdUOM(m.getIdUOM());
		mm.setMaterial(m);
		mm.setPosition(1);
		mm.setQty(qty);
		mm.setWorkOrder("WEB:"+workOrder);
		
		if(!ObjectService.save(mm)) {
			addMessage("Ocurrió un error al guardar los datos", true);
			return null;
		}
		
		com.neox.inventory.model.material.Outcome outcome = new com.neox.inventory.model.material.Outcome();
		
		outcome.setIdMovement(mm.getIdMovement());;
		outcome.setIdMaterialMovement(mm.getId());
		outcome.setIdInitialStatus(entregado.getId());
		outcome.setIdFinalStatus(entregado.getId());
		outcome.setIdInventory(inventory.getId());
		outcome.setQty(qty);
		outcome.setCreationUser(getUsername());
		
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
		history.setIdMovement(mm.getId());
		history.setCreationUser(getUsername());
		history.setIdInventory(inventory.getId());
		
		if(!OutcomeService.save(outcome,inventory,reorder,history)) {
			addMessage("Ocurrió un error al guardar los datos", true);
			return null;
		}
		
		return "index" + redirect;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
}
