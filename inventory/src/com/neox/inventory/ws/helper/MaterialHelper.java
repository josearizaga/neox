package com.neox.inventory.ws.helper;

import java.util.List;

import com.neox.inventory.ws.bean.MaterialBean;

public class MaterialHelper {
	
	private Integer idUser;
	private Integer idArea;
	private String workOrder;
	private List<MaterialBean> materials;
	
	public MaterialHelper() {}
	
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}
	public List<MaterialBean> getMaterials() {
		return materials;
	}
	public void setMaterials(List<MaterialBean> materials) {
		this.materials = materials;
	}
	
	@Override
	public String toString() {
		return "idUser: " + idUser + " workOrder: " + workOrder  + " materials: " + materials;
	}

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}
}
