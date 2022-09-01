package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SgdConvenioTipoDocumento;
import model.SgdTipoDocumento;

/**
 * Servlet implementation class ServletCargaRequerimientos
 */
@WebServlet("/ServletCargaRequerimientos")
public class ServletCargaRequerimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaRequerimientos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GetData gd = new GetData();
		System.out.println("1:Aqui empieza");
		
		String convenioId = request.getParameter("idConvenio");
		String tidoId = request.getParameter("tidoId");
		
		
		
		int convenioIdi = Integer.parseInt(convenioId);
		int tidoIdi = Integer.parseInt(tidoId);
		
		System.out.println("1:Aqui medio");
		
			List<SgdConvenioTipoDocumento> convTipoDoc = gd.getConvenioTipoDocumento(convenioIdi, tidoIdi);
			StringBuilder sb = new StringBuilder("");
			
			System.out.println("1:Aqui mediomedioa");
			
			for (SgdConvenioTipoDocumento convenioTipoDocumento : convTipoDoc) {
				sb.append(convenioTipoDocumento.getConvId()  + "-" + convenioTipoDocumento.getTidoId()  + "-" + convenioTipoDocumento.getCotdFechaReq()  + "-" + convenioTipoDocumento.getCotdValorReq()  + "-" + convenioTipoDocumento.getCotdComentarioReq()  + "-" +convenioTipoDocumento.getParticipantesReq() +"-" +convenioTipoDocumento.getCotdEnlaceReq() + ":");
			}
	
			System.out.println("1:Aqui termina");
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
