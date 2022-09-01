package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import model.SgdConvenioMarco;

/**
 * Servlet implementation class ServletAdminConvenioMarco
 */
@WebServlet("/ServletAdminConvenioMarco")
public class ServletAdminConvenioMarco extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdminConvenioMarco() {
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
		BufferedReader br = new BufferedReader (new InputStreamReader(request.getInputStream()));
		SgdConvenioMarco convenioMarco = new SgdConvenioMarco();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = null;
		String nombre=null;
		 String path = null;
		 Date fechajs = null;
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
				      
				       fecha =  (String) jsonObject.get("fecha");
				       path = (String) jsonObject.get("estado");
				       nombre = (String) jsonObject.get("nombre");
				       
				       
				       try {
						fechajs = formato.parse(fecha);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				      
				      System.out.println("El jsonArray interno---"+fechajs+"---"+path+"--"+nombre);

				        convenioMarco.setComaNombre(nombre);
				        convenioMarco.setComaDescripcion(path);
				        convenioMarco.setComaEstado("activo");
				        convenioMarco.setComaFechaInicio(fechajs);
				       
				        
				        try {
							convenioMarco.setComaFechaInicio(formato.parse(fecha));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
						Transaction tx = null;
				       
						
						
						
						System.out.println("ConvenioMArco____"+convenioMarco.getComaNombre());
				        
				        session.beginTransaction();
						tx = session.getTransaction();
						session.save(convenioMarco);
						tx.commit();
						
						if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
							
						    tx.commit();
						}
				      
				}
				
			} catch (org.json.simple.parser.ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			

	}

}
