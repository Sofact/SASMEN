package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SgdUsuario;

/**
 * Servlet implementation class ServletCargaUsuarios
 */
@WebServlet("/ServletCargaUsuarios")
public class ServletCargaUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaUsuarios() {
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
		
		GetData gd = new GetData();
		StringBuilder sb = new StringBuilder("");
		
		List <SgdUsuario> usuario =gd.getUsuario();

		for (int i=0 ; i<usuario.size(); i++) {
			System.out.println("Las entidaddes"+usuario.get(i).getUsuUser());
			sb.append(usuario.get(i).getUsuUser()+"|"+usuario.get(i).getUsuId() +"||");
		}
		
		System.out.println("LA cadena::::::"+ sb.toString());
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
	}

}
