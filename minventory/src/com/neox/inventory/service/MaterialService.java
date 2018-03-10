package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.Material;
import com.neox.inventory.util.HibernateUtil;

public class MaterialService {
	
	public static List<Material> getNotInArea(Integer idArea) {
		List<Material> list = new ArrayList<Material>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<Material> q = session.createQuery("from Material m where m.id not in (select idMaterial from AreaMaterialView where idArea = :idArea)");
			q.setParameter("idArea", idArea);
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
	
	public static List<Material> getInArea(Integer idArea) {
		List<Material> list = new ArrayList<Material>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<Material> q = session.createQuery("from Material m where m.id in (select idMaterial from AreaMaterialView where idArea = :idArea)");
			q.setParameter("idArea", idArea);
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
	
	public static Material getById(Integer idMaterial) {
		Material material = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<Material> q = session.createQuery("from Material where id = :material");
			q.setParameter("material", idMaterial);
			material = q.uniqueResult();
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return material;
	}
	
	public static Material getByName(String name) {
		Material material = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<Material> q = session.createQuery("from Material where material = :material and active = true ");
			q.setParameter("material", name);
			if(q.list().size() > 0) {
				material = q.getResultList().get(0);
			}
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return material;
	}
	
	public static List<Material> getAll() {
		List<Material> list = new ArrayList<Material>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<Material> q = session.createQuery("from Material order by material asc");
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
	
	public static List<Material> getList() {
		List<Material> list = new ArrayList<Material>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<Material> q = session.createQuery("from Material where active = true order by material asc");
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
