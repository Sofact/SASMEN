package controller;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import model.SgdConsecutivo;
import model.SgdConvenio;
import model.SgdConvenioMarco;
import model.SgdConvenioTipoDocumento;
import model.SgdDocumento;
import model.SgdEntidad;
import model.SgdParametroDominio;
import model.SgdTipoDocumento;
import model.SgdUsuario;
import model.VConvenioTipoDoc;
import model.ViewConvenioEntidad;
import model.ViewConvenioTipoDocumento;
import model.ViewResumen;
import model.ViewResumenDocumento;


public class GetData {
	
	Session sesion = HibernateUtil.getSessionFactory().openSession();
	
	
	public List<SgdConvenioMarco> getConvenioMarco() {
		
		//Session sesion = HibernateUtil.getSessionFactory().openSession();
		Query query = sesion.createQuery("SELECT c FROM SgdConvenioMarco c ");
					
		List<SgdConvenioMarco> convenioMarco = query.getResultList();
		
		
	
		System.out.println("1. ConvenioMarco:::"+convenioMarco.get(0).getComaNombre());
		return convenioMarco;
	}
	
	public List<SgdConvenio> getConvenio() {
		
		//Session sesion = HibernateUtil.getSessionFactory().openSession();
		Query query = sesion.createQuery("SELECT c FROM SgdConvenio c order by 3 desc, 2");
					
		List<SgdConvenio> convenio = query.getResultList();
		
		
	
	//	System.out.println("1. Convenio:::"+convenio.get(1).getConvNumero()+" de "+ convenio.get(1).getConvYear());
		return convenio;
	}
public List<SgdConvenio> getConvenioApodo(String apodo) {
		
		//Session sesion = HibernateUtil.getSessionFactory().openSession();
		Query query = sesion.createQuery("SELECT p FROM SgdConvenio p where p.convApodo =:arg");
		query.setParameter("arg", apodo);			
		
		List<SgdConvenio> convenio = query.getResultList();
		
		
	
	//	System.out.println("1. Convenio:::"+convenio.get(1).getConvNumero()+" de "+ convenio.get(1).getConvYear());
		return convenio;
	}
	public List<SgdConvenio> getConvenioNombre() {
		
		//Session sesion = HibernateUtil.getSessionFactory().openSession();
		Query query = sesion.createQuery("SELECT c FROM SgdConvenio c where c.convId =:arg");
					
		List<SgdConvenio> convenio = query.getResultList();
		
		
	
	//	System.out.println("1. Convenio:::"+convenio.get(1).getConvNumero()+" de "+ convenio.get(1).getConvYear());
		return convenio;
	}
	
	public List<SgdConvenio> getLastConvenio() {
		
		//Session sesion = HibernateUtil.getSessionFactory().openSession();
		Query query = sesion.createQuery("SELECT c FROM SgdConvenio c order by 1 desc");
					
		List<SgdConvenio> convenio = query.getResultList();
		
		
	
	//	System.out.println("1. Convenio:::"+convenio.get(1).getConvNumero()+" de "+ convenio.get(1).getConvYear());
		return convenio;
	}



	public List<SgdEntidad> getEntidad ()
	{
		
		
		
				Query query = sesion.createQuery("SELECT c FROM SgdEntidad c ORDER BY 2");
							
				List<SgdEntidad> entidad = query.getResultList();
		
		return entidad;
		
	}

	public List<SgdTipoDocumento> getTipoDocumento() {

		Query query = sesion.createQuery("SELECT c FROM SgdTipoDocumento c ORDER BY 2");
		
		List<SgdTipoDocumento> tipoDocumento = query.getResultList();

			return tipoDocumento;
	}
	
/*	public List<SgdTipoDocumento> getTipoDocumentoByConvenio( int idConvenio){
		
		Query query = sesion.createQuery("SELECT p FROM SgdConvenioTipoDocumento c INNER JOIN  c.tipoDocumento p where c.convId =:arg ORDER BY 2");
		query.setParameter("arg", idConvenio);
		
		List<SgdTipoDocumento> tipoDocumento = query.getResultList();

			return tipoDocumento;
	}
*/
	public List<SgdConvenioTipoDocumento> getConvenioTipoDocumento(int idConvenio, int tidoId) {
		
		Query query = sesion.createQuery("SELECT p FROM SgdConvenioTipoDocumento p   where p.convId =:arg and p.tidoId =:arg1 ORDER BY 2");
		query.setParameter("arg", idConvenio);
		query.setParameter("arg1", tidoId);
		
		System.out.println("1:Aqui get:::::");
		
		List<SgdConvenioTipoDocumento> convenioTipoDocumento = query.getResultList();
		
		System.out.println("1:Aqui get despues del quey"+tidoId);

			return convenioTipoDocumento;
	}
	
