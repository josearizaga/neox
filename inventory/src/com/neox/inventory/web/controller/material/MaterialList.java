package com.neox.inventory.web.controller.material;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.neox.inventory.model.material.Category;
import com.neox.inventory.model.material.Material;
import com.neox.inventory.model.material.MaterialView;
import com.neox.inventory.model.material.UOM;
import com.neox.inventory.service.CategoryService;
import com.neox.inventory.service.MaterialService;
import com.neox.inventory.service.MaterialViewService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.UOMService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class MaterialList extends Main implements Serializable {
	
	private List<Material> list;
	private List<Material> filtered;
	
	public MaterialList() {}

	public List<Material> getList() {
		long start_time = System.currentTimeMillis();
		if(list == null) {
			list = MaterialService.getAll();
		}
		long end_time = System.currentTimeMillis();
		long difference = end_time-start_time;
		System.out.println("And the difference is: " + difference);
		return list;
	}

	public void setList(List<Material> list) {
		this.list = list;
	}

	public List<Material> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Material> filtered) {
		this.filtered = filtered;
	}
	
	public String edit() {
		return "edit"+redirect;
	}
	
	public void onCellEdit(RowEditEvent event) {
			Material edited = (Material) event.getObject();
			
			edited.setModificationUser(getUsername());
			if(!ObjectService.save(edited)) {
				addMessage("Ocurrió un error al editar el material", true);
			}
    }
	
	
}
