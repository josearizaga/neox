package com.neox.inventory.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.Status;
import com.neox.inventory.util.HibernateUtil;

public class StatusService {
	
	public static Status byApproval() {
		Status status = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			//Query<Status> q = session.createQuery("from Status where upper(status) = upper(:status) and active = true ");
			Query<Status> q = session.createQuery("from Status where approval = true and active = true "); 
			status = q.uniqueResult();
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}
	
	public static Status byId(Integer id) {
		Status status = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			//Query<Status> q = session.createQuery("from Status where upper(status) = upper(:status) and active = true ");
			Query<Status> q = session.createQuery("from Status where id = :id and active = true "); 
			q.setParameter("id", id);
			status = q.uniqueResult();
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}
	
	public static Status byName(String name) {
		Status status = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			//Query<Status> q = session.createQuery("from Status where upper(status) = upper(:status) and active = true ");
			Query<Status> q = session.createQuery("from Status where upper(status)  like '%'||upper(:status)||'%' and active = true "); 
			q.setParameter("status", name);
			status = q.uniqueResult();
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}
}
