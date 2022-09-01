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
    <title>SASMEN</title>
  </head>
  
  <script>
  

  
  $(document).ready(function(){
  
  function getParameterByName(name, url) {
		 if (!url) {
		 url = window.location.href;
		 }
		 name = name.replace(/[\[\]]/g, "\\$&");
		 var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
		 results = regex.exec(url);
		 if (!results) return null;
		 if (!results[2]) return '';
		 return decodeURIComponent(results[2].replace(/\+/g, " "));
	}
	
	 var prodId = getParameterByName('documento');
	
	
	
	
	$.ajax({
			type: 'POST',
			url: 'ServletDownload',
			data: 'docu_id='+prodId,
			statusCode: {
				404: function(){
					alert("pagina no encontrada ValorProductoServlet");
				},
				500: function(){
					alert("Error servidor ValorProductoServlet");
				}
			},
			success: function(response){
																		
								
			}
		});
	
	
});
  
  
  </script>
  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1></h1>
             
    </body>
</html>
