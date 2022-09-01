package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SgdDocumento;

/**
 * Servlet implementation class ServletCargaInfoDocumento
 */
@WebServlet("/ServletCargaInfoDocumento")
public class ServletCargaInfoDocumento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaInfoDocumento() {
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
		System.out.println("eL PARAMATRo docuId:::::::"+ request.getParameter("docuId"));
		int docuId = Integer.parseInt(request.getParameter("docuId"));
		GetData gd = new GetData();
		List <SgdDocumento> doc = gd.getDocumentoById(docuId);
		StringBuilder sb = new StringBuilder("");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		String fini= null;
		String ffin= null;
		
		String nombre = (String) doc.get(0).getDocuNombre();
		int year = (int) doc.get(0).getEnlaId();
		LocalDate fechaIni = doc.get(0).getDocuFechaIni();
		LocalDate fechaFin = doc.get(0).getDocuFechaFin();
		
		fechaIni.plusDays(2);
		fechaFin.plusDays(2);
		String path = (String) doc.get(0).getDocuPath();
		double valor = doc.get(0).getDocuValor();
		
		 try {
			 fini =  formato.format(sdf1.parse(fechaIni.toString()));
			 ffin =  formato.format(sdf1.parse(fechaFin.toString()));
			
	    	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sb.append(nombre+ "|" + year+ "|" + fechaIni.plusDays(2) +  "|" + fechaFin.plusDays(2) +"|" + path +"|" + valor );
		
		
		
		sb.append("||");
		System.out.println("la cadema del documento::::"+ sb.toString());
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		
		
	}

}
