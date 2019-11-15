package testing;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class B {
	public static void main(String arg[]) {
		A al = null;
		Configuration cf = new Configuration().configure().addAnnotatedClass(A.class);
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(cf.getProperties()).buildServiceRegistry();
		SessionFactory sf = cf.buildSessionFactory(sr);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		/*
		 * Random rn = new Random(); for (int i = 1; i <= 50; i++) { A a = new A();
		 * a.setRollno(i); a.setName("name" + i); a.setMarks(rn.nextInt(100));
		 * session.save(a); }
		 */
		/*
		 * Query q = session.createQuery("from A"); List<A> a = q.list(); for(A a1:a) {
		 * System.out.println(a1);
		 * 
		 * }
		 */
		Query q = session.createQuery("update dischargetosea set strikeApproval=:n where id=:i");
		q.setParameter("n", "vinay");
		q.setParameter("i", 2);
		/*
		 * Object[] student = (Object[]) q.uniqueResult(); // in case of getting uniq
		 * result System.out.println(student[0] + "\n" + student[1] + "\n" +
		 * student[2]);
		 */
		int st = q.executeUpdate();
		tx.commit();
		System.out.println(st);
		session.getTransaction().commit();
	}


}
