package com.neox.inventory.web.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "areaUserView")
public class AreaUserViewConverter implements Converter {
	
	private static Map<Object, String> entities = new WeakHashMap<Object, String>();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
		for (Entry<Object, String> entry : entities.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
//		Object ret = null;
//		if (arg1 instanceof PickList) {
//			Object dualList = ((PickList) arg1).getValue();
//			DualListModel dl = (DualListModel) dualList;
//			for (Object o : dl.getSource()) {
//				String id = "" + ((UserView) o).getId();
//				if (arg2.equals(id)) {
//				ret = o;
//				break;
//				}
//			}
//			if (ret == null)
//				for (Object o : dl.getTarget()) {
//					String id = "" + ((UserView) o).getId();
//					if (arg2.equals(id)) {
//						ret = o;
//						break;
//					}
//				}
//		}
//		return ret;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object entity) {
		synchronized (entities) {
            if (!entities.containsKey(entity)) {
                String uuid = UUID.randomUUID().toString();
                entities.put(entity, uuid);
                return uuid;
            } else {
                return entities.get(entity);
            }
        }
		
//		String str = "--";
//		if (arg2 instanceof UserView) {
//			str = "" + ((UserView) arg2).getId();
//		}
//		return str;
	}

}
