<%@page import="ModeloDAO.CitasDAO"%>
<%@page import="ModeloDAO.EspecialidadesMedicasDAO"%>
<%@page import="Modelo.EspecialidadesMedicas"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="Modelo.Consultorio"%>
<%@page import="ModeloDAO.ConsultorioDAO"%>
<%@page import="ModeloDAO.MedicoDAO"%>
<%@page import="Modelo.Medico"%>
<%@page import="Modelo.Paciente"%>
<%@page import="ModeloDAO.PacienteDAO"%>
<%@page import="Modelo.MotivoConsulta"%>
<%@page import="ModeloDAO.MotivoConsultaDAO"%>
<%@page import="Modelo.TipoCita"%>
<%@page import="ModeloDAO.TipoCitaDAO"%>
<%@page import="ModeloDAO.HorarioCitasDiaDAO"%>
<%@page import="Modelo.HorarioCitasDia"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>



<!--
  ARCHIVO: vistas/citascit_agr.jsp
  @AUTOR: Grupo de desarrollo 3 - Mision Mintic
  FECHA DE CREACION: 25-09-2021
  APLICACIÓN: Contabilidad - Academusoft 3.2
  CASO DE USO: xxx
  DOCUMENTO: xxx
-->
<%@page import="java.util.*"%>
<%@page import="Modelo.Citas"%>
<%
    String grabar = (String)request.getParameter("grabar");
    JOptionPane.showMessageDialog(null, grabar, "Resultado de la Grabar", JOptionPane.INFORMATION_MESSAGE);
    if(request.getParameter("grabar")==null){
        grabar = "0";
    }
    JOptionPane.showMessageDialog(null, grabar, "Resultado de la Grabar", JOptionPane.INFORMATION_MESSAGE);
    Citas citas = new Citas();
    String parametro = (String)request.getParameter("parametro");
    String paginador = (String)request.getParameter("paginador");
    String valor = (String)request.getParameter("valor");
    String identif = (String)request.getParameter("idPaciente");

    PacienteDAO personaDAO = new PacienteDAO();
    Paciente paciente = new Paciente();
    
    paciente = (Paciente)personaDAO.consultar(identif);
    citas.setIdPaciente(paciente.getPers_id()); 
    citas.setPaciente(paciente.getNombre()); 
    //JOptionPane.showMessageDialog(null, citas.getPaciente(), "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
   //citas.setMedico(paciente.getNombre());
    /*citas.setPaciente("");
    citas.setIdHorarioCitas(0);
    citas.setHora("");
    citas.setIdMedico(0);
    citas.setMedico("");
    citas.setIdConsultorio(0);
    citas.setLugar("");
    citas.setIdTipoCita(0);
    citas.setTipoCita("");
    citas.setObservacion("");
    citas.setRegistradoPor("");
    citas.setFechaCambio(java.sql.Date.valueOf(java.time.LocalDate.now()));
    citas.setFecha("");
    citas.setMotivoConsulta("");
    citas.setIdMotivoConsulta(0);*/
    
    /*if(request.getParameter("idPaciente")== null){
        citas.setIdPaciente(0);
    }else{
        citas.setIdPaciente(Integer.parseInt((String)request.getParameter("idPaciente")));
    }*/
            
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
 
    if(request.getParameter("usuario")==null){
        citas.setRegistradoPor("");  
    }else{
        citas.setRegistradoPor((String)request.getParameter("usuario"));  
    }
    
    if(request.getParameter("fecha")==null){
        citas.setFecha(timeStamp);  
    }else{
        citas.setFecha((String)request.getParameter("fecha"));  
    }
      
    if(request.getParameter("horario")==null){
        citas.setIdHorarioCitas(0);
    }else{
        citas.setIdHorarioCitas(Integer.parseInt((String)request.getParameter("horario")));
    }

    if(request.getParameter("idEspecialidad")==null){
        citas.setIdEspecialidad(0);
    }else{
        citas.setIdEspecialidad(Integer.parseInt((String)request.getParameter("idEspecialidad")));
    }


    /*if(request.getParameter("medico")==null){
        citas.setMedico("");
    }else{
        citas.setMedico((String)request.getParameter("medico"));  
    }*/
           
    if(request.getParameter("idConsultorio") == null){
        citas.setIdConsultorio(0);
    }else{
        citas.setIdConsultorio(Integer.parseInt((String)request.getParameter("idConsultorio")));
    }

    if(request.getParameter("consultorio") == null){
        citas.setLugar("");
    }else{
        citas.setLugar(((String)request.getParameter("consultorio")));
    }
    
    if(request.getParameter("idTipoCita") == null){
        citas.setIdTipoCita(0);
    }else{
        citas.setIdTipoCita(Integer.parseInt((String)request.getParameter("idTipoCita")));
    }
         
    if(request.getParameter("motivoConsulta")==null){
        citas.setMotivoConsulta("");
    }else{
        citas.setMotivoConsulta((String)request.getParameter("motivoConsulta"));  
    }

    if(request.getParameter("observacion")==null){
        citas.setObservacion("");
    }else{
        citas.setObservacion((String)request.getParameter("observacion"));  
    }

    EspecialidadesMedicasDAO especialidadesDAO = new EspecialidadesMedicasDAO();
    ArrayList<EspecialidadesMedicas> listaEspecialidades = new ArrayList<>();
    listaEspecialidades = (ArrayList)especialidadesDAO.listar();
    EspecialidadesMedicas especialidad1 = new EspecialidadesMedicas(); 

    String idEsp = String.valueOf(request.getParameter("idEspecialidad"));
       //JOptionPane.showMessageDialog(null, idEsp, "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
    if(request.getParameter("idEspecialidad") == null && listaEspecialidades.size()>0){
        idEsp = String.valueOf(listaEspecialidades.get(0).getIdEspecialidadesMedicas());
    }
    
    MedicoDAO medicoDAO = new MedicoDAO();
    ArrayList<Medico> medicosPorEspecialidad = new ArrayList<>();
    medicosPorEspecialidad = (ArrayList)medicoDAO.listarConParametro(idEsp);

    if((String.valueOf(request.getParameter("Medico")).equals(""))||(request.getParameter("Medico"))==null){
        if(medicosPorEspecialidad.size()>0){
            citas.setIdMedico(medicosPorEspecialidad.get(0).getIdPers());
        }else{
            citas.setIdMedico(0);
        }  
    }else{
        citas.setIdMedico(Integer.parseInt( String.valueOf(request.getParameter("Medico"))));
    }
    
    
    TipoCitaDAO tipoCitaDAO = new TipoCitaDAO();
    ArrayList<TipoCita> tipoCitas = new ArrayList<>();
    tipoCitas = (ArrayList)tipoCitaDAO.listar();
    
    MotivoConsultaDAO motivoDAO = new MotivoConsultaDAO();
    ArrayList<MotivoConsulta> motivos = new ArrayList<>();
    motivos = (ArrayList)motivoDAO.listar();
    
        PacienteDAO pacienteDAO = new PacienteDAO(); 
        Paciente paciente1 = new Paciente();

        ArrayList< Paciente > listaPacientes = new ArrayList<>();    
        listaPacientes =(ArrayList)pacienteDAO.listar();
        
        /*int idMed = citas.getIdMedico();

    //if(request.getParameter("Medico") != null){
       // idMed = Integer.parseInt(request.getParameter("Medico"));
    //}

    //JOptionPane.showMessageDialog(null, idMed, "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
    if(medicosPorEspecialidad.size()>0 && request.getParameter("Medico")!=null){
        citas.setIdMedico(Integer.parseInt(request.getParameter("Medico")));
    }else{
        citas.setIdMedico(0);
    }*/
    



        ConsultorioDAO consultorioDAO = new ConsultorioDAO(); 
        Consultorio consultorio = new Consultorio();

        ArrayList< Consultorio > listaConsultorios = new ArrayList<>();    
        listaConsultorios =(ArrayList)consultorioDAO.listar();
        
    String paramHorario = citas.getFecha()+citas.getIdMedico();
    String fecha = "";
    String idMedico = "";
    if(paramHorario.length()>10){
        fecha = paramHorario.substring(0,10);
        idMedico = paramHorario.substring(10,paramHorario.length());
    }
