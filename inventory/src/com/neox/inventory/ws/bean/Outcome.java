package com.neox.inventory.ws.bean;

import java.io.Serializable;
import java.util.List;

import com.neox.inventory.model.material.MaterialMovementFullView;
import com.neox.inventory.model.material.MaterialMovementView;
import com.neox.inventory.service.MaterialMovementFullViewService;
import com.neox.inventory.service.StatusService;

public class Outcome implements Serializable {
	
	private MaterialMovementView materialMovementView;
	private List<MaterialMovementFullView> movementFullViewList;
	
	public Outcome() {}
	
	public Outcome(MaterialMovementView materialMovementView) {
		this.materialMovementView = materialMovementView;
	}
	
	public MaterialMovementView getMaterialMovementView() {
		return materialMovementView;
	}

	public void setMaterialMovementView(MaterialMovementView materialMovementView) {
		this.materialMovementView = materialMovementView;
	}

	public List<MaterialMovementFullView> getMovementFullViewList() {
		return movementFullViewList;
	}

	public void setMovementFullViewList(List<MaterialMovementFullView> movementFullViewList) {
		this.movementFullViewList = movementFullViewList;
	}
	
	public void load() {
		setMovementFullViewList(MaterialMovementFullViewService.getMaterialListByIdHeader(materialMovementView.getIdHeader()));
	}
	
	public void load(Integer ...idStatus) {
		setMovementFullViewList(MaterialMovementFullViewService.getMaterialListByIdHeader(materialMovementView.getIdHeader(),idStatus));
	}
	
	public boolean hasShowElements() {
		for(MaterialMovementFullView view:movementFullViewList) {
			if(view.getQty() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isApproval() {
		Status status = null;
		for(MaterialMovementFullView view:movementFullViewList) {
			if(StatusService.byId(view.getIdStatus()).isApproval()) {
				return true;
			}
		}
		return false;
	}
	
}
