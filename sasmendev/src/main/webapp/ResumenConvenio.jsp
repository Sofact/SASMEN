<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
     <head>
    <!-- Required meta tags -->| 
    <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">

   
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
     <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.rawgit.com/shabuninil/fileup/master/src/fileup.min.css" rel="stylesheet">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.rawgit.com/shabuninil/fileup/master/src/fileup.min.js"></script>
     <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <title>SASMEN</title>
  </head>
  
 <script>
 
  //************************************************************************************************************
// ELIMINA REGISTROS DE LA TABLA
//  IVA  SUBTOTAL Y TOTAL
// 05/10/2018 BY JHUALC
//************************************************************************************************************

 $(document).ready(function(){
	var usuario = '<%=request.getUserPrincipal().getName()%>';
		
		if (usuario == 'admin'){
			document.getElementById("menuRadicacion").style.display = "none";
		}
		if (usuario != 'admin'){
			document.getElementById("menuRadicacion").style.display = "";
			document.getElementById("adminusuarios").style.display = "none";
			document.getElementById("adminusuariosa").style.display = "none";
		}

});


 
$(document).ready(function(){
			$('a[id=agregar]').on('click', function(){
			var NombreConvenio = document.getElementById("convNombre").value;
			var fechaConvenio = document.getElementById("datepicker").value;
			var archivo = document.getElementById("inputGroupFile02").value;
		
			
			
			$('table[id=tableResumen]').append('<tr><td width= "10%">'+NombreConvenio+'</td><td width= "15%">'+fechaConvenio+'</td><td width= "15%">'+archivo+'</td></tr>')
			})
});

$(document).ready(function(){
	$('a[id=guardar]').on('click', function(){
	
	alert("Documento Guardado Correctamente");
	
	var detalles = [];
	var contador =0;
	
	$('#tableResumen tr').each(function (index){
		var nombre, tipoDocumento, periodicidad;
		
		$(this).children("td").each(function(index2){
		
		
			switch(index2){
				case 0:
					nombre= $(this).text();
					
					break;
				case 1:
					fecha= $(this).text();
					break;
				case 2:
					estado= $(this).text();
					break;
				
			}
			
	
		})
		if (contador == 0){
			contador++;
		}else{
			detalles.push ({
				"nombre" 	: nombre,
				"fecha"		: fecha,
				"estado"		: estado
				
			});
			}
			
		// jsonFactura.detalles.push({pvid: pvmid ,cantidad: cant, tot: total});
		})
		
		var jsonProducto = JSON.stringify(eval({
						detallePVM : detalles
						}))
						
		
	
	$.ajax({
			type: 'POST',
			url: 'ServletAdminConvenioMarco',
			dataType: 'json',
			data: jsonProducto , 
			statusCode: {
				404: function(){
					alert("pagina no encontrada ValorProductoServlet");
				},
				500: function(){
					alert("Error servidor ValorProductoServlet");
				}
			},
			success: function(response){
				var separado = response.split(":");
				
				
			}
		});
	
	})
});


