package com.neox.inventory.web.controller.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PostLoad;

import com.neox.inventory.model.material.MaterialMovementFullView;
import com.neox.inventory.model.material.MaterialMovementView;
import com.neox.inventory.model.material.Status;
import com.neox.inventory.service.MaterialMovementFullViewService;
import com.neox.inventory.service.StatusService;
import com.neox.inventory.web.controller.Main;

@ManagedBean
@ViewScoped
public class MaterialViewInfo extends Main implements Serializable {
	
	@ManagedProperty(value = "#{selectedMaterial}")
	MaterialMovementView selectedMaterial;
	
	public MaterialViewInfo() {
	}
	
	@PostConstruct
	public void init() {
		Status pa = StatusService.byApproval();
		Integer idHeader = new Integer(selectedMaterial.getIdHeader());
		List<MaterialMovementFullView> list = MaterialMovementFullViewService.getMaterialListByIdHeader(idHeader,pa.getId());
		System.out.println(list);
	}
	
	public String getId() {
		return this.hashCode()+"-"+selectedMaterial;
	}

	public MaterialMovementView getSelectedMaterial() {
		return selectedMaterial;
	}

	public void setSelectedMaterial(MaterialMovementView selectedMaterial) {
		this.selectedMaterial = selectedMaterial;
	}
	
}
