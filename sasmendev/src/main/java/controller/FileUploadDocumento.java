package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.SgdConvenio;
import model.SgdDocumento;

/**
 * Servlet implementation class FileUploadDocumento
 */
@WebServlet("/FileUploadDocumento")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   	// 100 MB
public class FileUploadDocumento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    /**
     * Directory where uploaded files will be saved, its relative to
     * the web application directory.
     */
    private static final String UPLOAD_DIR = "uploads";
     
    protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        String applicationPath = "./recursos/";
        GetData gd = new GetData();
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
        List<SgdDocumento> doc = (List<SgdDocumento>) new SgdDocumento();
        
        doc = (List<SgdDocumento>) gd.getDocumento();
        
        
        
        SgdConvenio conve =new SgdConvenio();
        conve = (SgdConvenio) gd.getConvenio(doc.get(0).getConvId()).get(0);
        String elpath= conve.getConvPath();
        System.out.println("El pathh:::::"+conve.getConvPath());
        MoverArchivoMove mam = new MoverArchivoMove();
        mam.mover(fileName, elpath, 1); /// esto no esta bien se supone que esta clase no se esta usando
       
        System.out.println("Efilename::::::"+fileName);
 
        request.setAttribute("message", fileName + " File uploaded successfully!");
        getServletContext().getRequestDispatcher("/AdminConvenio.jsp").forward(
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