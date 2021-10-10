<%@page import="Modelo.Usuario"%>
<%@page import="ModeloDAO.UsuarioDAO"%>

<!--
  ARCHIVO: vistas/usuariocit_agr.jsp
  @AUTOR: Grupo de desarrollo 3 - Mision Mintic
  FECHA DE CREACION: 25-09-2021
  APLICACIÓN: Contabilidad - Academusoft 3.2
  CASO DE USO: xxx
  DOCUMENTO: xxx
-->
<%@page import="java.util.*"%>
<%@page import="Modelo.Usuario"%>
<%
    Usuario usuario = new Usuario();
    String parametro = (String)request.getParameter("parametro");
    String paginador = (String)request.getParameter("paginador");
    String valor = (String)request.getParameter("valor");
 
            
    if(request.getParameter("identificacion")==null){
        usuario.setIdentificacion("");
    }else{
        usuario.setIdentificacion((String)request.getParameter("identificacion"));
    }
      
    if(request.getParameter("nombre")==null){
        usuario.setNombre("");
    }else{
        usuario.setNombre((String)request.getParameter("nombre"));  
    }
      
    if(request.getParameter("rol")==null){
        usuario.setIdRope(0);
    }else{
        usuario.setIdRope(Integer.parseInt((String)request.getParameter("rol")));
    }
    
    if(request.getParameter("usuario")==null){
        usuario.setUsuario("");
    }else{
        usuario.setUsuario((String)request.getParameter("usuario"));  
    }
         
    if(request.getParameter("contrasena")==null){
        usuario.setContrasena("");
    }else{
        usuario.setContrasena((String)request.getParameter("contrasena"));  
    }
    
    RolPersonaDAO rolPersonaDAO = new RolPersonaDAO(); 
    RolPersona rolPersona = new RolPersona();

        ArrayList< RolPersona > listaRolPersonas = new ArrayList<>();    
        listaRolPersonas =(ArrayList)consultorioDAO.listar();
%>
<script language="javascript" src="../jScripts/cargaPopup.js"></script>
<script language="javascript" src="../jScripts/validaNombres.js"></script>
<script language = "javascript">

function recargar(frm){
    frm.identificacion.value = "";
    
    <%for(int i=0;i<listaPersonas.size();i++){ 
        identificacion1 = (Identificacion)listaPersonas.get(i);
    %>
     if (frm.idIdentificacion.value ==<%= identificacion1.getPers_id()%>){     
        frm.identificacion.value = "<%= identificacion1.getNombre()%>";
     }
  <%}%>  
}

function recargar2(frm){
    frm.usuario.value = "";
    <%for(int i=0;i<listaUsuarios.size();i++){ 
        usuario1 = (Usuario)listaUsuarios.get(i);
    %>
     if (frm.idUsuario.value ==<%= usuario1.getIdPers()%>){     
        frm.usuario.value = "<%= usuario1.getNombre()%>";
     }
  <%}%>  
}
  
</script>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Adicionar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
  <script>
    </script>
    </head>
<body>

<form id="form1" action="ControlCita">
    <center>
  <h5><strong>             Usuario</strong></h5>
    <table style="width: 90%">
      <tr>
        <td style="width: 6%;" valign="top" >
          <a href="ControlCita?accion=Buscar&parametro=<%=parametro%>&paginador=<%=paginador%>&valor=0" class="btn btn-primary btn-sm"> <strong>Volver</strong></a>
        </td>
        <td style="width: 94%;" align="left">
          <p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>Adicionar Registro</strong></p>
        </td>
      </tr>
    </table>
    <table style="width: 90%">

        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-2">
                        <label for="identificacion" class="form-label"><strong>Nombre Identificacion</strong></label>
                        <input name="identificacion" id="identificacion" value="<%= usuario.getIdentificacion() %>" type="text" class="form-control" size="50" maxlength="50" readonly>
                    </div>
                    <div class="col-md-3">
                        <label for="Nombre" class="form-label"><strong>Nombre</strong></label>
                        <input name="Nombre" id="Nombre" value="<%= usuario.getNombre() != null ? usuario.getNombre() : "" %>" type="text" class="form-control" size="50" maxlength="50">                                             
                    </div>                     
                    <div class="col-md-2">
                        <label for="idRope" class="form-label"><strong>Rol</strong></label>
                        <select id="idRope" name="idRope" class="form-select">
                            <%
                                if(listaRolPersonas.size()>0){
                                    for(int i=0;i<listaRolPersonas.size();i++){ 
                                        rolPersona = (RolPersona)listaRolPersonas.get(i);
                                        if( rolPersona.getIdRope()==usuario.getIdRope()) { %>
                                            <option value="<%= rolPersona.getIdRope()%>" selected ><%= rolPersona.getNombre()%></option>
                                    <% } else { %>
                                        <option value="<%= rolPersona.getIdRope()%>">[<%= rolPersona.getCiudad()%>]<%= rolPersona.getDireccion()%>-<%= rolPersona.getNombre()%></option>
                                    <% } 
                                    }
                                }%>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label for="identificacion" class="form-label"><strong>Nombre Identificacion</strong></label>
                        <input name="identificacion" id="identificacion" value="<%= usuario.getIdentificacion() %>" type="text" class="form-control" size="50" maxlength="50" readonly>
                    </div>
                    <div class="col-md-3">
                        <label for="Nombre" class="form-label"><strong>Nombre</strong></label>
                        <input name="Nombre" id="Nombre" value="<%= usuario.getNombre() != null ? usuario.getNombre() : "" %>" type="text" class="form-control" size="50" maxlength="50">                                             
                    </div>                     
                </div>
            </td>
        </tr>
        <input type="hidden" name="paginador" id="paginador" maxlength="5" value="<%= paginador%>">
        <input type="hidden" name="parametro" id="parametro" maxlength="5" value="<%= parametro%>">
        <input type="hidden" name="valor" id="valor" maxlength="5" value="<%=valor%>"> 
    </table>
    <br>
    <table width="90%" border="0" cellspacing="1" cellpadding="2">
      <tr>
          <td style="width: 94%;"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"></p></td>
          <td style="width: 6%;" valign="top" >
              <input type="submit" name="accion" id="accion" value="Agregar" class="btn btn-primary btn-sm">
          </td>
      </tr>
    </table>
    </center>
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

        <!-- Include Date Range Picker -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
        
        <script>
            $(document).ready(function(){
                var date_input=$('input[name="fecha"]'); //our date input has the name "date"
                var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
                date_input.datepicker({
                    format: 'yyyy-mm-dd',
                    container: container,
                    todayHighlight: true,
                    autoclose: true,
                })
            }) 
        </script>
</form>
</body>
</html>




<!--
  25-09-2021  @:GRUPO DE DESARROLLO 3  &:Creación
--> 