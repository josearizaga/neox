package com.neox.inventory.web.controller.area;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.neox.inventory.model.area.Area;
import com.neox.inventory.service.AreaService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class AreaList extends Main implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3120128476101263249L;
	private Area selectedArea;
	private List<Area> areaList;
	private List<Area> filteredList;
	
	public AreaList() {}

	public Area getSelectedArea() {
		return selectedArea;
	}

	public void setSelectedArea(Area selectedArea) {
		this.selectedArea = selectedArea;
		setAttribute("selectedArea", selectedArea);
	}

	public List<Area> getAreaList() {
		if(areaList == null) {
			areaList = AreaService.getAll();
		}
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public List<Area> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(List<Area> filteredList) {
		this.filteredList = filteredList;
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		System.out.println("onRowSelect");
		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		
		setAttribute("selectedArea", selectedArea);
		
		configurableNavigationHandler.performNavigation("edit?faces-redirect=true");
    }
	
	public String edit() {
		return "edit"+redirect;
	}
	
	public String delete() {
		if(!ObjectService.delete(selectedArea)) {
			addMessage("Ocurrió un error al eliminar el área", true);
			return null;
		}
		return "index"+redirect;
	}
	
	public void onCellEdit(RowEditEvent event) {
//		Object oldValue = event.getOldValue();
//		Object newValue = event.getNewValue();
//		
//		
//		if(newValue != null && !newValue.equals(oldValue)) {
//			int index = event.getRowIndex();
			Area edited = null;
			edited = (Area)event.getObject();
			edited.setModificationUser(getUsername());
			if(!ObjectService.save(edited)) {
				addMessage("Ocurrió un error al editar el área", true);
			}
//		}
    }
	
	public void onValueChange(Object e) {
		Area edited = (Area)e;
		edited.setModificationUser(getUsername());
		if(!ObjectService.save(edited)) {
			addMessage("Ocurrió un error al editar el área", true);
		}
	}
	
}
