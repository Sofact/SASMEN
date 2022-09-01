package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SgdConvenioMarco;
import model.SgdEntidad;


/**
 * Servlet implementation class ServletCargaEntidad
 */
@WebServlet("/ServletCargaEntidad")
public class ServletCargaEntidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaEntidad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GetData gd = new GetData();
		
		
		List<SgdEntidad> entidad = gd.getEntidad();
		StringBuilder sb = new StringBuilder("");
		
		for (SgdEntidad listEntidad : entidad) {
			sb.append(listEntidad.getEntiId()+ "-" + listEntidad.getEntiNombre() + ":");
		}
			
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
