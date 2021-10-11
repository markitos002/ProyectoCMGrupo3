<!--
  ARCHIVO: vistas/con_med_con_med_agr.jsp
  @AUTOR: Grupo de desarrollo 3 - Mision Mintic
  FECHA DE CREACION: 09-10-2021
  APLICACIÓN: Contabilidad - Academusoft 3.2
  CASO DE USO: xxx
  DOCUMENTO: xxx
-->
<%@page import="Modelo.ConsultaMedica"%>
<%@page import="java.util.*"%>
<%@page import="ModeloDAO.ConsultaMedicaDAO"%>
<script language="javascript" src="../jScripts/cargaPopup.js"></script>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Adicionar ConsultaMedica</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
  <script>
    </script>
    </head>
<%
    ConsultaMedicaDAO dao = new ConsultaMedicaDAO();
    List list = new ArrayList<>();
    List lista = new ArrayList<>();
    lista = dao.listar();
    int totalRegistros = lista.size();
    int totalPags = 0;
    if((totalRegistros%10)==0){
     totalPags = totalRegistros/10;
    }
    String paginador = (String)request.getParameter("paginador");
     if(paginador==null){
        paginador = "1";
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
    
    int desde = (Integer.parseInt(paginador)*10)-9;
    int hasta = Integer.parseInt(paginador)*10;
    list = dao.listarPagina(parametro, desde, hasta);
    totalRegistros = lista.size();
     //list = dao.listarConParametro(parametro);
%>
<body>
    <center>
<form name ="form1" action="ControlConsultaMedica">
  <h5><strong>             ConsultaMedica</strong></h5>
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
            <a href="ControlConsulta?accion=edit&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-primary btn-sm"> <strong>Adicionar Registro</strong></a>
        </td>
      </tr>
        <div class="col">
            <td colspan="5" style="width: 100%;"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Listado de Consultas Medicas</strong></p></td>
        </div>
    </table>
</form>
    <table class="table table-sm" style="width: 90%">
        <thead class="table-dark table-sm">
                            <tr class="">
                                <td width="5%" align="left"><strong>Id</strong></td>
                                <td width="9%" align="left"><strong>Paciente</strong></td>
                                <td width="16%" align="left"><strong></strong></td>
                                <td width="24%" align="left"><strong>Motivo Consulta</strong></td>
                                <td width="24%" align="left"><strong>Diagnostico</strong></td>
                                <td width="7%" align="left"><strong>Id Cita</strong></td>
                                <td width="15%" align="center"><strong>Operaciones</strong></td>
                            </tr>
        </thead>
        <tbody>
            <%
            Iterator<ConsultaMedica> iter = list.iterator();
            ConsultaMedica consultaMedica = null;
            if (lista != null){
            while(iter.hasNext()){
                consultaMedica = iter.next();
            %>
               <div class="col">
                    <td align="left" class=""><%= consultaMedica.getIdConsultaMedica() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= consultaMedica.getIdPaciente() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= consultaMedica.getPaciente() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= consultaMedica.getIdMedico() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= consultaMedica.getMedico() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= consultaMedica.getIdMotivoConsulta() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= consultaMedica.getDiagnostico() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= consultaMedica.getIdCita() %></td>
                </div>
                <div class="col">
                    <td style=" width: 15%;" align="right">
                    <a href="ControlConsultaMedica?accion=edit&id=<%= consultaMedica.getIdConsultaMedica()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-primary btn-sm"><strong>Editar</strong></a>
                    <a href="ControlConsultaMedica?accion=eliminar&id=<%= consultaMedica.getIdConsultaMedica()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-danger btn-sm"><strong>Eliminar</strong></a>
                    </td>
                </div>
            </tr>
        <%                    }
            }else{ %>
            <tr class="">
                    <td colspan="14" align="center" height="20" class="" >No se encontraron registros.</td>
            </tr>
        <%                  } %>
        </tbody>
    </table >
    </center>
</body>
</html>




<!--
  09-10-2021  @:GRUPO DE DESARROLLO 3  &:Creación
-->
