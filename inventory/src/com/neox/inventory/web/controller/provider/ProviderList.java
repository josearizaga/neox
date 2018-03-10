package com.neox.inventory.web.controller.provider;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.neox.inventory.model.provider.Provider;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.ProviderService;
import com.neox.inventory.web.controller.Configuration;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class ProviderList extends Main implements Serializable {
	
	private List<Provider> list;
	private List<Provider> filtered;
	private Provider selected;
	private List<String> chips;
	
	public ProviderList() {}

	public List<Provider> getList() {
		if(list == null) {
			list = ProviderService.getAll();
			for(Provider p:list) {
				p.fromStringToList();
			}
		}
		return list;
	}

	public void setList(List<Provider> list) {
		this.list = list;
	}

	public List<Provider> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Provider> filtered) {
		this.filtered = filtered;
	}

	public Provider getSelected() {
		return selected;
	}

	public void setSelected(Provider selected) {
		this.selected = selected;
		setAttribute("selectedProvider", selected);
	}
	
	public void onValueChange(Object e) {
		Provider edited = (Provider)e;
		edited.setModificationUser(getUsername());
		if(!ObjectService.save(e)) {
			addMessage("Ocurrió un error al editar al proveedor", true);
		}
	}
	public String delete() {
		if(!ObjectService.delete(selected)) {
			addMessage("Ocurrió un error al eliminar al proveedor", true);
			return null;
		}
		return "index"+redirect;
	}
	
	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		
		
		
		if(newValue != null && !newValue.equals(oldValue)) {
			int index = event.getRowIndex();
			Provider edited = null;
			if(filtered == null) {
				edited = list.get(index);
			} else {
				edited = filtered.get(index);
			}
			edited.setModificationUser(getUsername());
			if(!edited.getMail().trim().isEmpty()) {
				if(!Pattern.matches(Configuration.validMail, edited.getMail().trim())) {
					addMessage("Debes escribir una dirección de correo válida", true);
					return;
				}
			}
			
			if(!ObjectService.save(edited)) {
				addMessage("Ocurrió un error al editar al proveedor", true);
				edited.setName(oldValue.toString());
			}
		}
    }
	
	public void onRowEdit(RowEditEvent event) {
		
//		if(newValue != null && !newValue.equals(oldValue)) {
//			int index = event.getRowIndex();
			Provider edited = null;
//			if(filtered == null) {
//				edited = list.get(index);
//			} else {
//				edited = filtered.get(index);
//			}
			edited = (Provider) event.getObject();
			edited.setModificationUser(getUsername());
			if(!edited.getMail().trim().isEmpty()) {
				if(!Pattern.matches(Configuration.validMail, edited.getMail().trim())) {
					addMessage("Debes escribir una dirección de correo válida", true);
					return;
				}
			}
			StringBuffer buff = new StringBuffer();
			if(!edited.getChips().isEmpty()) {
				chips = edited.getChips();
				for(String s:chips) {
					buff.append(s+",");
				}
			}
			buff.deleteCharAt(buff.length()-1);
			System.out.println(buff);
			edited.setList(buff.toString());
			if(!ObjectService.save(edited)) {
				addMessage("Ocurrió un error al editar al proveedor", true);
			}
//		}
    }

	public List<String> getChips() {
		return chips;
	}

	public void setChips(List<String> chips) {
		this.chips = chips;
	}
	
}
