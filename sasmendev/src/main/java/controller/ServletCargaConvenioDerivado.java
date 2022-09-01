package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SgdConvenio;


/**
 * Servlet implementation class ServletCargaConvenioDerivado
 */
@WebServlet("/ServletCargaConvenioDerivado")
public class ServletCargaConvenioDerivado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaConvenioDerivado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GetData gd = new GetData();
		
		
		List<SgdConvenio> conv = gd.getConvenio();
		StringBuilder sb = new StringBuilder("");
		
		for (SgdConvenio convenio : conv) {
			sb.append(convenio.getConvId() + "-" + "CONVENIO "+ convenio.getConvNumero()+" de "+convenio.getConvYear() + " (" +convenio.getConvApodo()+ ")"+ ":");
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
