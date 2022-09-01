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
  
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css" type="text/css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js" type="text/javascript"></script>
    <title>SASMEN</title>
  </head>
  
 <script>
 
 
//************************************************************************************************************
// CARGA LISTADO DE CONVENIOS
//  
// 25/03/2022 BY JHUALC
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
					alert("Debe seleccionar un convenio");
					}
				},
			success: function(res){
			
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
					$('select[name=nombreConvenio]').append('<option value ="'+idTipoProducto +'">'+ nombreTipoProducto+ '</option>')
				}	
			}
		});	
});
 
 
 
//************************************************************************************************************
// CARGA LISTADO TIPOS DOCUMENTO
//  
// 25/03/2022 BY JHUALC
//************************************************************************************************************

 
$(document).ready(function(){
	
		$.ajax({
			type: 'GET',
			url: 'ServletCargaTipoDoc',
			data: 'i= ',
			statusCode: {
				404: function(){
					alert("Pagina no encontrada");
					},
				500: function(){
					alert("Error en servidor");
					}
				},
			success: function(res){
				var separado = res.split(":");
				
				document.getElementById("tipoDoc").length=0;
				$('select[name=tipoDoc]').append (' <option>Seleccione...</option>')
				for(var i = 0; i< separado.length -1; i++){
					var idTipoProducto = separado[i].split("-")[0];
					var nombreTipoProducto = separado[i].split("-")[1];
					$('select[name=tipoDoc]').append('<option value = "'+idTipoProducto +'"> '+ nombreTipoProducto+ '</option>')
				}	
			}
		});	
});
 
 
 
  //************************************************************************************************************
// ELIMINA REGISTROS DE LA TABLA
//  IVA  SUBTOTAL Y TOTAL
// 05/10/2018 BY JHUALC
//************************************************************************************************************


 
$(document).ready(function(){
			$('a[id=agregar]').on('click', function(){
			var tipoDocumento = document.getElementById("tipoDoc").value;
			var periodicidad = document.getElementById("periodicidad").value;
			var convenio = document.getElementById("tags").value;
			var yer = document.getElementById("year").value;
			
			
			if( $('#checkFirma').is(':checked') ) {
				   var firma = "X";
				}else {var firma = "";}
			if( $('#checkFecha').is(':checked') ) {
				   var fecha = "X";
				}else {var fecha = "";}
			if( $('#checkParticipa').is(':checked') ) {
				   var participa = "X";
				}else {var participa = "";}
			if( $('#checkValor').is(':checked') ) {
				   var valor = "X";
				}else {var valor = "";}
			if( $('#checkComentario').is(':checked') ) {
				   var comentario = "X";
				}else {var comentario = "";}
			if( $('#checkEnlace').is(':checked') ) {
				   var enlace = "X";
				}else {var enlace = "";}
			
			
			$('table[id=tableResumen]').append('<tr><td width= "10%">'+convenio+'</td><td width= "15%">'+tipoDocumento+'</td><td width= "15%">'+periodicidad+'</td><td width= "2%">'+firma+'</td><td width= "2%">'+fecha+'</td><td width= "2%">'+participa+'</td><td width= "2%">'+valor+'</td><td width= "2%">'+comentario+'</td><td width= "2%">'+enlace+'</td><td width= "2%">'+yer+'</td></tr>')
			})
});

//************************************************************************************************************
// GUARDAR
//  IVA  SUBTOTAL Y TOTAL
// 30/03/22 BY JHUALC
//************************************************************************************************************



$(document).ready(function(){
	$('a[id=guardar]').on('click', function(){
	
	alert("Tipo documento cargado correctamente")
	
	var detalles = [];
	var contador =0;
	
	$('#tableResumen tr').each(function (index){
		var nombre, tipoDocumento, periodicidad;
		
		$(this).children("td").each(function(index2){
		
		
			switch(index2){
				case 0:
					idConvenio= $(this).text();
					
					break;
				case 1:
					tipoDocumento= $(this).text();
					break;
				case 2:
					periodicidad= $(this).text();
					break;
				case 3:
					firma= $(this).text();
					break;
				case 4:
					fecha= $(this).text();
					break;
				case 5:
					participantes= $(this).text();
					break;
				case 6:
					valor= $(this).text();
					break;
				case 7:
					comentarios= $(this).text();
					break;
				case 8:
					enlace= $(this).text();
					break;
				case 9:
					year= $(this).text();
					break;
				
			}
			
	
		})
		if (contador == 0){
			contador++;
		}else{
			detalles.push ({
				"idConvenio" 	: idConvenio,
				"tipoDocumento"		: tipoDocumento,
				"periodicidad"		: periodicidad,
				"firma"				: firma,
				"fecha"				: fecha,
				"participantes"		: participantes,
				"valor"				: valor,
				"comentarios"		: comentarios,
				"enlace"			: enlace,
				"year"				: year
				
			});
			}
			
		// jsonFactura.detalles.push({pvid: pvmid ,cantidad: cant, tot: total});
		})
		
		var jsonProducto = JSON.stringify(eval({
						detallePVM : detalles
						}))
						
		
	
	$.ajax({
			type: 'POST',
			url: 'ServletAdminDocumentosConvenioAuto',
			dataType: 'json',
			data: jsonProducto , 
			statusCode: {
				404: function(){
					alert("pagina no encontrada ServletAdminConvenio");
				},
				500: function(){
					alert("!!Error: está intentando insertar un tipo de documento duplicado para este convenio!!");
				}
			},
			success: function(response){
				var separado = response.split(":");
				
				
				alert ("El resultado del docu_id:::"+ separado);
				window.location.href = "./Demografico.jsp?formId="+separado;
				
			}
		});
	
	})
});

