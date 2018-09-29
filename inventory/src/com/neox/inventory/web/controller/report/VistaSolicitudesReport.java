package com.neox.inventory.web.controller.report;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.report.VistaSolicitudes;
import com.neox.inventory.service.VistaSolicitudesService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class VistaSolicitudesReport extends Main implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VistaSolicitudes> list;
	private List<VistaSolicitudes> filtered;
	
	public VistaSolicitudesReport() {}

	public List<VistaSolicitudes> getList() {
		if(list == null) {
			list = VistaSolicitudesService.getVistaSolicitudesReport();
			System.out.println(list);
		}
		return list;
	}

	public void setList(List<VistaSolicitudes> list) {
		this.list = list;
	}

	public List<VistaSolicitudes> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<VistaSolicitudes> filtered) {
		this.filtered = filtered;
	}
	
	
}
