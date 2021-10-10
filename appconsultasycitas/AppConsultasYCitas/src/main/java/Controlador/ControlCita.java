/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Citas;
import ModeloDAO.CitasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author jacun
 */
public class ControlCita extends HttpServlet {

    String listar = "vistas/cita_listar.jsp";
    String add = "vistas/cita_add.jsp";
    String add2 = "cita_usuario.jsp";
    String edit = "vistas/cita_edit.jsp";
    String ver = "vistas/cita_ver.jsp";
    String ver2 = "vistas/cita_ver2.jsp";
    String addCita = "vistas/cita_usuario.jsp";
    Citas citas = new Citas();
    CitasDAO dao = new CitasDAO();
    int cambio = 0;
    String sql;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlCita</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlCita at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acceso = "";
        String mensaje = "";
        String action = request.getParameter("accion");
        
        String accion2 = (String)request.getParameter("accion2");
        if (action==null){
            if(request.getParameter("accion2")==null){
                action="listarPagina";
            }else{
                action="add2";      
            }
        }
        if(action.equalsIgnoreCase("listarPagina")){
            acceso = listar;
        }else
            if(action.equalsIgnoreCase("add")){
                acceso = add;
            }else 
              if(action.equalsIgnoreCase("add2")){
                    acceso = add2;
              }else 
                if(action.equalsIgnoreCase("Agregar")){
                    mensaje = "";
                    //citas.setIdCitas(Integer.parseInt(request.getParameter("idCitas")));
                        String parametro = (String)request.getParameter("parametro");
                        String paginador = (String)request.getParameter("paginador");
                        String valor = (String)request.getParameter("valor");
                        
                            if(!((String)request.getParameter("idPaciente")).equals("0")){
                                citas.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
                            }else{
                                mensaje = "El campo idPaciente debe tener un valor.";
                            }
        //JOptionPane.showMessageDialog(null, citas.getIdPaciente(), "Resultado 1 de la Operación", JOptionPane.ERROR_MESSAGE);

                            if(mensaje.equals("")){
                                if((String)request.getParameter("paciente")!= ""){
                                    citas.setPaciente((String)request.getParameter("paciente"));
                                }else{
                                    mensaje = "El campo id Paciente debe tener un valor válido.";
                                }
                            }
                            if(mensaje.equals("")){
                                if((String)request.getParameter("fecha")!= ""){
                                    citas.setFecha((String)request.getParameter("fecha"));
                                }else{
                                    mensaje = "El campo Fecha debe tener un valor.";
                                }
                            }                            
                            if(mensaje.equals("")){
                                if(!((String)request.getParameter("idEspecialidad")).equals("0")){
                                    citas.setIdEspecialidad(Integer.parseInt(request.getParameter("idEspecialidad")));
                                }else{
                                    mensaje = "El campo Especialidad debe tener un valor.";
                                }
                            }   
                            if(mensaje.equals("")){
                                if(!((String)request.getParameter("Medico")).equals("0")){
                                    citas.setIdMedico(Integer.parseInt(request.getParameter("Medico")));
                                }else{
                                    mensaje = "El campo Médico debe tener un valor.";
                                }
                            }                                                                
                            if(mensaje.equals("")){
                                if(!((String)request.getParameter("horario")).equals("0")){
                                    citas.setIdHoraCitasDia(Integer.parseInt(request.getParameter("horario")));
                                }else{
                                    mensaje = "El campo Hora debe tener un valor.";
                                }
                            }                            
                            if(mensaje.equals("")){
                                if((String)request.getParameter("idConsultorio") != ""){
                                    citas.setIdConsultorio(Integer.parseInt(request.getParameter("idConsultorio")));
                                }else{
                                    mensaje = "El campo Consultorio debe tener un valor.";
                                }
                            }                             
                            citas.setObservacion(request.getParameter("observacion"));
                            if(mensaje.equals("")){
                                if(!((String)request.getParameter("idTipoCita")).equals("0")){
                                    citas.setIdTipoCita(Integer.parseInt(request.getParameter("idTipoCita")));
                                }else{
                                    mensaje = "El campo Tipo Cita debe tener un valor.";
                                }
                            }  
                            if(mensaje.equals("")){
                                if((String)request.getParameter("motivoConsulta") != ""){
                                    citas.setMotivoDeConsulta((String)request.getParameter("motivoConsulta"));
                                    cambio = 1;
                                }else{
                                    mensaje = "El campo Motivo Consulta debe tener un valor.";
                                }
                            } 
                            
                            citas.setRegistradoPor((String)request.getParameter("usuario"));

                            if(mensaje.equals("")){
                                mensaje = dao.add(citas);
                                //JOptionPane.showMessageDialog(null, "Grabo...."+ citas.getRegistradoPor(), "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                            }
                            if (!mensaje.equals("")){
                                JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                request.getSession().setAttribute("parametro",parametro);
                                request.getSession().setAttribute("paginador",paginador);
                                request.getSession().setAttribute("valor",0);

                                String idPaciente = (String)request.getParameter("idPaciente");
                                String paciente = (String)request.getParameter("paciente");        
                                String fecha = (String)request.getParameter("fecha");        
                                String horario = (String)request.getParameter("horario");        
                                String idMedico = (String)request.getParameter("idMedico");        
                                String medico = (String)request.getParameter("medico");      
                                String idConsultorio = (String)request.getParameter("idConsultorio");        
                                String idTipoCita = (String)request.getParameter("idTipoCita");        
                                String motivoConsulta = (String)request.getParameter("motivoConsulta");        
                                //String idMotivoConsulta = (String)request.getParameter("idMotivoConsulta");
                                String observacion = (String)request.getParameter("observacion");
                                        
                                request.getSession().setAttribute("idPaciente", idPaciente);
                                request.getSession().setAttribute("paciente",paciente);
                                request.getSession().setAttribute("fecha",fecha);
                                request.getSession().setAttribute("horario",horario);
                                request.getSession().setAttribute("idMedico",idMedico);
                                request.getSession().setAttribute("medico",medico);
                                request.getSession().setAttribute("idConsultorio",idConsultorio);
                                request.getSession().setAttribute("idTipoCita",idTipoCita);
                                request.getSession().setAttribute("motivoConsulta",motivoConsulta);
                                //request.getSession().setAttribute("idMotivoConsulta",idMotivoConsulta);
                                request.getSession().setAttribute("observacion",observacion);
    
                            }else{
                                request.setAttribute("idPaciente", null);
                                request.setAttribute("paciente", "");
                                String aux = (String)request.getParameter("idPaciente");
                                /*request.setAttribute("fecha", "");
                                request.setAttribute("idEspecialidad", 0);
                                request.setAttribute("idMedico", 0);
                                request.setAttribute("medico", 0);
                                request.setAttribute("horario", 0);
                                request.setAttribute("idConsultorio", 0);
                                request.setAttribute("consultorio", "");
                                request.setAttribute("observacion", "");
                                request.setAttribute("idTipoCita", "");
                                request.setAttribute("motivoConsulta", "");*/
                                
                                
                                JOptionPane.showMessageDialog(null, "Registro agregado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                            }
                            acceso = add;
                }else
                    if(action.equalsIgnoreCase("Grabar")){
                        mensaje = "";
                        //citas.setIdCitas(Integer.parseInt(request.getParameter("idCitas")));
                            String parametro = (String)request.getParameter("parametro");
                            String paginador = (String)request.getParameter("paginador");
                            String valor = (String)request.getParameter("valor");

                                if(!((String)request.getParameter("idPaciente")).equals("0")){
                                    citas.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
                                }else{
                                    mensaje = "El campo idPaciente debe tener un valor.";
                                }
            //JOptionPane.showMessageDialog(null, citas.getIdPaciente(), "Resultado 1 de la Operación", JOptionPane.ERROR_MESSAGE);

                                if(mensaje.equals("")){
                                    if((String)request.getParameter("paciente")!= ""){
                                        citas.setPaciente((String)request.getParameter("paciente"));
                                    }else{
                                        mensaje = "El campo id Paciente debe tener un valor válido.";
                                    }
                                }
                                if(mensaje.equals("")){
                                    if((String)request.getParameter("fecha")!= ""){
                                        citas.setFecha((String)request.getParameter("fecha"));
                                    }else{
                                        mensaje = "El campo Fecha debe tener un valor.";
                                    }
                                }                            
                                if(mensaje.equals("")){
                                    if(!((String)request.getParameter("idEspecialidad")).equals("0")){
                                        citas.setIdEspecialidad(Integer.parseInt(request.getParameter("idEspecialidad")));
                                    }else{
                                        mensaje = "El campo Especialidad debe tener un valor.";
                                    }
                                }   
                                if(mensaje.equals("")){
                                    if(!((String)request.getParameter("Medico")).equals("0")){
                                        citas.setIdMedico(Integer.parseInt(request.getParameter("Medico")));
                                    }else{
                                        mensaje = "El campo Médico debe tener un valor.";
                                    }
                                }                                                                
                                if(mensaje.equals("")){
                                    if(!((String)request.getParameter("horario")).equals("0")){
                                        citas.setIdHoraCitasDia(Integer.parseInt(request.getParameter("horario")));
                                    }else{
                                        mensaje = "El campo Hora debe tener un valor.";
                                    }
                                }                            
                                if(mensaje.equals("")){
                                    if((String)request.getParameter("idConsultorio") != ""){
                                        citas.setIdConsultorio(Integer.parseInt(request.getParameter("idConsultorio")));
                                    }else{
                                        mensaje = "El campo Consultorio debe tener un valor.";
                                    }
                                }                             
                                citas.setObservacion(request.getParameter("observacion"));
                                if(mensaje.equals("")){
                                    if(!((String)request.getParameter("idTipoCita")).equals("0")){
                                        citas.setIdTipoCita(Integer.parseInt(request.getParameter("idTipoCita")));
                                    }else{
                                        mensaje = "El campo Tipo Cita debe tener un valor.";
                                    }
                                }  
                                if(mensaje.equals("")){
                                    if((String)request.getParameter("motivoConsulta") != ""){
                                        citas.setMotivoDeConsulta((String)request.getParameter("motivoConsulta"));
                                        cambio = 1;
                                    }else{
                                        mensaje = "El campo Motivo Consulta debe tener un valor.";
                                    }
                                } 

                                citas.setRegistradoPor((String)request.getParameter("usuario"));

                                if(mensaje.equals("")){
                                    mensaje = dao.add(citas);
                                    //JOptionPane.showMessageDialog(null, "Grabo...."+ citas.getRegistradoPor(), "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                }
                                if (!mensaje.equals("")){
                                    JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                    request.getSession().setAttribute("parametro",parametro);
                                    request.getSession().setAttribute("paginador",paginador);
                                    request.getSession().setAttribute("valor",0);

                                    String idPaciente = (String)request.getParameter("idPaciente");
                                    String paciente = (String)request.getParameter("paciente");        
                                    String fecha = (String)request.getParameter("fecha");        
                                    String horario = (String)request.getParameter("horario");        
                                    String idMedico = (String)request.getParameter("idMedico");        
                                    String medico = (String)request.getParameter("medico");      
                                    String idConsultorio = (String)request.getParameter("idConsultorio");        
                                    String idTipoCita = (String)request.getParameter("idTipoCita");        
                                    String motivoConsulta = (String)request.getParameter("motivoConsulta");        
                                    //String idMotivoConsulta = (String)request.getParameter("idMotivoConsulta");
                                    String observacion = (String)request.getParameter("observacion");

                                    request.getSession().setAttribute("idPaciente", idPaciente);
                                    request.getSession().setAttribute("paciente",paciente);
                                    request.getSession().setAttribute("fecha",fecha);
                                    request.getSession().setAttribute("horario",horario);
                                    request.getSession().setAttribute("idMedico",idMedico);
                                    request.getSession().setAttribute("medico",medico);
                                    request.getSession().setAttribute("idConsultorio",idConsultorio);
                                    request.getSession().setAttribute("idTipoCita",idTipoCita);
                                    request.getSession().setAttribute("motivoConsulta",motivoConsulta);
                                    //request.getSession().setAttribute("idMotivoConsulta",idMotivoConsulta);
                                    request.getSession().setAttribute("observacion",observacion);
                                    acceso = add2;
                                }else{
                                    request.setAttribute("idPaciente", null);
                                    request.setAttribute("paciente", "");
                                    String aux = (String)request.getParameter("idPaciente");
                                    /*request.setAttribute("fecha", "");
                                    request.setAttribute("idEspecialidad", 0);
                                    request.setAttribute("idMedico", 0);
                                    request.setAttribute("medico", 0);
                                    request.setAttribute("horario", 0);
                                    request.setAttribute("idConsultorio", 0);
                                    request.setAttribute("consultorio", "");
                                    request.setAttribute("observacion", "");
                                    request.setAttribute("idTipoCita", "");
                                    request.setAttribute("motivoConsulta", "");*/


                                    JOptionPane.showMessageDialog(null, "Registro agregado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                                    acceso = ver2;
                                }

                    }else
                    if(action.equalsIgnoreCase("consultar")){
                        request.setAttribute("id", request.getParameter("id"));
                        acceso = edit;
                    }else
                        if(action.equalsIgnoreCase("Actualizar")){
                            //Integer id = Integer.parseInt((String)request.getParameter("id"));
                            String parametro = (String)request.getParameter("parametro");
                            String paginador = (String)request.getParameter("paginador");
                            String valor = (String)request.getParameter("valor");
                            String id = (String)request.getParameter("id");
                            cambio = 0;
                            mensaje = "";
                            citas.setIdCitas(Integer.parseInt(request.getParameter("id")));
                            //JOptionPane.showMessageDialog(null, "'"+request.getParameter("idPaciente")+"'", "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                            if(String.valueOf(request.getParameter("idPaciente"))!= ""){
                                citas.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
                            }else{
                                mensaje = "El campo idPaciente debe tener un valor.";
                            }
                            if(mensaje.equals("")){
                                if((String)request.getParameter("paciente")!= ""){
                                    citas.setPaciente((String)request.getParameter("paciente"));
                                }else{
                                    mensaje = "El campo id Paciente debe tener un valor válido.";
                                }
                            }
                            if(mensaje.equals("")){
                                if((String)request.getParameter("fecha")!= ""){
                                    citas.setFecha((String)request.getParameter("fecha"));
                                }else{
                                    mensaje = "El campo Fecha debe tener un valor.";
                                }
                            }                            
                            if(mensaje.equals("")){
                                if((String)request.getParameter("horario")!= ""){
                                    citas.setIdHoraCitasDia(Integer.parseInt(request.getParameter("horario")));
                                }else{
                                    mensaje = "El campo Hora debe tener un valor.";
                                }
                            }                            
                            if(mensaje.equals("")){
                                if((String)request.getParameter("idMedico") != ""){
                                    citas.setIdMedico(Integer.parseInt(request.getParameter("idMedico")));
                                }else{
                                    mensaje = "El campo id Médico debe tener un valor.";
                                }
                            }                                                                
                            if(mensaje.equals("")){
                                if((String)request.getParameter("medico") != ""){
                                    citas.setPaciente((String)request.getParameter("medico"));
                                }else{
                                    mensaje = "El campo id Médico debe tener un valor válido.";
                                }
                            }
                            if(mensaje.equals("")){
                                if((String)request.getParameter("idConsultorio") != ""){
                                    citas.setIdConsultorio(Integer.parseInt(request.getParameter("idConsultorio")));
                                }else{
                                    mensaje = "El campo Consultorio debe tener un valor.";
                                }
                            }                             
                            if(mensaje.equals("")){
                                if((String)request.getParameter("idTipoCita") != ""){
                                    citas.setIdTipoCita(Integer.parseInt(request.getParameter("idTipoCita")));
                                }else{
                                    mensaje = "El campo Tipo Cita debe tener un valor.";
                                }
                            }  
                            citas.setObservacion(request.getParameter("observacion"));
                            if(mensaje.equals("")){
                                if((String)request.getParameter("motivoConsulta") != ""){
                                    citas.setMotivoDeConsulta((String)request.getParameter("motivoConsulta"));
                                }else{
                                    mensaje = "El campo Motivo Consulta debe tener un valor.";
                                }
                            } 

                            citas.setRegistradoPor("USUARIO_03");                    

                            if(mensaje.equals("")){
                                mensaje = dao.edit(citas);
                            }
                            if (!mensaje.equals("")){
                                request.getSession().setAttribute("parametro",parametro);
                                request.getSession().setAttribute("paginador",paginador);
                                request.getSession().setAttribute("id", id);
                                
                                if(String.valueOf(request.getParameter("idPaciente"))!= ""){
                                    citas.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
                                    request.getSession().setAttribute("idPaciente", request.getParameter("idPaciente"));
                                }else{
                                    mensaje = "El campo idPaciente debe tener un valor.";
                                    request.getSession().setAttribute("idPaciente", null);
                                }
                                String paciente = (String)request.getParameter("paciente");        
                                String fecha = (String)request.getParameter("fecha");        
                                String horario = (String)request.getParameter("horario");        
                                //String idMedico = (String)request.getParameter("idMedico");        

                                if(String.valueOf(request.getParameter("idMedico"))!= ""){
                                    citas.setIdMedico(Integer.parseInt(request.getParameter("idMedico")));
                                    request.getSession().setAttribute("idMedico", request.getParameter("idMedico"));
                                }else{
                                    mensaje = "El campo idPaciente debe tener un valor.";
                                    request.getSession().setAttribute("idMedico", null);
                                }


                                String medico = (String)request.getParameter("medico");      
                                String idConsultorio = (String)request.getParameter("idConsultorio");        
                                String idTipoCita = (String)request.getParameter("idTipoCita");        
                                String motivoConsulta = (String)request.getParameter("motivoConsulta");        
                                //String idMotivoConsulta = (String)request.getParameter("idMotivoConsulta");
                                String observacion = (String)request.getParameter("observacion");
                                        
                                //request.getSession().setAttribute("idPaciente", idPaciente);
                                request.getSession().setAttribute("paciente",paciente);
                                request.getSession().setAttribute("fecha",fecha);
                                request.getSession().setAttribute("horario",horario);
                                //request.getSession().setAttribute("idMedico",idMedico);
                                request.getSession().setAttribute("medico",medico);
                                request.getSession().setAttribute("idConsultorio",idConsultorio);
                                request.getSession().setAttribute("idTipoCita",idTipoCita);
                                request.getSession().setAttribute("motivoConsulta",motivoConsulta);
                                //request.getSession().setAttribute("idMotivoConsulta",idMotivoConsulta);
                                request.getSession().setAttribute("observacion",observacion);
                                JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                acceso = edit;
                            }else{
                                if (cambio==1){
                                    JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                                }
                                acceso = listar;
                            }
                            
                            
                        }else 
                            if(action.equalsIgnoreCase("eliminar")){
                                    Integer id = Integer.parseInt(request.getParameter("id"));
                                    String parametro = (String)(request.getParameter("parametro"));
                                    mensaje = dao.elim(id);

                                    if (!mensaje.equals("")){
                                        //containsStr = mensaje.contains(subStr);
                                        //if(containsStr){
                                            JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                        //}
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Registro eliminado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                                    }

                                    acceso = listar;
                            }else 
                                if(action.equalsIgnoreCase("Buscar")){                                 
                                        acceso = listar;
                                }else
                                    if(action.equalsIgnoreCase("Ver")){                                 
                                        acceso = ver;
                                    }else
                                        if(action.equalsIgnoreCase("addCita")){                                 
                                            acceso = addCita;
                                        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}



