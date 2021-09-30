<%@page import="java.text.SimpleDateFormat"%>
<!--
  ARCHIVO: vistas/citascit_agr.jsp
  @AUTOR: Grupo de desarrollo 3 - Mision Mintic
  FECHA DE CREACION: 25-09-2021
  APLICACIÓN: Contabilidad - Academusoft 3.2
  CASO DE USO: xxx
  DOCUMENTO: xxx
-->
<%@page import="Modelo.Citas"%>
<%@page import="java.util.*"%>
<%@page import="ModeloDAO.CitasDAO"%>
<script language="javascript" src="../jScripts/cargaPopup.js"></script>
<script language="javascript" src="../jScripts/validaFechas.js"></script>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Adicionar Citas</title>
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
    CitasDAO dao = new CitasDAO();
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

%>
<body>
    <center>
<form name ="form1" action="ControlCita">
  <h5><strong>             Citas</strong></h5>
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
            <a href="ControlCita?accion=add&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-primary btn-sm"> <strong>Adicionar Registro</strong></a>
        </td>
      </tr>
        <div class="col">
            <td colspan="5" style="width: 100%;"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Listado de Citas</strong></p></td>
        </div>
    </table>
</form>
    <table class="table table-sm" style="width: 90%">
        <thead class="table-dark table-sm">
                            <tr class="">

                              <td width="5%" align="left" class=""><strong>Id</strong></td>
                              <td width="5%" align="left" class=""><strong>Fecha</strong></td>
                              <td width="5%" align="left" class=""><strong>Hora</strong></td>
                              <td width="20%" align="left" class=""><strong>Paciente</strong></td>
                              <td width="20%" align="left" class=""><strong>Medico</strong></td>
                              <td width="20%" align="left" class=""><strong>Consultorio</strong></td>
                              <td width="10%" align="left" class=""><strong>Tipo Cita</strong></td>
                              <td width="15%" align="center" class=""><strong>Operaciones</strong></td>
                            </tr>
        </thead>
        <tbody>
            <%
            Iterator<Citas> iter = list.iterator();
            Citas citas = null;
            String date_time = "11/27/2020 05:35:00";
            SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           if (lista != null){
            while(iter.hasNext()){
                citas = iter.next();
                date_time = citas.getFecha();
                Date date = dateParser.parse(date_time);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                String fechita = dateFormatter.format(date);
            %>
                <div class="col">
                    <td align="left" class=""><%= citas.getIdCitas() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= fechita %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= citas.getHora() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= citas.getPaciente() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= citas.getMedico() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= citas.getLugar() %></td>
                </div>
                <div class="col">
                    <td align="left" class=""><%= citas.getTipoCita() %></td>
                </div>
                <div class="col">
                    <td style=" width: 15%;" align="right">
                        <a href="ControlCita?accion=consultar&id=<%= citas.getIdCitas()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-primary btn-sm"><strong>Editar</strong></a>
                        <a href="ControlCita?accion=eliminar&id=<%= citas.getIdCitas()%>&parametro=<%= parametro%>&paginador=<%= paginador%>" class="btn btn-danger btn-sm"><strong>Eliminar</strong></a>
                    </td>
                </div>
            </tr>
        <%                    }
            }else{ %>
            <tr class="">
                    <td colspan="11" align="center" height="20" class="" >No se encontraron registros.</td>
            </tr>
        <%                  } %>
        </tbody>
    </table >
    </center>
</body>
</html>




<!--
  25-09-2021  @:GRUPO DE DESARROLLO 3  &:Creación
--> 