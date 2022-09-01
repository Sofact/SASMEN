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
// CARGA LISTADO DESTRATEGIAS MARCO
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
	
		$.ajax({
			type: 'GET',
			url: 'ServletCargaConvenio',
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
				
				document.getElementById("formConvenioMarco").length=0;
				$('select[name=formConvenioMarco]').append (' <option>Seleccione...</option>')
				for(var i = 0; i< separado.length -1; i++){
					var idTipoProducto = separado[i].split("-")[0];
					var nombreTipoProducto = separado[i].split("-")[1];
					$('select[name=formConvenioMarco]').append('<option value = "'+idTipoProducto +'"> '+ nombreTipoProducto+ '</option>')
				}	
			}
		});	
});

//************************************************************************************************************
// CARGA LISTADO DE ENTIDADES
// 25/03/2022 BY JHUALC
//************************************************************************************************************
 
  
$(document).ready(function(){
	
		$.ajax({
			type: 'GET',
			url: 'ServletCargaEntidad',
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
				
				document.getElementById("entidades").length=0;
				$('select[name=entidades]').append (' <option>Seleccione...</option>')
				for(var i = 0; i< separado.length -1; i++){
					var idEntidad = separado[i].split("-")[0];
					var nombreEntidad = separado[i].split("-")[1];
					$('select[name=entidades]').append('<option value = "'+idEntidad +'"> '+ nombreEntidad+ '</option>')
				}	
			}
		});	
});
 
 
  //************************************************************************************************************
// AGREGA PARTICIPANTES
//  
// 25/03/2022 BY JHUALC
//************************************************************************************************************


 
$(document).ready(function(){
			//$('a[id=agregarParticipante]').on('click', function(){
			//	$('select[name=entidades]').on('change', function(){	
				//	var entidad = [idprovincia.selectedIndex].value; $('select[name=entidades]').text;
					var select = document.getElementById('entidades');
					var entidad = null;
					var id = null;
						select.addEventListener('change',
						  function(){
						    var selectedOption = this.options[select.selectedIndex];
						     entidad = selectedOption.text ;
						     id = selectedOption.value ;
						 
												
			
			
			
			
			$('table[id=tableResumenParticipantes]').append('<tr><td  style= "display:none">'+id+'</td><td width= "10%" >'+entidad+'</td></tr>')
			})
	//		});
});

  //************************************************************************************************************
// AGREGA PARTICIPANTES
//  
// 25/03/2022 BY JHUALC
//************************************************************************************************************



