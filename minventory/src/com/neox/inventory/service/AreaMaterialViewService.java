package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.area.AreaMaterialView;
import com.neox.inventory.util.HibernateUtil;

public class AreaMaterialViewService {
	
	public static AreaMaterialView getByIds(Integer idArea, Integer idMaterial) {
		AreaMaterialView value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<AreaMaterialView> q = session.createQuery("from AreaMaterialView where idArea = :idArea and idMaterial = :idMaterial");
			q.setParameter("idArea", idArea);
			q.setParameter("idMaterial", idMaterial);
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
	
	public static List<AreaMaterialView> getAreas() {
		List<AreaMaterialView> list = new ArrayList<AreaMaterialView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<AreaMaterialView> q = session.createQuery("from AreaMaterialView group by idArea");
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
	
	public static List<AreaMaterialView> getListByArea(Integer idArea) {
		List<AreaMaterialView> list = new ArrayList<AreaMaterialView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<AreaMaterialView> q = session.createQuery("from AreaMaterialView where idArea = :idArea order by area asc");
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
	
	public static List<AreaMaterialView> getList() {
		List<AreaMaterialView> list = new ArrayList<AreaMaterialView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<AreaMaterialView> q = session.createQuery("from AreaMaterialView order by material asc");
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
