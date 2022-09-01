package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.SgdConvenio;
import model.SgdDocumento;

/**
 * Servlet implementation class FileUploadServletBase
 */
@WebServlet("/FileUploadServletBase")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
					maxFileSize=1024*1024*500,      	// 50 MB
					maxRequestSize=1024*1024*100)   // 100 MB
public class FileUploadServletBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServletBase() {
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
		
		System.out.println("Ingreso a la carga del archivo 999");
		  String applicationPath = "./recursos/";
		  Util util = new Util();
	       GetData gd= new GetData();
			
	        // termina el guardado de caracterización del documento
	        //-----------------------------------------
	        
	        
	        
	       // System.out.println("El docu_id ::::::::::::::::::::"+request.getParameter("parametro"));
	      //  String documentoId = request.getParameter("parametro");
	      

	        // constructs path of the directory to save uploaded file
	        
	        System.out.println("El path:::"+applicationPath);
	        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
	         
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File(uploadFilePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdirs();
	        }
	        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
	        
	        String fileName = null;
	        String fileNamet= null;
	        //Get all the parts from request and write it to the file on server
	        for (Part part : request.getParts()) {
	            fileName = getFileName(part);
	            
	            
	            part.write(fileName);
	            
	        }
	        
	     
	        String elpath= "./recursos/base";
	        System.out.println("El pathh:::::"+elpath);
	        MoverArchivoMove mam = new MoverArchivoMove();
	        mam.moverBase(fileName, elpath );
	       
	        System.out.println("Efilename::::::"+fileName);
	        
			
	        util.recorrerExcel(elpath);
	        
	        request.setAttribute("message", fileName + " File uploaded successfully!");
	        getServletContext().getRequestDispatcher("/BasesCuantitativas.jsp").forward(
	                request, response);
			
	    }
	 
	    /**
	     * Utility method to get file name from HTTP header content-disposition
	     */
	    private String getFileName(Part part) {
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