//************************************************************************************************************
// CARGA LISTADO DE CONVENIOS
//  
// 25/03/2022 BY JHUALC
//************************************************************************************************************

 
$(document).ready(function(){
	
	var usuario = '<%=request.getUserPrincipal().getName()%>';
	
		$.ajax({
			type: 'GET',
			url: 'ServletCargaConvenioFiltradoAuto',
			data: 'i='+usuario,
			statusCode: {
				404: function(){
					alert("Pagina no encontrada");
					},
				500: function(){
					alert("Error en servidor");
					}
				},
			success: function(res){
			
			var availableTags = res.split(":");
			$( "#tags" ).autocomplete({
		      source: availableTags
		    });
		    
		    var availableTags = res.split(":");
			$( "#tags" ).autocomplete({
		      source: availableTags
		    });
				var separado = res.split(":");
				
				document.getElementById("nombreConvenio").length=0;
				$('select[name=nombreConvenio]').append (' <option>Seleccione...</option>')
				for(var i = 0; i< separado.length -1; i++){
					var idTipoProducto = separado[i].split("-")[0];
					var nombreTipoProducto = separado[i].split("-")[1];
					$('select[name=nombreConvenio]').append('<option value = "'+idTipoProducto +'"> '+ nombreTipoProducto+ '</option>')
				}	
			}
		});	
});
 
  
//************************************************************************************************************
// CARGA DATOS DE CONVENIOS
//  
// 12/04/2022 BY JHUALC
//************************************************************************************************************

 
$(document).ready(function(){
	$('input[name=tags]').on('change', function(){
	
	$('div[name=resumenFechaIni]').empty();
	$('div[name=resumenFechaFin]').empty();
	$('div[name=resumenValor]').empty();
	$('div[name=alarmaFecha]').empty();
		$.ajax({
			type: 'POST',
			url: 'ServletCargaDatosConvenioAuto', 
			data: 'convenio='+$('input[name=tags]').val(),
			statusCode: {
				404: function(){
					alert("Pagina no encontrada");
					},
				500: function(){
					alert("Por favor seleccione un convenio");
					}
				},
			success: function(res){
				var separado = res.split("||");
				$('div[name=resumenFechaIni]').append(separado[0].split("|")[0]);
				$('div[name=resumenFechaFin]').append(separado[0].split("|")[3]);
				$('div[name=resumenValor]').append(separado[0].split("|")[2]);
				
				var terminacion =  separado[0].split("|")[4];
				var meses = terminacion /30;
				
					
				if (meses < 7 && meses > 1)
				{
				$('div[name=alarmaFecha]').append("<div class='alert alert-danger' role='alert' style='color:red'><strong>!!Faltan ("+Math.round(meses)+") meses para la terminación del convenio¡¡  </strong>   <i class='fa fa-bell' style='font-size:36px;color:red'></i></div>");
				}
				if (meses <= 1 && meses >= 0)
				{
				$('div[name=alarmaFecha]').append("<div class='alert alert-danger' role='alert' style='color:red'><strong>!!Faltan ("+separado[0].split("|")[4]+") días para la terminación del convenio¡¡  </strong>   <i class='fa fa-bell' style='font-size:36px;color:red'></i></div>");
				}
				if (meses < 0)
				{
				$('div[name=alarmaFecha]').append("<div class='alert alert-danger' role='alert' style='color:red'><strong>Este convenio está FINALIZADO o no se han agregado las prorrogas correspondientes  </strong>   <i class='fa fa-bell' style='font-size:36px;color:red'></i></div>");
				}
				
			/*	
				document.getElementById("nombreConvenio").length=0;
				$('select[name=nombreConvenio]').append (' <option>Seleccione...</option>')
				for(var i = 0; i< separado.length -1; i++){
					var idTipoProducto = separado[i].split("-")[0];
					var nombreTipoProducto = separado[i].split("-")[1];
					$('select[name=nombreConvenio]').append('<option value = "'+idTipoProducto +'"> '+ nombreTipoProducto+ '</option>')
				}	
				*/
			}
		});	
		})
});
 
