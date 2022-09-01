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

        $(document).ready(function () {
            $("a[id=agregar]").on("click", function () {
                var NombreConvenio = document.getElementById("convNombre").value;
                var fechaConvenio = document.getElementById("datepicker").value;
                var path = document.getElementById("convNombre").value;

                $("table[id=tableResumen]").append('<tr><td width= "10%">' + NombreConvenio + '</td><td width= "15%">' + fechaConvenio + '</td><td width= "15%">' + path + "</td></tr>");
            });
        });

        $(document).ready(function () {
            $("a[id=guardar]").on("click", function () {
                $("#modalResumenFactura").modal("show");

                alert("Convenio Guardado Correctamente");

                var detalles = [];
                var contador = 0;

                $("#tableResumen tr").each(function (index) {
                    var nombre, tipoDocumento, periodicidad;

                    $(this)
                        .children("td")
                        .each(function (index2) {
                            switch (index2) {
                                case 0:
                                    nombre = $(this).text();

                                    break;
                                case 1:
                                    fecha = $(this).text();
                                    break;
                                case 2:
                                    estado = $(this).text();
                                    break;
                            }
                        });
                    if (contador == 0) {
                        contador++;
                    } else {
                        detalles.push({
                            nombre: nombre,
                            fecha: fecha,
                            estado: estado,
                        });
                    }

                   
                });

                var jsonProducto = JSON.stringify(
                    eval({
                        detallePVM: detalles,
                    })
                );

                $.ajax({
                    type: "POST",
                    url: "ServletAdminConvenioMarco",
                    dataType: "json",
                    data: jsonProducto,
                    statusCode: {
                        404: function () {
                            alert("pagina no encontrada ValorProductoServlet");
                        },
                        500: function () {
                            alert("Error servidor ValorProductoServlet");
                        },
                    },
                    success: function (response) {
                        var separado = response.split(":");
                    },
                });
            });
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
                                        <li><hr class="dropdown-divider" /></li>
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
                                        <li><hr class="dropdown-divider" /></li>
                                        <li><a class="dropdown-item" href="BasesCuantitativas.jsp">Bases cuantitativas</a></li>
                                    </ul>
                                </li>
                            </ul>
                           <form class="d-flex">
       
						        <button type="button" class="btn btn-outline-dark"><a href=./Login.jsp>Salir</button>
						      </form>
                        </div>
                    </div>
                </nav>

                <div class="<nav class=&quot;navbar navbar-expand-lg navbar-light bg-light&quot;>"></div>
                <div class="col-6"></div>
                <div class="col"></div>
            </div>
            <div class="row">
                <div class="col">
                    <h3 class="H3t">Administraci&oacuten de Convenios Marco</h3>

                    <div>
                        <label for="Agregar Nombre Convenio" class="form-label">Nombre Convenio Marco</label>
                        <input type="text" class="form-control" id="convNombre" placeholder="Digite el Nombre del Convenio marco" />
                    </div>
                    <br />
                    <label for="Agregar Nombre Convenio" class="form-label">Fecha inicio del Convenio</label>
                    <input id="datepicker" width="276" />
                    <script>
                        $("#datepicker").datepicker({
                            uiLibrary: "bootstrap4",
                        });
                    </script>
                    <br />
                </div>

                <div id="agregar_registro">
                    <br />
                    <a id="agregar" name="agregar" class="btn btn-outline-secondary">
                        <div class="glyphicon glyphicon-plus"></div>
                        Agregar
                    </a>
                </div>

                <br />
                <br />
                <br />
            </div>
            <div class="table-responsive" id="tableResumen" name="tableResumen">
                <div class="col-xs-12">
                    <table class="table table-bordered" id="tableResumen">
                        <thead class="thead">
                            <tr>
                                <th width="10%">
                                    NOMBRE
                                </th>
                                <th width="15%">
                                    FECHA INICIO
                                </th>
                                <th width="15%">
                                    ARCHIVO
                                </th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <br />
            <div id="guardarRegistro">
                <a id="guardar" name="guardar" class="btn btn-outline-secondary">
                    <div class="glyphicon glyphicon-plus"></div>
                    Guardar
                </a>
            </div>
            <div class="&lt;">
                <div class="col"></div>
            </div>
        </div>

        <div class="modal fade" id="modalResumenFactura" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="false">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div id="upload-1-queue"" class="form-control">
                </div>
                Por favor antes de cargar el Archivo verifique que cumple todos los requerimientos
                <form action="FileUploadServlet" class="form-control" method="post" enctype="multipart/form-data">
                    Select File to Upload:<input type="file" name="fileName" />
                    <br />
                    <input type="submit" value="Upload" />
                </form>
                <div class="modal-body"></div>
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
