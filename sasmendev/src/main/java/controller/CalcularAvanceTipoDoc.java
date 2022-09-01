package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

//import org.joda.time.DateTime;

import model.SgdConvenio;
import model.SgdConvenioTipoDocumento;
import model.SgdDocumento;

public class CalcularAvanceTipoDoc {
	
	public int CalculaAvanceTotal(int convId, int tidoId, long dias, int year) {
		
		System.out.println("Ingreso Al calculador");
		GetData gd = new GetData();
		SgdConvenioTipoDocumento ctd = new SgdConvenioTipoDocumento();
		ctd =(SgdConvenioTipoDocumento) gd.getConvenioTipoDocumento(convId, tidoId).get(0);
		Calendar fechaHasta= null;
		Util u = new Util();
		
		List<SgdDocumento> documento =  gd.getDocumento(convId, tidoId, year);
		String periodicidad = ctd.getCotdPeriodicidad();
		List<SgdConvenio> convenio = gd.getConvenio(convId);
		
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy"); 
		LocalDate date = LocalDate.now();  
		
		LocalDate yearIni= convenio.get(0).getConvFechaInicio();
		String inicio = year+"-12-31";
		//String EneroActual = year+"-12-31";
		 try {
			Date dateInicio=new SimpleDateFormat("yyyy").parse(inicio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 
		String ini = formatter.format(yearIni);
		String fin = formatter.format(date);
		String iniYear = formatterYear.format(yearIni);
		
		LocalDate inicioConvenio = documento.get(0).getDocuFechaIni();
		
		String timeStamp = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		String ahoraSi = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		
		DateTime now = new DateTime();
		Date ahora = now.toDate();
		int yearActual = Integer.parseInt(timeStamp);
		String eneroActual = yearActual+"-01-01";
	
		
		System.out.println("El año:::enero actual::"+eneroActual+":gy::::"+ now.toString() );
		
	//	fechaHasta.add(Calendar.YEAR, ini);
	//	fechaHasta.add(Calendar.MONTH, 12);
	//	fechaHasta.add(Calendar.DAY_OF_MONTH, 31);
		
		
		
		if (Integer.parseInt(iniYear) == year) {
			System.out.println("El calculo del año perro"+ ini +"::::"+ fechaHasta);
			dias=u.calcularDiasComp(ini, inicio);
		}
		
		else if (yearActual == year) {
			System.out.println("El calculo del año perro"+ eneroActual +"::::"+ ahoraSi);
			dias= u.calcularDiasComp(eneroActual, ahoraSi);
			System.out.println("LA cantidad de dias es::::"+ dias);
		}
		else {
			dias= 360;
		}
		
		int resultado = u.calcularPeriodicidadTotales(periodicidad, dias, documento.size());
		
		return resultado;
	}
	
	@SuppressWarnings({ "deprecation", "null" })
	public String CalcularAvanceTipoDoc(int convId, int tidoId, long dias, int year)  {
		
		System.out.println("Ingreso Al calculador");
		GetData gd = new GetData();
		SgdConvenioTipoDocumento ctd = new SgdConvenioTipoDocumento();
		ctd =(SgdConvenioTipoDocumento) gd.getConvenioTipoDocumento(convId, tidoId).get(0);
		Calendar fechaHasta= null;
		Util u = new Util();
		
		List<SgdDocumento> documento =  gd.getDocumento(convId, tidoId, year);
		String periodicidad = ctd.getCotdPeriodicidad();
		List<SgdConvenio> convenio = gd.getConvenio(convId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy"); 
		
		LocalDate date =  LocalDate.now();  
		
		LocalDate yearIni= convenio.get(0).getConvFechaInicio();
		String inicio = year+"-12-31";
		//String EneroActual = year+"-12-31";
		 try {
			Date dateInicio=new SimpleDateFormat("yyyy").parse(inicio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 
		String ini = formatter.format(yearIni);
		String fin = formatter.format(date);
		String iniYear = formatterYear.format(yearIni);
		
		LocalDate inicioConvenio = documento.get(0).getDocuFechaIni();
		
		String timeStamp = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		String ahoraSi = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		
		DateTime now = new DateTime();
		Date ahora = now.toDate();
		int yearActual = Integer.parseInt(timeStamp);
		String eneroActual = yearActual+"-01-01";
	
		
		System.out.println("El año:::enero actual::"+eneroActual+":gy::::"+ now.toString() );
		
	//	fechaHasta.add(Calendar.YEAR, ini);
	//	fechaHasta.add(Calendar.MONTH, 12);
	//	fechaHasta.add(Calendar.DAY_OF_MONTH, 31);
		
		
		
		if (Integer.parseInt(iniYear) == year) {
			System.out.println("El calculo del año perro"+ ini +"::::"+ fechaHasta);
			dias=u.calcularDiasComp(ini, inicio);
		}
		
		else if (yearActual == year) {
			System.out.println("El calculo del año perro"+ eneroActual +"::::"+ ahoraSi);
			dias= u.calcularDiasComp(eneroActual, ahoraSi);
			System.out.println("LA cantidad de dias es::::"+ dias);
		}
		else {
			dias= 360;
		}
		
		/*
		int cantidadPeriodicidad =0;
		
		if (periodicidad.equals("unica               "))	
		{
			cantidadPeriodicidad++;
			
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
		
		String resultado = documento.size()+"/"+cantidadPeriodicidad+ " (Periodicidad: "+periodicidad+")";
		System.out.println("En el calculo::::::"+documento.size()+ periodicidad);
		*/
		
		String resultado = u.calcularPeriodicidad(periodicidad, dias, documento.size());
		
		return resultado;
	}

}
