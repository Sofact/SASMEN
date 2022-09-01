package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SgdConvenio;
import model.SgdDocumento;
import model.SgdEntidad;
import model.ViewResumen;

/**
 * Servlet implementation class ServletCargaMAtrizResumen
 */
@WebServlet("/ServletCargaMAtrizResumen")
public class ServletCargaMAtrizResumen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaMAtrizResumen() {
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
		
		String convId= request.getParameter("convenio");
		GetData gd = new GetData();
		 NumberFormat formatter = NumberFormat.getCurrencyInstance();
		 java.util.Date fechafinaliza = null;
		 
		 System.out.println("El valor del convenio recibido para filtrar el resumen:::"+ convId);
	        
		StringBuilder sb = new StringBuilder("");
		List<ViewResumen> doc = gd.getViewResumen(Integer.parseInt(convId));
		
		
		for(ViewResumen resumen : doc){
	
			sb.append(resumen.getNombre()+ "|" + resumen.getConvFechaInicio()+ "|" + resumen.getConvFechaFin() +  "|" + resumen.getConvValor()+"|" + resumen.getDocuNombre() +"|" + resumen.getConsecutivo() +"|" + resumen.getConvYear() +"|" + resumen.getConvMes() +"|" + resumen.getPeriodicidad() + "||");
		
		}
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		
		System.out.println("LA cadena que pasa al resumen:::"+sb.toString());
		
	}

}
