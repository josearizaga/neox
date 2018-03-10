package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.area.Validation;
import com.neox.inventory.util.HibernateUtil;

public class ValidationService {
	
	public static List<Validation> getList() {
		List<Validation> list = new ArrayList<Validation>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Validation> q = session.createQuery("from Validation where active = 1 order by idValidation");
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
	
	public static List<Validation> getAll() {
		List<Validation> list = new ArrayList<Validation>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Validation> q = session.createQuery("from Validation order by idValidation");
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
	
	public static Validation byIdArea(Integer idArea) {
		Validation validation = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Validation> q = session.createQuery("from Validation where idArea = :idArea");
			q.setParameter("idArea", idArea);
			validation = q.uniqueResult();
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return validation;
		
	}
	
}
