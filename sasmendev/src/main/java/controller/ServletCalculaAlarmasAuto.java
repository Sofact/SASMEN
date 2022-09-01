package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import model.SgdConvenioTipoDocumento;
import model.SgdDocumento;

/**
 * Servlet implementation class ServletCalculaAlarmasAuto
 */
@WebServlet("/ServletCalculaAlarmasAuto")
public class ServletCalculaAlarmasAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCalculaAlarmasAuto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso Al ServletCalculaAlarmas");
		GetData gd = new GetData();
		String val = (request.getParameter("convenio"));
		CalcularAvanceTipoDoc calAvaTipDoc = new CalcularAvanceTipoDoc();
		String  open = "(", close = ")";
		Util u = new Util();
		int totalFaltantes = 0;
		
	    String substringBetween = StringUtils.substringBetween(val, open, close);
	       System.out.println("Commons Lang3 : "+ substringBetween);
	       
	       int convenioId = gd.getConvenioApodo(substringBetween).get(0).getConvId();
	       
		
		List<SgdConvenioTipoDocumento> cotd = gd.getConvenioTipoDocumento(convenioId);
		
		
		
		StringBuilder sb= new StringBuilder("");
		
		for (int i = 0; i<cotd.size(); i++) {
			
			totalFaltantes = u.calcularFaltantes(cotd.get(i).getCotdPeriodicidad(), cotd.get(i).getTidoId(), cotd.get(i).getCotdFechaFin(), cotd.get(i).getConvId());
			List<SgdDocumento> documento = gd.getDocumento(cotd.get(i).getConvId(),cotd.get(i).getTidoId(), cotd.get(i).getCotdFechaFin() );
			//String cantidadTotalByDoc; cantidadTotalByDoc = (String) calAvaTipDoc.CalcularAvanceTipoDoc(documento.get(i).getConvId(), documento.get(i).getTidoId(), dias, documento.get(i).getEnlaId());
			if (documento.size()> 0) {
			sb.append("subtitulo"+"|"+documento.get(0).getDocuNombre() + " "+ "|" + documento.size()+ "|" + totalFaltantes+"||");
			}

		}
		System.out.println("El resultado Calculando Alarmas:::"+sb.toString());
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