//************************************************************************************************************
// CARGA AVANCE CONVENIOS
//  
// 31/03/2022 BY JHUALC
//************************************************************************************************************

 
$(document).ready(function(){
	$('input[name=tags]').on('change', function(){
	
	//document.getElementById()"#acordionBase").reset();
	$("#accordionResumen").empty();
	$("#avance").empty();
		$.ajax({
			type: 'GET',
			url: 'ServletCalculaAvanceConvenioAuto',
			data: 'convenio='+$('input[name=tags]').val(), 
			statusCode: {
				404: function(){
					alert("Pagina no encontrada");
					},
				500: function(){
					alert("Por favor seleccione un convenio");
					}
				},
			success: function(res){
				var separado = res.split("||");
				
			//	document.getElementById("nombreConvenio").length=0;
			//	$('select[name=nombreConvenio]').append (' <option>Seleccione...</option>')
				
					var cantidadTotal = separado[0].split("|")[1];
					var cantidadActual = separado[0].split("|")[2];
					//alert("total"+idTipoProducto+"::actual::"+nombreTipoProducto);
					var porcentTotal = (cantidadActual*100)/cantidadTotal;
					document.getElementById("avance").append("Avance total de carga de documentos para el convenio "+cantidadActual+"/"+cantidadTotal );
					$("#progresoTotal").text(Math.round(porcentTotal)+"%");
					$("#progresoTotal").attr("aria-valuenow", porcentTotal); 
					$("#progresoTotal").css("width", porcentTotal + "%"); 
			//Inicia el ajax anidado
			// ***************************
			//******************************		
				for(var i = 0; i< separado.length; i++){
				
					var tipo = separado[i].split("|")[0];
					var docuId = separado[i].split("|")[1];
					var cantidadActual = separado[i].split("|")[2];
					var fechaFin = separado[i].split("|")[3];
					var path =  separado[i].split("|")[4];
					var documento = separado[i].split("|")[5];
					var actual;
					var counterYear;
				
				
				if(tipo=="subtitulo"){
				actual = i;
				
				if(i==1){
				$("#accordionResumen").append("<div class='accordion-item' id='acordionBase"+actual+"' name='acordionBase"+actual+"'><h2 class='accordion-header' id='heading"+i+"'> <button class='accordion-button' type='button' data-bs-toggle='collapse' data-bs-target='#collapse"+i+"' aria-expanded='true' aria-controls='collapse"+i+"'> <DIV><table><tr><td colspan='3' width= '800px'>"+docuId+" "+fechaFin+"</td></tr><tr><th  width= '50%'>DOCUMENTO</th><th width= '50%'>PERIODO</th><th width= '50%'>ARCHIVO</th></tr></table></DIV></button></h2></div>");
				//$("#acordionBase").append("<div class='progress'><div class='progress-bar' role='progressbar'   style='width:100%;height:20px' aria-valuenow='25' aria-valuemin='0' aria-valuemax='100'>100%"+i+"</div><br>/div>");
				
				}else
				{
					$("#accordionResumen").append("<div class='accordion-item' id='acordionBase"+actual+"' name='acordionBase"+actual+"'><h2 class='accordion-header' id='heading"+i+"'> <button class='accordion-button' type='button' data-bs-toggle='collapse' data-bs-target='#collapse"+i+"' aria-expanded='false' aria-controls='collapse"+i+"'> <DIV><table><tr><td colspan='3' width= '800px'>"+docuId+" "+fechaFin+"</td></tr><tr><th  width= '50%'>DOCUMENTO</th><th width= '50%'>PERIODO</th><th width= '50%'>ARCHIVO</th></tr></table></DIV></button></h2> </div>");
				
				}
				}	
				if(tipo=="contenido"){
				
					var path = separado[i].split("|")[4];
					
					if(i==2){
					
					$("#acordionBase"+actual).append("<div id='collapse"+actual+"' class='accordion-collapse collapse show' aria-labelledby='heading"+actual+"' data-bs-parent='#acordionBase"+actual+"'><div class='accordion-body'><div class='table-responsive' id='tableResumen' name='tableResumen'><div class='col-xs-12'><table class='table table-bordered' id='tableResumen'  ><tr><td width= '20%'><a href='./UpdateDocumento.jsp?docuId="+documento+"' target='_blank'>"+docuId+"</td><td width= '20%'>"+cantidadActual+" / "+ fechaFin + "</td><td width= '20%'><a href='"+path+"' target='_blank'>"+docuId+"</td></tr></table></div></div> </div><div>");
				
					}else{
				
				$("#acordionBase"+actual).append("<div id='collapse"+actual+"' class='accordion-collapse collapse ' aria-labelledby='heading"+actual+"' data-bs-parent='#acordionBase"+actual+"'><div class='accordion-body'><div class='table-responsive' id='tableResumen' name='tableResumen'><div class='col-xs-12'><table class='table table-bordered' id='tableResumen'  ><tr><td width= '20%'><a href='./UpdateDocumento.jsp?docuId="+documento+"' target='_blank'>"+docuId+"</td><td width= '20%'>"+cantidadActual+" / "+ fechaFin + "</td><td width= '20%'><a href='"+path+"' target='_blank'>"+docuId+"</td></tr></table></div></div> </div><div>");
				}
				}
				
					
				}	
			}
		});	
		})
});
 

