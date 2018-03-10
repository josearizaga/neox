package com.neox.inventory.web.controller.transfer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;

import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.InventoryHistory;
import com.neox.inventory.model.material.InventoryView;
import com.neox.inventory.model.material.Movement;
import com.neox.inventory.model.material.Transfer;
import com.neox.inventory.service.InventoryService;
import com.neox.inventory.service.InventoryViewService;
import com.neox.inventory.service.LocationService;
import com.neox.inventory.service.MovementService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.TransferService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class TransferConfig extends Main implements Serializable {
	
	private Integer idMaterial;
	private Integer source;
	private Integer destination;
	private Double qty;
	private Double sourceInit;
	private Double destinationInit; 
	private List<InventoryView> selectedInventory;
	private InventoryView sourceView;
	private InventoryView destinationView;
	
	public TransferConfig() {}

	public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}
	
	public List<InventoryView> getSelectedInventory() {
		selectedInventory = InventoryViewService.getListByIdMaterial(idMaterial);
		return selectedInventory;
	}

	public void setSelectedInventory(List<InventoryView> selectedInventory) {
		this.selectedInventory = selectedInventory;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getDestination() {
		return destination;
	}

	public void setDestination(Integer destination) {
		this.destination = destination;
	}
	
	public InventoryView getSourceView() {
		return sourceView;
	}
	
	public InventoryView getDestinationView() {
		return destinationView;
	}

	public void setSourceView(InventoryView sourceView) {
		this.sourceView = sourceView;
	}

	public void setDestinationView(InventoryView destinationView) {
		this.destinationView = destinationView;
	}
	
	public void sourceListener() {
		sourceView = null;
		sourceInit = 0.0;
		if((destination != null && source != null) && (destination.intValue() == source.intValue())) {
			source = 0;
			addMessage("No puedes seleccionar la misma ubicación como fuente y destino", true);
			return;
		}
		for(InventoryView view:selectedInventory) {
			if(view.getIdLocation().intValue() == source.intValue()) {
				sourceView = view;
				sourceInit = sourceView.getQty();
			}
		}
	}
	
	public void destinationListener() {
		destinationView = null;
		destinationInit = 0.0;
		if((destination != null && source != null) && (destination.intValue() == source.intValue())) {
			destination = 0;
			addMessage("No puedes seleccionar la misma ubicación como fuente y destino", true);
			return;
		}
		for(InventoryView view:selectedInventory) {
			if(view.getIdLocation().intValue() == destination.intValue()) {
				destinationView = view;
				destinationInit = destinationView.getQty();
			}
		}
	}

	public void changeListener() {
		Double value = sourceInit - qty;
		sourceView.setQty(value);
		value = destinationInit + qty;
		destinationView.setQty(value);
	}
	
	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}
	
	public String save() {
		Inventory sinv = InventoryService.byId(sourceView.getId());
		Inventory dinv;
		if(destinationView.getId() != null) {
			dinv = InventoryService.byId(destinationView.getId());
		} else {
			dinv = new Inventory(sinv);
			dinv.setIdLocation(destinationView.getIdLocation());
			dinv.setCreationUser(getUsername());
		}
		sinv.setQty(sourceView.getQty());
		dinv.setQty(destinationView.getQty());
		
		Transfer transfer = new Transfer();
		
		transfer.setIdInventorySource(sinv.getId());
		transfer.setIdInventoryDestination(dinv.getId());
		transfer.setQty(qty);
		transfer.setCreationUser(getUsername());
		transfer.setIdMaterial(idMaterial);
		
		InventoryHistory history = new InventoryHistory(dinv.getIdMaterial(), getMaterialName(dinv.getIdMaterial()), dinv.getIdLocation(), 
				getLocationName(dinv.getIdLocation()), dinv.getIdProvider(), getProviderName(dinv.getIdProvider()),dinv.getPrice(),dinv.getQty());
		Movement mov = MovementService.byName("Traspaso");
		history.setIdMovement(mov.getId());
		history.setCreationUser(getUsername());
		
		if(!TransferService.save(sinv,dinv,transfer,history)) {
			addMessage("Ocurrió un error al guardar los datos", true);
			return null;
		}
		return "index" + redirect;
	}
	
	public void addAsNew() {
		destinationView = new InventoryView();
		destinationView.setIdLocation(destination);
		destinationView.setLocation(LocationService.getById(destination).getLocation());
		destinationView.setQty(0.0);
	}
}
