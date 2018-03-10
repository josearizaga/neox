package com.neox.inventory.web.controller.provider;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;

import com.neox.inventory.model.provider.Provider;
import com.neox.inventory.model.provider.ProviderContact;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.ProviderContactService;
import com.neox.inventory.web.controller.Configuration;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class ProviderContactList extends Main implements Serializable {
	
	@ManagedProperty(value = "#{selectedProvider}")
	Provider provider;
	
	List<ProviderContact> list;
	List<ProviderContact> filtered;
	ProviderContact selected;
	
	public ProviderContactList() {}
	
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public List<ProviderContact> getList() {
		if(list == null) {
			list = ProviderContactService.getAll(provider.getId());
		}
		return list;
	}

	public void setList(List<ProviderContact> list) {
		this.list = list;
	}

	public List<ProviderContact> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<ProviderContact> filtered) {
		this.filtered = filtered;
	}

	public ProviderContact getSelected() {
		return selected;
	}

	public void setSelected(ProviderContact selected) {
		this.selected = selected;
	}
	
	public void onValueChange(Object e) {
		ProviderContact edited = (ProviderContact)e;
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
			ProviderContact edited = null;
			if(filtered == null) {
				edited = list.get(index);
			} else {
				edited = filtered.get(index);
			}
			edited.setModificationUser(getUsername());
			
			if(ProviderContactService.byName(edited.getName()) != null) {
				addMessage("Ya existe un contacto con ese nombre", true);
				edited.setName(oldValue.toString());
				return;
			}
			
			if(!edited.getMail().trim().isEmpty()) {
				if(!Pattern.matches(Configuration.validMail, edited.getMail().trim())) {
					addMessage("Debes escribir una dirección de correo válida", true);
					return;
				}
			}
			
			if(!ObjectService.save(edited)) {
				addMessage("Ocurrió un error al editar al proveedor", true);
			}
		}
    }
	
}
