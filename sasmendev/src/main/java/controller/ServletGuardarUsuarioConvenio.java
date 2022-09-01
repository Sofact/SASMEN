package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.SgdConvenio;
import model.SgdDocumento;
import model.SgdUsuario;

/**
 * Servlet implementation class ServletGuardarUsuarioConvenio
 */
@WebServlet("/ServletGuardarUsuarioConvenio")
public class ServletGuardarUsuarioConvenio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGuardarUsuarioConvenio() {
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

		GetData gd = new GetData();
		BufferedReader br = new BufferedReader (new InputStreamReader(request.getInputStream()));
		String convenio;
		String usuario;
		String  open = "(", close = ")";
		
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
				      
				      convenio =  (String) jsonObject.get("convenio");
				      
				       usuario =  (String) jsonObject.get("usuario");
				       
				       String substringBetween = StringUtils.substringBetween(convenio, open, close);
				       System.out.println("Commons Lang3 : "+ substringBetween);
				       
				       int convenioId = gd.getConvenioApodo(substringBetween).get(0).getConvId();
				   
				       
				       System.out.println("El convenio cargado es::::"+convenio +"::"+ usuario);
				      
				      	
				      Session sesion = HibernateUtil.getSessionFactory().openSession();
						sesion.beginTransaction();
						Transaction tx = sesion.getTransaction();
						SgdConvenio documento = sesion.find(SgdConvenio.class,(convenioId));
						List <SgdUsuario> user = gd.getUsuario(usuario);
						
						
						documento.setUsuId(user.get(0).getUsuId());
						  
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
