package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import model.SgdConvenio;
import model.SgdDocumento;

/**
 * Servlet implementation class ServletCargaDatosConvenioAuto
 */
@WebServlet("/ServletCargaDatosConvenioAuto")
public class ServletCargaDatosConvenioAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaDatosConvenioAuto() {
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
		 LocalDate fechaInicio = null;
		 LocalDate fechafinaliza = null;
		 Double valorTotal=0.0;
		 Util u = new Util();
		 String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		 DateTimeFormatter formatoYear =   DateTimeFormatter.ofPattern("yyyy");
		 String  open = "(", close = ")";
	        
		 String substringBetween = StringUtils.substringBetween(convId, open, close);
	       System.out.println("Commons Lang3 : "+ substringBetween);
	       
	       int convenioId = gd.getConvenioApodo(substringBetween).get(0).getConvId();
		 
		StringBuilder sb = new StringBuilder("");
		List<SgdDocumento> doc = gd.getDocumentoValor(convenioId);
		List<SgdDocumento> docReduccion = gd.getDocumentoValorReduccion(convenioId);
		List<SgdDocumento> docUltimaFecha = gd.getDocumentoFechaFin(convenioId);
		
		List<SgdConvenio> conv= gd.getConvenio(convenioId);
		
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
		fechaInicio = conv.get(0).getConvFechaInicio();
		
		long diasFaltantes= u.calcularDiasClean(timeStamp, fechafinaliza.toString() );
		diasFaltantes +=2;
		
		 
		sb.append(fechaInicio.plusDays(2)+ "|" + fechafinaliza.plusDays(2)+ "|" + moneyString +  "|" + fechafinaliza.plusDays(2) +"|" + diasFaltantes + "||");
		
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		
		System.out.println("Ingreso a carga datos convenio:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+ fechafinaliza);
	
	}

}