//JOptionPane.showMessageDialog(null, paramHorario, "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
    
//JOptionPane.showMessageDialog(null, fecha, "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
//JOptionPane.showMessageDialog(null, idMedico, "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);

        HorarioCitasDiaDAO horarioDAO = new HorarioCitasDiaDAO();
        ArrayList<HorarioCitasDia> horarios = new ArrayList<>();
        if(fecha!=null){
            if(idMedico!=null){
                horarios = (ArrayList)horarioDAO.listarConParametro(paramHorario);


                if(request.getParameter("horario") == null && horarios.size()>0){
                    citas.setIdConsultorio(horarios.get(0).getIdConsultorio());
                    //JOptionPane.showMessageDialog(null,  citas.getIdConsultorio(), "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE); 
                }else{
                    citas.setIdConsultorio(Integer.parseInt(request.getParameter("idConsultorio")));

                    for(int i=0;i<horarios.size();i++){ 
                        HorarioCitasDia horario = (HorarioCitasDia)horarios.get(i);

                        if( horario.getIdHorarioCitasDia() == Integer.parseInt(request.getParameter("horario"))){     
                            citas.setIdConsultorio(horarios.get(i).getIdConsultorio());
                        }
                    }
                }
                consultorio = (Consultorio)consultorioDAO.consultar( String.valueOf(citas.getIdConsultorio()));
                citas.setLugar("["+consultorio.getCiudad()+"] "+consultorio.getDireccion()+" ["+consultorio.getNombre()+"]");




                 //JOptionPane.showMessageDialog(null, horarios.size(), "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);   
           }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar un médico.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);   
            }
        }else{
            //JOptionPane.showMessageDialog(null, "El campo fecha debe tener un valor.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
        }
    String mensaje  = "";
    //JOptionPane.showMessageDialog(null, grabar+"00000", "Resultado de la Grabar", JOptionPane.INFORMATION_MESSAGE);
    if(grabar.equals("1")){
        CitasDAO citaDAO = new CitasDAO();
       
        //JOptionPane.showMessageDialog(null, citas.getRegistradoPor(), "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
        mensaje = citaDAO.add(citas); 
        if(mensaje.equals("")){
            //JOptionPane.showMessageDialog(null, "La cita ha sido almacenada exitodamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, mensaje, "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
        }
        grabar = "0";
    }
