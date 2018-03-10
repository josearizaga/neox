package com.neox.inventory.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.neox.inventory.model.material.LogMovement;
import com.neox.inventory.util.HibernateUtil;

public class LogMovementService {
	
	public static boolean insertLoglMovement(LogMovement movement) {
		boolean value = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.save(movement);
			
			value = (movement.getId() != 0);
			
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
