package controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	 private static SessionFactory sessionFactory;
	 private static Session session;
	 
	    public static synchronized void buildSessionFactory() {
	          if (sessionFactory == null) {
	             Configuration configuration = new Configuration();
	              configuration.configure();
	          //   ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	             sessionFactory = configuration.buildSessionFactory();
	             session = sessionFactory.openSession();
				 session.getTransaction().begin();
	         }
	     }
	
	     public static void openSessionAndBindToThread() {
	         Session session = sessionFactory.openSession();
	         ThreadLocalSessionContext.bind(session);
	     }
	
	
	     public static SessionFactory getSessionFactory() {
	         if (sessionFactory==null)  {
	             buildSessionFactory();
	         }
	         return sessionFactory;
	     }
	     public static Session getSession() {
	         
	    	 System.out.println("Ingreso a guetSesion--"+ session.getSession().toString());
	         return session;
	     }
	
	     public static void closeSessionAndUnbindFromThread() {
	         Session session = ThreadLocalSessionContext.unbind(sessionFactory);
	         if (session!=null) {
	             session.close();
	         }
	     }
	
	     public static void closeSessionFactory() {
	         if ((sessionFactory!=null) && (sessionFactory.isClosed()==false)) {
	             sessionFactory.close();
	         }
	     }
}
