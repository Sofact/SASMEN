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
 
 
 // CREAR USUARIO---------------------------------------------
 //-------------------------
 //-----------------------------------------------------------
 
$(document).ready(function(){
	$('a[id=guardar]').on('click', function(){
	
	 
	
	alert("Documento Guardado Correctamente");
	
	var detalles = [];
	var contador =0;
	
	var usuario= document.getElementById("usuario").value;
	var password= document.getElementById("password").value;
	var perfil = $('select[id=perfil] option:selected').text(); 
				
		
			detalles.push ({
				"usuario" 	: usuario,
				"password" 	: password,
				"perfil"		: perfil			
				
			});
			
		

		var jsonProducto = JSON.stringify(eval({
						detallePVM : detalles
						}))
						
	$.ajax({
			type: 'POST',
			url: 'ServletGuardarUsuario',
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
				location.reload(true);
	
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

  </div>
 	
			<div
				class='<nav class="navbar navbar-expand-lg navbar-light bg-light">'>

			</div>
			<div class="col-6">
     
    </div>

  
  <div class="row">
    <div class="col">
      <h3 class="H3t">Administración de usuarios</h3>
			
						
		
		<br>
		<label hidden id='oculta' name= 'oculta'></label>
		
		     
		<div class="input-group">
			<span class="input-group-text">USUARIO</span>
			<input type="text" aria-label="usuario" id="usuario" name ="usuario" class="form-control" >			
		</div>
		  <br>
		<div class="input-group">
			<span class="input-group-text">PASSWORD</span>
			<input type="text" aria-label="password" id="password" name ="password" class="number form-control">
		 </div >
		
		   <br>
		  	<div class="input-group">
				  <span class="input-group-text">PERFIL</span>
				  <select class="form-select" aria-label="Default select example" id= "perfil" name= "perfil">
				  <option selected>Seleccione una opci&oacuten</option>
				  <option value="2005">ADMINISTRATIVO</option>
				  <option value="2006">RADICACION</option>
				  <option value="2007">GERENCIAL</option>
				  </select>
				 </div>
		
		
		
		</div>
		
		 <div class="form-check">
		
		
		
	
         <br>
		 <div id="guardarRegistro" disabled>
		
		     <a id="guardar" name="guardar"   class="btn btn-outline-secondary" >
	          GUARDAR  
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
 
  
  
</html>