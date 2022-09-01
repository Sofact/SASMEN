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
     <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.rawgit.com/shabuninil/fileup/master/src/fileup.min.css" rel="stylesheet">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.rawgit.com/shabuninil/fileup/master/src/fileup.min.js"></script>
    <title>SASMEN</title>
  </head>
  
 <script>
 
  //************************************************************************************************************
// ELIMINA REGISTROS DE LA TABLA
//  IVA  SUBTOTAL Y TOTAL
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
	
		$.ajax({
			type: 'GET',
			url: 'ServletCargaConvenioDerivado',
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
	$('select[name=nombreConvenio]').on('change', function(){
	
	$('div[name=resumenFechaIni]').empty();
	$('div[name=resumenFechaFin]').empty();
	$('div[name=resumenValor]').empty();
		$.ajax({
			type: 'POST',
			url: 'ServletCargaDatosConvenio',
			data: 'convenio='+$('select[name=nombreConvenio]').val(),
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
				$('div[name=resumenFechaIni]').append(separado[0].split("|")[0]);
				$('div[name=resumenFechaFin]').append(separado[0].split("|")[3]);
				$('div[name=resumenValor]').append(separado[0].split("|")[2]);
				
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
//  agrega REGISTROS DE LA TABLA
//  IVA  SUBTOTAL Y TOTAL
// 19/04/2022 BY JHUALC
//************************************************************************************************************


 

$(document).ready(function(){
	$('select[name=nombreConvenio]').on('change', function(){
	$('table[id=tableResumen]').empty();
		$.ajax({
			type: 'POST',
			url: 'ServletCargaMAtrizResumen',
			data: 'convenio='+$('select[name=nombreConvenio]').val(),
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
			
				for(var i = 0; i< separado.length -1; i++){
					var nombre = separado[i].split("|")[0];
					var fechaIni = separado[i].split("|")[1];
					var fechaFin = separado[i].split("|")[2];
					var valor = separado[i].split("|")[3];
					var documento = separado[i].split("|")[4];
					var consecutivo = separado[i].split("|")[5];
					var year = separado[i].split("|")[6];
					var mes = separado[i].split("|")[7];
					var periodicidad = separado[i].split("|")[8];
					
					$('table[id=tableResumen]').append('<tr><td width= "10%">'+nombre+'</td><td width= "15%">'+fechaIni+'</td><td width= "15%">'+fechaFin+'</td><td width= "2%">'+valor+'</td><td width= "2%">'+documento+'</td><td width= "2%">'+consecutivo+'</td><td width= "2%">'+year+'</td><td width= "2%">'+mes+'</td><td width= "2%">'+periodicidad+'</td></tr>')

				}	
				
			}
		});	
			
	})
});

$(document).ready(function () {
  $('#tableResumen').DataTable();
  $('.dataTables_length').addClass('bs-select');
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
    <a class="navbar-brand" href="Radicacion.jsp">Radicaci&oacuten</a>
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
            <li><a class="dropdown-item" href="ConvenioMarco.jsp">Crear Convenio Marco</a></li>
            <li><a class="dropdown-item" href="AdminConvenio.jsp">Crear Convenios</a></li>
             <li><a class="dropdown-item" href="AdminDocumentosConvenio.jsp">Crear documento Convenio</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Caracteristicas Documento</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Dashboard
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="ResumenConvenio.jsp">DashBoard</a></li>
            <li><a class="dropdown-item" href="DashBoard.jsp">Graficos</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="BasesCuantitativas.jsp">Bases cuantitativas</a></li>
            <li><a class="dropdown-item" href="MatrizResumen.jsp">Resumen Documentaci&oacuten </a></li>
          </ul>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
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
		 <br>
		  <label for="Agregar Nombre Convenio" class="form-label">Seleccione Convenio</label>
		 <select class="form-select" aria-label="Default select example" id= "nombreConvenio" name="nombreConvenio">
		 
		</select>
		</div>
		
		
		<br>
		<label for="Agregar Nombre Convenio" class="form-label" id="avance">Resumen documentos cargados</label>
		
		<table id="tableResumen" name="tableResumen"  class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
		  <thead>
		    <tr>
		      <th class="th-sm">Nombre
		
		      </th>
		      <th class="th-sm">Fecha Inicio
		
		      </th>
		      <th class="th-sm">Fecha fin
		
		      </th>
		      <th class="th-sm">Valor Convenio
		
		      </th>
		      <th class="th-sm">Nombre documento
		
		      </th>
		      <th class="th-sm">Consecutivo
		
		      </th>
		       <th class="th-sm">year
		
		      </th>
		       <th class="th-sm">mes
		
		      </th>
		       <th class="th-sm">periodicidad
		
		      </th>
		    </tr>
		  </thead>
		 </table>	
		 	 	
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