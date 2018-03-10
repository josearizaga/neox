package com.neox.inventory.web.controller.material;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.neox.inventory.model.material.MaterialProviderPrice;
import com.neox.inventory.model.material.MaterialProviderPriceView;
import com.neox.inventory.model.material.UOM;
import com.neox.inventory.service.MaterialPriceService;
import com.neox.inventory.service.MaterialProviderPriceService;
import com.neox.inventory.service.MaterialProviderPriceViewService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class MaterialProviderList extends Main implements Serializable {
	
	private List<MaterialProviderPriceView> list;
	private List<MaterialProviderPriceView> filtered;
	
	public MaterialProviderList() {}

	public List<MaterialProviderPriceView> getList() {
		if(list == null) {
			list = MaterialProviderPriceViewService.getList();
		}
		return list;
	}

	public void setList(List<MaterialProviderPriceView> list) {
		this.list = list;
	}

	public List<MaterialProviderPriceView> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<MaterialProviderPriceView> filtered) {
		this.filtered = filtered;
	}
	
	public void onRowEdit(RowEditEvent event) {
		
		MaterialProviderPriceView material = (MaterialProviderPriceView)event.getObject();
		
		MaterialProviderPrice edited = MaterialProviderPriceService.byId(material.getId());
		edited.setPrice(material.getPrice());
		
		edited.setModificationUser(getUsername());
		if(!ObjectService.save(edited)) {
			addMessage("Ocurrió un error al editar el precio", true);
		}
    }
	
}
