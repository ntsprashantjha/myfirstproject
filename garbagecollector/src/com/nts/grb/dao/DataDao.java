package com.nts.grb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.nts.grb.model.Testing;

public class DataDao {
	public boolean storeInfo(com.nts.grb.model.DischargeToSea dischargeToSea) {
		Configuration cf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(com.nts.grb.model.DischargeToSea.class);
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(cf.getProperties()).buildServiceRegistry();
		SessionFactory sf = cf.buildSessionFactory(sr);
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();
		session.save(dischargeToSea);
		tran.commit();
		return true;
	}

	
}
