package com.neox.inventory.ws.controller;

import java.util.List;

import com.neox.inventory.model.material.LogMovement;
import com.neox.inventory.model.material.MaterialMovement;
import com.neox.inventory.model.material.Status;
import com.neox.inventory.service.LogMovementService;
import com.neox.inventory.service.MaterialMovementService;
import com.neox.inventory.service.StatusService;
import com.neox.inventory.ws.bean.MaterialMovementBean;
import com.neox.inventory.ws.helper.MaterialMovementHelper;

public class MaterialMovementController {
	
	private String error;
	
	public boolean updateMaterial(MaterialMovementHelper movement) {
		boolean value = true;
		List<MaterialMovementBean> list = movement.getList();
		Status approved = StatusService.byName("Aprobado");
		Status parcial = StatusService.byName("Parcial");
		Status rechazado = StatusService.byName("Rechazado");
		for(MaterialMovementBean m:list) {
			MaterialMovement mov = MaterialMovementService.byId(m.getId());
			mov.setDqty(mov.getDqty() + m.getQty());
			if(m.getQty() >= 0) {
				if(mov.getDqty() >= mov.getQty()) {
					mov.setIdStatus(approved.getId());
				} else {
					mov.setIdStatus(parcial.getId());
				}
			} else {
				mov.setIdStatus(rechazado.getId());
				mov.setDqty(new Double(0));
			}
			if(!MaterialMovementService.updateMaterialMovement(mov)) {
				return false;
			}
			LogMovement log = new LogMovement(mov);
			log.setUser(movement.getUsername());
			LogMovementService.insertLoglMovement(log);
		}
		
		return value;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
