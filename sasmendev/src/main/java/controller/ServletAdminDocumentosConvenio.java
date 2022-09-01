package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import model.SgdConvenioTipoDocumento;

/**
 * Servlet implementation class ServletAdminDocumentosConvenio
 */
@WebServlet("/ServletAdminDocumentosConvenio")
public class ServletAdminDocumentosConvenio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdminDocumentosConvenio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(request.getInputStream()));
		SgdConvenioTipoDocumento convenioTipoDoc = new SgdConvenioTipoDocumento();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String convId =  null;
		String tidoId=  null;
		 String cotdFechaReq = null;
		 String participantesReq = null;
		 String cotdValorReq = null;
		 String cotdComentarioReq = null;
		 String cotdEnlaceReq = null;
		 String cotdPeriodicidad = null;
		 String cotdFechaFin = null;
		 Transaction tx = null;
		//HibernateUtil hiberUtil = new HibernateUtil();
		
		System.out.println("Entro al servlet");
		
		String json = "";
        if(br != null){
            json = br.readLine();
            System.out.println("--"+json.toString());
        }
        
        JSONParser parser = new JSONParser();  
       
			JSONObject jsonOb;
			try {
				jsonOb = (JSONObject) parser.parse(json);
				JSONArray jsonarr = (JSONArray) jsonOb.get("detallePVM");
				System.out.println("--"+jsonarr);
				
				
				
				for (int i = 0; i < jsonarr.size(); i++) {
				   
				        JSONObject jsonObject = (JSONObject) jsonarr.get(i);
				      System.out.println("El boject---"+i+"---"+jsonObject);
				      
				      convId =  (String) jsonObject.get("idConvenio");
				      tidoId = (String) jsonObject.get("tipoDocumento");
				      cotdFechaReq = (String) jsonObject.get("periodicidad");
				      
				      participantesReq = (String) jsonObject.get("participantes");
				      cotdValorReq = (String) jsonObject.get("valor");
				      cotdFechaReq = (String) jsonObject.get("fecha");
				      cotdComentarioReq = (String) jsonObject.get("comentarios");
				      cotdEnlaceReq = (String) jsonObject.get("enlace");
				      cotdPeriodicidad = (String) jsonObject.get("periodicidad");
				      cotdFechaFin = (String) jsonObject.get("year");
				       
				       
				       
				      
				      System.out.println("El jsonArray interno---"+convId+"---"+tidoId+"--"+cotdFechaReq);

				      convenioTipoDoc.setConvId(Integer.parseInt(convId)); 
				      convenioTipoDoc.setTidoId(Integer.parseInt(tidoId));
				      convenioTipoDoc.setCotdFechaReq(cotdFechaReq);
				      convenioTipoDoc.setParticipantesReq(participantesReq);
				      convenioTipoDoc.setCotdValorReq(cotdValorReq);
				      convenioTipoDoc.setCotdComentarioReq(cotdComentarioReq);
				      convenioTipoDoc.setCotdEnlaceReq(cotdEnlaceReq);
				      convenioTipoDoc.setCotdPeriodicidad(cotdPeriodicidad);
				      convenioTipoDoc.setCotdFechaFin(Integer.parseInt(cotdFechaFin));
				       
				        
				      
				        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
						
				       
						
						
						
						System.out.println("ConvenioDocumento____"+convenioTipoDoc.getConvId());
				        
				        session.beginTransaction();
				        System.out.println("0: por aqui paso");
						tx = session.getTransaction();
						System.out.println("1: por aqui paso");
						try {
						session.save(convenioTipoDoc);
						
						
						System.out.println("2: por aqui paso");
						tx.commit();
						System.out.println("3: por aqui paso");
						
						System.out.println("4: por aqui paso");
						
						if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
							
						    tx.commit();
						    System.out.println("5: por aqui paso");
						   
						    
						}
						} finally 
						{
							System.out.println("10: por aqui paso");
							//session.close();
							session.close();
						//	 session = HibernateUtil.getSessionFactory().getCurrentSession();
						//	 session.beginTransaction();
						
						}
						
						
				}
				
			} catch (org.json.simple.parser.ParseException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
			}
			
			
			System.out.println("FINALO al servlet----"+convenioTipoDoc.getCotdId());

			
		
			 
			 System.out.println("Convenio Tipo Documento guardado");
			
			StringBuilder sb = new StringBuilder("");
			sb.append("aa:aa");
			
			PrintWriter out = response.getWriter();
			
			 System.out.println("Ants de terminar");
			out.write(sb.toString());
			System.out.println("despues de terminar");


	}

}
