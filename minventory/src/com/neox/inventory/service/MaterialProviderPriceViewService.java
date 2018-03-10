package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.MaterialProviderPriceView;
import com.neox.inventory.util.HibernateUtil;

public class MaterialProviderPriceViewService {
	
	public static List<MaterialProviderPriceView> getListByIdMaterial(Integer idMaterial) {
		List<MaterialProviderPriceView> list = new ArrayList<MaterialProviderPriceView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<MaterialProviderPriceView> q = session.createQuery("from MaterialProviderPriceView where idMaterial = :idMaterial order by material asc");
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
	
	public static List<MaterialProviderPriceView> getList() {
		List<MaterialProviderPriceView> list = new ArrayList<MaterialProviderPriceView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<MaterialProviderPriceView> q = session.createQuery("from MaterialProviderPriceView order by material asc");
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
