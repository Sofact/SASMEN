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
//************************************************************************************************************

 $(document).ready(function(){
	var usuario = '<%=request.getUserPrincipal().getName()%>';
		
		if (usuario == 'admin'){
			document.getElementById("menuRadicacion").style.display = "none";
		}
		if (usuario != 'admin'){
			document.getElementById("menuRadicacion").style.display = "";
			document.getElementById("adminusuarios").style.display = "none";
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


	$('input[name=tags]').on('change', function(){
	
		
		$.ajax({
			type: 'POST',
			url: 'ServletCargaTipoDocAuto',
			data: 'convId='+$('input[name=tags]').val(), 
			statusCode: {
				404: function(){
					alert("Pagina no encontrada");
					},
				500: function(){
					alert("Debe seleccionar un convenio");
					}
				},
			success: function(res){
				var separado = res.split(":");
				
				document.getElementById("tipoDoc").length=0;
				$('select[name=tipoDoc]').append (' <option>Seleccione...</option>')
				for(var i = 0; i< separado.length -1; i++){
					var idTipoProducto = separado[i].split("-")[0];
					var nombreTipoProducto = separado[i].split("-")[1];
					var year = separado[i].split("-")[2];
					$('select[name=tipoDoc]').append('<option value = "'+idTipoProducto +'"> '+ nombreTipoProducto+' '+ year+ '</option>')
				}	
			}
		});	
		})
});
  
  
  
 
//************************************************************************************************************
// CARGA REQUERIMIENTOS DOC
//  FILTRO TIPO DOCUMENTO
// 25/03/2022 BY JHUALC
//************************************************************************************************************

 
$(document).ready(function(){
	$('select[name=tipoDoc]').on('change', function(){	
	var params = {
			idConvenio : $('input[name=tags]').val(),  tidoId  : $('select[name=tipoDoc]').val()
		};
		
		
		$.ajax({
			type: 'GET',
			url: 'ServletCargaRequerimientosAuto',
			data: params,
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
				
				
				//document.getElementById("convenioTipoDocumento").length=0;
				//$('select[name=tipoDoc]').append (' <option>Seleccione...</option>')
				for(var i = 0; i< separado.length -1; i++){
					var idConvenio = separado[i].split("-")[0];
					var tidoId = separado[i].split("-")[1];
					var fecha = separado[i].split("-")[2];
					var valor = separado[i].split("-")[3];
					var comentario = separado[i].split("-")[4];
					var participante = separado[i].split("-")[5];
					var enlace = separado[i].split("-")[6];
					
					
					
					
					
					if (valor == '          ')
					{	
						document.getElementById("acordionValor").style.display = "none";
						
					}else{
						document.getElementById("acordionValor").style.display = "";
					}
					
					if (comentario == '          ')
					{	
						
						document.getElementById("acordionComentario").style.display = "none";
						
					}else{
						document.getElementById("acordionComentario").style.display = "";
					}
					/*
					if (participante == '          ')
					{	
						
						document.getElementById("acordionPrticipante").style.display = "none";
						
					}else{
						document.getElementById("acordionPrticipante").style.display = "none";
					}
					*/
					if (enlace == '          ')
					{	
						
						document.getElementById("acordionEnlace").style.display = "none";
						
					}else{
						document.getElementById("acordionEnlace").style.display = "";
					}
					
					
					//$('select[name=tipoDoc]').append('<option value = "'+idTipoProducto +'"> '+ nombreTipoProducto+ '</option>')
				}	
			}
		});	
		})
});
  
 
 
  //************************************************************************************************************
// AGREGA REGISTROS DE LA TABLA
//  CONVENIO
// 05/10/2018 BY JHUALC
//************************************************************************************************************


 
$(document).ready(function(){
			$('a[id=agregar]').on('click', function(){
			var NombreConvenio = document.getElementById("convNombre").value;
			var fechaConvenio = document.getElementById("datepicker").value;
			var archivo = document.getElementById("inputGroupFile02").value;
		
			
			
			$('table[id=tableResumen]').append('<tr><td width= "10%">'+NombreConvenio+'</td><td width= "15%">'+fechaConvenio+'</td><td width= "15%">'+archivo+'</td></tr>')
			})
});


 

  //************************************************************************************************************
// GUARDA EL FORMULARIO
//  DEL DOCUMENTO
// 01/04/22 BY JHUALC
//************************************************************************************************************


