package com.neox.inventory.ws.app;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neox.inventory.model.area.AreaUserView;
import com.neox.inventory.model.user.User;
import com.neox.inventory.model.user.UserView;
import com.neox.inventory.service.UserService;
import com.neox.inventory.util.StringUtils;
import com.neox.inventory.ws.bean.Element;
import com.neox.inventory.ws.bean.Token;
import com.neox.inventory.ws.util.MapperUtil;



@Path("user")
public class UserWS {
	
	@Context
    ContainerRequestContext ctx;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("login.json")
	public String Login(User user) throws JsonProcessingException {
		User u = null;
		String name;
		ObjectMapper mapper = MapperUtil.getMapper();
		Map<String,Object> map = new HashMap<>();
		try {
			u = UserService.isUser(user.getUsername(), user.getPasswd());
			if(u == null) {
				map.put("code", "403");
				map.put("status", "Usuario o contraseña inválidos");
			} else {
				map.put("code", "200");
				map.put("id", u.getId());
				if(u.getUserInfo() != null && u.getUserInfo().getName() != null) {
					name = u.getUserInfo().getName() + " " + u.getUserInfo().getLastname();
				} else {
					name = u.getUsername();
				}
				map.put("name", name);
				map.put("username", u.getUsername());
				map.put("status", Base64.getEncoder().encodeToString(new String(user.getUsername()+":"+user.getPasswd()).getBytes()));
			}
		} catch(Exception e) {
			e.printStackTrace();
			map.put("code", "403");
			map.put("status", "Usuario o contraseña inválidos");
		}
		
		return mapper.writeValueAsString(map);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list.json")
	public String getUserList(Token data) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<UserView> list = UserService.getList();
		StringBuffer token = new StringBuffer();
		for(UserView u:list) {
			token.append(u.getId()+"|");
		}
		String hash = StringUtils.MD5(token.toString());
		
		Map<String,Object> a = new HashMap<>();
		
		if(data != null && data.getToken().compareTo(hash) == 0) {
			a.put("code", "204");
		} else {
			a.put("code", "200");
			a.put("token", hash);
			a.put("userList", list);
		}
		return mapper.writeValueAsString(a);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listByArea.json")
	public String getAreaUserList(Element data) throws JsonProcessingException {
		ObjectMapper mapper = MapperUtil.getMapper();
		List<AreaUserView> list = UserService.byArea(new Integer(data.getKey()));
		StringBuffer token = new StringBuffer();
		for(AreaUserView u:list) {
			token.append(u.getId()+"|");
		}
		String hash = StringUtils.MD5(token.toString());
		
		Map<String,Object> a = new HashMap<>();
		
		if(data != null && data.getValue().compareTo(hash) == 0) {
			a.put("code", "204");
		} else {
			a.put("code", "200");
			a.put("token", hash);
			a.put("userList", list);
		}
		return mapper.writeValueAsString(a);
	}
}
