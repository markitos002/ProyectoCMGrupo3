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
    if(request.getParameter("grabar")==null){
        grabar = "0";
    }
    //
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
      
    if(request.getParameter("hora")==null){
        citas.setHora("");
    }else{
        citas.setHora((String)request.getParameter("hora"));
    }

    if(request.getParameter("especialidad")==null){
        citas.setIdEspecialidad(0);
    }else{
        citas.setIdEspecialidad(Integer.parseInt((String)request.getParameter("idEspecialidad")));
    }
        
    if(request.getParameter("Medico") == null){
        citas.setIdMedico(0);
    }else{ 
        citas.setIdMedico(Integer.parseInt((String)request.getParameter("Medico")));
        String idEsp = (String)request.getParameter("idEspecialidad");
        citas.setEspecialidad(idEsp);   
      
        EspecialidadesMedicasDAO especialidadesDAO = new EspecialidadesMedicasDAO();
        EspecialidadesMedicas  especialidad = new EspecialidadesMedicas();
        especialidad = (EspecialidadesMedicas)especialidadesDAO.consultar(idEsp);
        citas.setEspecialidad(especialidad.getNombre());  
        
            MedicoDAO medicoDAO = new MedicoDAO();
            ArrayList<Medico> medicosPorEspecialidad = new ArrayList<>();
            medicosPorEspecialidad = (ArrayList)medicoDAO.listarConParametro(idEsp);
 
            if(medicosPorEspecialidad.size()>0){
                int esta=0;
                
                for(int i=0;i<medicosPorEspecialidad.size();i++){ 
                    Medico medico = (Medico)medicosPorEspecialidad.get(i);
                    if(medico.getIdPers() == citas.getIdMedico()){
                        citas.setIdMedico(medicosPorEspecialidad.get(i).getIdPers());
                        citas.setMedico(medicosPorEspecialidad.get(i).getNombre());
                        esta = 1;
                    }
                }
                if(esta==0){
                    citas.setIdMedico(0);
                }
           } 

    }

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
    
    TipoCitaDAO tipoCitaDAO = new TipoCitaDAO();
    TipoCita tipoCita = new TipoCita();
    
    
    if(request.getParameter("idTipoCita") == null){
        citas.setIdTipoCita(0);
    }else{
        citas.setIdTipoCita(Integer.parseInt((String)request.getParameter("idTipoCita")));
        String ticita=String.valueOf(citas.getIdConsultorio());
        tipoCita = (TipoCita)tipoCitaDAO.consultar(ticita);
        citas.setIdTipoCita(tipoCita.getIdTipoCita()); 
        citas.setTipoCita(tipoCita.getNombre()); 
       
    }
         
    if(request.getParameter("motivoConsulta")==null){
        citas.setMotivoDeConsulta("");
    }else{
        citas.setMotivoDeConsulta((String)request.getParameter("motivoConsulta"));  
    }

    if(request.getParameter("observacion")==null){
        citas.setObservacion("");
    }else{
        citas.setObservacion((String)request.getParameter("observacion"));  
    }

%>
<script language="javascript" src="../jScripts/cargaPopup.js"></script>
<script language="javascript" src="../jScripts/validaFechas.js"></script>
<script language = "javascript">
  
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
       
<form id="form1" method="POST" action="index.jsp">
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
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
            </tr>
            <tr>
                <td colspan="2"><label for="idPaciente" class="form-label"><strong>Id Paciente</strong></label></td>
                <td colspan="4"><label for="paciente" class="form-label"><strong>Nombre Paciente</strong></label></td>
                <td colspan="2"><label for="fecha" class="form-label"><strong>Fecha</strong></label></td>
                <td colspan="4"><label for="hora" class="form-label"><strong>Especialidad</strong></label></td>
            </tr>
            <tr>
                <td colspan="2"><%= citas.getIdPaciente() %></td>
                <td colspan="4"><%= citas.getPaciente() %></td>
                <td colspan="2"><%= citas.getFecha()%></td>
                <td colspan="4"><%= citas.getEspecialidad()%></td>
            </tr>
            <tr>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
            </tr>
            <tr>
                <td colspan="2"><label for="idMedico" class="form-label"><strong>Id Médico</strong></label></td>
                <td colspan="4"><label for="Medico" class="form-label"><strong>Médico</strong></label></label></td>
                <td colspan="2"><label for="hora" class="form-label"><strong>Hora</strong></label></td>
                <td colspan="4"><label for="idConsultorio" class="form-label"><strong>Consultorio</strong></label></td>
            </tr>
            <tr>
                <td colspan="2"><%= citas.getIdMedico() %></td>
                <td colspan="4"><%= citas.getMedico()%></td>
                <td colspan="2"><%= citas.getHora()%></td>
                <td colspan="4"><%= citas.getLugar()%></td>
            </tr>
            <tr>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
            </tr>
            <tr>
                <td colspan="12"><label for="tipoCita" class="form-label"><strong>Observación</strong></label></td>
            </tr>
            <tr>
                <td colspan="12"><%= citas.getObservacion() %></td>
            </tr>
            <tr>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
            </tr>
            <tr>
                <td colspan="4"><label for="tipoCita" class="form-label"><strong>Tipo Cita</strong></label></td>
                <td colspan="8"><label for="motivoConsulta" class="form-label"><strong>Motivo Consulta</strong></label></td>
            </tr>
            <tr>
                <td colspan="4"><%= citas.getTipoCita() %></td>
                <td colspan="8"><%= citas.getMotivoDeConsulta() %></td>
            <tr>
            <tr>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:8%;">&nbsp</td>
                <td style="width:9%;">&nbsp</td>
            </tr>
            <input type="hidden" name="paginador" id="paginador" maxlength="5" value="<%= paginador%>">
            <input type="hidden" name="parametro" id="parametro" maxlength="5" value="<%= parametro%>">
            <input type="hidden" name="valor" id="valor" maxlength="5" value="<%=valor%>"> 
        </table>
        <br>
        <table width="90%" border="0" cellspacing="1" cellpadding="2">
            <tr>
                <td style="width: 5%;" valign="top"> 
                    <input type="button" name="imprimir" value="Imprimir" class="btn btn-primary btn-sm" onclick="window.print();"> 
                </td>
                <td style="width: 90%;"><p class="fs-6 text-center" style="background-color: #C4E3FB; height: 2rem;"></p></td>
                <td style="width: 5%;" valign="top"> 
                    <input type="Submit" name="Salir" value="Salir" class="btn btn-primary btn-sm"> 
                </td>
            </tr>
        </table>
    </center>
</form>
</body>
</html>




<!--
  25-09-2021  @:GRUPO DE DESARROLLO 3  &:Creación
--> 