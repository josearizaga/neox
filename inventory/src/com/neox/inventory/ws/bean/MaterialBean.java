package com.neox.inventory.ws.bean;

public class MaterialBean {
	
	private Integer idMaterial = null;
	private Double qty = null;
	String type = null;
	String uom = null;
	
	public MaterialBean() {}
	
	public MaterialBean(Integer idMaterial, Double qty, String type) {
		this.idMaterial = idMaterial;
		this.qty = qty;
		this.type = type;
	}
	
	public Integer getIdMaterial() {
		return idMaterial;
	}
	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "idMaterial: " + idMaterial + " qty: " + qty + " type: " + type + " uom: " + uom;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
}