$(document).ready(function(){
	$('a[id=guardar]').on('click', function(){
	
	 
	
	alert("Los datos fueron guardados correctamente, continue con la carga del archivo");
	
	var detalles = [];
	var contador =0;
	
	var convenio= document.getElementById("tags").value;
	var NombreDocumento = $('select[id=tipoDoc] option:selected').text(); 
	var tipoDoc= document.getElementById("tipoDoc").value;
	var fechaIni = document.getElementById("datepicker").value;
	var fechaFin = document.getElementById("datepickerFin").value;
	var valor =  document.getElementById("valorConvenioInput").value;
	
		
			
		
			detalles.push ({
				"convenio" 	: convenio,
				"nombreDoc" 	: NombreDocumento,
				"tipoDoc"		: tipoDoc,
				"fechaIni"		: fechaIni,
				"fechaFin"		: fechaFin,
				"valor"			: valor
				
			});
			
		
		// jsonFactura.detalles.push({pvid: pvmid ,cantidad: cant, tot: total});
		
		
		var jsonProducto = JSON.stringify(eval({
						detallePVM : detalles
						}))
						
		$('#formSubmit').attr('action', "FileUploadServletAuto?data="+jsonProducto);
	/*
	$.ajax({
			type: 'POST',
			url: 'ServletGuardarDocumento',
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
				var separado = response;
				
				//alert ("El resultado del docu_id:::"+ separado)
				
			$('#formSubmit').attr('action', "FileUploadServlet?parametro="+separado);
		
			}
		});
		*/	
		$('#modalResumenFactura').modal('show');
				

	
	})
	
	
});


