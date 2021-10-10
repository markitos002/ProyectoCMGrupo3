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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <script>

    </script>
    </head>

    <body>
        <%
            String parametro = (String)request.getParameter("parametro");
            String paginador = (String)request.getParameter("paginador");
            String valor = (String)request.getParameter("valor");
        %>
    <center>
        <form action="ControlMotivo">
            <br>
            <h5><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Motivos Consulta</strong></h5>
                <table class="table" style="width: 89%">
                <tr>
                    <td style="width: 6%;"> 
                        <a href="ControlMotivo?accion=Buscar&parametro=<%=parametro%>&paginador=<%=paginador%>&valor=0" class="btn btn-primary btn-sm"> <strong>Volver</strong></a> 
                    </td>
                    <td style="width: 94%;" align="left">
                        <input type="hidden" name="valor" id="valor" maxlength="5" value="">
                        <p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Adicionar Registro</strong></p>
                    </td>
               </tr>
            </table>
  
            <table class="table table-sm" style="width: 88%">
                <thead class="table-dark table-sm">
                    <div class="col">
                        <td style="width: 94%;"><strong>Descripción</strong></td>
                    </div> 
                    <div class="col">
                        <td style="width: 6%;">&nbsp;&nbsp;</td>
                    </div>
                </thead>         
                <tbody>
                    <tr>                        
                        <div class="col">
                           <td colspan="2">
                                <div class="col-12"> 
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Ingrese la descripción del motivo" autofocus="yes">
                                </div>
                                <input type="hidden" name="paginador" id="paginador" maxlength="5" value="<%=paginador%>"> 
                                <input type="hidden" name="parametro" id="parametro" maxlength="5" value="<%=parametro%>">
                                <input type="hidden" name="valor" id="valor" maxlength="5" value="<%=valor%>">
                           </td>
                        </div>
                    <tr>                        
                    <tr>                        
                        <div class="col">
                            <td style="width: 94%;"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"></p></td>
                        </div>
                        <div class="col">
                            <td style="width: 6%; font-weight: bold">                
                                <input type="submit" name="accion"  id="accion" value="Agregar" class="btn btn-primary btn-sm">
                            </td>
                        </div>
                    <tr>  
                </tbody>
            </table> 
        </form>
    </center>


    </body>
</html>
