package com.neox.inventory.ws.bean;

import java.io.Serializable;

public class MaterialMovementBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idHeader;
	private Double qty;
	
	public MaterialMovementBean() {}
	
	public MaterialMovementBean(Integer id, Integer idHeader, Double qty) {
		this.id = id;
		this.idHeader = idHeader;
		this.qty = qty;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdHeader() {
		return idHeader;
	}
	public void setIdHeader(Integer idHeader) {
		this.idHeader = idHeader;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	
	public String toString() {
		return "id:"+id+",idHeader:"+idHeader+",qty:"+qty;
	}
	
}
