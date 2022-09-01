<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <!-- Required meta tags -->| 
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

     <!-- Bootstrap CSS -->
    

    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
  
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css" type="text/css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js" type="text/javascript"></script>
   <title>SASMEN</title>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.SgdConvenioMarco"%>
<%@page import="model.SgdConvenio"%>
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
 
  //************************************************************************************************************
// ACTUALIZAR informacion DOCUMENTOS PARTICIPANTES
//  
// 25/03/2022 BY JHUALC
//************************************************************************************************************



$(document).ready(function(){
	$('a[id=guardar]').on('click', function(){
	
	
	// $('#modalResumenFactura').modal('show');

	
	
	
	var fechaInicio = document.getElementById("datepicker").value;
	var fechaFin = document.getElementById("datepickerFin").value;
	var valorDocumento = document.getElementById("valor").value;

	var docuId = document.getElementById("oculta").value;
	//var form = document.getElementById("cargaArchivo");
	
	

	
	var detalles = [];
	var contador =1;
	
		
		if (contador == 0){
			contador++;
		}else{
			detalles.push ({
				
				"fechaInicio"		: fechaInicio,
				"fechaFin"		: fechaFin,
				"valorDocumento"	: valorDocumento,
			
				"docuId": docuId
				
			});
			}
			
		// jsonFactura.detalles.push({pvid: pvmid ,cantidad: cant, tot: total});
		//})
		
		var jsonProducto = JSON.stringify(eval({
						detallePVM : detalles
						}))
						
		
	
	$.ajax({
			type: 'POST',
			url: 'ServletUpdateDocumento',
			dataType: 'json',
			data: jsonProducto , 
			statusCode: {
				404: function(){
					alert("pagina no encontrada ServletAdminConvenio");
				},
				500: function(){
					alert("Verifique si esta intentando crear un convenio Duplicado");
				}
			},
			success: function(response){
				var separado = response.split(":");
				
				
			}
		});
	
	})
});


 $(document).ready(function(){
	$('input[id=checkValida]').on('click', function(){

    if($('#checkValida').prop('checked') ) {
		document.getElementById("guardar").style.display = "";
	}
	
});
});

//************************************************************************************************************
// CARGA INFORMACION DOCUMENTOS
// FILTROS: DOCUID  
// 23/03/2022 BY JHUALC
//************************************************************************************************************
$(document).ready(function(){
		const valores = window.location.search;
		const urlParams = new URLSearchParams(valores);
		var docuId = urlParams.get('docuId');
		
		$.ajax({
			type: 'POST',
			url: 'ServletCargaInfoDocumento',
			data: 'docuId='+docuId,
			statusCode: {
				404: function(){
					alert("Pagina no encontrada");
				},
				500: function(){
					alert("Continue con su trabajo");
				}
			},
			success: function(res){
				var separado = res.split("||");
					
				
				for(var i = 0; i< separado.length -1; i++){
					var nombre = separado[i].split("|")[0];
					var year = separado[i].split("|")[1];
					var fechaIni = separado[i].split("|")[2];
					var fechaFin = separado[i].split("|")[3];
					var path = separado[i].split("|")[4];
					var valor = separado[i].split("|")[5];
					
				
					
					document.getElementById("tipoDocumento").value = nombre;
					
					document.getElementById("datepicker").value = fechaIni;
					document.getElementById("datepickerFin").value = fechaFin;
					document.getElementById("valor").value = valor;
					document.getElementById("path").value = path;
					document.getElementById("oculta").value = docuId;
					
					var entidades = res.split("|");
				
					for (var x=6; x<entidades.length -2; x++){
					var entidad = separado[0].split("|")[x];
				
					$('table[id=tableResumenParticipantes]').append('<tr><td width= "10%">'+entidad+'</td></tr>')
					}
				}	
			}			
		});		

});


  //************************************************************************************************************
// eliminar DOCUMENTOS 
//  
// 25/03/2022 BY JHUALC
//************************************************************************************************************



$(document).ready(function(){
	$('a[id=eliminar]').on('click', function(){
	

	
	var docuId = document.getElementById("oculta").value;
	
$.ajax({
			type: 'POST',
			url: 'ServletDeleteDocumento',
			data: 'docuId='+docuId,
			statusCode: {
				404: function(){
					alert("Pagina no encontrada");
				},
				500: function(){
					alert("Continue con su trabajo");
				}
			},
			success: function(res){
			
				alert("El documento fue eliminado correctamente");
				location.reload(true);
			}			
		});	

	
	
	})
});



  //************************************************************************************************************
