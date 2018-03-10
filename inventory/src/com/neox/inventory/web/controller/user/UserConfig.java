package com.neox.inventory.web.controller.user;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

import com.neox.inventory.model.user.User;
import com.neox.inventory.model.user.UserInfo;
import com.neox.inventory.service.ObjectService;
import com.neox.inventory.service.UserService;
import com.neox.inventory.util.StringUtils;
import com.neox.inventory.web.controller.Main;

@ViewScoped
@ManagedBean
public class UserConfig extends Main implements Serializable {
	
	private User user;
	private UserInfo userInfo;
	
	public UserConfig() {
		user = new User();
		userInfo = new UserInfo();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
    }
	
	public String save() {
		user.setPasswd(StringUtils.MD5(user.getPasswd()));
		if(!UserService.save(user,userInfo)) {
			addMessage("Ocurrió un error al agregar al usuario", true);
			return null;
		}
		return "index" + redirect;
	}
	
	public String getHiddenPasswd() {
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < user.getPasswd().length(); i++) {
			str.append("*");
		}
		return str.toString();
	}
}
