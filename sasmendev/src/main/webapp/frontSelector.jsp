<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <!-- Required meta tags -->
        |
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" />
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
        <title>SASMEN</title>
    </head>

    <script>
        //************************************************************************************************************
        // AGREGA REGISTROS
        //  CONVENIOS MARCO
        // 05/03/2022 BY JHUALC
        //************************************************************************************************************
$(document).ready(function(){
	
		var usuario = '<%=request.getUserPrincipal().getName()%>';
		
		if (usuario == 'admin'){
			document.getElementById("adocumentos").href=("./AdminUsuarioConvenio.jsp");
			document.getElementById("aestadistica").href=("./BasesCuantitativas.jsp");
			document.getElementById("afinanciero").href=("./Financiero.jsp");
		}
		if (usuario != 'admin'){
			document.getElementById("adocumentos").href=("./Radicacion.jsp");
			document.getElementById("aestadistica").href=("./BasesCuantitativas.jsp");
			document.getElementById("afinanciero").href=("./Financiero.jsp");
		}
	
});
    </script>
    <style>
        .div-1 {
            background-color: #3366cc;
        }

        .div-2 {
            background-color: #abbaea;
        }

        .div-3 {
            background-color: #fbd603;
        }
        .H3t {
            color: #3366cc;
        }
    </style>

    <head>
        <div class="col-xs-6">
            <div class="div-1"><br /></div>
            <br />
            <table>
                <tr>
                    <td width="40%"></td>
                    <td width="40%"><img src="./MinEducacion.png" width="200px" height="40px" /></td>

                    <td width="10%">
                        <div></div>
                    </td>
                </tr>
            </table>
        </div>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h3 class="H3t">Herramienta de Apoyo a la supervisi&oacuten MEN</h3>

                

                <div class="<nav class=&quot;navbar navbar-expand-lg navbar-light bg-light"></div>
                <div class="col-6"></div>
                <div class="col"></div>
            </div>
            
            <button type="button" class="btn btn-light btn-lg" id="documentos" name="documentos" ><a  id="adocumentos">GESTION DOCUMENTAL</a></button>
			<button type="button" class="btn btn-light btn-lg" id="estadistica" name="estadistica"><a  id="aestadistica">ESTADISTICA</button>
           
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
    --></body>
</html>
