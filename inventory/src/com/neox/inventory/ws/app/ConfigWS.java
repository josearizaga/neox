package com.neox.inventory.ws.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neox.inventory.model.area.Area;
import com.neox.inventory.service.AreaService;
import com.neox.inventory.util.StringUtils;
import com.neox.inventory.ws.bean.Token;
import com.neox.inventory.ws.util.MapperUtil;
import com.neox.inventory.ws.util.Properties;

@Path("config")
public class ConfigWS {
	
	@Context
    private ServletContext context;
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("notAuthorized.json")
	public String notAuthorized() {
		return "{\"code\":\"403\",\"error\":\"Servicio restringido para usuarios registrados\"}";
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("notAuthorized.json")
	public String notGetAuthorized() {
		return "{\"code\":\"403\"}";
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("reload.json")
	public String reload() {
		Properties.reload();
		return "{}";
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("isAlive.json")
	public String lifeBit() {
		return "{\"isAlive\":\"1\"}";
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("topic.json")
	public String topic() {
		String value = null;
		Properties.loadFile(context.getRealPath("/WEB-INF/config.properties"));
		
		ObjectMapper mapper = MapperUtil.getMapper();
		Map<String,Object> map = new HashMap<>();
		
		map.put("topic", Properties.getProperty("config.topic.name"));
		map.put("code", 200);
		//map.put("senderId", Properties.getProperty("config.topic.senderId"));
		
		try {
			value = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			value = String.format("{\"code\":\"500\",\"error\":\"%s\"}", e.getMessage());
		}
		
		return value;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("area.json")
	public String getAreaList(Token token) {
		String value = null;
		try {
			ObjectMapper mapper = MapperUtil.getMapper();
			
			List<Area> list = AreaService.getList();
			StringBuffer hash = new StringBuffer();
			for(Area a:list) {
				hash.append(a.getId()+"|");
			}
			String digest = StringUtils.MD5(hash.toString());
			Map<String,Object> map = new HashMap<>();
			if(token != null && token.getToken().compareTo(digest) == 0) {
				map.put("code", "204");
			} else {
				map.put("code", "200");
				map.put("token", digest);
				map.put("areaList", list);
			}
			
			
			value = mapper.writeValueAsString(map);
			
		} catch(Exception e) {
			value = String.format("{\"code\":\"500\",\"error\":\"%s\"}", e.getMessage());
		}
		
		return value;
	}
	
}
