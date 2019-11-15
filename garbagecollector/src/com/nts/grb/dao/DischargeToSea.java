package com.nts.grb.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DischargeToSea {
	boolean  valueUpdate=false;
	public static boolean storeInfo(Session session,com.nts.grb.model.DischargeToSea dischrgetosea) {
		Transaction tran = session.beginTransaction();
		session.save(dischrgetosea);
		tran.commit();
		/*Configuration cf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(com.nts.grb.model.DischargeToSea.class);
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(cf.getProperties()).buildServiceRegistry();
		SessionFactory sf = cf.buildSessionFactory(sr);
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();
		session.save(dischargeToSea);
		tran.commit();*/
		return true;
	}

	
}
