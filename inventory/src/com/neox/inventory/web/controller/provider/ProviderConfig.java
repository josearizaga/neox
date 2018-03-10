package com.neox.inventory.web.controller.provider;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.provider.Provider;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Configuration;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class ProviderConfig  extends Main implements Serializable{
	
	private Provider provider;
	private List<String> list;
	
	public ProviderConfig() {
		provider = new Provider();
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public String save() {
		if(!provider.getMail().trim().isEmpty()) {
			if(!Pattern.matches(Configuration.validMail, provider.getMail().trim())) {
				addMessage("Debes escribir una dirección de correo válida", true);
				return null;
			}
		}
		provider.setCreationUser(getUsername());
		provider.setModificationUser(getUsername());
		StringBuffer buff = new StringBuffer();
		if(!list.isEmpty()) {
			for(String s:list) {
				buff.append(s+",");
			}
		}
		buff.deleteCharAt(buff.length()-1);
		provider.setList(buff.toString());
		if(!ObjectService.save(provider)) {
			addMessage("Ocurrió un error al agregar al proveedor", true);
			return null;
		}
		return "index"+redirect;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	
}
