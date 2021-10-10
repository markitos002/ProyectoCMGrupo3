<!--
  ARCHIVO: vistas/usu_usu_agr.jsp
  @AUTOR: Grupo de desarrollo 3 - Mision Mintic
  FECHA DE CREACION: 01-10-2021
  APLICACIÓN: Contabilidad - Academusoft 3.2
  CASO DE USO: xxx
  DOCUMENTO: xxx
-->
<%@page import="Modelo.Usuario"%>
<%@page import="java.util.*"%>
<%@page import="ModeloDAO.UsuarioDAO"%>
<%@page import="Modelo.Usuario"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listar Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <script>
        function cambiarValorPagina(){
            document.getElementById("valor").value = '1';
        }
    </script>
    </head>
<%
    UsuarioDAO dao = new UsuarioDAO();
    List<Usuario> list = new ArrayList<>();
    List<Usuario> lista = new ArrayList<>();
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
    
    int desde = (Integer.parseInt(paginador)*10)-9;
    int hasta = Integer.parseInt(paginador)*10;
    list = dao.listarPagina(parametro, desde, hasta);
    totalRegistros = lista.size();
     //list = dao.listarConParametro(parametro);
%>
<body>
    <center>
<form name ="form1" action="ControlUsuarios">
  <h5><strong>             Usuario</strong></h5>
    <table style="width: 90%">
      <tr>
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
            <input type="submit" name="accion" id="accion" value="Buscar" onclick="cambiarValorPagina()" class="btn btn-primary btn-sm">
        </td>
        <td style="width: 20%;" align="right">
            <a href="ControlUsuarios?accion=add&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-primary btn-sm"> <strong>Adicionar Registro</strong></a>
        </td>
      </tr>
        <div class="col">
            <td colspan="5" style="width: 100%;"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Listado de Usuarios</strong></p></td>
        </div>
    </table>
</form>
    <table class="table table-sm" style="width: 90%">
        <thead class="table-dark table-sm">
                            <tr class="">

                              <td width="7%" align="left" class=""><strong>Id <%=lista.size()%></strong></td>
                              <td width="12%" align="left" class=""><strong>Identificación</strong></td>
                              <td width="23%" align="left" class=""><strong>Nombre</strong></td>
                              <td width="10%" align="left" class=""><strong>Rol</strong></td>
                              <td width="12%" align="left" class=""><strong>Usuario</strong></td>
                              <td width="12%" align="left" class=""><strong>Contraseña</strong></td>
                              <td width="13%" align="center" class=""><strong>Operaciones</strong></td>
                            </tr>
        </thead>
        <tbody>
            <%
            Iterator<Usuario> iter = list.iterator();
            Usuario usuario = null;
           if (lista != null){
              while(iter.hasNext()){
                usuario = iter.next();
            %>
                <tr>  
                    <div class="col">
                        <td align="left" class=""><%= usuario.getIdUsuario() %></td>
                    </div>
                    <div class="col">
                        <td align="left" class=""><%= usuario.getIdentificacion() %></td>
                    </div>
                    <div class="col">
                        <td align="left" class=""><%= usuario.getNombre() %></td>
                    </div>
                    <div class="col">
                        <td align="left" class=""><%= usuario.getRol() %></td>
                    </div>
                    <div class="col">
                        <td align="left" class=""><%= usuario.getUsuario() %></td>
                    </div>
                    <div class="col">
                        <td align="left" class=""><%= usuario.getContrasena() %></td>
                    </div>
                    <div class="col">
                        <td style=" width: 13%;" align="right">
                            <a href="ControlCita?accion=consultar&id=<%= usuario.getIdUsuario()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-primary btn-sm"><strong>Editar</strong></a>
                            <a href="ControlCita?accion=eliminar&id=<%= usuario.getIdUsuario()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-danger btn-sm"><strong>Eliminar</strong></a>
                        </td>
                    </div>
                </tr>
        <%      }
            }else{ %>
                <tr class="">
                        <td colspan="11" align="center" height="20" class="" >No se encontraron registros.</td>
                </tr>
        <%  } %>
        </tbody>
    </table >
    </center>
</body>
</html>