$(document).ready(function(){
$('a[id=guardar]').on('click', function(){

	
		window.location.href = "./AdminDocumentosConvenio.jsp";
});
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
      <h3 class="H3t">Administraci&oacuten Documentos Convenio</h3>

		
		
		<div class="ui-widget">
		   <label for="Agregar Nombre Convenio" class="form-label">Seleccione Convenio</label>
		  <input id="tags" name="tags" class="form-control">
		</div>
		<br>
		
		  
				  
		  <div>
		  <label for="Agregar tipo documento" class="form-label">Tipo Documento</label>
		 <select class="form-select" aria-label="Default select example" id= "tipoDoc" name= "tipoDoc">
		  <option selected>Seleccione una opci&oacuten</option>
		
		</select>
		</div>
		<br>
		<label for="Agregar tipo documento" class="form-label">Año</label>
			<div class="input-group">
			
				  <select class="form-select" aria-label="Default select example" id= "year" name= "year">
				  <option selected>Seleccione una opci&oacuten</option>
				  <option value="2005">2005</option>
				  <option value="2006">2006</option>
				  <option value="2007">2007</option>
				  <option value="2008">2008</option>
				  <option value="2009">2009</option>
				  <option value="2010">2010</option>
				  <option value="2011">2011</option>
				  <option value="2012">2012</option>
				  <option value="2013">2013</option>
				  <option value="2014">2014</option>
				  <option value="2015">2015</option>
				  <option value="2016">2016</option>
				  <option value="2017">2017</option>
				  <option value="2018">2018</option>
				  <option value="2019">2019</option>
				  <option value="2020">2020</option>
				  <option value="2021">2021</option>
				  <option value="2022">2022</option>
				  <option value="2023">2023</option>
				  <option value="2024">2024</option>
				  <option value="2025">2025</option>
				  <option value="2025">2025</option>

				</select>
					
		</div>
		<div>
		<br>
		  <label for="Agregar tipo documento" class="form-label">Periodicidad</label>
		 <select class="form-select" aria-label="Default select example" id = "periodicidad">
		  <option selected>Seleccione una opci&oacuten</option>
		  <option value="aperiodica">APERIODICA</option>
		  <option value="unica">UNICA</option>
		  <option value="anual">ANUAL</option>
		  <option value="semestral">SEMESTRAL</option>
		  <option value="trimestral">TRIMESTRAL</option>
		  <option value="mensual">MENSUAL</option>
		</select>
		</div><br>
		<div class="form-check">
		  <input class="form-check-input" type="checkbox" value="firma" id="checkFirma">
		  <label class="form-check-label" for="flexCheckDefault">
		    Requiere firma
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input" type="checkbox" value="fecha" id="checkFecha">
		  <label class="form-check-label" for="flexCheckDefault">
		    Requiere fecha
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input" type="checkbox" value="participantes" id="checkParticipa">
		  <label class="form-check-label" for="flexCheckDefault">
		    Requiere participantes
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input" type="checkbox" value="valor" id="checkValor">
		  <label class="form-check-label" for="flexCheckDefault">
		    Requiere valor
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input" type="checkbox" value="comentario" id="checkComentario">
		  <label class="form-check-label" for="flexCheckDefault">
		    Requiere comentarios
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input" type="checkbox" value="enlace" id="checkEnlace">
		  <label class="form-check-label" for="flexCheckDefault">
		    Requiere enlace
		  </label>
		</div>
		<br>
		 <div id="agregar_registro">
		     <a id="agregar" name="agregar"   class="btn btn-outline-secondary" >
	          <div class="glyphicon glyphicon-plus"  ></div> Agregar 
	         </a>
	      </div>  
		 

       <br>
    </div>
    	 <div class="table-responsive" id="tableResumen" name="tableResumen">
    	 <BR>
            <div class="col-xs-12">
               <table class="table table-bordered" id="tableResumen"  >
                  <thead class="thead">
                     <tr>
                        <th width= "10%">
                           CONVENIO
                        </th>
                        <th width= "10%">
                           TIPO DOCUMENTO
                        </th>
                         <th width= "10%">
                           PERIODICIDAD
                        </th>
                         <th width= "2%">
                          FIRMA
                        </th>
                         <th width= "2%">
                           FECHA
                        </th>
                         <th width= "2%">
                           PARTICIPANTES
                        </th>
                         <th width= "2%">
                           VALOR
                        </th>
                         <th width= "2%">
                           COMENTARIOS
                        </th>
						 <th width= "2%">
                           ENLACE
                        </th>
                         <th width= "2%">
                           AÑO
                        </th>
                     </tr>
                  </thead>
               </table>
            </div>
         </div>
         
         <br>
		 <div id="guardarRegistro">
		     <a id="guardar" name="guardar"   class="btn btn-outline-secondary" >
	          <div class="glyphicon glyphicon-plus"  ></div> Guardar 
	         </a>
	      </div>  
			<div class="&lt;">


				<div class="col"></div>
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