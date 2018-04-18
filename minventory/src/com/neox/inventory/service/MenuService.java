package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.menu.Menu;
import com.neox.inventory.util.HibernateUtil;

public class MenuService {
	
	public static List<Menu> getById(Integer idUser) {
		List<Menu> list = new ArrayList<Menu>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Menu> q = session.createQuery("from Menu where id in (select um.idMenu from UserMenu um where um.idUser = :idUser) or value is null");
			q.setParameter("idUser", idUser);
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
	
	public static List<Menu> getOutcome(Integer idUser) {
		List<Menu> list = new ArrayList<Menu>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Menu> q = session.createQuery("from Menu u where u.outcome is not null and u.id not in (select m.idMenu from UserMenu m where m.idUser = :idUser)");
			q.setParameter("idUser", idUser);
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
	
	public static List<Menu> getOutcomeIn(Integer idUser) {
		List<Menu> list = new ArrayList<Menu>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Menu> q = session.createQuery("from Menu u where u.outcome is not null and u.id in (select m.idMenu from UserMenu m where m.idUser = :idUser)");
			q.setParameter("idUser", idUser);
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
	
	public static List<Menu> getAll() {
		List<Menu> list = new ArrayList<Menu>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Menu> q = session.createQuery("from Menu");
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
