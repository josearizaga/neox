package com.neox.inventory.web.controller.report;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.report.InventoryReportView;
import com.neox.inventory.service.ReportService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class InventoryReport extends Main implements Serializable {
	
	private List<InventoryReportView> list;
	private List<InventoryReportView> filtered;
	

	
	
	
	public InventoryReport() {}

	public List<InventoryReportView> getList() {
		if(list == null) {
			list = ReportService.getInventoryReport();
			System.out.println(list);
		}
		return list;
	}

	public void setList(List<InventoryReportView> list) {
		this.list = list;
	}

	public List<InventoryReportView> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<InventoryReportView> filtered) {
		this.filtered = filtered;
	}
	
	
}
