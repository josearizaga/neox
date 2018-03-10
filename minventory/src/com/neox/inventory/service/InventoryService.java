package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.InventoryHistory;
import com.neox.inventory.model.material.Material;
import com.neox.inventory.model.material.MaterialPrice;
import com.neox.inventory.model.material.MaterialProviderPrice;
import com.neox.inventory.model.material.Outcome;
import com.neox.inventory.util.HibernateUtil;

public class InventoryService {
	
	public static Inventory byId(Integer idInventory) {
		Inventory value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Inventory> q = session.createQuery("from Inventory where id = :idInventory");
			q.setParameter("idInventory", idInventory);
			
			value = q.uniqueResult();
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return value;
	}
	
	public static Inventory byIds(Integer idMaterial, Integer idLocation) {
		Inventory value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Inventory> q = session.createQuery("from Inventory where idMaterial = :idMaterial and idLocation = :idLocation");
			q.setParameter("idMaterial", idMaterial);
			q.setParameter("idLocation", idLocation);
			value = q.uniqueResult();
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return value;
	}
	
	public static boolean save(Inventory inventory, MaterialProviderPrice materialProvider, InventoryHistory history) {
		boolean value = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(inventory);
			if(inventory.getId() <= 0) {
				return false;
			}
			if(materialProvider != null) {
				session.saveOrUpdate(materialProvider);
			}
			MaterialPrice material = MaterialPriceService.byIdMaterial(inventory.getIdMaterial());
			if(material == null) {
				material = new MaterialPrice(inventory.getIdMaterial(),inventory.getPrice());
				material.setCreationUser(inventory.getModificationUser());
			}
			material.setPrice(inventory.getPrice());
			material.setModificationUser(inventory.getModificationUser());
			session.saveOrUpdate(material);
			if(history != null) {
				history.setIdInventory(inventory.getId());
				session.save(history);
				if(history.getId() <= 0) {
					return false;
				}
			}
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			value = false;
		} finally {
			session.close();
		}
		return value;
	}
	
	public static List<Inventory> getAll() {
		List<Inventory> list = new ArrayList<Inventory>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<Inventory> q = session.createQuery("from Inventory");
			list = q.list();
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	
	public static List<Inventory> getList() {
		return getAll();
	}
	
}
