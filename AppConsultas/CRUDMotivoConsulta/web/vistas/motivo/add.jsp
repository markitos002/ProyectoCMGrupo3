<%-- 
    Document   : add
    Created on : 10/09/2021, 8:18:19 a. m.
    Author     : jacun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adicionar Motivo</title>
    </head>
    <body>
        <form action="ControlMotivo">
                <br>
                <br>
                <center>
                <h1>Agregar Motivo de Consulta</h1>
                <br>
                    <table border="0" >
                        <thead>
                            <tr>
                                <th style="width: 10%; align-content: initial;"><strong>Id</strong></th>
                                <th style="width: 90%; align-content: initial;"><strong>Nombre</strong></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="width: 10%"><input name="id" id="id" size="10" type="text"></td>
                                <td style="width: 90%"><input name="descripcion" id="descripcion" size="130" type="text"></td>
                            </tr>
                        </tbody>
                    </table>
                <br>
                <b>
                    <input type="submit" name="accion"  id="accion" value="Agregar">
                </b>
                </center>
        </form>

    </body>
</html>
