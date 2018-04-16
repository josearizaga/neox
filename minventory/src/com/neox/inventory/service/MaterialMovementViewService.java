package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.MaterialMovementView;
import com.neox.inventory.util.HibernateUtil;

public class MaterialMovementViewService {
	
	public static List<MaterialMovementView> byStatus(Integer ...idStatus) {
		List<MaterialMovementView> list = new ArrayList<MaterialMovementView>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			StringBuffer buff = new StringBuffer("where idStatus in (");
			for(Integer id:idStatus) {
				buff.append(id+",");
			}
			buff.append(0+")");
			Query<MaterialMovementView> q = session.createQuery("from MaterialMovementView " + buff.toString());
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
	
	public static List<MaterialMovementView> getList() {
		List<MaterialMovementView> list = new ArrayList<MaterialMovementView>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<MaterialMovementView> q = session.createQuery("from MaterialMovementView");
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
	
}