%>
<script language="javascript" src="../jScripts/cargaPopup.js"></script>
<script language="javascript" src="../jScripts/validaFechas.js"></script>
<script language = "javascript">

function recargar(frm){
    frm.paciente.value = "";
    
    <%for(int i=0;i<listaPacientes.size();i++){ 
        paciente1 = (Paciente)listaPacientes.get(i);
    %>
     if (frm.idPaciente.value ==<%= paciente1.getPers_id()%>){     
        frm.paciente.value = "<%= paciente1.getNombre()%>";
     }
  <%}%>  
  
}

function cambiarValorGrabar(){
    document.getElementById("grabar").value = '1';
}
        
function recargar2(frm){
    //frm.idEspecialidad.value = "";
    //alert(frm.idEspecialidad.value);
    <% 
     for(int i=0;i<listaEspecialidades.size();i++){ 
        especialidad1 = (EspecialidadesMedicas)listaEspecialidades.get(i);
    %>
     if (frm.idEspecialidad.value ==<%= especialidad1.getIdEspecialidadesMedicas()%>){     
        frm.idEspecialidad.value = "<%= especialidad1.getNombre()%>";
     }
  <%}%>  
}
  
  
function validar(frm) {
    if (frm.idPaciente.value==""){
        alert("El campo id Paciente debe tener un valor.");
        document.getElementById("idPaciente").focus();
    }else 
        if (frm.paciente.value==""){
            alert("El campo id Paciente debe tener un valor válido.");
            document.getElementById("idPaciente").focus();
        }else 
            if (frm.fecha.value==""){
                alert("El campo Fecha debe tener un valor válido.");
                document.getElementById("fecha").focus();
            }else 
                if (frm.horario.value==""){
                    alert("El campo Hora debe tener un valor.");
                    document.getElementById("horario").focus();
                }else 
                    if (frm.idMedico.value==""){
                        alert("El campo id Medico debe tener un valor.");
                        document.getElementById("idMedico").focus();
                    }else 
                       if (frm.Medico.value==""){
                            alert("El campo Id Médico debe tener un valor válido.");
                            document.getElementById("idMedico").focus();
                        }else 
                            if (frm.idConsultorio.value==""){
                                alert("El campo Consultorio debe tener un valor.");
                                document.getElementById("idConsultorio").focus();
                            }else 
                                if (frm.idTipoCita.value==""){
                                    alert("El campo Tipo Cita debe tener un valor.");
                                    document.getElementById("idTipoCita").focus();
                                }else 
                                    if (frm.motivoConsulta.value==""){
                                        alert("El campo Motivo Consulta debe tener un valor.");
                                        document.getElementById("motivoConsulta").focus();
                                    }else{
                                        document.getElementById("grabar").value="1";
                                        alert(document.getElementById("grabar").value);
                                        //document.form1.submit();
                                    } 
} 
        

