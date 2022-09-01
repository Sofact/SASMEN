package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import static java.time.temporal.ChronoUnit.DAYS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import model.SgdConvenio;
import model.SgdDocumento;

public class Util {
	
	public long calcularDias(String ini, String inicio) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		System.out.println("Que informacion llega aqui::::: "+ini);
		
		LocalDate ini1= null;;
		ini1 =  LocalDate.parse(ini+"-01-01", formatter);  
		LocalDate fin1=null;;
		fin1 =  LocalDate.parse(inicio, formatter);  
		   
		   /*
		 long startTime = ini1. ;
	     long endTime = fin1.;
	     long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora 
	     long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
	     long dias = diasHasta - diasDesde;
	     
	     */
	     long dias = DAYS.between(ini1, fin1);
		
		return dias;
	}
	
	public long calcularDiasAlarma(String ini, String inicio) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		System.out.println("Que informacion llega aqui::::: "+ini);
		
		LocalDate ini1= null;;
		ini1 =  LocalDate.parse(ini, formatter);  
		LocalDate fin1=null;;
		fin1 =  LocalDate.parse(inicio, formatter);  
		   
		   /*
		 long startTime = ini1. ;
	     long endTime = fin1.;
	     long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora 
	     long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
	     long dias = diasHasta - diasDesde;
	     
	     */
	     long dias = DAYS.between(ini1, fin1);
		
		return dias;
	}
	
	public long calcularDiasComp(String ini, String inicio) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		System.out.println("Que informacion llega aqui::::: "+ini);
		
		LocalDate ini1= null;;
		ini1 =  LocalDate.parse(ini, formatter);  
		LocalDate fin1=null;;
		fin1 =  LocalDate.parse(inicio, formatter);  
		   
		   /*
		 long startTime = ini1. ;
	     long endTime = fin1.;
	     long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora 
	     long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
	     long dias = diasHasta - diasDesde;
	     
	     */
	     long dias = DAYS.between(ini1, fin1);
		
		return dias;
	}

	
	public String calcularPeriodicidad(String periodicidad, long dias, int docuSize) {
			
		
		System.out.println("El valor de la periodicidad::::"+periodicidad+":::"+ dias);
		int cantidadPeriodicidad =0;
		
		if (periodicidad.equals("unica               "))	
		{
			cantidadPeriodicidad++;
			
		}
		if (periodicidad.equals("aperiodica          "))	
		{
			cantidadPeriodicidad= docuSize;
			
		}
		
		if (periodicidad.equals("mensual             "))
		{
			cantidadPeriodicidad+= (int) (dias/30);
			
		}	
		if (periodicidad.equals("trimestral          "))
		{
			cantidadPeriodicidad+= (int) (dias/90);
			if(dias<90)
				cantidadPeriodicidad++;
			
		}
		if (periodicidad.equals("semestral           "))
		{
			cantidadPeriodicidad+= (int) (dias/180);
			if(dias<180)
				cantidadPeriodicidad++;
			
		}
		
		if (periodicidad.equals("anual                    "))
		{
			cantidadPeriodicidad+= (int) (dias/360);
			if(dias<360)
				cantidadPeriodicidad++;
			
		}
		
		String resultado = docuSize +"/"+cantidadPeriodicidad+ " (Periodicidad: "+periodicidad+")";
		
		return resultado;
	}
	
	public int calcularFaltantes(String periodicidad, int tidoId, int year, int convId) {
		
		GetData gd = new GetData();
		Util u = new Util();
		List<SgdConvenio> convenio = gd.getConvenio(convId);
		LocalDate yearIni= convenio.get(0).getConvFechaInicio();
		List<SgdDocumento> doc = gd.getDocumento(convId, tidoId, year);
		SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		DateTimeFormatter formattery = DateTimeFormatter.ofPattern("yyyy");
		long dias = 0;
		
		String iniYear = formattery.format(yearIni);
		String ini = formattery.format(yearIni);
		String inicio = year+"-12-31";
		String yearIniciando = yearIni.toString();
				
		String timeStamp = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		String ahoraSi = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		
		int yearActual = Integer.parseInt(timeStamp);
		String eneroActual = yearActual+"";
		
		
		if (Integer.parseInt(iniYear) == year) {
			System.out.println("El calculo del año perro inicia"+ ini +"::::"+ iniYear);
			dias=u.calcularDiasAlarma(yearIniciando, inicio);
		}
		
		else if (yearActual == year) {
			System.out.println("El calculo del año perro finañ"+ eneroActual +"::::"+ ahoraSi);
			dias= u.calcularDias(eneroActual, ahoraSi);
			System.out.println("LA cantidad de dias es::::"+ dias);
		}
		else {
			dias= 360;
		}
		
		int resultado = u.PeriodicidadFaltante(periodicidad, dias, doc.size());
		System.out.println("El resultado de periodicidad faltantes:::::"+ resultado);
		
		return resultado;
	}
	
	public int PeriodicidadFaltante(String periodicidad, long dias, int docuSize) {
		
		System.out.println("El valor de la periodicidad::::"+periodicidad+":::"+ dias+":::docusize::::"+ docuSize);
		int cantidadPeriodicidad =0;
		
		if (periodicidad.equals("unica               "))	
		{
			cantidadPeriodicidad++;
			
		}
		if (periodicidad.equals("aperiodica          "))	
		{
			cantidadPeriodicidad= docuSize;
			
		}
		
		
		if (periodicidad.equals("mensual             "))
		{
			cantidadPeriodicidad+= (int) (dias/30);
			
		}	
		if (periodicidad.equals("trimestral          "))
		{
			cantidadPeriodicidad+= (int) (dias/90);
			if(dias<90)
				cantidadPeriodicidad++;
			
		}
		if (periodicidad.equals("semestral           "))
		{
			cantidadPeriodicidad+= (int) (dias/180);
			if(dias<180)
				cantidadPeriodicidad++;
			
		}
		
		if (periodicidad.equals("anual                    "))
		{
			cantidadPeriodicidad+= (int) (dias/360);
			if(dias<360)
				cantidadPeriodicidad++;
			
		}
		
		int res = cantidadPeriodicidad - docuSize;
		
		System.out.println("El valor de la diferencia de los documentos::::"+ res);
		
		return res;
	}
	
