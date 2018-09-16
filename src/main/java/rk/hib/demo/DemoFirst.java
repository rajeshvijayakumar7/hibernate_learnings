package rk.hib.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import rk.hib.init.HibernateUtil;
import rk.hib.models.student;

public class DemoFirst {

	private static String names[] = { "karthik", "kumar", "surya", "tarun" };

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		for (String name : names) {

			student student = new student();
			student.setName(name);
			session.save(student);

		}
		session.getTransaction().commit();

		session.close();

		Session session2 = sessionFactory.openSession();
		for (int i = 1; i <= 20; i++) {
			Object o = session2.load(student.class,i);
			student s = (student) o;
//                For getting the Id property hibernate will not go to Cache or Database.
//                It will take from the Proxy.
			System.out.println("StudentId : " + s.getId());
//                As name is a non-id property, hibernate first checks in cache,
//                If the value doesn't found in the cache then it will go to data base and fetch the values

			System.out.println("Student Name : " + s.getName());
		}
	}
}
