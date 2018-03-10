package com.neox.inventory.web.controller.area;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.neox.inventory.model.area.Area;
import com.neox.inventory.model.area.AreaMaterial;
import com.neox.inventory.model.area.AreaMaterialView;
import com.neox.inventory.model.material.Material;
import com.neox.inventory.service.AreaMaterialService;
import com.neox.inventory.service.AreaMaterialViewService;
import com.neox.inventory.service.AreaService;
import com.neox.inventory.service.MaterialService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class AreaMaterialEdit extends Main implements Serializable {
	
	@ManagedProperty(value = "#{idArea}")
	private Integer idArea;
	private Area area;
	private List<Material> target;
	private List<Material> source;
	private DualListModel<Material> materialList;
	
	public AreaMaterialEdit() {}
	
	@PostConstruct
	public void init() {
		area = AreaService.getById(idArea);
		target = MaterialService.getInArea(idArea);
		source = MaterialService.getNotInArea(idArea);
		materialList = new DualListModel<Material>(source, target);
	}
	
	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public DualListModel<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(DualListModel<Material> materialList) {
		this.materialList = materialList;
	}
	
	public void onTransfer(TransferEvent event) {
		System.out.println(event.isAdd() + " - " + event.isRemove() + " - " + event.getItems());
		for(Object item : event.getItems()) {
			System.out.println(item);
			Integer edited = new Integer(item.toString());
			if(event.isRemove()) {
			    AreaMaterial mat = AreaMaterialService.getByIds(idArea, edited);
			    if(!ObjectService.delete(mat)) {
			    		addMessage("Ocurrió un error al eliminar el material", true);
			    }
			}
			if(event.isAdd()) {
				AreaMaterial mat = new AreaMaterial();
				mat.setIdArea(idArea);
				mat.setIdMaterial(edited);
				if(!ObjectService.save(mat)) {
			    		addMessage("Ocurrió un error al agregar el material", true);
			    }
			}
		}
         
        
    }
}
