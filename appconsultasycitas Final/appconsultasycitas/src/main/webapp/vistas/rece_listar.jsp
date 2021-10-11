<!--
  ARCHIVO: tabla/rece_rec_agr.jsp
  @AUTOR: Grupo de desarrollo 3 - Mision Mintic
  FECHA DE CREACION: 24-09-2021
  APLICACIÓN: Contabilidad - Academusoft 3.2
  CASO DE USO: xxx
  DOCUMENTO: xxx
-->
<%@page import="Modelo.Receta"%>
<%@page import="java.util.*"%>
<%@page import="ModeloDAO.RecetaDAO"%>
<script language="javascript" src="../jScripts/cargaPopup.js"></script>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Adicionar Receta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <script>
        function enviar(){
           
            //;
            document.forma01.action("rece_listar.jsp");
            
            alert(document.getElementById("valor").value);
            
            document.forma01.submit();
        }
        function cambiarValorPagina(){
            document.getElementById("valor").value = '1';
        }
    </script>
    </head>
<%
    RecetaDAO dao = new RecetaDAO();
    List list = new ArrayList<>();
    List lista = new ArrayList<>();
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
<form name ="form1" action="ControlReceta">
  <h5><strong>             Receta</strong></h5>
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
            <a href="ControlReceta?accion=add&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-primary btn-sm"> <strong>Adicionar Registro</strong></a>
        </td>
      </tr>
        <div class="col">
            <td colspan="5" style="width: 100%;"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Listado de Receta</strong></p></td>
        </div>
    </table>
</form>
    <table class="table table-sm" style="width: 90%;" >
        <thead class="table-dark table-sm">
                            <tr class="">
                              <td width="5%" align="left" class="text_negro">Id </td>
                              <td width="5%" align="left" class="text_negro">Cantidad </td>
                              <td width="30%" align="left" class="text_negro">Medicamento</td>
                              <td width="20%" align="left" class="text_negro">Posología</td>
                              <td width="5%" align="left" class="text_negro">Vía</td>
                              <td width="15%" align="left" class="text_negro">Observación</td>
                              <td width="5%" align="left" class="text_negro">Consulta</td>
                              <td width="15%" align="center" class="text_negro">Operaciones</td>
                            </tr>
        </thead>
        <tbody>
            <%
            Iterator<Receta> iter = list.iterator();
            Receta receta = null;
            if (lista != null){
            while(iter.hasNext()){
                receta = iter.next();
            %>
                <div class="col">
                    <td align="left" class=""><%= receta.getIdReceta() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= receta.getCantidadMedicamento() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= receta.getMedicamento() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= receta.getPosologia() %></td>
                </div>
                <div class="col">
                    <% if (receta.getViaAdministracion().equals("Topica")){ %>
                            <td align="left" class="">Tópica</td>
                    <% }else 
                            if (receta.getViaAdministracion().equals("Oftalmica")){%>
                                <td align="left" class="">Oftálmica</td>
                         <% }else 
                                if (receta.getViaAdministracion().equals("Otica")){%>
                                    <td align="left" class="">Ótica</td>
                             <% }else{ %>
                                    <td align="left" class=""><%= receta.getViaAdministracion() %></td>
                             <% } %>
                    
                </div>
                <div class="col">
                    <td align="left" class=""><%= receta.getObservacion() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= receta.getIdConsulta() %></td>
                </div>
                <div class="col">
                    <td style=" width: 15%;" align="right">
                    <a href="ControlReceta?accion=consultar&id=<%= receta.getIdReceta()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-primary btn-sm"><strong>Editar</strong></a>
                    <a href="ControlReceta?accion=eliminar&id=<%= receta.getIdReceta()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-danger btn-sm"><strong>Eliminar</strong></a>
                    </td>
                </div>
            </tr>
        <%                    }
            }else{ %>
            <tr class="">
                    <td colspan="8" align="center" height="20" class="" >No se encontraron registros.</td>
            </tr>
        <%                  } %>
        </tbody>
    </table >
    </center>
</body>
</html>




<!--
  24-09-2021  @:GRUPO DE DESARROLLO 3  &:Creación
--> 