public int calcularPeriodicidadTotales(String periodicidad, long dias, int docuSize) {
			
		
		System.out.println("El valor de la periodicidad::::"+periodicidad+":::"+ dias);
		int cantidadPeriodicidad =0;
		
		if (periodicidad.equals("unica               "))	
		{
			cantidadPeriodicidad++;
			
		}
		if (periodicidad.equals("aperiodica          "))	
		{
			cantidadPeriodicidad= docuSize;
			
		}
		
		
		if (periodicidad.equals("mensual             "))
		{
			cantidadPeriodicidad+= (int) (dias/30);
			
		}	
		if (periodicidad.equals("trimestral          "))
		{
			cantidadPeriodicidad+= (int) (dias/90);
			if(dias<90)
				cantidadPeriodicidad++;
			
		}
		if (periodicidad.equals("semestral           "))
		{
			cantidadPeriodicidad+= (int) (dias/180);
			if(dias<180)
				cantidadPeriodicidad++;
			
		}
		
		if (periodicidad.equals("anual                    "))
		{
			cantidadPeriodicidad+= (int) (dias/360);
			if(dias<360)
				cantidadPeriodicidad++;
			
		}
		
		int resultado = cantidadPeriodicidad;
		
		System.out.println("::::::::::::::::::::::ESTA ES LA CANTIDAD DE DOCUMENTOS CON PERIODICIDAD:::::::"+periodicidad+"::::CON CANTIDAD:::"+resultado);
		
		return resultado;
	}
	
	public long cantidadDias (Date fechaConv, Date fechaDoc) {
		
		SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");
		String yearConv = formatterYear.format(fechaConv);
		String yearDoc = formatterYear.format(fechaDoc);
		Util u = new Util();
		
		
		System.out.println("La fecha:::"+ fechaConv);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		long dias = 0;
		
		String iniYear = yearConv;
		String ini = formatter.format(fechaConv);
		String inicio = iniYear+"-12-31";
		
		String timeStamp = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		String ahoraSi = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		
		int yearActual = Integer.parseInt(timeStamp);
		String eneroActual = yearActual+"-01-01";
		

		if (Integer.parseInt(yearConv) == Integer.parseInt(yearDoc)) {
			System.out.println("El calculo del año perro inicia"+ ini +"::::"+ iniYear);
			dias=u.calcularDias(ini, inicio);
		}
		
		else if (yearActual == Integer.parseInt(iniYear)) {
			System.out.println("El calculo del año perro finañ"+ eneroActual +"::::"+ ahoraSi);
			dias= u.calcularDias(eneroActual, ahoraSi);
			System.out.println("LA cantidad de dias es::::"+ dias);
		}
		else {
			dias= 360;
		}
		
		return dias;
	}
	public long calcularDiasClean(String ini, String inicio) {

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		System.out.println("Que informacion llega aqui::::: "+ini);
		
		LocalDate ini1= null;;
		ini1 =  LocalDate.parse(ini, formatter);  
		LocalDate fin1=null;;
		fin1 =  LocalDate.parse(inicio, formatter);  
		   
		   /*
		 long startTime = ini1. ;
	     long endTime = fin1.;
	     long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora 
	     long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
	     long dias = diasHasta - diasDesde;
	     
	     */
	     long dias = DAYS.between(ini1, fin1);
		
		return dias;
	}
	
	public void recorrerExcel (String path) {
	/*	
		System.out.println("Ingreso al recorrerExcel");
		
		 File f = new File("C:\\Users\\Hugo Julian Jaimes R\\eclipse-workspace_MENDEV\\sasmendev\\src\\main\\webapp\\"+path+"\\basecompilada.xlsx");
	       InputStream inp;
		try {
			inp = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(inp);
		      Sheet sheet = wb.getSheetAt(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  Row row = sh.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
	      while(row!=null) 
	      {
	          Cell cell = row.getCell(1);  
	          String value = cell.getStringCellValue();
	          System.out.println("Valor de la celda es " + value);
	          iRow++;  
	          row = sh.getRow(iRow);
	      }
	       
	*/
	}

}
