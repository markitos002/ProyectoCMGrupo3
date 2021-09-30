<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Modelo.Consultorio"%>
<%@page import="ModeloDAO.ConsultorioDAO"%>
<%@page import="Modelo.Medico"%>
<%@page import="ModeloDAO.MedicoDAO"%>
<%@page import="Modelo.Paciente"%>
<%@page import="ModeloDAO.PacienteDAO"%>
<%@page import="Modelo.MotivoConsulta"%>
<%@page import="ModeloDAO.TipoCitaDAO"%>
<%@page import="Modelo.TipoCita"%>
<%@page import="ModeloDAO.MotivoConsultaDAO"%>
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
<%@page import="ModeloDAO.CitasDAO"%>
<%@page import="Modelo.HorarioCitasDia"%>
<%@page import="ModeloDAO.HorarioCitasDiaDAO"%>
<%
    CitasDAO citasDAO = new CitasDAO();
    int id = Integer.parseInt((String)request.getParameter("id"));
    String idStr = String.valueOf(id);
    Citas citas = (Citas) citasDAO.consultar(idStr);

    String date_time = citas.getFecha();
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = dateParser.parse(date_time);
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    citas.setFecha( dateFormatter.format(date));

    HorarioCitasDiaDAO horarioDAO = new HorarioCitasDiaDAO();
    ArrayList<HorarioCitasDia> horarios = new ArrayList<>();
    horarios = (ArrayList)horarioDAO.listar();

    TipoCitaDAO tipoCitaDAO = new TipoCitaDAO();
    ArrayList<TipoCita> tipoCitas = new ArrayList<>();
    tipoCitas = (ArrayList)tipoCitaDAO.listar();
    
    MotivoConsultaDAO motivoDAO = new MotivoConsultaDAO();
    ArrayList<MotivoConsulta> motivos = new ArrayList<>();
    motivos = (ArrayList)motivoDAO.listar();
    
    String parametro = (String)request.getParameter("parametro");
    String paginador = (String)request.getParameter("paginador");
    String valor = (String)request.getParameter("valor");

        PacienteDAO pacienteDAO = new PacienteDAO(); 
        Paciente paciente = new Paciente();

        ArrayList< Paciente > listaPacientes = new ArrayList<>();    
        listaPacientes =(ArrayList)pacienteDAO.listar();

        MedicoDAO medicoDAO = new MedicoDAO(); 
        Medico medico = new Medico();
        
        ArrayList< Medico > listaMedicos = new ArrayList<>();    
        listaMedicos =(ArrayList)medicoDAO.listar();
 
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(); 
        Consultorio consultorio = new Consultorio();

        ArrayList< Consultorio > listaConsultorios = new ArrayList<>();    
        listaConsultorios =(ArrayList)consultorioDAO.listar();
        
%>
    <%
    if(parametro==null){;
        parametro="";
    }
    if(paginador==null){
        paginador="";
    }
    %>

<script language = "javascript">

function recargar(frm){
    frm.paciente.value = "";
    <%for(int i=0;i<listaPacientes.size();i++){ 
        paciente = (Paciente)listaPacientes.get(i);
    %>
     if (frm.idPaciente.value == <%=paciente.getPers_id()%>){     
        frm.paciente.value = "<%= paciente.getNombre()%>";
     }
  <%}%>  
}

