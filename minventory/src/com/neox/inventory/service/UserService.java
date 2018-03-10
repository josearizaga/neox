package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.area.AreaUserView;
import com.neox.inventory.model.user.User;
import com.neox.inventory.model.user.UserInfo;
import com.neox.inventory.model.user.UserView;
import com.neox.inventory.util.HibernateUtil;

public class UserService {
	
	public static User byId(Integer id) {
		User value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<User> q = session.createQuery("from User where id = :id");
			q.setParameter("id", id);
			value = q.uniqueResult();
			
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
	
	public static boolean save(User user, UserInfo userInfo) {
		boolean value = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			session.saveOrUpdate(user);
			userInfo.setIdUser(user.getId());
			session.saveOrUpdate(userInfo);
			value = user.getId() > 0;
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			value = false;
		} finally {
			session.close();
		}
		return value;
	}
	
	public static List<AreaUserView> byArea(Integer area) {
		List<AreaUserView> list = new ArrayList<AreaUserView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from AreaUserView where idArea = :i_area order by name asc");
			q.setParameter("i_area", area);
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
	
	public static List<UserView> getAll() {
		List<UserView> list = new ArrayList<UserView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from UserView order by name asc");
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
	
	public static List<UserView> getList() {
		List<UserView> list = new ArrayList<UserView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from UserView where active = 1 order by name asc");
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
	
	
	
	public static User isUser(String username, String passwd) {
		User u = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<User> q = session.createQuery("from User where username = :s_username and passwd = :s_passwd and active = 1");
			q.setParameter("s_username", username);
			q.setParameter("s_passwd", passwd);
			List<User> l = q.list();
			if(l.size() > 0) {
				u = l.get(0);
			}
			//u = q.uniqueResult();
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return u;
	}
	
}
