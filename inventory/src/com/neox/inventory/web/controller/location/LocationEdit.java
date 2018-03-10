package com.neox.inventory.web.controller.location;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.neox.inventory.model.area.Location;
import com.neox.inventory.service.LocationService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class LocationEdit extends Main implements Serializable {
	
	private List<Location> list;
	private TreeNode root;
	
	public LocationEdit() {}
	
	@PostConstruct
	public void init() {
		if(list == null) {
			getList();
		}
		root = new DefaultTreeNode();
		fillTree(0,root);
	}	
	
	private void fillTree(Integer idLocation, TreeNode parent) {
		for(Location loc:list) {
			if(loc.getIdLocation() == idLocation) {
				TreeNode node = new DefaultTreeNode(loc,parent);
				fillTree(loc.getId(),node);
			}
		}
	}
	
	public List<Location> getList() {
		if(list == null) {
			list = LocationService.getAll();
		}
		return list;
	}

	public void setList(List<Location> list) {
		this.list = list;
	}
	
	public void onDragDrop(TreeDragDropEvent event) {
		TreeNode dragNode = event.getDragNode();
		TreeNode dropNode = event.getDropNode();
		int dropIndex = event.getDropIndex();
		 
		Location drag = (Location) dragNode.getData();
		Location drop = (Location) dropNode.getData();
		
		if(drop != null) {
			drag.setIdLocation(drop.getId());
		} else {
			drag.setIdLocation(0);
		}
		drag.setModificationUser(getUsername());
		if(!ObjectService.save(drag)) {
			addMessage("Ocurrió un error al agregar la categoría", true);
		}
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
}
