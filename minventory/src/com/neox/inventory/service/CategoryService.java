package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.Category;
import com.neox.inventory.util.HibernateUtil;

public class CategoryService {
	
	public static Category getByName(String category) {
		Category value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Category> q = session.createQuery("from Category where upper(category) = upper(:category)");
			q.setParameter("category", category);
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
	
	public static boolean hasChilds(Category category) {
		List<Category> childs = getByIdCategory(category.getId());
		return childs.size() > 0;
	}
	
	public static List<Category> getByIdCategory(Integer id) {
		List<Category> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Category> q = session.createQuery("from Category where idCategory = :id");
			q.setParameter("id", id);
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
	
	public static Category getById(Integer id) {
		Category value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Category> q = session.createQuery("from Category where id = :id");
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
	
	public static List<Category> getAll() {
		List<Category> list = new ArrayList<Category>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Category order by category asc");
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
	
	public static List<Category> getList() {
		List<Category> list = new ArrayList<Category>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Category where active = 1 order by category asc");
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
