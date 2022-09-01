package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import model.SgdDocumento;

public class MoverArchivoMove {

    public  void mover(String fileName, String path, int docuId) {
    	
    	GetData gd = new GetData();
    	
    	   SgdDocumento docu = new SgdDocumento();
           docu= gd.getDocumentoById(docuId).get(0);
           String docuNombre = (String) docu.getDocuNombre();

    	File directorio = new File("C:\\Users\\Hugo Julian Jaimes R\\eclipse-workspace_MENDEV\\sasmendev\\src\\main\\webapp\\"+path+docuNombre);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
   
        
        System.out.println("El path del documento antes:::"+ docu.getDocuPath());
       String pathTemporal = gd.getParametroDominio("path_temp").get(0).getPadoDescripcion();
     
    	
        Path origenPath = FileSystems.getDefault().getPath(pathTemporal+fileName);
        Path destinoPath = FileSystems.getDefault().getPath("C:\\Users\\Hugo Julian Jaimes R\\eclipse-workspace_MENDEV\\sasmendev\\src\\main\\webapp\\"+path+docuNombre+"/"+fileName);
       
        System.out.println("El path del destino::::::::::"+path+docuNombre+"/"+fileName);
        
        docu.setDocuPath(path+docuNombre+"/"+fileName);
        
        String destPath = path+fileName;
        
        System.out.println("El path del documento despues de"+ docu.getDocuPath());
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
     
        
        session.beginTransaction();
		tx = session.getTransaction();
		session.update(docu);
		tx.commit();
		
		if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
			
		    tx.commit();
		}
        
        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }

    }
    
 public  void moverBase(String fileName, String path) {
    	
    	GetData gd = new GetData();
    	
    	 

    	File directorio = new File("C:\\Users\\Hugo Julian Jaimes R\\eclipse-workspace_MENDEV\\sasmendev\\src\\main\\webapp\\"+path+"\\basecompilada.xlsx");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
   
        
     //   System.out.println("El path del documento antes:::"+ docu.getDocuPath());
       String pathTemporal = gd.getParametroDominio("path_temp").get(0).getPadoDescripcion();
     
    	
        Path origenPath = FileSystems.getDefault().getPath(pathTemporal+fileName);
        Path destinoPath = FileSystems.getDefault().getPath("C:\\Users\\Hugo Julian Jaimes R\\eclipse-workspace_MENDEV\\sasmendev\\src\\main\\webapp\\"+path+"\\basecompilada.xlsx");
       
       
     //   docu.setDocuPath(path+docuNombre+"/"+fileName);
        
        String destPath = path+fileName;
        
     //   System.out.println("El path del documento despues de"+ docu.getDocuPath());
        
     //   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	//	Transaction tx = null;
     
        
     //   session.beginTransaction();
	//	tx = session.getTransaction();
	//	session.update(docu);
	//	tx.commit();
	/*	
		if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
			
		    tx.commit();
		}
     */   
        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }

    }
    
    
}
