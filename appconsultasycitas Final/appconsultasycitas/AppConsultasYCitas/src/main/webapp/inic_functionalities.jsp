<%-- 
    Document   : add
    Created on : 10/09/2021, 8:18:19 a. m.
    Author     : jacun
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsuarioDAO dao = new UsuarioDAO();
    List<Usuario> listaUsuarios = new ArrayList<>();
    Usuario usuario = new Usuario();
    listaUsuarios = dao.listar();
    
    if(request.getParameter("usuario")==null){
        usuario.setUsuario("");  
    }else{
        usuario.setUsuario((String)request.getParameter("usuario"));  
    }

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
 
  </script>
    <body bgcolor="#c0c0c0" link="#999999" vlink="#999999" alink="#999999" ">

 <Form name="admin" id="admin" action="ControlAdmin">
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
                            <input name="usuario" id="usuario" value="<%= usuario.getUsuario()%>" type="hidden" class="form-control" size="50" maxlength="100">                     
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
                    <div class="col-md-1">
                        <center><input type="submit" name="accion" id="accion" value="01" class="btn btn-primary"></center>
                    </div>
                    <div class="col-md-3" style="background-color: #C4E3FB;">
                        <h5>Motivos de Consulta</h5>
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
                    <div class="col-md-1">
                        <center><input type="submit" name="accion" id="accion" value="02" class="btn btn-primary"></center>
                    </div>
                    <div class="col-md-3" style="background-color: #C4E3FB;">
                        <h5>Tipo de Citas</h5>
                    <div class="col-md-4">
                    </div>                     
                </div>
            </td>           
        </tr>               
        <tr>
            <td >
                <div class="row g-6" >
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-1" style=" align-items: flex-end;">
                        <center><input type="submit" name="accion" id="accion" value="03" class="btn btn-primary"></center>
                    </div>
                    <div class="col-md-3" style="background-color: #C4E3FB;">
                        <h5>Citas Médicas<h5>
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
                    <div class="col-md-1">
                        <center><input type="submit" name="accion" id="accion" value="04" class="btn btn-primary"></center>
                    </div>
                    <div class="col-md-3" style="background-color: #C4E3FB;">
                        <h5>Departamentos</h5>
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
                    <div class="col-md-1">
                        <center><input type="submit" name="accion" id="accion" value="05" class="btn btn-primary"></center>
                    </div>
                    <div class="col-md-3" style="background-color: #C4E3FB;">   
                        <h5>Usuario</h5>
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
                    <div class="col-md-1">
                        <center><input type="submit" name="accion" id="accion" value="06" class="btn btn-primary"></center>
                    </div>
                    <div class="col-md-3" style="background-color: #C4E3FB;"> 
                        <h5>Recetas</h5>
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
                    <div class="col-md-1">
                        <center><input type="submit" name="accion" id="accion" value="07" class="btn btn-primary"></center>
                    </div>
                    <div class="col-md-3" style="background-color: #C4E3FB;">
                        <h5>Países</h5>
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
                    <div class="col-md-1">
                        <center><input type="submit" name="accion" id="accion" value="08" class="btn btn-primary"></center>
                    </div>
                    <div class="col-md-3" style="background-color: #C4E3FB;">   
                        <h5>Ciudades</h5>
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