	public  SgdConvenio getPathLast () {
		
		Query query = sesion.createQuery("SELECT c FROM SgdConvenio c ORDER BY 1 desc");
		
		
		 SgdConvenio convenio = (SgdConvenio) query.setMaxResults(0).getSingleResult();

			return convenio;
	
		
	}

	public List<SgdConvenioTipoDocumento> getConvenioTipoDocumento(int val) {

		Query query = sesion.createQuery("SELECT p FROM SgdConvenioTipoDocumento p   where p.convId =:arg  ORDER BY 2");
		query.setParameter("arg", val);
		
		
		System.out.println("1:Aqui get:::::");
		
		List<SgdConvenioTipoDocumento> convenioTipoDocumento = query.getResultList();
		
		System.out.println("1:Aqui get despues del quey");

			return convenioTipoDocumento;
	}

	public List<SgdConvenio> getConvenio(int convId) {

		Query query = sesion.createQuery("SELECT c FROM SgdConvenio c  where c.convId =:arg");
		query.setParameter("arg", convId);
		
		
		List<SgdConvenio> convenio = query.getResultList();
		
		
	
		System.out.println("1. Convenio:::"+convenio.get(0).getConvNumero()+" de "+ convenio.get(0).getConvYear());
		return convenio;
		
	}
	public List<SgdDocumento> getDocumento() {

		Query query = sesion.createQuery("SELECT c FROM SgdDocumento c  order by 1 desc");
				
		
		List<SgdDocumento> documento = query.getResultList();
		
		
	
		System.out.println("1. Convenio:::"+documento.get(0).getEnlaId());
		return documento;
		
	}

	public List<SgdDocumento> getDocumento(int val) {
		Query query = sesion.createQuery("SELECT c FROM SgdDocumento c  where c.convId =:arg order by consecutivo, enlaId, tidoId");
		query.setParameter("arg", val);
		
		
		List<SgdDocumento> documento = query.getResultList();
		
		
	
		//System.out.println("1. Convenio:::"+convenio.get(0).getConvNumero()+" de "+ convenio.get(0).getConvYear());
		return documento;
	}
	
	public List<SgdDocumento> getDocumentoLast() {
		Query query = sesion.createQuery("SELECT c FROM SgdDocumento c   order by 1 desc");
		
		
		
		List<SgdDocumento> documento = query.getResultList();
		
		
	
		//System.out.println("1. Convenio:::"+convenio.get(0).getConvNumero()+" de "+ convenio.get(0).getConvYear());
		return documento;
	}

	public List<SgdDocumento> getDocumentoById(int val) {
		Query query = sesion.createQuery("SELECT c FROM SgdDocumento c  where c.docuId =:arg order by tidoId");
		query.setParameter("arg", val);
		
		
		List<SgdDocumento> documento = query.getResultList();
		
		
	
		//System.out.println("1. Convenio:::"+convenio.get(0).getConvNumero()+" de "+ convenio.get(0).getConvYear());
		return documento;
	}

	public List<SgdDocumento> getDocumento(int convId, int tidoId) {
		
		Query query = sesion.createQuery("SELECT c FROM SgdDocumento c  where c.convId =:arg and  c.tidoId =:arg1");
		query.setParameter("arg", convId);
		query.setParameter("arg1", tidoId);
		
		
		List<SgdDocumento> documento = query.getResultList();
		
		return documento;
	}
	
	public List<SgdDocumento> getDocumentoValor(int convId) {
		
		Query query = sesion.createQuery("SELECT c FROM SgdDocumento c  where c.convId =:arg and  c.tidoId in (17, 34, 36)");
		query.setParameter("arg", convId);
		
		
		
		List<SgdDocumento> documento = query.getResultList();
		
		return documento;
	}
	
		public List<SgdDocumento> getDocumentoValorReduccion(int convId) {
		
		Query query = sesion.createQuery("SELECT c FROM SgdDocumento c  where c.convId =:arg and  c.tidoId in (35)");
		query.setParameter("arg", convId);
		
		
		
		List<SgdDocumento> documento = query.getResultList();
		
		return documento;
	}
	
		public List<SgdDocumento> getDocumentoFechaFin(int convId) {
			
			Query query = sesion.createQuery("SELECT c FROM SgdDocumento c  where c.convId =:arg and  c.tidoId in (34, 19, 36) order by docuFechaFin desc");
			query.setParameter("arg", convId);
			
			
			
			List<SgdDocumento> documento = query.getResultList();
			
			return documento;
		}
		
		
	
	public List<SgdConsecutivo> getConsecutivo(int tidoId) {
		
		Query query = sesion.createQuery("SELECT c FROM SgdConsecutivo c  where c.tidoId =:arg ");
		query.setParameter("arg", tidoId);
		
		
		
		List<SgdConsecutivo> consecutivo = query.getResultList();
		
		return consecutivo;
	}


