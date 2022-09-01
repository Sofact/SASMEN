package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

/**
 * Servlet implementation class ServletGuardarDocumento
 */
@WebServlet("/ServletGuardarDocumento")
public class ServletGuardarDocumento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGuardarDocumento() {
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

		System.out.println("Ingreso al post");
		BufferedReader br = new BufferedReader (new InputStreamReader(request.getInputStream()));
		String fechaIni= null;
		String fechaFin = null;
		String convenio= null;
		String valor = null;
		String valorOriginal = null;
		String tipoDoc = null;
		String nombreDoc = null;
		LocalDate fechaInicio = null;
		LocalDate fechaFinalizacion = null;
		SgdDocumento documento = new SgdDocumento();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoYear = new SimpleDateFormat("yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		GetData gd = new GetData();
		int year = 0;
		
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
			      
			       fechaIni =  (String) jsonObject.get("fechaIni");
			       fechaFin =  (String) jsonObject.get("fechaFin");
			       convenio = (String) jsonObject.get("convenio");
			       valorOriginal = (String) jsonObject.get("valor");
			       tipoDoc = (String) jsonObject.get("tipoDoc");
			       nombreDoc = (String) jsonObject.get("nombreDoc");
			       valor = valorOriginal.replace(",", "");
			       Calendar calendar = Calendar.getInstance();
			       
			       String consecutivo = gd.getConsecutivo(Integer.parseInt(tipoDoc)).get(0).getConsNumero();
			       
			       if (valor.isEmpty())
			       {
			    	   valor="0";
			       }
			       
			       fechaInicio = LocalDate.parse(fechaIni, formatter);
				   fechaFinalizacion = LocalDate.parse(fechaFin, formatter);
				   //calendar.setTime(fechaInicio);
				    year =  fechaInicio.getYear();
			       
			       documento.setConvId(Integer.parseInt(convenio));
			       documento.setDocuFechaIni(fechaInicio);
			       documento.setDocuFechaFin(fechaFinalizacion);
			       documento.setDocuValor(Double.parseDouble(valor));
			       documento.setTidoId(Integer.parseInt(tipoDoc));
			       documento.setDocuNombre(nombreDoc);
			       documento.setDocuPath("./recursos");
			       documento.setDocuEstado("activo");
			       documento.setConsecutivo(Integer.parseInt(consecutivo));
			       documento.setEnlaId(year);
			       
			       Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					Transaction tx = null;
					StringBuilder sb = new StringBuilder("");
			       
					
					
					
					System.out.println("Documento_convenio guaread____"+documento.getDocuFechaIni());
			        
			        session.beginTransaction();
					tx = session.getTransaction();
					session.save(documento);
					tx.commit();
					
					if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
						
					    tx.commit();
					}
					System.out.println("Documento_convenio :::____"+documento.getDocuId());
			       
					
					sb.append(documento.getDocuId());
					
					PrintWriter out = response.getWriter();
					out.write(sb.toString());
				}
				
			} catch (org.json.simple.parser.ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

}
