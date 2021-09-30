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
    String edit = "vistas/cita_edit.jsp";
    String ver = "vistas/cita_ver.jsp";
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
                        //JOptionPane.showMessageDialog(null, action, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);

        if (action==null){
            action="listarPagina";
        }
        if(action.equalsIgnoreCase("listarPagina")){
            acceso = listar;
        }else
            if(action.equalsIgnoreCase("add")){
                acceso = add;
            }else 
                if(action.equalsIgnoreCase("Agregar")){
                    mensaje = "";
                    //citas.setIdCitas(Integer.parseInt(request.getParameter("idCitas")));

                    citas.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
                    if(String.valueOf(citas.getIdPaciente()).equals("") && mensaje.equals("")){
                        mensaje = "El campo Cantidad Id Paciente debe tener un valor";
                    }
                    citas.setIdHorarioCitas(Integer.parseInt(request.getParameter("idHorarioCitas")));
                    if(String.valueOf(citas.getIdHorarioCitas()).equals("") && mensaje.equals("") ){
                        mensaje = "El campo Id Horario Citas debe tener un valor";
                    }
                    citas.setIdMedico(Integer.parseInt(request.getParameter("idMedico")));
                    if(String.valueOf(citas.getIdMedico()).equals("") && mensaje.equals("") ){
                        mensaje = "El campo Id Medico debe tener un valor";
                    }
                    citas.setIdConsultorio(Integer.parseInt(request.getParameter("idConsultorio")));
                    if(String.valueOf(citas.getIdConsultorio()).equals("") && mensaje.equals("")){
                        mensaje = "El campo Consultorio debe tener un valor";
                    }
                    citas.setIdTipoCita(Integer.parseInt(request.getParameter("idTipoCita")));
                    if(String.valueOf(citas.getIdTipoCita()).equals("") && mensaje.equals("")){
                       mensaje = "El campo Id Tipo Cita debe tener un valor";
                    }
                    citas.setObservacion(request.getParameter("observacion"));
                    /*if(citas.getObservacion().equals("") && mensaje.equals("") ){
                        mensaje = "El campo Observacion debe tener un valor";

                    }*/
                    citas.setFecha(request.getParameter("fecha"));
                    if(citas.getFecha().equals("") && mensaje.equals("") ){
                       mensaje = "El campo Fecha debe tener un valor";
                    }
                    citas.setMotivoConsulta(request.getParameter("motivoCOnsulta"));
                    if(citas.getMotivoConsulta().equals("") && mensaje.equals("") ){
                       mensaje = "El campo motivo de consulta debe tener un valor";
                    }
                    citas.setIdMotivoConsulta(Integer.parseInt(request.getParameter("idMotivoConsulta")));
                    if(String.valueOf(citas.getIdMotivoConsulta()).equals("") && mensaje.equals("") ){
                       mensaje = "El campo id Motivo Consulta debe tener un valor";
                    }
                    citas.setRegistradoPor("USUARIO_03");
                    if(citas.getRegistradoPor().equals("") && mensaje.equals("")){
                        mensaje = "El campo Registrado Por debe tener un valor";
                    }
      
                    if(mensaje.equals("")){
                        mensaje = dao.add(citas);
                    }
                    if (!mensaje.equals("")){
                        JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Registro agregado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
                    }
                    acceso = add;
                }else
                    if(action.equalsIgnoreCase("consultar")){
                        request.setAttribute("id", request.getParameter("id"));
                        acceso = edit;
                    }else
                        if(action.equalsIgnoreCase("Actualizar")){
                            //Integer id = Integer.parseInt((String)request.getParameter("id"));
                            
                            cambio = 0;
                            mensaje = "";
                            citas.setIdCitas(Integer.parseInt(request.getParameter("idCitas")));
                            JOptionPane.showMessageDialog(null, "'"+request.getParameter("idPaciente")+"'", "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                            if(String.valueOf(request.getParameter("idPaciente"))!=""){
                                citas.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
                            }else{
                                mensaje = "El campo idPaciente debe tener un valor.";
                            }
                            if(mensaje.equals("")){
                                if((String)request.getParameter("paciente")!=""){
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
                                    citas.setIdHorarioCitas(Integer.parseInt(request.getParameter("horario")));
                                }else{
                                    mensaje = "El campo Hora debe tener un valor.";
                                }
                            }                            
                            if(mensaje.equals("")){
                                if((String)request.getParameter("idMedico") != ""){
                                    citas.setIdHorarioCitas(Integer.parseInt(request.getParameter("idMedico")));
                                }else{
                                    mensaje = "El campo id Médico debe tener un valor.";
                                }
                            }                                  
                            if(mensaje.equals("")){
                                if((String)request.getParameter("idMedico") != ""){
                                    citas.setIdHorarioCitas(Integer.parseInt(request.getParameter("idMedico")));
                                }else{
                                    mensaje = "El campo id Médico debe tener un valor.";
                                }
                            }                                  
                            if(mensaje.equals("")){
                                if((String)request.getParameter("medico")!=""){
                                    citas.setPaciente((String)request.getParameter("medico"));
                                }else{
                                    mensaje = "El campo id Médico debe tener un valor válido.";
                                }
                            }
                            if(mensaje.equals("")){
                                if((String)request.getParameter("idConsultorio") != ""){
                                    citas.setIdHorarioCitas(Integer.parseInt(request.getParameter("idConsultorio")));
                                }else{
                                    mensaje = "El campo Consultorio debe tener un valor.";
                                }
                            }                             
                            if(mensaje.equals("")){
                                if((String)request.getParameter("idTipoCita") != ""){
                                    citas.setIdHorarioCitas(Integer.parseInt(request.getParameter("idTipoCita")));
                                }else{
                                    mensaje = "El campo Tipo Cita debe tener un valor.";
                                }
                            } 
                            if(mensaje.equals("")){
                                if((String)request.getParameter("idTipoCita") != ""){
                                    citas.setIdHorarioCitas(Integer.parseInt(request.getParameter("idTipoCita")));
                                }else{
                                    mensaje = "El campo Tipo Cita debe tener un valor.";
                                }
                            } 
                            citas.setObservacion(request.getParameter("observacion"));
                            if(mensaje.equals("")){
                                if((String)request.getParameter("motivoConsulta") != ""){
                                    citas.setIdHorarioCitas(Integer.parseInt(request.getParameter("motivoConsulta")));
                                }else{
                                    mensaje = "El campo Motivo Consulta debe tener un valor.";
                                }
                            } 
                            citas.setRegistradoPor("USUARIO_03");                    

                            if (!mensaje.equals("")){
                                JOptionPane.showMessageDialog(null, "Error: "+ mensaje, "Resultado de la Operación", JOptionPane.ERROR_MESSAGE);
                                acceso = edit;
                            }else{
                                if (cambio==1){
                                    JOptionPane.showMessageDialog(null, "Registro actualizado éxitosamente.", "Resultado de la Operación", JOptionPane.INFORMATION_MESSAGE);
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



