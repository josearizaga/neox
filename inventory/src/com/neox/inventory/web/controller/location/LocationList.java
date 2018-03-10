package com.neox.inventory.web.controller.location;

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

import com.neox.inventory.model.area.Location;
import com.neox.inventory.service.CategoryService;
import com.neox.inventory.service.LocationService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class LocationList extends Main implements Serializable {
	
	private Location selected;
	private List<Location> list;
	private List<Location> filtered;
	private TreeNode root;
	
	public LocationList() {}
	
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
	
	public Location getSelected() {
		return selected;
	}

	public void setSelected(Location selected) {
		this.selected = selected;
		setAttribute("selectedLocation", selected);
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

	public List<Location> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Location> filtered) {
		this.filtered = filtered;
	}
	
	public void onCellEdit(RowEditEvent event) {
		TreeNode node = (TreeNode)event.getObject();
	   	if(node != null) {
	   		Location edited = (Location)node.getData();
	   		edited.setModificationUser(getUsername());
	    		if(!ObjectService.save(edited)) {
	    			addMessage("Ocurrió un error al editar la ubicación", true);
	    		}
	   	}
    }
	
	private TreeNode find(String rowKey, TreeNode node) {
		StringTokenizer st = new StringTokenizer(rowKey, "_");
		TreeNode result = null;
		while(st.hasMoreTokens()) {
			Integer index = new Integer(st.nextToken());
			node = node.getChildren().get(index);
		}
		
		return node;
	}
	
	public void onValueChange(Object e) {
		Location edited = (Location)e;
		edited.setModificationUser(getUsername());
		if(!ObjectService.save(edited)) {
			addMessage("Ocurrió un error al la ubicación", true);
		}
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public String delete() {
		if(LocationService.hasChilds(selected)) {
			addMessage("La ubicación que intentas eliminar tiene ubicaciones asignadas", true);
			return null;
		}
		if(!ObjectService.delete(selected)) {
			addMessage("Ocurrió un error al eliminar la ubicación", true);
			return null;
		}
		return "index"+redirect;
	}
	
}
