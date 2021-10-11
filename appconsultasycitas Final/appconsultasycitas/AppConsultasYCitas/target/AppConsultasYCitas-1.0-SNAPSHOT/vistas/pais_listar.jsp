<!--
  ARCHIVO: vistas/pais_listar.jsp
  @AUTOR: Grupo de desarrollo 3 - Mision Mintic
  FECHA DE CREACION: 09-10-2021
  APLICACIÓN: Contabilidad - Academusoft 3.2
  CASO DE USO: xxx
  DOCUMENTO: xxx
-->
<%@page import="Modelo.Pais"%>
<%@page import="java.util.*"%>
<%@page import="ModeloDAO.PaisDAO"%>
<script language="javascript" src="../jScripts/validaFechas.js"></script>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Adicionar Pais</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
  <script>
    </script>
    </head>
<%
    PaisDAO dao = new PaisDAO();
    List list = new ArrayList<>();
    List lista = new ArrayList<>();
    lista = dao.listar();
    int totalRegistros = lista.size();
    int totalPags = 0;
    if((totalRegistros%10)==0){
     totalPags = totalRegistros/10;
    }
    String paginador = (String)request.getParameter("paginador");

    if(request.getParameter("paginador")== null){
        paginador = "1";
    }else{
        paginador = (String)request.getParameter("paginador");
    }
                        
    int diferenciaPags= Integer.parseInt(paginador)- totalPags;
    if((diferenciaPags==1)&&(totalRegistros%10)==0){
     paginador = String.valueOf(totalPags);
    }
    String parametro = (String)request.getParameter("parametro");
    if(parametro==null){
     parametro="";
    }
    int paginas = dao.calcularPaginas(parametro);
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
 
    String usuario = "";
    if(request.getParameter("usuario")!=null){
        usuario=(String)request.getParameter("usuario");  
    }
    
    int desde = (Integer.parseInt(paginador)*10)-9;
    int hasta = Integer.parseInt(paginador)*10;
    list = dao.listarPagina(parametro, desde, hasta);
    totalRegistros = lista.size();
     //list = dao.listarConParametro(parametro);
%>
<body>
    <center>
<form name ="form1" action="ControlPais">
  <h5><strong>             Pais</strong></h5>
    <table style="width: 90%">
      <tr>
        <td style="width: 7%;"> 
            <a href="ControlPais?accion=volver" class="btn btn-primary btn-sm"> <strong>Volver</strong></a> 
        </td>
        <td style="width: 13%;" valign="top" >
            Página
            <select class="custom-select custom-select-lg mb-3" id="paginador" name="paginador" onchange="submit('Controlador?accion=Buscar')"%>
            <%
            for (int i = 1; i <= paginas; i++){
                if ((String.valueOf(i)).equals(paginador)){
            %>
                <option value="<%= i %>" selected><%= i %></option>
            <% }else{ %>
                    <option value="<%= i %>" ><%= i %></option%>
            <% }
            } %>
            </select>
        </td>
        <td style="width: 16%;">
            <label for="parametro" class="form-label">Parámetro de Búsqueda</label>
        </td>
        <td style="width: 37%;">
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
            <input type="submit" name="accion" id="accion" value="Buscar" onclick="cambiarValorPagina()" class="btn btn-primary btn-sm">
        </td>
        <td style="width: 20%;" align="right">
            <a href="ControlPais?accion=add&parametro=<%= parametro%>&paginador=<%= paginador%>&usuario=<%= usuario%>" class="btn btn-primary btn-sm"> <strong>Adicionar Registro</strong></a>
            <input name="usuario" id="usuario" value="<%= usuario%>" type="hidden" class="form-control" size="50" maxlength="100">                     
        </td>
      </tr>
        <div class="col">
            <td colspan="6" style="width: 100%;"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Listado de Pais</strong></p></td>
        </div>
    </table>
</form>
    <table class="table table-sm" style="width: 90%">
        <thead class="table-dark table-sm">
            <tr class="">
                <td width="10%" align="left" ><strong>Id</strong></td>
                <td width="75%" align="left" ><strong>Nombre del País</strong></td>
                <td width="15%" align="center"><strong>Operaciones</strong></td>
            </tr>
        </thead>
        <tbody>
            <%
            Iterator<Pais> iter = list.iterator();
            Pais pais = null;
            if (lista != null){
                while(iter.hasNext()){
                    pais = iter.next();
            %>
                     <div class="col">
                        <td align="left" class=""><%= pais.getIdPais() %></td>
                    </div>
                   <div class="col">
                        <td align="left" class=""><%= pais.getNombre() %></td>
                    </div>
                    <div class="col"> <center>
                        <td style=" width: 15%;" align="right">
                        <a href="ControlPais?accion=edit&id=<%= pais.getIdPais()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-primary btn-sm"><strong>Editar</strong></a>
                        <a href="ControlPais?accion=eliminar&id=<%= pais.getIdPais()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-danger btn-sm"><strong>Eliminar</strong></a>
                        </td>
                        </center>
                    </div>
                </tr>
              <%}
            }else{ %>
            <tr class="">
                    <td colspan="5" align="center" height="20" class="" >No se encontraron registros.</td>
            </tr>
        <%  } %>
        </tbody>
    </table >
    </center>
</body>
</html>




<!--
  09-10-2021  @:GRUPO DE DESARROLLO 3  &:Creación
-->

