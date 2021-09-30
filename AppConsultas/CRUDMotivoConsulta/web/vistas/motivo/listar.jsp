<%-- 
    Document   : listar
    Created on : 10/09/2021, 8:18:43 a. m.
    Author     : jacun
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.MotivoConsulta"%>
<%@page import="ModeloDAO.MotivoConsultaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>

    </head>
    <body>
                     <% 
                        MotivoConsultaDAO dao = new MotivoConsultaDAO();
                        List<MotivoConsulta> list = new ArrayList<>();

                        String parametro = (String)request.getParameter("parametro");
                        String paginador = (String)request.getParameter("paginador");
                        if(parametro==null){
                            parametro="";
                            list = dao.listar();
                        }else{
                            list = dao.listarConParametro(parametro);
                        }
                     %>
        <center>
            <h5><strong>Motivos Consulta</strong></h5>
            <form  action="ControlMotivo">
                <table class="table table-sm" style="width: 86%">
                    <tr>
                        <td style="width: 13%;"> 
                            Página
                            <select class="custom-select custom-select-lg mb-3" id="paginador" name="paginador" onchange="submit()">
                                        <option value="1">1</option>
                            </select>
                        </td>
                        <td style="width: 16%;">
                            <label for="parametro" class="form-label">Parámetro de Búsqueda</label>
                        </td>
                        <td style="width: 44%;">
                            <div class="col">
                                <div class="row control-group">
                                    <div class="for group col-xs-12 floating-label-form-group controls">
                                            <div class="col-12"> 
                                                <input type="text" value="<%=parametro%>" class="form-control" id="parametro" name="parametro" placeholder="Ingrese cadena de búsqueda">
                                            </div>
                                        <input type="hidden" name="paginador" id="paginador" maxlength="10" value=""> 
                                        <input type="hidden" name="valor" id="valor" maxlength="2" class="form-control" value="">                               
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td style="width: 7%;" align="right"> 
                            <input type="submit" name="accion"  id="accion" value="Buscar" class="btn btn-primary btn-sm">
                        </td>
                        <td style="width: 20%;" align="right"> 
                                <a href="ControlMotivo?accion=add" class="btn btn-primary btn-sm"> <strong>Adicionar Registro</strong></a>
                        </td>
                    </tr>

                        <div class="col">
                            <td colspan="5" style="width: 100%;"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Listado de Motivos de Consulta</strong></p></td>
                        </div> 

                </table>
            </form>

            </table>
            <table class="table table-sm" style="width: 86%">
                <thead class="table-dark table-sm">
                    
                        <div class="col">
                            <td style="width: 10%;"><strong>id</strong></td>
                        </div>
                        <div class="col">
                            <td style="width: 75%;"><strong>Nombre</strong></td>
                        </div>
                        <div class="col">
                            <td style="width: 15%;" align="center"><strong>Operaciones</strong></td>
                        </div> 
                        
                </thead>         
                <tbody>
                   
                    <% 
                     
                        Iterator<MotivoConsulta> iter = list.iterator();
                        MotivoConsulta mc = null;
                        while(iter.hasNext()){
                            mc = iter.next();
                    %>
                    <tbody>
                        <tr>                        
                            <div class="col">
                                <td style="width: 10%;"><%= mc.getId()%> &nbsp;&nbsp;</td>
                            </div>
                           <div class="col">
                                <td style="width: 75%;"><%= mc.getDescripcion()%> &nbsp;&nbsp;</td>
                            </div>
                            <div class="col">
                                <td style=" width: 15%;" align="center">
                                    <a href="ControlMotivo?accion=edit&idMC=<%= mc.getId()%>" class="btn btn-primary btn-sm"><strong>Editar</strong></a>
                                    <a href="ControlMotivo?accion=eliminar&idMC=<%= mc.getId()%>" class="btn btn-danger btn-sm"><strong>Eliminar</strong></a>
                                </td>
                            </div>     
                        </tr>
                        <%}%>
                    </tbody>
            </table> 
            </center>
    </body>
</html>
