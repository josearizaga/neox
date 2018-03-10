package com.neox.inventory.web.controller.category;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.neox.inventory.model.area.Area;
import com.neox.inventory.model.material.Category;
import com.neox.inventory.service.CategoryService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class CategoryList extends Main implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8125735469187754441L;
	private Category selectedCategory;
	private List<Category> list;
	private List<Category> filtered;
	private TreeNode root;
	
	public CategoryList() {}
	
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
	
	public Category getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
		setAttribute("selectedCategory", selectedCategory);
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

	public List<Category> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Category> filtered) {
		this.filtered = filtered;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public String edit() {
		return "edit"+redirect;
	}
	
	public String delete() {
		if(CategoryService.hasChilds(selectedCategory)) {
			addMessage("La categoría que intentas eliminar tiene categorías asignadas", true);
			return null;
		}
		if(!ObjectService.delete(selectedCategory)) {
			addMessage("Ocurrió un error al eliminar la categoría", true);
			return null;
		}
		return "index"+redirect;
	}
	
	private TreeNode find(String rowKey, TreeNode node) {
		//1_0
		StringTokenizer st = new StringTokenizer(rowKey, "_");
		TreeNode result = null;
		while(st.hasMoreTokens()) {
			Integer index = new Integer(st.nextToken());
			node = node.getChildren().get(index);
		}
		
		return node;
	}
	
	public void onCellEdit(RowEditEvent event) {

		TreeNode node = (TreeNode)event.getObject();
	   	if(node != null) {
	   		Category edited = (Category)node.getData();
	   		edited.setModificationUser(getUsername());
	    		if(!ObjectService.save(edited)) {
	    			addMessage("Ocurrió un error al editar la categoría", true);
	    		}
		  }
    }
	
	public void onValueChange(Object e) {
		Category edited = (Category)e;
   		edited.setModificationUser(getUsername());
		if(!ObjectService.save(edited)) {
			addMessage("Ocurrió un error al editar el área", true);
		}
	}
	
	public String getCategoryName(Integer id) {
		for(Category c:list) {
			if(id == c.getId()) {
				return c.getCategory();
			}
		}
		return null;
	}
}
