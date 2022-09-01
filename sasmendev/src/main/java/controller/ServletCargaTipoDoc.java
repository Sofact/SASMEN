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
import model.SgdTipoDocumento;
import model.ViewConvenioTipoDocumento;

/**
 * Servlet implementation class ServletCargaTipoDoc
 */
@WebServlet("/ServletCargaTipoDoc")
public class ServletCargaTipoDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaTipoDoc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	GetData gd = new GetData();
		String convenio= null;
		//convenio = request.getParameter("convId");
		//int idConvenio =0;
		//convenio = convenio.replace(" ", "");
		//idConvenio = Integer.parseInt(convenio);
		//List<ViewConvenioTipoDocumento> tdoc =   gd.ViewConvenioTipoDocumento(idConvenio);
		List<SgdTipoDocumento> tipoDoc = gd.getTipoDocumento();
		StringBuilder sb = new StringBuilder("");
		
		for (SgdTipoDocumento tipoDocumento : tipoDoc) {
			sb.append(tipoDocumento.getTidoId() + "-" + tipoDocumento.getTidoNombre()+ ":");
		}
			
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		GetData gd = new GetData();
		StringBuilder sb = new StringBuilder("");
		String convenio= null;
		convenio = request.getParameter("convId");
		int idConvenio =0;
		convenio = convenio.replace(" ", "");
		idConvenio = Integer.parseInt(convenio);
		List<ViewConvenioTipoDocumento> tdoc =   gd.ViewConvenioTipoDocumento(idConvenio);
		
		for (ViewConvenioTipoDocumento tipoDocumento : tdoc) {
			sb.append(tipoDocumento.getTidoId() + "-" + tipoDocumento.getTidoNombre()+"-" +tipoDocumento.getCotdFechaFin()+ ":");
		}
			
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
	}

}
