package com.neox.inventory.web.controller.validation;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.neox.inventory.model.area.Validation;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.ValidationService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class ValidationList extends Main implements Serializable {
	
	private List<Validation> list;
	private TreeNode root;
	
	public ValidationList() {}
	
	@PostConstruct
	public void init() {
		if(list == null) {
			getList();
		}
		root = new DefaultTreeNode();
		fillTree(0,root);
	}
	
	private void fillTree(Integer idValidation, TreeNode parent) {
		for(Validation val:list) {
			if(val.getIdValidation() == idValidation) {
				TreeNode node = new DefaultTreeNode(val,parent);
				fillTree(val.getId(),node);
			}
		}
	}
	
	public void onRowEdit(RowEditEvent event) {

		TreeNode node = (TreeNode)event.getObject();
	   	if(node != null) {
	   		Validation edited = (Validation)node.getData();
	   		edited.setModificationUser(getUsername());
	   		Validation other = ValidationService.byIdArea(edited.getIdArea());
	   		if(other != null && other.getId() != edited.getId()) {
	   			addMessage("Ya existe una validación para esa área", true);
	   			return;
	   		}
    		if(!ObjectService.save(edited)) {
    			addMessage("Ocurrió un error al editar la categoría", true);
    		}
		  }
    }
	
	public List<Validation> getList() {
		if(list == null) {
			list = ValidationService.getAll();
		}
		return list;
	}

	public void setList(List<Validation> list) {
		this.list = list;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
}
