package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadLocal
 */
@WebServlet("/DownloadLocal")
public class DownloadLocal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadLocal() {
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
		// TODO Auto-generated method stub
		 // Descargar archivos locales
		
		System.out.println("Ingreso al local download");
        String fileName = "Informe_financiero_julio2020_77.xls  -  firma.pdf" .toString (); // El nombre de guardado predeterminado del archivo
        // leer en la secuencia
        InputStream inStream = new FileInputStream ("F:\\12022\\Informe_financiero_julio2020_77.xls  -  firma.pdf"); // Ruta de almacenamiento de archivos
        // Establecer el formato de salida
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		        // Recorre los datos en la secuencia
		byte[] b = new byte[1000];
		int len;
		
		
		try {
		   while ((len = inStream.read(b)) > 0)
		       response.getOutputStream().write(b, 0, len);
		   inStream.close();
		} catch (IOException e) {
		   e.printStackTrace();
		}
	}

}
