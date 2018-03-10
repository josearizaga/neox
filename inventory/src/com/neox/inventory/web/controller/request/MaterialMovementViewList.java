package com.neox.inventory.web.controller.request;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.neox.inventory.model.material.MaterialMovement;
import com.neox.inventory.model.material.MaterialMovementFullView;
import com.neox.inventory.model.material.MaterialMovementView;
import com.neox.inventory.model.material.Status;
import com.neox.inventory.service.MaterialMovementService;
import com.neox.inventory.service.MaterialMovementViewService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.StatusService;
import com.neox.inventory.web.controller.Main;
import com.neox.inventory.ws.bean.Outcome;

@ViewScoped
@ManagedBean
public class MaterialMovementViewList extends Main implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MaterialMovementView selectedMaterial;
	MaterialMovementFullView selectedMovement;
	List<Outcome> materialList;
	List<Outcome> filteredList;
	
	public MaterialMovementViewList() {}
	
	@PostConstruct
	public void init() {
		if(materialList == null) {
			materialList = new ArrayList<Outcome>();
			List<MaterialMovementView> vlist = MaterialMovementViewService.getList();
			Integer idStatus = StatusService.byApproval().getId();
			for(MaterialMovementView element:vlist) {
				Outcome o = new Outcome(element);
				o.load(idStatus);
				if(o.isApproval()) {
					materialList.add(o);
				}
			}
		}
	}
	
	public List<Outcome> getMaterialList() {
		return materialList;
	}

	public MaterialMovementView getSelectedMaterial() {
		return selectedMaterial;
	}

	public void setSelectedMaterial(MaterialMovementView selectedMaterial) {
		this.selectedMaterial = selectedMaterial;
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		System.out.println("onRowSelect");
		setAttribute("selectedMaterial", selectedMaterial);
		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		configurableNavigationHandler.performNavigation("approve"+redirect);
    }

	public List<Outcome> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(List<Outcome> filteredList) {
		this.filteredList = filteredList;
	}
	
	public String approve() {
		Status status = StatusService.byName("Aprobado");
		System.out.println(selectedMovement);
		return updateStatus(status.getId());
	}
	
	public String deny() {
		Status status = StatusService.byName("Rechazado");
		System.out.println(selectedMovement);
		return updateStatus(status.getId());
	}
	
	public String updateStatus(Integer idStatus) {
		MaterialMovement movement = MaterialMovementService.byId(selectedMovement.getId());
		movement.setIdStatus(idStatus);
		movement.setModificationUser(getUsername());
		if(!ObjectService.save(movement)) {
			addMessage("Ocurrió un error al actualizar el elemento", true);
			return null;
		}
		return "index"+redirect;
	}

	public MaterialMovementFullView getSelectedMovement() {
		return selectedMovement;
	}

	public void setSelectedMovement(MaterialMovementFullView selectedMovement) {
		this.selectedMovement = selectedMovement;
	}
	
}