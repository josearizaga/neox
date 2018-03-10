package com.neox.inventory.web.controller.income;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

import com.neox.inventory.model.area.Location;
import com.neox.inventory.model.material.Income;
import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.InventoryHistory;
import com.neox.inventory.model.material.Movement;
import com.neox.inventory.model.material.Status;
import com.neox.inventory.model.provider.Provider;
import com.neox.inventory.service.InventoryService;
import com.neox.inventory.service.LocationService;
import com.neox.inventory.service.MovementService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.ProviderService;
import com.neox.inventory.service.StatusService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class IncomeConfig extends Main implements Serializable {
	
	private Inventory inventory;
	private Integer idMaterial;
	private Integer idLocation;
	private Integer idProvider;
	private Double qty;
	
	public IncomeConfig() {}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}

	public Integer getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Integer idLocation) {
		this.idLocation = idLocation;
	}
	
	public List<Location> getSelectedLocation() {
		List<Location> list = LocationService.byIdMaterial(idMaterial);
		System.out.println("lista: " + list);
		return list;
	}
	
	public List<Provider> getSelectedProvider() {
		return ProviderService.byIds(idMaterial, idLocation);
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}
	
	public String save() {
		inventory = InventoryService.byIds(idMaterial, idLocation);
		if(qty <= 0) {
			addMessage("Debes escribir un número positivo mayor a cero", true);
			return null;
		}
		if(inventory != null) {
			inventory.add(qty);
			inventory.setModificationUser(getUsername());
		} else {
			addMessage("Ocurrió un error con el inventario",true);
			return null;
		}
		/*Add the income object*/
		
		Movement movement = MovementService.byName("Entrada");
		
		Income income = new Income();
		income.setQty(qty);
		income.setIdInventory(inventory.getId());
		income.setIdMovement(movement.getId());
		income.setCreationUser(getUsername());
		income.setIdMaterialMovement(0);
		Status status = StatusService.byName("Entregado");
		income.setIdStatus(status.getId());
		
		InventoryHistory history = new InventoryHistory(inventory.getIdMaterial(), getMaterialName(), inventory.getIdLocation(), 
				getLocationName(), inventory.getIdProvider(), getProviderName(),inventory.getPrice(),qty);
		history.setIdMovement(movement.getId());
		history.setCreationUser(getUsername());
		history.setIdInventory(inventory.getId());
		if(!ObjectService.save(inventory,income,history)) {
			addMessage("Ocurrió un error al actualizar la información", true);
			return null;
		}
		return "index"+redirect;
	}
	
	public String getMaterialName() {
		System.out.println(inventory);
		return getMaterialName(inventory.getIdMaterial());
	}
	
	public String getLocationName() {
		return getLocationName(inventory.getIdLocation());
	}
	
	public String getProviderName() {
		return getProviderName(inventory.getIdProvider());
	}

	public Integer getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(Integer idProvider) {
		this.idProvider = idProvider;
	}
}
