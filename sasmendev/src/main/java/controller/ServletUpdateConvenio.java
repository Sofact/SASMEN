package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

import model.SgdConvenio;
import model.SgdConvenioEntidad;

/**
 * Servlet implementation class ServletUpdateConvenio
 */
@WebServlet("/ServletUpdateConvenio")
public class ServletUpdateConvenio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUpdateConvenio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		System.out.println("Ingreso a Admin convenio:::::"+ response);
		GetData gd = new GetData();
		BufferedReader br = new BufferedReader (new InputStreamReader(request.getInputStream()));
		
		
		SgdConvenioEntidad convenioEntidad = new SgdConvenioEntidad();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fecha = null;
		String nombre=null;
		 String path = null;
		 String year = null;
		 LocalDate fechajs = null;
		 LocalDate fechaFinJs = null;
		 String fechaFin = null;
		 String entidades = null;
		 int idEntidad= 0;

		
		System.out.println("Entro al servlet");
		
		String json = "";
        if(br != null){
            json = br.readLine();
            System.out.println("--"+json.toString());
        }
        
        JSONParser parser = new JSONParser();  
       
			JSONObject jsonOb;
			JSONArray jsonEntidades= null;
			try {
				jsonOb = (JSONObject) parser.parse(json);
				JSONArray jsonarr = (JSONArray) jsonOb.get("detallePVM");
				System.out.println("--"+jsonarr);
				
				
				
				for (int i = 0; i < jsonarr.size(); i++) {
				   
				        JSONObject jsonObject = (JSONObject) jsonarr.get(i);
				      System.out.println("El boject---"+i+"---"+jsonObject);
				      
				       fecha =  (String) jsonObject.get("fechaInicio");
				       path = (String) jsonObject.get("estado");
				       nombre = (String) jsonObject.get("numeroConvenio");
				       year = (String) jsonObject.get("year");
				       fechaFin =  (String) jsonObject.get("fechaFin");
				       jsonEntidades = (JSONArray) jsonObject.get("entidades");
				       String convId = (String) jsonObject.get("convId");
				       
				       fechajs = LocalDate.parse(fecha, formatter);
						fechaFinJs =  LocalDate.parse(fechaFin, formatter);
				      
				      System.out.println("El jsonArray interno---"+jsonEntidades+"---"+path+"--"+nombre);
				      	
				      Session sesion = HibernateUtil.getSessionFactory().openSession();
						sesion.beginTransaction();
						Transaction tx = sesion.getTransaction();
						SgdConvenio convenio = sesion.find(SgdConvenio.class, Integer.parseInt(convId));
						
						
						System.out.println("El convenio cargado es::::"+convenio.getConvNumero());
				        convenio.setConvNumero(Integer.parseInt(nombre));
				        convenio.setConvYear(Integer.parseInt(year));
				        convenio.setConvEstado("activo");
				        convenio.setConvFechaInicio(fechajs);
				        convenio.setConvFechaFin(fechaFinJs);
				        convenio.setConvPath("./recursos/"+nombre+year+"/");
				       
				        tx.commit();
				        convenio.setConvFechaInicio(LocalDate.parse(fecha, formatter));
						convenio.setConvFechaFin(LocalDate.parse(fechaFin, formatter));
				   
				       
						
						
						
					
						if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
							
						    tx.commit();
						}
						System.out.println("ConvenioMArco222::::____"+convenio.getConvNumero());
				}
				
			} catch (org.json.simple.parser.ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/*
			for (int i = 0; i < jsonEntidades.size(); i++) {
				
				 JSONObject jsonObjectEntidad = (JSONObject) jsonEntidades.get(i);
			      System.out.println("El boject---"+i+"---"+jsonObjectEntidad);
			      
			      idEntidad =  Integer.parseInt( (String) jsonObjectEntidad.get("idEntidad"));
			      
			      convenioEntidad.setConvId(convenio.getConvId());
			      convenioEntidad.setEntId(idEntidad);
			      convenioEntidad.setCoenEstado("ACTIVO");
			      convenioEntidad.setCoenCalidad("I");
			      
			
			      try {
					convenioEntidad.setCoenFechaInicio(formato.parse(fecha));
					 convenioEntidad.setCoenFechaFin(formato.parse(fechaFin));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					Transaction tx = null;
			       
					
					
					
					System.out.println("ConvenioMArco____"+convenio.getConvNumero());
			        
			        session.beginTransaction();
					tx = session.getTransaction();
					try {
					session.save(convenioEntidad);
					
					tx.commit();
					
				
					
					
					if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
						
					    tx.commit();
					    session.close();
					}
					
					}finally {
						System.out.println("10: por aqui paso");
						//session.close();
						session.close();
						
					}
					*/
					StringBuilder sb = new StringBuilder("");
					sb.append(":");
					
					PrintWriter out = response.getWriter();
					out.write(sb.toString());
			     
		//	}
			
	}

}
