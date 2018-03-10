package com.neox.inventory.web.controller.inventory;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.FlowEvent;


import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.InventoryHistory;
import com.neox.inventory.model.material.MaterialProviderPrice;
import com.neox.inventory.model.material.Movement;
import com.neox.inventory.model.provider.Provider;
import com.neox.inventory.service.InventoryService;
import com.neox.inventory.service.MaterialProviderPriceService;
import com.neox.inventory.service.MovementService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class InventoryViewConfig extends Main implements Serializable{
	
	private Inventory inventory;
	private Double price;
	private MaterialProviderPrice materialProvider;
	
	public InventoryViewConfig() {
		inventory = new Inventory();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public String onFlowProcess(FlowEvent event) {
//		if(event.getNewStep().compareTo("location") == 0 && inventory.getIdProvider() > 0) {
//			inventory.setIdProvider(0);
//		}
		System.out.println(inventory);
		return event.getNewStep();
    }
	
	public void onValueChangeListener(AjaxBehaviorEvent e) {
		materialProvider = MaterialProviderPriceService.byIds(inventory.getIdMaterial(), inventory.getIdProvider());
		inventory.setPrice(new Double(0));
		if(materialProvider != null) {
			price = materialProvider.getPrice();
			inventory.setPrice(price);
		}
	}
	
	public String save() {
		InventoryHistory history = new InventoryHistory(inventory.getIdMaterial(), getMaterialName(), inventory.getIdLocation(), 
				getLocationName(), inventory.getIdProvider(), getProviderName(),inventory.getPrice(),inventory.getQty());
		Movement mov = MovementService.byName("Entrada");
		history.setIdMovement(mov.getId());
		history.setCreationUser(getUsername());
		inventory.setCreationUser(getUsername());
		inventory.setModificationUser(getUsername());
		MaterialProviderPrice edited = null;
		if(materialProvider == null) {
			materialProvider = new MaterialProviderPrice();
			materialProvider.setCreationUser(getUsername());
			materialProvider.setModificationUser(getUsername());
			materialProvider.setIdMaterial(inventory.getIdMaterial());
			materialProvider.setIdProvider(inventory.getIdProvider());
			materialProvider.setPrice(new Double(-1));
		}
		if(inventory.getPrice() != materialProvider.getPrice()) {
			materialProvider.setModificationUser(getUsername());
			edited = materialProvider;
			edited.setPrice(inventory.getPrice());
		}
		
		Inventory search = InventoryService.byIds(inventory.getIdMaterial(), inventory.getIdLocation());
		if(search != null) {
			inventory.setQty(inventory.getQty() + search.getQty());
			search.setQty(inventory.getQty());
			search.setIdProvider(inventory.getIdProvider());
			search.setPrice(inventory.getPrice());
			inventory = search;
		}
		
		if(!InventoryService.save(inventory, edited, history)) {
			addMessage("Ocurrió un error al agregar el elemento", true);
			return null;
		}
		return "index" + redirect;
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
	
}
