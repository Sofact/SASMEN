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

/**
 * Servlet implementation class ServletCargaConvenio
 */
@WebServlet("/ServletCargaConvenio")
public class ServletCargaConvenio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public ServletCargaConvenio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GetData gd = new GetData();
				
					
		List<SgdConvenioMarco> convMarco = gd.getConvenioMarco();
		StringBuilder sb = new StringBuilder("");
		
		for (SgdConvenioMarco convenioMarco : convMarco) {
			sb.append(convenioMarco.getComaId() + "-" + convenioMarco.getComaNombre() + ":");
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
