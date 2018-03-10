package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.InventoryView;
import com.neox.inventory.util.HibernateUtil;

public class InventoryViewService {
	
	public static InventoryView byId(Integer id) {
		InventoryView value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<InventoryView>q = session.createQuery("from InventoryView where id = :id");
			q.setParameter("id", id);
			
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
	
	public static List<InventoryView> byIdMovement(Integer idMovement) {
		List<InventoryView> list = new ArrayList<InventoryView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<InventoryView>q = session.createQuery("from InventoryView where id in (select o.idInventory from Outcome o where o.idMovement = :idMovement)");
			q.setParameter("idMovement", idMovement);
			
			list = q.getResultList();
			
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
	
	public static List<InventoryView> getListByIdMaterial(Integer idMaterial) {
		List<InventoryView> list = new ArrayList<InventoryView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<InventoryView> q = session.createQuery("from InventoryView where idMaterial = :idMaterial order by material,location");
			q.setParameter("idMaterial", idMaterial);
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
	
	public static List<InventoryView> getAll() {
		List<InventoryView> list = new ArrayList<InventoryView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<InventoryView> q = session.createQuery("from InventoryView order by material,location");
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
	
	public static List<InventoryView> getList() {
		return getAll();
	}
}
