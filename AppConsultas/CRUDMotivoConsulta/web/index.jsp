<%-- 
    Document   : index
    Created on : 10/09/2021, 8:13:26 a. m.
    Author     : jacun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Motivos de Consulta</title>
                <!--BOOTSTRAP 5-->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>

    </head>
    <body>
        <div>
             
            <center>
                <table border="0">
                        <p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Opciones del Programa</strong></p>
                        <tr>
                            <td><a href="ControlMotivo?accion=Buscar&paginador=1&valor=1">Listar Motivos</a></td>
                        </tr>
                        <tr>
                            <td><a href="ControlReceta?accion=Buscar&paginador=1&valor=1">Listar Recetas</a></td>
                        </tr>
                        <tr>
                            <td><a href="ControlCita?accion=Buscar&paginador=1&valor=1">Listar Citas</a></td>
                        </tr>
                        <tr>
                            <td><a href="ControlTipoCita?accion=Buscar&paginador=1&valor=1">Listar Tipo de Citas</a></td>
                        </tr>
                </table>
            </center>
        </div>
    </body>
</html>