// actualizar DOCUMENTOS 
//  
// 25/03/2022 BY JHUALC
//************************************************************************************************************



$(document).ready(function(){
	$('a[id=modificar]').on('click', function(){
	
	
	
	var detalles = [];
	var contador =0;
	
	var docuId = document.getElementById("oculta").value;
	
	
		
	
	$('#formSubmit').attr('action', "FileUpdateServlet?docuId="+docuId);
	 $('#modalResumenFactura').modal('show');

	
	
	})
});



//************************************************************************************************************
//****FIN CARGA CONVENIOS MARCO
//************************************************************************************************************


</script>	


<script>

/*
    $(function() {
        $('#upload-form').ajaxForm({
            success: function(msg) {
                alert("File has been uploaded successfully");
            },
            error: function(msg) {
                $("#upload-error").text("Couldn't upload file");
            }
        });
    });
    */
 //************************************************************************************************************
//****FIN CARGA archivos
//************************************************************************************************************
    
    
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
  <h3 class="H3">Herramienta de Apoyo a la supervisi&oacuten MEN</h3>

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
 
 
 </div>
 	
			<div
				class='<nav class="navbar navbar-expand-lg navbar-light bg-light">'>

			</div>
			<div class="col-6">
     
    </div>

  
  <div class="row">
    <div class="col">
      <h3 class="H3t">Actualizaci&oacuten de Documentos</h3>
			
						
		
		<br>
		<label hidden id='oculta' name= 'oculta'></label>
		
		     
		<div class="input-group">
			<span class="input-group-text">TIPO DOCUMENTO</span>
			<input type="text" aria-label="tipoDocumento" id="tipoDocumento" name ="tipoDocumento" class="form-control" readonly>			
		</div>
		  <br>
		<div class="input-group">
			<span class="input-group-text">VALOR DOCUMENTO</span>
			<input type="text" aria-label="valor" id="valor" name ="valor" class="number form-control">
		 </div >
		 <br>
		 <div class="input-group">
			<span class="input-group-text">NOMBRE ARCHIVO</span>
			<input type="text" aria-label="path" id="path" name ="path" class="form-control" readonly>
		 </div >
		  
		<br>
		 <div class="input-group">
		<span class="input-group-text">FECHA INICIO DEL DOCUMENTO</span>
		<input id="datepicker" width="276" class="form-control">
		    <script>
		        $('#datepicker').datepicker({
		                format: "dd/mm/yyyy"    
		        });
		    </script>
		  </div>  
		  <br>
		    <div class="input-group">
				<span class="input-group-text">FECHA FIN DEL DOCUMENTO</span>
				<input id="datepickerFin" width="276" class="form-control">
				    <script>
				        $('#datepickerFin').datepicker({
				                format: "dd/mm/yyyy"    
				        });
				    </script>
		    </div>
		    <br>
			</div>
		
		
		</div>
		
		 <div class="form-check">
		
		
		
	
         <br>
		 <div id="guardarRegistro" disabled>
		
		     <a id="guardar" name="guardar"   class="btn btn-outline-secondary" >
	          Actualizar 
	         </a>
	         <a id="eliminar" name="eliminar"   class="btn btn-outline-secondary">
	          Eliminar Registro 
	         </a>
	         <a id="modificar" name="modificar"   class="btn btn-outline-secondary">
	          Modificar documento cargado 
	         </a>
	      </div>  
			<div class="&lt;">


				<div class="col"></div>
			</div>
		</div>	
		</div>
		</div>
		
</div>

<div class="modal fade" id="modalResumenFactura" role="dialog" 	aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">

			

    <div id="upload-1-queue"" class="form-control"></div>
			Por favor antes de cargar el Archivo verifique que cumple todos los requerimientos
			<form action="FileUpdateServlet"  class="form-control" method="post" enctype="multipart/form-data" id="formSubmit" name ="formSubmit">
			
			Select File to Upload:<input type="file" name="fileName">
			
			<br>
			<input type="submit" value="Upload" id="enviador">
			
			</form>
					<div class="modal-body">
					</div>
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
  
  <script>
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
  
  
</html>