$("formSubmit").on("submit", function(event){
	alert("El documento fue guardado correctamente");
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

  </div>
  <div class="row">
    <div class="col">
      <h3 class="H3t" >Radicación de documentos</h3>

		
		  
		 <div>
		 <br>
		 
		</div>
		
		<div class="ui-widget">
		   <label for="Agregar Nombre Convenio" class="form-label">Seleccione Convenio</label>
		  <input id="tags" name="tags" class="form-control">
		</div>
		
	
		<br>
		
		 <div>
		  <label for="Agregar tipo documento" class="form-label">Seleccione Convenio</label>
		 <select class="form-select" aria-label="Default select example" id= "tipoDoc" name="tipoDoc">
		  <option selected value="0">Seleccione una opci&oacuten</option>
		</select>
		</div>
		<br>
		<label for="Agregar Nombre Convenio" class="form-label">Fecha documento           .         .      .       .           .                  .</label>
		<input id="datepicker" name="datepicker" width="276" />
		    <script>
		        $('#datepicker').datepicker({
		              format: "dd/mm/yyyy"          
		            
		        });
		    </script>
		    <br>
		    
		     <label for="Fecha fin Documento" class="form-label">Fecha fin del documento               </label>
		<input id="datepickerFin" name="datepickerFin" width="276" />
		    <script>
		        $('#datepickerFin').datepicker({
		             format: "dd/mm/yyyy" 
		        });
		    </script>
		    <br>
			</div>
		   
		
			 <!--	
		<div class="accordion" id="accordionExample">
		  <div class="accordion-item" id="acordionPrticipante" name= "acordionParticipante">
		    <h2 class="accordion-header" id="heading1">
		      <button class="accordion-button" type="button"   data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="false" aria-controls="collapse1">
		        Participantes
		      </button>
		    </h2>
		    
		     
		    <div id="collapse1" class="accordion-collapse collapse show"  aria-labelledby="heading1" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		      	<div class="input-group">
				  <span class="input-group-text">ENTIDAD Y CARGO</span>
				  <input type="text" aria-label="First name" id="nombre" class="form-control">
				  <input type="text" aria-label="Last name" id="apellido" class="form-control">
				  
				</div>
		        	<div class="input-group">
				  <span class="input-group-text">ENTIDAD Y CARGO</span>
				  <input type="text" aria-label="First name" id="nombre" class="form-control">
				  <input type="text" aria-label="Last name" id="apellido" class="form-control">
				   
				</div>	    
		         <div id="agregaPArticipante">
				 <br>
				     
			      </div>  
			      Ingrese nombre, apellido y cargo de los participantes que firman el documento	
		        </div>
		    </div>
		    -->
		  </div>
		 
		   <div class="accordion-item" id="acordionValor" name="acordionValor" >
		    <h2 class="accordion-header" id="headingTwo">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="#collapseThree">
		        Valor
		      </button>
		    </h2>
		    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample3">
		      <div class="accordion-body">
		        <div class="input-group mb-3">
				  <span class="input-group-text">$</span>
				  <input type="text"  id="valorConvenioInput" name="valorConvenioInput"  class="number form-control" aria-label="Amount (to the nearest dollar)">
				  <span class="input-group-text">.00</span>
				 
				</div>
		      </div>
		    </div>
		  </div>
		  <div class="accordion-item" id="acordionEnlace" name="acordionEnlace" >
		    <h2 class="accordion-header" id="headingTwo">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="#collapseFour">
		        Enlace
		      </button>
		    </h2>
		    <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		       	<div class="input-group">
				  <span class="input-group-text">Nombres apellidos y cargo</span>
				  <input type="text" aria-label="First name" id="nombre" class="form-control">
				  <input type="text" aria-label="Last name" id="apellido" class="form-control">
				   <input type="text" aria-label="entidad"  id="cargo" class="form-control">
				</div>
		      </div>
		    </div>
		  </div>
		  <div class="accordion-item" id="acordionComentario" name="acordionComentario">
		    <h2 class="accordion-header" id="headingThree">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="#collapseFive">
		        Comentarios
		      </button>
		    </h2>
		    <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample4">
		      <div class="accordion-body">
		       <div class="mb-3">
				<br>
				  <label for="exampleFormControlTextarea1" class="form-label">Comentarios</label>
				  <textarea class="form-control" id="comentarios" rows="3"></textarea>
				</div>  
		      </div>
		    </div>
		  </div>
		</div>
		<br>
		
		<div class="form-check">
		<br>
		  <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="completo" >
		  <label class="form-check-label" for="exampleRadios1">
		    El documento se encuentra firmado y completo para su carga.
		  </label>
		  <br>
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

<div class="modal fade" id="modalResumenFactura" role="dialog" 	aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">

			

    <div id="upload-1-queue"" class="form-control"></div>
			Por favor antes de cargar el Archivo verifique que cumple todos los requerimientos
			<form action="FileUploadServlet"  class="form-control" method="post" enctype="multipart/form-data" id="formSubmit" name ="formSubmit">
			
			Select File to Upload:<input type="file" name="fileName">
			
			<br>
			<input type="submit" value="Upload" id="enviador">
			
			</form>
					<div class="modal-body">
					</div>
			</div>		
		</div>
		</div>
		
		<script>
			  //************************************************************************************************************
				// fORMATEAR VALOR
				//  DEL DOCUMENTO
				// 12/04/22 BY JHUALC
				//************************************************************************************************************
				
			/*	
				var flag=0;
				var numeroHerramienta=0;
				document.getElementById("valorConvenioInput").onkeyup = function() {myFunction()};

					function myFunction() {
					
					
					if (flag == 0){
					
					var numeroHerramienta = $("#valorConvenioInput").val();
					flag= 1;
					}
					
					alert("ingresa"+ flag+"::"+ numeroHerramienta);
					
					const formatter = new Intl.NumberFormat('es-CO', {
					      style: 'decimal',
					      currency: 'COP',
					      minimumFractionDigits: 0
					    })
					
					alert("Aqui::"+ numeroHerramienta);
					
					var formateado = formatter.format(numeroHerramienta);
				//	$("#valorConvenioInput").text(numeroHerramienta);
					$("#valorConvenioInput").val(formateado);
					
					alert("El valor"+ $("#valorConvenioInput").text());
					
					
					 
					}
				
				
					*/
			// Creamos el evento keyup
				document.querySelectorAll(".number").forEach(el=>el.addEventListener("keyup", numberFormat));
				 
				// Función que formatea el numero
				function numberFormat(e) {
				    if (this.value.trim()=="" || this.value.trim()=="-") {
				        return;
				    }
				 
				    // Obtenemos un array con el numero y los decimales si hay
				    let contenido = this.value.replace(/[^0-9\.]/g, "").split(".");
				 
				    // añadimos los separadores de miles al primer numero del array
				    contenido[0] = contenido[0].length ? new Intl.NumberFormat('en-US').format(parseInt(contenido[0])) : "0";
				 
				    // Juntamos el numero con los decimales si hay decimales
				    let resultado=contenido.length>1 ? contenido.slice(0, 2).join(".") : contenido[0];
				    this.value=this.value[0]=="-" ? "-"+resultado : resultado;
				}		
							
		</script>

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