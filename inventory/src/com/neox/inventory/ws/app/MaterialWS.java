package com.neox.inventory.ws.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neox.inventory.model.area.AreaMaterialView;
import com.neox.inventory.model.area.Validation;
import com.neox.inventory.model.material.Header;
import com.neox.inventory.model.material.LogMovement;
import com.neox.inventory.model.material.MaterialMovement;
import com.neox.inventory.model.material.MaterialPrice;
import com.neox.inventory.model.material.Movement;
import com.neox.inventory.model.material.Status;
import com.neox.inventory.model.material.UOM;
import com.neox.inventory.service.AreaService;
import com.neox.inventory.service.HeaderService;
import com.neox.inventory.service.LogMovementService;
import com.neox.inventory.service.MaterialMovementService;
import com.neox.inventory.service.MaterialPriceService;
import com.neox.inventory.service.MaterialService;
import com.neox.inventory.service.MovementService;
import com.neox.inventory.service.StatusService;
import com.neox.inventory.service.UOMService;
import com.neox.inventory.service.ValidationService;
import com.neox.inventory.ws.bean.AreaBean;
import com.neox.inventory.ws.bean.MaterialBean;
import com.neox.inventory.ws.bean.Token;
import com.neox.inventory.ws.helper.MaterialHelper;
import com.neox.inventory.ws.util.Auth;
import com.neox.inventory.ws.util.GoogleService;
import com.neox.inventory.ws.util.MapperUtil;
import com.neox.inventory.ws.util.Message;
import com.neox.inventory.ws.util.Properties;

@Path("material")
public class MaterialWS extends Auth {
	
	@Context
    private ServletContext context;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addMaterial.json")
	public String addMaterial(MaterialHelper input) {
		String value = null;
		ObjectMapper mapper = MapperUtil.getMapper();
		Map<String,Object> map = new HashMap<>();
		Properties.loadFile(context.getRealPath("/WEB-INF/config.properties"));
		Header header = new Header();
		header.setIdUser(input.getIdUser());
		boolean send = true;
		Integer idArea = input.getIdArea();
		Validation valid = ValidationService.byIdArea(idArea);
		Status approved = StatusService.byName("Aprobado");
		Status pending = StatusService.byName("Pendiente");
		int num = 0;
		if(HeaderService.save(header)) {
			map.put("code", "200");
			List<MaterialBean> materials = input.getMaterials();
			int position = 1;
			for(MaterialBean material:materials) {
				Movement movement = MovementService.byName(material.getType());
				UOM uom = UOMService.getByName(material.getUom());
				if(movement == null || uom == null) {
					map.put("code","510");
					map.put("error","Error al obtener los datos necesarios [Movement: " + movement + ", UOM: "+uom+"]");
					send = false;
					break;
				}
				MaterialMovement mm = new MaterialMovement();
				mm.setIdHeader(header.getId());
				mm.setPosition(position);
				mm.setIdMaterial(material.getIdMaterial());
				mm.setIdMovement(movement.getId());
				MaterialPrice price = MaterialPriceService.byIdMaterial(material.getIdMaterial());
				System.out.println("valid: " + valid + " price: " + price);
				if(valid == null || price == null || (price.getPrice() < valid.getTop())) {
					mm.setIdStatus(approved.getId());
				} else {
					mm.setIdStatus(pending.getId());
					num++;
				}
				mm.setQty(material.getQty());
				mm.setIdUOM(uom.getId());
				mm.setWorkOrder(input.getWorkOrder());
				if(!MaterialMovementService.insertMaterialMovement(mm)) {
					map.put("code","520");
					map.put("error","Error al agregar un movimiento con posición [" + position + "]");
					send = false;
					break;
				}
				LogMovement log = new LogMovement(mm);
				log.setUser(getUsername());
				LogMovementService.insertLoglMovement(log);
				position++;					
			}
			if(send) {
				try {
					Message msg = new Message();
					
					msg.setTopic(Properties.getProperty("config.topic.name"));
					msg.setTitle("Petición de material");
					if(num == 0) {
						msg.setMessage(String.format("Se hizo una petición por %d material(es), orden: %s",materials.size(),input.getWorkOrder()));
					} else {
						msg.setMessage(String.format("Se hizo una petición por materiales, %d por aprobar orden: %s",num,input.getWorkOrder()));
					}
					
					Map<String,Object> m = new HashMap<String,Object>();
					m.put("message", msg);
					
					String content = mapper.writeValueAsString(m);
					System.out.println("Message: " + content);
					String result = GoogleService.sendMessage(context.getRealPath("/WEB-INF/"), content);
					
					System.out.println("RESULT: " + result);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} else {
			map.put("code", "204");
		}
			
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
	@Path("listByArea.json")
	public String listByArea(AreaBean data) {
		String value = null;
		Map<String,Object> map = new HashMap<>();
		List<AreaMaterialView> list = AreaService.getAreaMaterialList(data.getIdArea(),data.getIdCategory());
		if(list.isEmpty()) {
			map.put("code", "204");
		} else {
			map.put("code", "200");
			map.put("areaList", list);
		}
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = MapperUtil.getMapper();
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
	@Path("categoryByArea.json")
	public String categoryByArea(Token data) {
		String value = null;
		Map<String,Object> map = new HashMap<>();
		List<AreaMaterialView> list = AreaService.getCategoryList(new Integer(data.getToken()));
		if(list.isEmpty()) {
			map.put("code", "204");
		} else {
			map.put("code", "200");
			map.put("categoryList", list);
		}
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = MapperUtil.getMapper();
		try {
			value = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			value = String.format("{\"code\":\"500\",\"error\":\"%s\"}", e.getMessage());
		}
		return value;
	}
}