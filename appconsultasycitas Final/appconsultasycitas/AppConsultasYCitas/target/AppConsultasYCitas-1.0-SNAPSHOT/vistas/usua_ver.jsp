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
      document.login.usuario.focus()
    }

    function remitirForm() {
      document.login.submit()
    }

    function restablecerForm() {
      document.login.reset()
      document.login.usuario.focus()
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
                     usuario = (Usuario)listaUsuarios.get(i);
                %>
                    if(frm.usuario.value == '<%= usuario.getUsuario()%>'){ 
                        if(frm.password.value == '<%= usuario.getContrasena()%>'){    
                            esUsuario = true;
                            frm.idPaciente.value = '<%= usuario.getIdentificacion()%>';
                        }
                    }
                <%}%> 

                if(esUsuario==true){
                    frm.setAttribute("action","vistas/cita_usuario.jsp");
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

        
<Form name="login" id="login" method="POST" action="vistas/cita_usuario.jsp">
    <center>   
        <a href="login.jsp"></a>
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
                            <strong>INICIO DE SESIÓN</strong></center>
                        </div>
                        <div class="col-md-4">
                            &nbsp;
                        </div>
                    </div>
                </td>
            </tr>    
        </thead>         
        <tbody>
            <tr>
                <td>
                    <div class="row g-6">
                        <div class="col-md-3">
                            &nbsp;
                        </div>
                        <div class="col-md-3">
                            &nbsp;
                        </div>
                        <div class="col-md-3">
                            &nbsp;
                        </div>
                        <div class="col-md-3">
                            &nbsp;
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="row g-6">
                        <div class="col-md-3">
                            &nbsp;
                        </div>
                        <div class="col-md-3">
                            <label for="Usuario" class="form-label"><B>Usuario</B></label>
                            <input type="text" class="form-control" id="usuario" name="usuario" aize="15" maxlength="15" >
                        </div>
                        <div class="col-md-3">
                          <label for="password" class="form-label"><B>Contraseña</B></label>
                          <input type="password" class="form-control" id="password" name="password" aize="15" maxlength="15" >
                          <input type="hidden" class="form-control" id="idPaciente" name="idPaciente" aize="15" maxlength="15" >
                       </div>
                        <div class="col-md-3">
                            &nbsp;
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="row g-6">
                        <div class="col-md-4">
                            &nbsp;
                        </div>
                        <div class="col-md-4">
                            <center>
                                <font size=+1>
                                <input type="button" name="boton1" id="boton1" value="INICIAR SESION" class="btn btn-primary btn-me" onclick="javascript:buscarUsuario(document.forms['login'])"> 
                                </font>
                            </center>
                        </div>
                        <div class="col-md-4">
                            &nbsp;
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
</Form>
</CENTER>
</body>
</html>
    </body>
</html>






