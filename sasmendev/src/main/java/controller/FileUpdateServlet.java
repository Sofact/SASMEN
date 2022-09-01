package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.SgdConvenio;
import model.SgdDocumento;

/**
 * Servlet implementation class FileUpdateServlet
 */
@WebServlet("/FileUpdateServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
					maxFileSize=1024*1024*50,      	// 50 MB
					maxRequestSize=1024*1024*100)   	// 100 MB
public class FileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpdateServlet() {
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
		 // gets absolute path of the web application
        String applicationPath = "./recursos/";
        int docuId = Integer.parseInt(request.getParameter("docuId"));
       
      
		GetData gd = new GetData();
		
				        
        System.out.println("El id del documento:::"+docuId);
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        
        String fileName = null;
        
        //Get all the parts from request and write it to the file on server
       System.out.println("Que dijo::::"+ request.getParts().isEmpty());
        for (Part part : request.getParts()) {
            fileName = getFileName(part);
            
            
            part.write(fileName);
            
        }
        
       SgdDocumento doc = (SgdDocumento) new SgdDocumento();
        
        doc = (SgdDocumento) gd.getDocumentoById(docuId).get(0);
        
        
        SgdConvenio conve =new SgdConvenio();
        
       
        conve = (SgdConvenio) gd.getConvenio(doc.getConvId()).get(0);
       
        String elpath= conve.getConvPath();
        System.out.println("El pathh:::::"+conve.getConvPath());
        MoverArchivoMove mam = new MoverArchivoMove();
        mam.mover(fileName, elpath, docuId);
       
        System.out.println("Efilename::::::"+fileName);
        
		
 
        request.setAttribute("message", fileName + " File uploaded successfully!");
        getServletContext().getRequestDispatcher("/Radicacion.jsp").forward(
                request, response);
	
    }
 
    /**
     * Utility method to get file name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
    	
    	System.out.println("Ingreso por el part");
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}