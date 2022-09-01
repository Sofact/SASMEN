package controller;

import static java.time.temporal.ChronoUnit.DAYS;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import model.SgdConvenio;
import model.SgdConvenioTipoDocumento;
import model.SgdDocumento;

/**
 * Servlet implementation class ServletCalculaAvanceConvenioAuto
 */
@WebServlet("/ServletCalculaAvanceConvenioAuto")
public class ServletCalculaAvanceConvenioAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCalculaAvanceConvenioAuto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		GetData gd = new GetData();
		CalcularAvanceTipoDoc calAvaTipDoc = new CalcularAvanceTipoDoc();
		String convenio = request.getParameter("convenio");
		int convId = 0;
		int cantidadDocumentos = 0;
		String unica = "unica               ";
		SgdConvenio conv= new SgdConvenio();
		Date fechaDesde = null;
		Date fechaHasta = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Date date = new Date();
		
		LocalDate fechaInicio = null;
		LocalDate fechafinaliza = null;
		
		int cantidadActual=0;
		int cantidadAperiodica = 0;
		int totalDocumentos = 0;
		
		fechaHasta = date;
		
		 String  open = "(", close = ")";
	        
		 String substringBetween = StringUtils.substringBetween(convenio, open, close);
	       System.out.println("Commons Lang3 :"+ substringBetween);
	       
	       int convenioId = gd.getConvenioApodo(substringBetween).get(0).getConvId();
		
			
		List<SgdConvenioTipoDocumento> cotido = gd.getConvenioTipoDocumento(convenioId);
		List<SgdDocumento> documento = gd.getDocumento(convenioId);
		List<SgdDocumento> documentoAperiodico = gd.getDocumentoAperiodico(convenioId);
	
		
		
		cantidadActual = documento.size();
		cantidadAperiodica = documentoAperiodico.size()+1;
		
		totalDocumentos = cantidadActual ;
		
		convId = cotido.get(0).getConvId();
		
		conv=gd.getConvenio(convId).get(0);
		
		

		
		
		LocalDate ini1= null;;
		ini1 =  LocalDate.parse(conv.getConvFechaInicio().toString(), formatter);  
		LocalDate fin1=null;;
		fin1 =  LocalDate.now();
		
		
		   
		   /*
		 long startTime = ini1. ;
	     long endTime = fin1.;
	     long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora 
	     long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
	     long dias = diasHasta - diasDesde;
	     
	     */
	     long dias = DAYS.between(ini1, fin1);
		
		
	     
	     String  requestDate = conv.getConvFechaInicio().toString();
	     LocalDate myDate = LocalDate.parse(requestDate);

	     LocalDate currentDate = LocalDate.now();

	     long numberOFDays = ChronoUnit.DAYS.between(myDate, currentDate);
	     dias=numberOFDays;

	     System.out.println(String.format("The diff of days is %d",numberOFDays));

	     System.out.println(String.format("The diff of days is %d",dias));
		
		System.out.println("NombreConvenio:::"+ conv.getConvNumero()+conv.getConvYear()+"::fechaInicio:::"+ conv.getConvFechaInicio()+"LA cantidad actual de documentos"+totalDocumentos);
		
		for (int i=0; i<cotido.size(); i++)
		 {
			String period = cotido.get(i).getCotdPeriodicidad();
			System.out.println("el valor periodicidad::"+period+"::::"+ unica);
			
			if (period.equals(unica))	
			{
				cantidadDocumentos++;
				System.out.println("La cantidad de documentos1:::"+ (dias/30)+"::CAntidad:::"+ cantidadDocumentos+"Qyue paso1:::"+cotido.get(0).getCotdPeriodicidad());
			}
			
			if (period.equals("mensual             "))
			{
				cantidadDocumentos+= (int) (dias/30);
				System.out.println("La cantidad de documentos2:::"+ (dias/30)+"::CAntidad:::"+ cantidadDocumentos+"Qyue paso2:::"+cotido.get(i).getCotdPeriodicidad());
			}	
			if (period.equals("trimestral          "))
			{
				cantidadDocumentos+= (int) (dias/90);
				if(dias<90)
					cantidadDocumentos++;
				System.out.println("La cantidad de documentos3:::"+ (dias/90)+"::CAntidad:::"+ cantidadDocumentos+"Qyue paso3:::"+cotido.get(i).getCotdPeriodicidad());
			}
			if (period.equals("semestral           "))
			{
				cantidadDocumentos+= (int) (dias/180);
				if(dias<180)
					cantidadDocumentos++;
				System.out.println("La cantidad de documentos3:::"+ (dias/90)+"::CAntidad:::"+ cantidadDocumentos+"Qyue paso3:::"+cotido.get(i).getCotdPeriodicidad());
			}
			
			if (period.equals("anual                    "))
			{
				cantidadDocumentos+= (int) (dias/360);
				if(dias<360)
					cantidadDocumentos++;
				System.out.println("La cantidad de documentos3:::"+ (dias/90)+"::CAntidad:::"+ cantidadDocumentos+"Qyue paso3:::"+cotido.get(i).getCotdPeriodicidad());
			}
			System.out.println("convenioDocumento::::"+ cotido.get(i).getConvId() +"::::PERIODICIDAD::" + cotido.get(i).getCotdPeriodicidad()+"::tipodoc::"+cotido.get(i).getTidoId());
		}
		
		
		StringBuilder sb= new StringBuilder("");
		int totalDocument=0;
			//sb.append("total"+"|"+cantidadDocumentos + "|" + Math.ceil(cantidadActual)+"||");
			
			if (documento.size()==1) {
				int cantidadGlobal;
				int global = 0;
				cantidadGlobal = calAvaTipDoc.CalculaAvanceTotal(documento.get(0).getConvId(), documento.get(0).getTidoId(), dias, documento.get(0).getEnlaId());
				global += cantidadGlobal;
				
				String cantidadTotalByDoc;
				fechaInicio = documento.get(0).getDocuFechaIni();
				fechafinaliza = documento.get(0).getDocuFechaFin();
				
				cantidadTotalByDoc = (String) calAvaTipDoc.CalcularAvanceTipoDoc(documento.get(0).getConvId(), documento.get(0).getTidoId(), dias, documento.get(0).getEnlaId());
				totalDocument = (int) Math.ceil(totalDocumentos);
				sb.append("total"+"|"+global + "|" + totalDocument +"||");
				sb.append("subtitulo"+"|"+documento.get(0).getDocuNombre() + " "+documento.get(0).getEnlaId()+ "|" + totalDocumentos + "|" + cantidadTotalByDoc+"||");
				sb.append("contenido"+"|"+documento.get(0).getDocuNombre()+"|"+fechaInicio.plusDays(2)+"|"+fechafinaliza.plusDays(2)+"|"+documento.get(0).getDocuPath()+"|"+documento.get(0).getDocuId()+"|"+cotido.get(0).getCotdPeriodicidad()+"||");
				
				
			}
		
			int global = 0;
			for(int i=1; i< documento.size(); i++) {
				
				
				
				if (i==1) {
					int cantidadGlobal;
					
					cantidadGlobal = calAvaTipDoc.CalculaAvanceTotal(documento.get(i-1).getConvId(), documento.get(i-1).getTidoId(), dias, documento.get(i-1).getEnlaId());
					global += cantidadGlobal;
					
				}
				 if (documento.get(i).getTidoId()!= documento.get(i-1).getTidoId() || documento.get(i).getEnlaId() != documento.get(i-1).getEnlaId()) {
					 
					  int cantidadGlobal;
						
					 cantidadGlobal =  calAvaTipDoc.CalculaAvanceTotal(documento.get(i).getConvId(), documento.get(i).getTidoId(), dias, documento.get(i).getEnlaId());
						global += cantidadGlobal;
				 }
				
			}
			totalDocument = (int) Math.ceil(totalDocumentos);
			sb.append("total"+"|"+global + "|" + totalDocument +"||");
			
			for(int i=1; i< documento.size(); i++) {
				System.out.println("ini:::"+i+"::fin::"+(i+1)+"size::"+documento.size());
				
				if (i==1) {
				String cantidadTotalByDoc;
				LocalDate inicioF = LocalDate.parse(documento.get(i-1).getDocuFechaIni().toString(), formatter);
				LocalDate fechFinal = LocalDate.parse(documento.get(i-1).getDocuFechaFin().toString(), formatter);
				inicioF = inicioF.plusDays(2);
				fechFinal = fechFinal.plusDays(2);
				
					cantidadTotalByDoc = (String) calAvaTipDoc.CalcularAvanceTipoDoc(documento.get(i-1).getConvId(), documento.get(i-1).getTidoId(), dias, documento.get(i-1).getEnlaId());
				
				//sb.append("year"+"|"+documento.get(i-1).getEnlaId());
				sb.append("subtitulo"+"|"+documento.get(i-1).getDocuNombre() + " "+documento.get(i-1).getEnlaId()+ "|" + totalDocumentos+ "|" + cantidadTotalByDoc+"||");
				sb.append("contenido"+"|"+documento.get(i-1).getDocuNombre()+"|"+inicioF+"|"+fechFinal+"|"+documento.get(i-1).getDocuPath()+"|"+documento.get(i-1).getDocuId()+"|"+cotido.get(i-1).getCotdPeriodicidad()+"||");
				
				}
				
				 if (documento.get(i).getTidoId()!= documento.get(i-1).getTidoId() || documento.get(i).getEnlaId() != documento.get(i-1).getEnlaId()) {
					
					String cantidadTotalByDoc;
					
						cantidadTotalByDoc = (String) calAvaTipDoc.CalcularAvanceTipoDoc(documento.get(i).getConvId(), documento.get(i).getTidoId(), dias, documento.get(i).getEnlaId());
					
				//	sb.append("year"+"|"+documento.get(i).getEnlaId());
						sb.append("subtitulo"+"|"+documento.get(i).getDocuNombre() + " "+documento.get(i).getEnlaId()+ "|" + totalDocumentos+"|" + cantidadTotalByDoc+"||");
					
				}
				LocalDate inicioF = LocalDate.parse(documento.get(i).getDocuFechaIni().toString(), formatter);
				LocalDate fechFinal = LocalDate.parse(documento.get(i).getDocuFechaFin().toString(), formatter);
				inicioF = inicioF.plusDays(2);
				fechFinal = fechFinal.plusDays(2);
				sb.append("contenido"+"|"+documento.get(i).getDocuNombre()+"|"+inicioF+"|"+fechFinal+"|"+documento.get(i).getDocuPath()+"|"+documento.get(i).getDocuId()+"||");
				
				
			}
		
			System.out.println("El resultado:::"+sb.toString());
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
