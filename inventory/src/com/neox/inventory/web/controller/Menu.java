package com.neox.inventory.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

import com.neox.inventory.service.MenuService;

@ViewScoped
@ManagedBean
public class Menu extends Main implements Serializable {
	
	private MenuModel menuModel;
	List<com.neox.inventory.model.menu.Menu> list;

	public MenuModel getMenuModel() {
		if(menuModel == null) {
			menuModel = new DefaultMenuModel();
			/*
			DefaultMenuItem item = new DefaultMenuItem();
			item.setValue("Inicio");
			item.setIcon("ui-icon-gear");
			item.setOutcome("/index");
			DefaultSubMenu submenu = new DefaultSubMenu("Enlaces");
			submenu.addElement(item);
			
			DefaultSubMenu _sub = new DefaultSubMenu("Materiales");
			item = new DefaultMenuItem();
			item.setValue("Entradas");
			item.setIcon("ui-icon-wrench");
			item.setOutcome("/webapp/income/index");
			_sub.addElement(item);
			submenu.addElement(_sub);
			
			menuModel.addElement(submenu);
			*/
			if(isAdmin()) {
				list = MenuService.getAll();
			} else {
				list = MenuService.getById(getUserId());
			}
			System.out.println(list);
			for(com.neox.inventory.model.menu.Menu menu:list) {
				if(menu.getIdMenu().intValue() == 0) {
					DefaultSubMenu submenu = new DefaultSubMenu(menu.getLabel());
					buildSubMenu(submenu, menu.getId());
					menuModel.addElement(submenu);
				}
			}
		}
		
		return menuModel;
	}
	
	private void buildSubMenu(DefaultSubMenu submenu, Integer idMenu) {
		for(com.neox.inventory.model.menu.Menu menu:list) {
			if(menu.getIdMenu() != idMenu) {
				continue;
			}
			if(menu.getValue() != null ) {
				DefaultMenuItem item = new DefaultMenuItem();
				item.setValue(menu.getValue());
				item.setIcon("ui-icon-gear");
				item.setOutcome(menu.getOutcome());
				submenu.addElement(item);
			}
			if(menu.getLabel() != null) {
				DefaultSubMenu _submenu = new DefaultSubMenu(menu.getLabel());
				buildSubMenu(_submenu, menu.getId());
				submenu.addElement(_submenu);
			}
		}
	}
	
	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public Menu() {}
	
	
}
