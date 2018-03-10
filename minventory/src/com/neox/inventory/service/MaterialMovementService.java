package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.MaterialMovement;
import com.neox.inventory.util.HibernateUtil;

public class MaterialMovementService {
	
	
	public static boolean updateMaterialMovement(MaterialMovement material) {
		boolean value = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.update(material);
			
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
	
	public static MaterialMovement byId(Integer id) {
		MaterialMovement material = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			//material = session.load(MaterialMovement.class, id);
			
			Query<MaterialMovement> q = session.createQuery("from MaterialMovement where id = :id");
			q.setParameter("id", id);
			
			material = q.getSingleResult();
			
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
	
	public static List<MaterialMovement> getListByIdHeader(Integer idHeader) {
		List<MaterialMovement> list = new ArrayList<MaterialMovement>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<MaterialMovement> q = session.createQuery("from MaterialMovement where idHeader = :idHeader");
			q.setParameter("idHeader", idHeader);
			
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
	
	public static boolean insertMaterialMovement(MaterialMovement material) {
		boolean value = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.save(material);
			
			value = (material.getId() != 0);
			
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
	
}
