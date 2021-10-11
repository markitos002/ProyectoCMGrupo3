<!--
  ARCHIVO: tabla/rece_rec_agr.jsp
  @AUTOR: Grupo de desarrollo 3 - Mision Mintic
  FECHA DE CREACION: 24-09-2021
  APLICACIÓN: Contabilidad - Academusoft 3.2
  CASO DE USO: xxx
  DOCUMENTO: xxx
-->
<%@page import="java.util.*"%>
<%@page import="Modelo.Receta"%>
<%@page import="ModeloDAO.RecetaDAO"%>
<%
    RecetaDAO recetaDAO = new RecetaDAO();
    int id = Integer.parseInt((String)request.getParameter("id"));
    String idStr = String.valueOf(id);
    Receta receta = (Receta) recetaDAO.consultar(idStr);
    String parametro = (String)request.getParameter("parametro");
    String paginador = (String)request.getParameter("paginador");
    String valor = (String)request.getParameter("valor");
%>
    <%
    if(parametro==null){;
        parametro="";
    }
    if(paginador==null){
        paginador="";
    }
    %>
<script language="javascript" src="../jScripts/cargaPopup.js"></script>
<script language = "javascript">
</script>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Adicionar Receta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
  <script>
    </script>
    </head>
<body>
<form name ="form1" action="ControlReceta">
    <center>
  <h5><strong>             Receta</strong></h5>
    <table style="width: 90%">
      <tr>
        <td style="width: 6%;" valign="top" >
          <a href="ControlReceta?accion=Buscar&parametro=<%=parametro%>&paginador=<%=paginador%>&valor=0" class="btn btn-primary btn-sm"> <strong>Volver</strong></a>
        </td>
        <td style="width: 94%;" align="left">
          <input type="hidden" name="valor" id="valor" maxlength="5" value="">
          <p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Modificar Registro</strong></p>
        </td>
      </tr>
    </table>


    <table style="width: 90%">

        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-2">
                        <label for="cantidadMedicamento" class="form-label"><strong>Cantidad Medicamento</strong></label>
                        <input type="text" class="form-control" name="cantidadMedicamento" id="cantidadMedicamento" value="<%= receta.getCantidadMedicamento() %>" size="10" maxlength="10">
                        <input type="text" class="form-control" name="idReceta" id="idReceta" value="<%= receta.getIdReceta() %>" size="10" maxlength="10">
                    </div>
                    <div class="col-md-6">
                        <label for="medicamento" class="form-label"><strong>Medicamento</strong></label>
                        <input name="medicamento" id="medicamento" value="<%= (String)receta.getMedicamento() %>" type="text" class="form-control" size="50" maxlength="50">
                    </div>
                    <div class="col-md-4">
                        <label for="posologia" class="form-label"><strong>Posología</strong></label>
                        <input id="posologia" name="posologia" value="<%= receta.getPosologia() %>" type="text" class="form-control" size="50" maxlength="50">
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-2">
                        <label for="viaAdministracion" class="form-label"><strong>Via Administración</strong></label>
                        <select id="viaAdministracion" name="viaAdministracion" class="form-select">
                                <% if(receta.getViaAdministracion().equals("Oral")) { %>
                                    <option value="Oral" selected >Oral</option>
                                <% } else { %>
                                    <option value="Oral">Oral</option>
                                <% } %>
                                <% if(receta.getViaAdministracion().equals("Parenteral")) { %>
                                    <option value="Parenteral" selected>Parenteral</option>
                                <% } else { %>
                                    <option value="Parenteral" >Parenteral</option>
                                <% } %>
                                <% if(receta.getViaAdministracion().equals("Topica")) { %>
                                    <option value="Topica" selected >Tópica</option>
                                <% } else { %>
                                    <option value="Topica" >Tópica</option>
                                <% } %>
                                <% if(receta.getViaAdministracion().equals("Nasal")) { %>
                                    <option value="Nasal" selected >Nasal</option>
                                <% } else { %>
                                    <option value="Nasal" >Nasal</option>
                                <% } %>
                                <% if(receta.getViaAdministracion().equals("Oftalmica")) { %>
                                    <option value="Oftalmica" selected>Oftálmica</option>
                                <% } else { %>
                                    <option value="Oftalmica" >Oftálmica</option>
                                <% } %>
                                <% if(receta.getViaAdministracion().equals("Otica")) { %>
                                    <<option value="Otica" selected >Ótica</option>
                                <% } else { %>
                                    <option value="Otica" >Ótica</option>
                                <% } %>
                                <% if(receta.getViaAdministracion().equals("Vaginal")) { %>
                                    <option value="Vaginal" selected>Vaginal</option>
                                <% } else { %>
                                    <option value="Vaginal" >Vaginal</option>
                                <% } %>
                                <% if(receta.getViaAdministracion().equals("Rectal")) { %>
                                    <option value="Rectal" selected>Rectal</option>
                                <% } else { %>
                                    <option value="Rectal" >Rectal</option>
                                <% } %>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="observacion" class="form-label"><strong>Observación</strong></label>
                        <input name="observacion" id="observacion" value="<%= receta.getObservacion() %>" type="text" class="form-control" size="50" maxlength="500">                    
                    </div>
                    <div class="col-md-4">
                        <label for="idConsulta" class="form-label"><strong>Consulta</strong></label>
                        <input id="idConsulta" name="idConsulta" value="<%= receta.getIdConsulta() %>" type="text" class="form-control" size="10" maxlength="10">
                    </div>
                    <input type="hidden" name="paginador" id="paginador" maxlength="5" value="<%= paginador%>">
                    <input type="hidden" name="parametro" id="parametro" maxlength="5" value="<%= parametro%>">
                    <input type="hidden" name="valor" id="valor" maxlength="5" value="<%=valor%>">                
                </div>
            </td>
        </tr>    
    </table>  

    <br>

    <table width="90%" border="0" cellspacing="1" cellpadding="2">
      <tr>
        <div class="col">
            <td colspan="2"> <p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"></p></td>
        </div>
        <div class="col">
          <td style="width: 6%;" valign="top" >
            <strong>
                <input type="submit" name="accion" id="accion" value="Actualizar" class="btn btn-primary btn-sm">
            </strong>
          </td>
        </div>
      </tr>
    </table>
</form>
    </center>
</body>
</html>



<!--
  24-09-2021  @:GRUPO DE DESARROLLO 3  &:Creación
-->
