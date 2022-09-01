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
// CARGA LISTADO DE usuarios
//  
//************************************************************************************************************

 
$(document).ready(function(){
	
		$.ajax({
			type: 'POST',
			url: 'ServletCargaUsuarios',
			data: 'i=',
			statusCode: {
				404: function(){
					alert("Pagina no encontrada");
					},
				500: function(){
					alert("Error en servidor");
					}
				},
			success: function(res){
				var separado = res.split("||");
				
				
				document.getElementById("usuario").length=0;
				$('select[name=usuario]').append (' <option>Seleccione...</option>')
				for(var i = 0; i< separado.length  ; i++){
					var idTipoProducto = separado[i].split("|")[0];
					var nombreTipoProducto = separado[i].split("|")[1];
					$('select[name=usuario]').append('<option value ="'+nombreTipoProducto +'">'+ idTipoProducto+ '</option>')
				}	
			}
		});	
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
	
	 	
	alert("Documento Guardado Correctamente");
	
	var detalles = [];
	var contador =0;
	
	var convenio= document.getElementById("tags").value;
	var usuario = $('select[id=usuario] option:selected').text(); 
	
	
		
		
			detalles.push ({
				"convenio" 	: convenio,
				"usuario" 	: usuario
				
				
			});
			
		
		// jsonFactura.detalles.push({pvid: pvmid ,cantidad: cant, tot: total});
		
		
		var jsonProducto = JSON.stringify(eval({
						detallePVM : detalles
						}))
						
		
	
	$.ajax({
			type: 'POST',
			url: 'ServletGuardarUsuarioConvenio',
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
            <li><a class="dropdown-item" href="AdminUsuarioConvenio.jsp" id="adminusuarios">Asociar convenio usuario</a></li>
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
      <h3 class="H3t" >Asociación Usuarios Convenios</h3>

		
		  
		 <div class="ui-widget">
		   <label for="Agregar Nombre Convenio" class="form-label">Seleccione Convenio</label>
		  <input id="tags" name="tags" class="form-control">
		</div>
		
		 <div>
		 <br>
		  <label for="Agregar usuario" class="form-label">Seleccione Usuario</label>
		 <select class="form-select" aria-label="Default select example" id= "usuario" name="usuario">
		  <option selected value="0">Seleccione una opci&oacuten</option>
		 
		</select>
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