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
 * Servlet implementation class FileUploadServletAuto
 */
@WebServlet("/FileUploadServletAuto")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   	// 100 MB
public class FileUploadServletAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static final String UPLOAD_DIR = "uploads";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServletAuto() {
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
        BufferedReader br = new BufferedReader (new InputStreamReader(request.getInputStream()));
        String fechaIni= null;
		String fechaFin = null;
		String convenio= null;
		String valor = null;
		String valorOriginal = null;
		String tipoDoc = null;
		String nombreDoc = null;
		LocalDate fechaInicio = null;
		LocalDate fechaFinalizacion = null;
		SgdDocumento documento = new SgdDocumento();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String  open = "(", close = ")";
		
		SimpleDateFormat formatoYear = new SimpleDateFormat("yyyy");
		GetData gd = new GetData();
		int year = 0;
        //inicial el gaurdado de la caracterizaciON DEL DOCUMENTO
        //------------------------------
		String json = "";
		String documentoId = request.getParameter("data");
		 System.out.println("-EN el FielUpload antes de parser>>>>>>>>>>>>>>-"+documentoId);
        if(br != null){
            json = br.readLine();
            System.out.println("-EN el FielUpload>>>>>>>>>>>>>>-"+json);
        }
        
        JSONParser parser = new JSONParser();  
        
		JSONObject jsonOb;
		JSONArray jsonEntidades= null;
		try {
			jsonOb = (JSONObject) parser.parse(documentoId);
			JSONArray jsonarr = (JSONArray) jsonOb.get("detallePVM");
			System.out.println("--"+jsonarr);
			
			for (int i = 0; i < jsonarr.size(); i++) {
				   
		        JSONObject jsonObject = (JSONObject) jsonarr.get(i);
		      System.out.println("El boject---"+i+"---"+jsonObject);
		      
		       fechaIni =  (String) jsonObject.get("fechaIni");
		       fechaFin =  (String) jsonObject.get("fechaFin");
		       convenio = (String) jsonObject.get("convenio");
		       valorOriginal = (String) jsonObject.get("valor");
		       tipoDoc = (String) jsonObject.get("tipoDoc");
		       nombreDoc = (String) jsonObject.get("nombreDoc");
		       valor = valorOriginal.replace(",", "");
		       Calendar calendar = Calendar.getInstance();
		       
		       String substringBetween = StringUtils.substringBetween(convenio, open, close);
		       System.out.println("Commons Lang3 : "+ substringBetween);
		       
		       int convenioId = gd.getConvenioApodo(substringBetween).get(0).getConvId();
		       
		       System.out.println("1. La fehca iiniccio foateada:::::::::"+ fechaIni);
		       
		       String consecutivo = gd.getConsecutivo(Integer.parseInt(tipoDoc)).get(0).getConsNumero();
		       
		       if (valor.isEmpty())
		       {
		    	   valor="0";
		       }
		       
		       fechaInicio = LocalDate.parse(fechaIni, formatter);
			   fechaFinalizacion = LocalDate.parse(fechaFin, formatter);
			   //calendar.setTime(fechaInicio);
			    year =  fechaInicio.getYear();
			    
			    fechaInicio = fechaInicio.plusDays(2);
			    fechaFinalizacion = fechaFinalizacion.plusDays(2);
		       
		       documento.setConvId(convenioId);
		       documento.setDocuFechaIni(fechaInicio);
		       documento.setDocuFechaFin(fechaFinalizacion);
		       documento.setDocuValor(Double.parseDouble(valor));
		       documento.setTidoId(Integer.parseInt(tipoDoc));
		       documento.setDocuNombre(nombreDoc);
		       documento.setDocuPath("./recursos");
		       documento.setDocuEstado("activo");
		       documento.setConsecutivo(Integer.parseInt(consecutivo));
		       documento.setEnlaId(year);
		       
		       Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				Transaction tx = null;
				StringBuilder sb = new StringBuilder("");
		       
				
				System.out.println("2. La fehca iiniccio foateada:::::::::"+ fechaInicio);
				
				System.out.println("Documento_convenio guaread____"+documento.getDocuFechaIni());
		        
		        session.beginTransaction();
				tx = session.getTransaction();
				session.save(documento);
				tx.commit();
				
				if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
					
				    tx.commit();
				}
			}
		
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
        
       SgdDocumento doc = (SgdDocumento) new SgdDocumento();
        
        doc = (SgdDocumento) gd.getDocumentoById(documento.getDocuId()).get(0);
        
        
        SgdConvenio conve =new SgdConvenio();
        
       
        conve = (SgdConvenio) gd.getConvenio(doc.getConvId()).get(0);
       
        String elpath= conve.getConvPath();
        System.out.println("El pathh:::::"+conve.getConvPath());
        MoverArchivoMove mam = new MoverArchivoMove();
        mam.mover(fileName, elpath, documento.getDocuId() );
       
        System.out.println("Efilename::::::"+fileName);
        
		
 
        request.setAttribute("message", fileName + " File uploaded successfully!");
        getServletContext().getRequestDispatcher("/Radicacion.jsp").forward(
                request, response);
		} catch (org.json.simple.parser.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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