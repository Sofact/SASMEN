package controller;

import org.hibernate.Session;

import model.SgdDocumento;

public class DeleteData {

	
	Session sesion = HibernateUtil.getSessionFactory().openSession();
	
	public void deleteDocumento(int docuId) {
		 
		 SgdDocumento documento = sesion.find(SgdDocumento.class, docuId);

		 sesion.getTransaction().begin();
		 sesion.remove(documento);
		 sesion.getTransaction().commit();
		 
	}
}	 