<!--
  ARCHIVO: vistas/pais_edit.jsp
  @AUTOR: Grupo de desarrollo 3 - Mision Mintic
  FECHA DE CREACION: 09-10-2021
  APLICACIÓN: Contabilidad - Academusoft 3.2
  CASO DE USO: xxx
  DOCUMENTO: xxx
-->
<%@page import="java.util.*"%>
<%@page import="Modelo.Pais"%>
<%@page import="ModeloDAO.PaisDAO"%>
<%
    PaisDAO paisDAO = new PaisDAO();
    String id = (String)request.getParameter("id");
    Pais pais = (Pais) paisDAO.consultar(id);
    String parametro = (String)request.getParameter("parametro");
    String paginador = (String)request.getParameter("paginador");
    String valor = (String)request.getParameter("valor");

    if(parametro==null){;
        parametro="";
    }
    if(paginador==null){
        paginador="";
    }
    String usuario = "";
    if(request.getParameter("usuario")!=null){
        usuario=(String)request.getParameter("usuario");  
    }
%>
<script language="javascript" src="../jScripts/validaFechas.js"></script>
<script language = "javascript">
</script>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Adicionar Pais</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
  <script>
    </script>
    </head>
<body>
<form name ="form1" action="ControlPais">
    <center>
  <h5><strong>             Pais</strong></h5>
    <table style="width: 90%">
      <tr>
        <td style="width: 6%;" valign="top" >
          <a href="ControlPais?accion=Buscar&parametro=<%=parametro%>&paginador=<%=paginador%>&valor=0" class="btn btn-primary btn-sm"> <strong>Volver</strong></a>
          <input name="usuario" id="usuario" value="<%= usuario%>" type="hidden" class="form-control" size="50" maxlength="100">                     
        </td>
        <td style="width: 94%;" align="left">
          <input type="hidden" name="valor" id="valor" maxlength="5" value="">
          <p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Modificar Registro</strong></p>
        </td>
      </tr>
    </table>
    <table width="90%" border="0" cellspacing="1" cellpadding="2">
      <tr>
        <td width="15%" align="left" class="text_negro">Id Pais</td>
        <td width="85%" align="left" class="text_negro"><strong>Nombre del Pa&iacute;s</strong></td>
      </tr>
      <tr>
          <td width="15%" height="20" align="left"><input name="id" value="<%= pais.getIdPais() %>" type="text" class="form-control" size="20" maxlength="100" readonly="yes"></td>
        <td width="85%" height="20" align="left"><input name="nombre" value="<%= pais.getNombre() %>" type="text" class="form-control" size="20" maxlength="100"></td>
      </tr>
      <tr>
        <td width="15%" align="left" class="text_negro"> </td>
        <td width="85%" align="left" class="text_negro"><strong> </strong></td>
      </tr>
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
    </center>
</form>
    
</body>
</html> 