function recargar2(frm){
    frm.medico.value = "";
    <%for(int i=0;i<listaMedicos.size();i++){ 
        medico = (Medico)listaMedicos.get(i);
    %>
     if (frm.idMedico.value == <%=medico.getIdPers()%>){     
        frm.medico.value = "<%= medico.getNombre()%>";
     }
  <%}%>  
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
<form id="form1" action="ControlCita">
    <center>
  <h5><strong>             Citas</strong></h5>
    <table style="width: 90%">
      <tr>
        <td style="width: 6%;" valign="top" >
          <a href="ControlCita?accion=Buscar&parametro=<%=parametro%>&paginador=<%=paginador%>&valor=0" class="btn btn-primary btn-sm"> <strong>Volver</strong></a>
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
                        <label for="idCitas" class="form-label"><strong>Id Cita</strong></label>
                        <input type="text" class="form-control" name="idCitas" id="idCitas" value="<%= citas.getIdCitas() %>" size="10" maxlength="10" readonly>
                    </div>
                    <div class="col-md-2">
                        <label for="idPaciente" class="form-label"><strong>Id Paciente</strong></label>
                        <input type="text" class="form-control" name="idPaciente" id="idPaciente" value="<%= citas.getIdPaciente() %>" size="10" maxlength="10" onChange="javascript:recargar(document.forms['form1']);">
                    </div>
                    <div class="col-md-4">
                        <label for="paciente" class="form-label"><strong>Nombre Paciente</strong></label>
                        <input name="paciente" id="paciente" value="<%= citas.getPaciente() %>" type="text" class="form-control" size="50" maxlength="50" readonly>
                    </div>
                    <div class="col-md-2">
                        <label for="fecha" class="form-label"><strong>Fecha</strong></label>
                        <input name="fecha" id="fecha" value="<%= citas.getFecha() != null ? citas.getFecha() : "" %>" placeholder="YYYY-MM-DD" type="text" class="form-control" size="50" maxlength="50">
                        <a href="javascript:fecha.popup();"> </a>                                               
                    </div>                   
                    <div class="col-md-2">
                        <label for="horario" class="form-label"><strong>Hora</strong></label>
                        <select id="horario" name="horario" class="form-select">
                            <%
                                HorarioCitasDia horarioDia = new HorarioCitasDia();
                                if(horarios.size()>0){
                                    for(int i=0;i<horarios.size();i++){ 
                                        horarioDia = (HorarioCitasDia)horarios.get(i);
                                        if( horarioDia.getIdHorarioCitasDia()==citas.getIdHorarioCitas()) { %>
                                            <option value="<%= horarioDia.getIdHorarioCitasDia()%>" selected ><%= horarioDia.getHora()%></option>
                                    <% } else { %>
                                        <option value="<%= horarioDia.getIdHorarioCitasDia()%>"><%= horarioDia.getHora()%></option>
                                    <% } 
                                    }
                                }%>
                        </select>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-2">
                        <label for="idMedico" class="form-label"><strong>Id Medico</strong></label>
                        <input type="text" class="form-control" name="idMedico" id="idMedico" value="<%= citas.getIdMedico() %>" size="10" maxlength="10" onChange="javascript:recargar2(document.forms['form1']);">
                    </div>
                    <div class="col-md-4">
                        <label for="medico" class="form-label"><strong>Nombre Médico</strong></label>
                        <input name="medico" id="medico" value="<%= citas.getMedico() %>" type="text" class="form-control" size="50" maxlength="100" readonly="">
                    </div>
                    <div class="col-md-4">
                        <label for="idConsultorio" class="form-label"><strong>Consultorio</strong></label>
                        <select id="consultorio" name="idConsultorio" class="form-select">
                            <%
                                if(listaConsultorios.size()>0){
                                    for(int i=0;i<listaConsultorios.size();i++){ 
                                        consultorio = (Consultorio)listaConsultorios.get(i);
                                        if( consultorio.getIdConsultorio()==citas.getIdConsultorio()) { %>
                                            <option value="<%= consultorio.getIdConsultorio()%>" selected >[<%= consultorio.getCiudad()%>] <%= consultorio.getDireccion()%> [<%= consultorio.getNombre()%>]</option>
                                    <% } else { %>
                                        <option value="<%= consultorio.getIdConsultorio()%>">[<%= consultorio.getCiudad()%>] <%= consultorio.getDireccion()%> [<%= consultorio.getNombre()%>]</option>
                                    <% } 
                                    }
                                }%>
                        </select>
                    </div>

                    <div class="col-md-2">
                        <label for="idTipoCita" class="form-label"><strong>Tipo Cita</strong></label>
                        <select id="idTipoCita" name="idTipoCita" class="form-select">
                                <%
                                    TipoCita tipoCita = new TipoCita();
                                    if(tipoCitas.size()>0){
                                        for(int i=0;i<tipoCitas.size();i++){ 
                                            tipoCita = (TipoCita)tipoCitas.get(i);
                                            if( tipoCita.getIdTipoCita()==citas.getIdTipoCita()) { %>
                                                <option value="<%= tipoCita.getIdTipoCita()%>" selected ><%= tipoCita.getNombre()%></option>
                                        <% } else { %>
                                            <option value="<%= tipoCita.getIdTipoCita()%>"><%= tipoCita.getNombre()%></option>
                                        <% } 
                                        }
                                    }%>
                        </select>                    
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-8">
                        <label for="motivoConsulta" class="form-label"><strong>Motivo Consulta</strong></label>
                        <input type="text" class="form-control" name="motivoConsulta" id="motivoConsulta" value="<%= citas.getMotivoConsulta() %>" size="100" maxlength="200" onchange="javascript:validar(document.forms['form1']);">
                    </div>      
               
                    <div class="col-md-4">
                        <label for="idMotivoConsulta" class="form-label"><strong>Especialidad</strong></label>
                        <select id="idMotivoConsulta" name="idMotivoConsulta" class="form-select">
                            <%
                                MotivoConsulta motivo = new MotivoConsulta();
                                if(motivos.size()>0){
                                    for(int i=0;i<motivos.size();i++){ 
                                        motivo = (MotivoConsulta)motivos.get(i);
                                        if( motivo.getId()==citas.getIdMotivoConsulta()) { %>
                                            <option value="<%= motivo.getId()%>" selected ><%= motivo.getDescripcion()%></option>
                                    <% } else { %>
                                        <option value="<%= motivo.getId()%>"><%= motivo.getDescripcion()%></option>
                                    <% } 
                                    }
                                }%>
                        </select>
                    </div>
                </div>
            </td>
        </tr>    
        <tr>
            <td>
                <div class="row g-6" >
                    <div class="col-md-12">
                        <label for="observacion" class="form-label"><strong>Observación</strong></label>
                        <input type="text" class="form-control" name="observacion" id="observacion" value="<%= citas.getObservacion() %>" size="100" maxlength="200">
                    </div>      
                </div>
            </td>
        </tr>
    </table>  

    <table width="90%" border="0" cellspacing="1" cellpadding="2">
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
</body>

</html>




<!--
  25-09-2021  @:GRUPO DE DESARROLLO 3  &:Creación
-->
