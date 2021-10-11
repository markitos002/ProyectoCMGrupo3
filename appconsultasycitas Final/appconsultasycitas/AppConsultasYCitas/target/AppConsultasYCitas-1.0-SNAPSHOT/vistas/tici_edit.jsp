<%-- 
    Document   : edit
    Created on : 10/09/2021, 8:18:31 a. m.
    Author     : jacun
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="Modelo.TipoCita"%>
<%@page import="ModeloDAO.TipoCitaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Tipo de Cita</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>

    </head>
    <body>
        <%
            TipoCitaDAO tipoCitaDAO = new TipoCitaDAO();
            int id = Integer.parseInt((String)(request.getParameter("id")));
            TipoCita tipoCita = (TipoCita) tipoCitaDAO.consultar(String.valueOf(id));
           // JOptionPane.showMessageDialog(null, id, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
            String parametro = (String)request.getParameter("parametro");
            String paginador = (String)request.getParameter("paginador");
            if(parametro==null){
                parametro="";
            }
            if(paginador==null){
                paginador="";
            }
        %>
    <center>
        <form action="ControlTipoCita">
            <br>
            <h5><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tipos de Citas Médicas</strong></h5>
                <table class="table" style="width: 89%">
                <tr>
                    <td style="width: 6%;"> 
                        <a href="ControlTipoCita?accion=buscar&parametro=<%=parametro%>&paginador=<%=paginador%>&valor=0" class="btn btn-primary btn-sm"> <strong>Volver</strong></a> 
                    </td>
                    <td style="width: 94%;" align="left">
                        <input type="hidden" name="valor" id="valor" maxlength="5" value="">
                        <p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Modificar Registro</strong></p>
                    </td>
               </tr>
            </table>
  
            <table class="table table-sm" style="width: 88%">
                <thead class="table-dark table-sm" >

                    <div class="col">
                        <td style="width: 6%;"><center><strong>Id</strong></center></td>
                    </div> 
                    <div class="col">
                        <td colspan="2"><strong>Descripción</strong></td>
                    </div> 
                </thead>         
                <tbody>
                    <tr>                        
                        <div class="col">
                            <td style="width: 6%;"><center><%= tipoCita.getIdTipoCita()%></center>
                                <input name="id" id="id" size="10" value="<%= tipoCita.getIdTipoCita()%>" type="hidden">
                            </td>
                        </div>
                        <div class="col">
                            <td colspan="2">
                                <div class="col-12"> 
                                    <input name="nombre" id="nombre" size="125" value="<%= tipoCita.getNombre()%>" type="text"> 
                                </div>
                                <input type="hidden" name="paginador" id="paginador" maxlength="5" value="<%=paginador%>"> 
                                <input type="hidden" name="parametro" id="parametro" maxlength="5" value="<%=parametro%>">
                            </td>
                        </div>
                    <tr>                        
                    <tr>                        
                        <div class="col">
                            <td colspan="2"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"></p></td>
                        </div>
                        <div class="col">
                            <td style="width: 6%;">                
                                <strong>
                                    <input type="submit" name="accion"  id="accion" value="Actualizar" class="btn btn-primary btn-sm">
                                </strong>
                            </td>
                        </div>
                    <tr>  
                </tbody>
            </table> 
        </form>
    </center>
    </body>
</html>
