package com.neox.inventory.web.controller.area;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.neox.inventory.model.area.AreaUser;
import com.neox.inventory.model.area.AreaUserView;
import com.neox.inventory.model.user.UserView;
import com.neox.inventory.service.AreaUserService;
import com.neox.inventory.service.AreaUserViewService;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.UserViewService;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class AreaUserList extends Main implements Serializable {
	
	private DualListModel<UserView> list;
	private Integer idArea = 0;
	
	public AreaUserList() {}

	public DualListModel<UserView> getList() {
		if(idArea > 0) {
			//list = AreaUserViewService.getListByArea(idArea);
			List<UserView> source = UserViewService.getListByIdArea(idArea, false);
			List<UserView> target = UserViewService.getListByIdArea(idArea, true);
			list = new DualListModel<UserView>(source, target);
		}
		return list;
	}

	public void setList(DualListModel<UserView> list) {
		this.list = list;
	}

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}
	
	public void onTransfer(TransferEvent event) {
		UserView moved = null;
		AreaUser areaUser = null;
		List<AreaUser> luser = new ArrayList<AreaUser>();
        for(Object item : event.getItems()) {
            moved = (UserView) item;
            if(event.isAdd()) {
            	areaUser = new AreaUser();
            	areaUser.setIdArea(idArea);
            	areaUser.setIdUser(moved.getId());
            	if(!ObjectService.save(areaUser)) {
            		addMessage("Ocurrió un error al actualizar los datos", true);
            	}
            } else {
            	areaUser = AreaUserService.byIds(idArea, moved.getId());
            	if(!ObjectService.delete(areaUser)) {
            		addMessage("Ocurrió un error al eliminar los datos", true);
            	}
            }
            
        }
    }
}
