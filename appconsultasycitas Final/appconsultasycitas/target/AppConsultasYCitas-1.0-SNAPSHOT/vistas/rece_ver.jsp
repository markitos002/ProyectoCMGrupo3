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
Integer id = 0 ;
if(request.getParameter("id") != null) {
id = Integer.parseInt((String)request.getParameter("id"));
}
    Receta receta = (Receta) recetaDAO.list(id);
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
    <center>
  <h5><strong>             Receta</strong></h5>
    <table style="width: 90%">
      <tr>
        <td style="width: 6%;" valign="top" >
          <a href="ControlReceta?accion=Buscar&parametro=<%=parametro%>&paginador=<%=paginador%>&valor=0" class="btn btn-primary btn-sm"> <strong>Volver</strong></a>
        </td>
        <td style="width: 94%;" align="left">
          <input type="hidden" name="valor" id="valor" maxlength="5" value="" >
          <p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Modificar Registro</strong></p>
        </td>
      </tr>
    </table>
    <table width="90%" border="0" cellspacing="1" cellpadding="2">
      <tr>
        <td width="15%" align="left" class="text_negro">Id Receta </td>
        <td width="85%" height="20" align="left"><input name="idReceta" value="<%= receta.getIdReceta() %>" type="text" class="form-control" size="20" maxlength="100" readonly ></td>
      </tr>
      <tr>
        <td width="15%" align="left" class="text_negro">Cantidad Medicamento </td>
        <td width="85%" height="20" align="left"><input name="cantidadMedicamento" value="<%= receta.getCantidadMedicamento() %>" type="text" class="form-control" size="20" maxlength="100" readonly ></td>
      </tr>
      <tr>
        <td width="15%" align="left" class="text_negro">Medicamento </td>
        <td width="85%" height="20" align="left"><input name="medicamento" value="<%= receta.getMedicamento() %>" type="text" class="form-control" size="20" maxlength="100" readonly ></td>
      </tr>
      <tr>
        <td width="15%" align="left" class="text_negro">Posologia </td>
        <td width="85%" height="20" align="left"><input name="posologia" value="<%= receta.getPosologia() %>" type="text" class="form-control" size="20" maxlength="100" readonly ></td>
      </tr>
      <tr>
        <td width="15%" align="left" class="text_negro">Via Administración </td>
        <td width="85%" height="20" align="left"><input name="viaAdministracion" value="<%= receta.getViaAdministracion() %>" type="text" class="form-control" size="20" maxlength="100" readonly ></td>
      </tr>
      <tr>
        <td width="15%" align="left" class="text_negro">Observacion </td>
        <td width="85%" height="20" align="left"><input name="observacion" value="<%= receta.getObservacion() %>" type="text" class="form-control" size="20" maxlength="100" readonly ></td>
      </tr>
      <tr>
        <td width="15%" align="left" class="text_negro">Id Consulta </td>
        <td width="85%" height="20" align="left"><input name="idConsulta" value="<%= receta.getIdConsulta() %>" type="text" class="form-control" size="20" maxlength="100" readonly ></td>
      </tr>
      <input type="hidden" name="paginador" id="paginador" maxlength="5" value="<%= paginador%>">
      <input type="hidden" name="parametro" id="parametro" maxlength="5" value="<%= parametro%>">
      <input type="hidden" name="valor" id="valor" maxlength="5" value="<%=valor%>">
    </table>
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