</script>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Adicionar Citas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
  <script>
    </script>
    </head>
<body>

<form id="form1" action="cita_usuario.jsp">
    <center>
  
    <table style="width: 90%">
      <tr>
        <td style="width: 100%;" align="left">
          <p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"><strong>DATOS DE LA CITA MÉDICA</strong></p>
        </td>
      </tr>
    </table>
    <table style="width: 90%">

        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-2">
                        <label for="idPaciente" class="form-label"><strong>Id Paciente</strong></label>
                        <input type="text" class="form-control" name="idPaciente" id="idPaciente" value="<%= citas.getIdPaciente() %>" size="10" maxlength="10" onChange="javascript:recargar(document.forms['form1']);" readOnly>
                    </div>
                    <div class="col-md-4">
                        <label for="paciente" class="form-label"><strong>Nombre Paciente</strong></label>
                        <input name="paciente" id="paciente" value="<%= citas.getPaciente() %>" type="text" class="form-control" size="50" maxlength="50" readonly>
                    </div>
                    <div class="col-md-2">
                        <label for="fecha" class="form-label"><strong>Fecha</strong></label>
                        <input name="fecha" id="fecha" value="<%= citas.getFecha() != null ? citas.getFecha() : "" %>" placeholder="YYYY-MM-DD" type="text" class="form-control" size="50" maxlength="50" disabled="yes">
                        <a href="javascript:fecha.popup();"> </a>                                               
                    </div>                     
                    <div class="col-md-4">
                        <label for="idEspecialidad" class="form-label"><strong>Fecha</strong></label>
                        <input name="idEspecialidad" id="idEspecialidad" value="<%= citas.getEspecialidad()%>" placeholder="YYYY-MM-DD" type="text" class="form-control" size="50" maxlength="50" disabled="yes">
                     </div>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-2">
                        <label for="idMedico" class="form-label"><strong>Id Médico</strong></label>
                        <input type="text" class="form-control" name="idMedico" id="idMedico" value="<%= citas.getIdMedico() %>" size="10" maxlength="10" readOnly>
                    </div>
                     <div class="col-md-4">
                        <label for="Medico" class="form-label"><strong>Id Médico</strong></label>
                        <input type="text" class="form-control" name="Medico" id="Medico" value="<%= citas.getMedico()%>" size="10" maxlength="10" disabled="yes">
                    </div>
                    <div class="col-md-2">
                        <label for="horario" class="form-label"><strong>Hora</strong></label>
                        <input type="text" class="form-control" name="horario" id="horario" value="<%= citas.getHora()%>" size="10" maxlength="10" disabled="yes">
                    </div>
                    <div class="col-md-4">
                        <label for="idConsultorio" class="form-label"><strong>Consultorio</strong></label>
                        <input name="consultorio" id="consultorio" value="<%= citas.getLugar() %>" type="text" class="form-control" size="50" maxlength="100" disabled="yes">
                        <input name="idConsultorio" id="idConsultorio" value="<%= citas.getIdConsultorio()%>" type="hidden" class="form-control" size="50" maxlength="100">               
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-12">
                        <label for="observacion" class="form-label"><strong>Observación</strong></label>
                        <input type="text" class="form-control" name="observacion" id="observacion" value="<%= citas.getObservacion() %>" size="100" maxlength="200" disabled="yes">
                    </div>      
                </div>
            </td>
        </tr>

        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-4">
                        <label for="tipoCita" class="form-label"><strong>Tipo Cita</strong></label>
                        <input type="text" class="form-control" name="tipoCita" id="tipoCita" value="<%= citas.getTipoCita() %>" size="50" maxlength="50" disabled="yes">
                    </div>
                    <div class="col-md-8">
                        <label for="motivoConsulta" class="form-label"><strong>Motivo Consulta</strong></label>
                        <input type="text" class="form-control" name="motivoConsulta" id="motivoConsulta" value="<%= citas.getMotivoConsulta() %>" size="100" maxlength="200" disabled="yes">
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
          <td style="width: 6%;" valign="top"> 
              <input type="submit" name="accion" id="accion" value="Guardar" class="btn btn-primary btn-sm" onclick="javascript:validar(document.forms['form1'])">
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