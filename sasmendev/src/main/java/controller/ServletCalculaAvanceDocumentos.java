package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SgdDocumento;

/**
 * Servlet implementation class ServletCalculaAvanceDocumentos
 */
@WebServlet("/ServletCalculaAvanceDocumentos")
public class ServletCalculaAvanceDocumentos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCalculaAvanceDocumentos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GetData gd = new GetData();
		int val = Integer.parseInt(request.getParameter("docuId"));
		
		int convId =0;
		int tido_id =0;
		
		List<SgdDocumento> documento = gd.getDocumentoById(val);
		
		List<SgdDocumento> doc = gd.getDocumento();
		
		
		
		StringBuilder sb= new StringBuilder("");
		sb.append(documento.get(0).getDocuNombre() + "-" + documento.get(0).getTidoId()+ "-" + documento.get(0).getDocuFechaIni()+"-" + documento.get(0).getDocuPath()+":");

		System.out.println("ingrso por el calculoo Documentos"+ sb.toString());
		
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
