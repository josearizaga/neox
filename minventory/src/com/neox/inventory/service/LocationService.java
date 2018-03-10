package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.area.Location;
import com.neox.inventory.util.HibernateUtil;

public class LocationService {
	
	public static List<Location> byIdMaterial(Integer idMaterial) {
		List<Location> list = new ArrayList<Location>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Location> q = session.createQuery("from Location l where l.id in (select i.idLocation from Inventory i where i.idMaterial = :idMaterial)");
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
	
	public static Location getById(Integer id) {
		Location value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Location> q = session.createQuery("from Location where id = :id");
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
	
	public static List<Location> getByIdLocation(Integer idLocation) {
		List<Location> list = new ArrayList<Location>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Location> q = session.createQuery("from Location where idLocation = :idLocation");
			q.setParameter("idLocation", idLocation);
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
	
	public static boolean hasChilds(Location location) {
		List<Location> childs = getByIdLocation(location.getId());
		return childs.size() > 0;
	}
	
	public static List<Location> getAll() {
		List<Location> list = new ArrayList<Location>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Location> q = session.createQuery("from Location order by location asc");
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
	
	public static List<Location> getList() {
		List<Location> list = new ArrayList<Location>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Location where active = 1 order by location asc");
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
}