$(document).ready(function(){
	$('a[id=guardar]').on('click', function(){
	
	var usuario = '<%=request.getUserPrincipal().getName()%>';
	
	alert("Convenio gaurdado correctamente")
	// $('#modalResumenFactura').modal('show');

	
	
	var numeroConvenio = document.getElementById("numeroConvenio").value;
	var nombreConvenio = document.getElementById("nombreConvenio").value;
	var year = document.getElementById("year").value;
	var fechaInicio = document.getElementById("datepicker").value;
	var fechaFin = document.getElementById("datepickerFin").value;
	var valorConvenio = document.getElementById("valor").value;
	var comentario = document.getElementById("comentarios").value;
	//var form = document.getElementById("cargaArchivo");
	
	
	var detallesEntidad = [];
	var contador1 =0;

   $('#tableResumenParticipantes tr').each(function (index){
   
   		var entidad;
   		var idEntidad;
			$(this).children("td").each(function(index2){
		
		
			switch(index2){
				case 0:
					idEntidad= $(this).text();
					
					break;
				case 1:
					entidad= $(this).text();
					
					break;
			}
		})
	
   	if (contador1 == 0){
			contador1++;
		}else{
			detallesEntidad.push ({
				"idEntidad" : idEntidad,
				"entidad" 	: entidad
				
			});
			}
   })
	
	var detalles = [];
	var contador =1;
	
		
		if (contador == 0){
			contador++;
		}else{
			detalles.push ({
				"numeroConvenio" 	: numeroConvenio,
				"nombreConvenio"	: nombreConvenio,
				"year"		: year,
				"fechaInicio"		: fechaInicio,
				"fechaFin"		: fechaFin,
				"valorConvenio"	: valorConvenio,
				"comentario" : comentario,
				"entidades": detallesEntidad, 
				"usuario" : usuario
				
			});
			}
			
		// jsonFactura.detalles.push({pvid: pvmid ,cantidad: cant, tot: total});
		//})
		
		var jsonProducto = JSON.stringify(eval({
						detallePVM : detalles
						}))
						
		
	
	$.ajax({
			type: 'POST',
			url: 'ServletAdminConvenio',
			dataType: 'json',
			data: jsonProducto , 
			statusCode: {
				404: function(){
					alert("pagina no encontrada ServletAdminConvenio");
				},
				500: function(){
					alert("Verifique si esta intentando crear un Tipo de documento duplicado para el convenio");
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
// CARGA CONVENIOS MARCO
// FILTROS: CONVENIOS MARCO 
// 23/03/2022 BY JHUALC
//************************************************************************************************************
$(document).ready(function(){
	$('select[name=marca]').on('change', function(){
		$.ajax({
			type: 'GET',
			url: 'TipoProductoServlet',
			data: 'marca='+$('select[name=marca]').val(),
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
				
				document.getElementById("tipoProd").length=0;
				$('select[name=tipoProd]').append (' <option>Seleccione...</option>')
				for(var i = 0; i< separado.length -1; i++){
					var idTipoProducto = separado[i].split("-")[0];
					var nombreTipoProducto = separado[i].split("-")[1];
					$('select[name=tipoProd]').append('<option value = "'+idTipoProducto +'"> '+ nombreTipoProducto+ '</option>')
				}	
			}			
		});		
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
 
 
 </div>
 	
			<div
				class='<nav class="navbar navbar-expand-lg navbar-light bg-light">'>

			</div>
			<div class="col-6">
     
    </div>

  
  <div class="row">
    <div class="col">
      <h3 class="H3t">Administraci&oacuten de Convenios</h3>
		<label for="Agregar Nombre Convenio" class="form-label">Seleccione Estrat&eacutegia Marco</label>
			<select class="form-control" id="formConvenioMarco" name="formConvenioMarco">
						<option value="">Seleccione ...</option>
		
						</select>
					
						
		
		<br>
		
		
		      <div class="accordion-body">
		      	<div class="input-group">
				  <span class="input-group-text">CONVENIO/CONTRATO</span>
				  <input type="text" aria-label="numeroConvenio" id="numeroConvenio" class="form-control">
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
		<br>
		<div>
			<span class="input-group-text">NOMBRE CONVENIO</span>
				  <input type="text" aria-label="nombreConvenio" id="nombreConvenio" class="form-control">
		</div>
		  <div >
		<br>
			<label for="Fecha inicio Convenio" class="form-label">Fecha inicio del Convenio</label>
		<input id="datepicker" width="276" />
		    <script>
		        $('#datepicker').datepicker({
		                format: "dd/mm/yyyy"    
		        });
		    </script>
		    
		    <label for="Fecha fin Convenio" class="form-label">Fecha fin del Convenio</label>
		<input id="datepickerFin" width="276" />
		    <script>
		        $('#datepickerFin').datepicker({
		                format: "dd/mm/yyyy"    
		        });
		    </script>
		    <br>
			</div>
		
		<div class="accordion" id="accordionExample">
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingOne">
		      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
		        Entidades Participantes
		      </button>
		    </h2>
		    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		      	 <div>
				  <label for="Entidades" class="form-label">Seleccione las Entidades participantes</label>
				 <select class="form-select" aria-label="Default select example" id= "entidades" name="entidades">
				  

				</select>
				</div>	    
		         <div id="agregaPArticipante">
				 <br>
				     <a id="agregarParticipante" name="agregarParticipante"   class="btn btn-outline-secondary" >
			          <div class="glyphicon glyphicon-plus"  ></div> Agregar participante 
			         </a>
			      </div>  
			      <div class="table-responsive" id="tableParticipantes" name="tableParticipantes">
    	 <BR>
            <div class="col-xs-12">
               <table class="table table-bordered" id="tableResumenParticipantes"  >
                  <thead class="thead">
                     <tr>
                        <th width= "10%">
                          ENTIDAD PARTICIPANTE
                        </th>
                        

                     </tr>
                  </thead>
               </table>
            </div>
         </div>
		        </div>
		    </div>
		  </div>
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingTwo">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
		        Valor Convenio
		      </button>
		    </h2>
		    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		        <div class="input-group mb-3">
				   <span class="input-group-text">$</span>
				  <input type="text"  id="valor" name="valor"  class="number form-control" aria-label="Amount (to the nearest dollar)">
				  <span class="input-group-text">.00</span>
				</div>
		      </div>
		    </div>
		  </div>
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingThree">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
		        Comentarios
		      </button>
		    </h2>
		    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
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
		  <input class="form-check-input" type="checkbox" name="checkValida" id="checkValida" value="completo" >
		  <label class="form-check-label" for="exampleRadios1">
		    El documento se encuentra completo para su carga.
		  </label>
		  <br>
		</div>
	<br>
		
         <br>
		 <div id="guardarRegistro" disabled>
		 <br>
		     <a id="guardar" name="guardar"   class="btn btn-outline-secondary" style="display:none">
	          Guardar 
	         </a>
	      </div>  
			<div class="&lt;">


				<div class="col"></div>
			</div>
		</div>	
		</div>
		</div>
		
</div>

<div class="modal fade" id="modalResumenFactura" role="dialog" 	aria-labelledby="exampleModalLabel" aria-hidden="false">
		<div class="modal-dialog" role="document">
			<div class="modal-content">

				Por favor antes de cargar el Archivo verifique que cumple todos los requerimientos dispuestos en el reglamento operativo del convenio

<br>
    <div id="upload-1-queue"">
    <br>
    </div>

			<form action="FileUploadServlet" method="post" enctype="multipart/form-data">
			Select File to Upload:<input type="file" name="fileName">
			<br>
			<input type="submit" value="Upload">
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