</script>	
  <style>
    .div-1 {
        background-color: #3366CC;
    }
    
    .div-2 {
    	background-color: #ABBAEA;
    }
    
    .div-3 {
    	background-color: #FBD603;
    }
    .H3t
{
 color: #3366CC;
}
</style>

  <head>
  <div class="col-xs-6">
  <div class="div-1"><br></div>
  <br>
	<table>
		<tr>
			<td width="40%"></td>
			<td width="40%"><img src="./MinEducacion.png" width="200px"
				height="40px">
			<td width="10%">
				<div>
					
				</div>
			</td>
		</tr>
	</table>
</div>
  </head>
  <body>
    
    
  <div class="container">
   <div class="row">
  <h3 class="H3t">Herramienta de Apoyo a la supervisi&oacuten MEN</h3>
 	
 	<nav class="navbar navbar-expand-lg navbar-light bg-light">
   <div class="container-fluid">
    <a class="navbar-brand" href="Radicacion.jsp" id="menuRadicacion">Radicaci&oacuten</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
       
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Administraci&oacuten
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="UpdateConvenio.jsp">Actualizar Convenio</a></li>
            <li><a class="dropdown-item" href="AdminConvenio.jsp">Crear Convenios</a></li>
             <li><a class="dropdown-item" href="AdminDocumentosConvenio.jsp">Crear documento Convenio</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="CrearUsuarios.jsp" id="adminusuarios">Administrar usuarios</a></li>
            <li><a class="dropdown-item" href="AdminUsuarioConvenio.jsp" id="adminusuariosa">Asociar convenio usuario</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Resumen
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="ResumenConvenio.jsp">Resumen Convenios</a></li>
            <li><a class="dropdown-item" href="DashBoard.jsp">Graficos</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="Alertas.jsp">Alarmas </a></li>
          </ul>
        </li>
      </ul>
     
    </div>
  </div>
</nav>
 

			<div
				class='<nav class="navbar navbar-expand-lg navbar-light bg-light">'>

			</div>
			<div class="col-6">
     
    </div>
    <div class="col">
     
    </div>
  </div>
  <div class="row">
    <div class="col">
      <h3 class="H3t">Resumen convenio</h3>

		
		  
		 <div>
		
	
		<div class="ui-widget">
		   <label for="Agregar Nombre Convenio" class="form-label">Seleccione Convenio</label>
		  <input id="tags" name="tags" class="form-control">
		</div>
		
		</div>
		<div>
		<br>
			<label>Datos del convenio </label>
			<div class="container">
			  <div class="row">
			    <div class="col" ><strong>Fecha de inicio convenio:</strong></div>
			    <div class="col" id="resumenFechaIni" name="resumenFechaIni"> </div>
			    <div class="w-150"></div>
			    <div class="col"><strong>Fecha de finalización convenio:</strong></div>
			    <div class="col" id="resumenFechaFin" name="resumenFechaFin"> </div>
			    <div class="w-150"></div>
			    <div class="col"><strong>Valor actual del convenio:</strong></div>
			    <div class="col" id="resumenValor" name="resumenValor"> </div>
			  </div>
			</div>
		</div>
		
		<div id="alarmaFecha" name= "alarmaFecha"></div>
		
		<br>
		<label for="Agregar Nombre Convenio" class="form-label" id="avance">Avance total completitud Convenio</label>
		<div class="progress">
		  <div class="progress-bar" role="progressbar" id="progresoTotal" name="progresoTotal"   style="width:100%;height:auto; font-size:20px" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100">0%</div>
		
			<br>
		</div>
		</div>
			<br>	
		<!-- inicia acordion -->
		<div class="accordion" id="accordionResumen">
		 	
		 	 	
		 	</div>
		</div>
		
</div>





    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
  </body>
</html>