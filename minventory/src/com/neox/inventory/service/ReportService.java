package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.report.InventoryReportView;
import com.neox.inventory.util.HibernateUtil;

public class ReportService {
	
	public static List<InventoryReportView> getInventoryReport() {
		List<InventoryReportView> list = new ArrayList<InventoryReportView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<InventoryReportView>q = session.createQuery("from InventoryReportView");
			
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
