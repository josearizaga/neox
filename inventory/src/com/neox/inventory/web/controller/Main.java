package com.neox.inventory.web.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.neox.inventory.model.area.Area;
import com.neox.inventory.model.area.Location;
import com.neox.inventory.model.area.Validation;
import com.neox.inventory.model.material.Category;
import com.neox.inventory.model.material.InventoryView;
import com.neox.inventory.model.material.Material;
import com.neox.inventory.model.material.UOM;
import com.neox.inventory.model.provider.Provider;
import com.neox.inventory.model.user.User;
import com.neox.inventory.model.user.UserView;
import com.neox.inventory.service.AreaService;
import com.neox.inventory.service.CategoryService;
import com.neox.inventory.service.InventoryViewService;
import com.neox.inventory.service.LocationService;
import com.neox.inventory.service.MaterialService;
import com.neox.inventory.service.ProviderService;
import com.neox.inventory.service.UOMService;
import com.neox.inventory.service.UserService;
import com.neox.inventory.service.ValidationService;


@ManagedBean
public class Main {
	
	@ManagedProperty(value = "#{username}")
	private String username;
	
	public String redirect = "?faces-redirect=true";
	
	public void addMessage(String message, boolean isError) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(message);
		if(isError) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		ctx.addMessage(null, msg);
	}
	
	public void setAttribute(String key, Object value) {
		HttpSession session = getSession();
		session.setAttribute(key, value);
	}
	
	public Object getAttribute(String key) {
		HttpSession session = getSession();
		return session.getAttribute(key);
	}
	
	public void removeAttribute(String key) {
		HttpSession session = getSession();
		session.removeAttribute(key);
	}
	
	public HttpSession getSession() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return session;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return (String)getAttribute("username");
	}
	
	public List<Category> getCategories() {
		return CategoryService.getList();
	}
	
	public List<UOM> getUnits() {
		return UOMService.getList();
	}
	
	public List<Location> getLocations() {
		return LocationService.getList();
	}
	
	public List<Area> getAreas() {
		return AreaService.getList();
	}
	
	public List<Material> getMaterials() {
		System.out.println("Materiales!!!");
		return MaterialService.getList();
	}
	
	public List<Provider> getProviders() {
		return ProviderService.getList();
	}
	
	public List<Validation> getValidations() {
		return ValidationService.getList();
	}
	
	public List<UserView> getUsers() {
		return UserService.getList();
	}
	
	public String redirect(String direction) {
		System.out.println("Direction: " + direction); 
		return direction + redirect;
	}
	
	public String getProviderName(Integer idProvider) {
		List<Provider> plist = getProviders();
		String value = "noname";
		for(Provider p:plist) {
			if(p.getId().intValue() == idProvider.intValue()) {
				value = p.getName();
				break;
			}
		}
		return value;
	}
	
	public String getMaterialName(Integer idMaterial) {
		List<Material> mlist = getMaterials();
		String value = "noname";
		for(Material m:mlist) {
			System.out.print(m.getId()+", ");
			if(m.getId().intValue() == idMaterial.intValue()) {
				System.out.println("\nFound\n");
				value = m.getMaterial();
				break;
			}
		}
		return value;
	}
	
	public String getLocationName(Integer idLocation) {
		List<Location> llist = getLocations();
		String value = "noname";
		for(Location l:llist) {
			if(l.getId().intValue() == idLocation.intValue()) {
				value = l.getLocation();
				break;
			}
		}
		return value;
	}
	
	public String getCategoryName(Integer idCategory) {
		List<Category> clist = getCategories();
		for(Category c:clist) {
			if(idCategory.intValue() == c.getId().intValue()) {
				return c.getCategory();
			}
		}
		return null;
	}
	
	public String getUnitName(Integer id) {
		List<UOM> ulist = getUnits();
		for(UOM u:ulist) {
			if(id.intValue() == u.getId().intValue()) {
				return u.getUom();
			}
		}
		return null;
	}
	
	public String getAreaName(Integer id) {
		List<Area> alist = getAreas();
		for(Area u:alist) {
			if(id.intValue() == u.getId().intValue()) {
				return u.getName();
			}
		}
		return null;
	}
	
	public User getUser() {
		User user = (User) getAttribute("user");
		return user;
	}
	
	public boolean isLoggedIn() {
		User user = getUser();
		return user != null;
	}
	
	public boolean isAdmin() {
		User user = getUser();
		return (user != null)?user.isAdmin():false;
	}
	
	public Integer getUserId() {
		User user = getUser();
		return (user != null)?user.getId():-1;
	}
	
}
