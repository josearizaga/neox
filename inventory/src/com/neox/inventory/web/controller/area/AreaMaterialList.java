package com.neox.inventory.web.controller.area;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;


import com.neox.inventory.model.area.AreaMaterialView;
import com.neox.inventory.service.AreaMaterialViewService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class AreaMaterialList extends Main implements Serializable {
	
	private List<AreaMaterialView> list;
	private List<AreaMaterialView> filtered;
	public List<AreaMaterialView> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<AreaMaterialView> filtered) {
		this.filtered = filtered;
	}

	private Integer idArea = 0;
	
	public AreaMaterialList() {}

	public List<AreaMaterialView> getList() {
		if(idArea > 0) {
			list = AreaMaterialViewService.getListByArea(idArea);
		}
		return list;
	}

	public void setList(List<AreaMaterialView> list) {
		this.list = list;
	}

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}
	
	public String add() {
		if(idArea <= 0) {
			addMessage("Debes seleccionar un área para trabajar", true);
			return null;
		}
		setAttribute("idArea", idArea);
		return "editAreaMaterial"+redirect;
	}
	
}
