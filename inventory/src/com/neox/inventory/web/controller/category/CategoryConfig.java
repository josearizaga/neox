package com.neox.inventory.web.controller.category;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neox.inventory.model.material.Category;
import com.neox.inventory.service.CategoryService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class CategoryConfig extends Main implements Serializable {
	
	private Category category;
	private List<Category> categoryList;
	
	public CategoryConfig() {
		category = new Category();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String save() {
		category.setCreationUser(getUsername());
		category.setModificationUser(getUsername());
		if(!ObjectService.save(category)) {
			addMessage("Ocurrió un error al agregar la categoría", true);
			return null;
		}
		return "index"+redirect;
	}
	
	
	
}
