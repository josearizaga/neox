package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.menu.UserMenu;
import com.neox.inventory.model.user.User;
import com.neox.inventory.util.HibernateUtil;

public class UserMenuService {
	
	public static UserMenu byIds(Integer idUser, Integer idMenu) {
		UserMenu value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<UserMenu> q = session.createQuery("from UserMenu where idUser = :idUser and idMenu = :idMenu");
			q.setParameter("idMenu", idMenu);
			q.setParameter("idUser", idUser);
			value = q.getSingleResult();
			
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
	
	public static List<UserMenu> getList() {
		List<UserMenu> list = new ArrayList<UserMenu>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<UserMenu> q = session.createQuery("from UserMenu where outcome is not null");
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
