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

import model.SgdTipoDocumento;
import model.ViewConvenioTipoDocumento;

/**
 * Servlet implementation class ServletCargaTipoDocAuto
 */
@WebServlet("/ServletCargaTipoDocAuto")
public class ServletCargaTipoDocAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCargaTipoDocAuto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

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
			String  open = "(", close = ")";
			StringBuilder sb = new StringBuilder("");
			String convenio= null;
			convenio = request.getParameter("convId");
			System.out.println("Commons Lang3 : "+ convenio);
			String substringBetween = StringUtils.substringBetween(convenio, open, close);
		       System.out.println("Commons Lang3 : "+ substringBetween);
		    int convenioId = gd.getConvenioApodo(substringBetween).get(0).getConvId();
			int idConvenio =0;
			convenio = convenio.replace(" ", "");
			idConvenio = convenioId;
			 
			List<ViewConvenioTipoDocumento> tdoc =   gd.ViewConvenioTipoDocumento(idConvenio);
			
			for (ViewConvenioTipoDocumento tipoDocumento : tdoc) {
				sb.append(tipoDocumento.getTidoId() + "-" + tipoDocumento.getTidoNombre()+"-" +tipoDocumento.getCotdFechaFin()+ ":");
			}
				
			PrintWriter out = response.getWriter();
			out.write(sb.toString());
		}

	}