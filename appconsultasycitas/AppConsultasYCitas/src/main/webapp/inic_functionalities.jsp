<%-- 
    Document   : add
    Created on : 10/09/2021, 8:18:19 a. m.
    Author     : jacun
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.UsuarioDAO"%>
<%@page import="Modelo.TipoCita"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsuarioDAO dao = new UsuarioDAO();
    List<Usuario> listaUsuarios = new ArrayList<>();
    Usuario usuario = new Usuario();
    listaUsuarios = dao.listar();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adicionar Tipo de Cita Médica</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>

    </head>
  <script language="JavaScript">

   /* function darEnfoque() {
      document.admin.usuario.focus()
    }

    function remitirForm() {
      document.admin.submit()
    }

    function restablecerForm() {
      document.admin.reset()
      document.admin.usuario.focus()
    }*/
    
    function buscarUsuario(frm){
        esUsuario = false;
        if(frm.usuario.value===''){
            alert("El campo Usuario es obligatorio");
            frm.usuario.value='';
            frm.usuario.focus();
        }else
            if(frm.password.value===''){
                alert("El campo Contraseña es obligatorio");
                frm.password.focus();
            }else{
                <%for(int i=0;i<listaUsuarios.size();i++){ 
                    usuario = (Usuario)listaUsuarios.get(i);%>
                    
                    if(frm.usuario.value == '<%= usuario.getUsuario()%>'){ 
                        if(frm.password.value == '<%= usuario.getContrasena()%>'){    
                            esUsuario = true;
                            frm.idPersona.value = '<%= usuario.getIdentificacion()%>';
                            frm.paginaInicio.value = '<%= usuario.getPaginaInicio()%>';
                        }
                    }
                    
                <%}%> 
                alert(frm.paginaInicio.value);
                if(esUsuario==true){
                    frm.setAttribute("action", frm.paginaInicio.value);
                    frm.submit();
                }else{
                    alert("Usuario o contraseña no válidos. Intente de Nuevo.");
                    frm.usuario.value='';
                    frm.password.value='';
                    frm.usuario.focus();            
                }
            }
    }
 
  </script>
    <body bgcolor="#c0c0c0" link="#999999" vlink="#999999" alink="#999999" ">

 <Form name="admin" id="admin" action="Controlador">
    <center>   
    <table style="width: 88%; align-content: center">
        <div class="row g-6" >                        
            <div class="col">
                <td style="width: 100%;"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><b>APP DE CITAS Y CONSULTAS MÉDICAS</B></p></td>
            </div>
        </div>  
    </table>   
    <table class="table table-sm" style="width: 88%; align-content: center">
        <thead class="table-dark table-sm">
            <tr>
                <td>
                    <div class="row g-6">
                        <div class="col-md-4">
                            &nbsp;
                        </div>
                        <div class="col-md-4"><center>
                            <strong>ADMINISTRACION DEL SISTEMA</strong></center>
                        </div>
                        <div class="col-md-4">
                            
                        </div>
                    </div>
                </td>
            </tr>    
        </thead>         
        <tbody>           
        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-secondary">&nbsp;&nbsp;&nbsp;</button>
                        &nbsp;&nbsp;<a href="ControlCita?accion=Buscar&paginador=1"><strong>Citas Médicas</strong></a>
                   <div class="col-md-4">
                    </div>                     
                </div>
            </td>           
        </tr>               
        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-success">&nbsp;&nbsp;&nbsp;</button>
                        &nbsp;&nbsp;<a href="ControlTipoCita?accion=Buscar&paginador=1"><strong>Tipo Citas</strong></a> 
                    <div class="col-md-4">
                    </div>                     
                </div>
            </td>           
        </tr>               
        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-danger">&nbsp;&nbsp;&nbsp;</button> 
                        &nbsp;&nbsp;<a href="ControlMotivo?accion=Buscar&paginador=1"><strong>Motivos de Consulta</strong></a> 
                    </div>
                    <div class="col-md-4">
                    </div>                     
                </div>
            </td>           
        </tr>               
        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-warning">&nbsp;&nbsp;&nbsp;</button>      
                        &nbsp;&nbsp;<a href="ControlUsuarios?accion=Ver"><strong>Usuario</strong></a> 
                    </div>
                    <div class="col-md-4">
                    </div>                     
                </div>
            </td>           
        </tr>               
        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-info">&nbsp;&nbsp;&nbsp;</button>   
                        &nbsp;&nbsp;<a href="ControlReceta?accion=Buscar&paginador=1"><strong>Recetas</strong></a> 
                    </div>
                    <div class="col-md-4">
                    </div>                     
                </div>
            </td>           
        </tr>               

        </tbody>
    </table>
                       
    <table style="width: 88%; align-content: center">
        <div class="row g-6" >                        
            <div class="col">
                <td style="width: 100%;"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"></p></td>
            </div>
        </div>  
    </table>   

    <BR>
    <BR>
    <BR>
</CENTER>
</Form>
        
</body>
</html>
    </body>
</html>






