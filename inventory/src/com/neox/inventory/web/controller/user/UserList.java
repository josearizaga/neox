package com.neox.inventory.web.controller.user;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.neox.inventory.model.user.User;
import com.neox.inventory.model.user.UserView;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.UserService;
import com.neox.inventory.util.StringUtils;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class UserList extends Main implements Serializable {
	
	private List<UserView> list;
	private List<UserView> filtered;
	
	public UserList() {}

	public List<UserView> getList() {
		if(list == null) {
			list = UserService.getAll();
		}
		return list;
	}

	public void setList(List<UserView> list) {
		this.list = list;
	}

	public List<UserView> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<UserView> filtered) {
		this.filtered = filtered;
	}
	
	public void onRowEdit(RowEditEvent event) {
			UserView uv = (UserView) event.getObject();
			User edited = UserService.byId(uv.getId());
			if(uv.getPasswd() != null) {
				String npasswd = StringUtils.MD5(uv.getPasswd());
				if(npasswd.compareTo(edited.getPasswd()) != 0) {
					edited.setPasswd(npasswd);
				}
			}
			if(edited.getUserInfo() != null) {
				edited.getUserInfo().setName(uv.getFirstname());
				edited.getUserInfo().setLastname(uv.getLastname());
			} else {
				edited.newInfo(uv.getFirstname(), uv.getLastname());
			}
			edited.setUsername(uv.getUsername());
			edited.setActive(uv.getActive());
			if(!ObjectService.save(edited)) {
				addMessage("Ocurrió un error al editar el material", true);
				return;
			}
			uv.setName(uv.getFirstname() + " " + uv.getLastname());
    }

}
