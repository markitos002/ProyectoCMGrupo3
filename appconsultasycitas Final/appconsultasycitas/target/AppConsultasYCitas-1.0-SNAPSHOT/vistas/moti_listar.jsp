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

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>


    </head>
    <script>
        function enviar(){
           
            //;
            document.forma01.action("moti_listar.jsp")
            
            alert(document.getElementById("valor").value);
            
            document.forma01.submit();
        }
        function cambiarValorPagina(){
            document.getElementById("valor").value = '1';
        }
    </script>
    <body>
                     <% 
                        MotivoConsultaDAO dao = new MotivoConsultaDAO();
                        List<MotivoConsulta> list = new ArrayList<>();
                        List<MotivoConsulta> lista = new ArrayList<>();
                        lista = dao.listar();
                        int totalRegistros = lista.size();
                        int totalPags = 0;
                        if((totalRegistros%10)==0){
                            totalPags = totalRegistros/10;
                        }
                        String paginador = (String)request.getParameter("paginador");
                        
                        int diferenciaPags= Integer.parseInt(paginador)- totalPags;
                        if((diferenciaPags==1)&&(totalRegistros%10)==0){
                            paginador = String.valueOf(totalPags);
                        }
                        String parametro = (String)request.getParameter("parametro");
                        if(parametro==null){
                            parametro="";
                        }
                        int paginas =  dao.calcularPaginas(parametro);
                        if(paginas ==0){
                            paginas = 1;
                        }
                        
                        String valor = (String)request.getParameter("valor");
                        if(valor == null){
                            valor = "0";
                        }
                        if (valor.equals('1')){
                            paginador = "1";
                        }
                                              
                        int desde = (Integer.parseInt(paginador)*10)-9;
                        int hasta = Integer.parseInt(paginador)*10;
                        list = dao.listarPagina(parametro, desde, hasta);
                        totalRegistros = lista.size();
                            //list = dao.listarConParametro(parametro);
                     %>
        <center>
            <h5><strong>Motivos Consulta</strong></h5>
            <form  action="ControlMotivo" name="forma01" id="forma01">
                <input type="hidden" name="valor" id="valor" maxlength="2" class="form-control" value="<%= diferenciaPags%>">  
                <table class="table table-sm" style="width: 86%">
                    <tr>
                        <td style="width: 13%;"> 
                            Página
                            <select class="custom-select custom-select-lg mb-3" id="paginador" name="paginador" onchange="submit('ControlMotivo?accion=Buscar')">
                            <%		
                                for (int i = 1; i <= paginas; i++){    
                                
                                    if ((String.valueOf(i)).equals(paginador)){
                            %>
                                        <option value="<%= i %>" selected><%= i %></option>
                            <%      }else{ %>	
                                        <option value="<%= i %>"  ><%= i %></option>
                            <%      } 
                            
                                }  %>
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
                                                                     
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td style="width: 7%;" align="right"> 
                            <input type="submit" name="accion"  id="accion" value="Buscar" onclick="cambiarValorPagina()" class="btn btn-primary btn-sm">
                        </td>
                        <td style="width: 20%;" align="right"> 
                            <a href="ControlMotivo?accion=add&parametro=<%=parametro%>&paginador=<%=paginador%>id=1" class="btn btn-primary btn-sm"> <strong>Adicionar Registro</strong></a>
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
                                <td style=" width: 15%;" align="right">
                                    <a href="ControlMotivo?accion=edit&idMC=<%= mc.getId()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-primary btn-sm"><strong>Editar</strong></a>
                                    <a href="ControlMotivo?accion=eliminar&idMC=<%= mc.getId()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-danger btn-sm"><strong>Eliminar</strong></a>
                                </td>
                            </div>     
                        </tr>
                        <%}%>
                    </tbody>
            </table> 
            </center>
    </body>

</html>
