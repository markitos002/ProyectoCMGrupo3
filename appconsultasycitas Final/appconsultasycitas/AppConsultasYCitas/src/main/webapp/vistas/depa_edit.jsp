<%@page import="ModeloDAO.PaisDAO"%>
<%@page import="Modelo.Pais"%>
<!--
  ARCHIVO: vistas/dep_dep_agr.jsp
  @AUTOR: Grupo de desarrollo 3 - Mision Mintic
  FECHA DE CREACION: 10-10-2021
  APLICACIÓN: Contabilidad - Academusoft 3.2
  CASO DE USO: xxx
  DOCUMENTO: xxx
-->
<%@page import="java.util.*"%>
<%@page import="Modelo.Departamento"%>
<%@page import="ModeloDAO.DepartamentoDAO"%>
<%
    DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    String id = (String)request.getParameter("id");
    Departamento departamento = (Departamento) departamentoDAO.consultar(id);
    
    String parametro = (String)request.getParameter("parametro");
    String paginador = (String)request.getParameter("paginador");
    String valor = (String)request.getParameter("valor");

    if(parametro==null){;
        parametro="";
    }
    if(paginador==null){
        paginador="1";
    }

    PaisDAO paisDAO = new PaisDAO();
    ArrayList<Pais> listaPaises = new ArrayList<>();
    listaPaises = (ArrayList)paisDAO.listar();

    /*if(request.getParameter("departamento")==null){
        departamento.setNombre("");
    }
    
    if(request.getParameter("idPais")==null){
        departamento.setIdPais(0);
*/
%>
<script language="javascript" src="../jScripts/cargaPopup.js"></script>
<script language = "javascript">
</script>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Adicionar Departamento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
  <script>
    </script>
    </head>
<body>
<form name ="form1" action="ControlDepartamento">
    <center>
        <h5><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Departamento</strong></h5>
    <table style="width: 90%">
      <tr>
        <td style="width: 6%;" valign="top" >
          <a href="ControlDepartamento?accion=Buscar&parametro=<%=parametro%>&paginador=<%=paginador%>&valor=0" class="btn btn-primary btn-sm"> <strong>Volver</strong></a>
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
                        <label for="id" class="form-label"><strong>Id</strong></label>
                        <input name="id" id="id" value="<%= departamento.getIdDepartamento() %>" type="text" class="form-control" size="50" maxlength="50" readonly>
                    </div>
                    <div class="col-md-6">
                        <label for="nombre" class="form-label"><strong>Nombre Departamento/Provincia/Estado</strong></label>
                        <input name="nombre" id="nombre" value="<%= departamento.getNombre() %>" type="text" class="form-control" size="50" maxlength="50">
                    </div>
                    <div class="col-md-4">
                        <label for="idPais" class="form-label"><strong>País</strong></label>
                        <select id="idPais" name="idPais" class="form-select">
                            <option value="0"></option>
                            <%
                                Pais pais = new Pais();
                                if(listaPaises.size()>0){
                                    for(int i=0;i<listaPaises.size();i++){ 
                                        pais = (Pais)listaPaises.get(i);%>
                                    <%    if( pais.getIdPais()==departamento.getIdPais()) { %>
                                            <option value="<%= pais.getIdPais()%>" selected ><%= pais.getNombre()%></option>
                                    <%  } else { %>
                                        <option value="<%= pais.getIdPais()%>"><%= pais.getNombre()%></option>
                                    <%  } 
                                    }
                                }else{%>
                                    <option value="0"></option>
                              <%}%>                                   
                        </select>
                    </div>
                    </div>      
                </div>
            </td>
        </tr>    
        <input type="hidden" name="paginador" id="paginador" maxlength="5" value="<%= paginador%>">
        <input type="hidden" name="parametro" id="parametro" maxlength="5" value="<%= parametro%>">
        <input type="hidden" name="valor" id="valor" maxlength="5" value="<%=valor%>"> 
        <input name="usuario" id="usuario" value="<%= departamento.getRegistradoPor()%>" type="hidden" class="form-control" size="50" maxlength="100">               
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
  10-10-2021  @:GRUPO DE DESARROLLO 3  &:Creación
--> 