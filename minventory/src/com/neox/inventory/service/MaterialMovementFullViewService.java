package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.MaterialMovementFullView;
import com.neox.inventory.util.HibernateUtil;

public class MaterialMovementFullViewService {
	
	public static List<MaterialMovementFullView> getMaterialListByIdHeader(Integer idHeader, Integer ...idStatus) {
		List<MaterialMovementFullView> list = new ArrayList<MaterialMovementFullView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<MaterialMovementFullView> q = session.createQuery("from MaterialMovementFullView where idHeader = :idHeader and idStatus in (:idStatus)");
			q.setParameter("idHeader", idHeader);
//			q.setParameter("idStatus", idStatus);
			q.setParameterList("idStatus", idStatus);
			
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
	
	public static List<MaterialMovementFullView> getMaterialListByIdHeader(Integer idHeader) {
		List<MaterialMovementFullView> list = new ArrayList<MaterialMovementFullView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<MaterialMovementFullView> q = session.createQuery("from MaterialMovementFullView where idHeader = :idHeader");
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
	
}
