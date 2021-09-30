<%-- 
    Document   : edit
    Created on : 10/09/2021, 8:18:31 a. m.
    Author     : jacun
--%>

<%@page import="Modelo.MotivoConsulta"%>
<%@page import="ModeloDAO.MotivoConsultaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Motivo de Consulta</title>
    </head>
    <body>
          <%
              MotivoConsultaDAO mcDAO = new MotivoConsultaDAO();
              int id = Integer.parseInt((String)(request.getParameter("idMC")));
              MotivoConsulta mc = (MotivoConsulta) mcDAO.list(id);
          
          %>      
         <form action="ControlMotivo">
                <br>              
                <br>
                <br>
                <center>
                    <h1>Modificar Motivo de Consulta</h1>
                    <table border="0" >
                        <thead>
                            <tr>
                                <th style="width: 10%; align-content: initial;"><strong>Id</strong></th>
                                <th style="width: 90%; align-content: initial;"><strong>Nombre</strong></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="width: 10%;"><center><%= mc.getId()%></center>
                                    <input name="id" id="id" size="10" value="<%= mc.getId()%>" type="hidden">
                                </td>
                                <td style="width: 90%"><input name="descripcion" id="descripcion" size="130" value="<%= mc.getDescripcion()%>" type="text"></td>
                            </tr>
                        </tbody>
                    </table>
                <br>
                <b>
                    <input type="submit" name="accion"  id="accion" value="Actualizar">
                </b>
                </center>
        </form>
    </body>
</html>
