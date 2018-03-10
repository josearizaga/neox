package com.neox.inventory.web.controller.provider;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.provider.Provider;
import com.neox.inventory.model.provider.ProviderContact;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.ProviderContactService;
import com.neox.inventory.web.controller.Configuration;
import com.neox.inventory.web.controller.Main;

@ManagedBean
@ViewScoped
public class ProviderContactConfig extends Main implements Serializable {
	
	@ManagedProperty(value = "#{selectedProvider}")
	Provider provider;
	
	ProviderContact contact;
	
	public ProviderContactConfig() {
		contact = new ProviderContact();
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public ProviderContact getContact() {
		return contact;
	}

	public void setContact(ProviderContact contact) {
		this.contact = contact;
	}
	
	public String save() {
		contact.setCreationUser(getUsername());
		contact.setModificationUser(getUsername());
		contact.setIdProvider(provider.getId());
		
		if(ProviderContactService.byName(contact.getName()) != null) {
			addMessage("Ya existe un contacto con ese nombre", true);
			return null;
		}
		
		if(!contact.getMail().trim().isEmpty()) {
			if(!Pattern.matches(Configuration.validMail, contact.getMail().trim())) {
				addMessage("Debes escribir una dirección de correo válida", true);
				return null;
			}
		}
		
		if(!ObjectService.save(contact)) {
			addMessage("Ocurrió un error al agregar el contacto al proveedor", true);
			return null;
		}
		return "contacts"+redirect;
	}
	
}
