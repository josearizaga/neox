package com.neox.inventory.web.controller.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.neox.inventory.model.area.AreaMaterial;
import com.neox.inventory.model.menu.Menu;
import com.neox.inventory.model.menu.UserMenu;
import com.neox.inventory.model.user.UserView;
import com.neox.inventory.service.AreaMaterialService;
import com.neox.inventory.service.MenuService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.UserMenuService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class UserData extends Main implements Serializable {
	
	@ManagedProperty(value = "#{selectedUserView}")
	private UserView selected;
	private DualListModel<Menu> menuList;
	
	public UserData() {}

	public UserView getSelected() {
		return selected;
	}

	public void setSelected(UserView selected) {
		this.selected = selected;
	}

	
	
	@PostConstruct
    public void init() {
		if(selected == null) {
			menuList = new DualListModel<Menu>();
			return;
		}
		List<Menu> list = MenuService.getOutcome(selected.getId());
		List<Menu> lselected = MenuService.getOutcomeIn(selected.getId());
		menuList = new DualListModel<Menu>(list,lselected);
	}

	public DualListModel<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(DualListModel<Menu> menuList) {
		this.menuList = menuList;
	}
	
	public void onTransfer(TransferEvent event) {
		System.out.println(event.isAdd() + " - " + event.isRemove() + " - " + event.getItems());
		for(Object item : event.getItems()) {
			System.out.println(item);
			Integer edited = new Integer(item.toString());
			UserMenu userMenu = null;
			if(event.isRemove()) {
				userMenu = UserMenuService.byIds(selected.getId(), edited);
				if(!ObjectService.delete(userMenu)) {
					addMessage("Ocurrió un error al eleiminar el menu al usuario", true);
					return;
				}
			}
			if(event.isAdd()) {
				userMenu = new UserMenu();
				userMenu.setIdUser(selected.getId());
				userMenu.setIdMenu(edited);
				userMenu.setId(UUID.randomUUID().toString());
				System.out.println(userMenu.getId());
				if(!ObjectService.save(userMenu)) {
					addMessage("Ocurrió un error al agregar el menu al usuario", true);
					return;
				}
//				AreaMaterial mat = new AreaMaterial();
//				mat.setIdArea(idArea);
//				mat.setIdMaterial(edited);
//				if(!ObjectService.save(mat)) {
//			    		addMessage("Ocurrió un error al agregar el material", true);
//			    }
			}
		}
         
        
    }
}
