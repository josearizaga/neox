package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.Outcome;
import com.neox.inventory.model.material.OutcomeView;
import com.neox.inventory.util.HibernateUtil;

public class OutcomeViewService {
	
	public static List<OutcomeView> byIdMovement(Integer idMovement) {
		List<OutcomeView> list = new ArrayList<OutcomeView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
//			Query<Outcome>q = session.createQuery("from Outcome where idMovement = :idMovement");
			Query<OutcomeView>q = session.createQuery("from OutcomeView where idMovement = :idMovement and qty > 0");
			q.setParameter("idMovement", idMovement);
			
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
