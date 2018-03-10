package com.neox.inventory.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.neox.inventory.model.material.Inventory;
import com.neox.inventory.model.material.InventoryHistory;
import com.neox.inventory.model.material.Transfer;
import com.neox.inventory.util.HibernateUtil;

public class TransferService {
	
	public static boolean save(Inventory source, Inventory destination, Transfer transfer,InventoryHistory history) {
		boolean value = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(source);
			session.saveOrUpdate(destination);
			transfer.setIdInventoryDestination(destination.getId());
			history.setIdInventory(destination.getId());
			session.saveOrUpdate(transfer);
			session.saveOrUpdate(history);
			
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
	
}
