package com.neox.inventory.web.controller.uom;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.neox.inventory.model.area.Area;
import com.neox.inventory.model.material.UOM;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.UOMService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class UOMList extends Main implements Serializable {
	
	private UOM selected;
	private List<UOM> list;
	private List<UOM> filtered;
	
	public UOMList() {}

	public UOM getSelected() {
		return selected;
	}

	public void setSelected(UOM selected) {
		this.selected = selected;
		setAttribute("selectedUOM", selected);
	}

	public List<UOM> getList() {
		if(list == null) {
			list = UOMService.getAll();
		}
		return list;
	}

	public void setList(List<UOM> list) {
		this.list = list;
	}

	public List<UOM> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<UOM> filtered) {
		this.filtered = filtered;
	}
	
	public void onValueChange(Object e) {
		UOM edited = (UOM)e;
		edited.setModificationUser(getUsername());
		if(!ObjectService.save(e)) {
			addMessage("Ocurrió un error al editar le medida", true);
		}
	}
	public String delete() {
		if(!ObjectService.delete(selected)) {
			addMessage("Ocurrió un error al eliminar el área", true);
			return null;
		}
		return "index"+redirect;
	}
	
	public void onCellEdit(RowEditEvent event) {
		
		UOM edited = (UOM)event.getObject();
		edited.setModificationUser(getUsername());
		if(!ObjectService.save(edited)) {
			addMessage("Ocurrió un error al editar la medida", true);
		}
    }
	
}