	public List<ViewConvenioTipoDocumento> ViewConvenioTipoDocumento(int convId) {
		Query query = sesion.createQuery("SELECT c FROM ViewConvenioTipoDocumento c where c.convId =:arg order by 2  ");
		query.setParameter("arg", convId);
		
		List<ViewConvenioTipoDocumento> documento = query.getResultList();
		
		System.out.println("aaaaa::"+documento.get(0).toString());
		return documento;
	}

	public List<SgdDocumento> getDocumentoFin(int convId) {
		
		Query query = sesion.createQuery("SELECT c FROM SgdDocumento c  where c.convId =:arg and  c.tidoId =19");
		query.setParameter("arg", convId);
		
		
		
		List<SgdDocumento> documento = query.getResultList();
		
		return documento;
	}
	
	public List<ViewResumen> getViewResumen(int convId) {
		Query query = sesion.createQuery("SELECT c FROM ViewResumen c  where c.convId =:arg order by consecutivo ");
		query.setParameter("arg", convId);
		
		List<ViewResumen> documento = query.getResultList();
		
	//	System.out.println("aaaaa::"+documento.get(0).toString());
		return documento;
	}

	public List<SgdDocumento> getDocumento(int convId, int tidoId, int year) {

		Query query = sesion.createQuery("SELECT c FROM SgdDocumento c  where c.convId =:arg and  c.tidoId =:arg1 and c.enlaId =:arg2");
		query.setParameter("arg", convId);
		query.setParameter("arg1", tidoId);
		query.setParameter("arg2", year);
		
		
		List<SgdDocumento> documento = query.getResultList();
		
		return documento;
	}

	public List<SgdUsuario> getUsuario(String usuario) {
		Query query = sesion.createQuery("SELECT c FROM SgdUsuario c  where c.usuUser =:arg ");
		query.setParameter("arg", usuario);
		
		
		
		List<SgdUsuario> documento = query.getResultList();
		
		return documento;
	}
	
	public List<SgdUsuario> getUsuario() {
		Query query = sesion.createQuery("SELECT c FROM SgdUsuario c  where c.usuUser !='admin' ");
		
		
		
		
		List<SgdUsuario> documento = query.getResultList();
		
		return documento;
	}

	public List<SgdConvenio> getConvenioUsuario(int userId) {
		Query query = sesion.createQuery("SELECT c FROM SgdConvenio c  where c.usuId =:arg");
		query.setParameter("arg", userId);
		
		
		List<SgdConvenio> convenio = query.getResultList();
		
		
	
		System.out.println("1. Convenio:::"+convenio.get(0).getConvNumero()+" de "+ convenio.get(0).getConvYear());
		return convenio;
	}

	public List<ViewConvenioEntidad> getConvenioEntidad(int convId) {
		Query query = sesion.createQuery("SELECT c FROM ViewConvenioEntidad c  where c.convId =:arg");
		query.setParameter("arg", convId);
		
		
		List<ViewConvenioEntidad> convenio = query.getResultList();
		
		
	
		//System.out.println("1. Convenio:::"+convenio.get(0).getConvNumero()+" de "+ convenio.get(0).getConvYear());
		return convenio;
	}

	public List<SgdParametroDominio> getParametroDominio(String padoNombre) {
		
		Query query = sesion.createQuery("SELECT c FROM SgdParametroDominio c  where c.padoNombre =:arg");
		query.setParameter("arg", padoNombre);
		
		
		List<SgdParametroDominio> parametro = query.getResultList();
		
		
	
		//System.out.println("1. Convenio:::"+convenio.get(0).getConvNumero()+" de "+ convenio.get(0).getConvYear());
		return parametro;
	}

	public List<SgdConvenioTipoDocumento> getConvenioTipoDocumento() {
		
		Query query = sesion.createQuery("SELECT p FROM SgdConvenioTipoDocumento p  ORDER BY 2");
		
		List<SgdConvenioTipoDocumento> ctd = query.getResultList();
		
		return ctd;
	}

	public List<ViewResumenDocumento> getViewResumenDocumento() {

		Query query = sesion.createQuery("SELECT p FROM ViewResumenDocumento p  ");
		
		List<ViewResumenDocumento> ctd = query.getResultList();
		
		return ctd;
	}

	public List<SgdDocumento> getDocumentoAperiodico(int val) {
		Query query = sesion.createQuery("SELECT c FROM ViewResumenDocumento c  where c.convId =:arg and c.cotdPeriodicidad ='aperiodica          '");
		query.setParameter("arg", val);
		
		
		List<SgdDocumento> documento = query.getResultList();
		
		
	
		//System.out.println("1. Convenio:::"+convenio.get(0).getConvNumero()+" de "+ convenio.get(0).getConvYear());
		return documento;
	}

	


	
}
