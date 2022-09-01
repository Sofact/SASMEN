package controller;

import java.util.List;

import model.SgdConvenioTipoDocumento;
import model.ViewResumen;
import model.ViewResumenDocumento;

public class CalculaTotalDocumentos {
	
	
	public String calculadorTotales() {
		
		GetData gd = new GetData();
		Util u = new Util();
		
		
		List <ViewResumenDocumento> vresumen = gd.getViewResumenDocumento();
		
		for (int i = 0; i< vresumen.size(); i++) {
		
			if (vresumen.size()>0)
			{
				long dias = u.cantidadDias(vresumen.get(i).getConvFechaInicio(), vresumen.get(i).getDocuFechaInicio());
				int cantidad = u.calcularPeriodicidadTotales(vresumen.get(i).getCotdPeriodicidad(), dias, 0);
			System.out.println(vresumen.get(i).getConvApodo()+" "+vresumen.get(i).getConvNumero()+" "+vresumen.get(i).getConvYear()+" "+ vresumen.get(i).getConvFechaInicio()+" "+ vresumen.get(i).getDocuNombre()+" "+vresumen.get(i).getConvFechaInicio()+" "+vresumen.get(i).getEnlaId()+" "+vresumen.get(i).getCotdPeriodicidad()+" "+vresumen.get(i).getDocuFechaInicio()+" "+ dias+ " "+ cantidad);
			}
		}
		
		return null;
		
	}
	
}
