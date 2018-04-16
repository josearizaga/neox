package com.neox.inventory.ws.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neox.inventory.model.material.MaterialMovementView;
import com.neox.inventory.model.material.MaterialMovementFullView;
import com.neox.inventory.model.material.Status;
import com.neox.inventory.service.MaterialMovementViewService;
import com.neox.inventory.service.MaterialMovementFullViewService;
import com.neox.inventory.service.StatusService;
import com.neox.inventory.ws.bean.Element;
import com.neox.inventory.ws.controller.MaterialMovementController;
import com.neox.inventory.ws.helper.MaterialMovementHelper;
import com.neox.inventory.ws.util.Auth;
import com.neox.inventory.ws.util.MapperUtil;

@Path("movement")
public class MaterialMovementWS extends Auth {
	
	MaterialMovementController controller = new MaterialMovementController();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("updateMaterial.json")
	public String addMaterial(MaterialMovementHelper movement) {
		String value = null;
		
		if(movement == null || movement.getList() == null || movement.getList().size() <= 0) {
			return String.format("{\"code\":\"400\",\"error\":\"%s\"}", "Error en la cabecera, datos nulos");
		}
		
		ObjectMapper mapper = MapperUtil.getMapper();
		Map<String,Object> map = new HashMap<>();
		
		String username = getUsername();
		if(username == null) {
			map.put("code", "500");
			map.put("error", "Usuario desconocido");
		} else {
			movement.setUsername(username);
			if(controller.updateMaterial(movement)) {
				map.put("code", "200");
			} else {
				map.put("code", "500");
				map.put("error", controller.getError());
			}
		}
		try {
			value = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			value = String.format("{\"code\":\"500\",\"error\":\"%s\"}", e.getMessage());
		}
		return value;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("materialList.json")
	public String getMaterialList(Element element) {
		String value = null;
		if(element == null) {
			return String.format("{\"code\":\"400\",\"error\":\"%s\"}", "No se enviaron los datos necesarios");
		}
		if(element.getKey() == null || element.getKey().compareTo("idHeader") != 0) {
			return String.format("{\"code\":\"400\",\"error\":\"%s\"}", "Error en la cabecera, datos nulos");
		}
		if(element.getValue() == null) {
			return String.format("{\"code\":\"400\",\"error\":\"%s\"}", "Error en la cabecera, dato inválido");
		}
		Integer idHeader = new Integer(element.getValue());
		if(idHeader <= 0) {
			return String.format("{\"code\":\"400\",\"error\":\"%s\"}", "Error en el id de cabecera");
		}
		ObjectMapper mapper = MapperUtil.getMapper();
		Map<String,Object> map = new HashMap<>();
		Status na = StatusService.byName("Pendiente");
		Status pa = StatusService.byName("Parcial");
		List<MaterialMovementFullView> list = MaterialMovementFullViewService.getMaterialListByIdHeader(idHeader,na.getId(),pa.getId());
		
		if(list.isEmpty()) {
			map.put("code", "204");
		} else {
			map.put("code", "200");
			map.put("list", list);
		}
		try {
			value = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			value = String.format("{\"code\":\"500\",\"error\":\"%s\"}", e.getMessage());
		}
		return value;
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("materialMovement.json")
	public String getMaterialMovement() {
		String value = null;
		ObjectMapper mapper = MapperUtil.getMapper();
		Map<String,Object> map = new HashMap<>();
		
		Status pendiente = StatusService.byName("Pendiente");
		
		List<MaterialMovementView> list = MaterialMovementViewService.byStatus(pendiente.getId());
		if(list.isEmpty()) {
			map.put("code", "204");
		} else {
			map.put("code", "200");
			map.put("list", list);
		}
		try {
			value = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			value = String.format("{\"code\":\"500\",\"error\":\"%s\"}", e.getMessage());
		}
		
		return value;
	}

}
