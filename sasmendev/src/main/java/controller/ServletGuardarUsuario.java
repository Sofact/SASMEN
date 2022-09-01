package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import model.SgdDocumento;
import model.SgdUsuario;

/**
 * Servlet implementation class ServletGuardarUsuario
 */
@WebServlet("/ServletGuardarUsuario")
public class ServletGuardarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGuardarUsuario() {
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

		BufferedReader br = new BufferedReader (new InputStreamReader(request.getInputStream()));
		
		SgdUsuario user = new SgdUsuario();
		
		String usuario= null;
		String password = null;
		String perfil= null;
		
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
			      
			       usuario =  (String) jsonObject.get("usuario");
			       password =  (String) jsonObject.get("password");
			       perfil = (String) jsonObject.get("perfil");
			      
			   
			       
			       user.setUsuUser(usuario);
			       user.setUsuClave(password);
			       user.setUsuNombre(perfil);
			       
			       
			       Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					Transaction tx = null;
					StringBuilder sb = new StringBuilder("");
			       
					
					
					
					System.out.println("Documento_convenio guaread____"+user.getUsuNombre());
			        
			        session.beginTransaction();
					tx = session.getTransaction();
					session.save(user);
					tx.commit();
					
					if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
						
					    tx.commit();
					}
					
					
					sb.append(user.getUsuNombre());
					
					PrintWriter out = response.getWriter();
					out.write(sb.toString());
				}
				
			} catch (org.json.simple.parser.ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}


}
