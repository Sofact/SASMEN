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
import model.SgdUsuario;

/**
 * Servlet implementation class ServletCargaConvenioFiltradoAuto
 */
@WebServlet("/ServletCargaConvenioFiltradoAuto")
public class ServletCargaConvenioFiltradoAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaConvenioFiltradoAuto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
GetData gd = new GetData();
		
		String usuario= request.getParameter("i");
		System.out.println("El valor del usuario::::"+ usuario);
		List<SgdConvenio> conv;
		
		if(usuario.equals("admin")) {
			 conv = gd.getConvenio();
		}else {
			List<SgdUsuario> usu = gd.getUsuario(usuario);
			int userId= usu.get(0).getUsuId();
			 conv = gd.getConvenioUsuario(userId);
		}
		
		//List<SgdConvenio> conv = gd.getConvenio();
		StringBuilder sb = new StringBuilder("");
		
		for (SgdConvenio convenio : conv) {
			sb.append( "CONVENIO "+ convenio.getConvNumero()+" de "+convenio.getConvYear() + " (" +convenio.getConvApodo()+ ")"+ ":");
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
