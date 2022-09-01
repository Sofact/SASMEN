package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SgdConvenio;
import model.SgdDocumento;

/**
 * Servlet implementation class ServletCargaDatosConvenio
 */
@WebServlet("/ServletCargaDatosConvenio")
public class ServletCargaDatosConvenio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaDatosConvenio() {
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
		 LocalDate fechafinaliza = null;
		 Double valorTotal=0.0;
		 Util u = new Util();
		 String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	        
		StringBuilder sb = new StringBuilder("");
		List<SgdDocumento> doc = gd.getDocumentoValor(Integer.parseInt(convId));
		List<SgdDocumento> docReduccion = gd.getDocumentoValorReduccion(Integer.parseInt(convId));
		List<SgdDocumento> docUltimaFecha = gd.getDocumentoFechaFin(Integer.parseInt(convId));
		
		List<SgdConvenio> conv= gd.getConvenio(Integer.parseInt(convId));
		
		for(int i = 0; i< doc.size(); i++) {
			System.out.println("El valor total del convenio por iteracion ::::"+ doc.get(i).getDocuValor() +":::::"+ i);
			valorTotal+= doc.get(i).getDocuValor();
		}
		
		for(int i = 0; i< docReduccion.size(); i++) {
			System.out.println("El valor total del convenio por reduccion ::::"+ doc.get(i).getDocuValor() +":::::"+ i);
			valorTotal-= docReduccion.get(i).getDocuValor();
		}
		
		
		System.out.println("El valor total del convenio::::"+ valorTotal);
				
		String moneyString = formatter.format(valorTotal);
		
		if (docUltimaFecha.size()>0) {
		fechafinaliza = docUltimaFecha.get(0).getDocuFechaFin();
		
		}else {
			fechafinaliza= conv.get(0).getConvFechaFin();
		}
		
		long diasFaltantes= u.calcularDiasComp(timeStamp, fechafinaliza.toString() );
		
		 
		sb.append(conv.get(0).getConvFechaInicio()+ "|" + conv.get(0).getConvFechaFin()+ "|" + moneyString +  "|" + fechafinaliza +"|" + diasFaltantes + "||");
		
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		
		System.out.println("Ingreso a carga datos convenio:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+ fechafinaliza);
	}

}
