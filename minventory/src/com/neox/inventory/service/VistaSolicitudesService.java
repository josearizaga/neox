package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.report.VistaSolicitudes;
import com.neox.inventory.util.HibernateUtil;

public class VistaSolicitudesService {
	
	public static List<VistaSolicitudes> getVistaSolicitudesReport() {
		List<VistaSolicitudes> list = new ArrayList<VistaSolicitudes>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<VistaSolicitudes>q = session.createQuery("from VistaSolicitudes");
			
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
