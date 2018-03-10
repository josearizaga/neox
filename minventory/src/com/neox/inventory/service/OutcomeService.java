package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.MaterialMovement;
import com.neox.inventory.model.material.Outcome;
import com.neox.inventory.util.HibernateUtil;

public class OutcomeService {
	
	public static Outcome byId(Integer id) {
		Outcome value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Outcome>q = session.createQuery("from Outcome where id = :id");
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
	
	public static Outcome byIds(Integer idMaterialMovement, Integer idInventory) {
		Outcome value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Outcome>q = session.createQuery("from Outcome where idMaterialMovement = :idMaterialMovement and idInventory = :idInventory");
			q.setParameter("idMaterialMovement", idMaterialMovement);
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
	
	public static boolean save(Object ...objects) {
		boolean value = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			/*session.save(outcome);
			session.save(material);*/
			for(Object obj:objects) {
				if(obj != null) {
					session.saveOrUpdate(obj);
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
	
}
