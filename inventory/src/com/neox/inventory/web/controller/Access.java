package com.neox.inventory.web.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import com.neox.inventory.model.user.User;
import com.neox.inventory.service.UserService;
import com.neox.inventory.util.StringUtils;

@ViewScoped
@ManagedBean
public class Access extends Main implements Serializable {
	
	private String username;
	private String passwd;
	
	
	public String logout() {
		HttpSession session = getSession();
		while(session.getAttributeNames().hasMoreElements()) {
			String next = session.getAttributeNames().nextElement();
			System.out.println("Next: " + next);
			removeAttribute(next);
		}
		return "index" + redirect;
	}
	
	public String isLogged() {
		System.out.println("isLoggedIn: " + isLoggedIn());
		return (isLoggedIn())?null:"/login" + redirect;
	}
	
	public String login() {
		passwd = StringUtils.MD5(passwd);
		User user = UserService.isUser(username, passwd);
		if(user != null) {
			setAttribute("user", user);
			setAttribute("username",user.getUsername());
			return "index" + redirect;
		} else {
			addMessage("Usuario o contraseña inválidos", true);
			return null;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String onIdle() {
		logout();
		return "asdf";
	}
}
