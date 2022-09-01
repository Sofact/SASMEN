package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SgdConvenio;
import model.ViewConvenioEntidad;

/**
 * Servlet implementation class ServletCargaInfoConvenio
 */
@WebServlet("/ServletCargaInfoConvenio")
public class ServletCargaInfoConvenio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaInfoConvenio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String convId= request.getParameter("convenio");
		GetData gd = new GetData();
		StringBuilder sb = new StringBuilder("");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaInicio = null;
		String fini= null;
		String ffin= null;
		List <SgdConvenio> conv = gd.getConvenio(Integer.parseInt(convId));
		List <ViewConvenioEntidad> coen = gd.getConvenioEntidad(Integer.parseInt(convId));
		try {
			 fini =  formato.format(sdf1.parse(conv.get(0).getConvFechaInicio().toString()));
			 ffin =  formato.format(sdf1.parse(conv.get(0).getConvFechaFin().toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Antes de l entidadLas entidaddes"+ coen.size());
		
		
		sb.append(conv.get(0).getConvNumero()+ "|" + conv.get(0).getConvYear()+ "|" + fini  +  "|" + ffin +"|" + conv.get(0).getConvValor()+"|" + conv.get(0).getConvId() );
		
		for (int i=0 ; i<coen.size(); i++) {
			System.out.println("Las entidaddes"+coen.get(i).getEntiNombre());
			sb.append("|"+coen.get(i).getEntiNombre());
		}
		
		sb.append("||");
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
