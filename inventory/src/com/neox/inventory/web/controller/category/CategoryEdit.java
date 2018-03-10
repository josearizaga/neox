package com.neox.inventory.web.controller.category;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.neox.inventory.model.material.Category;
import com.neox.inventory.service.CategoryService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class CategoryEdit extends Main implements Serializable {
		
	private List<Category> list;
	private TreeNode root;
	
	public CategoryEdit() {}
	
	@PostConstruct
	public void init() {
		if(list == null) {
			getList();
		}
		root = new DefaultTreeNode();
		fillTree(0,root);
	}
	
	private void fillTree(Integer idCategory, TreeNode parent) {
		for(Category cat:list) {
			if(cat.getIdCategory() == idCategory) {
				TreeNode node = new DefaultTreeNode(cat,parent);
				fillTree(cat.getId(),node);
			}
		}
	}
	
	public List<Category> getList() {
		if(list == null) {
			list = CategoryService.getAll();
		}
		return list;
	}
	public void setList(List<Category> list) {
		this.list = list;
	}
	
	public TreeNode getRoot() {
		return root;
	}
	
	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public void onDragDrop(TreeDragDropEvent event) {
		TreeNode dragNode = event.getDragNode();
		TreeNode dropNode = event.getDropNode();
		int dropIndex = event.getDropIndex();
		 
		Category drag = (Category) dragNode.getData();
		Category drop = (Category) dropNode.getData();
		
		if(drop != null) {
			drag.setIdCategory(drop.getId());
		} else {
			drag.setIdCategory(0);
		}
		drag.setModificationUser(getUsername());
		if(!ObjectService.save(drag)) {
			addMessage("Ocurrió un error al agregar la categoría", true);
		}
	}
}
