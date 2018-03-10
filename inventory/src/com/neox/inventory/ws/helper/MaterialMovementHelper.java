package com.neox.inventory.ws.helper;

import java.io.Serializable;
import java.util.List;

import com.neox.inventory.ws.bean.MaterialMovementBean;

public class MaterialMovementHelper implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4587988734287651549L;
	private List<MaterialMovementBean> list;
	private String username;
	
	public MaterialMovementHelper() {}
	
	public MaterialMovementHelper(List<MaterialMovementBean> list) {
		this.list = list;
	}

	public List<MaterialMovementBean> getList() {
		return list;
	}

	public void setList(List<MaterialMovementBean> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		StringBuffer string = new StringBuffer("[");
		for(MaterialMovementBean mvb:list) {
			string.append("{" + mvb.toString() + "}");
		}
		return string.toString();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
